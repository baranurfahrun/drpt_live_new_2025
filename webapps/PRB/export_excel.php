<?php
// export_excel.php - Export data to Excel
session_start();

error_reporting(E_ALL);
ini_set('display_errors', 1);

// Include database configuration
require_once '../conf/conf.php';
require_once 'prb.php';

// Authentication check
$sesilogin = isset($_SESSION['ses_admin_login']) ? $_SESSION['ses_admin_login'] : NULL;
if ($sesilogin != USERHYBRIDWEB . PASHYBRIDWEB) {
    die("Unauthorized access");
}

try {
    // Database connection
    $dsn = "mysql:host={$db_hostname};dbname={$db_name};charset=utf8mb4";
    $pdo = new PDO($dsn, $db_username, $db_password, [
        PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
        PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
    ]);

    // Initialize Prb class
    $prb = new Prb($pdo);

    // Get parameters
    $search = $_POST['search'] ?? '';
    $date_from = $_POST['date_from'] ?? '';
    $date_to = $_POST['date_to'] ?? '';
    $prb_filter = $_POST['prb_filter'] ?? '';
    $prb_document_filter = $_POST['prb_document_filter'] ?? '';
    $unique_patient_filter = $_POST['unique_patient_filter'] ?? '0';
    $poli_filter = $_POST['poli_filter'] ?? '';
    $limit = $_POST['limit'] ?? 0;

    // Get data
    $result = $prb->exportToExcel($search, $date_from, $date_to, $prb_filter, $prb_document_filter, $unique_patient_filter, $poli_filter, $limit);

    if (!$result['success']) {
        die("Error: " . $result['message']);
    }

    $data = $result['data'];

    // Set headers for Excel download
    $filename = "Data_PRB_Pasien_" . date('Y-m-d_H-i-s') . ".xls";
    header("Content-Type: application/vnd.ms-excel");
    header("Content-Disposition: attachment; filename=\"$filename\"");
    header("Pragma: no-cache");
    header("Expires: 0");

    // Build filter info for display
    $filterInfo = [];
    if (!empty($search)) $filterInfo[] = "Pencarian: $search";
    if (!empty($date_from)) $filterInfo[] = "Dari Tanggal: $date_from";
    if (!empty($date_to)) $filterInfo[] = "Sampai Tanggal: $date_to";
    if (!empty($prb_filter)) {
        $prbLabels = [
            'prb' => 'PRB',
            'tidak_prb' => 'Tidak PRB',
            'potensi_prb' => 'Potensi PRB',
            'prb_dan_potensi' => 'PRB + Potensi PRB'
        ];
        $filterInfo[] = "Status PRB: " . ($prbLabels[$prb_filter] ?? $prb_filter);
    }
    if (!empty($prb_document_filter)) {
        $docLabels = [
            'has_prb_number' => 'Hanya yang Punya No. Surat PRB',
            'no_prb_number' => 'Hanya yang Tidak Punya No. Surat PRB'
        ];
        $filterInfo[] = "Filter Dokumen: " . ($docLabels[$prb_document_filter] ?? $prb_document_filter);
    }
    if ($unique_patient_filter === '1') $filterInfo[] = "Mode: 1 Peserta = 1 Data Terakhir";
    if (!empty($poli_filter)) {
        if ($poli_filter === 'poli_prb') {
            $filterInfo[] = "Poliklinik: Poliklinik PRB (Jantung, Dalam, Paru, Syaraf)";
        } elseif ($poli_filter === 'non_poli_prb') {
            $filterInfo[] = "Poliklinik: Non-Poliklinik PRB";
        } else {
            $filterInfo[] = "Poliklinik: $poli_filter";
        }
    }
    $filterDisplay = !empty($filterInfo) ? implode(" | ", $filterInfo) : "Semua Data";
    ?>
    <html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <style>
            .header { background-color: #4472C4; color: white; font-weight: bold; text-align: center; }
            .subheader { background-color: #667eea; color: white; font-weight: bold; }
            .number { mso-number-format: "\@"; } /* Text format for numbers */
            .date { mso-number-format: "dd/mm/yyyy"; }
            table { border-collapse: collapse; width: 100%; }
            th, td { border: 1px solid #000000; padding: 5px; vertical-align: top; }
        </style>
    </head>
    <body>
        <table>
            <thead>
                <tr>
                    <th colspan="29" class="header" style="font-size: 16px; padding: 10px;">DATA PRB PASIEN BPJS - SISTEM TERINTEGRASI</th>
                </tr>
                <tr>
                    <th colspan="29" style="text-align: left; font-size: 12px; font-weight: normal; padding: 8px;">
                        <strong>Filter:</strong> <?php echo htmlspecialchars($filterDisplay); ?>
                    </th>
                </tr>
                <tr class="subheader">
                    <th>No</th>
                    <th>No Rawat</th>
                    <th>Tgl Kunjungan</th>
                    <th>No RM</th>
                    <th>No. Kartu</th>
                    <th>Kelas Rawat</th>
                    <th>Nama Peserta</th>
                    <th>Tanggal Lahir</th>
                    <th>Jenis Kelamin</th>
                    <th>No Telpon</th>
                    <th>Poliklinik</th>
                    <th>Dokter</th>
                    <th>No. SEP</th>
                    <th>Tgl. SEP</th>
                    <th>Tanggal Rujukan</th>
                    <th>No Rujukan</th>
                    <th>PPK Rujukan</th>
                    <th>Jenis Peserta</th>
                    <th>Kode Diagnosa Awal</th>
                    <th>Diagnosa Awal</th>
                    <th>PRB</th>
                    <th>Catatan SEP</th>
                    <th>Petugas Pembuat SEP</th>
                    <th>No. Surat PRB</th>
                    <th>Tanggal PRB</th>
                    <th>Dokter DPJP</th>
                    <th>Diagnosa PRB</th>
                    <th>Petugas Pembuat PRB</th>
                    <th>Obat Details</th>
                </tr>
            </thead>
            <tbody>
                <?php if (empty($data)): ?>
                    <tr><td colspan="28" style="text-align: center;">Tidak ada data ditemukan</td></tr>
                <?php else: ?>
                    <?php foreach ($data as $index => $row): 
                        $gender = $row['jkel'] === 'L' ? 'Laki-Laki' : ($row['jkel'] === 'P' ? 'Perempuan' : '-');
                        $prbStatus = $row['prb_status'] ?: 'Tidak';
                        // Format dates
                        $tglLahir = $row['tanggal_lahir'] && $row['tanggal_lahir'] != '0000-00-00' ? date('d/m/Y', strtotime($row['tanggal_lahir'])) : '-';
                        $tglSep = $row['tglsep'] && $row['tglsep'] != '0000-00-00' ? date('d/m/Y', strtotime($row['tglsep'])) : '-';
                        $tglKunjungan = $row['tgl_registrasi'] && $row['tgl_registrasi'] != '0000-00-00' ? date('d/m/Y', strtotime($row['tgl_registrasi'])) : '-';
                        $tglRujukan = $row['tglrujukan'] && $row['tglrujukan'] != '0000-00-00' ? date('d/m/Y', strtotime($row['tglrujukan'])) : '-';
                        $tglPrb = $row['tanggal_prb'] && $row['tanggal_prb'] != '0000-00-00' ? date('d/m/Y', strtotime($row['tanggal_prb'])) : '-';
                    ?>
                        <tr>
                            <td><?php echo $index + 1; ?></td>
                            <td class="number"><?php echo htmlspecialchars($row['no_rawat'] ?? '-'); ?></td>
                            <td class="date"><?php echo $tglKunjungan; ?></td>
                            <td class="number"><?php echo htmlspecialchars($row['norm'] ?? '-'); ?></td>
                            <td class="number"><?php echo htmlspecialchars($row['no_kartu'] ?? '-'); ?></td>
                            <td><?php echo htmlspecialchars($row['klsrawat'] ?? '-'); ?></td>
                            <td><?php echo htmlspecialchars($row['nama_pasien'] ?? '-'); ?></td>
                            <td class="date"><?php echo $tglLahir; ?></td>
                            <td><?php echo $gender; ?></td>
                            <td class="number"><?php echo htmlspecialchars($row['notelep'] ?? '-'); ?></td>
                            <td><?php echo htmlspecialchars($row['nm_poli'] ?? '-'); ?></td>
                            <td><?php echo htmlspecialchars($row['nm_dokter'] ?? '-'); ?></td>
                            <td class="number"><?php echo htmlspecialchars($row['no_sep'] ?? '-'); ?></td>
                            <td class="date"><?php echo $tglSep; ?></td>
                            <td class="date"><?php echo $tglRujukan; ?></td>
                            <td class="number"><?php echo htmlspecialchars($row['no_rujukan'] ?? '-'); ?></td>
                            <td><?php echo htmlspecialchars($row['nmppkrujukan'] ?? '-'); ?></td>
                            <td><?php echo htmlspecialchars($row['peserta'] ?? '-'); ?></td>
                            <td><?php echo htmlspecialchars($row['diagawal'] ?? '-'); ?></td>
                            <td><?php echo htmlspecialchars($row['nmdiagnosaawal'] ?? '-'); ?></td>
                            <td><?php echo htmlspecialchars($prbStatus); ?></td>
                            <td><?php echo htmlspecialchars($row['catatan'] ?? '-'); ?></td>
                            <td><?php echo htmlspecialchars($row['petugas_pembuat_sep'] ?? '-'); ?></td>
                            <td class="number" style="background-color: #ffe6e6;"><?php echo htmlspecialchars($row['no_surat_prb'] ?? '-'); ?></td>
                            <td class="date"><?php echo $tglPrb; ?></td>
                            <td><?php echo htmlspecialchars($row['dokter_dpjp'] ?? '-'); ?></td>
                            <td><?php echo htmlspecialchars($row['diagnosa_prb'] ?? '-'); ?></td>
                            <td><?php echo htmlspecialchars($row['petugas_pembuat_prb'] ?? '-'); ?></td>
                            <?php 
                                // Format obat details for better Excel readability
                                $obatDetails = $row['obat_details'] ?? '-';
                                if ($obatDetails && $obatDetails !== '-') {
                                    // Split by newline to get individual medications
                                    $obatList = explode("\n", $obatDetails);
                                    $obatList = array_filter($obatList); // Remove empty entries
                                    
                                    if (count($obatList) > 0) {
                                        $formattedObat = [];
                                        foreach ($obatList as $index => $obat) {
                                            $formattedObat[] = ($index + 1) . ". " . trim($obat);
                                        }
                                        $obatDetails = implode("\n", $formattedObat);
                                    }
                                }
                                echo '<td style="white-space: pre-wrap; vertical-align: top; font-size: 11px;">' . htmlspecialchars($obatDetails) . '</td>';
                            ?>
                        </tr>
                    <?php endforeach; ?>
                <?php endif; ?>
            </tbody>
        </table>
    </body>
    </html>
    <?php

} catch (Exception $e) {
    die("Server Error: " . $e->getMessage());
}
?>