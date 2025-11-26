<?php
include('koneksidb.php');
$table_reg_periksa = "sik.reg_periksa";
$table_dokter = "sik.dokter";
$table_poliklinik = "sik.poliklinik";
$table_simrs_kode_panggil = "sik.simrs_kode_panggil";
$table_panggil_loket = "sik.simrs_panggil_loket";
$table_sudah_panggil_loket = "sik.simrs_sudah_panggil_loket";
$table_ambil_antrian_loket = "sik.simrs_ambil_antrian_loket";
$table_jadwal = "sik.jadwal";
$table_pasien = "sik.pasien";
$table_setting = "sik.setting";
$data = [];
$rows = [];

if (isset($_GET['get_setting'])) {
    $data = null;
    $query = "SELECT * FROM " . $table_setting;
    // echo $query;
    $exec = mysqli_query($connMysql, $query);
    while ($r = mysqli_fetch_assoc($exec)) {
        $data[] = $r;
    }
    $rows = [];
    if (!mysqli_error($connMysql)) {
        $rows['connection'] = $connInfo;
        $rows['message'] = "success";
        $rows['data'] = $data;
    } else {
        $rows['connection'] = $connInfo;
        $rows['message'] = "error";
        $rows['data'] = null;
    }
    echo json_encode($rows);
}
if (isset($_GET['get_panggil_loket'])) {
    $data = null;
    $query = "SELECT * FROM " . $table_panggil_loket . " order by id asc limit 0,1";
    // echo $query;
    $exec = mysqli_query($connMysql, $query);
    while ($r = mysqli_fetch_assoc($exec)) {
        $data[] = $r;
    }
    $rows = [];
    if (!mysqli_error($connMysql)) {
        $rows['connection'] = $connInfo;
        $rows['message'] = "success";
        $rows['data'] = $data;
    } else {
        $rows['connection'] = $connInfo;
        $rows['message'] = "error";
        $rows['data'] = null;
    }
    echo json_encode($rows);
}
if (isset($_GET['get_sudah_panggil_loket'])) {
    $data = null;
    $query = "SELECT * FROM " . $table_sudah_panggil_loket . " where loket='" . $_GET['loket'] . "' and kode='" . $_GET['kode'] . "'";
    // echo $query;
    $exec = mysqli_query($connMysql, $query);
    while ($r = mysqli_fetch_assoc($exec)) {
        $data[] = $r;
    }
    $rows = [];
    if (!mysqli_error($connMysql)) {
        $rows['connection'] = $connInfo;
        $rows['message'] = "success";
        $rows['data'] = $data;
    } else {
        $rows['connection'] = $connInfo;
        $rows['message'] = "error";
        $rows['data'] = null;
    }
    echo json_encode($rows);
}

if (isset($_GET['get_sisa_kuota'])) {
    $querys = "SELECT " . $table_reg_periksa . ".kd_dokter, " . $table_simrs_kode_panggil . ".kode  FROM " . $table_reg_periksa . " left join " . $table_simrs_kode_panggil . " on " . $table_reg_periksa . ".kd_dokter=" . $table_simrs_kode_panggil . ".kd_dokter where " . $table_reg_periksa . ".kd_dokter='" . $_GET['kd_dokter'] . "' and " . $table_reg_periksa . ".tgl_registrasi='" . date("Y-m-d") . "'";
    // echo $querys;
    $execs = mysqli_query($connMysql, $querys);
    $jum = mysqli_num_rows($execs);
    // $rs = mysqli_fetch_assoc($execs);
    // if ($jum > 0) {
    //     $quer =  "SELECT * FROM " . $table_ambil_antrian_loket . " where kd_dokter='".$_GET['kd_dokter']."' and tgl='". date("Y-m-d")."'";
    //     // echo $quer;
    //     $reg = mysqli_query($connMysql,$quer);
    //     // print_r($reg);
    //     if (mysqli_num_rows($reg) > 0) {
    //         // echo "A";
    //         $ints = mysqli_query($connMysql, "UPDATE " . $table_ambil_antrian_loket . " set jumlah = '" . $jum . "' WHERE kd_dokter='" . $_GET['kd_dokter'] . "'");
    //     } else {
    //         // echo "B";
    //         $ints = mysqli_query($connMysql, "INSERT INTO " . $table_ambil_antrian_loket . " (kd_poli, kd_dokter, kode, jumlah, tgl) VALUES ('" . $_GET['kd_poli'] . "','" . $_GET['kd_dokter'] . "','" . $rs['kode'] . "','" . $jum . "','" . date("Y-m-d") . "')");
    //     }
    //     // if ($ints) {
    //     //     echo mysqli_error($connMysql);
    //     // }
    // }
    // die;
    $query = "SELECT " . $table_jadwal . ".*, " . $jum . " as jum, " . $table_ambil_antrian_loket . ".jumlah, (sum(" . $table_jadwal . ".kuota) - " . $jum . ") as sisa  FROM " . $table_jadwal . " JOIN " . $table_ambil_antrian_loket . " ON  " . $table_ambil_antrian_loket . ".kd_dokter=" . $table_jadwal . ".kd_dokter where " . $table_jadwal . ".hari_kerja='" . $_GET['hari_kerja'] . "' AND " . $table_jadwal . ".kd_dokter='" . $_GET['kd_dokter'] . "' and " . $table_ambil_antrian_loket . ".tgl='" . date("Y-m-d") . "' group by " . $table_jadwal . ".kd_dokter";
    // echo $query;
    $exec = mysqli_query($connMysql, $query);
    while ($r = mysqli_fetch_assoc($exec)) {
        $data[] = $r;
    }

    $rows = [];
    if (!mysqli_error($connMysql)) {
        $rows['connection'] = $connInfo;
        $rows['message'] = "success";
        $rows['data'] = $data;
    } else {
        $rows['connection'] = $connInfo;
        $rows['message'] = "error";
        $rows['data'] = null;
    }
    echo json_encode($rows);
}


if (isset($_GET['get_jadwal'])) {
    $query = "SELECT " . $table_jadwal . ".*,  sum(" . $table_jadwal . ".kuota) as kuota, " . $table_dokter . ".nm_dokter, " . $table_poliklinik . ".nm_poli, " . $table_simrs_kode_panggil . ".kode FROM " . $table_jadwal . " JOIN " . $table_dokter . " ON " . $table_jadwal . ".kd_dokter=" . $table_dokter . ".kd_dokter JOIN " . $table_poliklinik . " ON " . $table_jadwal . ".kd_poli=" . $table_poliklinik . ".kd_poli LEFT JOIN " . $table_simrs_kode_panggil . " ON " . $table_jadwal . ".kd_poli=" . $table_simrs_kode_panggil . ".kd_poli where " . $table_jadwal . ".hari_kerja='" . $_GET['hari_kerja'] . "' group by " . $table_jadwal . ".kd_dokter";
    // echo $query;
    $exec = mysqli_query($connMysql, $query);
    while ($r = mysqli_fetch_assoc($exec)) {
        $data[] = $r;
    }
    $rows = [];
    if (!mysqli_error($connMysql)) {
        $rows['connection'] = $connInfo;
        $rows['message'] = "success";
        $rows['data'] = $data;
    } else {
        $rows['connection'] = $connInfo;
        $rows['message'] = "error";
        $rows['data'] = null;
    }
    echo json_encode($rows);
}
if (isset($_GET['get_ambil_antrian_loket'])) {
    $query = "SELECT jumlah FROM " . $table_ambil_antrian_loket . " where tgl='" . date("Y-m-d") . "' and kd_poli='" . $_GET['kd_poli'] . "' and kd_dokter='" . $_GET['kd_dokter'] . "'";
    // echo $query;
    $exec = mysqli_query($connMysql, $query);
    while ($r = mysqli_fetch_assoc($exec)) {
        $data[] = $r;
    }
    $rows = [];
    if (!mysqli_error($connMysql)) {
        $rows['connection'] = $connInfo;
        $rows['message'] = "success";
        $rows['data'] = $data;
    } else {
        $rows['connection'] = $connInfo;
        $rows['message'] = "error";
        $rows['data'] = null;
    }
    echo json_encode($rows);
}

if (isset($_GET['post_ambil_antrian_loket'])) {
    $input = json_decode(file_get_contents('php://input'), true);
    $qu = mysqli_query($connMysql, "SELECT * FROM " . $table_ambil_antrian_loket . " WHERE kd_dokter='" . $input['kd_dokter'] . "' and tgl='" . date("Y-m-d") . "'");
    if (mysqli_num_rows($qu) > 0) {
        $input['jum'] = mysqli_num_rows($qu);
        $r = mysqli_fetch_assoc($qu);
        $jum = $input['jumlah'];
        $q = mysqli_query($connMysql, "UPDATE " . $table_ambil_antrian_loket . " set jumlah = '" . $jum . "',kode='" . $input['kode'] . "' WHERE kd_dokter='" . $input['kd_dokter'] . "'");
    } else {
        $input['jum'] = 0;
        $jum = $input['jumlah'];
        $q = mysqli_query($connMysql, "INSERT INTO " . $table_ambil_antrian_loket . " (kd_poli, kd_dokter, kode, jumlah, tgl) VALUES ('" . $input['kd_poli'] . "','" . $input['kd_dokter'] . "','" . $input['kode'] . "','" . $jum . "','" . date("Y-m-d") . "')");
    }
    if (!mysqli_error($connMysql)) {
        $rows['connection'] = $connInfo;
        $rows['message'] = "success";
        $rows['data'] = $input;
    } else {
        $rows['connection'] = $connInfo;
        $rows['message'] = "error";
        $rows['data'] = null;
    }
    echo json_encode($rows);
}

if (isset($_GET['post_panggil_loket'])) {
    $input = json_decode(file_get_contents('php://input'), true);

    $str = "SELECT * FROM " . $table_sudah_panggil_loket . " where loket='" . $input['loket'] . "' and kode='" . $input['kode'] . "' and created_at like '" . date("Y-m-d") . "%'";
    // echo $str;
    $query = mysqli_query($connMysql, $str);
    if (mysqli_num_rows($query) > 0) {
        mysqli_query($connMysql, "UPDATE " . $table_sudah_panggil_loket . " SET nomor='" . $input['nomor'] . "' WHERE loket='" . $input['loket'] . "' AND kode='" . $input['kode'] . "'");
    } else {
        mysqli_query($connMysql, "INSERT INTO " . $table_sudah_panggil_loket . " (loket, kode, nomor) VALUES ('" . $input['loket'] . "','" . $input['kode'] . "','" . $input['nomor'] . "')");
    }


    $q = mysqli_query($connMysql, "INSERT INTO " . $table_panggil_loket . " (loket, kode, nomor) VALUES ('" . $input['loket'] . "','" . $input['kode'] . "','" . $input['nomor'] . "')");
    if (!mysqli_error($connMysql)) {
        $rows['connection'] = $connInfo;
        $rows['message'] = "success";
        $rows['data'] = $input;
    } else {
        $rows['connection'] = $connInfo;
        $rows['message'] = "error";
        $rows['data'] = null;
    }
    echo json_encode($rows);
}

if (isset($_GET['del_panggil_loket'])) {
    $query = "DELETE FROM " . $table_panggil_loket . " where id='" . $_GET['id'] . "'";
    $exec = mysqli_query($connMysql, $query);
    if (!mysqli_error($connMysql)) {
        $rows['connection'] = $connInfo;
        $rows['message'] = "success";
        $rows['data'] = $_GET['id'];
    } else {
        $rows['connection'] = $connInfo;
        $rows['message'] = "error";
        $rows['data'] = null;
    }
    echo json_encode($rows);
}
