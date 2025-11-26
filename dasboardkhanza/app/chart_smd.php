<!DOCTYPE html>
<html lang="en">
<!-- file header dashboard -->
<?php error_reporting(0); ?>
<?php
session_start();
if (!$_SESSION['nama']) {
  header('location: ../');
}
include('header.php'); ?>
<?php include('../conf/config.php'); ?>
<!-- /file header dashboard -->


<body class="hold-transition sidebar-mini layout-fixed">
  <div class="wrapper">

    <!-- Preloader -->
    <?php include('preloader.php'); ?>

    <!-- .navbar -->
    <?php include('navbar.php'); ?>
    <!-- /.navbar -->

    <!-- Main Sidebar Container -->
    <aside class="main-sidebar sidebar-dark-primary elevation-4">
      <!-- Brand Logo -->
      <?php include('logo.php'); ?>
      <!-- Sidebar -->
      <?php include('sidebar.php'); ?>
      <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
      <!-- Content Header (Page header) -->
      <?php include('content_header.php'); ?>
      <!-- /.content-header -->
      <section class="content">
        <div class="container-fluid">
          <h5 class="mb-2">Sumber Daya Manusia</h5>
          <div class="row">
            <div class="col-md-12 ">
              <figure class="highcharts-figure">
                <div id="komponenpegawai"></div>
              </figure>
            </div>
            <div class="col-md-6 ">
              <figure class="highcharts-figure">
                <div id="umur"></div>
              </figure>
            </div>
            <div class="col-md-6 ">
              <figure class="highcharts-figure">
                <div id="agama"></div>
              </figure>
            </div>
            <div class="col-md-6 ">
              <figure class="highcharts-figure">
                <div id="status"></div>
              </figure>
            </div>
            <div class="col-md-6 ">
              <figure class="highcharts-figure">
                <div id="departemen"></div>
              </figure>
            </div>
            <div class="col-md-12 ">
              <figure class="highcharts-figure">
                <div id="pendidikan"></div>
              </figure>
            </div>
            <div class="col-md-12 ">
              <figure class="highcharts-figure">
                <div id="bidang"></div>
              </figure>
            </div>
          </div>
          <!-- Main content -->
        </div>
        <!-- /.error-page -->
    </div>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <?php include('footer.php'); ?>

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
  </aside>
  <!-- /.control-sidebar -->
  </div>
  <!-- ./wrapper -->

  <!-- FILE QWERY -->
  <?php
  $Jumlah_kelompok_komponen_anggota_pria = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.jk,kelompok_jabatan.nama_kelompok,pegawai.kode_kelompok, COUNT(pegawai.kode_kelompok)
  FROM pegawai,kelompok_jabatan
  WHERE pegawai.jk='Pria' AND pegawai.kode_kelompok=kelompok_jabatan.kode_kelompok AND pegawai.kode_kelompok='AG'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jk_komponen_pria[] = $row[0];
    $nama_kelompok_komponen_anggota[] = $row[1];
    $Kode_kelompok_komponen_anggota_pria[] = $row[2];
    $Jumlah_kelompok_komponen_anggota_pria[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_kelompok_komponen_anggota_wanita = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.jk,kelompok_jabatan.nama_kelompok,pegawai.kode_kelompok, COUNT(pegawai.kode_kelompok)
  FROM pegawai,kelompok_jabatan
  WHERE pegawai.jk='Wanita' AND pegawai.kode_kelompok=kelompok_jabatan.kode_kelompok AND pegawai.kode_kelompok='AG'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jk_komponen_wanita[] = $row[0];
    $nama_kelompok_komponen_anggota[] = $row[1];
    $Kode_kelompok_komponen_anggota_wanita[] = $row[2];
    $Jumlah_kelompok_komponen_anggota_wanita[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_kelompok_komponen_bendahara_pria = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.jk,kelompok_jabatan.nama_kelompok,pegawai.kode_kelompok, COUNT(pegawai.kode_kelompok)
  FROM pegawai,kelompok_jabatan
  WHERE pegawai.jk='Pria' AND pegawai.kode_kelompok=kelompok_jabatan.kode_kelompok AND pegawai.kode_kelompok='BDH'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $nama_kelompok_komponen_bendahara[] = $row[1];
    $Kode_kelompok_komponen_bendahara_pria[] = $row[2];
    $Jumlah_kelompok_komponen_bendahara_pria[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_kelompok_komponen_bendahara_wanita = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.jk,kelompok_jabatan.nama_kelompok,pegawai.kode_kelompok, COUNT(pegawai.kode_kelompok)
  FROM pegawai,kelompok_jabatan
  WHERE pegawai.jk='Wanita' AND pegawai.kode_kelompok=kelompok_jabatan.kode_kelompok AND pegawai.kode_kelompok='BDH'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $nama_kelompok_komponen_bendahara[] = $row[1];
    $Kode_kelompok_komponen_bendahara_wanita[] = $row[2];
    $Jumlah_kelompok_komponen_bendahara_wanita[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_kelompok_komponen_kapol_pria = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.jk,kelompok_jabatan.nama_kelompok,pegawai.kode_kelompok, COUNT(pegawai.kode_kelompok)
  FROM pegawai,kelompok_jabatan
  WHERE pegawai.jk='Pria' AND pegawai.kode_kelompok=kelompok_jabatan.kode_kelompok AND pegawai.kode_kelompok='KAP'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $nama_kelompok_komponen_kapol[] = $row[1];
    $Kode_kelompok_komponen_kapol_pria[] = $row[2];
    $Jumlah_kelompok_komponen_kapol_pria[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_kelompok_komponen_kapol_wanita = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.jk,kelompok_jabatan.nama_kelompok,pegawai.kode_kelompok, COUNT(pegawai.kode_kelompok)
  FROM pegawai,kelompok_jabatan
  WHERE pegawai.jk='Wanita' AND pegawai.kode_kelompok=kelompok_jabatan.kode_kelompok AND pegawai.kode_kelompok='KAP'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $nama_kelompok_komponen_kapol[] = $row[1];
    $Kode_kelompok_komponen_kapol_wanita[] = $row[2];
    $Jumlah_kelompok_komponen_kapol_wanita[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_kelompok_komponen_karu_pria = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.jk,kelompok_jabatan.nama_kelompok,pegawai.kode_kelompok, COUNT(pegawai.kode_kelompok)
  FROM pegawai,kelompok_jabatan
  WHERE pegawai.jk='Pria' AND pegawai.kode_kelompok=kelompok_jabatan.kode_kelompok AND pegawai.kode_kelompok='KAR'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $nama_kelompok_komponen_karu[] = $row[1];
    $Kode_kelompok_komponen_karu_pria[] = $row[2];
    $Jumlah_kelompok_komponen_karu_pria[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_kelompok_komponen_karu_wanita = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.jk,kelompok_jabatan.nama_kelompok,pegawai.kode_kelompok, COUNT(pegawai.kode_kelompok)
  FROM pegawai,kelompok_jabatan
  WHERE pegawai.jk='Wanita' AND pegawai.kode_kelompok=kelompok_jabatan.kode_kelompok AND pegawai.kode_kelompok='KAR'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $nama_kelompok_komponen_karu[] = $row[1];
    $Kode_kelompok_komponen_karu_wanita[] = $row[2];
    $Jumlah_kelompok_komponen_karu_wanita[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_kelompok_komponen_kepala_pria = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.jk,kelompok_jabatan.nama_kelompok,pegawai.kode_kelompok, COUNT(pegawai.kode_kelompok)
  FROM pegawai,kelompok_jabatan
  WHERE pegawai.jk='Pria' AND pegawai.kode_kelompok=kelompok_jabatan.kode_kelompok AND pegawai.kode_kelompok='KPL'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $nama_kelompok_komponen_kepala[] = $row[1];
    $Kode_kelompok_komponen_kepala_pria[] = $row[2];
    $Jumlah_kelompok_komponen_kepala_pria[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_kelompok_komponen_kepala_wanita = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.jk,kelompok_jabatan.nama_kelompok,pegawai.kode_kelompok, COUNT(pegawai.kode_kelompok)
  FROM pegawai,kelompok_jabatan
  WHERE pegawai.jk='Wanita' AND pegawai.kode_kelompok=kelompok_jabatan.kode_kelompok AND pegawai.kode_kelompok='KPL'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $nama_kelompok_komponen_kepala[] = $row[1];
    $Kode_kelompok_komponen_kepala_wanita[] = $row[2];
    $Jumlah_kelompok_komponen_kepala_wanita[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_kelompok_komponen_operator_pria = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.jk,kelompok_jabatan.nama_kelompok,pegawai.kode_kelompok, COUNT(pegawai.kode_kelompok)
  FROM pegawai,kelompok_jabatan
  WHERE pegawai.jk='Pria' AND pegawai.kode_kelompok=kelompok_jabatan.kode_kelompok AND pegawai.kode_kelompok='OPR'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $nama_kelompok_komponen_operator[] = $row[1];
    $Kode_kelompok_komponen_operator_pria[] = $row[2];
    $Jumlah_kelompok_komponen_operator_pria[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_kelompok_komponen_operator_wanita = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.jk,kelompok_jabatan.nama_kelompok,pegawai.kode_kelompok, COUNT(pegawai.kode_kelompok)
  FROM pegawai,kelompok_jabatan
  WHERE pegawai.jk='Wanita' AND pegawai.kode_kelompok=kelompok_jabatan.kode_kelompok AND pegawai.kode_kelompok='OPR'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $nama_kelompok_komponen_operator[] = $row[1];
    $Kode_kelompok_komponen_operator_wanita[] = $row[2];
    $Jumlah_kelompok_komponen_operator_wanita[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_kelompok_komponen_Sekretaris_pria = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.jk,kelompok_jabatan.nama_kelompok,pegawai.kode_kelompok, COUNT(pegawai.kode_kelompok)
  FROM pegawai,kelompok_jabatan
  WHERE pegawai.jk='Pria' AND pegawai.kode_kelompok=kelompok_jabatan.kode_kelompok AND pegawai.kode_kelompok='SKR'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $nama_kelompok_komponen_Sekretaris[] = $row[1];
    $Kode_kelompok_komponen_Sekretaris_pria[] = $row[2];
    $Jumlah_kelompok_komponen_Sekretaris_pria[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_kelompok_komponen_Sekretaris_wanita = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.jk,kelompok_jabatan.nama_kelompok,pegawai.kode_kelompok, COUNT(pegawai.kode_kelompok)
  FROM pegawai,kelompok_jabatan
  WHERE pegawai.jk='Wanita' AND pegawai.kode_kelompok=kelompok_jabatan.kode_kelompok AND pegawai.kode_kelompok='SKR'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $nama_kelompok_komponen_Sekretaris[] = $row[1];
    $Kode_kelompok_komponen_Sekretaris_wanita[] = $row[2];
    $Jumlah_kelompok_komponen_Sekretaris_wanita[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_kelompok_komponen_urdal_pria = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.jk,kelompok_jabatan.nama_kelompok,pegawai.kode_kelompok, COUNT(pegawai.kode_kelompok)
  FROM pegawai,kelompok_jabatan
  WHERE pegawai.jk='Pria' AND pegawai.kode_kelompok=kelompok_jabatan.kode_kelompok AND pegawai.kode_kelompok='UDL'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $nama_kelompok_komponen_urdal[] = $row[1];
    $Kode_kelompok_komponen_urdal_pria[] = $row[2];
    $Jumlah_kelompok_komponen_urdal_pria[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_kelompok_komponen_urdal_wanita = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.jk,kelompok_jabatan.nama_kelompok,pegawai.kode_kelompok, COUNT(pegawai.kode_kelompok)
  FROM pegawai,kelompok_jabatan
  WHERE pegawai.jk='Wanita' AND pegawai.kode_kelompok=kelompok_jabatan.kode_kelompok AND pegawai.kode_kelompok='UDL'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $nama_kelompok_komponen_urdal[] = $row[1];
    $Kode_kelompok_komponen_urdal_wanita[] = $row[2];
    $Jumlah_kelompok_komponen_urdal_wanita[] = $row[3];
  }
  ?>

  <!-- umur-->
  <?php
  $Jumlah_umur_dibawah_25th_pria = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.jk,pegawai.tgl_lahir,COUNT(pegawai.nama) AS Jumlahdibawah25tahun,
  (YEAR(CURDATE())-YEAR(pegawai.tgl_lahir)) AS Umur FROM pegawai
  WHERE (YEAR(CURDATE())-year(pegawai.tgl_lahir)) <=25 AND pegawai.jk='Pria'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_umur_dibawah_25th_pria[] = $row[2];
  }
  ?>
  <?php
  $Jumlah_umur_dibawah_25th_wanita = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.jk,pegawai.tgl_lahir,COUNT(pegawai.nama) AS Jumlahdibawah25tahun,
  (YEAR(CURDATE())-YEAR(pegawai.tgl_lahir)) AS Umur FROM pegawai
  WHERE (YEAR(CURDATE())-year(pegawai.tgl_lahir)) <=25 AND pegawai.jk='wanita'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_umur_dibawah_25th_wanita[] = $row[2];
  }
  ?>

  <?php
  $Jumlah_umur_26_30_pria = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.jk,pegawai.tgl_lahir,COUNT(pegawai.nama) AS umur26_30,
  (YEAR(CURDATE())-YEAR(pegawai.tgl_lahir)) AS Umur FROM pegawai
  WHERE (YEAR(CURDATE())-year(pegawai.tgl_lahir)) BETWEEN  26 AND 30 AND pegawai.jk='Pria'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_umur_26_30_pria[] = $row[2];
  }
  ?>
  <?php
  $Jumlah_umur_26_30_wanita = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.jk,pegawai.tgl_lahir,COUNT(pegawai.nama) AS umur26_30,
  (YEAR(CURDATE())-YEAR(pegawai.tgl_lahir)) AS Umur FROM pegawai
  WHERE (YEAR(CURDATE())-year(pegawai.tgl_lahir)) BETWEEN  26 AND 30 AND pegawai.jk='Wanita'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_umur_26_30_wanita[] = $row[2];
  }
  ?>

  <?php
  $Jumlah_umur_31_35_pria = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.jk,pegawai.tgl_lahir,COUNT(pegawai.nama) AS umur31_35,
  (YEAR(CURDATE())-YEAR(pegawai.tgl_lahir)) AS Umur FROM pegawai
  WHERE (YEAR(CURDATE())-year(pegawai.tgl_lahir)) BETWEEN  31 AND 35 AND pegawai.jk='Pria'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_umur_31_35_pria[] = $row[2];
  }
  ?>
  <?php
  $Jumlah_umur_31_35_wanita = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.jk,pegawai.tgl_lahir,COUNT(pegawai.nama) AS umur31_35,
  (YEAR(CURDATE())-YEAR(pegawai.tgl_lahir)) AS Umur FROM pegawai
  WHERE (YEAR(CURDATE())-year(pegawai.tgl_lahir)) BETWEEN  31 AND 35 AND pegawai.jk='Wanita'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_umur_31_35_wanita[] = $row[2];
  }
  ?>

  <?php
  $Jumlah_umur_36_40_pria = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.jk,pegawai.tgl_lahir,COUNT(pegawai.nama) AS umur36_40,
  (YEAR(CURDATE())-YEAR(pegawai.tgl_lahir)) AS Umur FROM pegawai
  WHERE (YEAR(CURDATE())-year(pegawai.tgl_lahir)) BETWEEN  36 AND 40 AND pegawai.jk='Pria'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_umur_36_40_pria[] = $row[2];
  }
  ?>
  <?php
  $Jumlah_umur_36_40_wanita = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.jk,pegawai.tgl_lahir,COUNT(pegawai.nama) AS umur36_40,
  (YEAR(CURDATE())-YEAR(pegawai.tgl_lahir)) AS Umur FROM pegawai
  WHERE (YEAR(CURDATE())-year(pegawai.tgl_lahir)) BETWEEN  36 AND 40 AND pegawai.jk='Wanita'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_umur_36_40_wanita[] = $row[2];
  }
  ?>

  <?php
  $Jumlah_umur_41_45_pria = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.jk,pegawai.tgl_lahir,COUNT(pegawai.nama) AS umur41_45,
  (YEAR(CURDATE())-YEAR(pegawai.tgl_lahir)) AS Umur FROM pegawai
  WHERE (YEAR(CURDATE())-year(pegawai.tgl_lahir)) BETWEEN  41 AND 45 AND pegawai.jk='Pria'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_umur_41_45_pria[] = $row[2];
  }
  ?>
  <?php
  $Jumlah_umur_41_45_wanita = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.jk,pegawai.tgl_lahir,COUNT(pegawai.nama) AS umur41_45,
  (YEAR(CURDATE())-YEAR(pegawai.tgl_lahir)) AS Umur FROM pegawai
  WHERE (YEAR(CURDATE())-year(pegawai.tgl_lahir)) BETWEEN  41 AND 45 AND pegawai.jk='Wanita'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_umur_41_45_wanita[] = $row[2];
  }
  ?>

  <?php
  $Jumlah_umur_46_50_pria = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.jk,pegawai.tgl_lahir,COUNT(pegawai.nama) AS umur46_50,
  (YEAR(CURDATE())-YEAR(pegawai.tgl_lahir)) AS Umur FROM pegawai
  WHERE (YEAR(CURDATE())-year(pegawai.tgl_lahir)) BETWEEN  46 AND 50 AND pegawai.jk='Pria'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_umur_46_50_pria[] = $row[2];
  }
  ?>
  <?php
  $Jumlah_umur_46_50_wanita = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.jk,pegawai.tgl_lahir,COUNT(pegawai.nama) AS umur46_50,
  (YEAR(CURDATE())-YEAR(pegawai.tgl_lahir)) AS Umur FROM pegawai
  WHERE (YEAR(CURDATE())-year(pegawai.tgl_lahir)) BETWEEN  46 AND 50 AND pegawai.jk='Wanita'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_umur_46_50_wanita[] = $row[2];
  }
  ?>

  <?php
  $Jumlah_umur_51_55_pria = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.jk,pegawai.tgl_lahir,COUNT(pegawai.nama) AS umur51_55,
  (YEAR(CURDATE())-YEAR(pegawai.tgl_lahir)) AS Umur FROM pegawai
  WHERE (YEAR(CURDATE())-year(pegawai.tgl_lahir)) BETWEEN  51 AND 55 AND pegawai.jk='Pria'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_umur_51_55_pria[] = $row[2];
  }
  ?>
  <?php
  $Jumlah_umur_51_55_wanita = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.jk,pegawai.tgl_lahir,COUNT(pegawai.nama) AS umur51_55,
  (YEAR(CURDATE())-YEAR(pegawai.tgl_lahir)) AS Umur FROM pegawai
  WHERE (YEAR(CURDATE())-year(pegawai.tgl_lahir)) BETWEEN  51 AND 55 AND pegawai.jk='Wanita'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_umur_51_55_wanita[] = $row[2];
  }
  ?>

  <?php
  $Jumlah_umur_56_pria = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.jk,pegawai.tgl_lahir,COUNT(pegawai.nama) AS umur56,
  (YEAR(CURDATE())-YEAR(pegawai.tgl_lahir)) AS Umur FROM pegawai
  WHERE (YEAR(CURDATE())-year(pegawai.tgl_lahir)) =56 AND pegawai.jk='Pria'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_umur_56_pria[] = $row[2];
  }
  ?>
  <?php
  $Jumlah_umur_56_wanita = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.jk,pegawai.tgl_lahir,COUNT(pegawai.nama) AS umur56,
  (YEAR(CURDATE())-YEAR(pegawai.tgl_lahir)) AS Umur FROM pegawai
  WHERE (YEAR(CURDATE())-year(pegawai.tgl_lahir)) =56 AND pegawai.jk='Wanita'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_umur_56_wanita[] = $row[2];
  }
  ?>

  <?php
  $Jumlah_umur_57_60_pria = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.jk,pegawai.tgl_lahir,COUNT(pegawai.nama) AS umur56,
  (YEAR(CURDATE())-YEAR(pegawai.tgl_lahir)) AS Umur FROM pegawai
  WHERE (YEAR(CURDATE())-year(pegawai.tgl_lahir)) =57 AND 60 AND pegawai.jk='Pria'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_umur_57_60_pria[] = $row[2];
  }
  ?>
  <?php
  $Jumlah_umur_57_60_wanita = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.jk,pegawai.tgl_lahir,COUNT(pegawai.nama) AS umur56,
  (YEAR(CURDATE())-YEAR(pegawai.tgl_lahir)) AS Umur FROM pegawai
  WHERE (YEAR(CURDATE())-year(pegawai.tgl_lahir)) =57 AND 60 AND pegawai.jk='Wanita'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_umur_57_60_wanita[] = $row[2];
  }
  ?>

  <?php
  $Jumlah_umur_60_pria = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.jk,pegawai.tgl_lahir,COUNT(pegawai.nama) AS umur56,
  (YEAR(CURDATE())-YEAR(pegawai.tgl_lahir)) AS Umur FROM pegawai
  WHERE (YEAR(CURDATE())-year(pegawai.tgl_lahir)) =60 AND pegawai.jk='Pria'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_umur_60_pria[] = $row[2];
  }
  ?>
  <?php
  $Jumlah_umur_60_wanita = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.jk,pegawai.tgl_lahir,COUNT(pegawai.nama) AS umur56,
  (YEAR(CURDATE())-YEAR(pegawai.tgl_lahir)) AS Umur FROM pegawai
  WHERE (YEAR(CURDATE())-year(pegawai.tgl_lahir)) =60 AND pegawai.jk='Wanita'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_umur_60_wanita[] = $row[2];
  }
  ?>

  <?php
  $Jumlah_militer = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.stts_kerja, COUNT(pegawai.nik) AS Jumlah_Militer
  FROM pegawai
  WHERE pegawai.stts_kerja='ML'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_militer[] = $row[1];
  }
  ?>

  <?php
  $Jumlah_pns = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.stts_kerja, COUNT(pegawai.nik) AS Jumlah_Militer
  FROM pegawai
  WHERE pegawai.stts_kerja='PNS'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_pns[] = $row[1];
  }
  ?>


  <?php
  $Jumlah_phl = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.stts_kerja, COUNT(pegawai.nik) AS Jumlah_Militer
  FROM pegawai
  WHERE pegawai.stts_kerja='PHL'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_phl[] = $row[1];
  }
  ?>

  <?php
  $jumlah_pendidikan = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.pendidikan, COUNT(pegawai.pendidikan)
  FROM pegawai
  GROUP BY pegawai.pendidikan");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jenis_pendidikan[] = $row[0];
    $jumlah_pendidikan[] = $row[1];
  }
  ?>

  <?php
  $jumlah_departemen = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.departemen,departemen.nama, COUNT(pegawai.departemen)
  FROM pegawai,departemen WHERE pegawai.departemen=departemen.dep_id
  GROUP BY pegawai.departemen");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $kode_departemen[] = $row[0];
    $nama_departemen[] = $row[1];
    $jumlah_departemen[] = $row[2];
  }
  ?>

  <?php
  $jumlah_bidang = array();
  $result = mysqli_query($koneksi, "SELECT pegawai.bidang, COUNT(pegawai.bidang) AS Jumlah
  FROM pegawai
  GROUP BY pegawai.bidang");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $nama_bidang[] = $row[0];
    $jumlah_bidang[] = $row[1];
  }
  ?>



  <?php
  $islam = mysqli_query($koneksi, "SELECT * FROM petugas WHERE petugas.agama='ISLAM'");
  $jumlah_islam = mysqli_num_rows($islam);
  ?>

  <?php
  $kristen = mysqli_query($koneksi, "SELECT * FROM petugas WHERE petugas.agama='KRISTEN'");
  $jumlah_kristen = mysqli_num_rows($kristen);
  ?>

  <?php
  $katolik = mysqli_query($koneksi, "SELECT * FROM petugas WHERE petugas.agama='KATOLIK'");
  $jumlah_katolik = mysqli_num_rows($katolik);
  ?>

  <?php
  $hindu = mysqli_query($koneksi, "SELECT * FROM petugas WHERE petugas.agama='HINDU'");
  $jumlah_hindu = mysqli_num_rows($hindu);
  ?>

  <?php
  $budha = mysqli_query($koneksi, "SELECT * FROM petugas WHERE petugas.agama='BUDHA'");
  $jumlah_budha = mysqli_num_rows($budha);
  ?>

  <?php
  $konghucu = mysqli_query($koneksi, "SELECT * FROM petugas WHERE petugas.agama='Konghucu'");
  $jumlah_konghucu = mysqli_num_rows($konghucu);
  ?>
</body>

</html>

<script>
  Highcharts.chart('komponenpegawai', {
    chart: {
      type: 'column'
    },
    title: {
      text: 'Berdasarkan Komponen Jabatan'
    },
    subtitle: {
      text: ''
    },
    xAxis: {
      categories: [
        'Anggota',
        'Bendahara',
        'Kapol',
        'Karu',
        'Kepala',
        'Operator',
        'Sekretaris',
        'Urdal'
      ],
      crosshair: true
    },
    yAxis: {

      title: {
        text: 'Orang'
      }
    },
    tooltip: {
      headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
      pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
        '<td style="padding:0"><b>{point.y:.1f} Orang</b></td></tr>',
      footerFormat: '</table>',
      shared: true,
      useHTML: true
    },
    plotOptions: {
      column: {
        pointPadding: 0.2,
        borderWidth: 0
      }
    },
    series: [{
      name: 'Pria',
      data: [<?php echo  implode("','", $Jumlah_kelompok_komponen_anggota_pria) ?>,
        <?php echo  implode("','", $Jumlah_kelompok_komponen_bendahara_pria) ?>,
        <?php echo  implode("','", $Jumlah_kelompok_komponen_kapol_pria) ?>,
        <?php echo  implode("','", $Jumlah_kelompok_komponen_karu_pria) ?>,
        <?php echo  implode("','", $Jumlah_kelompok_komponen_kepala_pria) ?>,
        <?php echo  implode("','", $Jumlah_kelompok_komponen_operator_pria) ?>,
        <?php echo  implode("','", $Jumlah_kelompok_komponen_Sekretaris_pria) ?>,
        <?php echo  implode("','", $Jumlah_kelompok_komponen_urdal_pria) ?>
      ]

    }, {
      name: 'Wanita',
      data: [<?php echo  implode("','", $Jumlah_kelompok_komponen_anggota_wanita) ?>,
        <?php echo  implode("','", $Jumlah_kelompok_komponen_bendahara_wanita) ?>,
        <?php echo  implode("','", $Jumlah_kelompok_komponen_kapol_wanita) ?>,
        <?php echo  implode("','", $Jumlah_kelompok_komponen_karu_wanita) ?>,
        <?php echo  implode("','", $Jumlah_kelompok_komponen_kepala_wanita) ?>,
        <?php echo  implode("','", $Jumlah_kelompok_komponen_operator_wanita) ?>,
        <?php echo  implode("','", $Jumlah_kelompok_komponen_Sekretaris_wanita) ?>,
        <?php echo  implode("','", $Jumlah_kelompok_komponen_urdal_wanita) ?>
      ]

    }]
  });
</script>

<script>
  // Age categories
  var categories = [
    '<=25', '26-30 Thn', '31-35 Thn', '36-40 Thn', '41-45 Thn', '46-50 Thn', '51-55 Thn', '56 Thn', '57-60 Thn',
    '>60 Thn'
  ];

  Highcharts.chart('umur', {
    chart: {
      type: 'bar'
    },
    title: {
      text: 'Berdasarkan Umur Pegawai',
      align: 'left'
    },
    subtitle: {
      text: ''
    },
    accessibility: {
      point: {
        valueDescriptionFormat: '{index}. Age {xDescription}, {value}%.'
      }
    },
    xAxis: [{
      categories: categories,
      reversed: false,
      labels: {
        step: 1
      },
      accessibility: {
        description: 'Age (male)'
      }
    }, { // mirror axis on right side
      opposite: true,
      reversed: false,
      categories: categories,
      linkedTo: 0,
      labels: {
        step: 1
      },
      accessibility: {
        description: 'Age (female)'
      }
    }],
    yAxis: {
      title: {
        text: null
      },
      labels: {
        formatter: function() {
          return Math.abs(this.value) + '%';
        }
      },
      accessibility: {
        description: 'Percentage population',
        rangeDescription: 'Range: 0 to 5%'
      }
    },

    plotOptions: {
      series: {
        stacking: 'normal'
      }
    },

    tooltip: {
      formatter: function() {
        return '<b>' + this.series.name + ', age ' + this.point.category + '</b><br/>' +
          'Population: ' + Highcharts.numberFormat(Math.abs(this.point.y), 1) + '%';
      }
    },

    series: [{
      name: 'Laki-Laki',
      data: [
        -<?php echo  implode("','", $Jumlah_umur_dibawah_25th_pria) ?>,
        -<?php echo  implode("','", $Jumlah_umur_26_30_pria) ?>,
        -<?php echo  implode("','", $Jumlah_umur_31_35_pria) ?>,
        -<?php echo  implode("','", $Jumlah_umur_36_40_pria) ?>,
        -<?php echo  implode("','", $Jumlah_umur_41_45_pria) ?>,
        -<?php echo  implode("','", $Jumlah_umur_46_50_pria) ?>,
        -<?php echo  implode("','", $Jumlah_umur_51_55_pria) ?>,
        -<?php echo  implode("','", $Jumlah_umur_56_pria) ?>,
        -<?php echo  implode("','", $Jumlah_umur_57_60_pria) ?>,
        -<?php echo  implode("','", $Jumlah_umur_60_pria) ?>,
      ]
    }, {
      name: 'Perempuan',
      data: [
        <?php echo  implode("','", $Jumlah_umur_dibawah_25th_wanita) ?>,
        <?php echo  implode("','", $Jumlah_umur_26_30_wanita) ?>,
        <?php echo  implode("','", $Jumlah_umur_31_35_wanita) ?>,
        <?php echo  implode("','", $Jumlah_umur_36_40_wanita) ?>,
        <?php echo  implode("','", $Jumlah_umur_41_45_wanita) ?>,
        <?php echo  implode("','", $Jumlah_umur_46_50_wanita) ?>,
        <?php echo  implode("','", $Jumlah_umur_51_55_wanita) ?>,
        <?php echo  implode("','", $Jumlah_umur_57_60_wanita) ?>,
        <?php echo  implode("','", $Jumlah_umur_60_wanita) ?>,
      ]
    }]
  });
</script>

<script>
  // Data retrieved from https://netmarketshare.com
  Highcharts.chart('agama', {
    chart: {
      plotBackgroundColor: null,
      plotBorderWidth: null,
      plotShadow: false,
      type: 'pie'
    },
    title: {
      text: 'Berdasarkan Agama',
      align: 'left'
    },
    tooltip: {
      pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
    },
    accessibility: {
      point: {
        valueSuffix: '%'
      }
    },
    plotOptions: {
      pie: {
        allowPointSelect: true,
        cursor: 'pointer',
        dataLabels: {
          enabled: true,
          format: '<b>{point.name}</b>: {point.percentage:.1f} %'
        }
      }
    },
    series: [{
      name: 'Brands',
      colorByPoint: true,
      data: [{
        name: 'Islam',
        y: <?php echo "$jumlah_islam" ?>
      }, {
        name: 'Kristen ',
        y: <?php echo "$jumlah_kristen" ?>
      }, {
        name: 'Kristen Katolik',
        y: <?php echo "$jumlah_katolik" ?>
      }, {
        name: 'Hindu',
        y: <?php echo "$jumlah_hindu" ?>
      }, {
        name: 'Budha',
        y: <?php echo "$jumlah_budha" ?>
      }, {
        name: 'Konghucu',
        y: <?php echo "$jumlah_konghucu" ?>
      }, ]
    }]
  });
</script>

<script>
  const chart = Highcharts.chart('status', {
    title: {
      text: 'Berdasarkan Status Kerja',
      align: 'left'
    },
    subtitle: {
      text: '',
      align: 'left'
    },
    xAxis: {
      categories: ['Militer', 'PNS', 'PHL']
    },
    series: [{
      type: 'column',
      name: 'Jumlah',
      colorByPoint: true,
      data: [<?php echo  implode("','", $Jumlah_militer) ?>,
        <?php echo  implode("','", $Jumlah_pns) ?>,
        <?php echo  implode("','", $Jumlah_phl) ?>
      ],
      showInLegend: false
    }]
  });

  document.getElementById('plain').addEventListener('click', () => {
    chart.update({
      chart: {
        inverted: false,
        polar: false
      },
      subtitle: {
        text: 'Chart option: Plain | Source: ' +
          '<a href="https://www.nav.no/no/nav-og-samfunn/statistikk/arbeidssokere-og-stillinger-statistikk/helt-ledige"' +
          'target="_blank">NAV</a>'
      }
    });
  });

  document.getElementById('inverted').addEventListener('click', () => {
    chart.update({
      chart: {
        inverted: true,
        polar: false
      },
      subtitle: {
        text: 'Chart option: Inverted | Source: ' +
          '<a href="https://www.nav.no/no/nav-og-samfunn/statistikk/arbeidssokere-og-stillinger-statistikk/helt-ledige"' +
          'target="_blank">NAV</a>'
      }
    });
  });

  document.getElementById('polar').addEventListener('click', () => {
    chart.update({
      chart: {
        inverted: false,
        polar: true
      },
      subtitle: {
        text: 'Chart option: Polar | Source: ' +
          '<a href="https://www.nav.no/no/nav-og-samfunn/statistikk/arbeidssokere-og-stillinger-statistikk/helt-ledige"' +
          'target="_blank">NAV</a>'
      }
    });
  });
</script>

<script>
  Highcharts.chart("departemen", {

    chart: {
      type: 'areaspline'
    },
    title: {
      text: 'Departemen'
    },
    subtitle: {
      text: "",
    },
    legend: {
      layout: 'vertical',
      align: 'left',
      verticalAlign: 'top',
      x: 150,
      y: 100,
      floating: true,
      borderWidth: 1,
      backgroundColor: Highcharts.defaultOptions.legend.backgroundColor || '#FFFFFF'
    },
    xAxis: {
      categories: [
        '<?php echo  implode("','", $nama_departemen) ?> '
      ]

      // plotBands: [{ // visualize the weekend
      //   from: 4.5,
      //   to: 6.5,
      //   color: 'rgba(68, 170, 213, .2)'
      // }]
    },
    yAxis: {
      title: {
        text: 'Jumlah'
      }
    },
    tooltip: {
      shared: true,
      valueSuffix: ' Orang'
    },
    credits: {
      enabled: false
    },
    plotOptions: {
      areaspline: {
        lOpacity: 0.5
      }
    },
    series: [
      <?php {  ?> {
          name: 'Jumlah',
          data: [
            <?php foreach ($jumlah_departemen as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>
    ]
  });
</script>

<script>
  Highcharts.chart("pendidikan", {

    chart: {
      type: 'areaspline'
    },

    title: {
      text: 'Pendidikan'
    },
    subtitle: {
      text: "",
    },
    legend: {
      layout: 'vertical',
      align: 'left',
      verticalAlign: 'top',
      x: 150,
      y: 100,
      floating: true,
      borderWidth: 1,
      backgroundColor: Highcharts.defaultOptions.legend.backgroundColor || '#FFFFFF'
    },
    xAxis: {
      categories: [
        '<?php echo  implode("','", $jenis_pendidikan) ?> '
      ]

      // plotBands: [{ // visualize the weekend
      //   from: 4.5,
      //   to: 6.5,
      //   color: 'rgba(68, 170, 213, .2)'
      // }]
    },
    yAxis: {
      title: {
        text: 'Jumlah'
      }
    },
    tooltip: {
      shared: true,
      valueSuffix: ' Orang'
    },
    credits: {
      enabled: false
    },
    plotOptions: {
      areaspline: {
        lOpacity: 0.5
      }
    },
    series: [
      <?php {  ?> {
          name: 'Jumlah',
          data: [
            <?php foreach ($jumlah_pendidikan as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>
    ]
  });
</script>

<script>
  Highcharts.chart("bidang", {

    chart: {
      type: 'areaspline'
    },

    title: {
      text: 'Bidang'
    },
    subtitle: {
      text: "",
    },
    legend: {
      layout: 'vertical',
      align: 'left',
      verticalAlign: 'top',
      x: 150,
      y: 100,
      floating: true,
      borderWidth: 1,
      backgroundColor: Highcharts.defaultOptions.legend.backgroundColor || '#FFFFFF'
    },
    xAxis: {
      categories: [
        '<?php echo  implode("','", $nama_bidang) ?> '
      ]
    },
    yAxis: {
      title: {
        text: 'Jumlah'
      }
    },
    tooltip: {
      shared: true,
      valueSuffix: ' Orang'
    },
    credits: {
      enabled: false
    },
    plotOptions: {
      areaspline: {
        lineColor: 'black'
      }
    },

    series: [
      <?php {  ?> {
          name: 'Jumlah',
          data: [
            <?php foreach ($jumlah_bidang as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ],
          color: '#9ED5C5'
        },
      <?php } ?>
    ]

  });
</script>