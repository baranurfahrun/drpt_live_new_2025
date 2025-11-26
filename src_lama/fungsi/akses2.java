package fungsi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.Timer;

/**
 *
 * @author Owner
 */
/**
 *
 * @author Owner
 */
public final class akses2 {

    private static final Connection koneksi = koneksiDB.condb();
    private static PreparedStatement ps, ps2;
    private static ResultSet rs, rs2;

    private static String kode = "", kdbangsal = "", namars = "", alamatrs = "", kabupatenrs = "", propinsirs = "", kontakrs = "", emailrs = "",
            form = "", namauser = "", jenisLoket = "", nomorLoket = "", CopyData = "", notifLab = "", notifRad = "", notifApotek = "",
            CopyData1 = "", CopyData2 = "", CopyData3 = "", CopyData4 = "", CopyData5 = "";
    private static int jml1 = 0, jml2 = 0, lebar = 0, tinggi = 0;
    private static boolean aktif = false, admin = false, user = false, vakum = false, aplikasi = false, penyakit = false, obat_penyakit = false, dokter = false, jadwal_praktek = false, petugas = false, pasien = false, registrasi = false,
            tindakan_ralan = false, kamar_inap = false, tindakan_ranap = false, operasi = false, rujukan_keluar = false, rujukan_masuk = false, beri_obat = false,
            resep_pulang = false, pasien_meninggal = false, diet_pasien = false, kelahiran_bayi = false, periksa_lab = false, periksa_radiologi = false,
            kasir_ralan = false, deposit_pasien = false, piutang_pasien = false, peminjaman_berkas = false, barcode = false, presensi_harian = false,
            presensi_bulanan = false, pegawai_admin = false, pegawai_user = false, suplier = false, satuan_barang = false, konversi_satuan = false, jenis_barang = false,
            obat = false, stok_opname_obat = false, stok_obat_pasien = false, pengadaan_obat = false, pemesanan_obat = false, penjualan_obat = false, piutang_obat = false,
            retur_ke_suplier = false, retur_dari_pembeli = false, retur_obat_ranap = false, retur_piutang_pasien = false, keuntungan_penjualan = false, keuntungan_beri_obat = false,
            sirkulasi_obat = false, ipsrs_barang = false, ipsrs_jenis_barang = false, ipsrs_pengadaan_barang = false, ipsrs_stok_keluar = false, ipsrs_rekap_pengadaan = false, ipsrs_rekap_stok_keluar = false,
            ipsrs_pengeluaran_harian = false, inventaris_jenis = false, inventaris_kategori = false, inventaris_merk = false, inventaris_ruang = false, inventaris_produsen = false,
            inventaris_koleksi = false, inventaris_inventaris = false, inventaris_sirkulasi = false, parkir_jenis = false, parkir_in = false, parkir_out = false,
            parkir_rekap_harian = false, parkir_rekap_bulanan = false, informasi_kamar = false, harian_tindakan_poli = false, obat_per_poli = false, obat_per_kamar = false,
            obat_per_dokter_ralan = false, obat_per_dokter_ranap = false, harian_dokter = false, bulanan_dokter = false, harian_paramedis = false, bulanan_paramedis = false,
            pembayaran_ralan = false, pembayaran_ranap = false, rekap_pembayaran_ralan = false, rekap_pembayaran_ranap = false, tagihan_masuk = false, tambahan_biaya = false,
            potongan_biaya = false, resep_obat = false, resume_pasien = false, penyakit_ralan = false, penyakit_ranap = false, kamar = false, tarif_ralan = false, tarif_ranap = false,
            tarif_lab = false, tarif_radiologi = false, tarif_operasi = false, akun_rekening = false, rekening_tahun = false, posting_jurnal = false, buku_besar = false,
            cashflow = false, keuangan = false, pengeluaran = false, setup_pjlab = false, setup_otolokasi = false, setup_jam_kamin = false, setup_embalase = false, tracer_login = false,
            display = false, set_harga_obat = false, set_penggunaan_tarif = false, set_oto_ralan = false, biaya_harian = false, biaya_masuk_sekali = false, set_no_rm = false,
            billing_ralan = false, billing_ranap = false, status = false, jm_ranap_dokter = false, igd = false, barcoderalan = false, barcoderanap = false, set_harga_obat_ralan = false,
            set_harga_obat_ranap = false, penyakit_pd3i = false, surveilans_pd3i = false, surveilans_ralan = false, diagnosa_pasien = false, surveilans_ranap = false,
            pny_takmenular_ranap = false, pny_takmenular_ralan = false, kunjungan_ralan = false, rl32 = false, rl33 = false, rl37 = false, rl38 = false, harian_tindakan_dokter = false,
            sms = false, sidikjari = false, jam_masuk = false, jadwal_pegawai = false, parkir_barcode = false, set_nota = false, dpjp_ranap = false, mutasi_barang = false, rl34 = false, rl36 = false,
            fee_visit_dokter = false, fee_bacaan_ekg = false, fee_rujukan_rontgen = false, fee_rujukan_ranap = false, fee_ralan = false, akun_bayar = false, bayar_pemesanan_obat = false,
            obat_per_dokter_peresep = false, pemasukan_lain = false, pengaturan_rekening = false, closing_kasir = false, keterlambatan_presensi = false, set_harga_kamar = false,
            rekap_per_shift = false, bpjs_cek_nik = false, bpjs_cek_kartu = false, obat_per_cara_bayar = false, kunjungan_ranap = false, bayar_piutang = false,
            payment_point = false, bpjs_cek_nomor_rujukan = false, icd9 = false, darurat_stok = false, retensi_rm = false, temporary_presensi = false, jurnal_harian = false,
            sirkulasi_obat2 = false, edit_registrasi = false, bpjs_referensi_diagnosa = false, bpjs_referensi_poli = false, industrifarmasi = false, harian_js = false, bulanan_js = false,
            harian_paket_bhp = false, bulanan_paket_bhp = false, piutang_pasien2 = false, bpjs_referensi_faskes = false, bpjs_sep = false, pengambilan_utd = false, tarif_utd = false,
            pengambilan_utd2 = false, utd_medis_rusak = false, pengambilan_penunjang_utd = false, pengambilan_penunjang_utd2 = false, utd_penunjang_rusak = false,
            suplier_penunjang = false, utd_donor = false, bpjs_monitoring_klaim = false, utd_cekal_darah = false, utd_komponen_darah = false, utd_stok_darah = false,
            utd_pemisahan_darah = false, harian_kamar = false, rincian_piutang_pasien = false, keuntungan_beri_obat_nonpiutang = false, reklasifikasi_ralan = false,
            reklasifikasi_ranap = false, utd_penyerahan_darah = false, hutang_obat = false, riwayat_obat_alkes_bhp = false, sensus_harian_poli = false, rl4a = false,
            aplicare_referensi_kamar = false, aplicare_ketersediaan_kamar = false, inacbg_klaim_baru_otomatis = false, inacbg_klaim_baru_manual = false, inacbg_coder_nik = false,
            mutasi_berkas = false, akun_piutang = false, harian_kso = false, bulanan_kso = false, harian_menejemen = false, bulanan_menejemen = false, inhealth_cek_eligibilitas = false,
            inhealth_referensi_jenpel_ruang_rawat = false, inhealth_referensi_poli = false, inhealth_referensi_faskes = false, inhealth_sjp = false, piutang_ralan = false,
            piutang_ranap = false, detail_piutang_penjab = false, lama_pelayanan_ralan = false, catatan_pasien = false, rl4b = false, rl4asebab = false, rl4bsebab = false,
            data_HAIs = false, harian_HAIs = false, bulanan_HAIs = false, hitung_bor = false, perusahaan_pasien = false, resep_dokter = false, lama_pelayanan_apotek = false,
            hitung_alos = false, detail_tindakan = false, rujukan_poli_internal = false, rekap_poli_anak = false, grafik_kunjungan_poli = false, grafik_kunjungan_perdokter = false,
            grafik_kunjungan_perpekerjaan = false, grafik_kunjungan_perpendidikan = false, grafik_kunjungan_pertahun = false, berkas_digital_perawatan = false,
            penyakit_menular_ranap = false, penyakit_menular_ralan = false, grafik_kunjungan_perbulan = false, grafik_kunjungan_pertanggal = false, grafik_kunjungan_demografi = false,
            grafik_kunjungan_statusdaftartahun = false, grafik_kunjungan_statusdaftartahun2 = false, grafik_kunjungan_statusdaftarbulan = false, grafik_kunjungan_statusdaftarbulan2 = false,
            grafik_kunjungan_statusdaftartanggal = false, grafik_kunjungan_statusdaftartanggal2 = false, grafik_kunjungan_statusbataltahun = false, grafik_kunjungan_statusbatalbulan = false,
            pcare_cek_penyakit = false, grafik_kunjungan_statusbataltanggal = false, kategori_barang = false, golongan_barang = false, pemberian_obat_pertanggal = false, penjualan_obat_pertanggal = false,
            bpjs_rujukan_vclaim = false, skdp_bpjs = false, booking_registrasi = false, bpjs_cek_riwayat_rujukan_pcare = false, bpjs_cek_riwayat_rujukan_rs = false, bpjs_cek_rujukan_kartu_rs = false,
            bpjs_cek_tgl_rujukan = false, bpjs_cek_no_rujukan_rs = false, bpjs_cek_rujukan_kartu_pcare = false, bpjs_cek_referensi_kelas_rawat = false, bpjs_cek_referensi_prosedur = false,
            bpjs_cek_referensi_dpjp = false, bpjs_cek_referensi_dokter = false, bpjs_cek_referensi_spesialistik = false, bpjs_cek_referensi_ruang_rawat = false, bpjs_cek_referensi_cara_keluar = false,
            bpjs_cek_referensi_pasca_pulang = false, bpjs_cek_referensi_propinsi = false, bpjs_cek_referensi_kabupaten = false, bpjs_cek_referensi_kecamatan = false, permintaan_lab = false,
            permintaan_radiologi = false, selisih_tarif_bpjs = false, edit_data_kematian = false, bridging_jamkesda = false, masuk_pindah_pulang_inap = false, masuk_pindah_inap = false,
            jumlah_macam_diet = false, jumlah_porsi_diet = false, status_gizi = false, gizi_buruk = false, master_faskes, setstatusralan = false, telusurpasien = false, sisrute_rujukan_keluar = false, sisrute_rujukan_masuk = false,
            sisrute_referensi_diagnosa = false, sisrute_referensi_alasanrujuk = false, sisrute_referensi_faskes = false, barang_cssd = false, status_pulang_inap = false, data_persalinan = false, data_ponek = false,
            reg_boking_kasir = false, bahasa_pasien = false, suku_bangsa = false, harian_hais_inap = false, harian_hais_jalan = false, bulanan_hais_inap = false, bulanan_hais_jalan = false, ringkasan_pulang_ranap = false,
            laporan_farmasi = false, master_masalah_keperawatan = false, penilaian_awal_keperawatan_ralan = false, master_triase_skala1 = false, master_triase_skala2 = false,
            master_triase_skala3 = false, master_triase_skala4 = false, master_triase_skala5 = false, data_triase_igd = false, master_triase_pemeriksaan = false, master_triase_macamkasus = false, master_cara_bayar = false,
            status_kerja_dokter = false, pasien_corona = false, diagnosa_pasien_corona = false, perawatan_pasien_corona = false, inacbg_klaim_baru_manual2 = false, assesmen_gizi_harian = false, assesmen_gizi_ulang = false,
            tombol_nota_billing = false, tombol_simpan_hasil_rad = false, monev_asuhan_gizi = false, inacbg_klaim_raza = false, pengajuan_klaim_inacbg_raza = false,
            copy_pemeriksaan_dokter_kepetugas_ralan = false, jkn_belum_diproses_klaim = false, input_kode_icd = false, kendali_Mutu_kendali_Biaya_INACBG = false, dashboard_eResep = false, bpjs_sep_internal = false,
            kemenkes_sitt = false, rencana_kontrol_jkn = false, spri_jkn = false, hapus_sep = false, penilaian_awal_medis_ralan_kebidanan = false, penilaian_awal_keperawatan_kebidanan = false,
            ikhtisar_perawatan_hiv = false, survey_kepuasan = false, kemenkes_kanker = false, aktivasi_bridging = false, operator_antrian = false, penilaian_awal_medis_ralan_tht = false,
            rekam_psikologis = false, penilaian_pasien_geriatri = false, penilaian_awal_medis_ralan_mata = false, surat_sakit = false, surat_keterangan_kir_mcu = false, asesmen_medik_dewasa_ranap = false,
            pemberian_obat = false, cppt = false, bridging_satu_sehat = false, kemoterapi=false, cek_piutang=false;
    public static Timer tRefreshAntrian, tRefreshPoli, tRefreshAntri, tRefreshNotifApotek, tRefreshNotifLab, tRefreshNotifRad;

    public static void setData(String user, String pass) {
        try {
            ps = koneksi.prepareStatement("select * from admin where usere=AES_ENCRYPT(?,'nur') and passworde=AES_ENCRYPT(?,'windi')");
            ps2 = koneksi.prepareStatement("select * from user u "
                    + "left join petugas p on p.user_id = AES_DECRYPT(u.id_user,'nur') "
                    + "left join dokter d on d.kd_dokter = p.nip where "
                    + "u.id_user=AES_ENCRYPT(?,'nur') and u.password=AES_ENCRYPT(?,'windi')");
            try {
                ps.setString(1, user);
                ps.setString(2, pass);
                rs = ps.executeQuery();
                rs.last();

                ps2.setString(1, user);
                ps2.setString(2, pass);
                rs2 = ps2.executeQuery();
                rs2.last();

                akses2.jml1 = rs.getRow();
                akses2.jml2 = rs2.getRow();

                if (user.equals("admin") && pass.equals("satu")) {
                    akses2.kode = "Admin Utama";
                    akses2.penyakit = true;
                    akses2.obat_penyakit = true;
                    akses2.dokter = true;
                    akses2.jadwal_praktek = true;
                    akses2.petugas = true;
                    akses2.pasien = true;
                    akses2.registrasi = true;
                    akses2.tindakan_ralan = true;
                    akses2.kamar_inap = true;
                    akses2.tindakan_ranap = true;
                    akses2.operasi = true;
                    akses2.rujukan_keluar = true;
                    akses2.rujukan_masuk = true;
                    akses2.beri_obat = true;
                    akses2.resep_pulang = true;
                    akses2.pasien_meninggal = true;
                    akses2.diet_pasien = true;
                    akses2.kelahiran_bayi = true;
                    akses2.periksa_lab = true;
                    akses2.periksa_radiologi = true;
                    akses2.kasir_ralan = true;
                    akses2.deposit_pasien = true;
                    akses2.piutang_pasien = true;
                    akses2.peminjaman_berkas = true;
                    akses2.barcode = true;
                    akses2.presensi_harian = true;
                    akses2.presensi_bulanan = true;
                    akses2.pegawai_admin = true;
                    akses2.pegawai_user = true;
                    akses2.suplier = true;
                    akses2.satuan_barang = true;
                    akses2.konversi_satuan = true;
                    akses2.jenis_barang = true;
                    akses2.obat = true;
                    akses2.stok_opname_obat = true;
                    akses2.stok_obat_pasien = true;
                    akses2.pengadaan_obat = true;
                    akses2.pemesanan_obat = true;
                    akses2.penjualan_obat = true;
                    akses2.piutang_obat = true;
                    akses2.retur_ke_suplier = true;
                    akses2.retur_dari_pembeli = true;
                    akses2.retur_obat_ranap = true;
                    akses2.retur_piutang_pasien = true;
                    akses2.keuntungan_penjualan = true;
                    akses2.keuntungan_beri_obat = true;
                    akses2.sirkulasi_obat = true;
                    akses2.ipsrs_barang = true;
                    akses2.ipsrs_jenis_barang = true;
                    akses2.ipsrs_pengadaan_barang = true;
                    akses2.ipsrs_stok_keluar = true;
                    akses2.ipsrs_rekap_pengadaan = true;
                    akses2.ipsrs_rekap_stok_keluar = true;
                    akses2.ipsrs_pengeluaran_harian = true;
                    akses2.inventaris_jenis = true;
                    akses2.inventaris_kategori = true;
                    akses2.inventaris_merk = true;
                    akses2.inventaris_ruang = true;
                    akses2.inventaris_produsen = true;
                    akses2.inventaris_koleksi = true;
                    akses2.inventaris_inventaris = true;
                    akses2.inventaris_sirkulasi = true;
                    akses2.parkir_jenis = true;
                    akses2.parkir_in = true;
                    akses2.parkir_out = true;
                    akses2.parkir_rekap_harian = true;
                    akses2.parkir_rekap_bulanan = true;
                    akses2.informasi_kamar = true;
                    akses2.harian_tindakan_poli = true;
                    akses2.obat_per_poli = true;
                    akses2.obat_per_kamar = true;
                    akses2.obat_per_dokter_ralan = true;
                    akses2.obat_per_dokter_ranap = true;
                    akses2.harian_dokter = true;
                    akses2.bulanan_dokter = true;
                    akses2.harian_paramedis = true;
                    akses2.bulanan_paramedis = true;
                    akses2.pembayaran_ralan = true;
                    akses2.pembayaran_ranap = true;
                    akses2.rekap_pembayaran_ralan = true;
                    akses2.rekap_pembayaran_ranap = true;
                    akses2.tagihan_masuk = true;
                    akses2.tambahan_biaya = true;
                    akses2.potongan_biaya = true;
                    akses2.resep_obat = true;
                    akses2.resume_pasien = true;
                    akses2.penyakit_ralan = true;
                    akses2.penyakit_ranap = true;
                    akses2.kamar = true;
                    akses2.tarif_ralan = true;
                    akses2.tarif_ranap = true;
                    akses2.tarif_lab = true;
                    akses2.tarif_radiologi = true;
                    akses2.tarif_operasi = true;
                    akses2.akun_rekening = true;
                    akses2.rekening_tahun = true;
                    akses2.posting_jurnal = true;
                    akses2.buku_besar = true;
                    akses2.cashflow = true;
                    akses2.keuangan = true;
                    akses2.pengeluaran = true;
                    akses2.setup_pjlab = true;
                    akses2.setup_otolokasi = true;
                    akses2.setup_jam_kamin = true;
                    akses2.setup_embalase = true;
                    akses2.tracer_login = true;
                    akses2.display = true;
                    akses2.set_harga_obat = true;
                    akses2.set_penggunaan_tarif = true;
                    akses2.set_oto_ralan = true;
                    akses2.biaya_harian = true;
                    akses2.biaya_masuk_sekali = true;
                    akses2.set_no_rm = true;
                    akses2.billing_ralan = true;
                    akses2.billing_ranap = true;
                    akses2.jm_ranap_dokter = true;
                    akses2.igd = true;
                    akses2.barcoderalan = true;
                    akses2.barcoderanap = true;
                    akses2.set_harga_obat_ralan = true;
                    akses2.set_harga_obat_ranap = true;
                    akses2.penyakit_pd3i = true;
                    akses2.surveilans_pd3i = true;
                    akses2.surveilans_ralan = true;
                    akses2.diagnosa_pasien = true;
                    akses2.admin = true;
                    akses2.user = true;
                    akses2.vakum = true;
                    akses2.aplikasi = true;
                    akses2.surveilans_ranap = true;
                    akses2.pny_takmenular_ranap = true;
                    akses2.pny_takmenular_ralan = true;
                    akses2.kunjungan_ralan = true;
                    akses2.rl32 = true;
                    akses2.rl33 = true;
                    akses2.rl37 = true;
                    akses2.rl38 = true;
                    akses2.harian_tindakan_dokter = true;
                    akses2.sms = true;
                    akses2.sidikjari = true;
                    akses2.jam_masuk = true;
                    akses2.jadwal_pegawai = true;
                    akses2.parkir_barcode = true;
                    akses2.set_nota = true;
                    akses2.dpjp_ranap = true;
                    akses2.mutasi_barang = true;
                    akses2.rl34 = true;
                    akses2.rl36 = true;
                    akses2.fee_visit_dokter = true;
                    akses2.fee_bacaan_ekg = true;
                    akses2.fee_rujukan_rontgen = true;
                    akses2.fee_rujukan_ranap = true;
                    akses2.fee_ralan = true;
                    akses2.akun_bayar = true;
                    akses2.bayar_pemesanan_obat = true;
                    akses2.obat_per_dokter_peresep = true;
                    akses2.pemasukan_lain = true;
                    akses2.pengaturan_rekening = true;
                    akses2.closing_kasir = true;
                    akses2.keterlambatan_presensi = true;
                    akses2.set_harga_kamar = true;
                    akses2.rekap_per_shift = true;
                    akses2.bpjs_cek_nik = true;
                    akses2.bpjs_cek_kartu = true;
                    akses2.obat_per_cara_bayar = true;
                    akses2.kunjungan_ranap = true;
                    akses2.bayar_piutang = true;
                    akses2.payment_point = true;
                    akses2.bpjs_cek_nomor_rujukan = true;
                    akses2.icd9 = true;
                    akses2.darurat_stok = true;
                    akses2.retensi_rm = true;
                    akses2.temporary_presensi = true;
                    akses2.jurnal_harian = true;
                    akses2.sirkulasi_obat2 = true;
                    akses2.edit_registrasi = true;
                    akses2.bpjs_referensi_diagnosa = true;
                    akses2.bpjs_referensi_poli = true;
                    akses2.industrifarmasi = true;
                    akses2.harian_js = true;
                    akses2.bulanan_js = true;
                    akses2.harian_paket_bhp = true;
                    akses2.bulanan_paket_bhp = true;
                    akses2.piutang_pasien2 = true;
                    akses2.bpjs_referensi_faskes = true;
                    akses2.bpjs_sep = true;
                    akses2.pengambilan_utd = true;
                    akses2.tarif_utd = true;
                    akses2.pengambilan_utd2 = true;
                    akses2.utd_medis_rusak = true;
                    akses2.pengambilan_penunjang_utd = true;
                    akses2.pengambilan_penunjang_utd2 = true;
                    akses2.utd_penunjang_rusak = true;
                    akses2.suplier_penunjang = true;
                    akses2.utd_donor = true;
                    akses2.bpjs_monitoring_klaim = true;
                    akses2.utd_cekal_darah = true;
                    akses2.utd_komponen_darah = true;
                    akses2.utd_stok_darah = true;
                    akses2.utd_pemisahan_darah = true;
                    akses2.harian_kamar = true;
                    akses2.rincian_piutang_pasien = true;
                    akses2.keuntungan_beri_obat_nonpiutang = true;
                    akses2.reklasifikasi_ralan = true;
                    akses2.reklasifikasi_ranap = true;
                    akses2.utd_penyerahan_darah = true;
                    akses2.hutang_obat = true;
                    akses2.riwayat_obat_alkes_bhp = true;
                    akses2.sensus_harian_poli = true;
                    akses2.rl4a = true;
                    akses2.aplicare_referensi_kamar = true;
                    akses2.aplicare_ketersediaan_kamar = true;
                    akses2.inacbg_klaim_baru_otomatis = true;
                    akses2.inacbg_klaim_baru_manual = true;
                    akses2.inacbg_coder_nik = true;
                    akses2.mutasi_berkas = true;
                    akses2.akun_piutang = true;
                    akses2.harian_kso = true;
                    akses2.bulanan_kso = true;
                    akses2.harian_menejemen = true;
                    akses2.bulanan_menejemen = true;
                    akses2.inhealth_cek_eligibilitas = true;
                    akses2.inhealth_referensi_jenpel_ruang_rawat = true;
                    akses2.inhealth_referensi_poli = true;
                    akses2.inhealth_referensi_faskes = true;
                    akses2.inhealth_sjp = true;
                    akses2.piutang_ralan = true;
                    akses2.piutang_ranap = true;
                    akses2.detail_piutang_penjab = true;
                    akses2.lama_pelayanan_ralan = true;
                    akses2.catatan_pasien = true;
                    akses2.rl4b = true;
                    akses2.rl4asebab = true;
                    akses2.rl4bsebab = true;
                    akses2.namauser = "Admin Utama";
                    akses2.data_HAIs = true;
                    akses2.harian_HAIs = true;
                    akses2.bulanan_HAIs = true;
                    akses2.hitung_bor = true;
                    akses2.perusahaan_pasien = true;
                    akses2.resep_dokter = true;
                    akses2.lama_pelayanan_apotek = true;
                    akses2.hitung_alos = true;
                    akses2.detail_tindakan = true;
                    akses2.rujukan_poli_internal = true;
                    akses2.rekap_poli_anak = true;
                    akses2.grafik_kunjungan_poli = true;
                    akses2.grafik_kunjungan_perdokter = true;
                    akses2.grafik_kunjungan_perpekerjaan = true;
                    akses2.grafik_kunjungan_perpendidikan = true;
                    akses2.grafik_kunjungan_pertahun = true;
                    akses2.berkas_digital_perawatan = true;
                    akses2.penyakit_menular_ranap = true;
                    akses2.penyakit_menular_ralan = true;
                    akses2.grafik_kunjungan_perbulan = true;
                    akses2.grafik_kunjungan_pertanggal = true;
                    akses2.grafik_kunjungan_demografi = true;
                    akses2.grafik_kunjungan_statusdaftartahun = true;
                    akses2.grafik_kunjungan_statusdaftartahun2 = true;
                    akses2.grafik_kunjungan_statusdaftarbulan = true;
                    akses2.grafik_kunjungan_statusdaftarbulan2 = true;
                    akses2.grafik_kunjungan_statusdaftartanggal = true;
                    akses2.grafik_kunjungan_statusdaftartanggal2 = true;
                    akses2.grafik_kunjungan_statusbataltahun = true;
                    akses2.grafik_kunjungan_statusbatalbulan = true;
                    akses2.pcare_cek_penyakit = true;
                    akses2.grafik_kunjungan_statusbataltanggal = true;
                    akses2.kategori_barang = true;
                    akses2.golongan_barang = true;
                    akses2.pemberian_obat_pertanggal = true;
                    akses2.penjualan_obat_pertanggal = true;
                    akses2.bpjs_rujukan_vclaim = true;
                    akses2.skdp_bpjs = true;
                    akses2.booking_registrasi = true;
                    akses2.bpjs_cek_riwayat_rujukan_pcare = true;
                    akses2.bpjs_cek_riwayat_rujukan_rs = true;
                    akses2.bpjs_cek_rujukan_kartu_rs = true;
                    akses2.bpjs_cek_tgl_rujukan = true;
                    akses2.bpjs_cek_no_rujukan_rs = true;
                    akses2.bpjs_cek_rujukan_kartu_pcare = true;
                    akses2.bpjs_cek_referensi_kelas_rawat = true;
                    akses2.bpjs_cek_referensi_prosedur = true;
                    akses2.bpjs_cek_referensi_dpjp = true;
                    akses2.bpjs_cek_referensi_dokter = true;
                    akses2.bpjs_cek_referensi_spesialistik = true;
                    akses2.bpjs_cek_referensi_ruang_rawat = true;
                    akses2.bpjs_cek_referensi_cara_keluar = true;
                    akses2.bpjs_cek_referensi_pasca_pulang = true;
                    akses2.bpjs_cek_referensi_propinsi = true;
                    akses2.bpjs_cek_referensi_kabupaten = true;
                    akses2.bpjs_cek_referensi_kecamatan = true;
                    akses2.permintaan_lab = true;
                    akses2.permintaan_radiologi = true;
                    akses2.selisih_tarif_bpjs = true;
                    akses2.edit_data_kematian = true;
                    akses2.bridging_jamkesda = true;
                    akses2.masuk_pindah_pulang_inap = true;
                    akses2.masuk_pindah_inap = true;
                    akses2.jumlah_macam_diet = true;
                    akses2.jumlah_porsi_diet = true;
                    akses2.status_gizi = true;
                    akses2.gizi_buruk = true;
                    akses2.master_faskes = true;
                    akses2.setstatusralan = true;
                    akses2.telusurpasien = true;
                    akses2.sisrute_rujukan_keluar = true;
                    akses2.sisrute_rujukan_masuk = true;
                    akses2.sisrute_referensi_diagnosa = true;
                    akses2.sisrute_referensi_alasanrujuk = true;
                    akses2.sisrute_referensi_faskes = true;
                    akses2.barang_cssd = true;
                    akses2.status_pulang_inap = true;
                    akses2.data_persalinan = true;
                    akses2.data_ponek = true;
                    akses2.reg_boking_kasir = true;
                    akses2.bahasa_pasien = true;
                    akses2.suku_bangsa = true;
                    akses2.harian_hais_inap = true;
                    akses2.harian_hais_jalan = true;
                    akses2.bulanan_hais_inap = true;
                    akses2.bulanan_hais_jalan = true;
                    akses2.ringkasan_pulang_ranap = true;
                    akses2.laporan_farmasi = true;
                    akses2.master_masalah_keperawatan = true;
                    akses2.penilaian_awal_keperawatan_ralan = true;
                    akses2.master_triase_skala1 = true;
                    akses2.master_triase_skala2 = true;
                    akses2.master_triase_skala3 = true;
                    akses2.master_triase_skala4 = true;
                    akses2.master_triase_skala5 = true;
                    akses2.data_triase_igd = true;
                    akses2.master_triase_pemeriksaan = true;
                    akses2.master_triase_macamkasus = true;
                    akses2.master_cara_bayar = true;
                    akses2.status_kerja_dokter = true;
                    akses2.pasien_corona = true;
                    akses2.diagnosa_pasien_corona = true;
                    akses2.perawatan_pasien_corona = true;
                    akses2.inacbg_klaim_baru_manual2 = true;
                    akses2.assesmen_gizi_harian = true;
                    akses2.assesmen_gizi_ulang = true;
                    akses2.tombol_nota_billing = true;
                    akses2.tombol_simpan_hasil_rad = true;
                    akses2.monev_asuhan_gizi = true;
                    akses2.inacbg_klaim_raza = true;
                    akses2.pengajuan_klaim_inacbg_raza = true;
                    akses2.copy_pemeriksaan_dokter_kepetugas_ralan = true;
                    akses2.jkn_belum_diproses_klaim = true;
                    akses2.input_kode_icd = true;
                    akses2.kendali_Mutu_kendali_Biaya_INACBG = true;
                    akses2.dashboard_eResep = true;
                    akses2.bpjs_sep_internal = true;
                    akses2.kemenkes_sitt = true;
                    akses2.rencana_kontrol_jkn = true;
                    akses2.spri_jkn = true;
                    akses2.hapus_sep = true;
                    akses2.penilaian_awal_medis_ralan_kebidanan = true;
                    akses2.penilaian_awal_keperawatan_kebidanan = true;
                    akses2.ikhtisar_perawatan_hiv = true;
                    akses2.survey_kepuasan = true;
                    akses2.kemenkes_kanker = true;
                    akses2.aktivasi_bridging = true;
                    akses2.operator_antrian = true;
                    akses2.penilaian_awal_medis_ralan_tht = true;
                    akses2.rekam_psikologis = true;
                    akses2.penilaian_pasien_geriatri = true;
                    akses2.penilaian_awal_medis_ralan_mata = true;
                    akses2.surat_sakit = true;
                    akses2.surat_keterangan_kir_mcu = true;
                    akses2.asesmen_medik_dewasa_ranap = true;
                    akses2.pemberian_obat = true;
                    akses2.cppt = true;
                    akses2.bridging_satu_sehat = true;
                    akses2.kemoterapi = true;
                    akses2.cek_piutang = true;
                } else if (rs.getRow() >= 1) {
                    akses2.kode = "Admin Utama";
                    akses2.penyakit = true;
                    akses2.obat_penyakit = true;
                    akses2.dokter = true;
                    akses2.jadwal_praktek = true;
                    akses2.petugas = true;
                    akses2.pasien = true;
                    akses2.registrasi = true;
                    akses2.tindakan_ralan = true;
                    akses2.kamar_inap = true;
                    akses2.tindakan_ranap = true;
                    akses2.operasi = true;
                    akses2.rujukan_keluar = true;
                    akses2.rujukan_masuk = true;
                    akses2.beri_obat = true;
                    akses2.resep_pulang = true;
                    akses2.pasien_meninggal = true;
                    akses2.diet_pasien = true;
                    akses2.kelahiran_bayi = true;
                    akses2.periksa_lab = true;
                    akses2.periksa_radiologi = true;
                    akses2.kasir_ralan = true;
                    akses2.deposit_pasien = true;
                    akses2.piutang_pasien = true;
                    akses2.peminjaman_berkas = true;
                    akses2.barcode = true;
                    akses2.presensi_harian = true;
                    akses2.presensi_bulanan = true;
                    akses2.pegawai_admin = true;
                    akses2.pegawai_user = true;
                    akses2.suplier = true;
                    akses2.satuan_barang = true;
                    akses2.konversi_satuan = true;
                    akses2.jenis_barang = true;
                    akses2.obat = true;
                    akses2.stok_opname_obat = true;
                    akses2.stok_obat_pasien = true;
                    akses2.pengadaan_obat = true;
                    akses2.pemesanan_obat = true;
                    akses2.penjualan_obat = true;
                    akses2.piutang_obat = true;
                    akses2.retur_ke_suplier = true;
                    akses2.retur_dari_pembeli = true;
                    akses2.retur_obat_ranap = true;
                    akses2.retur_piutang_pasien = true;
                    akses2.keuntungan_penjualan = true;
                    akses2.keuntungan_beri_obat = true;
                    akses2.sirkulasi_obat = true;
                    akses2.ipsrs_barang = true;
                    akses2.ipsrs_pengadaan_barang = true;
                    akses2.ipsrs_stok_keluar = true;
                    akses2.ipsrs_rekap_pengadaan = true;
                    akses2.ipsrs_rekap_stok_keluar = true;
                    akses2.ipsrs_pengeluaran_harian = true;
                    akses2.ipsrs_jenis_barang = true;
                    akses2.inventaris_jenis = true;
                    akses2.inventaris_kategori = true;
                    akses2.inventaris_merk = true;
                    akses2.inventaris_ruang = true;
                    akses2.inventaris_produsen = true;
                    akses2.inventaris_koleksi = true;
                    akses2.inventaris_inventaris = true;
                    akses2.inventaris_sirkulasi = true;
                    akses2.parkir_jenis = true;
                    akses2.parkir_in = true;
                    akses2.parkir_out = true;
                    akses2.parkir_rekap_harian = true;
                    akses2.parkir_rekap_bulanan = true;
                    akses2.informasi_kamar = true;
                    akses2.harian_tindakan_poli = true;
                    akses2.obat_per_poli = true;
                    akses2.obat_per_kamar = true;
                    akses2.obat_per_dokter_ralan = true;
                    akses2.obat_per_dokter_ranap = true;
                    akses2.harian_dokter = true;
                    akses2.bulanan_dokter = true;
                    akses2.harian_paramedis = true;
                    akses2.bulanan_paramedis = true;
                    akses2.pembayaran_ralan = true;
                    akses2.pembayaran_ranap = true;
                    akses2.rekap_pembayaran_ralan = true;
                    akses2.rekap_pembayaran_ranap = true;
                    akses2.tagihan_masuk = true;
                    akses2.tambahan_biaya = true;
                    akses2.potongan_biaya = true;
                    akses2.resep_obat = true;
                    akses2.resume_pasien = true;
                    akses2.penyakit_ralan = true;
                    akses2.penyakit_ranap = true;
                    akses2.kamar = true;
                    akses2.tarif_ralan = true;
                    akses2.tarif_ranap = true;
                    akses2.tarif_lab = true;
                    akses2.tarif_radiologi = true;
                    akses2.tarif_operasi = true;
                    akses2.akun_rekening = true;
                    akses2.rekening_tahun = true;
                    akses2.posting_jurnal = true;
                    akses2.buku_besar = true;
                    akses2.cashflow = true;
                    akses2.keuangan = true;
                    akses2.pengeluaran = true;
                    akses2.setup_pjlab = true;
                    akses2.setup_otolokasi = true;
                    akses2.setup_jam_kamin = true;
                    akses2.setup_embalase = true;
                    akses2.tracer_login = true;
                    akses2.display = true;
                    akses2.set_harga_obat = true;
                    akses2.set_penggunaan_tarif = true;
                    akses2.set_oto_ralan = true;
                    akses2.biaya_harian = true;
                    akses2.biaya_masuk_sekali = true;
                    akses2.set_no_rm = true;
                    akses2.billing_ralan = true;
                    akses2.billing_ranap = true;
                    akses2.jm_ranap_dokter = true;
                    akses2.igd = true;
                    akses2.barcoderalan = true;
                    akses2.barcoderanap = true;
                    akses2.set_harga_obat_ralan = true;
                    akses2.set_harga_obat_ranap = true;
                    akses2.penyakit_pd3i = true;
                    akses2.surveilans_pd3i = true;
                    akses2.surveilans_ralan = true;
                    akses2.diagnosa_pasien = true;
                    akses2.admin = true;
                    akses2.user = true;
                    akses2.vakum = true;
                    akses2.aplikasi = true;
                    akses2.surveilans_ranap = true;
                    akses2.pny_takmenular_ranap = true;
                    akses2.pny_takmenular_ralan = true;
                    akses2.kunjungan_ralan = true;
                    akses2.rl32 = true;
                    akses2.rl33 = true;
                    akses2.rl37 = true;
                    akses2.rl38 = true;
                    akses2.harian_tindakan_dokter = true;
                    akses2.sms = true;
                    akses2.sidikjari = true;
                    akses2.jam_masuk = true;
                    akses2.jadwal_pegawai = true;
                    akses2.parkir_barcode = true;
                    akses2.set_nota = true;
                    akses2.dpjp_ranap = true;
                    akses2.mutasi_barang = true;
                    akses2.rl34 = true;
                    akses2.rl36 = true;
                    akses2.fee_visit_dokter = true;
                    akses2.fee_bacaan_ekg = true;
                    akses2.fee_rujukan_rontgen = true;
                    akses2.fee_rujukan_ranap = true;
                    akses2.fee_ralan = true;
                    akses2.akun_bayar = true;
                    akses2.bayar_pemesanan_obat = true;
                    akses2.obat_per_dokter_peresep = true;
                    akses2.pemasukan_lain = true;
                    akses2.pengaturan_rekening = true;
                    akses2.closing_kasir = true;
                    akses2.keterlambatan_presensi = true;
                    akses2.set_harga_kamar = true;
                    akses2.rekap_per_shift = true;
                    akses2.bpjs_cek_nik = true;
                    akses2.bpjs_cek_kartu = true;
                    akses2.obat_per_cara_bayar = true;
                    akses2.kunjungan_ranap = true;
                    akses2.bayar_piutang = true;
                    akses2.payment_point = true;
                    akses2.bpjs_cek_nomor_rujukan = true;
                    akses2.icd9 = true;
                    akses2.darurat_stok = true;
                    akses2.retensi_rm = true;
                    akses2.temporary_presensi = true;
                    akses2.jurnal_harian = true;
                    akses2.sirkulasi_obat2 = true;
                    akses2.edit_registrasi = true;
                    akses2.bpjs_referensi_diagnosa = true;
                    akses2.bpjs_referensi_poli = true;
                    akses2.industrifarmasi = true;
                    akses2.harian_js = true;
                    akses2.bulanan_js = true;
                    akses2.harian_paket_bhp = true;
                    akses2.bulanan_paket_bhp = true;
                    akses2.piutang_pasien2 = true;
                    akses2.bpjs_referensi_faskes = true;
                    akses2.bpjs_sep = true;
                    akses2.pengambilan_utd = true;
                    akses2.tarif_utd = true;
                    akses2.pengambilan_utd2 = true;
                    akses2.utd_medis_rusak = true;
                    akses2.pengambilan_penunjang_utd = true;
                    akses2.pengambilan_penunjang_utd2 = true;
                    akses2.utd_penunjang_rusak = true;
                    akses2.suplier_penunjang = true;
                    akses2.utd_donor = true;
                    akses2.bpjs_monitoring_klaim = true;
                    akses2.utd_cekal_darah = true;
                    akses2.utd_komponen_darah = true;
                    akses2.utd_stok_darah = true;
                    akses2.utd_pemisahan_darah = true;
                    akses2.harian_kamar = true;
                    akses2.rincian_piutang_pasien = true;
                    akses2.keuntungan_beri_obat_nonpiutang = true;
                    akses2.reklasifikasi_ralan = true;
                    akses2.reklasifikasi_ranap = true;
                    akses2.utd_penyerahan_darah = true;
                    akses2.hutang_obat = true;
                    akses2.riwayat_obat_alkes_bhp = true;
                    akses2.sensus_harian_poli = true;
                    akses2.rl4a = true;
                    akses2.aplicare_referensi_kamar = true;
                    akses2.aplicare_ketersediaan_kamar = true;
                    akses2.inacbg_klaim_baru_otomatis = true;
                    akses2.inacbg_klaim_baru_manual = true;
                    akses2.inacbg_coder_nik = true;
                    akses2.mutasi_berkas = true;
                    akses2.akun_piutang = true;
                    akses2.harian_kso = true;
                    akses2.bulanan_kso = true;
                    akses2.harian_menejemen = true;
                    akses2.bulanan_menejemen = true;
                    akses2.inhealth_cek_eligibilitas = true;
                    akses2.inhealth_referensi_jenpel_ruang_rawat = true;
                    akses2.inhealth_referensi_poli = true;
                    akses2.inhealth_referensi_faskes = true;
                    akses2.inhealth_sjp = true;
                    akses2.piutang_ralan = true;
                    akses2.piutang_ranap = true;
                    akses2.detail_piutang_penjab = true;
                    akses2.lama_pelayanan_ralan = true;
                    akses2.catatan_pasien = true;
                    akses2.rl4b = true;
                    akses2.rl4asebab = true;
                    akses2.rl4bsebab = true;
                    akses2.data_HAIs = true;
                    akses2.harian_HAIs = true;
                    akses2.bulanan_HAIs = true;
                    akses2.hitung_bor = true;
                    akses2.perusahaan_pasien = true;
                    akses2.resep_dokter = true;
                    akses2.lama_pelayanan_apotek = true;
                    akses2.hitung_alos = true;
                    akses2.detail_tindakan = true;
                    akses2.rujukan_poli_internal = true;
                    akses2.rekap_poli_anak = true;
                    akses2.grafik_kunjungan_poli = true;
                    akses2.grafik_kunjungan_perdokter = true;
                    akses2.grafik_kunjungan_perpekerjaan = true;
                    akses2.grafik_kunjungan_perpendidikan = true;
                    akses2.grafik_kunjungan_pertahun = true;
                    akses2.berkas_digital_perawatan = true;
                    akses2.penyakit_menular_ranap = true;
                    akses2.penyakit_menular_ralan = true;
                    akses2.grafik_kunjungan_perbulan = true;
                    akses2.grafik_kunjungan_pertanggal = true;
                    akses2.grafik_kunjungan_demografi = true;
                    akses2.grafik_kunjungan_statusdaftartahun = true;
                    akses2.grafik_kunjungan_statusdaftartahun2 = true;
                    akses2.grafik_kunjungan_statusdaftarbulan = true;
                    akses2.grafik_kunjungan_statusdaftarbulan2 = true;
                    akses2.grafik_kunjungan_statusdaftartanggal = true;
                    akses2.grafik_kunjungan_statusdaftartanggal2 = true;
                    akses2.grafik_kunjungan_statusbataltahun = true;
                    akses2.grafik_kunjungan_statusbatalbulan = true;
                    akses2.pcare_cek_penyakit = true;
                    akses2.grafik_kunjungan_statusbataltanggal = true;
                    akses2.kategori_barang = true;
                    akses2.golongan_barang = true;
                    akses2.pemberian_obat_pertanggal = true;
                    akses2.penjualan_obat_pertanggal = true;
                    akses2.bpjs_rujukan_vclaim = true;
                    akses2.skdp_bpjs = true;
                    akses2.booking_registrasi = true;
                    akses2.bpjs_cek_riwayat_rujukan_pcare = true;
                    akses2.bpjs_cek_riwayat_rujukan_rs = true;
                    akses2.bpjs_cek_rujukan_kartu_rs = true;
                    akses2.bpjs_cek_tgl_rujukan = true;
                    akses2.bpjs_cek_no_rujukan_rs = true;
                    akses2.bpjs_cek_rujukan_kartu_pcare = true;
                    akses2.bpjs_cek_referensi_kelas_rawat = true;
                    akses2.bpjs_cek_referensi_prosedur = true;
                    akses2.bpjs_cek_referensi_dpjp = true;
                    akses2.bpjs_cek_referensi_dokter = true;
                    akses2.bpjs_cek_referensi_spesialistik = true;
                    akses2.bpjs_cek_referensi_ruang_rawat = true;
                    akses2.bpjs_cek_referensi_cara_keluar = true;
                    akses2.bpjs_cek_referensi_pasca_pulang = true;
                    akses2.bpjs_cek_referensi_propinsi = true;
                    akses2.bpjs_cek_referensi_kabupaten = true;
                    akses2.bpjs_cek_referensi_kecamatan = true;
                    akses2.permintaan_lab = true;
                    akses2.permintaan_radiologi = true;
                    akses2.selisih_tarif_bpjs = true;
                    akses2.edit_data_kematian = true;
                    akses2.bridging_jamkesda = true;
                    akses2.masuk_pindah_pulang_inap = true;
                    akses2.masuk_pindah_inap = true;
                    akses2.jumlah_macam_diet = true;
                    akses2.jumlah_porsi_diet = true;
                    akses2.status_gizi = true;
                    akses2.gizi_buruk = true;
                    akses2.master_faskes = true;
                    akses2.setstatusralan = true;
                    akses2.telusurpasien = true;
                    akses2.sisrute_rujukan_keluar = true;
                    akses2.sisrute_rujukan_masuk = true;
                    akses2.sisrute_referensi_diagnosa = true;
                    akses2.sisrute_referensi_alasanrujuk = true;
                    akses2.sisrute_referensi_faskes = true;
                    akses2.barang_cssd = true;
                    akses2.status_pulang_inap = true;
                    akses2.data_persalinan = true;
                    akses2.data_ponek = true;
                    akses2.reg_boking_kasir = true;
                    akses2.bahasa_pasien = true;
                    akses2.suku_bangsa = true;
                    akses2.harian_hais_inap = true;
                    akses2.harian_hais_jalan = true;
                    akses2.bulanan_hais_inap = true;
                    akses2.bulanan_hais_jalan = true;
                    akses2.ringkasan_pulang_ranap = true;
                    akses2.laporan_farmasi = true;
                    akses2.master_masalah_keperawatan = true;
                    akses2.penilaian_awal_keperawatan_ralan = true;
                    akses2.master_triase_skala1 = true;
                    akses2.master_triase_skala2 = true;
                    akses2.master_triase_skala3 = true;
                    akses2.master_triase_skala4 = true;
                    akses2.master_triase_skala5 = true;
                    akses2.data_triase_igd = true;
                    akses2.master_triase_pemeriksaan = true;
                    akses2.master_triase_macamkasus = true;
                    akses2.master_cara_bayar = true;
                    akses2.status_kerja_dokter = true;
                    akses2.pasien_corona = true;
                    akses2.diagnosa_pasien_corona = true;
                    akses2.perawatan_pasien_corona = true;
                    akses2.inacbg_klaim_baru_manual2 = true;
                    akses2.assesmen_gizi_harian = true;
                    akses2.assesmen_gizi_ulang = true;
                    akses2.tombol_nota_billing = true;
                    akses2.tombol_simpan_hasil_rad = true;
                    akses2.monev_asuhan_gizi = true;
                    akses2.inacbg_klaim_raza = true;
                    akses2.pengajuan_klaim_inacbg_raza = true;
                    akses2.copy_pemeriksaan_dokter_kepetugas_ralan = true;
                    akses2.jkn_belum_diproses_klaim = true;
                    akses2.input_kode_icd = true;
                    akses2.kendali_Mutu_kendali_Biaya_INACBG = true;
                    akses2.dashboard_eResep = true;
                    akses2.bpjs_sep_internal = true;
                    akses2.kemenkes_sitt = true;
                    akses2.rencana_kontrol_jkn = true;
                    akses2.spri_jkn = true;
                    akses2.hapus_sep = true;
                    akses2.penilaian_awal_medis_ralan_kebidanan = true;
                    akses2.penilaian_awal_keperawatan_kebidanan = true;
                    akses2.ikhtisar_perawatan_hiv = true;
                    akses2.survey_kepuasan = true;
                    akses2.kemenkes_kanker = true;
                    akses2.aktivasi_bridging = true;
                    akses2.operator_antrian = true;
                    akses2.penilaian_awal_medis_ralan_tht = true;
                    akses2.rekam_psikologis = true;
                    akses2.penilaian_pasien_geriatri = true;
                    akses2.penilaian_awal_medis_ralan_mata = true;
                    akses2.surat_sakit = true;
                    akses2.surat_keterangan_kir_mcu = true;
                    akses2.asesmen_medik_dewasa_ranap = true;
                    akses2.pemberian_obat = true;
                    akses2.cppt = true;
                    akses2.bridging_satu_sehat = true;
                    akses2.kemoterapi = true;
                    akses2.cek_piutang = true;
                } else if (rs2.getRow() >= 1) {
                    rs2.beforeFirst();
                    rs2.next();
//                    akses.kode = user;
                    akses2.kode = rs2.getString("nip");
                    akses2.penyakit = rs2.getBoolean("penyakit");
                    akses2.obat_penyakit = rs2.getBoolean("obat_penyakit");
                    akses2.dokter = rs2.getBoolean("dokter");
                    akses2.jadwal_praktek = rs2.getBoolean("jadwal_praktek");
                    akses2.petugas = rs2.getBoolean("petugas");
                    akses2.pasien = rs2.getBoolean("pasien");
                    akses2.registrasi = rs2.getBoolean("registrasi");
                    akses2.tindakan_ralan = rs2.getBoolean("tindakan_ralan");
                    akses2.kamar_inap = rs2.getBoolean("kamar_inap");
                    akses2.tindakan_ranap = rs2.getBoolean("tindakan_ranap");
                    akses2.operasi = rs2.getBoolean("operasi");
                    akses2.rujukan_keluar = rs2.getBoolean("rujukan_keluar");
                    akses2.rujukan_masuk = rs2.getBoolean("rujukan_masuk");
                    akses2.beri_obat = rs2.getBoolean("beri_obat");
                    akses2.resep_pulang = rs2.getBoolean("resep_pulang");
                    akses2.pasien_meninggal = rs2.getBoolean("pasien_meninggal");
                    akses2.diet_pasien = rs2.getBoolean("diet_pasien");
                    akses2.kelahiran_bayi = rs2.getBoolean("kelahiran_bayi");
                    akses2.periksa_lab = rs2.getBoolean("periksa_lab");
                    akses2.periksa_radiologi = rs2.getBoolean("periksa_radiologi");
                    akses2.kasir_ralan = rs2.getBoolean("kasir_ralan");
                    akses2.deposit_pasien = rs2.getBoolean("deposit_pasien");
                    akses2.piutang_pasien = rs2.getBoolean("piutang_pasien");
                    akses2.peminjaman_berkas = rs2.getBoolean("peminjaman_berkas");
                    akses2.barcode = rs2.getBoolean("barcode");
                    akses2.presensi_harian = rs2.getBoolean("presensi_harian");
                    akses2.presensi_bulanan = rs2.getBoolean("presensi_bulanan");
                    akses2.pegawai_admin = rs2.getBoolean("pegawai_admin");
                    akses2.pegawai_user = rs2.getBoolean("pegawai_user");
                    akses2.suplier = rs2.getBoolean("suplier");
                    akses2.satuan_barang = rs2.getBoolean("satuan_barang");
                    akses2.konversi_satuan = rs2.getBoolean("konversi_satuan");
                    akses2.jenis_barang = rs2.getBoolean("jenis_barang");
                    akses2.obat = rs2.getBoolean("obat");
                    akses2.stok_opname_obat = rs2.getBoolean("stok_opname_obat");
                    akses2.stok_obat_pasien = rs2.getBoolean("stok_obat_pasien");
                    akses2.pengadaan_obat = rs2.getBoolean("pengadaan_obat");
                    akses2.pemesanan_obat = rs2.getBoolean("pemesanan_obat");
                    akses2.penjualan_obat = rs2.getBoolean("penjualan_obat");
                    akses2.piutang_obat = rs2.getBoolean("piutang_obat");
                    akses2.retur_ke_suplier = rs2.getBoolean("retur_ke_suplier");
                    akses2.retur_dari_pembeli = rs2.getBoolean("retur_dari_pembeli");
                    akses2.retur_obat_ranap = rs2.getBoolean("retur_obat_ranap");
                    akses2.retur_piutang_pasien = rs2.getBoolean("retur_piutang_pasien");
                    akses2.keuntungan_penjualan = rs2.getBoolean("keuntungan_penjualan");
                    akses2.keuntungan_beri_obat = rs2.getBoolean("keuntungan_beri_obat");
                    akses2.sirkulasi_obat = rs2.getBoolean("sirkulasi_obat");
                    akses2.ipsrs_barang = rs2.getBoolean("ipsrs_barang");
                    akses2.ipsrs_pengadaan_barang = rs2.getBoolean("ipsrs_pengadaan_barang");
                    akses2.ipsrs_stok_keluar = rs2.getBoolean("ipsrs_stok_keluar");
                    akses2.ipsrs_jenis_barang = rs2.getBoolean("ipsrs_jenis_barang");
                    akses2.ipsrs_rekap_pengadaan = rs2.getBoolean("ipsrs_rekap_pengadaan");
                    akses2.ipsrs_rekap_stok_keluar = rs2.getBoolean("ipsrs_rekap_stok_keluar");
                    akses2.ipsrs_pengeluaran_harian = rs2.getBoolean("ipsrs_pengeluaran_harian");
                    akses2.inventaris_jenis = rs2.getBoolean("inventaris_jenis");
                    akses2.inventaris_kategori = rs2.getBoolean("inventaris_kategori");
                    akses2.inventaris_merk = rs2.getBoolean("inventaris_merk");
                    akses2.inventaris_ruang = rs2.getBoolean("inventaris_ruang");
                    akses2.inventaris_produsen = rs2.getBoolean("inventaris_produsen");
                    akses2.inventaris_koleksi = rs2.getBoolean("inventaris_koleksi");
                    akses2.inventaris_inventaris = rs2.getBoolean("inventaris_inventaris");
                    akses2.inventaris_sirkulasi = rs2.getBoolean("inventaris_sirkulasi");
                    akses2.parkir_jenis = rs2.getBoolean("parkir_jenis");
                    akses2.parkir_in = rs2.getBoolean("parkir_in");
                    akses2.parkir_out = rs2.getBoolean("parkir_out");
                    akses2.parkir_rekap_harian = rs2.getBoolean("parkir_rekap_harian");
                    akses2.parkir_rekap_bulanan = rs2.getBoolean("parkir_rekap_bulanan");
                    akses2.informasi_kamar = rs2.getBoolean("informasi_kamar");
                    akses2.harian_tindakan_poli = rs2.getBoolean("harian_tindakan_poli");
                    akses2.obat_per_poli = rs2.getBoolean("obat_per_poli");
                    akses2.obat_per_kamar = rs2.getBoolean("obat_per_kamar");
                    akses2.obat_per_dokter_ralan = rs2.getBoolean("obat_per_dokter_ralan");
                    akses2.obat_per_dokter_ranap = rs2.getBoolean("obat_per_dokter_ranap");
                    akses2.harian_dokter = rs2.getBoolean("harian_dokter");
                    akses2.bulanan_dokter = rs2.getBoolean("bulanan_dokter");
                    akses2.harian_paramedis = rs2.getBoolean("harian_paramedis");
                    akses2.bulanan_paramedis = rs2.getBoolean("bulanan_paramedis");
                    akses2.pembayaran_ralan = rs2.getBoolean("pembayaran_ralan");
                    akses2.pembayaran_ranap = rs2.getBoolean("pembayaran_ranap");
                    akses2.rekap_pembayaran_ralan = rs2.getBoolean("rekap_pembayaran_ralan");
                    akses2.rekap_pembayaran_ranap = rs2.getBoolean("rekap_pembayaran_ranap");
                    akses2.tagihan_masuk = rs2.getBoolean("tagihan_masuk");
                    akses2.tambahan_biaya = rs2.getBoolean("tambahan_biaya");
                    akses2.potongan_biaya = rs2.getBoolean("potongan_biaya");
                    akses2.resep_obat = rs2.getBoolean("resep_obat");
                    akses2.resume_pasien = rs2.getBoolean("resume_pasien");
                    akses2.penyakit_ralan = rs2.getBoolean("penyakit_ralan");
                    akses2.penyakit_ranap = rs2.getBoolean("penyakit_ranap");
                    akses2.kamar = rs2.getBoolean("kamar");
                    akses2.tarif_ralan = rs2.getBoolean("tarif_ralan");
                    akses2.tarif_ranap = rs2.getBoolean("tarif_ranap");
                    akses2.tarif_lab = rs2.getBoolean("tarif_lab");
                    akses2.tarif_radiologi = rs2.getBoolean("tarif_radiologi");
                    akses2.tarif_operasi = rs2.getBoolean("tarif_operasi");
                    akses2.akun_rekening = rs2.getBoolean("akun_rekening");
                    akses2.rekening_tahun = rs2.getBoolean("rekening_tahun");
                    akses2.posting_jurnal = rs2.getBoolean("posting_jurnal");
                    akses2.buku_besar = rs2.getBoolean("buku_besar");
                    akses2.cashflow = rs2.getBoolean("cashflow");
                    akses2.keuangan = rs2.getBoolean("keuangan");
                    akses2.pengeluaran = rs2.getBoolean("pengeluaran");
                    akses2.setup_pjlab = rs2.getBoolean("setup_pjlab");
                    akses2.setup_otolokasi = rs2.getBoolean("setup_otolokasi");
                    akses2.setup_jam_kamin = rs2.getBoolean("setup_jam_kamin");
                    akses2.setup_embalase = rs2.getBoolean("setup_embalase");
                    akses2.tracer_login = rs2.getBoolean("tracer_login");
                    akses2.display = rs2.getBoolean("display");
                    akses2.set_harga_obat = rs2.getBoolean("set_harga_obat");
                    akses2.set_penggunaan_tarif = rs2.getBoolean("set_penggunaan_tarif");
                    akses2.set_oto_ralan = rs2.getBoolean("set_oto_ralan");
                    akses2.biaya_harian = rs2.getBoolean("biaya_harian");
                    akses2.biaya_masuk_sekali = rs2.getBoolean("biaya_masuk_sekali");
                    akses2.set_no_rm = rs2.getBoolean("set_no_rm");
                    akses2.billing_ralan = rs2.getBoolean("billing_ralan");
                    akses2.billing_ranap = rs2.getBoolean("billing_ranap");
                    akses2.jm_ranap_dokter = rs2.getBoolean("jm_ranap_dokter");
                    akses2.igd = rs2.getBoolean("igd");
                    akses2.barcoderalan = rs2.getBoolean("barcoderalan");
                    akses2.barcoderanap = rs2.getBoolean("barcoderanap");
                    akses2.set_harga_obat_ralan = rs2.getBoolean("set_harga_obat_ralan");
                    akses2.set_harga_obat_ranap = rs2.getBoolean("set_harga_obat_ranap");
                    akses2.penyakit_pd3i = rs2.getBoolean("penyakit_pd3i");
                    akses2.surveilans_pd3i = rs2.getBoolean("surveilans_pd3i");
                    akses2.surveilans_ralan = rs2.getBoolean("surveilans_ralan");
                    akses2.diagnosa_pasien = rs2.getBoolean("diagnosa_pasien");
                    akses2.surveilans_ranap = rs2.getBoolean("surveilans_ranap");
                    akses2.admin = false;
                    akses2.user = false;
                    akses2.vakum = false;
                    akses2.aplikasi = false;
                    akses2.pny_takmenular_ranap = rs2.getBoolean("pny_takmenular_ranap");
                    akses2.pny_takmenular_ralan = rs2.getBoolean("pny_takmenular_ralan");
                    akses2.kunjungan_ralan = rs2.getBoolean("kunjungan_ralan");
                    akses2.rl32 = rs2.getBoolean("rl32");
                    akses2.rl33 = rs2.getBoolean("rl33");
                    akses2.rl37 = rs2.getBoolean("rl37");
                    akses2.rl38 = rs2.getBoolean("rl38");
                    akses2.harian_tindakan_dokter = rs2.getBoolean("harian_tindakan_dokter");
                    akses2.sms = rs2.getBoolean("sms");
                    akses2.sidikjari = rs2.getBoolean("sidikjari");
                    akses2.jam_masuk = rs2.getBoolean("jam_masuk");
                    akses2.jadwal_pegawai = rs2.getBoolean("jadwal_pegawai");
                    akses2.parkir_barcode = rs2.getBoolean("parkir_barcode");
                    akses2.set_nota = rs2.getBoolean("set_nota");
                    akses2.dpjp_ranap = rs2.getBoolean("dpjp_ranap");
                    akses2.mutasi_barang = rs2.getBoolean("mutasi_barang");
                    akses2.rl34 = rs2.getBoolean("rl34");
                    akses2.rl36 = rs2.getBoolean("rl36");
                    akses2.fee_visit_dokter = rs2.getBoolean("fee_visit_dokter");
                    akses2.fee_bacaan_ekg = rs2.getBoolean("fee_bacaan_ekg");
                    akses2.fee_rujukan_rontgen = rs2.getBoolean("fee_rujukan_rontgen");
                    akses2.fee_rujukan_ranap = rs2.getBoolean("fee_rujukan_ranap");
                    akses2.fee_ralan = rs2.getBoolean("fee_ralan");
                    akses2.akun_bayar = rs2.getBoolean("akun_bayar");
                    akses2.bayar_pemesanan_obat = rs2.getBoolean("bayar_pemesanan_obat");
                    akses2.obat_per_dokter_peresep = rs2.getBoolean("obat_per_dokter_peresep");
                    akses2.pemasukan_lain = rs2.getBoolean("pemasukan_lain");
                    akses2.pengaturan_rekening = rs2.getBoolean("pengaturan_rekening");
                    akses2.closing_kasir = rs2.getBoolean("closing_kasir");
                    akses2.keterlambatan_presensi = rs2.getBoolean("keterlambatan_presensi");
                    akses2.set_harga_kamar = rs2.getBoolean("set_harga_kamar");
                    akses2.rekap_per_shift = rs2.getBoolean("rekap_per_shift");
                    akses2.bpjs_cek_nik = rs2.getBoolean("bpjs_cek_nik");
                    akses2.bpjs_cek_kartu = rs2.getBoolean("bpjs_cek_kartu");
                    akses2.obat_per_cara_bayar = rs2.getBoolean("obat_per_cara_bayar");
                    akses2.kunjungan_ranap = rs2.getBoolean("kunjungan_ranap");
                    akses2.bayar_piutang = rs2.getBoolean("bayar_piutang");
                    akses2.payment_point = rs2.getBoolean("payment_point");
                    akses2.bpjs_cek_nomor_rujukan = rs2.getBoolean("bpjs_cek_nomor_rujukan");
                    akses2.icd9 = rs2.getBoolean("icd9");
                    akses2.darurat_stok = rs2.getBoolean("darurat_stok");
                    akses2.retensi_rm = rs2.getBoolean("retensi_rm");
                    akses2.temporary_presensi = rs2.getBoolean("temporary_presensi");
                    akses2.jurnal_harian = rs2.getBoolean("jurnal_harian");
                    akses2.sirkulasi_obat2 = rs2.getBoolean("sirkulasi_obat2");
                    akses2.edit_registrasi = rs2.getBoolean("edit_registrasi");
                    akses2.bpjs_referensi_diagnosa = rs2.getBoolean("bpjs_referensi_diagnosa");
                    akses2.bpjs_referensi_poli = rs2.getBoolean("bpjs_referensi_poli");
                    akses2.industrifarmasi = rs2.getBoolean("industrifarmasi");
                    akses2.harian_js = rs2.getBoolean("harian_js");
                    akses2.bulanan_js = rs2.getBoolean("bulanan_js");
                    akses2.harian_paket_bhp = rs2.getBoolean("harian_paket_bhp");
                    akses2.bulanan_paket_bhp = rs2.getBoolean("bulanan_paket_bhp");
                    akses2.piutang_pasien2 = rs2.getBoolean("piutang_pasien2");
                    akses2.bpjs_referensi_faskes = rs2.getBoolean("bpjs_referensi_faskes");
                    akses2.bpjs_sep = rs2.getBoolean("bpjs_sep");
                    akses2.pengambilan_utd = rs2.getBoolean("pengambilan_utd");
                    akses2.tarif_utd = rs2.getBoolean("tarif_utd");
                    akses2.pengambilan_utd2 = rs2.getBoolean("pengambilan_utd2");
                    akses2.utd_medis_rusak = rs2.getBoolean("utd_medis_rusak");
                    akses2.pengambilan_penunjang_utd = rs2.getBoolean("pengambilan_penunjang_utd");
                    akses2.pengambilan_penunjang_utd2 = rs2.getBoolean("pengambilan_penunjang_utd2");
                    akses2.utd_penunjang_rusak = rs2.getBoolean("utd_penunjang_rusak");
                    akses2.suplier_penunjang = rs2.getBoolean("suplier_penunjang");
                    akses2.utd_donor = rs2.getBoolean("utd_donor");
                    akses2.bpjs_monitoring_klaim = rs2.getBoolean("bpjs_monitoring_klaim");
                    akses2.utd_cekal_darah = rs2.getBoolean("utd_cekal_darah");
                    akses2.utd_komponen_darah = rs2.getBoolean("utd_komponen_darah");
                    akses2.utd_stok_darah = rs2.getBoolean("utd_stok_darah");
                    akses2.utd_pemisahan_darah = rs2.getBoolean("utd_pemisahan_darah");
                    akses2.harian_kamar = rs2.getBoolean("harian_kamar");
                    akses2.rincian_piutang_pasien = rs2.getBoolean("rincian_piutang_pasien");
                    akses2.keuntungan_beri_obat_nonpiutang = rs2.getBoolean("keuntungan_beri_obat_nonpiutang");
                    akses2.reklasifikasi_ralan = rs2.getBoolean("reklasifikasi_ralan");
                    akses2.reklasifikasi_ranap = rs2.getBoolean("reklasifikasi_ranap");
                    akses2.utd_penyerahan_darah = rs2.getBoolean("utd_penyerahan_darah");
                    akses2.hutang_obat = rs2.getBoolean("hutang_obat");
                    akses2.riwayat_obat_alkes_bhp = rs2.getBoolean("riwayat_obat_alkes_bhp");
                    akses2.sensus_harian_poli = rs2.getBoolean("sensus_harian_poli");
                    akses2.rl4a = rs2.getBoolean("rl4a");
                    akses2.aplicare_referensi_kamar = rs2.getBoolean("aplicare_referensi_kamar");
                    akses2.aplicare_ketersediaan_kamar = rs2.getBoolean("aplicare_ketersediaan_kamar");
                    akses2.inacbg_klaim_baru_otomatis = rs2.getBoolean("inacbg_klaim_baru_otomatis");
                    akses2.inacbg_klaim_baru_manual = rs2.getBoolean("inacbg_klaim_baru_manual");
                    akses2.inacbg_coder_nik = rs2.getBoolean("inacbg_coder_nik");
                    akses2.mutasi_berkas = rs2.getBoolean("mutasi_berkas");
                    akses2.akun_piutang = rs2.getBoolean("akun_piutang");
                    akses2.harian_kso = rs2.getBoolean("harian_kso");
                    akses2.bulanan_kso = rs2.getBoolean("bulanan_kso");
                    akses2.harian_menejemen = rs2.getBoolean("harian_menejemen");
                    akses2.bulanan_menejemen = rs2.getBoolean("bulanan_menejemen");
                    akses2.inhealth_cek_eligibilitas = rs2.getBoolean("inhealth_cek_eligibilitas");
                    akses2.inhealth_referensi_jenpel_ruang_rawat = rs2.getBoolean("inhealth_referensi_jenpel_ruang_rawat");
                    akses2.inhealth_referensi_poli = rs2.getBoolean("inhealth_referensi_poli");
                    akses2.inhealth_referensi_faskes = rs2.getBoolean("inhealth_referensi_faskes");
                    akses2.inhealth_sjp = rs2.getBoolean("inhealth_sjp");
                    akses2.piutang_ralan = rs2.getBoolean("piutang_ralan");
                    akses2.piutang_ranap = rs2.getBoolean("piutang_ranap");
                    akses2.detail_piutang_penjab = rs2.getBoolean("detail_piutang_penjab");
                    akses2.lama_pelayanan_ralan = rs2.getBoolean("lama_pelayanan_ralan");
                    akses2.catatan_pasien = rs2.getBoolean("catatan_pasien");
                    akses2.rl4b = rs2.getBoolean("rl4b");
                    akses2.rl4asebab = rs2.getBoolean("rl4asebab");
                    akses2.rl4bsebab = rs2.getBoolean("rl4bsebab");
                    akses2.data_HAIs = rs2.getBoolean("data_HAIs");
                    akses2.harian_HAIs = rs2.getBoolean("harian_HAIs");
                    akses2.bulanan_HAIs = rs2.getBoolean("bulanan_HAIs");
                    akses2.hitung_bor = rs2.getBoolean("hitung_bor");
                    akses2.perusahaan_pasien = rs2.getBoolean("perusahaan_pasien");
                    akses2.namauser = rs2.getString("nama");
                    akses2.resep_dokter = rs2.getBoolean("resep_dokter");
                    akses2.lama_pelayanan_apotek = rs2.getBoolean("lama_pelayanan_apotek");
                    akses2.hitung_alos = rs2.getBoolean("hitung_alos");
                    akses2.detail_tindakan = rs2.getBoolean("detail_tindakan");
                    akses2.rujukan_poli_internal = rs2.getBoolean("rujukan_poli_internal");
                    akses2.rekap_poli_anak = rs2.getBoolean("rekap_poli_anak");
                    akses2.grafik_kunjungan_poli = rs2.getBoolean("grafik_kunjungan_poli");
                    akses2.grafik_kunjungan_perdokter = rs2.getBoolean("grafik_kunjungan_perdokter");
                    akses2.grafik_kunjungan_perpekerjaan = rs2.getBoolean("grafik_kunjungan_perpekerjaan");
                    akses2.grafik_kunjungan_perpendidikan = rs2.getBoolean("grafik_kunjungan_perpendidikan");
                    akses2.grafik_kunjungan_pertahun = rs2.getBoolean("grafik_kunjungan_pertahun");
                    akses2.berkas_digital_perawatan = rs2.getBoolean("berkas_digital_perawatan");
                    akses2.penyakit_menular_ranap = rs2.getBoolean("penyakit_menular_ranap");
                    akses2.penyakit_menular_ralan = rs2.getBoolean("penyakit_menular_ralan");
                    akses2.grafik_kunjungan_perbulan = rs2.getBoolean("grafik_kunjungan_perbulan");
                    akses2.grafik_kunjungan_pertanggal = rs2.getBoolean("grafik_kunjungan_pertanggal");
                    akses2.grafik_kunjungan_demografi = rs2.getBoolean("grafik_kunjungan_demografi");
                    akses2.grafik_kunjungan_statusdaftartahun = rs2.getBoolean("grafik_kunjungan_statusdaftartahun");
                    akses2.grafik_kunjungan_statusdaftartahun2 = rs2.getBoolean("grafik_kunjungan_statusdaftartahun2");
                    akses2.grafik_kunjungan_statusdaftarbulan = rs2.getBoolean("grafik_kunjungan_statusdaftarbulan");
                    akses2.grafik_kunjungan_statusdaftarbulan2 = rs2.getBoolean("grafik_kunjungan_statusdaftarbulan2");
                    akses2.grafik_kunjungan_statusdaftartanggal = rs2.getBoolean("grafik_kunjungan_statusdaftartanggal");
                    akses2.grafik_kunjungan_statusdaftartanggal2 = rs2.getBoolean("grafik_kunjungan_statusdaftartanggal2");
                    akses2.grafik_kunjungan_statusbataltahun = rs2.getBoolean("grafik_kunjungan_statusbataltahun");
                    akses2.grafik_kunjungan_statusbatalbulan = rs2.getBoolean("grafik_kunjungan_statusbatalbulan");
                    akses2.pcare_cek_penyakit = rs2.getBoolean("pcare_cek_penyakit");
                    akses2.grafik_kunjungan_statusbataltanggal = rs2.getBoolean("grafik_kunjungan_statusbataltanggal");
                    akses2.kategori_barang = rs2.getBoolean("kategori_barang");
                    akses2.golongan_barang = rs2.getBoolean("golongan_barang");
                    akses2.pemberian_obat_pertanggal = rs2.getBoolean("pemberian_obat_pertanggal");
                    akses2.penjualan_obat_pertanggal = rs2.getBoolean("penjualan_obat_pertanggal");
                    akses2.bpjs_rujukan_vclaim = rs2.getBoolean("rujukan_keluar_vclaim_bpjs");
                    akses2.skdp_bpjs = rs2.getBoolean("skdp_bpjs");
                    akses2.booking_registrasi = rs2.getBoolean("booking_registrasi");
                    akses2.bpjs_cek_riwayat_rujukan_pcare = rs2.getBoolean("bpjs_cek_riwayat_rujukan_pcare");
                    akses2.bpjs_cek_riwayat_rujukan_rs = rs2.getBoolean("bpjs_cek_riwayat_rujukan_rs");
                    akses2.bpjs_cek_rujukan_kartu_rs = rs2.getBoolean("bpjs_cek_rujukan_kartu_rs");
                    akses2.bpjs_cek_tgl_rujukan = rs2.getBoolean("bpjs_cek_tanggal_rujukan");
                    akses2.bpjs_cek_no_rujukan_rs = rs2.getBoolean("bpjs_cek_no_rujukan_rs");
                    akses2.bpjs_cek_rujukan_kartu_pcare = rs2.getBoolean("bpjs_cek_rujukan_kartu_pcare");
                    akses2.bpjs_cek_referensi_kelas_rawat = rs2.getBoolean("bpjs_cek_referensi_kelas_rawat");
                    akses2.bpjs_cek_referensi_prosedur = rs2.getBoolean("bpjs_cek_referensi_prosedur");
                    akses2.bpjs_cek_referensi_dpjp = rs2.getBoolean("bpjs_cek_referensi_dpjp");
                    akses2.bpjs_cek_referensi_dokter = rs2.getBoolean("bpjs_cek_referensi_dokter");
                    akses2.bpjs_cek_referensi_spesialistik = rs2.getBoolean("bpjs_cek_referensi_spesialistik");
                    akses2.bpjs_cek_referensi_ruang_rawat = rs2.getBoolean("bpjs_cek_referensi_ruang_rawat");
                    akses2.bpjs_cek_referensi_cara_keluar = rs2.getBoolean("bpjs_cek_referensi_cara_keluar");
                    akses2.bpjs_cek_referensi_pasca_pulang = rs2.getBoolean("bpjs_cek_referensi_pasca_pulang");
                    akses2.bpjs_cek_referensi_propinsi = rs2.getBoolean("bpjs_cek_referensi_propinsi");
                    akses2.bpjs_cek_referensi_kabupaten = rs2.getBoolean("bpjs_cek_referensi_kabupaten");
                    akses2.bpjs_cek_referensi_kecamatan = rs2.getBoolean("bpjs_cek_referensi_kecamatan");
                    akses2.permintaan_lab = rs2.getBoolean("permintaan_lab");
                    akses2.permintaan_radiologi = rs2.getBoolean("permintaan_radiologi");
                    akses2.selisih_tarif_bpjs = rs2.getBoolean("selisih_tarif_bpjs");
                    akses2.edit_data_kematian = rs2.getBoolean("edit_data_kematian");
                    akses2.bridging_jamkesda = rs2.getBoolean("bridging_jamkesda");
                    akses2.masuk_pindah_pulang_inap = rs2.getBoolean("masuk_pindah_pulang_inap");
                    akses2.masuk_pindah_inap = rs2.getBoolean("masuk_pindah_inap");
                    akses2.jumlah_macam_diet = rs2.getBoolean("jumlah_macam_diet");
                    akses2.jumlah_porsi_diet = rs2.getBoolean("jumlah_porsi_diet");
                    akses2.status_gizi = rs2.getBoolean("status_gizi");
                    akses2.gizi_buruk = rs2.getBoolean("gizi_buruk");
                    akses2.master_faskes = rs2.getBoolean("master_faskes");
                    akses2.setstatusralan = rs2.getBoolean("set_status_registrasi_ralan");
                    akses2.telusurpasien = rs2.getBoolean("telusur_kunjungan_pasien");
                    akses2.sisrute_rujukan_keluar = rs2.getBoolean("sisrute_rujukan_keluar");
                    akses2.sisrute_rujukan_masuk = rs2.getBoolean("sisrute_rujukan_masuk");
                    akses2.sisrute_referensi_diagnosa = rs2.getBoolean("sisrute_referensi_diagnosa");
                    akses2.sisrute_referensi_alasanrujuk = rs2.getBoolean("sisrute_referensi_alasanrujuk");
                    akses2.sisrute_referensi_faskes = rs2.getBoolean("sisrute_referensi_faskes");
                    akses2.barang_cssd = rs2.getBoolean("barang_cssd");
                    akses2.status_pulang_inap = rs2.getBoolean("status_pulang_inap");
                    akses2.data_persalinan = rs2.getBoolean("data_persalinan");
                    akses2.data_ponek = rs2.getBoolean("data_ponek");
                    akses2.reg_boking_kasir = rs2.getBoolean("registrasi_booking_dikasir");
                    akses2.bahasa_pasien = rs2.getBoolean("bahasa_pasien");
                    akses2.suku_bangsa = rs2.getBoolean("suku_pasien");
                    akses2.harian_hais_inap = rs2.getBoolean("harian_hais_ranap");
                    akses2.harian_hais_jalan = rs2.getBoolean("harian_hais_ralan");
                    akses2.bulanan_hais_inap = rs2.getBoolean("bulanan_hais_ranap");
                    akses2.bulanan_hais_jalan = rs2.getBoolean("bulanan_hais_ralan");
                    akses2.ringkasan_pulang_ranap = rs2.getBoolean("ringkasan_pulang_ranap");
                    akses2.laporan_farmasi = rs2.getBoolean("laporan_farmasi");
                    akses2.master_masalah_keperawatan = rs2.getBoolean("master_masalah_keperawatan");
                    akses2.penilaian_awal_keperawatan_ralan = rs2.getBoolean("penilaian_awal_keperawatan_ralan");
                    akses2.master_triase_skala1 = rs2.getBoolean("master_triase_skala1");
                    akses2.master_triase_skala2 = rs2.getBoolean("master_triase_skala2");
                    akses2.master_triase_skala3 = rs2.getBoolean("master_triase_skala3");
                    akses2.master_triase_skala4 = rs2.getBoolean("master_triase_skala4");
                    akses2.master_triase_skala5 = rs2.getBoolean("master_triase_skala5");
                    akses2.data_triase_igd = rs2.getBoolean("data_triase_igd");
                    akses2.master_triase_pemeriksaan = rs2.getBoolean("master_triase_pemeriksaan");
                    akses2.master_triase_macamkasus = rs2.getBoolean("master_triase_macamkasus");
                    akses2.master_cara_bayar = rs2.getBoolean("master_cara_bayar");
                    akses2.status_kerja_dokter = rs2.getBoolean("status_kerja_dokter");
                    akses2.pasien_corona = rs2.getBoolean("pasien_corona");
                    akses2.diagnosa_pasien_corona = rs2.getBoolean("diagnosa_pasien_corona");
                    akses2.perawatan_pasien_corona = rs2.getBoolean("perawatan_pasien_corona");
                    akses2.inacbg_klaim_baru_manual2 = rs2.getBoolean("inacbg_klaim_baru_manual2");
                    akses2.assesmen_gizi_harian = rs2.getBoolean("assesmen_gizi_harian");
                    akses2.assesmen_gizi_ulang = rs2.getBoolean("assesmen_gizi_ulang");
                    akses2.tombol_nota_billing = rs2.getBoolean("tombol_nota_billing");
                    akses2.tombol_simpan_hasil_rad = rs2.getBoolean("tombol_simpan_hasil_radiologi");
                    akses2.monev_asuhan_gizi = rs2.getBoolean("monev_asuhan_gizi");
                    akses2.inacbg_klaim_raza = rs2.getBoolean("inacbg_klaim_raza");
                    akses2.pengajuan_klaim_inacbg_raza = rs2.getBoolean("pengajuan_klaim_inacbg_raza");
                    akses2.copy_pemeriksaan_dokter_kepetugas_ralan = rs2.getBoolean("copy_pemeriksaan_dokter_kepetugas_ralan");
                    akses2.jkn_belum_diproses_klaim = rs2.getBoolean("jkn_belum_diproses_klaim");
                    akses2.input_kode_icd = rs2.getBoolean("input_kode_icd");
                    akses2.kendali_Mutu_kendali_Biaya_INACBG = rs2.getBoolean("kendali_Mutu_kendali_Biaya_INACBG");
                    akses2.dashboard_eResep = rs2.getBoolean("dashboard_eResep");
                    akses2.bpjs_sep_internal = rs2.getBoolean("bpjs_sep_internal");
                    akses2.kemenkes_sitt = rs2.getBoolean("kemenkes_sitt");
                    akses2.rencana_kontrol_jkn = rs2.getBoolean("rencana_kontrol_jkn");
                    akses2.spri_jkn = rs2.getBoolean("spri_jkn");
                    akses2.hapus_sep = rs2.getBoolean("hapus_sep");
                    akses2.penilaian_awal_medis_ralan_kebidanan = rs2.getBoolean("penilaian_awal_medis_ralan_kebidanan");
                    akses2.penilaian_awal_keperawatan_kebidanan = rs2.getBoolean("penilaian_awal_keperawatan_kebidanan");
                    akses2.ikhtisar_perawatan_hiv = rs2.getBoolean("ikhtisar_perawatan_hiv");
                    akses2.survey_kepuasan = rs2.getBoolean("survey_kepuasan");
                    akses2.kemenkes_kanker = rs2.getBoolean("kemenkes_kanker");
                    akses2.aktivasi_bridging = rs2.getBoolean("aktivasi_bridging");
                    akses2.operator_antrian = rs2.getBoolean("operator_antrian");
                    akses2.penilaian_awal_medis_ralan_tht = rs2.getBoolean("penilaian_awal_medis_ralan_tht");
                    akses2.rekam_psikologis = rs2.getBoolean("rekam_psikologis");
                    akses2.penilaian_pasien_geriatri = rs2.getBoolean("penilaian_pasien_geriatri");
                    akses2.penilaian_awal_medis_ralan_mata = rs2.getBoolean("penilaian_awal_medis_ralan_mata");
                    akses2.surat_sakit = rs2.getBoolean("surat_sakit");
                    akses2.surat_keterangan_kir_mcu = rs2.getBoolean("surat_keterangan_kir_mcu");
                    akses2.asesmen_medik_dewasa_ranap = rs2.getBoolean("asesmen_medik_dewasa_ranap");
                    akses2.pemberian_obat = rs2.getBoolean("pemberian_obat");
                    akses2.cppt = rs2.getBoolean("cppt");
                    akses2.bridging_satu_sehat = rs2.getBoolean("bridging_satu_sehat");
                    akses2.kemoterapi = rs2.getBoolean("kemoterapi");
                    akses2.cek_piutang = rs2.getBoolean("cek_piutang");
                } else if ((rs.getRow() == 0) && (rs2.getRow() == 0)) {
                    akses2.kode = "";
                    akses2.penyakit = false;
                    akses2.obat_penyakit = false;
                    akses2.dokter = false;
                    akses2.jadwal_praktek = false;
                    akses2.petugas = false;
                    akses2.pasien = false;
                    akses2.registrasi = false;
                    akses2.tindakan_ralan = false;
                    akses2.kamar_inap = false;
                    akses2.tindakan_ranap = false;
                    akses2.operasi = false;
                    akses2.rujukan_keluar = false;
                    akses2.rujukan_masuk = false;
                    akses2.beri_obat = false;
                    akses2.resep_pulang = false;
                    akses2.pasien_meninggal = false;
                    akses2.diet_pasien = false;
                    akses2.kelahiran_bayi = false;
                    akses2.periksa_lab = false;
                    akses2.periksa_radiologi = false;
                    akses2.kasir_ralan = false;
                    akses2.deposit_pasien = false;
                    akses2.piutang_pasien = false;
                    akses2.peminjaman_berkas = false;
                    akses2.barcode = false;
                    akses2.presensi_harian = false;
                    akses2.presensi_bulanan = false;
                    akses2.pegawai_admin = false;
                    akses2.pegawai_user = false;
                    akses2.suplier = false;
                    akses2.satuan_barang = false;
                    akses2.konversi_satuan = false;
                    akses2.jenis_barang = false;
                    akses2.obat = false;
                    akses2.stok_opname_obat = false;
                    akses2.stok_obat_pasien = false;
                    akses2.pengadaan_obat = false;
                    akses2.pemesanan_obat = false;
                    akses2.penjualan_obat = false;
                    akses2.piutang_obat = false;
                    akses2.retur_ke_suplier = false;
                    akses2.retur_dari_pembeli = false;
                    akses2.retur_obat_ranap = false;
                    akses2.retur_piutang_pasien = false;
                    akses2.keuntungan_penjualan = false;
                    akses2.keuntungan_beri_obat = false;
                    akses2.sirkulasi_obat = false;
                    akses2.ipsrs_barang = false;
                    akses2.ipsrs_pengadaan_barang = false;
                    akses2.ipsrs_stok_keluar = false;
                    akses2.ipsrs_rekap_pengadaan = false;
                    akses2.ipsrs_rekap_stok_keluar = false;
                    akses2.ipsrs_pengeluaran_harian = false;
                    akses2.ipsrs_jenis_barang = false;
                    akses2.inventaris_jenis = false;
                    akses2.inventaris_kategori = false;
                    akses2.inventaris_merk = false;
                    akses2.inventaris_ruang = false;
                    akses2.inventaris_produsen = false;
                    akses2.inventaris_koleksi = false;
                    akses2.inventaris_inventaris = false;
                    akses2.inventaris_sirkulasi = false;
                    akses2.parkir_jenis = false;
                    akses2.parkir_in = false;
                    akses2.parkir_out = false;
                    akses2.parkir_rekap_harian = false;
                    akses2.parkir_rekap_bulanan = false;
                    akses2.informasi_kamar = false;
                    akses2.harian_tindakan_poli = false;
                    akses2.obat_per_poli = false;
                    akses2.obat_per_kamar = false;
                    akses2.obat_per_dokter_ralan = false;
                    akses2.obat_per_dokter_ranap = false;
                    akses2.harian_dokter = false;
                    akses2.bulanan_dokter = false;
                    akses2.harian_paramedis = false;
                    akses2.bulanan_paramedis = false;
                    akses2.pembayaran_ralan = false;
                    akses2.pembayaran_ranap = false;
                    akses2.rekap_pembayaran_ralan = false;
                    akses2.rekap_pembayaran_ranap = false;
                    akses2.tagihan_masuk = false;
                    akses2.tambahan_biaya = false;
                    akses2.potongan_biaya = false;
                    akses2.resep_obat = false;
                    akses2.resume_pasien = false;
                    akses2.penyakit_ralan = false;
                    akses2.penyakit_ranap = false;
                    akses2.kamar = false;
                    akses2.tarif_ralan = false;
                    akses2.tarif_ranap = false;
                    akses2.tarif_lab = false;
                    akses2.tarif_radiologi = false;
                    akses2.tarif_operasi = false;
                    akses2.akun_rekening = false;
                    akses2.rekening_tahun = false;
                    akses2.posting_jurnal = false;
                    akses2.buku_besar = false;
                    akses2.cashflow = false;
                    akses2.keuangan = false;
                    akses2.pengeluaran = false;
                    akses2.setup_pjlab = false;
                    akses2.setup_otolokasi = false;
                    akses2.setup_jam_kamin = false;
                    akses2.setup_embalase = false;
                    akses2.tracer_login = false;
                    akses2.display = false;
                    akses2.set_harga_obat = false;
                    akses2.set_penggunaan_tarif = false;
                    akses2.set_oto_ralan = false;
                    akses2.biaya_harian = false;
                    akses2.biaya_masuk_sekali = false;
                    akses2.set_no_rm = false;
                    akses2.billing_ralan = false;
                    akses2.billing_ranap = false;
                    akses2.jm_ranap_dokter = false;
                    akses2.igd = false;
                    akses2.barcoderalan = false;
                    akses2.barcoderanap = false;
                    akses2.set_harga_obat_ralan = false;
                    akses2.set_harga_obat_ranap = false;
                    akses2.admin = false;
                    akses2.user = false;
                    akses2.vakum = false;
                    akses2.aplikasi = false;
                    akses2.penyakit_pd3i = false;
                    akses2.surveilans_pd3i = false;
                    akses2.surveilans_ralan = false;
                    akses2.diagnosa_pasien = false;
                    akses2.surveilans_ranap = false;
                    akses2.pny_takmenular_ranap = false;
                    akses2.pny_takmenular_ralan = false;
                    akses2.kunjungan_ralan = false;
                    akses2.rl32 = false;
                    akses2.rl33 = false;
                    akses2.rl37 = false;
                    akses2.rl38 = false;
                    akses2.harian_tindakan_dokter = false;
                    akses2.sms = false;
                    akses2.sidikjari = false;
                    akses2.jam_masuk = false;
                    akses2.jadwal_pegawai = false;
                    akses2.parkir_barcode = false;
                    akses2.set_nota = false;
                    akses2.dpjp_ranap = false;
                    akses2.mutasi_barang = false;
                    akses2.rl34 = false;
                    akses2.rl36 = false;
                    akses2.fee_visit_dokter = false;
                    akses2.fee_bacaan_ekg = false;
                    akses2.fee_rujukan_rontgen = false;
                    akses2.fee_rujukan_ranap = false;
                    akses2.fee_ralan = false;
                    akses2.akun_bayar = false;
                    akses2.bayar_pemesanan_obat = false;
                    akses2.obat_per_dokter_peresep = false;
                    akses2.pemasukan_lain = false;
                    akses2.pengaturan_rekening = false;
                    akses2.closing_kasir = false;
                    akses2.keterlambatan_presensi = false;
                    akses2.set_harga_kamar = false;
                    akses2.rekap_per_shift = false;
                    akses2.bpjs_cek_nik = false;
                    akses2.bpjs_cek_kartu = false;
                    akses2.obat_per_cara_bayar = false;
                    akses2.kunjungan_ranap = false;
                    akses2.bayar_piutang = false;
                    akses2.payment_point = false;
                    akses2.bpjs_cek_nomor_rujukan = false;
                    akses2.icd9 = false;
                    akses2.darurat_stok = false;
                    akses2.retensi_rm = false;
                    akses2.temporary_presensi = false;
                    akses2.jurnal_harian = false;
                    akses2.sirkulasi_obat2 = false;
                    akses2.edit_registrasi = false;
                    akses2.bpjs_referensi_diagnosa = false;
                    akses2.bpjs_referensi_poli = false;
                    akses2.industrifarmasi = false;
                    akses2.harian_js = false;
                    akses2.bulanan_js = false;
                    akses2.harian_paket_bhp = false;
                    akses2.bulanan_paket_bhp = false;
                    akses2.piutang_pasien2 = false;
                    akses2.bpjs_referensi_faskes = false;
                    akses2.bpjs_sep = false;
                    akses2.pengambilan_utd = false;
                    akses2.tarif_utd = false;
                    akses2.pengambilan_utd2 = false;
                    akses2.utd_medis_rusak = false;
                    akses2.pengambilan_penunjang_utd = false;
                    akses2.pengambilan_penunjang_utd2 = false;
                    akses2.utd_penunjang_rusak = false;
                    akses2.suplier_penunjang = false;
                    akses2.utd_donor = false;
                    akses2.bpjs_monitoring_klaim = false;
                    akses2.utd_cekal_darah = false;
                    akses2.utd_komponen_darah = false;
                    akses2.utd_stok_darah = false;
                    akses2.utd_pemisahan_darah = false;
                    akses2.harian_kamar = false;
                    akses2.rincian_piutang_pasien = false;
                    akses2.keuntungan_beri_obat_nonpiutang = false;
                    akses2.reklasifikasi_ralan = false;
                    akses2.reklasifikasi_ranap = false;
                    akses2.utd_penyerahan_darah = false;
                    akses2.hutang_obat = false;
                    akses2.riwayat_obat_alkes_bhp = false;
                    akses2.sensus_harian_poli = false;
                    akses2.rl4a = false;
                    akses2.aplicare_referensi_kamar = false;
                    akses2.aplicare_ketersediaan_kamar = false;
                    akses2.inacbg_klaim_baru_otomatis = false;
                    akses2.inacbg_klaim_baru_manual = false;
                    akses2.inacbg_coder_nik = false;
                    akses2.mutasi_berkas = false;
                    akses2.akun_piutang = false;
                    akses2.harian_kso = false;
                    akses2.bulanan_kso = false;
                    akses2.harian_menejemen = false;
                    akses2.bulanan_menejemen = false;
                    akses2.inhealth_cek_eligibilitas = false;
                    akses2.inhealth_referensi_jenpel_ruang_rawat = false;
                    akses2.inhealth_referensi_poli = false;
                    akses2.inhealth_referensi_faskes = false;
                    akses2.inhealth_sjp = false;
                    akses2.piutang_ralan = false;
                    akses2.piutang_ranap = false;
                    akses2.detail_piutang_penjab = false;
                    akses2.lama_pelayanan_ralan = false;
                    akses2.catatan_pasien = false;
                    akses2.rl4b = false;
                    akses2.rl4asebab = false;
                    akses2.rl4bsebab = false;
                    akses2.data_HAIs = false;
                    akses2.harian_HAIs = false;
                    akses2.bulanan_HAIs = false;
                    akses2.hitung_bor = false;
                    akses2.namauser = rs2.getString("nama");
                    akses2.perusahaan_pasien = false;
                    akses2.resep_dokter = false;
                    akses2.lama_pelayanan_apotek = false;
                    akses2.hitung_alos = false;
                    akses2.detail_tindakan = false;
                    akses2.rujukan_poli_internal = false;
                    akses2.rekap_poli_anak = false;
                    akses2.grafik_kunjungan_poli = false;
                    akses2.grafik_kunjungan_perdokter = false;
                    akses2.grafik_kunjungan_perpekerjaan = false;
                    akses2.grafik_kunjungan_perpendidikan = false;
                    akses2.grafik_kunjungan_pertahun = false;
                    akses2.berkas_digital_perawatan = false;
                    akses2.penyakit_menular_ranap = false;
                    akses2.penyakit_menular_ralan = false;
                    akses2.grafik_kunjungan_perbulan = false;
                    akses2.grafik_kunjungan_pertanggal = false;
                    akses2.grafik_kunjungan_demografi = false;
                    akses2.grafik_kunjungan_statusdaftartahun = false;
                    akses2.grafik_kunjungan_statusdaftartahun2 = false;
                    akses2.grafik_kunjungan_statusdaftarbulan = false;
                    akses2.grafik_kunjungan_statusdaftarbulan2 = false;
                    akses2.grafik_kunjungan_statusdaftartanggal = false;
                    akses2.grafik_kunjungan_statusdaftartanggal2 = false;
                    akses2.grafik_kunjungan_statusbataltahun = false;
                    akses2.grafik_kunjungan_statusbatalbulan = false;
                    akses2.pcare_cek_penyakit = false;
                    akses2.grafik_kunjungan_statusbataltanggal = false;
                    akses2.kategori_barang = false;
                    akses2.golongan_barang = false;
                    akses2.pemberian_obat_pertanggal = false;
                    akses2.penjualan_obat_pertanggal = false;
                    akses2.bpjs_rujukan_vclaim = false;
                    akses2.skdp_bpjs = false;
                    akses2.booking_registrasi = false;
                    akses2.bpjs_cek_riwayat_rujukan_pcare = false;
                    akses2.bpjs_cek_riwayat_rujukan_rs = false;
                    akses2.bpjs_cek_rujukan_kartu_rs = false;
                    akses2.bpjs_cek_tgl_rujukan = false;
                    akses2.bpjs_cek_no_rujukan_rs = false;
                    akses2.bpjs_cek_rujukan_kartu_pcare = false;
                    akses2.bpjs_cek_referensi_kelas_rawat = false;
                    akses2.bpjs_cek_referensi_prosedur = false;
                    akses2.bpjs_cek_referensi_dpjp = false;
                    akses2.bpjs_cek_referensi_dokter = false;
                    akses2.bpjs_cek_referensi_spesialistik = false;
                    akses2.bpjs_cek_referensi_ruang_rawat = false;
                    akses2.bpjs_cek_referensi_cara_keluar = false;
                    akses2.bpjs_cek_referensi_pasca_pulang = false;
                    akses2.bpjs_cek_referensi_propinsi = false;
                    akses2.bpjs_cek_referensi_kabupaten = false;
                    akses2.bpjs_cek_referensi_kecamatan = false;
                    akses2.permintaan_lab = false;
                    akses2.permintaan_radiologi = false;
                    akses2.selisih_tarif_bpjs = false;
                    akses2.edit_data_kematian = false;
                    akses2.bridging_jamkesda = false;
                    akses2.masuk_pindah_pulang_inap = false;
                    akses2.masuk_pindah_inap = false;
                    akses2.jumlah_macam_diet = false;
                    akses2.jumlah_porsi_diet = false;
                    akses2.status_gizi = false;
                    akses2.gizi_buruk = false;
                    akses2.master_faskes = false;
                    akses2.setstatusralan = false;
                    akses2.telusurpasien = false;
                    akses2.sisrute_rujukan_keluar = false;
                    akses2.sisrute_rujukan_masuk = false;
                    akses2.sisrute_referensi_diagnosa = false;
                    akses2.sisrute_referensi_alasanrujuk = false;
                    akses2.sisrute_referensi_faskes = false;
                    akses2.barang_cssd = false;
                    akses2.status_pulang_inap = false;
                    akses2.data_persalinan = false;
                    akses2.data_ponek = false;
                    akses2.reg_boking_kasir = false;
                    akses2.bahasa_pasien = false;
                    akses2.suku_bangsa = false;
                    akses2.harian_hais_inap = false;
                    akses2.harian_hais_jalan = false;
                    akses2.bulanan_hais_inap = false;
                    akses2.bulanan_hais_jalan = false;
                    akses2.ringkasan_pulang_ranap = false;
                    akses2.laporan_farmasi = false;
                    akses2.master_masalah_keperawatan = false;
                    akses2.penilaian_awal_keperawatan_ralan = false;
                    akses2.master_triase_skala1 = false;
                    akses2.master_triase_skala2 = false;
                    akses2.master_triase_skala3 = false;
                    akses2.master_triase_skala4 = false;
                    akses2.master_triase_skala5 = false;
                    akses2.data_triase_igd = false;
                    akses2.master_triase_pemeriksaan = false;
                    akses2.master_triase_macamkasus = false;
                    akses2.master_cara_bayar = false;
                    akses2.status_kerja_dokter = false;
                    akses2.pasien_corona = false;
                    akses2.diagnosa_pasien_corona = false;
                    akses2.perawatan_pasien_corona = false;
                    akses2.inacbg_klaim_baru_manual2 = false;
                    akses2.assesmen_gizi_harian = false;
                    akses2.assesmen_gizi_ulang = false;
                    akses2.tombol_nota_billing = false;
                    akses2.tombol_simpan_hasil_rad = false;
                    akses2.monev_asuhan_gizi = false;
                    akses2.inacbg_klaim_raza = false;
                    akses2.pengajuan_klaim_inacbg_raza = false;
                    akses2.copy_pemeriksaan_dokter_kepetugas_ralan = false;
                    akses2.jkn_belum_diproses_klaim = false;
                    akses2.input_kode_icd = false;
                    akses2.kendali_Mutu_kendali_Biaya_INACBG = false;
                    akses2.dashboard_eResep = false;
                    akses2.bpjs_sep_internal = false;
                    akses2.kemenkes_sitt = false;
                    akses2.rencana_kontrol_jkn = false;
                    akses2.spri_jkn = false;
                    akses2.hapus_sep = false;
                    akses2.penilaian_awal_medis_ralan_kebidanan = false;
                    akses2.penilaian_awal_keperawatan_kebidanan = false;
                    akses2.ikhtisar_perawatan_hiv = false;
                    akses2.survey_kepuasan = false;
                    akses2.kemenkes_kanker = false;
                    akses2.aktivasi_bridging = false;
                    akses2.operator_antrian = false;
                    akses2.penilaian_awal_medis_ralan_tht = false;
                    akses2.rekam_psikologis = false;
                    akses2.penilaian_pasien_geriatri = false;
                    akses2.penilaian_awal_medis_ralan_mata = false;
                    akses2.surat_sakit = false;
                    akses2.surat_keterangan_kir_mcu = false;
                    akses2.asesmen_medik_dewasa_ranap = false;
                    akses2.pemberian_obat = false;
                    akses2.cppt = false;
                    akses2.bridging_satu_sehat = false;
                    akses2.kemoterapi = false;
                    akses2.cek_piutang = false;
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (rs2 != null) {
                    rs2.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (ps2 != null) {
                    ps2.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public static void setNMLoket(String nmrawat) {
        if (nmrawat.equals("ralan")) {
            akses2.jenisLoket = "Rawat Jalan";
        } else if (nmrawat.equals("ranap")) {
            akses2.jenisLoket = "Rawat Inap";
        } else {
            akses2.jenisLoket = "";
        }
    }

    public static void setNomorLoket(String noloket) {
        if (noloket.equals("1")) {
            akses2.nomorLoket = "1";
        } else if (noloket.equals("2")) {
            akses2.nomorLoket = "2";
        } else if (noloket.equals("3")) {
            akses2.nomorLoket = "3";
        } else if (noloket.equals("4")) {
            akses2.nomorLoket = "4";
        } else if (noloket.equals("5")) {
            akses2.nomorLoket = "5";
        } else if (noloket.equals("6")) {
            akses2.nomorLoket = "6";
        } else if (noloket.equals("")) {
            akses2.nomorLoket = "";
        }
    }

    public static void setCopyData(String datanya) {
        akses2.CopyData = datanya;
    }
    
    public static void setCopyData1(String datanya1) {
        akses2.CopyData1 = datanya1;
    }
    
    public static void setCopyData2(String datanya2) {
        akses2.CopyData2 = datanya2;
    }
    
    public static void setCopyData3(String datanya3) {
        akses2.CopyData3 = datanya3;
    }
    
    public static void setCopyData4(String datanya4) {
        akses2.CopyData4 = datanya4;
    }
    
    public static void setCopyData5(String datanya5) {
        akses2.CopyData5 = datanya5;
    }

    public static int getjml1() {
        return akses2.jml1;
    }

    public static int getjml2() {
        return akses2.jml2;
    }

    public static boolean getadmin() {
        return akses2.admin;
    }

    public static boolean getuser() {
        return akses2.user;
    }

    public static boolean getvakum() {
        return akses2.vakum;
    }

    public static boolean getaplikasi() {
        return akses2.aplikasi;
    }

    public static boolean getpenyakit() {
        return akses2.penyakit;
    }

    public static boolean getobat_penyakit() {
        return akses2.obat_penyakit;
    }

    public static boolean getdokter() {
        return akses2.dokter;
    }

    public static boolean getjadwal_praktek() {
        return akses2.jadwal_praktek;
    }

    public static boolean getpetugas() {
        return akses2.petugas;
    }

    public static boolean getpasien() {
        return akses2.pasien;
    }

    public static boolean getregistrasi() {
        return akses2.registrasi;
    }

    public static boolean gettindakan_ralan() {
        return akses2.tindakan_ralan;
    }

    public static boolean getkamar_inap() {
        return akses2.kamar_inap;
    }

    public static boolean gettindakan_ranap() {
        return akses2.tindakan_ranap;
    }

    public static boolean getoperasi() {
        return akses2.operasi;
    }

    public static boolean getrujukan_keluar() {
        return akses2.rujukan_keluar;
    }

    public static boolean getrujukan_masuk() {
        return akses2.rujukan_masuk;
    }

    public static boolean getberi_obat() {
        return akses2.beri_obat;
    }

    public static boolean getresep_pulang() {
        return akses2.resep_pulang;
    }

    public static boolean getpasien_meninggal() {
        return akses2.pasien_meninggal;
    }

    public static boolean getdiet_pasien() {
        return akses2.diet_pasien;
    }

    public static boolean getkelahiran_bayi() {
        return akses2.kelahiran_bayi;
    }

    public static boolean getperiksa_lab() {
        return akses2.periksa_lab;
    }

    public static boolean getperiksa_radiologi() {
        return akses2.periksa_radiologi;
    }

    public static boolean getkasir_ralan() {
        return akses2.kasir_ralan;
    }

    public static boolean getdeposit_pasien() {
        return akses2.deposit_pasien;
    }

    public static boolean getpiutang_pasien() {
        return akses2.piutang_pasien;
    }

    public static boolean getpeminjaman_berkas() {
        return akses2.peminjaman_berkas;
    }

    public static boolean getbarcode() {
        return akses2.barcode;
    }

    public static boolean getpresensi_harian() {
        return akses2.presensi_harian;
    }

    public static boolean getpresensi_bulanan() {
        return akses2.presensi_bulanan;
    }

    public static boolean getpegawai_admin() {
        return akses2.pegawai_admin;
    }

    public static boolean getpegawai_user() {
        return akses2.pegawai_user;
    }

    public static boolean getsuplier() {
        return akses2.suplier;
    }

    public static boolean getsatuan_barang() {
        return akses2.satuan_barang;
    }

    public static boolean getkonversi_satuan() {
        return akses2.konversi_satuan;
    }

    public static boolean getjenis_barang() {
        return akses2.jenis_barang;
    }

    public static boolean getobat() {
        return akses2.obat;
    }

    public static boolean getstok_opname_obat() {
        return akses2.stok_opname_obat;
    }

    public static boolean getstok_obat_pasien() {
        return akses2.stok_obat_pasien;
    }

    public static boolean getpengadaan_obat() {
        return akses2.pengadaan_obat;
    }

    public static boolean getpemesanan_obat() {
        return akses2.pemesanan_obat;
    }

    public static boolean getpenjualan_obat() {
        return akses2.penjualan_obat;
    }

    public static void setpenjualan_obatfalse() {
        akses2.penjualan_obat = false;
    }

    public static boolean getpiutang_obat() {
        return akses2.piutang_obat;
    }

    public static boolean getretur_ke_suplier() {
        return akses2.retur_ke_suplier;
    }

    public static boolean getretur_dari_pembeli() {
        return akses2.retur_dari_pembeli;
    }

    public static boolean getretur_obat_ranap() {
        return akses2.retur_obat_ranap;
    }

    public static boolean getretur_piutang_pasien() {
        return akses2.retur_piutang_pasien;
    }

    public static boolean getkeuntungan_penjualan() {
        return akses2.keuntungan_penjualan;
    }

    public static boolean getkeuntungan_beri_obat() {
        return akses2.keuntungan_beri_obat;
    }

    public static boolean getsirkulasi_obat() {
        return akses2.sirkulasi_obat;
    }

    public static boolean getipsrs_barang() {
        return akses2.ipsrs_barang;
    }

    public static boolean getipsrs_pengadaan_barang() {
        return akses2.ipsrs_pengadaan_barang;
    }

    public static boolean getipsrs_stok_keluar() {
        return akses2.ipsrs_stok_keluar;
    }

    public static boolean getipsrs_rekap_pengadaan() {
        return akses2.ipsrs_rekap_pengadaan;
    }

    public static boolean getipsrs_rekap_stok_keluar() {
        return akses2.ipsrs_rekap_stok_keluar;
    }

    public static boolean getipsrs_pengeluaran_harian() {
        return akses2.ipsrs_pengeluaran_harian;
    }

    public static boolean getipsrs_jenis_barang() {
        return akses2.ipsrs_jenis_barang;
    }

    public static boolean getinventaris_jenis() {
        return akses2.inventaris_jenis;
    }

    public static boolean getinventaris_kategori() {
        return akses2.inventaris_kategori;
    }

    public static boolean getinventaris_merk() {
        return akses2.inventaris_merk;
    }

    public static boolean getinventaris_ruang() {
        return akses2.inventaris_ruang;
    }

    public static boolean getinventaris_produsen() {
        return akses2.inventaris_produsen;
    }

    public static boolean getinventaris_koleksi() {
        return akses2.inventaris_koleksi;
    }

    public static boolean getinventaris_inventaris() {
        return akses2.inventaris_inventaris;
    }

    public static boolean getinventaris_sirkulasi() {
        return akses2.inventaris_sirkulasi;
    }

    public static boolean getparkir_jenis() {
        return akses2.parkir_jenis;
    }

    public static boolean getparkir_in() {
        return akses2.parkir_in;
    }

    public static boolean getparkir_out() {
        return akses2.parkir_out;
    }

    public static boolean getparkir_rekap_harian() {
        return akses2.parkir_rekap_harian;
    }

    public static boolean getparkir_rekap_bulanan() {
        return akses2.parkir_rekap_bulanan;
    }

    public static boolean getinformasi_kamar() {
        return akses2.informasi_kamar;
    }

    public static boolean getharian_tindakan_poli() {
        return akses2.harian_tindakan_poli;
    }

    public static boolean getobat_per_poli() {
        return akses2.obat_per_poli;
    }

    public static boolean getobat_per_kamar() {
        return akses2.obat_per_kamar;
    }

    public static boolean getobat_per_dokter_ralan() {
        return akses2.obat_per_dokter_ralan;
    }

    public static boolean getobat_per_dokter_ranap() {
        return akses2.obat_per_dokter_ranap;
    }

    public static boolean getharian_dokter() {
        return akses2.harian_dokter;
    }

    public static boolean getbulanan_dokter() {
        return akses2.bulanan_dokter;
    }

    public static boolean getharian_paramedis() {
        return akses2.harian_paramedis;
    }

    public static boolean getbulanan_paramedis() {
        return akses2.bulanan_paramedis;
    }

    public static boolean getpembayaran_ralan() {
        return akses2.pembayaran_ralan;
    }

    public static boolean getpembayaran_ranap() {
        return akses2.pembayaran_ranap;
    }

    public static boolean getrekap_pembayaran_ralan() {
        return akses2.rekap_pembayaran_ralan;
    }

    public static boolean getrekap_pembayaran_ranap() {
        return akses2.rekap_pembayaran_ranap;
    }

    public static boolean gettagihan_masuk() {
        return akses2.tagihan_masuk;
    }

    public static boolean gettambahan_biaya() {
        return akses2.tambahan_biaya;
    }

    public static boolean getpotongan_biaya() {
        return akses2.potongan_biaya;
    }

    public static boolean getresep_obat() {
        return akses2.resep_obat;
    }

    public static boolean getresume_pasien() {
        return akses2.resume_pasien;
    }

    public static boolean getpenyakit_ralan() {
        return akses2.penyakit_ralan;
    }

    public static boolean getpenyakit_ranap() {
        return akses2.penyakit_ranap;
    }

    public static boolean getkamar() {
        return akses2.kamar;
    }

    public static boolean gettarif_ralan() {
        return akses2.tarif_ralan;
    }

    public static boolean gettarif_ranap() {
        return akses2.tarif_ranap;
    }

    public static boolean gettarif_lab() {
        return akses2.tarif_lab;
    }

    public static boolean gettarif_radiologi() {
        return akses2.tarif_radiologi;
    }

    public static boolean gettarif_operasi() {
        return akses2.tarif_operasi;
    }

    public static boolean getakun_rekening() {
        return akses2.akun_rekening;
    }

    public static boolean getrekening_tahun() {
        return akses2.rekening_tahun;
    }

    public static boolean getposting_jurnal() {
        return akses2.posting_jurnal;
    }

    public static boolean getbuku_besar() {
        return akses2.buku_besar;
    }

    public static boolean getcashflow() {
        return akses2.cashflow;
    }

    public static boolean getkeuangan() {
        return akses2.keuangan;
    }

    public static boolean getpengeluaran() {
        return akses2.pengeluaran;
    }

    public static boolean getsetup_pjlab() {
        return akses2.setup_pjlab;
    }

    public static boolean getsetup_otolokasi() {
        return akses2.setup_otolokasi;
    }

    public static boolean getsetup_jam_kamin() {
        return akses2.setup_jam_kamin;
    }

    public static boolean getsetup_embalase() {
        return akses2.setup_embalase;
    }

    public static boolean gettracer_login() {
        return akses2.tracer_login;
    }

    public static boolean getdisplay() {
        return akses2.display;
    }

    public static boolean getset_harga_obat() {
        return akses2.set_harga_obat;
    }

    public static boolean getset_penggunaan_tarif() {
        return akses2.set_penggunaan_tarif;
    }

    public static boolean getset_oto_ralan() {
        return akses2.set_oto_ralan;
    }

    public static boolean getbiaya_harian() {
        return akses2.biaya_harian;
    }

    public static boolean getbiaya_masuk_sekali() {
        return akses2.biaya_masuk_sekali;
    }

    public static boolean getset_no_rm() {
        return akses2.set_no_rm;
    }

    public static boolean getbilling_ralan() {
        return akses2.billing_ralan;
    }

    public static boolean getbilling_ranap() {
        return akses2.billing_ranap;
    }

    public static String getkode() {
        return akses2.kode;
    }

    public static String getJenisLoket() {
        return akses2.jenisLoket;
    }
    
    public static String getNotifLab() {
        return akses2.notifLab;
    }
    
    public static String getNotifRad() {
        return akses2.notifRad;
    }
    
    public static String getNotifApotek() {
        return akses2.notifApotek;
    }

    public static String getNomorLoket() {
        return akses2.nomorLoket;
    }
    
    public static String getPasteData() {
        return akses2.CopyData;
    }
    
    public static String getPasteData1() {
        return akses2.CopyData1;
    }
    
    public static String getPasteData2() {
        return akses2.CopyData2;
    }
    
    public static String getPasteData3() {
        return akses2.CopyData3;
    }
    
    public static String getPasteData4() {
        return akses2.CopyData4;
    }
    
    public static String getPasteData5() {
        return akses2.CopyData5;
    }
    
    public static void setNotifApotek(String cek) {
        akses2.notifApotek = cek;
    }
    
    public static void setNotifLab(String cek) {
        akses2.notifLab = cek;
    }
    
    public static void setNotifRad(String cek) {
        akses2.notifRad = cek;
    }

    public static void setkdbangsal(String kdbangsal) {
        akses2.kdbangsal = kdbangsal;
    }

    public static String getkdbangsal() {
        return akses2.kdbangsal;
    }

    public static void setform(String form) {
        akses2.form = form;
    }

    public static String getform() {
        return akses2.form;
    }

    public static void setnamauser(String namauser) {
        akses2.namauser = namauser;
    }

    public static String getnamauser() {
        return akses2.namauser;
    }

    public static void setstatus(boolean status) {
        akses2.status = status;
    }

    public static boolean getstatus() {
        return akses2.status;
    }

    public static boolean getjm_ranap_dokter() {
        return akses2.jm_ranap_dokter;
    }

    public static boolean getigd() {
        return akses2.igd;
    }

    public static boolean getbarcoderalan() {
        return akses2.barcoderalan;
    }

    public static boolean getbarcoderanap() {
        return akses2.barcoderanap;
    }

    public static boolean getset_harga_obat_ralan() {
        return akses2.set_harga_obat_ralan;
    }

    public static boolean getset_harga_obat_ranap() {
        return akses2.set_harga_obat_ranap;
    }

    public static boolean getpenyakit_pd3i() {
        return akses2.penyakit_pd3i;
    }

    public static boolean getsurveilans_pd3i() {
        return akses2.surveilans_pd3i;
    }

    public static boolean getsurveilans_ralan() {
        return akses2.surveilans_ralan;
    }

    public static boolean getdiagnosa_pasien() {
        return akses2.diagnosa_pasien;
    }

    public static boolean getsurveilans_ranap() {
        return akses2.surveilans_ranap;
    }

    public static boolean getpny_takmenular_ranap() {
        return akses2.pny_takmenular_ranap;
    }

    public static boolean getpny_takmenular_ralan() {
        return akses2.pny_takmenular_ralan;
    }

    public static void setnamars(String namars) {
        akses2.namars = namars;
    }

    public static void setalamatrs(String alamatrs) {
        akses2.alamatrs = alamatrs;
    }

    public static void setkabupatenrs(String kabupatenrs) {
        akses2.kabupatenrs = kabupatenrs;
    }

    public static void setpropinsirs(String propinsirs) {
        akses2.propinsirs = propinsirs;
    }

    public static void setkontakrs(String kontakrs) {
        akses2.kontakrs = kontakrs;
    }

    public static void setemailrs(String emailrs) {
        akses2.emailrs = emailrs;
    }

    public static String getnamars() {
        return akses2.namars;
    }

    public static String getalamatrs() {
        return akses2.alamatrs;
    }

    public static String getkabupatenrs() {
        return akses2.kabupatenrs;
    }

    public static String getpropinsirs() {
        return akses2.propinsirs;
    }

    public static String getkontakrs() {
        return akses2.kontakrs;
    }

    public static String getemailrs() {
        return akses2.emailrs;
    }

    public static boolean getkunjungan_ralan() {
        return akses2.kunjungan_ralan;
    }

    public static boolean getrl32() {
        return akses2.rl32;
    }

    public static boolean getrl33() {
        return akses2.rl33;
    }

    public static boolean getrl37() {
        return akses2.rl37;
    }

    public static boolean getrl38() {
        return akses2.rl38;
    }

    public static boolean getharian_tindakan_dokter() {
        return akses2.harian_tindakan_dokter;
    }

    public static boolean getsms() {
        return akses2.sms;
    }

    public static boolean getsidikjari() {
        return akses2.sidikjari;
    }

    public static boolean getjam_masuk() {
        return akses2.jam_masuk;
    }

    public static boolean getjadwal_pegawai() {
        return akses2.jadwal_pegawai;
    }

    public static boolean getparkir_barcode() {
        return akses2.parkir_barcode;
    }

    public static boolean getset_nota() {
        return akses2.set_nota;
    }

    public static boolean getdpjp_ranap() {
        return akses2.dpjp_ranap;
    }

    public static boolean getmutasi_barang() {
        return akses2.mutasi_barang;
    }

    public static boolean getrl34() {
        return akses2.rl34;
    }

    public static boolean getrl36() {
        return akses2.rl36;
    }

    public static boolean getfee_visit_dokter() {
        return akses2.fee_visit_dokter;
    }

    public static boolean getfee_bacaan_ekg() {
        return akses2.fee_bacaan_ekg;
    }

    public static boolean getfee_rujukan_rontgen() {
        return akses2.fee_rujukan_rontgen;
    }

    public static boolean getfee_rujukan_ranap() {
        return akses2.fee_rujukan_ranap;
    }

    public static boolean getfee_ralan() {
        return akses2.fee_ralan;
    }

    public static boolean getakun_bayar() {
        return akses2.akun_bayar;
    }

    public static boolean getbayar_pemesanan_obat() {
        return akses2.bayar_pemesanan_obat;
    }

    public static boolean getobat_per_dokter_peresep() {
        return akses2.obat_per_dokter_peresep;
    }

    public static boolean getpemasukan_lain() {
        return akses2.pemasukan_lain;
    }

    public static boolean getpengaturan_rekening() {
        return akses2.pengaturan_rekening;
    }

    public static boolean getclosing_kasir() {
        return akses2.closing_kasir;
    }

    public static boolean getketerlambatan_presensi() {
        return akses2.keterlambatan_presensi;
    }

    public static boolean getset_harga_kamar() {
        return akses2.set_harga_kamar;
    }

    public static boolean getrekap_per_shift() {
        return akses2.rekap_per_shift;
    }

    public static boolean getbpjs_cek_nik() {
        return akses2.bpjs_cek_nik;
    }

    public static boolean getbpjs_cek_kartu() {
        return akses2.bpjs_cek_kartu;
    }

    public static boolean getobat_per_cara_bayar() {
        return akses2.obat_per_cara_bayar;
    }

    public static boolean getkunjungan_ranap() {
        return akses2.kunjungan_ranap;
    }

    public static boolean getbayar_piutang() {
        return akses2.bayar_piutang;
    }

    public static boolean getpayment_point() {
        return akses2.payment_point;
    }

    public static boolean getbpjs_cek_nomor_rujukan() {
        return akses2.bpjs_cek_nomor_rujukan;
    }

    public static boolean geticd9() {
        return akses2.icd9;
    }

    public static boolean getdarurat_stok() {
        return akses2.darurat_stok;
    }

    public static boolean getretensi_rm() {
        return akses2.retensi_rm;
    }

    public static boolean gettemporary_presensi() {
        return akses2.temporary_presensi;
    }

    public static boolean getjurnal_harian() {
        return akses2.jurnal_harian;
    }

    public static boolean getsirkulasi_obat2() {
        return akses2.sirkulasi_obat2;
    }

    public static boolean getedit_registrasi() {
        return akses2.edit_registrasi;
    }

    public static boolean getbpjs_referensi_diagnosa() {
        return akses2.bpjs_referensi_diagnosa;
    }

    public static boolean getbpjs_referensi_poli() {
        return akses2.bpjs_referensi_poli;
    }

    public static boolean getindustrifarmasi() {
        return akses2.industrifarmasi;
    }

    public static boolean getharian_js() {
        return akses2.harian_js;
    }

    public static boolean getbulanan_js() {
        return akses2.bulanan_js;
    }

    public static boolean getharian_paket_bhp() {
        return akses2.harian_paket_bhp;
    }

    public static boolean getbulanan_paket_bhp() {
        return akses2.bulanan_paket_bhp;
    }

    public static boolean getpiutang_pasien2() {
        return akses2.piutang_pasien2;
    }

    public static boolean getbpjs_referensi_faskes() {
        return akses2.bpjs_referensi_faskes;
    }

    public static boolean getbpjs_sep() {
        return akses2.bpjs_sep;
    }

    public static boolean getpengambilan_utd() {
        return akses2.pengambilan_utd;
    }

    public static boolean gettarif_utd() {
        return akses2.tarif_utd;
    }

    public static boolean getpengambilan_utd2() {
        return akses2.pengambilan_utd2;
    }

    public static boolean getutd_medis_rusak() {
        return akses2.utd_medis_rusak;
    }

    public static boolean getpengambilan_penunjang_utd() {
        return akses2.pengambilan_penunjang_utd;
    }

    public static boolean getpengambilan_penunjang_utd2() {
        return akses2.pengambilan_penunjang_utd2;
    }

    public static boolean getutd_penunjang_rusak() {
        return akses2.utd_penunjang_rusak;
    }

    public static boolean getsuplier_penunjang() {
        return akses2.suplier_penunjang;
    }

    public static boolean getutd_donor() {
        return akses2.utd_donor;
    }

    public static boolean getbpjs_monitoring_klaim() {
        return akses2.bpjs_monitoring_klaim;
    }

    public static boolean getutd_cekal_darah() {
        return akses2.utd_cekal_darah;
    }

    public static boolean getutd_komponen_darah() {
        return akses2.utd_komponen_darah;
    }

    public static boolean getutd_stok_darah() {
        return akses2.utd_stok_darah;
    }

    public static boolean getutd_pemisahan_darah() {
        return akses2.utd_pemisahan_darah;
    }

    public static boolean getharian_kamar() {
        return akses2.harian_kamar;
    }

    public static boolean getrincian_piutang_pasien() {
        return akses2.rincian_piutang_pasien;
    }

    public static boolean getkeuntungan_beri_obat_nonpiutang() {
        return akses2.keuntungan_beri_obat_nonpiutang;
    }

    public static boolean getreklasifikasi_ralan() {
        return akses2.reklasifikasi_ralan;
    }

    public static boolean getreklasifikasi_ranap() {
        return akses2.reklasifikasi_ranap;
    }

    public static boolean getutd_penyerahan_darah() {
        return akses2.utd_penyerahan_darah;
    }

    public static void setutd_penyerahan_darahfalse() {
        akses2.utd_penyerahan_darah = false;
    }

    public static boolean gethutang_obat() {
        return akses2.hutang_obat;
    }

    public static boolean getriwayat_obat_alkes_bhp() {
        return akses2.riwayat_obat_alkes_bhp;
    }

    public static boolean getsensus_harian_poli() {
        return akses2.sensus_harian_poli;
    }

    public static boolean getrl4a() {
        return akses2.rl4a;
    }

    public static boolean getaplicare_referensi_kamar() {
        return akses2.aplicare_referensi_kamar;
    }

    public static boolean getaplicare_ketersediaan_kamar() {
        return akses2.aplicare_ketersediaan_kamar;
    }

    public static boolean getinacbg_klaim_baru_otomatis() {
        return akses2.inacbg_klaim_baru_otomatis;
    }

    public static boolean getinacbg_klaim_baru_manual() {
        return akses2.inacbg_klaim_baru_manual;
    }

    public static boolean getinacbg_klaim_baru_manual2() {
        return akses2.inacbg_klaim_baru_manual2;
    }

    public static boolean getinacbg_coder_nik() {
        return akses2.inacbg_coder_nik;
    }

    public static boolean getmutasi_berkas() {
        return akses2.mutasi_berkas;
    }

    public static boolean getakun_piutang() {
        return akses2.akun_piutang;
    }

    public static boolean getharian_kso() {
        return akses2.harian_kso;
    }

    public static boolean getbulanan_kso() {
        return akses2.bulanan_kso;
    }

    public static boolean getharian_menejemen() {
        return akses2.harian_menejemen;
    }

    public static boolean getbulanan_menejemen() {
        return akses2.bulanan_menejemen;
    }

    public static boolean getinhealth_cek_eligibilitas() {
        return akses2.inhealth_cek_eligibilitas;
    }

    public static boolean getinhealth_referensi_jenpel_ruang_rawat() {
        return akses2.inhealth_referensi_jenpel_ruang_rawat;
    }

    public static boolean getinhealth_referensi_poli() {
        return akses2.inhealth_referensi_poli;
    }

    public static boolean getinhealth_referensi_faskes() {
        return akses2.inhealth_referensi_faskes;
    }

    public static boolean getinhealth_sjp() {
        return akses2.inhealth_sjp;
    }

    public static boolean getpiutang_ralan() {
        return akses2.piutang_ralan;
    }

    public static boolean getpiutang_ranap() {
        return akses2.piutang_ranap;
    }

    public static boolean getdetail_piutang_penjab() {
        return akses2.detail_piutang_penjab;
    }

    public static boolean getlama_pelayanan_ralan() {
        return akses2.lama_pelayanan_ralan;
    }

    public static boolean getcatatan_pasien() {
        return akses2.catatan_pasien;
    }

    public static boolean getrl4b() {
        return akses2.rl4b;
    }

    public static boolean getrl4asebab() {
        return akses2.rl4asebab;
    }

    public static boolean getrl4bsebab() {
        return akses2.rl4bsebab;
    }

    public static boolean getdata_HAIs() {
        return akses2.data_HAIs;
    }

    public static boolean getharian_HAIs() {
        return akses2.harian_HAIs;
    }

    public static boolean getbulanan_HAIs() {
        return akses2.bulanan_HAIs;
    }

    public static boolean gethitung_bor() {
        return akses2.hitung_bor;
    }

    public static boolean getperusahaan_pasien() {
        return akses2.perusahaan_pasien;
    }

    public static boolean getresep_dokter() {
        return akses2.resep_dokter;
    }

    public static void setresep_dokterfalse() {
        akses2.resep_dokter = false;
    }

    public static boolean getlama_pelayanan_apotek() {
        return akses2.lama_pelayanan_apotek;
    }

    public static boolean gethitung_alos() {
        return akses2.hitung_alos;
    }

    public static boolean getdetail_tindakan() {
        return akses2.detail_tindakan;
    }

    public static boolean getrujukan_poli_internal() {
        return akses2.rujukan_poli_internal;
    }

    public static boolean getrekap_poli_anak() {
        return akses2.rekap_poli_anak;
    }

    public static boolean getgrafik_kunjungan_poli() {
        return akses2.grafik_kunjungan_poli;
    }

    public static boolean getgrafik_kunjungan_perdokter() {
        return akses2.grafik_kunjungan_perdokter;
    }

    public static boolean getgrafik_kunjungan_perpekerjaan() {
        return akses2.grafik_kunjungan_perpekerjaan;
    }

    public static boolean getgrafik_kunjungan_perpendidikan() {
        return akses2.grafik_kunjungan_perpendidikan;
    }

    public static boolean getgrafik_kunjungan_pertahun() {
        return akses2.grafik_kunjungan_pertahun;
    }

    public static boolean getberkas_digital_perawatan() {
        return akses2.berkas_digital_perawatan;
    }

    public static boolean getpenyakit_menular_ranap() {
        return akses2.penyakit_menular_ranap;
    }

    public static boolean getpenyakit_menular_ralan() {
        return akses2.penyakit_menular_ralan;
    }

    public static boolean getgrafik_kunjungan_perbulan() {
        return akses2.grafik_kunjungan_perbulan;
    }

    public static boolean getgrafik_kunjungan_pertanggal() {
        return akses2.grafik_kunjungan_pertanggal;
    }

    public static boolean getgrafik_kunjungan_demografi() {
        return akses2.grafik_kunjungan_demografi;
    }

    public static boolean getgrafik_kunjungan_statusdaftartahun() {
        return akses2.grafik_kunjungan_statusdaftartahun;
    }

    public static boolean getgrafik_kunjungan_statusdaftartahun2() {
        return akses2.grafik_kunjungan_statusdaftartahun2;
    }

    public static boolean getgrafik_kunjungan_statusdaftarbulan() {
        return akses2.grafik_kunjungan_statusdaftarbulan;
    }

    public static boolean getgrafik_kunjungan_statusdaftarbulan2() {
        return akses2.grafik_kunjungan_statusdaftarbulan2;
    }

    public static boolean getgrafik_kunjungan_statusdaftartanggal() {
        return akses2.grafik_kunjungan_statusdaftartanggal;
    }

    public static boolean getgrafik_kunjungan_statusdaftartanggal2() {
        return akses2.grafik_kunjungan_statusdaftartanggal2;
    }

    public static boolean getgrafik_kunjungan_statusbataltahun() {
        return akses2.grafik_kunjungan_statusbataltahun;
    }

    public static boolean getgrafik_kunjungan_statusbatalbulan() {
        return akses2.grafik_kunjungan_statusbatalbulan;
    }

    public static boolean getpcare_cek_penyakit() {
        return akses2.pcare_cek_penyakit;
    }

    public static boolean getgrafik_kunjungan_statusbataltanggal() {
        return akses2.grafik_kunjungan_statusbataltanggal;
    }

    public static boolean getkategori_barang() {
        return akses2.kategori_barang;
    }

    public static boolean getgolongan_barang() {
        return akses2.golongan_barang;
    }

    public static boolean getpemberian_obat_pertanggal() {
        return akses2.pemberian_obat_pertanggal;
    }

    public static boolean getpenjualan_obat_pertanggal() {
        return akses2.penjualan_obat_pertanggal;
    }

    public static boolean getbpjs_rujukan_keluar() {
        return akses2.bpjs_rujukan_vclaim;
    }

    public static boolean getskdp_bpjs() {
        return akses2.skdp_bpjs;
    }

    public static boolean getbooking_registrasi() {
        return akses2.booking_registrasi;
    }

    public static boolean getbpjs_cek_riwayat_rujukan_pcare() {
        return akses2.bpjs_cek_riwayat_rujukan_pcare;
    }

    public static boolean getbpjs_cek_riwayat_rujukan_rs() {
        return akses2.bpjs_cek_riwayat_rujukan_rs;
    }

    public static boolean getbpjs_cek_rujukan_kartu_rs() {
        return akses2.bpjs_cek_rujukan_kartu_rs;
    }

    public static boolean getbpjs_cek_tgl_rujukan() {
        return akses2.bpjs_cek_tgl_rujukan;
    }

    public static boolean getbpjs_cek_no_rujukan_rs() {
        return akses2.bpjs_cek_no_rujukan_rs;
    }

    public static boolean getbpjs_cek_rujukan_kartu_pcare() {
        return akses2.bpjs_cek_rujukan_kartu_pcare;
    }

    public static boolean getbpjs_cek_referensi_kelas_rawat() {
        return akses2.bpjs_cek_referensi_kelas_rawat;
    }

    public static boolean getbpjs_cek_referensi_prosedur() {
        return akses2.bpjs_cek_referensi_prosedur;
    }

    public static boolean getbpjs_cek_referensi_dpjp() {
        return akses2.bpjs_cek_referensi_dpjp;
    }

    public static boolean getbpjs_cek_referensi_dokter() {
        return akses2.bpjs_cek_referensi_dokter;
    }

    public static boolean getbpjs_cek_referensi_spesialistik() {
        return akses2.bpjs_cek_referensi_spesialistik;
    }

    public static boolean getbpjs_cek_referensi_ruang_rawat() {
        return akses2.bpjs_cek_referensi_ruang_rawat;
    }

    public static boolean getbpjs_cek_referensi_cara_keluar() {
        return akses2.bpjs_cek_referensi_cara_keluar;
    }

    public static boolean getbpjs_cek_referensi_pasca_pulang() {
        return akses2.bpjs_cek_referensi_pasca_pulang;
    }

    public static boolean getbpjs_cek_referensi_propinsi() {
        return akses2.bpjs_cek_referensi_propinsi;
    }

    public static boolean getbpjs_cek_referensi_kabupaten() {
        return akses2.bpjs_cek_referensi_kabupaten;
    }

    public static boolean getbpjs_cek_referensi_kecamatan() {
        return akses2.bpjs_cek_referensi_kecamatan;
    }

    public static boolean getpermintaan_lab() {
        return akses2.permintaan_lab;
    }

    public static boolean getpermintaan_radiologi() {
        return akses2.permintaan_radiologi;
    }

    public static boolean getselisih_tarif_bpjs() {
        return akses2.selisih_tarif_bpjs;
    }

    public static boolean getedit_data_kematian() {
        return akses2.edit_data_kematian;
    }

    public static boolean getbridging_jamkesda() {
        return akses2.bridging_jamkesda;
    }

    public static boolean getmasuk_pindah_pulang_inap() {
        return akses2.masuk_pindah_pulang_inap;
    }

    public static boolean getmasuk_pindah_inap() {
        return akses2.masuk_pindah_inap;
    }

    public static boolean getjumlah_macam_diet() {
        return akses2.jumlah_macam_diet;
    }

    public static boolean getjumlah_porsi_diet() {
        return akses2.jumlah_porsi_diet;
    }

    public static boolean getstatusgizi() {
        return akses2.status_gizi;
    }

    public static boolean getgizi_buruk() {
        return akses2.gizi_buruk;
    }

    public static boolean getmaster_faskes() {
        return akses2.master_faskes;
    }

    public static boolean getsetstatusralan() {
        return akses2.setstatusralan;
    }

    public static boolean gettelusurpasien() {
        return akses2.telusurpasien;
    }

    public static boolean getsisrute_rujukan_keluar() {
        return akses2.sisrute_rujukan_keluar;
    }

    public static void setAktif(boolean status) {
        akses2.aktif = status;
    }

    public static boolean getAktif() {
        return akses2.aktif;
    }

    public static boolean getsisrute_rujukan_masuk() {
        return akses2.sisrute_rujukan_masuk;
    }

    public static boolean getsisrute_referensi_diagnosa() {
        return akses2.sisrute_referensi_diagnosa;
    }

    public static boolean getsisrute_referensi_alasanrujuk() {
        return akses2.sisrute_referensi_alasanrujuk;
    }

    public static boolean getsisrute_referensi_faskes() {
        return akses2.sisrute_referensi_faskes;
    }

    public static boolean getbarang_cssd() {
        return akses2.barang_cssd;
    }

    public static boolean getstatus_pulang_inap() {
        return akses2.status_pulang_inap;
    }

    public static boolean getdata_persalinan() {
        return akses2.data_persalinan;
    }

    public static boolean getdata_ponek() {
        return akses2.data_ponek;
    }

    public static boolean getreg_boking_kasir() {
        return akses2.reg_boking_kasir;
    }

    public static boolean getbahasa_pasien() {
        return akses2.bahasa_pasien;
    }

    public static boolean getsuku_bangsa() {
        return akses2.suku_bangsa;
    }

    public static boolean getharianhaisinap() {
        return akses2.harian_hais_inap;
    }

    public static boolean getharianhaisjalan() {
        return akses2.harian_hais_jalan;
    }

    public static boolean getbulananhaisinap() {
        return akses2.bulanan_hais_inap;
    }

    public static boolean getbulananhaisjalan() {
        return akses2.bulanan_hais_jalan;
    }

    public static boolean getringkasanpulangranap() {
        return akses2.ringkasan_pulang_ranap;
    }

    public static boolean getlaporanfarmasi() {
        return akses2.laporan_farmasi;
    }

    public static boolean getmaster_masalah_keperawatan() {
        return akses2.master_masalah_keperawatan;
    }

    public static boolean getpenilaian_awal_keperawatan_ralan() {
        return akses2.penilaian_awal_keperawatan_ralan;
    }

    public static boolean getmaster_triase_skala1() {
        return akses2.master_triase_skala1;
    }

    public static boolean getmaster_triase_skala2() {
        return akses2.master_triase_skala2;
    }

    public static boolean getmaster_triase_skala3() {
        return akses2.master_triase_skala3;
    }

    public static boolean getmaster_triase_skala4() {
        return akses2.master_triase_skala4;
    }

    public static boolean getmaster_triase_skala5() {
        return akses2.master_triase_skala5;
    }

    public static boolean getdata_triase_igd() {
        return akses2.data_triase_igd;
    }

    public static boolean getmaster_triase_pemeriksaan() {
        return akses2.master_triase_pemeriksaan;
    }

    public static boolean getmaster_triase_macamkasus() {
        return akses2.master_triase_macamkasus;
    }

    public static boolean getmaster_cara_bayar() {
        return akses2.master_cara_bayar;
    }

    public static boolean getstatus_kerja_dokter() {
        return akses2.status_kerja_dokter;
    }

    public static boolean getpasien_corona() {
        return akses2.pasien_corona;
    }

    public static boolean getdiagnosa_pasien_corona() {
        return akses2.diagnosa_pasien_corona;
    }

    public static boolean getperawatan_pasien_corona() {
        return akses2.perawatan_pasien_corona;
    }

    public static boolean getassesmen_gizi_harian() {
        return akses2.assesmen_gizi_harian;
    }

    public static boolean getassesmen_gizi_ulang() {
        return akses2.assesmen_gizi_ulang;
    }

    public static boolean gettombolnota_billing() {
        return akses2.tombol_nota_billing;
    }

    public static boolean gettombolsimpan_hasil_rad() {
        return akses2.tombol_simpan_hasil_rad;
    }

    public static boolean getmonev_asuhan_gizi() {
        return akses2.monev_asuhan_gizi;
    }

    public static boolean getinacbg_klaim_raza() {
        return akses2.inacbg_klaim_raza;
    }

    public static boolean getpengajuan_klaim_raza() {
        return akses2.pengajuan_klaim_inacbg_raza;
    }

    public static boolean getcopy_pemeriksaan_dr_kepetugas() {
        return akses2.copy_pemeriksaan_dokter_kepetugas_ralan;
    }

    public static boolean getjkn_belum_diproses_klaim() {
        return akses2.jkn_belum_diproses_klaim;
    }

    public static boolean getinput_kode_icd() {
        return akses2.input_kode_icd;
    }

    public static boolean getkendali_mutu_kendali_biaya_inacbg() {
        return akses2.kendali_Mutu_kendali_Biaya_INACBG;
    }

    public static boolean getdashboard_eResep() {
        return akses2.dashboard_eResep;
    }

    public static boolean getbpjsSEPinternal() {
        return akses2.bpjs_sep_internal;
    }

    public static boolean getkemenkes_sitt() {
        return akses2.kemenkes_sitt;
    }

    public static boolean getRencanaKontrolJKN() {
        return akses2.rencana_kontrol_jkn;
    }

    public static boolean getSPRIJKN() {
        return akses2.spri_jkn;
    }

    public static boolean getHapusSEP() {
        return akses2.hapus_sep;
    }

    public static boolean getpenilaian_awal_medis_ralan_kebidanan() {
        return akses2.penilaian_awal_medis_ralan_kebidanan;
    }

    public static boolean getpenilaian_awal_keperawatan_kebidanan() {
        return akses2.penilaian_awal_keperawatan_kebidanan;
    }

    public static boolean getikhtisar_perawatan_hiv() {
        return akses2.ikhtisar_perawatan_hiv;
    }

    public static boolean getsurvey_kepuasan() {
        return akses2.survey_kepuasan;
    }

    public static boolean getkemenkes_kanker() {
        return akses2.kemenkes_kanker;
    }

    public static boolean getset_bridging() {
        return akses2.aktivasi_bridging;
    }

    public static boolean getOperator_antrian() {
        return akses2.operator_antrian;
    }

    public static boolean getpenilaian_awal_medis_ralan_tht() {
        return akses2.penilaian_awal_medis_ralan_tht;
    }

    public static boolean getrekam_psikologis() {
        return akses2.rekam_psikologis;
    }

    public static boolean getpenilaian_pasien_geriatri() {
        return akses2.penilaian_pasien_geriatri;
    }

    public static boolean getpenilaian_awal_medis_ralan_mata() {
        return akses2.penilaian_awal_medis_ralan_mata;
    }

    public static boolean getsurat_sakit() {
        return akses2.surat_sakit;
    }

    public static boolean getsurat_keterangan_kir_mcu() {
        return akses2.surat_keterangan_kir_mcu;
    }

    public static boolean getasesmen_medik_dewasa_ranap() {
        return akses2.asesmen_medik_dewasa_ranap;
    }
    
    public static boolean getpemberian_obat() {
        return akses2.pemberian_obat;
    }
    
    public static boolean getcppt() {
        return akses2.cppt;
    }
    
    public static boolean getsatu_sehat() {
        return akses2.bridging_satu_sehat;
    }
    
    public static boolean getkemoterapi() {
        return akses2.kemoterapi;
    }
    
    public static boolean getcek_piutang() {
        return akses2.cek_piutang;
    }
}
