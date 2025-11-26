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
<!-- disable error reporting -->
<?php error_reporting(0); ?>
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
        <!-- baris pertama) -->
        <div class="row">
          <div class="col-md-2">
            <div class="card card-info shadow-none">
              <div class="card-header">
                <h3 class="card-title">Klinik Anak</h3>
                <div class="card-tools">
                  <button type="button" class="btn btn-tool" data-card-widget="collapse">
                    <i class="fas fa-minus"></i>
                  </button>
                </div>
                <!-- /.card-tools -->
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <div class="row">
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='ANA' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-plus-square" aria-hidden="true"></i>&nbsp;Terdaftar</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <!-- /.col -->
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.stts='Sudah' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='ANA' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-check-square" aria-hidden="true"></i>&nbsp;Terlayani</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <?php
                  $jumlah_antrian_anak = array();
                  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.kd_dokter,poliklinik.nm_poli
                  FROM reg_periksa,dokter,poliklinik
                  WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli AND reg_periksa.status_lanjut='Ralan'
                  AND reg_periksa.kd_poli='ANA' AND reg_periksa.tgl_registrasi=CURDATE()
                  GROUP BY reg_periksa.kd_dokter");
                  //urutab tampilkan hasil query dari database
                  while ($row = mysqli_fetch_array($result)) {
                    $dokter_anak[] = $row[1];
                  }
                  ?>
                  <span class="count_top "><i class="fa fa-stethoscope "></i> <small><?php echo  implode("<br>", $dokter_anak) ?></small></span>
                </div>
                <!-- /.card-body -->
              </div>
              <!-- /.card -->
            </div>
          </div>

          <div class="col-md-2">
            <div class="card card-info shadow-none">
              <div class="card-header">
                <h3 class="card-title">Klinik Bedah</h3>
                <div class="card-tools">
                  <button type="button" class="btn btn-tool" data-card-widget="collapse">
                    <i class="fas fa-minus"></i>
                  </button>
                </div>
                <!-- /.card-tools -->
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <div class="row">
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND  reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='BED' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-plus-square" aria-hidden="true"></i>&nbsp;Terdaftar</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <!-- /.col -->
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.stts='Sudah' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='BED' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-check-square" aria-hidden="true"></i>&nbsp;Terlayani</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <?php
                  $jumlah_bedah = array();
                  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.kd_dokter,poliklinik.nm_poli
                  FROM reg_periksa,dokter,poliklinik
                  WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli AND reg_periksa.status_lanjut='Ralan'
                  AND reg_periksa.kd_poli='BED' AND reg_periksa.tgl_registrasi=CURDATE()
                  GROUP BY reg_periksa.kd_dokter");
                  //urutab tampilkan hasil query dari database
                  while ($row = mysqli_fetch_array($result)) {
                    $dokter_bedah[] = $row[1];
                  }
                  ?>
                  <span class="count_top "><i class="fa fa-stethoscope "></i> <small><?php echo  implode("<br>", $dokter_bedah) ?> </small></span>
                </div>
                <!-- /.card-body -->
              </div>
              <!-- /.card -->
            </div>
          </div>

          <div class="col-md-2">
            <div class="card card-info shadow-none">
              <div class="card-header">
                <h3 class="card-title">Klinik Bedah Anak</h3>
                <div class="card-tools">
                  <button type="button" class="btn btn-tool" data-card-widget="collapse">
                    <i class="fas fa-minus"></i>
                  </button>
                </div>
                <!-- /.card-tools -->
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <div class="row">
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='BDA' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-plus-square" aria-hidden="true"></i>&nbsp;Terdaftar</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <!-- /.col -->
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.stts='Sudah' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='BDA' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-check-square" aria-hidden="true"></i>&nbsp;Terlayani</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <?php
                  $jumlah_bedah_anak = array();
                  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.kd_dokter,poliklinik.nm_poli
                  FROM reg_periksa,dokter,poliklinik
                  WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli AND reg_periksa.status_lanjut='Ralan'
                  AND reg_periksa.kd_poli='BDA' AND reg_periksa.tgl_registrasi=CURDATE()
                  GROUP BY reg_periksa.kd_dokter");
                  //urutab tampilkan hasil query dari database
                  while ($row = mysqli_fetch_array($result)) {
                    $dokter_bedah_anak[] = $row[1];
                  }
                  ?>
                  <span class="count_top "><i class="fa fa-stethoscope "></i> <small><?php echo  implode("<br>", $dokter_bedah_anak) ?> </small></span>
                </div>
                <!-- /.card-body -->
              </div>
              <!-- /.card -->
            </div>
          </div>

          <div class="col-md-2">
            <div class="card card-info shadow-none">
              <div class="card-header">
                <h3 class="card-title">Klinik Bedah Digestif</h3>
                <div class="card-tools">
                  <button type="button" class="btn btn-tool" data-card-widget="collapse">
                    <i class="fas fa-minus"></i>
                  </button>
                </div>
                <!-- /.card-tools -->
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <div class="row">
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='018' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-plus-square" aria-hidden="true"></i>&nbsp;Terdaftar</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <!-- /.col -->
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.stts='Sudah' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='018' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-check-square" aria-hidden="true"></i>&nbsp;Terlayani</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <?php
                  $jumlah_degestif = array();
                  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.kd_dokter,poliklinik.nm_poli
                  FROM reg_periksa,dokter,poliklinik
                  WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli AND reg_periksa.status_lanjut='Ralan'
                  AND reg_periksa.kd_poli='018' AND reg_periksa.tgl_registrasi=CURDATE()
                  GROUP BY reg_periksa.kd_dokter");
                  //urutab tampilkan hasil query dari database
                  while ($row = mysqli_fetch_array($result)) {
                    $dokter_degestif[] = $row[1];
                  }
                  ?>
                  <span class="count_top "><i class="fa fa-stethoscope "></i> <small><?php echo  implode("<br>", $dokter_degestif) ?></small></span>
                </div>
                <!-- /.card-body -->
              </div>
              <!-- /.card -->
            </div>
          </div>

          <div class="col-md-2">
            <div class="card card-info shadow-none">
              <div class="card-header">
                <h3 class="card-title">Klinik Bedah Mulut</h3>
                <div class="card-tools">
                  <button type="button" class="btn btn-tool" data-card-widget="collapse">
                    <i class="fas fa-minus"></i>
                  </button>
                </div>
                <!-- /.card-tools -->
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <div class="row">
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='BDM' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-plus-square" aria-hidden="true"></i>&nbsp;Terdaftar</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <!-- /.col -->
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.stts='Sudah' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='BDM' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-check-square" aria-hidden="true"></i>&nbsp;Terlayani</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <?php
                  $jumlah_bedah_mulut = array();
                  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.kd_dokter,poliklinik.nm_poli
                  FROM reg_periksa,dokter,poliklinik
                  WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli AND reg_periksa.status_lanjut='Ralan'
                  AND reg_periksa.kd_poli='BDM' AND reg_periksa.tgl_registrasi=CURDATE()
                  GROUP BY reg_periksa.kd_dokter ");
                  //urutab tampilkan hasil query dari database
                  while ($row = mysqli_fetch_array($result)) {
                    $dokter_mulut[] = $row[1];
                  }
                  ?>
                  <span class="count_top "><i class="fa fa-stethoscope "></i> <small><?php echo  implode("<br>", $dokter_mulut) ?></small></span>
                </div>
                <!-- /.card-body -->
              </div>
              <!-- /.card -->
            </div>
          </div>

          <div class="col-md-2">
            <div class="card card-info shadow-none">
              <div class="card-header">
                <h3 class="card-title">Klinik Ongkologi</h3>
                <div class="card-tools">
                  <button type="button" class="btn btn-tool" data-card-widget="collapse">
                    <i class="fas fa-minus"></i>
                  </button>
                </div>
                <!-- /.card-tools -->
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <div class="row">
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='017' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-plus-square" aria-hidden="true"></i>&nbsp;Terdaftar</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <!-- /.col -->
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.stts='Belum' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='017' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-check-square" aria-hidden="true"></i>&nbsp;Terlayani</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <?php
                  $jumlah_ongkologi = array();
                  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.kd_dokter,poliklinik.nm_poli
                  FROM reg_periksa,dokter,poliklinik
                  WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli AND reg_periksa.status_lanjut='Ralan'
                  AND reg_periksa.kd_poli='017' AND reg_periksa.tgl_registrasi=CURDATE()
                  GROUP BY reg_periksa.kd_dokter ");
                  //urutab tampilkan hasil query dari database
                  while ($row = mysqli_fetch_array($result)) {
                    $dokter_ongkologi[] = $row[1];
                  }
                  ?>
                  <span class="count_top "><i class="fa fa-stethoscope "></i> <small><?php echo  implode("<br>", $dokter_ongkologi) ?></small></span>
                </div>
                <!-- /.card-body -->
              </div>
              <!-- /.card -->
            </div>
          </div>

          <!-- penutup baris pertama -->
        </div>

        <!-- baris kedua -->

        <div class="row">
          <div class="col-md-2">
            <div class="card card-primary shadow-none">
              <div class="card-header">
                <h3 class="card-title">Klinik Bedah Saraf</h3>
                <div class="card-tools">
                  <button type="button" class="btn btn-tool" data-card-widget="collapse">
                    <i class="fas fa-minus"></i>
                  </button>
                </div>
                <!-- /.card-tools -->
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <div class="row">
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='BSY' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-plus-square" aria-hidden="true"></i>&nbsp;Terdaftar</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <!-- /.col -->
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.stts='Sudah' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='BSY' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-check-square" aria-hidden="true"></i>&nbsp;Terlayani</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <?php
                  $jumlah_bedah_saraf = array();
                  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.kd_dokter,poliklinik.nm_poli
                  FROM reg_periksa,dokter,poliklinik
                  WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli AND reg_periksa.status_lanjut='Ralan'
                  AND reg_periksa.kd_poli='BSY' and reg_periksa.tgl_registrasi=CURDATE()
                  GROUP BY reg_periksa.kd_dokter ");
                  //urutab tampilkan hasil query dari database
                  while ($row = mysqli_fetch_array($result)) {
                    $dokter_bedah_saraf[] = $row[1];
                  }
                  ?>
                  <span class="count_top "><i class="fa fa-stethoscope "></i> <small><?php echo  implode("<br>", $dokter_bedah_saraf) ?> </small></span>
                </div>
                <!-- /.card-body -->
              </div>
              <!-- /.card -->
            </div>
          </div>

          <div class="col-md-2">
            <div class="card card-primary shadow-none">
              <div class="card-header">
                <h3 class="card-title">Klinik Vascular</h3>
                <div class="card-tools">
                  <button type="button" class="btn btn-tool" data-card-widget="collapse">
                    <i class="fas fa-minus"></i>
                  </button>
                </div>
                <!-- /.card-tools -->
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <div class="row">
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='U0036' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-plus-square" aria-hidden="true"></i>&nbsp;Terdaftar</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <!-- /.col -->
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.stts='Sudah' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='U0036' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-check-square" aria-hidden="true"></i>&nbsp;Terlayani</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <?php
                  $jumlah_vascular = array();
                  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.kd_dokter,poliklinik.nm_poli
                  FROM reg_periksa,dokter,poliklinik
                  WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli AND reg_periksa.status_lanjut='Ralan'
                  AND reg_periksa.kd_poli='U0036' AND reg_periksa.tgl_registrasi=CURDATE()
                  GROUP BY reg_periksa.kd_dokter ");
                  //urutab tampilkan hasil query dari database
                  while ($row = mysqli_fetch_array($result)) {
                    $dokter_vascular[] = $row[1];
                  }
                  ?>
                  <span class="count_top "><i class="fa fa-stethoscope "></i> <small><?php echo  implode("<br>", $dokter_vascular) ?> </small></span>
                </div>
                <!-- /.card-body -->
              </div>
              <!-- /.card -->
            </div>
          </div>

          <div class="col-md-2">
            <div class="card card-primary shadow-none">
              <div class="card-header">
                <h3 class="card-title">Klinik Gigi</h3>
                <div class="card-tools">
                  <button type="button" class="btn btn-tool" data-card-widget="collapse">
                    <i class="fas fa-minus"></i>
                  </button>
                </div>
                <!-- /.card-tools -->
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <div class="row">
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='U0041' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-plus-square" aria-hidden="true"></i>&nbsp;Terdaftar</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <!-- /.col -->
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.stts='Sudah' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='U0041' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-check-square" aria-hidden="true"></i>&nbsp;Terlayani</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <?php
                  $jumlah_gigi = array();
                  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.kd_dokter,poliklinik.nm_poli
                  FROM reg_periksa,dokter,poliklinik
                  WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli AND reg_periksa.status_lanjut='Ralan'
                  AND reg_periksa.kd_poli='U0041' AND reg_periksa.tgl_registrasi=CURDATE()
                  GROUP BY reg_periksa.kd_dokter ");
                  //urutab tampilkan hasil query dari database
                  while ($row = mysqli_fetch_array($result)) {
                    $dokter_gigi[] = $row[1];
                  }
                  ?>
                  <span class="count_top "><i class="fa fa-stethoscope "></i> <small><?php echo  implode("<br>", $dokter_gigi) ?> </small></span>
                </div>
                <!-- /.card-body -->
              </div>
              <!-- /.card -->
            </div>
          </div>

          <div class="col-md-2">
            <div class="card card-primary shadow-none">
              <div class="card-header">
                <h3 class="card-title">Klinik Ginjal Hipertensi</h3>
                <div class="card-tools">
                  <button type="button" class="btn btn-tool" data-card-widget="collapse">
                    <i class="fas fa-minus"></i>
                  </button>
                </div>
                <!-- /.card-tools -->
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <div class="row">
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='007' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-plus-square" aria-hidden="true"></i>&nbsp;Terdaftar</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <!-- /.col -->
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND  reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='007' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-check-square" aria-hidden="true"></i>&nbsp;Terlayani</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <?php
                  $jumlah_ginjal_hipertensi = array();
                  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.kd_dokter,poliklinik.nm_poli
                  FROM reg_periksa,dokter,poliklinik
                  WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli AND reg_periksa.status_lanjut='Ralan'
                  AND reg_periksa.kd_poli='007'  AND reg_periksa.tgl_registrasi=CURDATE()
                  GROUP BY reg_periksa.kd_dokter ");
                  //urutab tampilkan hasil query dari database
                  while ($row = mysqli_fetch_array($result)) {
                    $dokter_ginjal_hipertensi[] = $row[1];
                  }
                  ?>
                  <span class="count_top "><i class="fa fa-stethoscope "></i> <small><?php echo  implode("<br>", $dokter_ginjal_hipertensi) ?> </small></span>
                </div>
                <!-- /.card-body -->
              </div>
              <!-- /.card -->
            </div>
          </div>

          <div class="col-md-2">
            <div class="card card-primary shadow-none">
              <div class="card-header">
                <h3 class="card-title">Klinik Gizi </h3>
                <div class="card-tools">
                  <button type="button" class="btn btn-tool" data-card-widget="collapse">
                    <i class="fas fa-minus"></i>
                  </button>
                </div>
                <!-- /.card-tools -->
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <div class="row">
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='GIZ' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-plus-square" aria-hidden="true"></i>&nbsp;Terdaftar</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <!-- /.col -->
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.stts='Sudah' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='GIZ' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-check-square" aria-hidden="true"></i>&nbsp;Terlayani</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <?php
                  $jumlah_gizi = array();
                  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.kd_dokter,poliklinik.nm_poli
                  FROM reg_periksa,dokter,poliklinik
                  WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli AND reg_periksa.status_lanjut='Ralan'
                  AND reg_periksa.kd_poli='GIZ' AND reg_periksa.tgl_registrasi=CURDATE()
                  GROUP BY reg_periksa.kd_dokter ");
                  //urutab tampilkan hasil query dari database
                  while ($row = mysqli_fetch_array($result)) {
                    $dokter_gizi[] = $row[1];
                  }
                  ?>
                  <span class="count_top "><i class="fa fa-stethoscope "></i> <small><?php echo  implode("<br>", $dokter_gizi) ?> </small></span>
                </div>
                <!-- /.card-body -->
              </div>
              <!-- /.card -->
            </div>
          </div>

          <div class="col-md-2">
            <div class="card card-primary shadow-none">
              <div class="card-header">
                <h3 class="card-title">Klinik Jantung</h3>
                <div class="card-tools">
                  <button type="button" class="btn btn-tool" data-card-widget="collapse">
                    <i class="fas fa-minus"></i>
                  </button>
                </div>
                <!-- /.card-tools -->
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <div class="row">
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='JAN' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-plus-square" aria-hidden="true"></i>&nbsp;Terdaftar</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <!-- /.col -->
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.stts='Sudah' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='JAN' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-check-square" aria-hidden="true"></i>&nbsp;Terlayani</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <?php
                  $jumlah_jantung = array();
                  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.kd_dokter,poliklinik.nm_poli
                  FROM reg_periksa,dokter,poliklinik
                  WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli AND reg_periksa.status_lanjut='Ralan'
                  AND reg_periksa.kd_poli='JAN' AND reg_periksa.tgl_registrasi=CURDATE()
                  GROUP BY reg_periksa.kd_dokter ");
                  //urutab tampilkan hasil query dari database
                  while ($row = mysqli_fetch_array($result)) {
                    $dokter_jantung[] = $row[1];
                  }
                  ?>
                  <span class="count_top "><i class="fa fa-stethoscope "></i> <small><?php echo  implode("<br>", $dokter_jantung) ?> </small></span>
                </div>
                <!-- /.card-body -->
              </div>
              <!-- /.card -->
            </div>
          </div>

          <!-- penutup baris ketiga-->
        </div>

        <!-- baris keempat-->

        <div class="row">
          <div class="col-md-2">
            <div class="card card-success shadow-none">
              <div class="card-header">
                <h3 class="card-title">Klinik Jiwa</h3>
                <div class="card-tools">
                  <button type="button" class="btn btn-tool" data-card-widget="collapse">
                    <i class="fas fa-minus"></i>
                  </button>
                </div>
                <!-- /.card-tools -->
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <div class="row">
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='JIW' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-plus-square" aria-hidden="true"></i>&nbsp;Terdaftar</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <!-- /.col -->
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.stts='Sudah' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='JIW' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-check-square" aria-hidden="true"></i>&nbsp;Terlayani</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <?php
                  $jumlah_jiwa = array();
                  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.kd_dokter,poliklinik.nm_poli
                  FROM reg_periksa,dokter,poliklinik
                  WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli AND reg_periksa.status_lanjut='Ralan'
                  AND reg_periksa.kd_poli='JIW' AND reg_periksa.tgl_registrasi=CURDATE()
                  GROUP BY reg_periksa.kd_dokter ");
                  //urutab tampilkan hasil query dari database
                  while ($row = mysqli_fetch_array($result)) {
                    $dokter_jiwa[] = $row[1];
                  }
                  ?>
                  <span class="count_top "><i class="fa fa-stethoscope "></i> <small><?php echo  implode("<br>", $dokter_jiwa) ?> </small></span>
                </div>
                <!-- /.card-body -->
              </div>
              <!-- /.card -->
            </div>
          </div>

          <div class="col-md-2">
            <div class="card card-success shadow-none">
              <div class="card-header">
                <h3 class="card-title">Klinik konservasi Gigi</h3>
                <div class="card-tools">
                  <button type="button" class="btn btn-tool" data-card-widget="collapse">
                    <i class="fas fa-minus"></i>
                  </button>
                </div>
                <!-- /.card-tools -->
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <div class="row">
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='GND' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-plus-square" aria-hidden="true"></i>&nbsp;Terdaftar</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <!-- /.col -->
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.stts='Sudah' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='GND' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-check-square" aria-hidden="true"></i>&nbsp;Terlayani</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <?php
                  $jumlah_konservasi_gigi = array();
                  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.kd_dokter,poliklinik.nm_poli
                  FROM reg_periksa,dokter,poliklinik
                  WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli AND reg_periksa.status_lanjut='Ralan'
                  AND reg_periksa.kd_poli='GND' AND reg_periksa.tgl_registrasi=CURDATE()
                  GROUP BY reg_periksa.kd_dokter ");
                  //urutab tampilkan hasil query dari database
                  while ($row = mysqli_fetch_array($result)) {
                    $dokter_konservasi_gigi[] = $row[1];
                  }
                  ?>
                  <span class="count_top "><i class="fa fa-stethoscope "></i> <small><?php echo  implode("<br>", $dokter_konservasi_gigi) ?> </small></span>
                </div>
                <!-- /.card-body -->
              </div>
              <!-- /.card -->
            </div>
          </div>

          <div class="col-md-2">
            <div class="card card-success shadow-none">
              <div class="card-header">
                <h3 class="card-title">Klinik Kulit Kelamin</h3>
                <div class="card-tools">
                  <button type="button" class="btn btn-tool" data-card-widget="collapse">
                    <i class="fas fa-minus"></i>
                  </button>
                </div>
                <!-- /.card-tools -->
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <div class="row">
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='KLT' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-plus-square" aria-hidden="true"></i>&nbsp;Terdaftar</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <!-- /.col -->
                  <div class="col-sm-6 border-right">
                    <div class="description-block"><?php
                                                    $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.stts='Sudah' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='KLT' AND reg_periksa.tgl_registrasi=CURDATE()");
                                                    $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                                                    ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-check-square" aria-hidden="true"></i>&nbsp;Terlayani</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <?php
                  $jumlah_kulit_kelamin = array();
                  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.kd_dokter,poliklinik.nm_poli
                  FROM reg_periksa,dokter,poliklinik
                  WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli AND reg_periksa.status_lanjut='Ralan'
                  AND reg_periksa.kd_poli='KLT' AND reg_periksa.tgl_registrasi=CURDATE()
                  GROUP BY reg_periksa.kd_dokter ");
                  //urutab tampilkan hasil query dari database
                  while ($row = mysqli_fetch_array($result)) {
                    $dokter_kulit_kelamin[] = $row[1];
                  }
                  ?>
                  <span class="count_top "><i class="fa fa-stethoscope "></i> <small><?php echo  implode("<br>", $dokter_kulit_kelamin) ?> </small></span>
                </div>
                <!-- /.card-body -->
              </div>
              <!-- /.card -->
            </div>
          </div>

          <div class="col-md-2">
            <div class="card card-success shadow-none">
              <div class="card-header">
                <h3 class="card-title">Klinik Mata</h3>
                <div class="card-tools">
                  <button type="button" class="btn btn-tool" data-card-widget="collapse">
                    <i class="fas fa-minus"></i>
                  </button>
                </div>
                <!-- /.card-tools -->
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <div class="row">
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='MAT' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-plus-square" aria-hidden="true"></i>&nbsp;Terdaftar</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <!-- /.col -->
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.stts='Sudah' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='MAT' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-check-square" aria-hidden="true"></i>&nbsp;Terlayani</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <?php
                  $jumlah_mata = array();
                  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.kd_dokter,poliklinik.nm_poli
                  FROM reg_periksa,dokter,poliklinik
                  WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli AND reg_periksa.status_lanjut='Ralan'
                  AND reg_periksa.kd_poli='MAT' AND reg_periksa.tgl_registrasi=CURDATE() 
                  GROUP BY reg_periksa.kd_dokter ");
                  //urutab tampilkan hasil query dari database
                  while ($row = mysqli_fetch_array($result)) {
                    $dokter_mata[] = $row[1];
                  }
                  ?>
                  <span class="count_top "><i class="fa fa-stethoscope "></i> <small><?php echo  implode("<br>", $dokter_mata) ?> </small></span>
                </div>
                <!-- /.card-body -->
              </div>
              <!-- /.card -->
            </div>
          </div>

          <div class="col-md-2">
            <div class="card card-success shadow-none">
              <div class="card-header">
                <h3 class="card-title">Klinik Obgyn </h3>
                <div class="card-tools">
                  <button type="button" class="btn btn-tool" data-card-widget="collapse">
                    <i class="fas fa-minus"></i>
                  </button>
                </div>
                <!-- /.card-tools -->
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <div class="row">
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND  reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='OBG' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-plus-square" aria-hidden="true"></i>&nbsp;Terdaftar</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <!-- /.col -->
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.stts='Sudah' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='OBG' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-check-square" aria-hidden="true"></i>&nbsp;Terlayani</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <?php
                  $jumlah_obgyn = array();
                  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.kd_dokter,poliklinik.nm_poli
                  FROM reg_periksa,dokter,poliklinik
                  WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli AND reg_periksa.status_lanjut='Ralan'
                  AND reg_periksa.kd_poli='OBG' AND reg_periksa.tgl_registrasi=CURDATE()
                  GROUP BY reg_periksa.kd_dokter ");
                  //urutab tampilkan hasil query dari database
                  while ($row = mysqli_fetch_array($result)) {
                    $dokter_obgyn[] = $row[1];
                  }
                  ?>
                  <span class="count_top "><i class="fa fa-stethoscope "></i> <small><?php echo  implode("<br>", $dokter_obgyn) ?> </small></span>
                </div>
                <!-- /.card-body -->
              </div>
              <!-- /.card -->
            </div>
          </div>

          <div class="col-md-2">
            <div class="card card-success shadow-none">
              <div class="card-header">
                <h3 class="card-title">Klinik Ortopedi</h3>
                <div class="card-tools">
                  <button type="button" class="btn btn-tool" data-card-widget="collapse">
                    <i class="fas fa-minus"></i>
                  </button>
                </div>
                <!-- /.card-tools -->
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <div class="row">
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND  reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='ORT' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-plus-square" aria-hidden="true"></i>&nbsp;Terdaftar</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <!-- /.col -->
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.stts='Sudah' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='ORT' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-check-square" aria-hidden="true"></i>&nbsp;Terlayani</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <?php
                  $jumlah_ortopedi = array();
                  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.kd_dokter,poliklinik.nm_poli
                  FROM reg_periksa,dokter,poliklinik
                  WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli AND reg_periksa.status_lanjut='Ralan'
                  AND reg_periksa.kd_poli='ORT' AND reg_periksa.tgl_registrasi=CURDATE()
                  GROUP BY reg_periksa.kd_dokter ");
                  //urutab tampilkan hasil query dari database
                  while ($row = mysqli_fetch_array($result)) {
                    $dokter_ortopedi[] = $row[1];
                  }
                  ?>
                  <span class="count_top "><i class="fa fa-stethoscope "></i> <small><?php echo  implode("<br>", $dokter_ortopedi) ?> </small></span>
                </div>
                <!-- /.card-body -->
              </div>
              <!-- /.card -->
            </div>
          </div>
          <!-- penutup baris Ketiga-->
        </div>
        <!-- baris Keempat-->
        <div class="row">
          <div class="col-md-2">
            <div class="card card-dark shadow-none">
              <div class="card-header">
                <h3 class="card-title">Klinik Paru</h3>
                <div class="card-tools">
                  <button type="button" class="btn btn-tool" data-card-widget="collapse">
                    <i class="fas fa-minus"></i>
                  </button>
                </div>
                <!-- /.card-tools -->
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <div class="row">
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='PAR' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-plus-square" aria-hidden="true"></i>&nbsp;Terdaftar</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <!-- /.col -->
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.stts='Sudah' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='PAR' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-check-square" aria-hidden="true"></i>&nbsp;Terlayani</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <?php
                  $jumlah_paru = array();
                  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.kd_dokter,poliklinik.nm_poli
                  FROM reg_periksa,dokter,poliklinik
                  WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli AND reg_periksa.status_lanjut='Ralan'
                  AND reg_periksa.kd_poli='PAR' AND reg_periksa.tgl_registrasi=CURDATE()
                  GROUP BY reg_periksa.kd_dokter ");
                  //urutab tampilkan hasil query dari database
                  while ($row = mysqli_fetch_array($result)) {
                    $dokter_paru[] = $row[1];
                  }
                  ?>
                  <span class="count_top "><i class="fa fa-stethoscope "></i> <small><?php echo  implode("<br>", $dokter_paru) ?> </small></span>
                </div>
                <!-- /.card-body -->
              </div>
              <!-- /.card -->
            </div>
          </div>

          <div class="col-md-2">
            <div class="card card-dark shadow-none">
              <div class="card-header">
                <h3 class="card-title">Klinik Penyakit Dalam</h3>
                <div class="card-tools">
                  <button type="button" class="btn btn-tool" data-card-widget="collapse">
                    <i class="fas fa-minus"></i>
                  </button>
                </div>
                <!-- /.card-tools -->
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <div class="row">
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND  reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='INT' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-plus-square" aria-hidden="true"></i>&nbsp;Terdaftar</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <!-- /.col -->
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.stts='Sudah' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='INT' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-check-square" aria-hidden="true"></i>&nbsp;Terlayani</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <?php
                  $jumlah_penyakit_dalam = array();
                  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.kd_dokter,poliklinik.nm_poli
                  FROM reg_periksa,dokter,poliklinik
                  WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli AND reg_periksa.status_lanjut='Ralan'
                  AND reg_periksa.kd_poli='INT' AND reg_periksa.tgl_registrasi=CURDATE()
                  GROUP BY reg_periksa.kd_dokter ");
                  //urutab tampilkan hasil query dari database
                  while ($row = mysqli_fetch_array($result)) {
                    $dokter_penyakit_dalam[] = $row[1];
                  }
                  ?>
                  <span class="count_top "><i class="fa fa-stethoscope "></i> <small><?php echo  implode("<br>", $dokter_penyakit_dalam) ?> </small></span>
                </div>
                <!-- /.card-body -->
              </div>
              <!-- /.card -->
            </div>
          </div>

          <div class="col-md-2">
            <div class="card card-dark shadow-none">
              <div class="card-header">
                <h3 class="card-title">Klinik Penyakit Jantung</h3>
                <div class="card-tools">
                  <button type="button" class="btn btn-tool" data-card-widget="collapse">
                    <i class="fas fa-minus"></i>
                  </button>
                </div>
                <!-- /.card-tools -->
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <div class="row">
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='JAN' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-plus-square" aria-hidden="true"></i>&nbsp;Terdaftar</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <!-- /.col -->
                  <div class="col-sm-6 border-right">
                    <div class="description-block"><?php
                                                    $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.stts='Sudah' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='JAN' AND reg_periksa.tgl_registrasi=CURDATE()");
                                                    $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                                                    ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-check-square" aria-hidden="true"></i>&nbsp;Terlayani</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <?php
                  $jumlah_penyakit_janting = array();
                  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.kd_dokter,poliklinik.nm_poli
                  FROM reg_periksa,dokter,poliklinik
                  WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli AND reg_periksa.status_lanjut='Ralan'
                  AND reg_periksa.kd_poli='JAN' AND reg_periksa.tgl_registrasi=CURDATE()
                  GROUP BY reg_periksa.kd_dokter ");
                  //urutab tampilkan hasil query dari database
                  while ($row = mysqli_fetch_array($result)) {
                    $dokter_penyakit_jantung[] = $row[1];
                  }
                  ?>
                  <span class="count_top "><i class="fa fa-stethoscope "></i> <small><?php echo  implode("<br>", $dokter_penyakit_jantung) ?> </small></span>
                </div>
                <!-- /.card-body -->
              </div>
              <!-- /.card -->
            </div>
          </div>

          <div class="col-md-2">
            <div class="card card-dark shadow-none">
              <div class="card-header">
                <h3 class="card-title">Klinik Saraf</h3>
                <div class="card-tools">
                  <button type="button" class="btn btn-tool" data-card-widget="collapse">
                    <i class="fas fa-minus"></i>
                  </button>
                </div>
                <!-- /.card-tools -->
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <div class="row">
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.stts='Belum' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='SAR' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-plus-square" aria-hidden="true"></i>&nbsp;Terdaftar</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <!-- /.col -->
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.stts='Sudah' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='SAR' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-check-square" aria-hidden="true"></i>&nbsp;Terlayani</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <?php
                  $jumlah_saraf = array();
                  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.kd_dokter,poliklinik.nm_poli
                  FROM reg_periksa,dokter,poliklinik
                  WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli AND reg_periksa.status_lanjut='Ralan'
                  AND reg_periksa.kd_poli='SAR' AND reg_periksa.tgl_registrasi=CURDATE() 
                  GROUP BY reg_periksa.kd_dokter ");
                  //urutab tampilkan hasil query dari database
                  while ($row = mysqli_fetch_array($result)) {
                    $dokter_saraf[] = $row[1];
                  }
                  ?>
                  <span class="count_top "><i class="fa fa-stethoscope "></i> <small><?php echo  implode("<br>", $dokter_saraf) ?> </small></span>
                </div>
                <!-- /.card-body -->
              </div>
              <!-- /.card -->
            </div>
          </div>

          <div class="col-md-2">
            <div class="card card-dark shadow-none">
              <div class="card-header">
                <h3 class="card-title">Klinik Tb Dots </h3>
                <div class="card-tools">
                  <button type="button" class="btn btn-tool" data-card-widget="collapse">
                    <i class="fas fa-minus"></i>
                  </button>
                </div>
                <!-- /.card-tools -->
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <div class="row">
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='TB' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-plus-square" aria-hidden="true"></i>&nbsp;Terdaftar</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <!-- /.col -->
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.stts='Sudah' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='TB' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-check-square" aria-hidden="true"></i>&nbsp;Terlayani</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <?php
                  $jumlah_tb_dots = array();
                  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.kd_dokter,poliklinik.nm_poli
                  FROM reg_periksa,dokter,poliklinik
                  WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli AND reg_periksa.status_lanjut='Ralan'
                  AND reg_periksa.kd_poli='TB' AND reg_periksa.tgl_registrasi=CURDATE()
                  GROUP BY reg_periksa.kd_dokter ");
                  //urutab tampilkan hasil query dari database
                  while ($row = mysqli_fetch_array($result)) {
                    $dokter_tb_dots[] = $row[1];
                  }
                  ?>
                  <span class="count_top "><i class="fa fa-stethoscope "></i> <small><?php echo  implode("<br>", $dokter_tb_dots) ?> </small></span>
                </div>
                <!-- /.card-body -->
              </div>
              <!-- /.card -->
            </div>
          </div>

          <div class="col-md-2">
            <div class="card card-dark shadow-none">
              <div class="card-header">
                <h3 class="card-title">Klinik THT KL</h3>
                <div class="card-tools">
                  <button type="button" class="btn btn-tool" data-card-widget="collapse">
                    <i class="fas fa-minus"></i>
                  </button>
                </div>
                <!-- /.card-tools -->
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <div class="row">
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND  reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='THT' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-plus-square" aria-hidden="true"></i>&nbsp;Terdaftar</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <!-- /.col -->
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.stts='Sudah' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='THT' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-check-square" aria-hidden="true"></i>&nbsp;Terlayani</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <?php
                  $jumlah_tht_kl = array();
                  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.kd_dokter,poliklinik.nm_poli
                  FROM reg_periksa,dokter,poliklinik
                  WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli AND reg_periksa.status_lanjut='Ralan'
                  AND reg_periksa.kd_poli='THT' AND reg_periksa.tgl_registrasi=CURDATE()
                  GROUP BY reg_periksa.kd_dokter ");
                  //urutab tampilkan hasil query dari database
                  while ($row = mysqli_fetch_array($result)) {
                    $dokter_tht_kl[] = $row[1];
                  }
                  ?>
                  <span class="count_top "><i class="fa fa-stethoscope "></i> <small><?php echo  implode("<br>", $dokter_tht_kl) ?> </small></span>
                </div>
                <!-- /.card-body -->
              </div>
              <!-- /.card -->
            </div>
          </div>

          <!-- penutup baris keempat-->
        </div>
        <!-- BARIS KELIMA -->
        <div class="row">
          <div class="col-md-2">
            <div class="card card-dark shadow-none">
              <div class="card-header">
                <h3 class="card-title">Klinik Urologi</h3>
                <div class="card-tools">
                  <button type="button" class="btn btn-tool" data-card-widget="collapse">
                    <i class="fas fa-minus"></i>
                  </button>
                </div>
                <!-- /.card-tools -->
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <div class="row">
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='URO' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-plus-square" aria-hidden="true"></i>&nbsp;Terdaftar</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <!-- /.col -->
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.stts='Sudah' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='URO' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-check-square" aria-hidden="true"></i>&nbsp;Terlayani</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <?php
                  $jumlah_paru = array();
                  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.kd_dokter,poliklinik.nm_poli
                  FROM reg_periksa,dokter,poliklinik
                  WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli AND reg_periksa.status_lanjut='Ralan'
                  AND reg_periksa.kd_poli='URO' AND reg_periksa.tgl_registrasi=CURDATE()
                  GROUP BY reg_periksa.kd_dokter ");
                  //urutab tampilkan hasil query dari database
                  while ($row = mysqli_fetch_array($result)) {
                    $dokter_uro[] = $row[1];
                  }
                  ?>
                  <span class="count_top "><i class="fa fa-stethoscope "></i> <small><?php echo  implode("<br>", $dokter_uro) ?> </small></span>
                </div>
                <!-- /.card-body -->
              </div>
              <!-- /.card -->
            </div>
          </div>

          <div class="col-md-2">
            <div class="card card-dark shadow-none">
              <div class="card-header">
                <h3 class="card-title">MCU</h3>
                <div class="card-tools">
                  <button type="button" class="btn btn-tool" data-card-widget="collapse">
                    <i class="fas fa-minus"></i>
                  </button>
                </div>
                <!-- /.card-tools -->
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <div class="row">
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND  reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='P20' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-plus-square" aria-hidden="true"></i>&nbsp;Terdaftar</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <!-- /.col -->
                  <div class="col-sm-6 border-right">
                    <div class="description-block">
                      <?php
                      $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                        FROM reg_periksa,dokter,poliklinik
                        WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                        AND reg_periksa.stts='Sudah' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='P20' AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                      ?>
                      <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                      <span class="description-text"><i class="fa fa-check-square" aria-hidden="true"></i>&nbsp;Terlayani</span>
                    </div>
                    <!-- /.description-block -->
                  </div>
                  <?php
                  $jumlah_penyakit_dalam = array();
                  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.kd_dokter,poliklinik.nm_poli
                  FROM reg_periksa,dokter,poliklinik
                  WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli AND reg_periksa.status_lanjut='Ralan'
                  AND reg_periksa.kd_poli='PS0' AND reg_periksa.tgl_registrasi=CURDATE()
                  GROUP BY reg_periksa.kd_dokter ");
                  //urutab tampilkan hasil query dari database
                  while ($row = mysqli_fetch_array($result)) {
                    $dokter_mcu[] = $row[1];
                  }
                  ?>
                  <span class="count_top "><i class="fa fa-stethoscope "></i> <small><?php echo  implode("<br>", $dokter_mcu) ?> </small></span>
                </div>
                <!-- /.card-body -->
              </div>
              <!-- /.card -->
            </div>
          </div>


        </div>

        <!-- penutup baris KELIMA-->
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


</body>

</html>