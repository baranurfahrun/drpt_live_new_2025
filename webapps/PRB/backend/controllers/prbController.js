const db = require('../config/db');

/**
 * Get patients with pagination and filtering
 */
exports.getPatients = async (req, res) => {
    try {
        console.log('📥 getPatients called with query:', req.query);

        const {
            page = 1,
            limit = 10,
            search = '',
            date_from = '',
            date_to = '',
            prb_filter = '',
            prb_document_filter = ''
        } = req.query;

        // Validate inputs
        const pageNum = Math.max(1, parseInt(page));
        const params = [];

        console.log('🔍 Building query with params:', { page: pageNum, search, date_from, date_to, prb_filter, prb_document_filter });

        // Build base query with UNION
        const baseQuery = `(SELECT 
                    bs.no_sep,
                    bs.tglsep,
                    bs.no_kartu,
                    bs.nama_pasien,
                    bs.no_rawat,
                    bs.nomr as norm,
                    bs.catatan,
                    bs.tanggal_lahir,
                    bs.peserta,
                    bs.diagawal,
                    bs.nmdiagnosaawal,
                    bs.klsrawat,
                    bs.notelep,
                    bs.tglrujukan,
                    bs.no_rujukan,
                    bs.nmppkrujukan,
                    bs.jkel,
                    bs.user as petugas_pembuat_sep,
                    COALESCE(prb.prb, 'Tidak') as prb_status
                FROM bridging_sep bs
                LEFT JOIN bpjs_prb prb ON bs.no_sep = prb.no_sep
                
                UNION ALL
                
                SELECT 
                    bsi.no_sep,
                    bsi.tglsep,
                    bsi.no_kartu,
                    bsi.nama_pasien,
                    bsi.no_rawat,
                    bsi.nomr as norm,
                    bsi.catatan,
                    bsi.tanggal_lahir,
                    bsi.peserta,
                    bsi.diagawal,
                    bsi.nmdiagnosaawal,
                    bsi.klsrawat,
                    bsi.notelep,
                    bsi.tglrujukan,
                    bsi.no_rujukan,
                    bsi.nmppkrujukan,
                    bsi.jkel,
                    bsi.user as petugas_pembuat_sep,
                    COALESCE(prb.prb, 'Tidak') as prb_status
                FROM bridging_sep_internal bsi
                LEFT JOIN bpjs_prb prb ON bsi.no_sep = prb.no_sep
                ) as combined_data`;

        // Build WHERE conditions
        const whereConditions = [];

        if (date_from) {
            whereConditions.push('combined_data.tglsep >= ?');
            params.push(date_from);
        }

        if (date_to) {
            whereConditions.push('combined_data.tglsep <= ?');
            params.push(date_to);
        }

        // PRB filter
        if (prb_filter) {
            switch (prb_filter) {
                case 'prb':
                    whereConditions.push("combined_data.prb_status LIKE 'PRB :%'");
                    break;
                case 'tidak_prb':
                    whereConditions.push("combined_data.prb_status = 'Tidak'");
                    break;
                case 'potensi_prb':
                    whereConditions.push("combined_data.prb_status = 'Potensi PRB'");
                    break;
            }
        }

        const whereClause = whereConditions.length > 0
            ? 'WHERE ' + whereConditions.join(' AND ')
            : '';

        // Search condition
        let searchCondition = '';
        if (search) {
            searchCondition = whereConditions.length > 0 ? ' AND ' : ' WHERE ';
            searchCondition += `(combined_data.no_kartu LIKE ? OR 
                                combined_data.nama_pasien LIKE ? OR 
                                combined_data.no_sep LIKE ? OR 
                                combined_data.no_rawat LIKE ? OR 
                                combined_data.norm LIKE ? OR 
                                srb.no_srb LIKE ?)`;
            const searchParam = `%${search}%`;
            params.push(searchParam, searchParam, searchParam, searchParam, searchParam, searchParam);
        }

        // PRB document filter
        let prbDocumentCondition = '';
        if (prb_document_filter) {
            prbDocumentCondition = (whereConditions.length > 0 || search) ? ' AND ' : ' WHERE ';
            switch (prb_document_filter) {
                case 'has_prb_number':
                    prbDocumentCondition += "(srb.no_srb IS NOT NULL AND srb.no_srb != '' AND srb.no_srb != '-')";
                    break;
                case 'no_prb_number':
                    prbDocumentCondition += "(srb.no_srb IS NULL OR srb.no_srb = '' OR srb.no_srb = '-')";
                    break;
            }
        }

        // Count total records
        const countSql = `SELECT COUNT(DISTINCT combined_data.no_sep) as total 
                    FROM ${baseQuery}
                    LEFT JOIN reg_periksa r ON combined_data.no_rawat = r.no_rawat
                    LEFT JOIN poliklinik pol ON r.kd_poli = pol.kd_poli
                    LEFT JOIN dokter d ON r.kd_dokter = d.kd_dokter
                    LEFT JOIN bridging_srb_bpjs srb ON combined_data.no_sep = srb.no_sep
                    ${whereClause} ${searchCondition} ${prbDocumentCondition}`;

        const [countResult] = await db.query(countSql, params);
        const totalRecords = countResult[0].total;

        console.log('📊 Total records found:', totalRecords);

        // Get data
        const sql = `SELECT 
                    combined_data.no_sep,
                    combined_data.tglsep,
                    combined_data.no_kartu,
                    combined_data.nama_pasien,
                    pol.nm_poli,
                    d.nm_dokter,
                    combined_data.prb_status,
                    combined_data.no_rawat,
                    combined_data.norm,
                    combined_data.catatan,
                    combined_data.tanggal_lahir,
                    combined_data.peserta,
                    combined_data.diagawal,
                    combined_data.nmdiagnosaawal,
                    combined_data.klsrawat,
                    combined_data.notelep,
                    combined_data.tglrujukan,
                    combined_data.no_rujukan,
                    combined_data.nmppkrujukan,
                    combined_data.jkel,
                    combined_data.petugas_pembuat_sep,
                    srb.no_srb as no_surat_prb,
                    srb.tgl_srb as tanggal_prb,
                    srb.nmdpjp as dokter_dpjp,
                    srb.namaprogram as diagnosa_prb,
                    srb.user as petugas_pembuat_prb,
                    GROUP_CONCAT(DISTINCT CONCAT(bo.nm_obat, ' (', ' jumlah: ', bo.jumlah, ' | ', ' S1: ', bo.signa1, ' | ', ' S2: ', bo.signa2, ')') SEPARATOR '\\n') as obat_details
                FROM ${baseQuery}
                LEFT JOIN reg_periksa r ON combined_data.no_rawat = r.no_rawat
                LEFT JOIN poliklinik pol ON r.kd_poli = pol.kd_poli
                LEFT JOIN dokter d ON r.kd_dokter = d.kd_dokter
                LEFT JOIN bridging_srb_bpjs srb ON combined_data.no_sep = srb.no_sep
                LEFT JOIN bridging_srb_bpjs_obat bo ON combined_data.no_sep = bo.no_sep
                ${whereClause} ${searchCondition} ${prbDocumentCondition}
                GROUP BY combined_data.no_sep, combined_data.tglsep, combined_data.no_kartu, combined_data.nama_pasien, 
                         pol.nm_poli, d.nm_dokter, combined_data.prb_status, combined_data.no_rawat, combined_data.norm,
                         combined_data.catatan, combined_data.tanggal_lahir, combined_data.peserta, combined_data.diagawal,
                         combined_data.nmdiagnosaawal, combined_data.klsrawat, combined_data.notelep, combined_data.tglrujukan,
                         combined_data.no_rujukan, combined_data.nmppkrujukan, combined_data.jkel, combined_data.petugas_pembuat_sep,
                         srb.no_srb, srb.tgl_srb, srb.nmdpjp, srb.namaprogram, srb.user
                ORDER BY combined_data.tglsep DESC`;

        const [patients] = await db.query(sql, params);

        console.log('✅ Query executed, patients found:', patients.length);
        console.log('📤 Sending response with', patients.length, 'patients');

        res.json({
            success: true,
            data: patients,
            pagination: {
                current_page: 1,
                total_pages: 1,
                total_records: totalRecords,
                limit: totalRecords > 0 ? totalRecords : 1,
                offset: 0,
                has_next: false,
                has_prev: false
            },
            filters: {
                search,
                date_from,
                date_to,
                prb_filter,
                prb_document_filter
            },
            message: 'Data pasien berhasil diambil'
        });

    } catch (error) {
        console.error('Error in getPatients:', error);
        res.status(500).json({
            success: false,
            data: [],
            pagination: {
                current_page: 1,
                total_pages: 0,
                total_records: 0,
                limit: 10
            },
            message: 'Error: ' + error.message
        });
    }
};

/**
 * Get patient details
 */
exports.getPatientDetails = async (req, res) => {
    try {
        const { no_sep } = req.params;

        if (!no_sep) {
            return res.status(400).json({
                success: false,
                message: 'No SEP is required'
            });
        }

        // Query untuk mendapatkan detail pasien
        const sql = `SELECT 
                    bs.no_sep,
                    bs.tglsep,
                    bs.no_kartu,
                    bs.nama_pasien,
                    bs.no_rawat,
                    bs.nomr as norm,
                    bs.catatan,
                    bs.tanggal_lahir,
                    bs.peserta,
                    bs.diagawal,
                    bs.nmdiagnosaawal,
                    bs.klsrawat,
                    bs.notelep,
                    bs.tglrujukan,
                    bs.no_rujukan,
                    bs.nmppkrujukan,
                    bs.jkel,
                    bs.user as petugas_pembuat_sep,
                    COALESCE(prb.prb, 'Tidak') as prb_status,
                    pol.nm_poli,
                    d.nm_dokter,
                    srb.no_srb as no_surat_prb,
                    srb.tgl_srb as tanggal_prb,
                    srb.nmdpjp as dokter_dpjp,
                    srb.namaprogram as diagnosa_prb,
                    srb.user as petugas_pembuat_prb
                FROM bridging_sep bs
                LEFT JOIN bpjs_prb prb ON bs.no_sep = prb.no_sep
                LEFT JOIN reg_periksa r ON bs.no_rawat = r.no_rawat
                LEFT JOIN poliklinik pol ON r.kd_poli = pol.kd_poli
                LEFT JOIN dokter d ON r.kd_dokter = d.kd_dokter
                LEFT JOIN bridging_srb_bpjs srb ON bs.no_sep = srb.no_sep
                WHERE bs.no_sep = ?
                
                UNION ALL
                
                SELECT 
                    bsi.no_sep,
                    bsi.tglsep,
                    bsi.no_kartu,
                    bsi.nama_pasien,
                    bsi.no_rawat,
                    bsi.nomr as norm,
                    bsi.catatan,
                    bsi.tanggal_lahir,
                    bsi.peserta,
                    bsi.diagawal,
                    bsi.nmdiagnosaawal,
                    bsi.klsrawat,
                    bsi.notelep,
                    bsi.tglrujukan,
                    bsi.no_rujukan,
                    bsi.nmppkrujukan,
                    bsi.jkel,
                    bsi.user as petugas_pembuat_sep,
                    COALESCE(prb.prb, 'Tidak') as prb_status,
                    pol.nm_poli,
                    d.nm_dokter,
                    srb.no_srb as no_surat_prb,
                    srb.tgl_srb as tanggal_prb,
                    srb.nmdpjp as dokter_dpjp,
                    srb.namaprogram as diagnosa_prb,
                    srb.user as petugas_pembuat_prb
                FROM bridging_sep_internal bsi
                LEFT JOIN bpjs_prb prb ON bsi.no_sep = prb.no_sep
                LEFT JOIN reg_periksa r ON bsi.no_rawat = r.no_rawat
                LEFT JOIN poliklinik pol ON r.kd_poli = pol.kd_poli
                LEFT JOIN dokter d ON r.kd_dokter = d.kd_dokter
                LEFT JOIN bridging_srb_bpjs srb ON bsi.no_sep = srb.no_sep
                WHERE bsi.no_sep = ?`;

        const [patients] = await db.query(sql, [no_sep, no_sep]);

        if (patients.length === 0) {
            return res.status(404).json({
                success: false,
                message: 'Patient not found'
            });
        }

        // Get obat details
        const obatSql = `SELECT 
                        nm_obat,
                        jumlah,
                        signa1,
                        signa2
                    FROM bridging_srb_bpjs_obat
                    WHERE no_sep = ?`;

        const [obatList] = await db.query(obatSql, [no_sep]);

        res.json({
            success: true,
            data: {
                ...patients[0],
                obat_list: obatList
            },
            message: 'Detail pasien berhasil diambil'
        });

    } catch (error) {
        console.error('Error in getPatientDetails:', error);
        res.status(500).json({
            success: false,
            message: 'Error: ' + error.message
        });
    }
};
