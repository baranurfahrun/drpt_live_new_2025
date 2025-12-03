<?php
class Prb {
    private $pdo;
    
    public function __construct($pdo = null) {
        $this->pdo = $pdo;
    }
    
    /**
     * Get patients with pagination and filtering
     * FIXED: Improved pagination, search by No. Surat PRB, and filtering
     * NEW: Added PRB Document Filter, Unique Patient Filter, and Poliklinik Filter
     */
    public function getPatients($page = 1, $limit = 10, $search = '', $date_from = '', $date_to = '', $prb_filter = '', $prb_document_filter = '', $unique_patient_filter = '0', $poli_filter = '') {
        try {
            if (!$this->pdo) {
                throw new Exception("Database connection not available");
            }
            
            // Validate and sanitize inputs
            $page = max(1, (int)$page);
            $isUnlimited = ((int)$limit >= 1000000);
            
            if ($isUnlimited) {
                $limit = 999999999; // Very large number for unlimited
                $offset = 0;
            } else {
                $limit = max(1, min(1000, (int)$limit));
                $offset = ($page - 1) * $limit;
            }
            
            // Build parameters array
            $params = [];
            
            // Build the base query with UNION
            $baseQuery = "(SELECT 
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
                    ) as combined_data";
            
            // Build WHERE conditions
            $whereConditions = [];
            
            // Add date range conditions
            if (!empty($date_from)) {
                $whereConditions[] = "combined_data.tglsep >= :date_from";
                $params['date_from'] = $date_from;
            }
            
            if (!empty($date_to)) {
                $whereConditions[] = "combined_data.tglsep <= :date_to";
                $params['date_to'] = $date_to;
            }
            
            // Add PRB filter conditions
            if (!empty($prb_filter)) {
                switch ($prb_filter) {
                    case 'prb':
                        $whereConditions[] = "(combined_data.prb_status LIKE 'PRB :%')";
                        break;
                    case 'tidak_prb':
                        $whereConditions[] = "(combined_data.prb_status = 'Tidak')";
                        break;
                    case 'potensi_prb':
                        $whereConditions[] = "(combined_data.prb_status = 'Potensi PRB')";
                        break;
                    case 'prb_dan_potensi':
                        $whereConditions[] = "(combined_data.prb_status LIKE 'PRB :%' OR combined_data.prb_status = 'Potensi PRB')";
                        break;
                }
            }
            
            
            // Add Poliklinik filter conditions
            if (!empty($poli_filter)) {
                if ($poli_filter === 'poli_prb') {
                    // Filter for PRB Polyclinics: Jantung, Dalam, Paru, Syaraf
                    $whereConditions[] = "(pol.nm_poli LIKE '%Jantung%' OR pol.nm_poli LIKE '%Dalam%' OR pol.nm_poli LIKE '%Paru%' OR pol.nm_poli LIKE '%Syaraf%')";
                } elseif ($poli_filter === 'non_poli_prb') {
                    // Filter for Non-PRB Polyclinics: Exclude Jantung, Dalam, Paru, Syaraf
                    $whereConditions[] = "(pol.nm_poli NOT LIKE '%Jantung%' AND pol.nm_poli NOT LIKE '%Dalam%' AND pol.nm_poli NOT LIKE '%Paru%' AND pol.nm_poli NOT LIKE '%Syaraf%')";
                } else {
                    // Filter by specific poliklinik code
                    $whereConditions[] = "pol.kd_poli = :poli_filter";
                    $params['poli_filter'] = $poli_filter;
                }
            }
            
            $whereClause = !empty($whereConditions) ? 'WHERE ' . implode(' AND ', $whereConditions) : '';
            
            // FIXED: Improved search query to include No. Surat PRB and fix search by No Rawat, No RM, No. Kartu
            $searchCondition = '';
            if (!empty($search)) {
                $searchCondition = !empty($whereConditions) ? ' AND ' : ' WHERE ';
                $searchCondition .= "(combined_data.no_kartu LIKE :search OR 
                                    combined_data.nama_pasien LIKE :search OR 
                                    combined_data.no_sep LIKE :search OR 
                                    combined_data.no_rawat LIKE :search OR 
                                    combined_data.norm LIKE :search OR 
                                    srb.no_srb LIKE :search)";
                $params['search'] = '%' . $search . '%';
            }
            
            // NEW: Add PRB Document Filter conditions
            $prbDocumentCondition = '';
            if (!empty($prb_document_filter)) {
                $prbDocumentCondition = (!empty($whereConditions) || !empty($search)) ? ' AND ' : ' WHERE ';
                switch ($prb_document_filter) {
                    case 'has_prb_number':
                        $prbDocumentCondition .= "(srb.no_srb IS NOT NULL AND srb.no_srb != '' AND srb.no_srb != '-')";
                        break;
                    case 'no_prb_number':
                        $prbDocumentCondition .= "(srb.no_srb IS NULL OR srb.no_srb = '' OR srb.no_srb = '-')";
                        break;
                }
            }
            
            // NEW: Add Unique Patient Filter condition
            $uniquePatientCondition = '';
            if ($unique_patient_filter === '1') {
                // Add condition to get only the latest record per no_kartu
                $uniquePatientCondition = (!empty($whereConditions) || !empty($search) || !empty($prb_document_filter)) ? ' AND ' : ' WHERE ';
                $uniquePatientCondition .= "combined_data.no_sep IN (
                    SELECT sub.no_sep FROM (
                        SELECT no_sep, no_kartu, tglsep,
                               ROW_NUMBER() OVER (PARTITION BY no_kartu ORDER BY tglsep DESC) as rn
                        FROM $baseQuery
                    ) sub WHERE sub.rn = 1
                )";
            }
            
            // Count total records with proper JOIN for search
            $countSql = "SELECT COUNT(DISTINCT combined_data.no_sep) as total 
                        FROM $baseQuery
                        LEFT JOIN reg_periksa r ON combined_data.no_rawat = r.no_rawat
                        LEFT JOIN poliklinik pol ON r.kd_poli = pol.kd_poli
                        LEFT JOIN dokter d ON r.kd_dokter = d.kd_dokter
                        LEFT JOIN bridging_srb_bpjs srb ON combined_data.no_sep = srb.no_sep
                        $whereClause $searchCondition $prbDocumentCondition $uniquePatientCondition";
            
            $countStmt = $this->pdo->prepare($countSql);
            $countStmt->execute($params);
            $totalRecords = $countStmt->fetch(PDO::FETCH_ASSOC)['total'];
            
            // Get paginated data
            $sql = "SELECT 
                        combined_data.no_sep,
                        combined_data.tglsep,
                        r.tgl_registrasi,
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
                        GROUP_CONCAT(CONCAT(bo.nm_obat, ' | Jumlah: ', COALESCE(bo.jumlah, 0), ' | Signa 1: ', COALESCE(bo.signa1, '-'), ' | Signa 2: ', COALESCE(bo.signa2, '-')) ORDER BY bo.nm_obat SEPARATOR '\n') as obat_details
                    FROM $baseQuery
                    LEFT JOIN reg_periksa r ON combined_data.no_rawat = r.no_rawat
                    LEFT JOIN poliklinik pol ON r.kd_poli = pol.kd_poli
                    LEFT JOIN dokter d ON r.kd_dokter = d.kd_dokter
                    LEFT JOIN bridging_srb_bpjs srb ON combined_data.no_sep = srb.no_sep
                    LEFT JOIN bridging_srb_bpjs_obat bo ON srb.no_srb = bo.no_srb
                    $whereClause $searchCondition $prbDocumentCondition $uniquePatientCondition
                    GROUP BY combined_data.no_sep, combined_data.tglsep, r.tgl_registrasi, combined_data.no_kartu, combined_data.nama_pasien, 
                             pol.nm_poli, d.nm_dokter, combined_data.prb_status, combined_data.no_rawat, combined_data.norm,
                             combined_data.catatan, combined_data.tanggal_lahir, combined_data.peserta, combined_data.diagawal,
                             combined_data.nmdiagnosaawal, combined_data.klsrawat, combined_data.notelep, combined_data.tglrujukan,
                             combined_data.no_rujukan, combined_data.nmppkrujukan, combined_data.jkel, combined_data.petugas_pembuat_sep,
                             srb.no_srb, srb.tgl_srb, srb.nmdpjp, srb.namaprogram, srb.user
                    ORDER BY combined_data.tglsep DESC";
            
            // Add LIMIT only if not unlimited
            if (!$isUnlimited) {
                $sql .= " LIMIT :limit OFFSET :offset";
            }
            
            $stmt = $this->pdo->prepare($sql);
            
            // Bind parameters
            foreach ($params as $key => $value) {
                $stmt->bindValue(':' . $key, $value);
            }
            
            // Bind limit and offset only if not unlimited
            if (!$isUnlimited) {
                $stmt->bindValue(':limit', (int)$limit, PDO::PARAM_INT);
                $stmt->bindValue(':offset', (int)$offset, PDO::PARAM_INT);
            }
            
            $stmt->execute();
            $patients = $stmt->fetchAll(PDO::FETCH_ASSOC);
            
            // Calculate pagination info
            $totalPages = $isUnlimited ? 1 : ceil($totalRecords / $limit);
            
            return [
                'success' => true,
                'data' => $patients,
                'pagination' => [
                    'current_page' => (int)$page,
                    'total_pages' => (int)$totalPages,
                    'total_records' => (int)$totalRecords,
                    'limit' => (int)$limit,
                    'offset' => (int)$offset,
                    'has_next' => $page < $totalPages,
                    'has_prev' => $page > 1
                ],
                'filters' => [
                    'search' => $search,
                    'date_from' => $date_from,
                    'date_to' => $date_to,
                    'prb_filter' => $prb_filter,
                    'prb_document_filter' => $prb_document_filter // NEW
                ],
                'message' => 'Data pasien berhasil diambil'
            ];
            
        } catch (Exception $e) {
            return [
                'success' => false,
                'data' => [],
                'pagination' => [
                    'current_page' => 1,
                    'total_pages' => 0,
                    'total_records' => 0,
                    'limit' => $limit
                ],
                'message' => 'Error: ' . $e->getMessage()
            ];
        }
    }
    
    /**
     * Get list of all polyclinics
     */
    public function getPoliklinikList() {
        try {
            if (!$this->pdo) {
                throw new Exception("Database connection not available");
            }
            
            $sql = "SELECT kd_poli, nm_poli FROM poliklinik WHERE status='1' ORDER BY nm_poli ASC";
            $stmt = $this->pdo->prepare($sql);
            $stmt->execute();
            
            return [
                'success' => true,
                'data' => $stmt->fetchAll(PDO::FETCH_ASSOC)
            ];
        } catch (Exception $e) {
            return [
                'success' => false,
                'message' => 'Error: ' . $e->getMessage()
            ];
        }
    }
    
    /**
     * Export patients data to Excel format
     * FIXED: Improved export with proper filtering and No. Surat PRB search
     * NEW: Added PRB Document Filter, Unique Patient Filter, and Poliklinik Filter support
     */
    public function exportToExcel($search = '', $date_from = '', $date_to = '', $prb_filter = '', $prb_document_filter = '', $unique_patient_filter = '0', $poli_filter = '', $limit = 0) {
        try {
            if (!$this->pdo) {
                throw new Exception("Database connection not available");
            }
            
            // Build parameters array
            $params = [];
            
            // Build the base query with UNION
            $baseQuery = "(SELECT 
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
                    ) as combined_data";
            
            // Build WHERE conditions
            $whereConditions = [];
            
            // Add date range conditions
            if (!empty($date_from)) {
                $whereConditions[] = "combined_data.tglsep >= :date_from";
                $params['date_from'] = $date_from;
            }
            
            if (!empty($date_to)) {
                $whereConditions[] = "combined_data.tglsep <= :date_to";
                $params['date_to'] = $date_to;
            }
            
            // Add PRB filter conditions
            if (!empty($prb_filter)) {
                switch ($prb_filter) {
                    case 'prb':
                        $whereConditions[] = "(combined_data.prb_status LIKE 'PRB :%')";
                        break;
                    case 'tidak_prb':
                        $whereConditions[] = "(combined_data.prb_status = 'Tidak')";
                        break;
                    case 'potensi_prb':
                        $whereConditions[] = "(combined_data.prb_status = 'Potensi PRB')";
                        break;
                    case 'prb_dan_potensi':
                        $whereConditions[] = "(combined_data.prb_status LIKE 'PRB :%' OR combined_data.prb_status = 'Potensi PRB')";
                        break;
                }
            }


            // Add Poliklinik filter conditions
            if (!empty($poli_filter)) {
                if ($poli_filter === 'poli_prb') {
                    // Filter for PRB Polyclinics: Jantung, Dalam, Paru, Syaraf
                    $whereConditions[] = "(pol.nm_poli LIKE '%Jantung%' OR pol.nm_poli LIKE '%Dalam%' OR pol.nm_poli LIKE '%Paru%' OR pol.nm_poli LIKE '%Syaraf%')";
                } elseif ($poli_filter === 'non_poli_prb') {
                    // Filter for Non-PRB Polyclinics: Exclude Jantung, Dalam, Paru, Syaraf
                    $whereConditions[] = "(pol.nm_poli NOT LIKE '%Jantung%' AND pol.nm_poli NOT LIKE '%Dalam%' AND pol.nm_poli NOT LIKE '%Paru%' AND pol.nm_poli NOT LIKE '%Syaraf%')";
                } else {
                    // Filter by specific poliklinik code
                    $whereConditions[] = "pol.kd_poli = :poli_filter";
                    $params['poli_filter'] = $poli_filter;
                }
            }
            
            $whereClause = !empty($whereConditions) ? 'WHERE ' . implode(' AND ', $whereConditions) : '';
            
            // FIXED: Add search condition for No. Surat PRB in export and fix search by No Rawat, No RM, No. Kartu
            $searchCondition = '';
            if (!empty($search)) {
                $searchCondition = !empty($whereConditions) ? ' AND ' : ' WHERE ';
                $searchCondition .= "(combined_data.no_kartu LIKE :search OR 
                                    combined_data.nama_pasien LIKE :search OR 
                                    combined_data.no_sep LIKE :search OR 
                                    combined_data.no_rawat LIKE :search OR 
                                    combined_data.norm LIKE :search OR 
                                    srb.no_srb LIKE :search)";
                $params['search'] = '%' . $search . '%';
            }
            
            // NEW: Add PRB Document Filter conditions for export
            $prbDocumentCondition = '';
            if (!empty($prb_document_filter)) {
                $prbDocumentCondition = (!empty($whereConditions) || !empty($search)) ? ' AND ' : ' WHERE ';
                switch ($prb_document_filter) {
                    case 'has_prb_number':
                        $prbDocumentCondition .= "(srb.no_srb IS NOT NULL AND srb.no_srb != '' AND srb.no_srb != '-')";
                        break;
                    case 'no_prb_number':
                        $prbDocumentCondition .= "(srb.no_srb IS NULL OR srb.no_srb = '' OR srb.no_srb = '-')";
                        break;
                }
            }

            // NEW: Add Unique Patient Filter condition for export
            $uniquePatientCondition = '';
            if ($unique_patient_filter === '1') {
                // Add condition to get only the latest record per no_kartu
                $uniquePatientCondition = (!empty($whereConditions) || !empty($search) || !empty($prb_document_filter)) ? ' AND ' : ' WHERE ';
                $uniquePatientCondition .= "combined_data.no_sep IN (
                    SELECT sub.no_sep FROM (
                        SELECT no_sep, no_kartu, tglsep,
                               ROW_NUMBER() OVER (PARTITION BY no_kartu ORDER BY tglsep DESC) as rn
                        FROM $baseQuery
                    ) sub WHERE sub.rn = 1
                )";
            }
            
            // Get all patient data first
            $sql = "SELECT 
                        combined_data.no_sep,
                        combined_data.tglsep,
                        r.tgl_registrasi,
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
                        GROUP_CONCAT(CONCAT(bo.nm_obat, ' | Jumlah: ', COALESCE(bo.jumlah, 0), ' | Signa 1: ', COALESCE(bo.signa1, '-'), ' | Signa 2: ', COALESCE(bo.signa2, '-')) ORDER BY bo.nm_obat SEPARATOR '\n') as obat_details
                    FROM $baseQuery
                    LEFT JOIN reg_periksa r ON combined_data.no_rawat = r.no_rawat
                    LEFT JOIN poliklinik pol ON r.kd_poli = pol.kd_poli
                    LEFT JOIN dokter d ON r.kd_dokter = d.kd_dokter
                    LEFT JOIN bridging_srb_bpjs srb ON combined_data.no_sep = srb.no_sep
                    LEFT JOIN bridging_srb_bpjs_obat bo ON srb.no_srb = bo.no_srb
                    $whereClause $searchCondition $prbDocumentCondition $uniquePatientCondition
                    GROUP BY combined_data.no_sep, combined_data.tglsep, r.tgl_registrasi, combined_data.no_kartu, combined_data.nama_pasien, 
                             pol.nm_poli, d.nm_dokter, combined_data.prb_status, combined_data.no_rawat, combined_data.norm,
                             combined_data.catatan, combined_data.tanggal_lahir, combined_data.peserta, combined_data.diagawal,
                             combined_data.nmdiagnosaawal, combined_data.klsrawat, combined_data.notelep, combined_data.tglrujukan,
                             combined_data.no_rujukan, combined_data.nmppkrujukan, combined_data.jkel, combined_data.petugas_pembuat_sep,
                             srb.no_srb, srb.tgl_srb, srb.nmdpjp, srb.namaprogram, srb.user
                    ORDER BY combined_data.tglsep DESC";
            
            // Add limit if specified (for testing purposes)
            if ($limit > 0) {
                $sql .= " LIMIT " . (int)$limit;
            }
            
            $stmt = $this->pdo->prepare($sql);
            $stmt->execute($params);
            $data = $stmt->fetchAll(PDO::FETCH_ASSOC);
            
            // DISABLED: This code was overwriting obat_details from bridging_srb_bpjs_obat with billing data
            // We want to keep obat_details from bridging_srb_bpjs_obat (PRB medication list)
            /*
            // Get billing data for medication history if needed
            if (!empty($data)) {
                // Get all no_rawat values
                $noRawatList = [];
                foreach ($data as $row) {
                    if (!empty($row['no_rawat'])) {
                        $noRawatList[] = $row['no_rawat'];
                    }
                }
                
                if (!empty($noRawatList)) {
                    // Get billing data for all patients
                    $placeholders = str_repeat('?,', count($noRawatList) - 1) . '?';
                    $billingSql = "SELECT no_rawat, nm_perawatan, biaya, jumlah, totalbiaya 
                                   FROM billing 
                                   WHERE no_rawat IN ($placeholders) AND status = 'Obat'";
                    
                    $billingStmt = $this->pdo->prepare($billingSql);
                    $billingStmt->execute($noRawatList);
                    $billingData = $billingStmt->fetchAll(PDO::FETCH_ASSOC);
                    
                    // Group billing data by no_rawat
                    $billingByVisit = [];
                    foreach ($billingData as $billingItem) {
                        if (isset($billingItem['no_rawat']) && 
                            isset($billingItem['nm_perawatan']) && 
                            strpos($billingItem['nm_perawatan'], ':') === false) {
                            if (!isset($billingByVisit[$billingItem['no_rawat']])) {
                                $billingByVisit[$billingItem['no_rawat']] = [];
                            }
                            $billingByVisit[$billingItem['no_rawat']][] = $billingItem;
                        }
                    }
                    
                    // Add billing summary to each patient record
                    foreach ($data as &$patient) {
                        $noRawat = $patient['no_rawat'] ?? '';
                        if (!empty($noRawat) && isset($billingByVisit[$noRawat])) {
                            $billingItems = $billingByVisit[$noRawat];
                            $billingSummary = [];
                            
                            foreach ($billingItems as $item) {
                                $itemName = $item['nm_perawatan'] ?? '';
                                $quantity = $item['jumlah'] ?? '';
                                $totalBiaya = (float)($item['totalbiaya'] ?? 0);
                                
                                if (!empty($itemName)) {
                                    $billingSummary[] = "$itemName (Jumlah: $quantity, Total: Rp " . number_format($totalBiaya, 0, ',', '.') . ')';
                                }
                            }
                            
                            if (!empty($billingSummary)) {
                                $patient['obat_details'] = implode("\n", $billingSummary);
                            }
                        }
                    }
                }
            }
            */
            
            // Add limit if specified (for testing purposes)
            if ($limit > 0) {
                $sql .= " LIMIT " . (int)$limit;
            }
            
            // Return the updated data with medication history
            return [
                'success' => true,
                'data' => $data,
                'total_exported' => count($data),
                'filters_applied' => [
                    'search' => $search,
                    'date_from' => $date_from,
                    'date_to' => $date_to,
                    'prb_filter' => $prb_filter,
                    'prb_document_filter' => $prb_document_filter, // NEW
                    'limit' => $limit
                ],
                'message' => 'Data export berhasil diambil'
            ];
            
        } catch (Exception $e) {
            return [
                'success' => false,
                'data' => [],
                'message' => 'Error: ' . $e->getMessage()
            ];
        }
    }
    
    /**
     * Get patient history grouped by polyclinic
     * Returns all patient visits grouped by polyclinic
     */
    public function getPatientHistory($no_kartu) {
        try {
            if (!$this->pdo) {
                throw new Exception("Database connection not available");
            }
            
            if (empty($no_kartu)) {
                throw new Exception("No. Kartu is required");
            }
            
            // Get patient history with additional rujukan fields, latest first, limited to 20 records
            $sql = "SELECT 
                        r.no_rawat,
                        r.tgl_registrasi,
                        pol.nm_poli,
                        d.nm_dokter,
                        COALESCE(bs.no_sep, bsi.no_sep) as no_sep,
                        COALESCE(bs.tglsep, bsi.tglsep) as tglsep,
                        COALESCE(bs.tglrujukan, bsi.tglrujukan) as tglrujukan,
                        COALESCE(bs.no_rujukan, bsi.no_rujukan) as no_rujukan,
                        COALESCE(bs.nmppkrujukan, bsi.nmppkrujukan) as nmppkrujukan,
                        COALESCE(prb.prb, 'Tidak') as prb_status,
                        srb.no_srb as no_surat_prb,
                        r.no_rkm_medis as norm,
                        COALESCE(bs.no_kartu, bsi.no_kartu) as no_kartu,
                        COALESCE(bs.klsrawat, bsi.klsrawat) as klsrawat,
                        COALESCE(bs.nama_pasien, bsi.nama_pasien) as nama_peserta,
                        COALESCE(bs.tanggal_lahir, bsi.tanggal_lahir) as tanggal_lahir,
                        COALESCE(bs.jkel, bsi.jkel) as jkel,
                        COALESCE(bs.notelep, bsi.notelep) as notelep,
                        COALESCE(bs.peserta, bsi.peserta) as peserta,
                        COALESCE(bs.diagawal, bsi.diagawal) as diagawal,
                        COALESCE(bs.nmdiagnosaawal, bsi.nmdiagnosaawal) as nmdiagnosaawal,
                        COALESCE(bs.catatan, bsi.catatan) as catatan,
                        COALESCE(bs.user, bsi.user) as petugas_pembuat_sep,
                        srb.tgl_srb as tanggal_prb,
                        srb.nmdpjp as dokter_dpjp,
                        srb.namaprogram as diagnosa_prb,
                        srb.user as petugas_pembuat_prb,
                        GROUP_CONCAT(DISTINCT CONCAT(bo.nm_obat, ' (', ' jumlah: ', bo.jumlah, ' | ', ' S1: ', bo.signa1, ' | ', ' S2: ', bo.signa2, ')') SEPARATOR '\n') as obat_details
                    FROM reg_periksa r
                    LEFT JOIN poliklinik pol ON r.kd_poli = pol.kd_poli
                    LEFT JOIN dokter d ON r.kd_dokter = d.kd_dokter
                    LEFT JOIN bridging_sep bs ON r.no_rawat = bs.no_rawat
                    LEFT JOIN bridging_sep_internal bsi ON r.no_rawat = bsi.no_rawat
                    LEFT JOIN bpjs_prb prb ON COALESCE(bs.no_sep, bsi.no_sep) = prb.no_sep
                    LEFT JOIN bridging_srb_bpjs srb ON COALESCE(bs.no_sep, bsi.no_sep) = srb.no_sep
                    LEFT JOIN bridging_srb_bpjs_obat bo ON COALESCE(bs.no_sep, bsi.no_sep) = bo.no_sep
                    WHERE r.no_rkm_medis = (
                        SELECT DISTINCT nomr FROM (
                            SELECT nomr FROM bridging_sep WHERE no_kartu = :no_kartu
                            UNION
                            SELECT nomr FROM bridging_sep_internal WHERE no_kartu = :no_kartu
                        ) AS sub
                        LIMIT 1
                    )
                    GROUP BY r.no_rawat, r.tgl_registrasi, pol.nm_poli, d.nm_dokter, bs.no_sep, bsi.no_sep,
                             bs.tglsep, bsi.tglsep, bs.tglrujukan, bsi.tglrujukan, bs.no_rujukan, bsi.no_rujukan,
                             bs.nmppkrujukan, bsi.nmppkrujukan, prb.prb, srb.no_srb, r.no_rkm_medis,
                             bs.no_kartu, bsi.no_kartu, bs.klsrawat, bsi.klsrawat, bs.nama_pasien, bsi.nama_pasien,
                             bs.tanggal_lahir, bsi.tanggal_lahir, bs.jkel, bsi.jkel, bs.notelep, bsi.notelep,
                             bs.peserta, bsi.peserta, bs.diagawal, bsi.diagawal, bs.nmdiagnosaawal, bsi.nmdiagnosaawal,
                             bs.catatan, bsi.catatan, bs.user, bsi.user, srb.tgl_srb, srb.nmdpjp, srb.namaprogram, srb.user
                    ORDER BY r.tgl_registrasi DESC
                    LIMIT 20";
            
            // Get billing data for medication history
            $billingSql = "SELECT no_rawat, nm_perawatan, biaya, jumlah, totalbiaya 
                           FROM billing 
                           WHERE no_rawat IN (
                               SELECT no_rawat FROM reg_periksa WHERE no_rkm_medis = (
                                   SELECT DISTINCT nomr FROM (
                                       SELECT nomr FROM bridging_sep WHERE no_kartu = :no_kartu
                                       UNION
                                       SELECT nomr FROM bridging_sep_internal WHERE no_kartu = :no_kartu
                                   ) AS sub
                                   LIMIT 1
                               )
                           ) AND status = 'Obat'";
            
            $stmt = $this->pdo->prepare($sql);
            $stmt->bindValue(':no_kartu', $no_kartu);
            $stmt->execute();
            $history = $stmt->fetchAll(PDO::FETCH_ASSOC);
            
            // Get billing data
            $billingStmt = $this->pdo->prepare($billingSql);
            $billingStmt->bindValue(':no_kartu', $no_kartu);
            $billingStmt->execute();
            $billingData = $billingStmt->fetchAll(PDO::FETCH_ASSOC);
            
            // Get patient basic info
            $infoSql = "SELECT DISTINCT 
                            COALESCE(bs.nama_pasien, bsi.nama_pasien) as nama_pasien,
                            COALESCE(bs.no_kartu, bsi.no_kartu) as no_kartu,
                            COALESCE(bs.tanggal_lahir, bsi.tanggal_lahir) as tanggal_lahir
                        FROM (
                            SELECT no_kartu, nama_pasien, tanggal_lahir, nomr FROM bridging_sep WHERE no_kartu = :no_kartu
                            UNION
                            SELECT no_kartu, nama_pasien, tanggal_lahir, nomr FROM bridging_sep_internal WHERE no_kartu = :no_kartu
                        ) AS combined
                        LEFT JOIN bridging_sep bs ON combined.no_kartu = bs.no_kartu
                        LEFT JOIN bridging_sep_internal bsi ON combined.no_kartu = bsi.no_kartu
                        LIMIT 1";
            
            $infoStmt = $this->pdo->prepare($infoSql);
            $infoStmt->bindValue(':no_kartu', $no_kartu);
            $infoStmt->execute();
            $patientInfo = $infoStmt->fetch(PDO::FETCH_ASSOC);
            
            return [
                'success' => true,
                'data' => [
                    'patient_info' => $patientInfo,
                    'history' => $history,
                    'billing' => $billingData
                ],
                'message' => 'Riwayat pasien berhasil diambil'
            ];
            
        } catch (Exception $e) {
            return [
                'success' => false,
                'data' => [],
                'message' => 'Error: ' . $e->getMessage()
            ];
        }
    }
    
    /**
     * Export patient history to Excel format
     * Returns patient history with medication details for Excel export
     */
    public function exportPatientHistoryToExcel($no_kartu) {
        try {
            if (!$this->pdo) {
                throw new Exception("Database connection not available");
            }
            
            if (empty($no_kartu)) {
                throw new Exception("No. Kartu is required");
            }
            
            // Get patient history
            $historyResult = $this->getPatientHistory($no_kartu);
            
            if (!$historyResult['success']) {
                throw new Exception($historyResult['message']);
            }
            
            $patientInfo = $historyResult['data']['patient_info'];
            $history = $historyResult['data']['history'];
            $billingData = $historyResult['data']['billing'] ?? [];
            
            // Process data for export
            $exportData = [];
            
            foreach ($history as $visit) {
                // Format dates
                $tglRegistrasi = !empty($visit['tgl_registrasi']) ? date('d/m/Y', strtotime($visit['tgl_registrasi'])) : '-';
                $tglSep = !empty($visit['tglsep']) ? date('d/m/Y', strtotime($visit['tglsep'])) : '-';
                $tglRujukan = !empty($visit['tglrujukan']) ? date('d/m/Y', strtotime($visit['tglrujukan'])) : '-';
                $tanggalPrb = !empty($visit['tanggal_prb']) ? date('d/m/Y', strtotime($visit['tanggal_prb'])) : '-';
                $tanggalLahir = !empty($visit['tanggal_lahir']) ? date('d/m/Y', strtotime($visit['tanggal_lahir'])) : '-';
                
                // Format gender
                $gender = $visit['jkel'] === 'L' ? 'Laki-Laki' : 
                         ($visit['jkel'] === 'P' ? 'Perempuan' : 
                         ($visit['jkel'] ?: '-'));
                
                // Format PRB status
                $prbStatus = $visit['prb_status'] ?: 'Tidak';
                if (strpos($prbStatus, 'PRB :') !== false) {
                    $prbStatusDisplay = 'PRB';
                } elseif ($prbStatus === 'Potensi PRB') {
                    $prbStatusDisplay = 'POTENSI PRB';
                } else {
                    $prbStatusDisplay = 'Tidak PRB';
                }
                
                // Add visit data
                $exportData[] = [
                    'visit_info' => [
                        'no' => '-', // Will be set later
                        'tgl_registrasi' => $tglRegistrasi,
                        'nm_poli' => $visit['nm_poli'] ?? '-',
                        'nm_dokter' => $visit['nm_dokter'] ?? '-',
                        'no_rawat' => $visit['no_rawat'] ?? '-',
                        'no_sep' => $visit['no_sep'] ?? '-',
                        'tgl_sep' => $tglSep,
                        'tgl_rujukan' => $tglRujukan,
                        'no_rujukan' => $visit['no_rujukan'] ?? '-',
                        'nmppkrujukan' => $visit['nmppkrujukan'] ?? '-',
                        'diagawal' => $visit['diagawal'] ?? '-',
                        'no_surat_prb' => $visit['no_surat_prb'] ?? '-',
                        'prb_status' => $prbStatusDisplay,
                        'norm' => $visit['norm'] ?? '-',
                        'no_kartu' => $visit['no_kartu'] ?? '-',
                        'klsrawat' => $visit['klsrawat'] ?? '-',
                        'nama_peserta' => $visit['nama_peserta'] ?? '-',
                        'tanggal_lahir' => $tanggalLahir,
                        'jkel' => $gender,
                        'notelep' => $visit['notelep'] ?? '-',
                        'peserta' => $visit['peserta'] ?? '-',
                        'nmdiagnosaawal' => $visit['nmdiagnosaawal'] ?? '-',
                        'catatan' => $visit['catatan'] ?? '-',
                        'petugas_pembuat_sep' => $visit['petugas_pembuat_sep'] ?? '-',
                        'tanggal_prb' => $tanggalPrb,
                        'dokter_dpjp' => $visit['dokter_dpjp'] ?? '-',
                        'diagnosa_prb' => $visit['diagnosa_prb'] ?? '-',
                        'petugas_pembuat_prb' => $visit['petugas_pembuat_prb'] ?? '-',
                        'obat_details' => $visit['obat_details'] ?? '-'
                    ],
                    'billing' => []
                ];
                
                // Add billing data for this visit
                $visitBilling = [];
                foreach ($billingData as $billingItem) {
                    if (isset($billingItem['no_rawat']) && $billingItem['no_rawat'] === $visit['no_rawat'] && 
                        isset($billingItem['nm_perawatan']) && strpos($billingItem['nm_perawatan'], ':') === false) {
                        $visitBilling[] = $billingItem;
                    }
                }
                
                // Add billing data to the last visit entry
                $exportData[count($exportData) - 1]['billing'] = $visitBilling;
            }
            
            // Set row numbers
            foreach ($exportData as $index => &$row) {
                $row['visit_info']['no'] = $index + 1;
            }
            
            return [
                'success' => true,
                'data' => [
                    'patient_info' => $patientInfo,
                    'history_with_billing' => $exportData
                ],
                'message' => 'Patient history export data retrieved successfully'
            ];
            
        } catch (Exception $e) {
            return [
                'success' => false,
                'data' => [],
                'message' => 'Error: ' . $e->getMessage()
            ];
        }
    }
}