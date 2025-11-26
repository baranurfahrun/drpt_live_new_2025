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
          <h5 class="mb-2">Informasi </h5>
          <div class="row">
            <div class="col-md-3 col-sm-6 col-12">
              <div class="info-box">
                <span class="info-box-icon bg-info"><i class="far fa-envelope"></i></span>
                <div class="info-box-content">
                  <span class="info-box-text">Total Pasien Poliklinik</span>
                  <?php
                  $total_pasien_poliklinik = mysqli_query($koneksi, "SELECT * FROM reg_periksa WHERE status_lanjut='Ralan'");
                  $jumlah = mysqli_num_rows($total_pasien_poliklinik);
                  ?>
                  <span class="info-box-number"><?php echo "$jumlah" ?></span>
                </div>
                <!-- /.info-box-content -->
              </div>
              <!-- /.info-box -->
            </div>
            <!-- /.col -->
            <div class="col-md-2 col-sm-6 col-12">
              <div class="info-box">
                <span class="info-box-icon bg-success"><i class="fa fa-user-md"></i></span>

                <div class="info-box-content">
                  <span class="info-box-text">Pasien Daftar Hari Ini</span>
                  <?php
                  $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT * FROM reg_periksa WHERE status_lanjut='Ralan' and reg_periksa.tgl_registrasi = curdate() ");
                  $jumlah_Hari_ini = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                  ?>
                  <span class="info-box-number">
                    <?php echo "$jumlah_Hari_ini" ?>
                  </span>
                </div>
                <!-- /.info-box-content -->
              </div>
              <!-- /.info-box -->
            </div>
            <!-- /.col -->
            <div class="col-md-2 col-sm-6 col-12">
              <div class="info-box">
                <span class="info-box-icon bg-warning"><i class="far fa-copy"></i></span>

                <div class="info-box-content">
                  <span class="info-box-text">Sudah Periksa Hari ini</span>
                  <?php
                  $total_pasien_dirawat_hari_ini = mysqli_query($koneksi, "SELECT * FROM reg_periksa WHERE status_lanjut='Ralan' AND stts='Sudah' and reg_periksa.tgl_registrasi = curdate()");
                  $jumlah = mysqli_num_rows($total_pasien_dirawat_hari_ini);
                  ?>
                  <span class="info-box-number"><?php echo "$jumlah" ?></span>
                </div>
                <!-- /.info-box-content -->
              </div>
              <!-- /.info-box -->
            </div>
            <!-- /.col -->
            <div class="col-md-2 col-sm-6 col-12">
              <div class="info-box">
                <span class="info-box-icon bg-warning"><i class="fa fa-bed"></i></span>

                <div class="info-box-content">
                  <span class="info-box-text">Pasien Dirujuk Hari ini</span>
                  <?php
                  $total_pasien_dirujuk_hari_ini = mysqli_query($koneksi, "SELECT * FROM reg_periksa WHERE status_lanjut='Ralan' AND stts='Dirujuk' AND reg_periksa.tgl_registrasi = curdate()");
                  $jumlah = mysqli_num_rows($total_pasien_dirujuk_hari_ini);
                  ?>
                  <span class="info-box-number"><?php echo "$jumlah" ?></span>
                </div>
                <!-- /.info-box-content -->
              </div>
              <!-- /.info-box -->
            </div>
            <!-- /.col -->
            <div class="col-md-3 col-sm-6 col-12">
              <div class="info-box">
                <span class="info-box-icon bg-danger"><i class="fa fa-window-close"></i></span>

                <div class="info-box-content">
                  <span class="info-box-text">Pasien Batal Hari ini</span>
                  <?php
                  $total_pasien_batal_hari_ini = mysqli_query($koneksi, "SELECT * FROM reg_periksa WHERE status_lanjut='Ralan' AND stts='batal' AND reg_periksa.tgl_registrasi = curdate()");
                  $jumlah = mysqli_num_rows($total_pasien_batal_hari_ini);
                  ?>
                  <span class="info-box-number"><?php echo "$jumlah" ?></span>
                </div>
                <!-- /.info-box-content -->
              </div>
              <!-- /.info-box -->
            </div>
            <!-- /.col -->
            <div class="col-md-6">
              <!-- AREA CHART jENIS bayar -->
              <figure class="highcharts-figure">
                <div id="cara-bayar"></div>
              </figure>
            </div>
            <div class="col-md-6">
              <!-- AREA CHART Jenis Bayar -->
              <figure class="highcharts-figure">
                <div id="jenis-tni"></div>
              </figure>
            </div>
            <div class="col-md-6">
              <!-- AREA CHART Pasien Baru -->
              <figure class="highcharts-figure">
                <div id="pasien-baru"></div>
              </figure>
            </div>
            <div class="col-md-6">
              <!-- Chart Pasien Lama -->
              <figure class="highcharts-figure">
                <div id="pasien-lama"></div>
              </figure>
            </div>
            <div class="col-md-12">
              <!-- AREA CHART Pasien bATAL -->
              <figure class="highcharts-figure">
                <div id="pasien-batal"></div>
              </figure>
            </div>
            <div class="col-md-12">
              <!-- AREA CHART hari ini & bulanan kolom -->
              <figure class="highcharts-figure">
                <div id="chart_ralan"></div>
              </figure>
            </div>

            <div class="container-fluid">
              <label>Filter:</label>
              <form action="<?php echo $_SERVER["PHP_SELF"]; ?>" method="post">
                <div class="row align-items-center">
                  <div class="col-md-2">
                    <div class="input-group date" id="reservationdate" data-target-input="nearest">
                      <input type="date" id="tgl_mulai" class="form-control" data-target="#reservationdate" name="tgl_awal" value="<?php if (isset($_POST['tgl_awal'])) echo $_POST['tgl_awal']; ?>" />
                      <div class="input-group-append" data-target="#reservationdate" data-toggle="datetimepicker">
                        <!-- <div class="input-group-text"><i class="fa fa-calendar"></i></div> -->
                      </div>
                    </div>
                  </div>
                  <div class="col-md-2">
                    <div class="input-group date" id="reservationdate" data-target-input="nearest">
                      <input type="date" id="tgl_akhir" class="form-control" data-target="#reservationdate" name="tgl_akhir" value="<?php if (isset($_POST['tgl_akhir'])) echo $_POST['tgl_akhir']; ?>" />
                      <div class="input-group-append" data-target="#reservationdate" data-toggle="datetimepicker">
                        <!-- <div class="input-group-text"><i class="fa fa-calendar"></i></div> -->
                      </div>
                    </div>
                  </div>
                  <div class="col-md-2">
                    <div class="col text-center">
                      <button type="submit" class="btn btn-block btn-primary">Cari</button>
                    </div>
                  </div>
                </div>
            </div>
            <div class="mx-auto" style="width: 200px;">
              &nbsp;
            </div>
            <div class="col-md-12">
              <!-- AREA CHART mingguan -->
              <figure class="highcharts-figure">
                <div id="chart_pertanggal"></div>
              </figure>
            </div>
            <!-- </div> -->
            <div class="col-md-12">
              <!-- AREA CHART mingguan -->
              <figure class="highcharts-figure">
                <div id="chart_mingguan"></div>
              </figure>
            </div>
            <div class="col-md-12">
              <!-- AREA CHART perbandingan bulanan -->
              <figure class="highcharts-figure">
                <div id="chart_perbandingan_bulanan"></div>
              </figure>
            </div>
            <!-- <div class="col-md-12">
                  <figure class="highcharts-figure">
                    <div id="chart_tahunan"></div>
                  </figure>
                </div> -->
            <div class="col-md-12">
              <!-- AREA CHART perbandingan tahunan-->
              <figure class="highcharts-figure">
                <div id="chart_perbandingan_tahunan"></div>
              </figure>
            </div>
            <div class="col-md-12">
              <!-- AREA CHART dokter now -->
              <figure class="highcharts-figure">
                <div id="chart_dokter_now"></div>
                <p class="highcharts-description">
                </p>
              </figure>
            </div>
            <!-- tombol pencarian dokter -->
            <!-- <div class="col-md-4">
              <div class="form-group">
                <label>Pilih dokter</label>
                <select name="nm_dokter" class="custom-select" style="width: 100%;" id="filterdokter">
                  <?php
                  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.kd_poli,poliklinik.nm_poli 
                          AS Poli 
                          FROM reg_periksa,poliklinik,dokter
                          WHERE status_lanjut='Ralan'  AND reg_periksa.kd_dokter
                          and poliklinik.kd_poli=reg_periksa.kd_poli AND reg_periksa.kd_dokter=dokter.kd_dokter
                          GROUP BY reg_periksa.kd_dokter ");
                  while ($data = mysqli_fetch_array($result)) {
                  ?>
                    <option value="<?= $data['nm_dokter']; ?>"><?php echo $data['nm_dokter']; ?></option>
                  <?php
                  }
                  ?>
                </select>
              </div>
            </div> -->
            <!-- <div class="col-md-6">
              <div class="container-fluid">
                <label>Filter:</label>
                <form action="<?php echo $_SERVER["PHP_SELF"]; ?>" method="post">
                  <div class="row align-items-center">
                    <div class="col-md-3">
                      <div class="input-group date" id="reservationdate" data-target-input="nearest">
                        <input type="date" id="tgl_mulai" class="form-control" data-target="#reservationdate" name="tgl_awal" value="<?php if (isset($_POST['tgl_awal'])) echo $_POST['tgl_awal']; ?>" />
                        <div class="input-group-append" data-target="#reservationdate" data-toggle="datetimepicker">  
                        </div>
                      </div>
                    </div>
                    <div class="col-md-3">
                      <div class="input-group date" id="reservationdate" data-target-input="nearest">
                        <input type="date" id="tgl_akhir" class="form-control" data-target="#reservationdate" name="tgl_akhir" value="<?php if (isset($_POST['tgl_akhir'])) echo $_POST['tgl_akhir']; ?>" />
                        <div class="input-group-append" data-target="#reservationdate" data-toggle="datetimepicker">     
                        </div>
                      </div>
                    </div>
                    <div class="col-md-2">
                      <div class="col text-center">
                        <button type="submit" class="btn btn-block btn-primary">Cari</button>
                      </div>
                    </div>
                  </div>
              </div>
            </div> -->


            <div class="col-md-12">
              <!-- Chart_cari_dokter -->
              <figure class="highcharts-figure">
                <div id="chart_cari_dokter0ff"></div>
              </figure>
            </div>
            <!-- <div class="col-md-6">
                  <figure class="highcharts-figure">
                    <div id="pasien-online"></div>
                  </figure>
                </div> -->
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

  <!-- qwery Status ralan sudah periksa hari ini Poliklinik -->
  <?php
  $tglregistrasi = array();
  $kdpoli = array();
  $norekam = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli, count(reg_periksa.kd_poli) jumlah 
  FROM reg_periksa, poliklinik 
  WHERE reg_periksa.status_lanjut ='ralan' and reg_periksa.tgl_registrasi = curdate() and reg_periksa.kd_poli=poliklinik.kd_poli 
  GROUP BY reg_periksa.kd_poli ");
  //tampilkan hasil query
  while ($row = mysqli_fetch_array($result)) {
    $tglregistrasi[] = $row[0];
    $kdpoli[] = $row[1];
    $norekam[] = $row[2];
  }
  ?>


  <?php
  if (isset($_POST['tgl_awal']) && isset($_POST['tgl_akhir'])) {
    $tgl_awal = date('Y-m-d', strtotime($_POST["tgl_awal"]));
    $tgl_akhir = date('Y-m-d', strtotime($_POST["tgl_akhir"]));
  }
  ?>
  <!-- qwery Pertanggal -->
  <?php
  $jumlah_pasien_uro_tanggal = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='URO' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY (tgl_registrasi) ASC");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_pasien_uro_tanggal[] = $row[0];
    $kdpolim_uro[] = $row[1];
    $nm_poli_uro[] = $row[2];
    $tahun_bulan_tgl_pertanggal[] = $row[3];
  }
  ?>

  <?php
  $jumlah_pasien_anak_tanggal = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='ANA' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY (tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_pasien_anak_tanggal[] = $row[0];
    $kdpolim_anak[] = $row[1];
    $nm_poli_anak[] = $row[2];
    $tahun_bulan_tgl_pertanggal[] = $row[3];
  }
  ?>

  <?php
  $jumlah_pasein_bedah_tanggal = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='BED' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY (tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_pasein_bedah_tanggal[] = $row[0];
    $kdpolim_bedah[] = $row[1];
    $nm_bedah[] = $row[2];
    $tahun_bulan_tgl_pertanggal[] = $row[3];
  }
  ?>

  <?php
  $jumlah_bedah_anak_tanggal = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='BDA' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY (tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_bedah_anak_tanggal[] = $row[0];
    $kdpolim_anak[] = $row[1];
    $nm_anak[] = $row[2];
    $tahun_bulan_tgl_pertanggal[] = $row[3];
  }
  ?>

  <?php
  $jumlah_bedah_degif_tanggal = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='018' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY (tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_bedah_degif_tanggal[] = $row[0];
    $kdpolim_defif[] = $row[1];
    $nm_bedah_degif[] = $row[2];
    $tahun_bulan_tgl_pertanggal[] = $row[3];
  }
  ?>

  <?php
  $jumlah_bedah_mulut_tanggal = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='BDM' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY (tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_bedah_mulut_tanggal[] = $row[0];
    $kdpolim_defif[] = $row[1];
    $nm_bedah_mulut[] = $row[2];
    $tahun_bulan_tgl_perminggu[] = $row[3];
  }
  ?>

  <?php
  $jumlah_bedah_saraf_tanggal = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='BSY' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY (tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_bedah_saraf_tanggal[] = $row[0];
    $kdpolim_saraf[] = $row[1];
    $nm_bedah_saraf[] = $row[2];
    $tahun_bulan_tgl_pertanggal[3];
  }
  ?>

  <?php
  $jumlah_bedah_vascular_tanggal = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='U0036' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY (tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_bedah_vascular_tanggal[] = $row[0];
    $kdpolim_vascular[] = $row[1];
    $nm_bedah_vascular[] = $row[2];
    $tahun_bulan_tgl_pertanggal[3];
  }
  ?>

  <?php
  $jumlah_gigi_tanggal = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='GIG' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY (tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_gigi_tanggal[] = $row[0];
    $kdpolim_gigi[] = $row[1];
    $nm_bedah_gigi[] = $row[2];
    $tahun_bulan_tgl_pertanggal[3];
  }
  ?>

  <?php
  $jumlah_Ginjal_Hipeternsi_tanggal = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='007' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY (tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_Ginjal_Hipeternsi_tanggal[] = $row[0];
    $kdpolim_ginjal[] = $row[1];
    $nm_bedah_ginjal[] = $row[2];
    $tahun_bulan_tgl_pertanggal[3];
  }
  ?>

  <?php
  $jumlah_jantung_tanggal = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='JAN' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY (tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_jantung_tanggal[] = $row[0];
    $kdpolim_jantung[] = $row[1];
    $nm_bedah_jantung[] = $row[2];
    $tahun_bulan_tgl_pertanggal[3];
  }
  ?>

  <?php
  $jumlah_jiwa_tanggal = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='JIW' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY (tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_jiwa_tanggal[] = $row[0];
    $kdpolim_jiwa[] = $row[1];
    $nm_bedah_jiwa[] = $row[2];
    $tahun_bulan_tgl_pertanggal[3];
  }
  ?>

  <?php
  $jumlah_konservai_gigi_tanggal = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='GND' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY (tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_konservai_gigi_tanggal[] = $row[0];
    $kdpolim_konservai_gigi[] = $row[1];
    $nm_bedah_konservai_gigi[] = $row[2];
    $tahun_bulan_tgl_perminggu[3];
  }
  ?>

  <?php
  $jumlah_kulkel_tanggal = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='KLT' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY (tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_kulkel_tanggal[] = $row[0];
    $kdpolim_kulkel[] = $row[1];
    $nm_kulkel[] = $row[2];
    $tahun_bulan_tgl_pertanggal[3];
  }
  ?>

  <?php
  $jumlah_mata_tanggal = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='MAT' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY (tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_mata_tanggal[] = $row[0];
    $kdpolim_mata[] = $row[1];
    $nm_mata[] = $row[2];
    $tahun_bulan_tgl_pertanggal[3];
  }
  ?>

  <?php
  $jumlah_obgyn_tanggal = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='OBG' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY (tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_obgyn_tanggal[] = $row[0];
    $kdpolim_obgyn[] = $row[1];
    $nm_obgyn[] = $row[2];
    $tahun_bulan_tgl_pertanggal[3];
  }
  ?>

  <?php
  $jumlah_ortopedi_tanggal = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='ORT' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY (tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_ortopedi_tanggal[] = $row[0];
    $kdpolim_ortopedi[] = $row[1];
    $nm_ortopedi[] = $row[2];
    $tahun_bulan_tgl_pertanggal[3];
  }
  ?>

  <?php
  $jumlah_paru_tanggal = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='PAR' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY (tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_paru_tanggal[] = $row[0];
    $kdpolim_paru[] = $row[1];
    $nm_paru[] = $row[2];
    $tahun_bulan_tgl_pertanggal[3];
  }
  ?>

  <?php
  $jumlah_penyakit_dalam_tanggal = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='INT' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY (tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_penyakit_dalam_tanggal[] = $row[0];
    $kdpolim_penyakit_dalam[] = $row[1];
    $nm_penyakit_dalam[] = $row[2];
    $tahun_bulan_tgl_pertanggal[3];
  }
  ?>

  <?php
  $jumlah_saraf_tanggal = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='SAR' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY (tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_saraf_tanggal[] = $row[0];
    $kdpolim_saraf[] = $row[1];
    $nm_saraf[] = $row[2];
    $tahun_bulan_tgl_pertanggal[3];
  }
  ?>

  <?php
  $jumlah_tht_tanggal = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='THT' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY (tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_tht_tanggal[] = $row[0];
    $kdpolim_tht[] = $row[1];
    $nm_tht[] = $row[2];
    $tahun_bulan_tgl_pertanggal[3];
  }
  ?>

  <?php
  $jumlah_vct_tanggal = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='VCT' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY (tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_vct_tanggal[] = $row[0];
    $kdpolim_vct[] = $row[1];
    $nm_vct[] = $row[2];
    $tahun_bulan_tgl_pertanggal[3];
  }
  ?>

  <?php
  $jumlah_bedah_ongkologi_tanggal = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='017' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY (tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_bedah_ongkologi_tanggal[] = $row[0];
    $kdpolim_ongkologi[] = $row[1];
    $nm_bedah_ongkologi[] = $row[2];
    $tahun_bulan_tgl_pertanggal[3];
  }
  ?>


  <!-- qwery Mingguan -->
  <?php
  $jumlah_pasien_uro = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='URO' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEARWEEK(tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_pasien_uro[] = $row[0];
    $kdpolim_uro[] = $row[1];
    $nm_poli_uro[] = $row[2];
    $tahun_bulan_tgl_mingguan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_pasien_anak = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='ANA' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEARWEEK(tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_pasien_anak[] = $row[0];
    $kdpolim_anak[] = $row[1];
    $nm_poli_anak[] = $row[2];
    $tahun_bulan_tgl_mingguan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_pasein_bedah = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='BED' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEARWEEK(tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_pasein_bedah[] = $row[0];
    $kdpolim_bedah[] = $row[1];
    $nm_bedah[] = $row[2];
    $tahun_bulan_tgl_mingguan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_bedah_anak = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='BDA' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEARWEEK(tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_bedah_anak[] = $row[0];
    $kdpolim_anak[] = $row[1];
    $nm_anak[] = $row[2];
    $tahun_bulan_tgl_mingguan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_bedah_degif = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='018' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEARWEEK(tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_bedah_degif[] = $row[0];
    $kdpolim_defif[] = $row[1];
    $nm_bedah_degif[] = $row[2];
    $tahun_bulan_tgl_mingguan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_bedah_mulut = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='BDM' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEARWEEK(tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_bedah_mulut[] = $row[0];
    $kdpolim_defif[] = $row[1];
    $nm_bedah_mulut[] = $row[2];
    $tahun_bulan_tgl_mingguan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_bedah_saraf = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='BSY' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEARWEEK(tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_bedah_saraf[] = $row[0];
    $kdpolim_saraf[] = $row[1];
    $nm_bedah_saraf[] = $row[2];
    $tahun_bulan_tgl_mingguan[3];
  }
  ?>

  <?php
  $jumlah_bedah_vascular = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='U0036' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEARWEEK(tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_bedah_vascular[] = $row[0];
    $kdpolim_vascular[] = $row[1];
    $nm_bedah_vascular[] = $row[2];
    $tahun_bulan_tgl_mingguan[3];
  }
  ?>

  <?php
  $jumlah_gigi = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='GIG' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEARWEEK(tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_gigi[] = $row[0];
    $kdpolim_gigi[] = $row[1];
    $nm_bedah_gigi[] = $row[2];
    $tahun_bulan_tgl_mingguan[3];
  }
  ?>

  <?php
  $jumlah_Ginjal_Hipeternsi = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='007' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEARWEEK(tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_Ginjal_Hipeternsi[] = $row[0];
    $kdpolim_ginjal[] = $row[1];
    $nm_bedah_ginjal[] = $row[2];
    $tahun_bulan_tgl_mingguan[3];
  }
  ?>

  <?php
  $jumlah_jantung = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='JAN' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEARWEEK(tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_jantung[] = $row[0];
    $kdpolim_jantung[] = $row[1];
    $nm_bedah_jantung[] = $row[2];
    $tahun_bulan_tgl_mingguan[3];
  }
  ?>

  <?php
  $jumlah_jiwa = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='JIW' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEARWEEK(tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_jiwa[] = $row[0];
    $kdpolim_jiwa[] = $row[1];
    $nm_bedah_jiwa[] = $row[2];
    $tahun_bulan_tgl_mingguan[3];
  }
  ?>

  <?php
  $jumlah_konservai_gigi = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='GND' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEARWEEK(tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_konservai_gigi[] = $row[0];
    $kdpolim_konservai_gigi[] = $row[1];
    $nm_bedah_konservai_gigi[] = $row[2];
    $tahun_bulan_tgl_mingguan[3];
  }
  ?>

  <?php
  $jumlah_kulkel = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='KLT' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEARWEEK(tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_kulkel[] = $row[0];
    $kdpolim_kulkel[] = $row[1];
    $nm_kulkel[] = $row[2];
    $tahun_bulan_tgl_mingguan[3];
  }
  ?>

  <?php
  $jumlah_mata = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='MAT' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEARWEEK(tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_mata[] = $row[0];
    $kdpolim_mata[] = $row[1];
    $nm_mata[] = $row[2];
    $tahun_bulan_tgl_mingguan[3];
  }
  ?>

  <?php
  $jumlah_obgyn = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='OBG' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEARWEEK(tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_obgyn[] = $row[0];
    $kdpolim_obgyn[] = $row[1];
    $nm_obgyn[] = $row[2];
    $tahun_bulan_tgl_mingguan[3];
  }
  ?>

  <?php
  $jumlah_ortopedi = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='ORT' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEARWEEK(tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_ortopedi[] = $row[0];
    $kdpolim_ortopedi[] = $row[1];
    $nm_ortopedi[] = $row[2];
    $tahun_bulan_tgl_mingguan[3];
  }
  ?>

  <?php
  $jumlah_paru = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='PAR' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEARWEEK(tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_paru[] = $row[0];
    $kdpolim_paru[] = $row[1];
    $nm_paru[] = $row[2];
    $tahun_bulan_tgl_mingguan[3];
  }
  ?>

  <?php
  $jumlah_penyakit_dalam = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='INT' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEARWEEK(tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_penyakit_dalam[] = $row[0];
    $kdpolim_penyakit_dalam[] = $row[1];
    $nm_penyakit_dalam[] = $row[2];
    $tahun_bulan_tgl_mingguan[3];
  }
  ?>

  <?php
  $jumlah_saraf = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='SAR' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEARWEEK(tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_saraf[] = $row[0];
    $kdpolim_saraf[] = $row[1];
    $nm_saraf[] = $row[2];
    $tahun_bulan_tgl_mingguan[3];
  }
  ?>

  <?php
  $jumlah_tht = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='THT' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEARWEEK(tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_tht[] = $row[0];
    $kdpolim_tht[] = $row[1];
    $nm_tht[] = $row[2];
    $tahun_bulan_tgl_mingguan[3];
  }
  ?>

  <?php
  $jumlah_vct = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='VCT' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEARWEEK(tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_vct[] = $row[0];
    $kdpolim_vct[] = $row[1];
    $nm_vct[] = $row[2];
    $tahun_bulan_tgl_mingguan[3];
  }
  ?>

  <?php
  $jumlah_bedah_ongkologi = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='017' AND reg_periksa.tgl_registrasi 
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEARWEEK(tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_bedah_ongkologi[] = $row[0];
    $kdpolim_ongkologi[] = $row[1];
    $nm_bedah_ongkologi[] = $row[2];
    $tahun_bulan_tgl_mingguan[3];
  }
  ?>

  <!-- QWERY BULANAN -->
  <?php
  $jumlah_bulan_anak = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%M %Y')
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='ANA' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  and poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY DATE_FORMAT(tgl_registrasi,'%M %Y') order BY tgl_registrasi ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_bulan_anak[] = $row[0];
    $kdpolim_bulan_anak[] = $row[1];
    $nm_poli_bulan_anak[] = $row[2];
    $bulanan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_bulan_bedah = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%M %Y')
        AS Tahun_bulan_Tgl
        FROM reg_periksa,poliklinik
        WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='BED' AND reg_periksa.tgl_registrasi
        BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
        and poliklinik.kd_poli=reg_periksa.kd_poli  
        GROUP BY DATE_FORMAT(tgl_registrasi,'%M %Y') order BY tgl_registrasi ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_bulan_bedah[] = $row[0];
    $kdpolim_bulan_bedah[] = $row[1];
    $nm_poli_bulan_bedah[] = $row[2];
    $bulanan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_bulan_bedah_anak = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%M %Y')
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='BDA' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  and poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY DATE_FORMAT(tgl_registrasi,'%M %Y') order BY tgl_registrasi ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_bulan_bedah_anak[] = $row[0];
    $kdpolim_bulan_bedah_anak[] = $row[1];
    $nm_poli_bulan_bedah_anak[] = $row[2];
    $bulanan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_bulan_digestif = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%M %Y')
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='018' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  and poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY DATE_FORMAT(tgl_registrasi,'%M %Y') order BY tgl_registrasi ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_bulan_digestif[] = $row[0];
    $kdpolim_bulan_digestif[] = $row[1];
    $nm_poli_bulan_digestif[] = $row[2];
    $bulanan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_bulan_bedah_mulut = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%M %Y')
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='BDM' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  and poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY DATE_FORMAT(tgl_registrasi,'%M %Y') order BY tgl_registrasi ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_bulan_bedah_mulut[] = $row[0];
    $kdpolim_bulan_bedah_mulut[] = $row[1];
    $nm_poli_bulan_bedah_mulut[] = $row[2];
    $bulanan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_bulan_bedah_ongkologi = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%M %Y')
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='017' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  and poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY DATE_FORMAT(tgl_registrasi,'%M %Y') order BY tgl_registrasi ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_bulan_bedah_ongkologi[] = $row[0];
    $kdpolim_bulan_bedah_ongkologi[] = $row[1];
    $nm_poli_bulan_bedah_ongkologi[] = $row[2];
    $bulanan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_bulan_bedah_saraf = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%M %Y')
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='BSY' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  and poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY DATE_FORMAT(tgl_registrasi,'%M %Y') order BY tgl_registrasi ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_bulan_bedah_saraf[] = $row[0];
    $kdpolim_bulan_bedah_saraf[] = $row[1];
    $nm_poli_bulan_bedah_saraf[] = $row[2];
    $bulanan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_bulan_bedah_vascular = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%M %Y')
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='U0036' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  and poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY DATE_FORMAT(tgl_registrasi,'%M %Y') order BY tgl_registrasi ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_bulan_bedah_vascular[] = $row[0];
    $kdpolim_bulan_bedah_vascular[] = $row[1];
    $nm_poli_bulan_bedah_vascular[] = $row[2];
    $bulanan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_bulan_gigi = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%M %Y')
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='GIG' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  and poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY DATE_FORMAT(tgl_registrasi,'%M %Y') order BY tgl_registrasi ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_bulan_gigi[] = $row[0];
    $kdpolim_bulan_gigi[] = $row[1];
    $nm_poli_bulan_gigi[] = $row[2];
    $bulanan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_bulan_jantung = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%M %Y')
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='JAN' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  and poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY DATE_FORMAT(tgl_registrasi,'%M %Y') order BY tgl_registrasi ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_bulan_jantung[] = $row[0];
    $kdpolim_bulan_jantung[] = $row[1];
    $nm_poli_bulan_jantung[] = $row[2];
    $bulanan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_bulan_jiwa = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%M %Y')
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='JIW' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  and poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY DATE_FORMAT(tgl_registrasi,'%M %Y') order BY tgl_registrasi ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_bulan_jiwa[] = $row[0];
    $kdpolim_bulan_jiwa[] = $row[1];
    $nm_poli_bulan_jiwa[] = $row[2];
    $bulanan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_bulan_kgigi = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%M %Y')
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='GND' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  and poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY DATE_FORMAT(tgl_registrasi,'%M %Y') order BY tgl_registrasi ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_bulan_kgigi[] = $row[0];
    $kdpolim_bulan_kgigi[] = $row[1];
    $nm_poli_bulan_kgigi[] = $row[2];
    $bulanan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_bulan_kulkel = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%M %Y')
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='KLT' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  and poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY DATE_FORMAT(tgl_registrasi,'%M %Y') order BY tgl_registrasi ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_bulan_kulkel[] = $row[0];
    $kdpolim_bulan_kulkel[] = $row[1];
    $nm_poli_bulan_kulkel[] = $row[2];
    $bulanan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_bulan_mata = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%M %Y')
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='MAT' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  and poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY DATE_FORMAT(tgl_registrasi,'%M %Y') order BY tgl_registrasi ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_bulan_mata[] = $row[0];
    $kdpolim_bulan_mata[] = $row[1];
    $nm_poli_bulan_mata[] = $row[2];
    $bulanan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_bulan_obgyn = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%M %Y')
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='OBG' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  and poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY DATE_FORMAT(tgl_registrasi,'%M %Y') order BY tgl_registrasi ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_bulan_obgyn[] = $row[0];
    $kdpolim_bulan_obgyn[] = $row[1];
    $nm_poli_bulan_obgyn[] = $row[2];
    $bulanan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_bulan_ortopedi = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%M %Y')
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='ORT' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  and poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY DATE_FORMAT(tgl_registrasi,'%M %Y') order BY tgl_registrasi ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_bulan_ortopedi[] = $row[0];
    $kdpolim_bulan_ortopedi[] = $row[1];
    $nm_poli_bulan_ortopedi[] = $row[2];
    $bulanan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_bulan_paru = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%M %Y')
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='PAR' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  and poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY DATE_FORMAT(tgl_registrasi,'%M %Y') order BY tgl_registrasi ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_bulan_paru[] = $row[0];
    $kdpolim_bulan_paru[] = $row[1];
    $nm_poli_bulan_paru[] = $row[2];
    $bulanan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_bulan_int = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%M %Y')
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='INT' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  and poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY DATE_FORMAT(tgl_registrasi,'%M %Y') order BY tgl_registrasi ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_bulan_int[] = $row[0];
    $kdpolim_bulan_int[] = $row[1];
    $nm_poli_bulan_int[] = $row[2];
    $bulanan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_bulan_saraf = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%M %Y')
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='SAR' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  and poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY DATE_FORMAT(tgl_registrasi,'%M %Y') order BY tgl_registrasi ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_bulan_saraf[] = $row[0];
    $kdpolim_bulan_saraf[] = $row[1];
    $nm_poli_bulan_saraf[] = $row[2];
    $bulanan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_bulan_tht = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%M %Y')
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='THT' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  and poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY DATE_FORMAT(tgl_registrasi,'%M %Y') order BY tgl_registrasi ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_bulan_tht[] = $row[0];
    $kdpolim_bulan_tht[] = $row[1];
    $nm_poli_bulan_tht[] = $row[2];
    $bulanan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_bulan_uro = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%M %Y')
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='URO' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  and poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY DATE_FORMAT(tgl_registrasi,'%M %Y') order BY tgl_registrasi ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_bulan_uro[] = $row[0];
    $kdpolim_bulan_uro[] = $row[1];
    $nm_poli_bulan_uro[] = $row[2];
    $bulanan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_bulan_vct = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%M %Y')
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='VCT' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  and poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY DATE_FORMAT(tgl_registrasi,'%M %Y') order BY tgl_registrasi ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_bulan_vct[] = $row[0];
    $kdpolim_bulan_vct[] = $row[1];
    $nm_poli_bulan_vct[] = $row[2];
    $bulanan[] = $row[3];
  }
  ?>

  <!-- qwery TAHUNAN-->
  <?php
  $jumlah_tahun_anak = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%Y') 
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='ANA' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEAR(tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_tahun_anak[] = $row[0];
    $kdpolim_tahun_anak[] = $row[1];
    $nm_poli_tahun_anak[] = $row[2];
    $tahunan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_tahun_bedah = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%Y') 
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='BED' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEAR(tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_tahun_bedah[] = $row[0];
    $kdpolim_tahun_bedah[] = $row[1];
    $nm_poli_tahun_bedah[] = $row[2];
    $tahunan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_tahun_bedah_anak = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%Y') 
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='BDA' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEAR(tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_tahun_bedah_anak[] = $row[0];
    $kdpolim_tahun_bedah_anak[] = $row[1];
    $nm_poli_tahun_bedah_anak[] = $row[2];
    $tahunan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_tahun_digestif = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%Y') 
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='018' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEAR(tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_tahun_digestif[] = $row[0];
    $kdpolim_tahun_digestif[] = $row[1];
    $nm_poli_tahun_digestif[] = $row[2];
    $tahunan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_tahun_bedah_mulut = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%Y') 
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='BDM' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEAR(tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_tahun_bedah_mulut[] = $row[0];
    $kdpolim_tahun_bedah_mulut[] = $row[1];
    $nm_poli_tahun_bedah_mulut[] = $row[2];
    $tahunan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_tahun_bedah_ongkologi = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%Y') 
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='017' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEAR(tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_tahun_bedah_ongkologi[] = $row[0];
    $kdpolim_tahun_bedah_ongkologi[] = $row[1];
    $nm_poli_tahun_bedah_ongkologi[] = $row[2];
    $tahunan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_tahun_bedah_saraf = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%Y') 
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='BSY' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEAR(tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_tahun_bedah_saraf[] = $row[0];
    $kdpolim_tahun_bedah_saraf[] = $row[1];
    $nm_poli_tahun_bedah_saraf[] = $row[2];
    $tahunan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_tahun_bedah_vascular = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%Y') 
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='U0036' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEAR(tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_bulan_tahun_vascular[] = $row[0];
    $kdpolim_bulan_tahun_vascular[] = $row[1];
    $nm_poli_bulan_tahun_vascular[] = $row[2];
    $tahunan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_tahun_gigi = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%Y') 
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='GIG' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEAR(tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_tahun_gigi[] = $row[0];
    $kdpolim_tahun_gigi[] = $row[1];
    $nm_poli_tahun_gigi[] = $row[2];
    $tahunan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_tahun_jantung = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%Y') 
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='JAN' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEAR(tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_tahun_jantung[] = $row[0];
    $kdpolim_tahun_jantung[] = $row[1];
    $nm_poli_tahun_jantung[] = $row[2];
    $tahunan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_tahun_jiwa = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%Y') 
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='JIW' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEAR(tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_tahun_jiwa[] = $row[0];
    $kdpolim_tahun_jiwa[] = $row[1];
    $nm_poli_tahun_jiwa[] = $row[2];
    $tahunan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_tahun_kgigi = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%Y') 
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='GND' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEAR(tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_tahun_kgigi[] = $row[0];
    $kdpolim_tahun_kgigi[] = $row[1];
    $nm_poli_tahun_kgigi[] = $row[2];
    $tahunan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_tahun_kulkel = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%Y') 
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='KLT' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEAR(tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_tahun_kulkel[] = $row[0];
    $kdpolim_tahun_kulkel[] = $row[1];
    $nm_poli_tahun_kulkel[] = $row[2];
    $tahunan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_tahun_mata = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%Y') 
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='MAT' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEAR(tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_tahun_mata[] = $row[0];
    $kdpolim_tahun_mata[] = $row[1];
    $nm_poli_tahun_mata[] = $row[2];
    $tahunan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_tahun_obgyn = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%Y') 
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='OBG' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEAR(tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_tahun_obgyn[] = $row[0];
    $kdpolim_tahun_obgyn[] = $row[1];
    $nm_poli_tahun_obgyn[] = $row[2];
    $tahunan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_tahun_ortopedi = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%Y') 
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='ORT' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEAR(tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_tahun_ortopedi[] = $row[0];
    $kdpolim_tahun_ortopedi[] = $row[1];
    $nm_poli_tahun_ortopedi[] = $row[2];
    $tahunan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_tahun_paru = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%Y') 
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='PAR' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEAR(tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_tahun_paru[] = $row[0];
    $kdpolim_tahun_paru[] = $row[1];
    $nm_poli_tahun_paru[] = $row[2];
    $tahunan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_tahun_int = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%Y') 
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='INT' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEAR(tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_tahun_int[] = $row[0];
    $kdpolim_tahun_int[] = $row[1];
    $nm_poli_tahun_int[] = $row[2];
    $tahunan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_tahun_saraf = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%Y') 
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='SAR' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEAR(tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_tahun_saraf[] = $row[0];
    $kdpolim_tahun_saraf[] = $row[1];
    $nm_poli_tahun_saraf[] = $row[2];
    $tahunan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_tahun_tht = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%Y') 
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='THT' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEAR(tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_tahun_tht[] = $row[0];
    $kdpolim_tahun_tht[] = $row[1];
    $nm_poli_tahun_tht[] = $row[2];
    $tahunan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_tahun_uro = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%Y') 
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='URO' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEAR(tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_tahun_uro[] = $row[0];
    $kdpolim_tahun_uro[] = $row[1];
    $nm_poli_tahun_uro[] = $row[2];
    $tahunan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_tahun_vct = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%Y') 
  AS Tahun_bulan_Tgl
  FROM reg_periksa,poliklinik
  WHERE status_lanjut='Ralan' AND poliklinik.kd_poli='VCT' AND reg_periksa.tgl_registrasi
  BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
  AND poliklinik.kd_poli=reg_periksa.kd_poli  
  GROUP BY YEAR(tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_tahun_vct[] = $row[0];
    $kdpolim_tahun_vct[] = $row[1];
    $nm_poli_tahun_vct[] = $row[2];
    $tahunan[] = $row[3];
  }
  ?>

  <!-- Kunjungan Dokter Sekarang-->
  <?php
  $jumlah_pasien_now_dokter = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis) AS Jumlah_pasien,reg_periksa.kd_dokter,dokter.nm_dokter
,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi 
                                    AS Tahun_bulan_Tgl
                                    FROM reg_periksa,poliklinik,dokter
                                    WHERE status_lanjut='Ralan' AND reg_periksa.tgl_registrasi = curdate() 
                                    and poliklinik.kd_poli=reg_periksa.kd_poli AND reg_periksa.kd_dokter=dokter.kd_dokter
                                    GROUP BY dokter.kd_dokter");

  while ($row = mysqli_fetch_array($result)) {
    $jumlah_pasien_now_dokter[] = $row[0];
    $kddokter_now[] = $row[1];
    $nmdokter_now[] = $row[2];
    $kdpoli_now[] = $row[3];
    $nm_poli_now[] = $row[4];
    $dokternow[] = $row[5];
  }
  ?>

  <!-- KUNJUNGAN DOKTER BULANAN-->
  <?php
  $jumlah_pasien_dokter_bulanan = array();
  $result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis)AS Jumlah_pasien,reg_periksa.kd_dokter,dokter.nm_dokter
  ,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi 
                                      AS Tahun_bulan_Tgl
                                      FROM reg_periksa,poliklinik,dokter
                                      WHERE status_lanjut='Ralan' AND dokter.kd_dokter='11020015321076' AND reg_periksa.tgl_registrasi 
                                      BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "' 
                                      and poliklinik.kd_poli=reg_periksa.kd_poli AND reg_periksa.kd_dokter=dokter.kd_dokter
                                      GROUP BY reg_periksa.tgl_registrasi;");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_pasien_dokter_bulanan[] = $row[0];
    $kddokter_bulanan[] = $row[1];
    $nmdokter_bulanan[] = $row[2];
    $kdpoli_bulanana[] = $row[3];
    $nm_poli_bulanan[] = $row[4];
    $tgldokterbulanan[] = $row[5];
  }
  ?>

  <!-- chart cara bayar -->
  <!-- QWERY JUMLAH PASIEN RANAP -->
  <?php
  $jumlah_pasien_ranap = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.stts,reg_periksa.status_lanjut,DATE_FORMAT(tgl_registrasi,'%M'),COUNT(reg_periksa.no_rawat) AS Jumlah 
  FROM reg_periksa
  WHERE stts='Dirawat' AND status_lanjut='Ranap'  ");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_pasien_ranap[] = $row[0];
    $status_ranap[] = $row[1];
    $tgl_bulan_ranap_bro[] = $row[2];
    $jumlah_ranap[] = $row[3];
  }
  ?>

  <!-- /chart cara bayar BPJS-->
  <?php
  $jumlah_pasien_bayar = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.kd_pj,COUNT(reg_periksa.kd_pj)
  FROM reg_periksa WHERE status_lanjut='Ralan' AND kd_pj='BPJ' and tgl_registrasi=CURDATE()");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_pasien_bayar[] = $row[0];
    $status_lanjut[] = $row[1];
    $kd_pj[] = $row[2];
    $jumlah_bayar_BPJS[] = $row[3];
  }
  ?>
  <!-- /chart cara bayar BPJS KETENAGAKERJAAN-->
  <?php
  $jumlah_pasien_bayar = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.kd_pj,COUNT(reg_periksa.kd_pj)
  FROM reg_periksa WHERE status_lanjut='Ralan' AND kd_pj='A88' and tgl_registrasi=CURDATE()");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_pasien_bayar[] = $row[0];
    $status_lanjut[] = $row[1];
    $kd_pj[] = $row[2];
    $jumlah_bayar_BPJS_KETENAGAKERJAAN[] = $row[3];
  }
  ?>
  <!-- /chart cara bayar MANDIRI INHEALTH-->
  <?php
  $jumlah_pasien_bayar = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.kd_pj,COUNT(reg_periksa.kd_pj)
  FROM reg_periksa WHERE status_lanjut='Ralan' AND kd_pj='INH' and tgl_registrasi=CURDATE()");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_pasien_bayar[] = $row[0];
    $status_lanjut[] = $row[1];
    $kd_pj[] = $row[2];
    $jumlah_bayar_MANDIRI_INHEALTH[] = $row[3];
  }
  ?>
  <!-- /chart cara bayar PT PLN ADMEDIKA-->
  <?php
  $jumlah_pasien_bayar = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.kd_pj,COUNT(reg_periksa.kd_pj)
  FROM reg_periksa WHERE status_lanjut='Ralan' AND kd_pj='A30' and tgl_registrasi=CURDATE()");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_pasien_bayar[] = $row[0];
    $status_lanjut[] = $row[1];
    $kd_pj[] = $row[2];
    $jumlah_bayar_PLN[] = $row[3];
  }
  ?>
  <!-- /chart cara bayar TELKOM-->
  <?php
  $jumlah_pasien_bayar = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.kd_pj,COUNT(reg_periksa.kd_pj)
  FROM reg_periksa WHERE status_lanjut='Ralan' AND kd_pj='A41' and tgl_registrasi=CURDATE()");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_pasien_bayar[] = $row[0];
    $status_lanjut[] = $row[1];
    $kd_pj[] = $row[2];
    $jumlah_bayar_TELKOM[] = $row[3];
  }
  ?>
  <!-- /chart cara bayar Umum-->
  <?php
  $jumlah_pasien_bayar = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.kd_pj,COUNT(reg_periksa.kd_pj)
  FROM reg_periksa WHERE status_lanjut='Ralan' AND kd_pj='A09' and tgl_registrasi=CURDATE()");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_pasien_bayar[] = $row[0];
    $status_lanjut[] = $row[1];
    $kd_pj[] = $row[2];
    $jumlah_bayar_umum[] = $row[3];
  }
  ?>

  <!-- /chart cara bayar LAINNya-->
  <?php
  $jumlah_pasien_bayar = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.kd_pj,COUNT(reg_periksa.kd_pj)
  FROM reg_periksa WHERE  status_lanjut='Ralan' AND kd_pj='A86' and tgl_registrasi=CURDATE()");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_pasien_bayar[] = $row[0];
    $status_lanjut[] = $row[1];
    $kd_pj[] = $row[2];
    $jumlah_bayar_lain[] = $row[3];
  }
  ?>

  <!-- /chart jenis pasien TNI AU-->
  <?php
  $jumlah_jenis_pasien = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,reg_periksa.status_bayar,
  pasien_tni.golongan_tni,reg_periksa.status_lanjut,reg_periksa.stts,COUNT(reg_periksa.no_rkm_medis)AS Jumlah_pasien 
  FROM reg_periksa,pasien_tni
  WHERE reg_periksa.status_lanjut='Ralan' AND reg_periksa.stts='Sudah' AND pasien_tni.no_rkm_medis=reg_periksa.no_rkm_medis
  AND reg_periksa.tgl_registrasi=CURDATE() AND pasien_tni.golongan_tni='1'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_jenis_pasien[] = $row[0];
    $status_bayar[] = $row[1];
    $golongan[] = $row[2];
    $statu_lanjut[] = $row[3];
    $stts[] = $row[4];
    $jumlah_gol_tni_au[] = $row[5];
  }
  ?>

  <!-- /chart jenis pasien TNI AL-->
  <?php
  $jumlah_jenis_pasien = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,reg_periksa.status_bayar,
  pasien_tni.golongan_tni,reg_periksa.status_lanjut,reg_periksa.stts,COUNT(reg_periksa.no_rkm_medis)AS Jumlah_pasien 
  FROM reg_periksa,pasien_tni
  WHERE reg_periksa.status_lanjut='Ralan' AND reg_periksa.stts='Sudah' AND pasien_tni.no_rkm_medis=reg_periksa.no_rkm_medis
  AND reg_periksa.tgl_registrasi=CURDATE() AND pasien_tni.golongan_tni='2'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_jenis_pasien[] = $row[0];
    $status_bayar[] = $row[1];
    $golongan[] = $row[2];
    $statu_lanjut[] = $row[3];
    $stts[] = $row[4];
    $jumlah_gol_tni_al[] = $row[5];
  }
  ?>

  <!-- /chart jenis pasien TNI AD-->
  <?php
  $jumlah_jenis_pasien = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,reg_periksa.status_bayar,
  pasien_tni.golongan_tni,reg_periksa.status_lanjut,reg_periksa.stts,COUNT(reg_periksa.no_rkm_medis)AS Jumlah_pasien 
  FROM reg_periksa,pasien_tni
  WHERE reg_periksa.status_lanjut='Ralan' AND reg_periksa.stts='Sudah' AND pasien_tni.no_rkm_medis=reg_periksa.no_rkm_medis
  AND reg_periksa.tgl_registrasi=CURDATE() AND pasien_tni.golongan_tni='3'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_jenis_pasien[] = $row[0];
    $status_bayar[] = $row[1];
    $golongan[] = $row[2];
    $statu_lanjut[] = $row[3];
    $stts[] = $row[4];
    $jumlah_gol_tni_ad[] = $row[5];
  }
  ?>

  <!-- /chart jenis pasien PURNAWIRAWAN-->
  <?php
  $jumlah_jenis_pasien = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,reg_periksa.status_bayar,
  pasien_tni.golongan_tni,reg_periksa.status_lanjut,reg_periksa.stts,COUNT(reg_periksa.no_rkm_medis)AS Jumlah_pasien 
  FROM reg_periksa,pasien_tni
  WHERE reg_periksa.status_lanjut='Ralan' AND reg_periksa.stts='Sudah' AND pasien_tni.no_rkm_medis=reg_periksa.no_rkm_medis
  AND reg_periksa.tgl_registrasi=CURDATE() AND pasien_tni.golongan_tni='4'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_jenis_pasien[] = $row[0];
    $status_bayar[] = $row[1];
    $golongan[] = $row[2];
    $statu_lanjut[] = $row[3];
    $stts[] = $row[4];
    $jumlah_gol_tni_purnawirawan[] = $row[5];
  }
  ?>

  <!-- /chart jenis pasien PNS TNI AD-->
  <?php
  $jumlah_jenis_pasien = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,reg_periksa.status_bayar,
  pasien_tni.golongan_tni,reg_periksa.status_lanjut,reg_periksa.stts,COUNT(reg_periksa.no_rkm_medis)AS Jumlah_pasien 
  FROM reg_periksa,pasien_tni
  WHERE reg_periksa.status_lanjut='Ralan' AND reg_periksa.stts='Sudah' AND pasien_tni.no_rkm_medis=reg_periksa.no_rkm_medis
  AND reg_periksa.tgl_registrasi=CURDATE() AND pasien_tni.golongan_tni='5'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_jenis_pasien[] = $row[0];
    $status_bayar[] = $row[1];
    $golongan[] = $row[2];
    $statu_lanjut[] = $row[3];
    $stts[] = $row[4];
    $jumlah_psn_tni_ad[] = $row[5];
  }
  ?>

  <!-- /chart jenis pasien PNS TNI AL-->
  <?php
  $jumlah_jenis_pasien = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,reg_periksa.status_bayar,
  pasien_tni.golongan_tni,reg_periksa.status_lanjut,reg_periksa.stts,COUNT(reg_periksa.no_rkm_medis)AS Jumlah_pasien 
  FROM reg_periksa,pasien_tni
  WHERE reg_periksa.status_lanjut='Ralan' AND reg_periksa.stts='Sudah' AND pasien_tni.no_rkm_medis=reg_periksa.no_rkm_medis
  AND reg_periksa.tgl_registrasi=CURDATE() AND pasien_tni.golongan_tni='6'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_jenis_pasien[] = $row[0];
    $status_bayar[] = $row[1];
    $golongan[] = $row[2];
    $statu_lanjut[] = $row[3];
    $stts[] = $row[4];
    $jumlah_psn_tni_al[] = $row[5];
  }
  ?>

  <!-- /chart jenis pasien  PNS TNI AU-->
  <?php
  $jumlah_jenis_pasien = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,reg_periksa.status_bayar,
  pasien_tni.golongan_tni,reg_periksa.status_lanjut,reg_periksa.stts,COUNT(reg_periksa.no_rkm_medis)AS Jumlah_pasien 
  FROM reg_periksa,pasien_tni
  WHERE reg_periksa.status_lanjut='Ralan' AND reg_periksa.stts='Sudah' AND pasien_tni.no_rkm_medis=reg_periksa.no_rkm_medis
  AND reg_periksa.tgl_registrasi=CURDATE() AND pasien_tni.golongan_tni='7'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_jenis_pasien[] = $row[0];
    $status_bayar[] = $row[1];
    $golongan[] = $row[2];
    $statu_lanjut[] = $row[3];
    $stts[] = $row[4];
    $jumlah_psn_tni_au[] = $row[5];
  }
  ?>

  <!-- /chart jenis pasien KELUARGA PS TNI AD-->
  <?php
  $jumlah_jenis_pasien = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,reg_periksa.status_bayar,
  pasien_tni.golongan_tni,reg_periksa.status_lanjut,reg_periksa.stts,COUNT(reg_periksa.no_rkm_medis)AS Jumlah_pasien 
  FROM reg_periksa,pasien_tni
  WHERE reg_periksa.status_lanjut='Ralan' AND reg_periksa.stts='Sudah' AND pasien_tni.no_rkm_medis=reg_periksa.no_rkm_medis
  AND reg_periksa.tgl_registrasi=CURDATE() AND pasien_tni.golongan_tni='8'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_jenis_pasien[] = $row[0];
    $status_bayar[] = $row[1];
    $golongan[] = $row[2];
    $statu_lanjut[] = $row[3];
    $stts[] = $row[4];
    $jumlah_keluarga_tni_ad[] = $row[5];
  }
  ?>

  <!-- /chart jenis pasien KELUARGA TNI Al-->
  <?php
  $jumlah_jenis_pasien = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,reg_periksa.status_bayar,
  pasien_tni.golongan_tni,reg_periksa.status_lanjut,reg_periksa.stts,COUNT(reg_periksa.no_rkm_medis)AS Jumlah_pasien 
  FROM reg_periksa,pasien_tni
  WHERE reg_periksa.status_lanjut='Ralan' AND reg_periksa.stts='Sudah' AND pasien_tni.no_rkm_medis=reg_periksa.no_rkm_medis
  AND reg_periksa.tgl_registrasi=CURDATE() AND pasien_tni.golongan_tni='9'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_jenis_pasien[] = $row[0];
    $status_bayar[] = $row[1];
    $golongan[] = $row[2];
    $statu_lanjut[] = $row[3];
    $stts[] = $row[4];
    $jumlah_keluarga_tni_al[] = $row[5];
  }
  ?>

  <!-- /chart jenis pasien KELUARGA TNI AU-->
  <?php
  $jumlah_jenis_pasien = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,reg_periksa.status_bayar,
  pasien_tni.golongan_tni,reg_periksa.status_lanjut,reg_periksa.stts,COUNT(reg_periksa.no_rkm_medis)AS Jumlah_pasien 
  FROM reg_periksa,pasien_tni
  WHERE reg_periksa.status_lanjut='Ralan' AND reg_periksa.stts='Sudah' AND pasien_tni.no_rkm_medis=reg_periksa.no_rkm_medis
  AND reg_periksa.tgl_registrasi=CURDATE() AND pasien_tni.golongan_tni='10'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_jenis_pasien[] = $row[0];
    $status_bayar[] = $row[1];
    $golongan[] = $row[2];
    $statu_lanjut[] = $row[3];
    $stts[] = $row[4];
    $jumlah_keluarga_tni_au[] = $row[5];
  }
  ?>

  <!-- /chart jenis pasien KELUARGA PNS TNI AD-->
  <?php
  $jumlah_jenis_pasien = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,reg_periksa.status_bayar,
  pasien_tni.golongan_tni,reg_periksa.status_lanjut,reg_periksa.stts,COUNT(reg_periksa.no_rkm_medis)AS Jumlah_pasien 
  FROM reg_periksa,pasien_tni
  WHERE reg_periksa.status_lanjut='Ralan' AND reg_periksa.stts='Sudah' AND pasien_tni.no_rkm_medis=reg_periksa.no_rkm_medis
  AND reg_periksa.tgl_registrasi=CURDATE() AND pasien_tni.golongan_tni='11'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_jenis_pasien[] = $row[0];
    $status_bayar[] = $row[1];
    $golongan[] = $row[2];
    $statu_lanjut[] = $row[3];
    $stts[] = $row[4];
    $jumlah_keluarga_pns_tni_ad[] = $row[5];
  }
  ?>

  <!-- /chart jenis pasien KELUARGA PNS TNI AU-->
  <?php
  $jumlah_jenis_pasien = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,reg_periksa.status_bayar,
  pasien_tni.golongan_tni,reg_periksa.status_lanjut,reg_periksa.stts,COUNT(reg_periksa.no_rkm_medis)AS Jumlah_pasien 
  FROM reg_periksa,pasien_tni
  WHERE reg_periksa.status_lanjut='Ralan' AND reg_periksa.stts='Sudah' AND pasien_tni.no_rkm_medis=reg_periksa.no_rkm_medis
  AND reg_periksa.tgl_registrasi=CURDATE() AND pasien_tni.golongan_tni='12'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_jenis_pasien[] = $row[0];
    $status_bayar[] = $row[1];
    $golongan[] = $row[2];
    $statu_lanjut[] = $row[3];
    $stts[] = $row[4];
    $jumlah_keluarga_pns_tni_au[] = $row[5];
  }
  ?>

  <!-- /chart jenis pasien KELUARGA PNS TNI AL-->
  <?php
  $jumlah_jenis_pasien = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,reg_periksa.status_bayar,
  pasien_tni.golongan_tni,reg_periksa.status_lanjut,reg_periksa.stts,COUNT(reg_periksa.no_rkm_medis)AS Jumlah_pasien 
  FROM reg_periksa,pasien_tni
  WHERE reg_periksa.status_lanjut='Ralan' AND reg_periksa.stts='Sudah' AND pasien_tni.no_rkm_medis=reg_periksa.no_rkm_medis
  AND reg_periksa.tgl_registrasi=CURDATE() AND pasien_tni.golongan_tni='12'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_jenis_pasien[] = $row[0];
    $status_bayar[] = $row[1];
    $golongan[] = $row[2];
    $statu_lanjut[] = $row[3];
    $stts[] = $row[4];
    $jumlah_keluarga_pns_tni_al[] = $row[5];
  }
  ?>


  <!-- qwery jenis pasien baru poli -->
  <?php
  $jumlah_jenis_pasien_poli_anak = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
  COUNT(reg_periksa.kd_poli) jumlah 
  FROM reg_periksa, poliklinik 
  WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Baru' 
  and reg_periksa.status_lanjut ='ralan'
  and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='ANA' 
  and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_poli[] = $row[0];
    $nm_jenis_pasien_poli_anak[] = $row[1];
    $stts_daftar_pasien_poli_anak[] = $row[2];
    $Jumlah_jenis_pasien_poli_anak[] = $row[3];
  }
  ?>

  <?php
  $jumlah_jenis_pasien_poli_bedah = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
  COUNT(reg_periksa.kd_poli) jumlah 
  FROM reg_periksa, poliklinik 
  WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Baru' 
  and reg_periksa.status_lanjut ='ralan'
  and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='BED' 
  and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_poli[] = $row[0];
    $nm_jenis_pasien_poli_bedah[] = $row[1];
    $stts_daftar_pasien_poli_bedah[] = $row[2];
    $Jumlah_jenis_pasien_poli_bedah[] = $row[3];
  }
  ?>

  <?php
  $jumlah_jenis_pasien_poli_bedah_anak = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
  COUNT(reg_periksa.kd_poli) jumlah 
  FROM reg_periksa, poliklinik 
  WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Baru' 
  and reg_periksa.status_lanjut ='ralan'
  and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='BDA' 
  and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_poli[] = $row[0];
    $nm_jenis_pasien_poli_bedah_anak[] = $row[1];
    $stts_daftar_pasien_poli_bedah_anak[] = $row[2];
    $Jumlah_jenis_pasien_poli_bedah_anak[] = $row[3];
  }
  ?>

  <?php
  $jumlah_jenis_pasien_poli_bedah_digestif = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Baru' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='018' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_poli[] = $row[0];
    $nm_jenis_pasien_poli_bedah_digestif[] = $row[1];
    $stts_daftar_pasien_poli_bedah_digestif[] = $row[2];
    $Jumlah_jenis_pasien_poli_bedah_digestif[] = $row[3];
  }
  ?>


  <?php
  $Jumlah_jenis_pasien_poli_bedah_mulut = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Baru' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='BDM' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_poli[] = $row[0];
    $nm_jenis_pasien_poli_bedah_mulut[] = $row[1];
    $stts_daftar_pasien_poli_bedah_mulut[] = $row[2];
    $Jumlah_jenis_pasien_poli_bedah_mulut[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_bedah_ongkologi = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Baru' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='017' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_poli[] = $row[0];
    $nm_jenis_pasien_poli_bedah_ongkologi[] = $row[1];
    $stts_daftar_pasien_poli_bedah_ongkologi[] = $row[2];
    $Jumlah_jenis_pasien_poli_bedah_ongkologi[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_bedah_saraf = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Baru' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='BSY' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_poli[] = $row[0];
    $nm_jenis_pasien_poli_bedah_saraf[] = $row[1];
    $stts_daftar_pasien_poli_bedah_saraf[] = $row[2];
    $Jumlah_jenis_pasien_poli_bedah_saraf[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_bedah_vascular = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Baru' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='U0036' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_poli[] = $row[0];
    $nm_jenis_pasien_poli_bedah_vascular[] = $row[1];
    $stts_daftar_pasien_poli_bedah_vascular[] = $row[2];
    $Jumlah_jenis_pasien_poli_bedah_vascular[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_gigi = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Baru' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='U0041' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_poli[] = $row[0];
    $nm_jenis_pasien_poli_gigi[] = $row[1];
    $stts_daftar_pasien_poli_gigi[] = $row[2];
    $Jumlah_jenis_pasien_poli_gigi[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_ginjal_hipertensi = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Baru' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='U0035' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_poli[] = $row[0];
    $nm_jenis_pasien_poli_ginjal_hipertensi[] = $row[1];
    $stts_daftar_pasien_poli_ginjal_hipertensi[] = $row[2];
    $Jumlah_jenis_pasien_poli_ginjal_hipertensi[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_gizi = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Baru' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='GIZ' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_poli[] = $row[0];
    $nm_jenis_pasien_poli_gizi[] = $row[1];
    $stts_daftar_pasien_poli_gizi[] = $row[2];
    $Jumlah_jenis_pasien_poli_gizi[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_hdl = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Baru' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='HDL' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_poli[] = $row[0];
    $nm_jenis_pasien_poli_hdl[] = $row[1];
    $stts_daftar_pasien_poli_hdl[] = $row[2];
    $Jumlah_jenis_pasien_poli_hdl[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_jantung = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Baru' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='020' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_poli[] = $row[0];
    $nm_jenis_pasien_poli_jantung[] = $row[1];
    $stts_daftar_pasien_poli_jantung[] = $row[2];
    $Jumlah_jenis_pasien_poli_jantung[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_jiwa = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Baru' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='JIW' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_poli[] = $row[0];
    $nm_jenis_pasien_poli_jiwa[] = $row[1];
    $stts_daftar_pasien_poli_jiwa[] = $row[2];
    $Jumlah_jenis_pasien_poli_jiwa[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_konvervasi_gigi = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Baru' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='GND' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_poli[] = $row[0];
    $nm_jenis_pasien_poli_konvervasi_gigi[] = $row[1];
    $stts_daftar_pasien_poli_konvervasi_gigi[] = $row[2];
    $Jumlah_jenis_pasien_poli_konvervasi_gigi[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_klt = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Baru' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='KLT' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_poli[] = $row[0];
    $nm_jenis_pasien_poli_klt[] = $row[1];
    $stts_daftar_pasien_poli_klt[] = $row[2];
    $Jumlah_jenis_pasien_poli_klt[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_mata = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Baru' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='MAT' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_poli[] = $row[0];
    $nm_jenis_pasien_poli_mata[] = $row[1];
    $stts_daftar_pasien_poli_mata[] = $row[2];
    $Jumlah_jenis_pasien_poli_mata[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_mcu = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Baru' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='P20' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_poli[] = $row[0];
    $nm_jenis_pasien_poli_mcu[] = $row[1];
    $stts_daftar_pasien_poli_mcu[] = $row[2];
    $Jumlah_jenis_pasien_poli_mcu[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_obgyn = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Baru' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='OBG' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_poli[] = $row[0];
    $nm_jenis_pasien_poli_obgyn[] = $row[1];
    $stts_daftar_pasien_poli_obgyn[] = $row[2];
    $Jumlah_jenis_pasien_poli_obgyn[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_ortopedi = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Baru' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='ORT' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_poli[] = $row[0];
    $nm_jenis_pasien_poli_ortopedi[] = $row[1];
    $stts_daftar_pasien_poli_ortopedi[] = $row[2];
    $Jumlah_jenis_pasien_poli_ortopedi[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_paru = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Baru' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='PAR' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_poli[] = $row[0];
    $nm_jenis_pasien_poli_paru[] = $row[1];
    $stts_daftar_pasien_poli_paru[] = $row[2];
    $Jumlah_jenis_pasien_poli_paru[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_int = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Baru' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='INT' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_poli[] = $row[0];
    $nm_jenis_pasien_poli_int[] = $row[1];
    $stts_daftar_pasien_poli_int[] = $row[2];
    $Jumlah_jenis_pasien_poli_int[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_rehabmedik = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Baru' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='IRM' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_poli[] = $row[0];
    $nm_jenis_pasien_poli_rehabmedik[] = $row[1];
    $stts_daftar_pasien_poli_rehabmedik[] = $row[2];
    $Jumlah_jenis_pasien_poli_rehabmedik[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_saraf = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Baru' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='SAR' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_poli[] = $row[0];
    $nm_jenis_pasien_poli_saraf[] = $row[1];
    $stts_daftar_pasien_poli_saraf[] = $row[2];
    $Jumlah_jenis_pasien_poli_saraf[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_tht = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Baru' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='THT' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_poli[] = $row[0];
    $nm_jenis_pasien_poli_tht[] = $row[1];
    $stts_daftar_pasien_poli_tht[] = $row[2];
    $Jumlah_jenis_pasien_poli_tht[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_uro = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Baru' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='URO' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_poli[] = $row[0];
    $nm_jenis_pasien_poli_uro[] = $row[1];
    $stts_daftar_pasien_poli_uro[] = $row[2];
    $Jumlah_jenis_pasien_poli_uro[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_vct = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Baru' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='U0029' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_poli[] = $row[0];
    $nm_jenis_pasien_poli_vct[] = $row[1];
    $stts_daftar_pasien_poli_vct[] = $row[2];
    $Jumlah_jenis_pasien_poli_vct[] = $row[3];
  }
  ?>

  <!-- qwery jenis pasien lama poli -->
  <?php
  $jumlah_jenis_pasien_poli_lama_anak = array();
  $result = mysqli_query($koneksi, " SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts_daftar='Lama' and reg_periksa.stts in ('Belum','Sudah','Batal','Berkas Diterima','Dirujuk','Meninggal','Dirawat','Pulang Paksa')
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='ANA' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_lama_poli[] = $row[0];
    $nm_jenis_pasien_poli_lama_anak[] = $row[1];
    $stts_daftar_pasien_poli_lama_anak[] = $row[2];
    $Jumlah_jenis_pasien_poli_lama_anak[] = $row[3];
  }
  ?>

  <?php
  $jumlah_jenis_pasien_poli_lama_bedah = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Lama' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='BED' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_lama_poli[] = $row[0];
    $nm_jenis_pasien_poli_lama_bedah[] = $row[1];
    $stts_daftar_pasien_poli_lama_bedah[] = $row[2];
    $Jumlah_jenis_pasien_poli_lama_bedah[] = $row[3];
  }
  ?>

  <?php
  $jumlah_jenis_pasien_poli_lama_bedah_anak = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Lama' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='BDA' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_lama_poli[] = $row[0];
    $nm_jenis_pasien_poli_lama_bedah_anak[] = $row[1];
    $stts_daftar_pasien_poli_lama_bedah_anak[] = $row[2];
    $Jumlah_jenis_pasien_poli_lama_bedah_anak[] = $row[3];
  }
  ?>

  <?php
  $jumlah_jenis_pasien_poli_lama_bedah_digestif = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Lama' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='018' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_lama_poli[] = $row[0];
    $nm_jenis_pasien_poli_lama_bedah_digestif[] = $row[1];
    $stts_daftar_pasien_poli_lama_bedah_digestif[] = $row[2];
    $Jumlah_jenis_pasien_poli_lama_bedah_digestif[] = $row[3];
  }
  ?>


  <?php
  $Jumlah_jenis_pasien_poli_lama_bedah_mulut = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Lama' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='BDM' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_lama_poli[] = $row[0];
    $nm_jenis_pasien_poli_lama_bedah_mulut[] = $row[1];
    $stts_daftar_pasien_poli_lama_bedah_mulut[] = $row[2];
    $Jumlah_jenis_pasien_poli_lama_bedah_mulut[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_lama_bedah_ongkologi = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Lama' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='017' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_lama_poli[] = $row[0];
    $nm_jenis_pasien_poli_lama_bedah_ongkologi[] = $row[1];
    $stts_daftar_pasien_poli_lama_bedah_ongkologi[] = $row[2];
    $Jumlah_jenis_pasien_poli_lama_bedah_ongkologi[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_lama_bedah_saraf = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Lama' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='BSY' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_lama_poli[] = $row[0];
    $nm_jenis_pasien_lama_poli_bedah_saraf[] = $row[1];
    $stts_daftar_pasien_poli_lama_bedah_saraf[] = $row[2];
    $Jumlah_jenis_pasien_poli_lama_bedah_saraf[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_lama_bedah_vascular = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Lama' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='U0036' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_lama_poli[] = $row[0];
    $nm_jenis_pasien_poli_lama_bedah_vascular[] = $row[1];
    $stts_daftar_pasien_poli_lama_bedah_vascular[] = $row[2];
    $Jumlah_jenis_pasien_poli_lama_bedah_vascular[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_lama_gigi = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Lama' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='U0041' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_lama_poli[] = $row[0];
    $nm_jenis_pasien_poli_lama_gigi[] = $row[1];
    $stts_daftar_pasien_poli_lama_gigi[] = $row[2];
    $Jumlah_jenis_pasien_poli_lama_gigi[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_lama_ginjal_hipertensi = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Lama' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='U0035' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_lama_poli[] = $row[0];
    $nm_jenis_pasien_poli_lama_ginjal_hipertensi[] = $row[1];
    $stts_daftar_pasien_poli_lama_ginjal_hipertensi[] = $row[2];
    $Jumlah_jenis_pasien_poli_lama_ginjal_hipertensi[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_lama_gizi = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Lama' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='GIZ' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_lama_poli[] = $row[0];
    $nm_jenis_pasien_poli_lama_gizi[] = $row[1];
    $stts_daftar_pasien_poli_lama_gizi[] = $row[2];
    $Jumlah_jenis_pasien_poli_lama_gizi[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_lama_hdl = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Lama' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='HDL' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_lama_poli[] = $row[0];
    $nm_jenis_pasien_poli_lama_hdl[] = $row[1];
    $stts_daftar_pasien_poli_lama_hdl[] = $row[2];
    $Jumlah_jenis_pasien_poli_Lama_hdl[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_lama_jantung = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Lama' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='020' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_lama_poli[] = $row[0];
    $nm_jenis_pasien_poli_lama_jantung[] = $row[1];
    $stts_daftar_pasien_poli_lama_jantung[] = $row[2];
    $Jumlah_jenis_pasien_poli_lama_jantung[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_lama_jiwa = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Lama' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='JIW' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_lama_poli[] = $row[0];
    $nm_jenis_pasien_poli_lama_jiwa[] = $row[1];
    $stts_daftar_pasien_poli_lama_jiwa[] = $row[2];
    $Jumlah_jenis_pasien_poli_lama_jiwa[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_lama_konvervasi_gigi = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Lama' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='GND' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_lama_poli[] = $row[0];
    $nm_jenis_pasien_poli_lama_konvervasi_gigi[] = $row[1];
    $stts_daftar_pasien_poli_lama_konvervasi_gigi[] = $row[2];
    $Jumlah_jenis_pasien_poli_lama_konvervasi_gigi[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_lama_klt = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Lama' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='KLT' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_lama_poli[] = $row[0];
    $nm_jenis_pasien_poli_lama_klt[] = $row[1];
    $stts_daftar_pasien_poli_lama_klt[] = $row[2];
    $Jumlah_jenis_pasien_poli_lama_klt[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_lama_mata = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Lama' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='MAT' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_lama_poli[] = $row[0];
    $nm_jenis_pasien_poli_lama_mata[] = $row[1];
    $stts_daftar_pasien_poli_lama_mata[] = $row[2];
    $Jumlah_jenis_pasien_poli_lama_mata[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_lama_mcu = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Baru' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='P20' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_lama_poli[] = $row[0];
    $nm_jenis_pasien_poli_lama_mcu[] = $row[1];
    $stts_daftar_pasien_poli_lama_mcu[] = $row[2];
    $Jumlah_jenis_pasien_poli_lama_mcu[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_lama_obgyn = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Lama' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='OBG' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_lama_poli[] = $row[0];
    $nm_jenis_pasien_poli_lama_obgyn[] = $row[1];
    $stts_daftar_pasien_poli_lama_obgyn[] = $row[2];
    $Jumlah_jenis_pasien_poli_lama_obgyn[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_lama_ortopedi = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Lama' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='ORT' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_lama_poli[] = $row[0];
    $nm_jenis_pasien_poli_lama_ortopedi[] = $row[1];
    $stts_daftar_pasien_poli_lama_ortopedi[] = $row[2];
    $Jumlah_jenis_pasien_poli_lama_ortopedi[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_lama_paru = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Lama' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='PAR' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_lama_poli[] = $row[0];
    $nm_jenis_pasien_poli_lama_paru[] = $row[1];
    $stts_daftar_pasien_poli_lama_paru[] = $row[2];
    $Jumlah_jenis_pasien_poli_lama_paru[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_lama_int = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Lama' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='INT' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_lama_poli[] = $row[0];
    $nm_jenis_pasien_poli_lama_int[] = $row[1];
    $stts_daftar_pasien_poli_lama_int[] = $row[2];
    $Jumlah_jenis_pasien_poli_lama_int[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_lama_rehabmedik = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Lama' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='IRM' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_lama_poli[] = $row[0];
    $nm_jenis_pasien_poli_Lama_rehabmedik[] = $row[1];
    $stts_daftar_pasien_poli_lama_rehabmedik[] = $row[2];
    $Jumlah_jenis_pasien_poli_Lama_rehabmedik[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_lama_saraf = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Lama' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='SAR' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_lama_poli[] = $row[0];
    $nm_jenis_pasien_poli_lama_saraf[] = $row[1];
    $stts_daftar_pasien_poli_lama_saraf[] = $row[2];
    $Jumlah_jenis_pasien_poli_lama_saraf[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_lama_tht = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Lama' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='THT' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_lama_poli[] = $row[0];
    $nm_jenis_pasien_poli_lama_tht[] = $row[1];
    $stts_daftar_pasien_poli_lama_tht[] = $row[2];
    $Jumlah_jenis_pasien_poli_lama_tht[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_lama_uro = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Lama' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='URO' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_lama_poli[] = $row[0];
    $nm_jenis_pasien_poli_lama_uro[] = $row[1];
    $stts_daftar_pasien_poli_lama_uro[] = $row[2];
    $Jumlah_jenis_pasien_poli_lama_uro[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_lama_vct = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.stts_daftar='Lama' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='U0029' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_lama_poli[] = $row[0];
    $nm_jenis_pasien_poli_lama_vct[] = $row[1];
    $stts_daftar_pasien_poli_lama_vct[] = $row[2];
    $Jumlah_jenis_pasien_poli_lama_vct[] = $row[3];
  }
  ?>

  <!-- qwery jenis pasien Batal -->
  <?php
  $jumlah_jenis_pasien_poli_batal_anak = array();
  $result = mysqli_query($koneksi, " SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Batal' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='ANA' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_batal_poli[] = $row[0];
    $nm_jenis_pasien_poli_batal_anak[] = $row[1];
    $stts_daftar_pasien_poli_batal_anak[] = $row[2];
    $Jumlah_jenis_pasien_poli_batal_anak[] = $row[3];
  }
  ?>

  <?php
  $jumlah_jenis_pasien_poli_batal_bedah = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Batal' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='BED' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_batal_poli[] = $row[0];
    $nm_jenis_pasien_poli_batal_bedah[] = $row[1];
    $stts_daftar_pasien_poli_batal_bedah[] = $row[2];
    $Jumlah_jenis_pasien_poli_batal_bedah[] = $row[3];
  }
  ?>

  <?php
  $jumlah_jenis_pasien_poli_batal_bedah_anak = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Batal' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='BDA' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_batal_poli[] = $row[0];
    $nm_jenis_pasien_poli_batal_bedah_anak[] = $row[1];
    $stts_daftar_pasien_poli_batal_bedah_anak[] = $row[2];
    $Jumlah_jenis_pasien_poli_batal_bedah_anak[] = $row[3];
  }
  ?>

  <?php
  $jumlah_jenis_pasien_poli_batal_bedah_digestif = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Batal' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='018' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_batal_poli[] = $row[0];
    $nm_jenis_pasien_poli_batal_bedah_digestif[] = $row[1];
    $stts_daftar_pasien_poli_batal_bedah_digestif[] = $row[2];
    $Jumlah_jenis_pasien_poli_batal_bedah_digestif[] = $row[3];
  }
  ?>


  <?php
  $Jumlah_jenis_pasien_poli_batal_bedah_mulut = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Batal' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='BDM' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_batal_poli[] = $row[0];
    $nm_jenis_pasien_poli_batal_bedah_mulut[] = $row[1];
    $stts_daftar_pasien_poli_batal_bedah_mulut[] = $row[2];
    $Jumlah_jenis_pasien_poli_batal_bedah_mulut[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_batal_bedah_ongkologi = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Batal'
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='017' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_batal_poli[] = $row[0];
    $nm_jenis_pasien_poli_batal_bedah_ongkologi[] = $row[1];
    $stts_daftar_pasien_poli_batal_bedah_ongkologi[] = $row[2];
    $Jumlah_jenis_pasien_poli_batal_bedah_ongkologi[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_batal_bedah_saraf = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Batal' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='BSY' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_batal_poli[] = $row[0];
    $nm_jenis_pasien_poli_batal_bedah_saraf[] = $row[1];
    $stts_daftar_pasien_poli_batal_bedah_saraf[] = $row[2];
    $Jumlah_jenis_pasien_poli_batal_bedah_saraf[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_batal_bedah_vascular = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Batal'
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='U0036' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_batal_poli[] = $row[0];
    $nm_jenis_pasien_poli_batal_bedah_vascular[] = $row[1];
    $stts_daftar_pasien_poli_batal_bedah_vascular[] = $row[2];
    $Jumlah_jenis_pasien_poli_batal_bedah_vascular[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_batal_gigi = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Batal'
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='U0041' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_batal_poli[] = $row[0];
    $nm_jenis_pasien_poli_batal_gigi[] = $row[1];
    $stts_daftar_pasien_poli_batal_gigi[] = $row[2];
    $Jumlah_jenis_pasien_poli_batal_gigi[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_batal_ginjal_hipertensi = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Batal' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='U0035' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_batal_poli[] = $row[0];
    $nm_jenis_pasien_poli_batal_ginjal_hipertensi[] = $row[1];
    $stts_daftar_pasien_poli_batal_ginjal_hipertensi[] = $row[2];
    $Jumlah_jenis_pasien_poli_batal_ginjal_hipertensi[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_batal_gizi = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Batal' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='GIZ' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_batal_poli[] = $row[0];
    $nm_jenis_pasien_poli_batal_gizi[] = $row[1];
    $stts_daftar_pasien_poli_batal_gizi[] = $row[2];
    $Jumlah_jenis_pasien_poli_batal_gizi[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_batal_hdl = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Batal' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='HDL' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_batal_poli[] = $row[0];
    $nm_jenis_pasien_poli_batal_hdl[] = $row[1];
    $stts_daftar_pasien_poli_batal_hdl[] = $row[2];
    $Jumlah_jenis_pasien_poli_batal_hdl[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_batal_jantung = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Batal' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='020' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_batal_poli[] = $row[0];
    $nm_jenis_pasien_poli_batal_jantung[] = $row[1];
    $stts_daftar_pasien_poli_batal_jantung[] = $row[2];
    $Jumlah_jenis_pasien_poli_batal_jantung[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_batal_jiwa = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Batal'
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='JIW' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_batal_poli[] = $row[0];
    $nm_jenis_pasien_poli_batal_jiwa[] = $row[1];
    $stts_daftar_pasien_poli_batal_jiwa[] = $row[2];
    $Jumlah_jenis_pasien_poli_batal_jiwa[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_batal_konvervasi_gigi = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='batal' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='GND' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_batal_poli[] = $row[0];
    $nm_jenis_pasien_poli_batal_konvervasi_gigi[] = $row[1];
    $stts_daftar_pasien_poli_batal_konvervasi_gigi[] = $row[2];
    $Jumlah_jenis_pasien_poli_batal_konvervasi_gigi[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_batal_klt = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Batal' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='KLT' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_batal_poli[] = $row[0];
    $nm_jenis_pasien_poli_batal_klt[] = $row[1];
    $stts_daftar_pasien_poli_batal_klt[] = $row[2];
    $Jumlah_jenis_pasien_poli_batal_klt[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_batal_mata = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Batal'
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='MAT' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_batal_poli[] = $row[0];
    $nm_jenis_pasien_poli_batal_mata[] = $row[1];
    $stts_daftar_pasien_poli_batal_mata[] = $row[2];
    $Jumlah_jenis_pasien_poli_batal_mata[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_batal_mcu = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Batal'
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='P20' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_batal_poli[] = $row[0];
    $nm_jenis_pasien_poli_batal_mcu[] = $row[1];
    $stts_daftar_pasien_poli_batal_mcu[] = $row[2];
    $Jumlah_jenis_pasien_poli_batal_mcu[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_batal_obgyn = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Batal' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='OBG' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_batal_poli[] = $row[0];
    $nm_jenis_pasien_poli_batal_obgyn[] = $row[1];
    $stts_daftar_pasien_poli_batal_obgyn[] = $row[2];
    $Jumlah_jenis_pasien_poli_batal_obgyn[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_batal_ortopedi = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Batal' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='ORT' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_batal_poli[] = $row[0];
    $nm_jenis_pasien_poli_batal_ortopedi[] = $row[1];
    $stts_daftar_pasien_poli_batal_ortopedi[] = $row[2];
    $Jumlah_jenis_pasien_poli_batal_ortopedi[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_batal_paru = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Batal' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='PAR' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_batal_poli[] = $row[0];
    $nm_jenis_pasien_poli_batal_paru[] = $row[1];
    $stts_daftar_pasien_poli_batal_paru[] = $row[2];
    $Jumlah_jenis_pasien_poli_batal_paru[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_batal_int = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Batal'
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='INT' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_batal_poli[] = $row[0];
    $nm_jenis_pasien_poli_batal_int[] = $row[1];
    $stts_daftar_pasien_poli_batal_int[] = $row[2];
    $Jumlah_jenis_pasien_poli_batal_int[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_batal_rehabmedik = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Batal' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='IRM' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_batal_poli[] = $row[0];
    $nm_jenis_pasien_poli_batal_rehabmedik[] = $row[1];
    $stts_daftar_pasien_poli_batal_rehabmedik[] = $row[2];
    $Jumlah_jenis_pasien_poli_batal_rehabmedik[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_batal_saraf = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Batal' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='SAR' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_batal_poli[] = $row[0];
    $nm_jenis_pasien_poli_batal_saraf[] = $row[1];
    $stts_daftar_pasien_poli_batal_saraf[] = $row[2];
    $Jumlah_jenis_pasien_poli_batal_saraf[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_batal_tht = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Batal' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='THT' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_batal_poli[] = $row[0];
    $nm_jenis_pasien_poli_batal_tht[] = $row[1];
    $stts_daftar_pasien_poli_batal_tht[] = $row[2];
    $Jumlah_jenis_pasien_poli_batal_tht[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_batal_uro = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Batal' 
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='URO' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_batal_poli[] = $row[0];
    $nm_jenis_pasien_poli_batal_uro[] = $row[1];
    $stts_daftar_pasien_poli_batal_uro[] = $row[2];
    $Jumlah_jenis_pasien_poli_batal_uro[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_batal_vct = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli,reg_periksa.stts_daftar ,
COUNT(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Batal'
and reg_periksa.status_lanjut ='ralan'
and reg_periksa.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli='U0029' 
and reg_periksa.tgl_registrasi=CURDATE() ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_batal_poli[] = $row[0];
    $nm_jenis_pasien_poli_batal_vct[] = $row[1];
    $stts_daftar_pasien_poli_batal_vct[] = $row[2];
    $Jumlah_jenis_pasien_poli_batal_vct[] = $row[3];
  }
  ?>

  <!-- qwery Pasien Online  -->
  <?php
  $Jumlah_jenis_pasien_poli_online_anak = array();
  $result = mysqli_query($koneksi, "SELECT booking_periksa.tanggal,booking_periksa.kd_poli,booking_periksa.status,COUNT(booking_periksa.kd_poli) AS Jumlah
FROM booking_periksa,booking_periksa_diterima
WHERE booking_periksa.`status`='Diterima' AND booking_periksa.kd_poli='ANA'  AND booking_periksa.tanggal=CURDATE()
AND booking_periksa.no_booking=booking_periksa_diterima.no_booking
GROUP BY booking_periksa.kd_poli");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_online_poli[] = $row[0];
    $nm_jenis_pasien_poli_online_anak[] = $row[1];
    $stts_daftar_pasien_poli_online_anak[] = $row[2];
    $Jumlah_jenis_pasien_poli_online_anak[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_online_bedah = array();
  $result = mysqli_query($koneksi, "SELECT booking_periksa.tanggal,booking_periksa.kd_poli,booking_periksa.status,COUNT(booking_periksa.kd_poli) AS Jumlah
FROM booking_periksa,booking_periksa_diterima
WHERE booking_periksa.`status`='Diterima' AND booking_periksa.kd_poli='BED'  AND booking_periksa.tanggal=CURDATE()
AND booking_periksa.no_booking=booking_periksa_diterima.no_booking
GROUP BY booking_periksa.kd_poli");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_online_poli[] = $row[0];
    $nm_jenis_pasien_poli_online_bedah[] = $row[1];
    $stts_daftar_pasien_poli_online_bedah[] = $row[2];
    $Jumlah_jenis_pasien_poli_online_bedah[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_online_bedah_anak = array();
  $result = mysqli_query($koneksi, "SELECT booking_periksa.tanggal,booking_periksa.kd_poli,booking_periksa.status,COUNT(booking_periksa.kd_poli) AS Jumlah
FROM booking_periksa,booking_periksa_diterima
WHERE booking_periksa.`status`='Diterima' AND booking_periksa.kd_poli='BDA'  AND booking_periksa.tanggal=CURDATE()
AND booking_periksa.no_booking=booking_periksa_diterima.no_booking
GROUP BY booking_periksa.kd_poli");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_online_poli[] = $row[0];
    $nm_jenis_pasien_poli_online_bedah_anak[] = $row[1];
    $stts_daftar_pasien_poli_online_bedah_anak[] = $row[2];
    $Jumlah_jenis_pasien_poli_online_bedah_anak[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_online_bedah_digestif = array();
  $result = mysqli_query($koneksi, "SELECT booking_periksa.tanggal,booking_periksa.kd_poli,booking_periksa.status,COUNT(booking_periksa.kd_poli) AS Jumlah
FROM booking_periksa,booking_periksa_diterima
WHERE booking_periksa.`status`='Diterima' AND booking_periksa.kd_poli='018'  AND booking_periksa.tanggal=CURDATE()
AND booking_periksa.no_booking=booking_periksa_diterima.no_booking
GROUP BY booking_periksa.kd_poli");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_online_poli[] = $row[0];
    $nm_jenis_pasien_poli_online_bedah_digestif[] = $row[1];
    $stts_daftar_pasien_poli_online_bedah_digestif[] = $row[2];
    $Jumlah_jenis_pasien_poli_online_bedah_digestif[] = $row[3];
  }
  ?>


  <?php
  $Jumlah_jenis_pasien_poli_online_bedah_mulut = array();
  $result = mysqli_query($koneksi, "SELECT booking_periksa.tanggal,booking_periksa.kd_poli,booking_periksa.status,COUNT(booking_periksa.kd_poli) AS Jumlah
FROM booking_periksa,booking_periksa_diterima
WHERE booking_periksa.`status`='Diterima' AND booking_periksa.kd_poli='BDM'  AND booking_periksa.tanggal=CURDATE()
AND booking_periksa.no_booking=booking_periksa_diterima.no_booking
GROUP BY booking_periksa.kd_poli ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_online_poli[] = $row[0];
    $nm_jenis_pasien_poli_online_bedah_mulut[] = $row[1];
    $stts_daftar_pasien_poli_online_bedah_mulut[] = $row[2];
    $Jumlah_jenis_pasien_poli_online_bedah_mulut[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_online_bedah_ongkologi = array();
  $result = mysqli_query($koneksi, "SELECT booking_periksa.tanggal,booking_periksa.kd_poli,booking_periksa.status,COUNT(booking_periksa.kd_poli) AS Jumlah
FROM booking_periksa,booking_periksa_diterima
WHERE booking_periksa.`status`='Diterima' AND booking_periksa.kd_poli='017'  AND booking_periksa.tanggal=CURDATE()
AND booking_periksa.no_booking=booking_periksa_diterima.no_booking
GROUP BY booking_periksa.kd_poli ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_online_poli[] = $row[0];
    $nm_jenis_pasien_poli_online_bedah_ongkologi[] = $row[1];
    $stts_daftar_pasien_poli_online_bedah_ongkologi[] = $row[2];
    $Jumlah_jenis_pasien_poli_online_bedah_ongkologi[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_online_bedah_saraf = array();
  $result = mysqli_query($koneksi, "SELECT booking_periksa.tanggal,booking_periksa.kd_poli,booking_periksa.status,COUNT(booking_periksa.kd_poli) AS Jumlah
FROM booking_periksa,booking_periksa_diterima
WHERE booking_periksa.`status`='Diterima' AND booking_periksa.kd_poli='BSY'  AND booking_periksa.tanggal=CURDATE()
AND booking_periksa.no_booking=booking_periksa_diterima.no_booking
GROUP BY booking_periksa.kd_poli");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_online_poli[] = $row[0];
    $nm_jenis_pasien_poli_online_bedah_saraf[] = $row[1];
    $stts_daftar_pasien_poli_online_bedah_saraf[] = $row[2];
    $Jumlah_jenis_pasien_poli_online_bedah_saraf[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_online_bedah_vascular = array();
  $result = mysqli_query($koneksi, "SELECT booking_periksa.tanggal,booking_periksa.kd_poli,booking_periksa.status,COUNT(booking_periksa.kd_poli) AS Jumlah
FROM booking_periksa,booking_periksa_diterima
WHERE booking_periksa.`status`='Diterima' AND booking_periksa.kd_poli='U0036'  AND booking_periksa.tanggal=CURDATE()
AND booking_periksa.no_booking=booking_periksa_diterima.no_booking
GROUP BY booking_periksa.kd_poli");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_online_poli[] = $row[0];
    $nm_jenis_pasien_poli_online_bedah_vascular[] = $row[1];
    $stts_daftar_pasien_poli_online_bedah_vascular[] = $row[2];
    $Jumlah_jenis_pasien_poli_online_bedah_vascular[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_online_gigi = array();
  $result = mysqli_query($koneksi, "SELECT booking_periksa.tanggal,booking_periksa.kd_poli,booking_periksa.status,COUNT(booking_periksa.kd_poli) AS Jumlah
FROM booking_periksa,booking_periksa_diterima
WHERE booking_periksa.`status`='Diterima' AND booking_periksa.kd_poli='U0041'  AND booking_periksa.tanggal=CURDATE()
AND booking_periksa.no_booking=booking_periksa_diterima.no_booking
GROUP BY booking_periksa.kd_poli");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_online_poli[] = $row[0];
    $nm_jenis_pasien_poli_online_gigi[] = $row[1];
    $stts_daftar_pasien_online_batal_gigi[] = $row[2];
    $Jumlah_jenis_pasien_online_batal_gigi[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_online_ginjal_hipertensi = array();
  $result = mysqli_query($koneksi, "SELECT booking_periksa.tanggal,booking_periksa.kd_poli,booking_periksa.status,COUNT(booking_periksa.kd_poli) AS Jumlah
FROM booking_periksa,booking_periksa_diterima
WHERE booking_periksa.`status`='Diterima' AND booking_periksa.kd_poli='U0035'  AND booking_periksa.tanggal=CURDATE()
AND booking_periksa.no_booking=booking_periksa_diterima.no_booking
GROUP BY booking_periksa.kd_poli");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_online_poli[] = $row[0];
    $nm_jenis_pasien_poli_online_ginjal_hipertensi[] = $row[1];
    $stts_daftar_pasien_poli_online_ginjal_hipertensi[] = $row[2];
    $Jumlah_jenis_pasien_poli_online_ginjal_hipertensi[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_online_gizi = array();
  $result = mysqli_query($koneksi, "SELECT booking_periksa.tanggal,booking_periksa.kd_poli,booking_periksa.status,COUNT(booking_periksa.kd_poli) AS Jumlah
FROM booking_periksa,booking_periksa_diterima
WHERE booking_periksa.`status`='Diterima' AND booking_periksa.kd_poli='GIZ'  AND booking_periksa.tanggal=CURDATE()
AND booking_periksa.no_booking=booking_periksa_diterima.no_booking
GROUP BY booking_periksa.kd_poli");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_online_poli[] = $row[0];
    $nm_jenis_pasien_poli_online_gizi[] = $row[1];
    $stts_daftar_pasien_poli_online_gizi[] = $row[2];
    $Jumlah_jenis_pasien_poli_online_gizi[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_online_hdl = array();
  $result = mysqli_query($koneksi, "SELECT booking_periksa.tanggal,booking_periksa.kd_poli,booking_periksa.status,COUNT(booking_periksa.kd_poli) AS Jumlah
FROM booking_periksa,booking_periksa_diterima
WHERE booking_periksa.`status`='Diterima' AND booking_periksa.kd_poli='HDL'  AND booking_periksa.tanggal=CURDATE()
AND booking_periksa.no_booking=booking_periksa_diterima.no_booking
GROUP BY booking_periksa.kd_poli");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_online_poli[] = $row[0];
    $nm_jenis_pasien_poli_online_hdl[] = $row[1];
    $stts_daftar_pasien_poli_online_hdl[] = $row[2];
    $Jumlah_jenis_pasien_poli_online_hdl[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_online_jantung = array();
  $result = mysqli_query($koneksi, "SELECT booking_periksa.tanggal,booking_periksa.kd_poli,booking_periksa.status,COUNT(booking_periksa.kd_poli) AS Jumlah
FROM booking_periksa,booking_periksa_diterima
WHERE booking_periksa.`status`='Diterima' AND booking_periksa.kd_poli='020'  AND booking_periksa.tanggal=CURDATE()
AND booking_periksa.no_booking=booking_periksa_diterima.no_booking
GROUP BY booking_periksa.kd_poli");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_online_poli[] = $row[0];
    $nm_jenis_pasien_poli_online_jantung[] = $row[1];
    $stts_daftar_pasien_poli_online_jantung[] = $row[2];
    $Jumlah_jenis_pasien_poli_online_jantung[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_online_jiwa = array();
  $result = mysqli_query($koneksi, "SELECT booking_periksa.tanggal,booking_periksa.kd_poli,booking_periksa.status,COUNT(booking_periksa.kd_poli) AS Jumlah
FROM booking_periksa,booking_periksa_diterima
WHERE booking_periksa.`status`='Diterima' AND booking_periksa.kd_poli='JIW'  AND booking_periksa.tanggal=CURDATE()
AND booking_periksa.no_booking=booking_periksa_diterima.no_booking
GROUP BY booking_periksa.kd_poli");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_online_poli[] = $row[0];
    $nm_jenis_pasien_poli_online_jiwa[] = $row[1];
    $stts_daftar_pasien_poli_online_jiwa[] = $row[2];
    $Jumlah_jenis_pasien_poli_online_jiwa[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_online_konvervasi_gigi = array();
  $result = mysqli_query($koneksi, "SELECT booking_periksa.tanggal,booking_periksa.kd_poli,booking_periksa.status,COUNT(booking_periksa.kd_poli) AS Jumlah
FROM booking_periksa,booking_periksa_diterima
WHERE booking_periksa.`status`='Diterima' AND booking_periksa.kd_poli='GND'  AND booking_periksa.tanggal=CURDATE()
AND booking_periksa.no_booking=booking_periksa_diterima.no_booking
GROUP BY booking_periksa.kd_poli");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_online_poli[] = $row[0];
    $nm_jenis_pasien_poli_online_konvervasi_gigi[] = $row[1];
    $stts_daftar_pasien_poli_online_konvervasi_gigi[] = $row[2];
    $Jumlah_jenis_pasien_poli_online_konvervasi_gigi[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_online_klt = array();
  $result = mysqli_query($koneksi, "SELECT booking_periksa.tanggal,booking_periksa.kd_poli,booking_periksa.status,COUNT(booking_periksa.kd_poli) AS Jumlah
FROM booking_periksa,booking_periksa_diterima
WHERE booking_periksa.`status`='Diterima' AND booking_periksa.kd_poli='KLT'  AND booking_periksa.tanggal=CURDATE()
AND booking_periksa.no_booking=booking_periksa_diterima.no_booking
GROUP BY booking_periksa.kd_poli");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_online_poli[] = $row[0];
    $nm_jenis_pasien_poli_online_klt[] = $row[1];
    $stts_daftar_pasien_poli_online_klt[] = $row[2];
    $Jumlah_jenis_pasien_poli_online_klt[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_online_mata = array();
  $result = mysqli_query($koneksi, "SELECT booking_periksa.tanggal,booking_periksa.kd_poli,booking_periksa.status,COUNT(booking_periksa.kd_poli) AS Jumlah
FROM booking_periksa,booking_periksa_diterima
WHERE booking_periksa.`status`='Diterima' AND booking_periksa.kd_poli='MAT'  AND booking_periksa.tanggal=CURDATE()
AND booking_periksa.no_booking=booking_periksa_diterima.no_booking
GROUP BY booking_periksa.kd_poli");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_online_poli[] = $row[0];
    $nm_jenis_pasien_poli_online_mata[] = $row[1];
    $stts_daftar_pasien_poli_online_mata[] = $row[2];
    $Jumlah_jenis_pasien_poli_online_mata[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_online_mcu = array();
  $result = mysqli_query($koneksi, "SELECT booking_periksa.tanggal,booking_periksa.kd_poli,booking_periksa.status,COUNT(booking_periksa.kd_poli) AS Jumlah
FROM booking_periksa,booking_periksa_diterima
WHERE booking_periksa.`status`='Diterima' AND booking_periksa.kd_poli='P20'  AND booking_periksa.tanggal=CURDATE()
AND booking_periksa.no_booking=booking_periksa_diterima.no_booking
GROUP BY booking_periksa.kd_poli");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_online_poli[] = $row[0];
    $nm_jenis_pasien_poli_online_mcu[] = $row[1];
    $stts_daftar_pasien_poli_online_mcu[] = $row[2];
    $Jumlah_jenis_pasien_poli_online_mcu[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_online_obgyn = array();
  $result = mysqli_query($koneksi, "SELECT booking_periksa.tanggal,booking_periksa.kd_poli,booking_periksa.status,COUNT(booking_periksa.kd_poli) AS Jumlah
FROM booking_periksa,booking_periksa_diterima
WHERE booking_periksa.`status`='Diterima' AND booking_periksa.kd_poli='OBG'  AND booking_periksa.tanggal=CURDATE()
AND booking_periksa.no_booking=booking_periksa_diterima.no_booking
GROUP BY booking_periksa.kd_poli");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_online_poli[] = $row[0];
    $nm_jenis_pasien_poli_online_obgyn[] = $row[1];
    $stts_daftar_pasien_poli_online_obgyn[] = $row[2];
    $Jumlah_jenis_pasien_poli_online_obgyn[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_online_ortopedi = array();
  $result = mysqli_query($koneksi, "SELECT booking_periksa.tanggal,booking_periksa.kd_poli,booking_periksa.status,COUNT(booking_periksa.kd_poli) AS Jumlah
FROM booking_periksa,booking_periksa_diterima
WHERE booking_periksa.`status`='Diterima' AND booking_periksa.kd_poli='ORT'  AND booking_periksa.tanggal=CURDATE()
AND booking_periksa.no_booking=booking_periksa_diterima.no_booking
GROUP BY booking_periksa.kd_poli");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_online_poli[] = $row[0];
    $nm_jenis_pasien_poli_online_ortopedi[] = $row[1];
    $stts_daftar_pasien_poli_online_ortopedi[] = $row[2];
    $Jumlah_jenis_pasien_poli_online_ortopedi[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_online_paru = array();
  $result = mysqli_query($koneksi, "SELECT booking_periksa.tanggal,booking_periksa.kd_poli,booking_periksa.status,COUNT(booking_periksa.kd_poli) AS Jumlah
FROM booking_periksa,booking_periksa_diterima
WHERE booking_periksa.`status`='Diterima' AND booking_periksa.kd_poli='PAR'  AND booking_periksa.tanggal=CURDATE()
AND booking_periksa.no_booking=booking_periksa_diterima.no_booking
GROUP BY booking_periksa.kd_poli");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_online_poli[] = $row[0];
    $nm_jenis_pasien_poli_online_paru[] = $row[1];
    $stts_daftar_pasien_poli_online_paru[] = $row[2];
    $Jumlah_jenis_pasien_poli_online_paru[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_online_int = array();
  $result = mysqli_query($koneksi, "SELECT booking_periksa.tanggal,booking_periksa.kd_poli,booking_periksa.status,COUNT(booking_periksa.kd_poli) AS Jumlah
FROM booking_periksa,booking_periksa_diterima
WHERE booking_periksa.`status`='Diterima' AND booking_periksa.kd_poli='INT'  AND booking_periksa.tanggal=CURDATE()
AND booking_periksa.no_booking=booking_periksa_diterima.no_booking
GROUP BY booking_periksa.kd_poli");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_online_poli[] = $row[0];
    $nm_jenis_pasien_poli_online_int[] = $row[1];
    $stts_daftar_pasien_poli_online_int[] = $row[2];
    $Jumlah_jenis_pasien_poli_online_int[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_online_rehabmedik = array();
  $result = mysqli_query($koneksi, "SELECT booking_periksa.tanggal,booking_periksa.kd_poli,booking_periksa.status,COUNT(booking_periksa.kd_poli) AS Jumlah
FROM booking_periksa,booking_periksa_diterima
WHERE booking_periksa.`status`='Diterima' AND booking_periksa.kd_poli='IRM'  AND booking_periksa.tanggal=CURDATE()
AND booking_periksa.no_booking=booking_periksa_diterima.no_booking
GROUP BY booking_periksa.kd_poli ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_online_poli[] = $row[0];
    $nm_jenis_pasien_poli_online_rehabmedik[] = $row[1];
    $stts_daftar_pasien_poli_online_rehabmedik[] = $row[2];
    $Jumlah_jenis_pasien_poli_online_rehabmedik[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_online_saraf = array();
  $result = mysqli_query($koneksi, "SELECT booking_periksa.tanggal,booking_periksa.kd_poli,booking_periksa.status,COUNT(booking_periksa.kd_poli) AS Jumlah
FROM booking_periksa,booking_periksa_diterima
WHERE booking_periksa.`status`='Diterima' AND booking_periksa.kd_poli='SAR'  AND booking_periksa.tanggal=CURDATE()
AND booking_periksa.no_booking=booking_periksa_diterima.no_booking
GROUP BY booking_periksa.kd_poli ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_online_poli[] = $row[0];
    $nm_jenis_pasien_poli_online_saraf[] = $row[1];
    $stts_daftar_pasien_poli_online_saraf[] = $row[2];
    $Jumlah_jenis_pasien_poli_online_saraf[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_online_tht = array();
  $result = mysqli_query($koneksi, "SELECT booking_periksa.tanggal,booking_periksa.kd_poli,booking_periksa.status,COUNT(booking_periksa.kd_poli) AS Jumlah
FROM booking_periksa,booking_periksa_diterima
WHERE booking_periksa.`status`='Diterima' AND booking_periksa.kd_poli='THT'  AND booking_periksa.tanggal=CURDATE()
AND booking_periksa.no_booking=booking_periksa_diterima.no_booking
GROUP BY booking_periksa.kd_poli ");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_online_poli[] = $row[0];
    $nm_jenis_pasien_poli_online_tht[] = $row[1];
    $stts_daftar_pasien_poli_online_tht[] = $row[2];
    $Jumlah_jenis_pasien_poli_online_tht[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_online_uro = array();
  $result = mysqli_query($koneksi, "SELECT booking_periksa.tanggal,booking_periksa.kd_poli,booking_periksa.status,COUNT(booking_periksa.kd_poli) AS Jumlah
FROM booking_periksa,booking_periksa_diterima
WHERE booking_periksa.`status`='Diterima' AND booking_periksa.kd_poli='URO'  AND booking_periksa.tanggal=CURDATE()
AND booking_periksa.no_booking=booking_periksa_diterima.no_booking
GROUP BY booking_periksa.kd_poli");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_online_poli[] = $row[0];
    $nm_jenis_pasien_poli_online_uro[] = $row[1];
    $stts_daftar_pasien_poli_online_uro[] = $row[2];
    $Jumlah_jenis_pasien_poli_online_uro[] = $row[3];
  }
  ?>

  <?php
  $Jumlah_jenis_pasien_poli_online_vct = array();
  $result = mysqli_query($koneksi, "SELECT booking_periksa.tanggal,booking_periksa.kd_poli,booking_periksa.status,COUNT(booking_periksa.kd_poli) AS Jumlah
FROM booking_periksa,booking_periksa_diterima
WHERE booking_periksa.`status`='Diterima' AND booking_periksa.kd_poli='U0029'  AND booking_periksa.tanggal=CURDATE()
AND booking_periksa.no_booking=booking_periksa_diterima.no_booking
GROUP BY booking_periksa.kd_poli");
  //urutan tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tanggal_jenis_pasien_online_poli[] = $row[0];
    $nm_jenis_pasien_poli_online_vct[] = $row[1];
    $stts_daftar_pasien_poli_online_vct[] = $row[2];
    $Jumlah_jenis_pasien_poli_online_vct[] = $row[3];
  }
  ?>

</body>

</html>
<script>
  // chart hari ini  
  Highcharts.chart('chart_ralan', {
    chart: {
      type: 'area',
      scrollablePlotArea: {
      minWidth: 700,
      scrollPositionX: 1
    }
    },
    title: {
      text: 'Rawat Jalan Hari Ini'
    },
    subtitle: {
      text: "Status sudah Periksa",
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
      categories: ['<?php echo  implode("','", $kdpoli) ?>'],
    },
    yAxis: {
      title: {
        text: 'Jumlah'
      }
    },
    credits: {
      enabled: false
    },
    series: [{
      name: 'Jumlah',
      data: [<?php echo  implode(",", $norekam) ?>],
    }]
  });
  //

  // CHART PERTANGGAL
  Highcharts.chart("chart_pertanggal", {

    chart: {
      type: 'areaspline'
    },
    title: {
      text: 'Grafik Rawat Jalan Pertanggal'
    },
    subtitle: {
      text: "Status sudah Periksa",
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
      categories: ['<?php echo  implode("','", $tahun_bulan_tgl_pertanggal) ?>'],
      // plotBands: [{ // visualize the weekend
      //     from: 4.5,
      //     to: 6.5,
      //     color: 'rgba(68, 170, 213, .2)'
      // }]
    },
    yAxis: {
      title: {
        text: 'Jumlah'
      }
    },
    tooltip: {
      shared: true,
      valueSuffix: ' Jumlah Pasien'
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
          name: 'Poli Anak',
          data: [
            <?php foreach ($jumlah_pasien_anak_tanggal as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli bedah',
          data: [
            <?php foreach ($jumlah_pasein_bedah_tanggal as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },

      <?php } ?>

      <?php {  ?> {
          name: 'Poli bedah anak',
          data: [
            <?php foreach ($jumlah_bedah_anak_tanggal as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },

      <?php } ?>

      <?php {  ?> {
          name: 'Poli Bedah DIGESTIF',
          data: [
            <?php foreach ($jumlah_bedah_degif_tanggal as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },

      <?php } ?>

      <?php {  ?> {
          name: 'Poli Bedah Bedah Mulut',
          data: [
            <?php foreach ($jumlah_bedah_mulut_tanggal as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },

      <?php } ?>

      <?php {  ?> {
          name: 'Poli Bedah Bedah Ongkologi',

          data: [
            <?php foreach ($jumlah_bedah_ongkologi_tanggal as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },

      <?php } ?>

      <?php {  ?> {
          name: 'Poli Bedah Bedah saraf',
          data: [
            <?php foreach ($jumlah_bedah_saraf_tanggal as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },

      <?php } ?>

      <?php {  ?> {
          name: 'Poli Bedah Bedah Vascular',
          data: [
            <?php foreach ($jumlah_bedah_vascular_tanggal as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Gigi',
          data: [
            <?php foreach ($jumlah_gigi_tanggal as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },

      <?php } ?>

      <?php {  ?> {
          name: 'Poli Jantung & Pembuluh Darah',
          data: [
            <?php foreach ($jumlah_jantung_tanggal as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Ginjal Hipertensi',

          data: [
            <?php foreach ($jumlah_Ginjal_Hipeternsi_tanggal as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Jiwa',
          data: [
            <?php foreach ($jumlah_jiwa_tanggal as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Konservai Gigi',
          data: [
            <?php foreach ($jumlah_konservai_gigi_tanggal as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Kulit Kelamin',
          data: [
            <?php foreach ($jumlah_kulkel_tanggal as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Mata',
          data: [
            <?php foreach ($jumlah_mata_tanggal as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Obgyn',
          data: [
            <?php foreach ($jumlah_obgyn_tanggal as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Ortopedi',
          data: [
            <?php foreach ($jumlah_ortopedi_tanggal as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Paru',
          data: [
            <?php foreach ($jumlah_paru_tanggal as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Penyakit Dalam',
          data: [
            <?php foreach ($jumlah_penyakit_dalam_tanggal as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Saraf',
          data: [
            <?php foreach ($jumlah_saraf_tanggal as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli THT',
          data: [
            <?php foreach ($jumlah_tht_tanggal as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli VCT',
          data: [
            <?php foreach ($jumlah_vct_tanggal as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Urologi',
          data: [
            <?php foreach ($jumlah_pasien_uro_tanggal as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>
    ]
  });

  //chart Mingguan
  Highcharts.chart("chart_mingguan", {

    chart: {
      type: 'areaspline'
    },
    title: {
      text: 'Grafik Rawat Jalan Perminggu'
    },
    subtitle: {
      text: "Status sudah Periksa",
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
      categories: ['<?php echo  implode("','", $tahun_bulan_tgl_mingguan) ?>'],
      // plotBands: [{ // visualize the weekend
      //     from: 4.5,
      //     to: 6.5,
      //     color: 'rgba(68, 170, 213, .2)'
      // }]
    },
    yAxis: {
      title: {
        text: 'Jumlah'
      }
    },
    tooltip: {
      shared: true,
      valueSuffix: ' Jumlah Pasien'
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
          name: 'Poli Anak',
          data: [
            <?php foreach ($jumlah_pasien_anak as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli bedah',
          data: [
            <?php foreach ($jumlah_pasein_bedah as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },

      <?php } ?>

      <?php {  ?> {
          name: 'Poli bedah anak',
          data: [
            <?php foreach ($jumlah_bedah_anak as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },

      <?php } ?>

      <?php {  ?> {
          name: 'Poli Bedah DIGESTIF',
          data: [
            <?php foreach ($jumlah_bedah_degif as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },

      <?php } ?>

      <?php {  ?> {
          name: 'Poli Bedah Bedah Mulut',
          data: [
            <?php foreach ($jumlah_bedah_mulut as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },

      <?php } ?>

      <?php {  ?> {
          name: 'Poli Bedah Bedah Ongkologi',

          data: [
            <?php foreach ($jumlah_bedah_ongkologi as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },

      <?php } ?>

      <?php {  ?> {
          name: 'Poli Bedah Bedah saraf',
          data: [
            <?php foreach ($jumlah_bedah_saraf as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },

      <?php } ?>

      <?php {  ?> {
          name: 'Poli Bedah Bedah Vascular',
          data: [
            <?php foreach ($jumlah_bedah_vascular as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Gigi',
          data: [
            <?php foreach ($jumlah_gigi as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },

      <?php } ?>

      <?php {  ?> {
          name: 'Poli Jantung & Pembuluh Darah',
          data: [
            <?php foreach ($jumlah_jantung as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Ginjal Hipertensi',

          data: [
            <?php foreach ($jumlah_Ginjal_Hipeternsi as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Jiwa',
          data: [
            <?php foreach ($jumlah_jiwa as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Konservai Gigi',
          data: [
            <?php foreach ($jumlah_konservai_gigi as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Kulit Kelamin',
          data: [
            <?php foreach ($jumlah_kulkel as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Mata',
          data: [
            <?php foreach ($jumlah_mata as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Obgyn',
          data: [
            <?php foreach ($jumlah_obgyn as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Ortopedi',
          data: [
            <?php foreach ($jumlah_ortopedi as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Paru',
          data: [
            <?php foreach ($jumlah_paru as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Penyakit Dalam',
          data: [
            <?php foreach ($jumlah_paru as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Saraf',
          data: [
            <?php foreach ($jumlah_paru as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli THT',
          data: [
            <?php foreach ($jumlah_tht as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli VCT',
          data: [
            <?php foreach ($jumlah_vct as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },

      <?php } ?>

      <?php {  ?> {
          name: 'Poli Urologi',
          data: [
            <?php foreach ($jumlah_pasien_uro as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>
    ]
  });


  // chart bulanan
  Highcharts.chart("chart_perbandingan_bulanan", {
    chart: {
      type: 'areaspline'
    },
    title: {
      text: 'Grafik Rawat Jalan Bulanan'
    },
    subtitle: {
      text: "Status sudah Periksa",
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
      categories: ['<?php echo  implode("','", $bulanan) ?>'],
    },
    yAxis: {
      title: {
        text: 'Jumlah'
      }
    },
    tooltip: {
      shared: true,
      valueSuffix: ' Jumlah Pasien'
    },
    credits: {
      enabled: false
    },
    plotOptions: {
      areaspline: {
        fillOpacity: 0.5
      }
    },
    series: [
      <?php {  ?> {
          name: 'Poli Anak',
          data: [
            <?php foreach ($jumlah_bulan_anak as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Bedah',

          data: [
            <?php foreach ($jumlah_bulan_bedah as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Bedah Anak',
          data: [
            <?php foreach ($jumlah_bulan_bedah_anak as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Bedah Digestif',
          data: [
            <?php foreach ($jumlah_bulan_digestif as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Bedah Mulut',
          data: [
            <?php foreach ($jumlah_bulan_bedah_mulut as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Bedah Ongkologi',
          data: [
            <?php foreach ($jumlah_bulan_bedah_ongkologi as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Bedah Saraf',
          data: [
            <?php foreach ($jumlah_bulan_bedah_saraf as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Bedah Vascular',
          data: [
            <?php foreach ($jumlah_bulan_bedah_vascular as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Gigi',
          data: [
            <?php foreach ($jumlah_bulan_gigi as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Jantung',
          data: [
            <?php foreach ($jumlah_bulan_jantung as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Jiwa',
          data: [
            <?php foreach ($jumlah_bulan_jiwa as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Konservasi Gigi',
          data: [
            <?php foreach ($jumlah_bulan_kgigi as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Kulit Kelamin',
          data: [
            <?php foreach ($jumlah_bulan_kulkel as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Mata',
          data: [
            <?php foreach ($jumlah_bulan_mata as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Obgyn',
          data: [
            <?php foreach ($jumlah_bulan_obgyn as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Ortopedi',
          data: [
            <?php foreach ($jumlah_bulan_ortopedi as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Paru',
          data: [
            <?php foreach ($jumlah_bulan_paru as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Penyakit Dalam',
          data: [
            <?php foreach ($jumlah_bulan_int as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Saraf',
          data: [
            <?php foreach ($jumlah_bulan_saraf as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli THT-KL',
          data: [
            <?php foreach ($jumlah_bulan_tht as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Urologi',
          data: [
            <?php foreach ($jumlah_bulan_uro as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli VCT',
          data: [
            <?php foreach ($jumlah_bulan_vct as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>
    ]
  });


  // chart Tahunan
  Highcharts.chart("chart_perbandingan_tahunan", {
    chart: {
      type: 'spline'
    },
    title: {
      text: 'Grafik Rawat Jalan Tahunan'
    },
    subtitle: {
      text: "Status sudah Periksa",
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
      categories: ['<?php echo  implode("','", $tahunan) ?>'],
    },
    yAxis: {
      title: {
        text: 'Jumlah'
      }
    },
    tooltip: {
      shared: true,
      valueSuffix: ' Jumlah Pasien'
    },
    credits: {
      enabled: false
    },
    plotOptions: {
      areaspline: {
        fillOpacity: 0.5
      }
    },
    series: [
      <?php {  ?> {
          name: 'Poli Anak',
          data: [
            <?php foreach ($jumlah_tahun_anak as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Bedah',

          data: [
            <?php foreach ($jumlah_tahun_bedah as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Bedah Anak',
          data: [
            <?php foreach ($jumlah_tahun_bedah_anak as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Bedah Digestif',
          data: [
            <?php foreach ($jumlah_tahun_digestif as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Bedah Mulut',
          data: [
            <?php foreach ($jumlah_tahun_bedah_mulut as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Bedah Ongkologi',
          data: [
            <?php foreach ($jumlah_tahun_bedah_ongkologi as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Bedah Saraf',
          data: [
            <?php foreach ($jumlah_tahun_bedah_saraf as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Bedah Vascular',
          data: [
            <?php foreach ($jumlah_tahun_bedah_vascular as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Gigi',
          data: [
            <?php foreach ($jumlah_tahun_gigi as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Jantung',
          data: [
            <?php foreach ($jumlah_tahun_jantung as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Jiwa',
          data: [
            <?php foreach ($jumlah_tahun_jiwa as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Konservasi Gigi',
          data: [
            <?php foreach ($jumlah_tahun_kgigi as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Kulit Kelamin',
          data: [
            <?php foreach ($jumlah_tahun_kulkel as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Mata',
          data: [
            <?php foreach ($jumlah_tahun_mata as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Obgyn',
          data: [
            <?php foreach ($jumlah_tahun_obgyn as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Ortopedi',
          data: [
            <?php foreach ($jumlah_tahun_ortopedi as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Paru',
          data: [
            <?php foreach ($jumlah_tahun_paru as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Penyakit Dalam',
          data: [
            <?php foreach ($jumlah_tahun_int as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Saraf',
          data: [
            <?php foreach ($jumlah_tahun_saraf as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli THT-KL',
          data: [
            <?php foreach ($jumlah_tahun_tht as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli Urologi',
          data: [
            <?php foreach ($jumlah_tahun_uro as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Poli VCT',
          data: [
            <?php foreach ($jumlah_tahun_vct as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>
    ]
  });


  // chart dokter now
  Highcharts.chart("chart_dokter_now", {
    chart: {
      type: 'spline',
      scrollablePlotArea: {
      minWidth: 700,
      scrollPositionX: 1
    }
    },
    title: {
      text: 'Grafik Kunjungan  Dokter'
    },
    subtitle: {
      text: "Status sudah Periksa Hari ini",
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
      categories: ['<?php echo  implode("','", $nmdokter_now) ?>'],
    },
    yAxis: {
      title: {
        text: 'Jumlah'
      }
    },
    tooltip: {
      shared: true,
      valueSuffix: ' Jumlah Pasien'
    },
    credits: {
      enabled: false
    },
    plotOptions: {
      areaspline: {
        fillOpacity: 0.5
      }
    },
    series: [
      <?php {  ?> {
          name: '',
          data: [
            <?php foreach ($jumlah_pasien_now_dokter as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>
    ]
  });

  // chart cari dokter
  Highcharts.chart("chart_cari_dokter", {
    chart: {
      type: 'spline'
    },
    title: {
      text: 'Grafik Kunjungan'
    },
    subtitle: {
      text: "Status sudah Periksa",
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
      categories: ['<?php echo  implode("','", $tgldokterbulanan) ?>'],
    },
    yAxis: {
      title: {
        text: 'Jumlah'
      }
    },
    tooltip: {
      shared: true,
      valueSuffix: 'Jumlah Pasien'
    },
    credits: {
      enabled: false
    },
    plotOptions: {
      areaspline: {
        fillOpacity: 0.5
      }
    },
    series: [
      <?php {  ?> {
          name: ' ',
          data: [
            <?php foreach ($jumlah_pasien_dokter_bulanan as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>
    ]
  });
  //
</script>

<!-- cara bayar -->
<script>
  Highcharts.chart('cara-bayar', {
    chart: {
      plotBackgroundColor: null,
      plotBorderWidth: null,
      plotShadow: false,
      type: 'pie'
    },
    title: {
      text: 'Jenis Bayar'
    },
    subtitle: {
        text:
        '<?php echo "Update " . date("Y/m/d")  ?>'
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
          enabled: false
        },
        showInLegend: true
      }
    },
    series: [{
      name: 'Jenis Bayar',
      colorByPoint: true,
      data: [{
        name: 'BPJS',
        y: <?php echo  implode("','", $jumlah_bayar_BPJS) ?>,
        sliced: true,
        selected: true
      }, {
        name: 'BPJS KETENAGA KERJAAN',
        y: <?php echo  implode("','", $jumlah_bayar_BPJS_KETENAGAKERJAAN) ?>
      }, {
        name: 'MANDIRI INHEALTH',
        y: <?php echo  implode("','", $jumlah_bayar_MANDIRI_INHEALTH) ?>
      }, {
        name: 'PT. PLN ADMEDIKA',
        y: <?php echo  implode("','", $jumlah_bayar_PLN) ?>
      }, {
        name: 'UMUM',
        y: <?php echo  implode("','", $jumlah_bayar_umum) ?>
      }, {
        name: 'Lainnya',
        y: <?php echo  implode("','", $jumlah_bayar_lain) ?>
      }]

    }]
  });
</script>

<!-- data chart JENIS TNI-->
<script>
  Highcharts.chart('jenis-tni', {
    chart: {
      plotBackgroundColor: null,
      plotBorderWidth: null,
      plotShadow: false,
      type: 'pie'
    },
    title: {
      text: 'Jenis Pasien TNI'
    },subtitle: {
        text:
        '<?php echo "Update " . date("Y/m/d")  ?>'
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
          enabled: false
        },
        showInLegend: true
      }
    },
    series: [{
      name: 'Jenis Pasien',
      colorByPoint: true,
      data: [{
        name: 'TNI-AU',
        y: <?php echo  implode("','", $jumlah_gol_tni_au) ?>,
        sliced: true,
        selected: true
      }, {
        name: 'TNI-AL',
        y: <?php echo  implode("','", $jumlah_gol_tni_al) ?>,
        sliced: true,
        selected: true
      }, {
        name: 'TNI-AD',
        y: <?php echo  implode("','", $jumlah_gol_tni_ad) ?>,
        sliced: true,
        selected: true
      }, {
        name: 'PURNAWIRAWAN',
        y: <?php echo  implode("','", $jumlah_gol_tni_purnawirawan) ?>,
        sliced: true,
        selected: true
      }, {
        name: 'PNS TNI AD',
        y: <?php echo  implode("','", $jumlah_psn_tni_ad) ?>,
        sliced: true,
        selected: true
      }, {
        name: 'PNS TNI AL',
        y: <?php echo  implode("','", $jumlah_psn_tni_al) ?>,
        sliced: true,
        selected: true
      }, {
        name: 'PNS TNI AU',
        y: <?php echo  implode("','", $jumlah_psn_tni_au) ?>,
        sliced: true,
        selected: true
      }, {
        name: 'KELUARGA TNI AD',
        y: <?php echo  implode("','", $jumlah_keluarga_tni_ad) ?>,
        sliced: true,
        selected: true
      }, {
        name: 'KELUARGA TNI AL',
        y: <?php echo  implode("','", $jumlah_keluarga_tni_al) ?>,
        sliced: true,
        selected: true
      }, {
        name: 'KELUARGA TNI AU',
        y: <?php echo  implode("','", $jumlah_keluarga_tni_au) ?>,
        sliced: true,
        selected: true
      }, {
        name: 'KELUARGA PNS TNI AD',
        y: <?php echo  implode("','", $jumlah_keluarga_pns_tni_ad) ?>,
        sliced: true,
        selected: true
      }, {
        name: 'KELUARGA PNS TNI AU',
        y: <?php echo  implode("','", $jumlah_keluarga_pns_tni_au) ?>,
        sliced: true,
        selected: true
      }, {
        name: 'KELUARGA PNS TNI AL',
        y: <?php echo  implode("','", $jumlah_keluarga_pns_tni_al) ?>
      }]
    }]
  });
</script>

<!-- Chart pasien Baru -->
<script>
  var chart = Highcharts.chart('pasien-baru', {

    chart: {
      type: 'column'
    },

    title: {
      text: 'Pasien Baru Poliklinik'
    },

    subtitle: {
        text:
        '<?php echo "Update " . date("Y/m/d")  ?>'
         },

    legend: {
      align: 'right',
      verticalAlign: 'middle',
      layout: 'vertical'
    },

    xAxis: {
      categories: ['<?php echo  implode("','", $tanggal_jenis_pasien_poli) ?>'],
      labels: {
        x: -10
      }
    },

    yAxis: {
      allowDecimals: false,
      title: {
        text: 'Jumlah'
      }
    },

    series: [{
      name: 'Anak',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_anak) ?>]
    }, {
      name: 'Bedah',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_bedah) ?>]
    }, {
      name: 'Bedah Anak',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_bedah_anak) ?>]
    }, {
      name: 'Bedah Digestif',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_bedah_digestif) ?>]
    }, {
      name: 'Bedah Mulut',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_bedah_mulut) ?>]
    }, {
      name: 'Bedah Ongkologi',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_bedah_ongkologi) ?>]
    }, {
      name: 'Bedah Saraf',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_bedah_saraf) ?>]
    }, {
      name: 'Bedah Vascular',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_bedah_vascular) ?>]
    }, {
      name: 'Gigi',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_gigi) ?>]
    }, {
      name: 'Ginjal Hepertensi',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_ginjal_hipertensi) ?>]
    }, {
      name: 'Gizi Klinik',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_gizi) ?>]
    }, {
      name: 'Hemodelisa',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_hdl) ?>]
    }, {
      name: 'Jantung & Pembuluh Darah',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_jantung) ?>]
    }, {
      name: 'Jiwa',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_jiwa) ?>]
    }, {
      name: 'Konservasi Gigi',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_konvervasi_gigi) ?>]
    }, {
      name: 'Kulit Kelamin',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_klt) ?>]
    }, {
      name: 'Mata',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_mata) ?>]
    }, {
      name: 'MCU',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_mcu) ?>]
    }, {
      name: 'Obgyn',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_obgyn) ?>]
    }, {
      name: 'Orthopedi',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_ortopedi) ?>]
    }, {
      name: 'Paru',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_paru) ?>]
    }, {
      name: 'Penyakit Dalam',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_int) ?>]
    }, {
      name: 'Rehabiltasi Medik',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_rehabmedik) ?>]
    }, {
      name: 'Saraf',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_saraf) ?>]
    }, {
      name: 'THT',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_tht) ?>]
    }, {
      name: 'Urologi',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_uro) ?>]
    }, {
      name: 'VCT',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_vct) ?>]
    }],

    responsive: {
      rules: [{
        condition: {
          maxWidth: 500
        },
        chartOptions: {
          legend: {
            align: 'center',
            verticalAlign: 'bottom',
            layout: 'horizontal'
          },
          yAxis: {
            labels: {
              align: 'left',
              x: 0,
              y: -5
            },
            title: {
              text: null
            }
          },
          subtitle: {
            text: null
          },
          credits: {
            enabled: false
          }
        }
      }]
    }
  });
</script>

<!-- Chart Jenis pasien Lama-->
<script>
  var chart = Highcharts.chart('pasien-lama', {

    chart: {
      type: 'column'
    },

    title: {
      text: 'Pasien Lama Poliklinik'
    },

    subtitle: {
        text:
        '<?php echo "Update " . date("Y/m/d")  ?>'
         },


    legend: {
      align: 'right',
      verticalAlign: 'middle',
      layout: 'vertical'
    },

    xAxis: {
      categories: ['<?php echo  implode("','", $tanggal_jenis_pasien_lama_poli) ?>'],
      labels: {
        x: -10
      }
    },

    yAxis: {
      allowDecimals: false,
      title: {
        text: 'Jumlah'
      }
    },

    series: [{
      name: 'Anak',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_lama_anak) ?>]
    }, {
      name: 'Bedah',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_lama_bedah) ?>]
    }, {
      name: 'Bedah Anak',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_lama_bedah_anak) ?>]
    }, {
      name: 'Bedah Digestif',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_lama_bedah_digestif) ?>]
    }, {
      name: 'Bedah Mulut',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_lama_bedah_mulut) ?>]
    }, {
      name: 'Bedah Ongkologi',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_lama_bedah_ongkologi) ?>]
    }, {
      name: 'Bedah Saraf',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_lama_bedah_saraf) ?>]
    }, {
      name: 'Bedah Vascular',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_lama_bedah_vascular) ?>]
    }, {
      name: 'Gigi',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_lama_gigi) ?>]
    }, {
      name: 'Ginjal Hepertensi',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_lama_ginjal_hipertensi) ?>]
    }, {
      name: 'Gizi Klinik',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_lama_gizi) ?>]
    }, {
      name: 'Hemodelisa',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_lama_hdl) ?>]
    }, {
      name: 'Jantung & Pembuluh Darah',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_lama_jantung) ?>]
    }, {
      name: 'Jiwa',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_lama_jiwa) ?>]
    }, {
      name: 'Konservasi Gigi',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_lama_konvervasi_gigi) ?>]
    }, {
      name: 'Kulit Kelamin',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_lama_klt) ?>]
    }, {
      name: 'Mata',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_lama_mata) ?>]
    }, {
      name: 'MCU',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_lama_mcu) ?>]
    }, {
      name: 'Obgyn',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_lama_obgyn) ?>]
    }, {
      name: 'Orthopedi',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_lama_ortopedi) ?>]
    }, {
      name: 'Paru',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_lama_paru) ?>]
    }, {
      name: 'Penyakit Dalam',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_lama_int) ?>]
    }, {
      name: 'Rehabiltasi Medik',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_lama_rehabmedik) ?>]
    }, {
      name: 'Saraf',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_lama_saraf) ?>]
    }, {
      name: 'THT',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_lama_tht) ?>]
    }, {
      name: 'Urologi',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_lama_uro) ?>]
    }, {
      name: 'VCT',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_lama_vct) ?>]
    }],

    responsive: {
      rules: [{
        condition: {
          maxWidth: 500
        },
        chartOptions: {
          legend: {
            align: 'center',
            verticalAlign: 'bottom',
            layout: 'horizontal'
          },
          yAxis: {
            labels: {
              align: 'left',
              x: 0,
              y: -5
            },
            title: {
              text: null
            }
          },
          subtitle: {
            text: null
          },
          credits: {
            enabled: false
          }
        }
      }]
    }
  });
</script>

<!-- Chart Pasien Batal-->
<script>
  var chart = Highcharts.chart('pasien-batal', {

    chart: {
      type: 'column'
    },

    title: {
      text: 'Pasien Batal Poliklinik'
    },

    subtitle: {
        text:
        '<?php echo "Update " . date("Y/m/d")  ?>'
         },

    legend: {
      align: 'right',
      verticalAlign: 'middle',
      layout: 'vertical'
    },

    xAxis: {
      categories: ['<?php echo  implode("','", $tanggal_jenis_pasien_batal_poli) ?>'],
      labels: {
        x: -10
      }
    },

    yAxis: {
      allowDecimals: false,
      title: {
        text: 'Jumlah'
      }
    },

    series: [{
      name: 'Anak',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_batal_anak) ?>]
    }, {
      name: 'Bedah',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_batal_bedah) ?>]
    }, {
      name: 'Bedah Anak',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_batal_bedah_anak) ?>]
    }, {
      name: 'Bedah Digestif',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_batal_bedah_digestif) ?>]
    }, {
      name: 'Bedah Mulut',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_batal_bedah_mulut) ?>]
    }, {
      name: 'Bedah Ongkologi',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_batal_bedah_ongkologi) ?>]
    }, {
      name: 'Bedah Saraf',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_batal_bedah_saraf) ?>]
    }, {
      name: 'Bedah Vascular',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_batal_bedah_vascular) ?>]
    }, {
      name: 'Gigi',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_batal_gigi) ?>]
    }, {
      name: 'Ginjal Hepertensi',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_batal_ginjal_hipertensi) ?>]
    }, {
      name: 'Gizi Klinik',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_batal_gizi) ?>]
    }, {
      name: 'Hemodelisa',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_batal_hdl) ?>]
    }, {
      name: 'Jantung & Pembuluh Darah',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_batal_jantung) ?>]
    }, {
      name: 'Jiwa',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_batal_jiwa) ?>]
    }, {
      name: 'Konservasi Gigi',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_batal_konvervasi_gigi) ?>]
    }, {
      name: 'Kulit Kelamin',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_batal_klt) ?>]
    }, {
      name: 'Mata',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_batal_mata) ?>]
    }, {
      name: 'MCU',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_batal_mcu) ?>]
    }, {
      name: 'Obgyn',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_batal_obgyn) ?>]
    }, {
      name: 'Orthopedi',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_batal_ortopedi) ?>]
    }, {
      name: 'Paru',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_batal_paru) ?>]
    }, {
      name: 'Penyakit Dalam',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_batal_int) ?>]
    }, {
      name: 'Rehabiltasi Medik',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_batal_rehabmedik) ?>]
    }, {
      name: 'Saraf',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_batal_saraf) ?>]
    }, {
      name: 'THT',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_batal_tht) ?>]
    }, {
      name: 'Urologi',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_batal_uro) ?>]
    }, {
      name: 'VCT',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_batal_vct) ?>]
    }],

    responsive: {
      rules: [{
        condition: {
          maxWidth: 500
        },
        chartOptions: {
          legend: {
            align: 'center',
            verticalAlign: 'bottom',
            layout: 'horizontal'
          },
          yAxis: {
            labels: {
              align: 'left',
              x: 0,
              y: -5
            },
            title: {
              text: null
            }
          },
          subtitle: {
            text: null
          },
          credits: {
            enabled: false
          }
        }
      }]
    }
  });
</script>

<!-- Chart Pasien Online-->
<script>
  var chart = Highcharts.chart('pasien-online', {

    chart: {
      type: 'column'
    },

    title: {
      text: 'Chart Pasien Daftar Online'
    },

    subtitle: {
      text: ''
    },

    legend: {
      align: 'right',
      verticalAlign: 'middle',
      layout: 'vertical'
    },

    xAxis: {
      categories: ['<?php echo  implode("','", $tanggal_jenis_pasien_online_poli) ?>'],
      labels: {
        x: -10
      }
    },

    yAxis: {
      allowDecimals: false,
      title: {
        text: 'Jumlah'
      }
    },

    series: [{
      name: 'Anak',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_online_anak) ?>]
    }, {
      name: 'Bedah',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_online_bedah) ?>]
    }, {
      name: 'Bedah Anak',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_online_bedah_anak) ?>]
    }, {
      name: 'Bedah Digestif',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_online_bedah_digestif) ?>]
    }, {
      name: 'Bedah Mulut',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_online_bedah_mulut) ?>]
    }, {
      name: 'Bedah Ongkologi',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_online_bedah_ongkologi) ?>]
    }, {
      name: 'Bedah Saraf',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_online_bedah_saraf) ?>]
    }, {
      name: 'Bedah Vascular',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_online_bedah_vascular) ?>]
    }, {
      name: 'Gigi',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_online_gigi) ?>]
    }, {
      name: 'Ginjal Hepertensi',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_online_ginjal_hipertensi) ?>]
    }, {
      name: 'Gizi Klinik',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_online_gizi) ?>]
    }, {
      name: 'Hemodelisa',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_online_hdl) ?>]
    }, {
      name: 'Jantung & Pembuluh Darah',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_online_jantung) ?>]
    }, {
      name: 'Jiwa',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_online_jiwa) ?>]
    }, {
      name: 'Konservasi Gigi',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_online_konvervasi_gigi) ?>]
    }, {
      name: 'Kulit Kelamin',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_online_klt) ?>]
    }, {
      name: 'Mata',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_online_mata) ?>]
    }, {
      name: 'MCU',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_online_mcu) ?>]
    }, {
      name: 'Obgyn',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_online_obgyn) ?>]
    }, {
      name: 'Orthopedi',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_online_ortopedi) ?>]
    }, {
      name: 'Paru',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_online_paru) ?>]
    }, {
      name: 'Penyakit Dalam',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_online_int) ?>]
    }, {
      name: 'Rehabiltasi Medik',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_online_rehabmedik) ?>]
    }, {
      name: 'Saraf',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_online_saraf) ?>]
    }, {
      name: 'THT',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_online_tht) ?>]
    }, {
      name: 'Urologi',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_online_uro) ?>]
    }, {
      name: 'VCT',
      data: [<?php echo  implode("','", $Jumlah_jenis_pasien_poli_online_vct) ?>]
    }],

    responsive: {
      rules: [{
        condition: {
          maxWidth: 500
        },
        chartOptions: {
          legend: {
            align: 'center',
            verticalAlign: 'bottom',
            layout: 'horizontal'
          },
          yAxis: {
            labels: {
              align: 'left',
              x: 0,
              y: -5
            },
            title: {
              text: null
            }
          },
          subtitle: {
            text: null
          },
          credits: {
            enabled: false
          }
        }
      }]
    }
  });
</script>

<!-- datapickter -->
<script type="text/javascript">
  $(function() {
    $(".datepicker").datepicker({
      format: 'dd-mm-yyyy',
      autoclose: true,
      todayHighlight: false,
    });

    $("#tgl_mulai").on('changeDate', function(selected) {
      var startDate = new Date(selected.date.valueOf());
      $("#tgl_akhir").datepicker('setStartDate', startDate);
      if ($("#tgl_mulai").val() > $("#tgl_akhir").val()) {
        $("#tgl_akhir").val($("#tgl_mulai").val());
      }
    });
  });
</script>

<script>
  $(document).on("change", "select#filterdokter", function() {
    employee_filter($(this).val());
  });
</script>