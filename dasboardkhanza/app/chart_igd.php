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
          <h5 class="mb-2">IGD</h5>
          <div class="alert alert-info alert-dismissible">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <h5><i class="icon fas fa-info"></i> Informasi!</h5>
            Maaf masih dalam Pengembangan
          </div>
          <div class="row">
            <div class="col-md-2 col-sm-2 col-12">
              <div class="info-box">
                <span class="info-box-icon bg-info"><i class="fa fa-desktop"></i></span>
                <div class="info-box-content">
                  <span class="info-box-text">Total Kunjungan</span>
                  <?php
                  $data_pasien = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar,reg_periksa.kd_poli
                                                          FROM pasien 
                                                          INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                                                          WHERE reg_periksa.kd_poli='IGDK'
                                                          AND reg_periksa.tgl_registrasi=CURDATE()");
                  $jumlah = mysqli_num_rows($data_pasien);
                  ?>
                  <span class="info-box-number"><?php echo "$jumlah"; ?></span>
                  <h5>
                    <small>
                      <?php
                      $data_pasien = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar,reg_periksa.kd_poli
                                                  FROM pasien 
                                                  INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                                                  WHERE pasien.jk='L'  AND reg_periksa.kd_poli='IGDK'
                                         AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($data_pasien);
                      ?>
                      <span class="count_top"><i class="fa fa-male"></i> <?php echo "$jumlah"; ?></span>
                      <?php
                      $data_pasien = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar,reg_periksa.kd_poli
                      FROM pasien 
                      INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                      WHERE pasien.jk='P' AND reg_periksa.kd_poli='IGDK'
                      AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($data_pasien);
                      ?>
                      <span class="count_top"><i class="fa fa-female"></i> <?php echo "$jumlah"; ?></span>
                    </small>
                  </h5>
                </div>
                <!-- /.info-box-content -->
              </div>
              <!-- /.info-box -->
            </div>
            <!-- /.col -->
            <div class="col-md-2 col-sm-2 col-12">
              <div class="info-box">
                <span class="info-box-icon bg-dark"><i class="fa fa-user-md"></i></span>
                <div class="info-box-content">
                  <span class="info-box-text">Rawat Jalan</span>
                  <?php
                  $data_pasien = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar,reg_periksa.kd_poli
                  FROM pasien 
                  INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                  WHERE  reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='IGDK'
                  AND reg_periksa.tgl_registrasi=CURDATE();");
                  $jumlah = mysqli_num_rows($data_pasien);
                  ?>
                  <span class="info-box-number"><?php echo "$jumlah"; ?></span>
                  <h5>
                    <small>
                      <?php
                      $data_pasien = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar,reg_periksa.kd_poli
                                                  FROM pasien 
                                                  INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                                                  WHERE pasien.jk='L' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='IGDK'
                                         AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($data_pasien);
                      ?>
                      <span class="count_top"><i class="fa fa-male"></i> <?php echo "$jumlah"; ?></span>
                      <?php
                      $data_pasien = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar,reg_periksa.kd_poli
                      FROM pasien 
                      INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                      WHERE pasien.jk='P' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='IGDK'
                      AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($data_pasien);
                      ?>
                      <span class="count_top"><i class="fa fa-female"></i> <?php echo "$jumlah"; ?></span>
                    </small>
                  </h5>

                </div>
                <!-- /.info-box-content -->
              </div>
              <!-- /.info-box -->
            </div>
            <!-- /.col -->
            <div class="col-md-2 col-sm-2 col-12">
              <div class="info-box">
                <span class="info-box-icon bg-dark"><i class="fa fa-bed"></i></span>
                <div class="info-box-content">
                  <span class="info-box-text">Rawat Inap</span>
                  <?php
                  $data_pasien = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar,reg_periksa.kd_poli
                  FROM pasien 
                  INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                  WHERE reg_periksa.status_lanjut='Ranap' AND reg_periksa.kd_poli='IGDK'
                  AND reg_periksa.tgl_registrasi=CURDATE()");
                  $jumlah = mysqli_num_rows($data_pasien);
                  ?>
                  <span class="info-box-number"><?php echo "$jumlah"; ?></span>
                  <!-- <?php echo var_dump($data_pasien) ?> -->
                  <h5>
                    <small>
                      <?php
                      $data_pasien = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar,reg_periksa.kd_poli
                      FROM pasien 
                      INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                      WHERE pasien.jk='L' and reg_periksa.status_lanjut='Ranap' AND reg_periksa.kd_poli='IGDK'
                      AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($data_pasien);
                      ?>
                      <span class="count_top"><i class="fa fa-male"></i> <?php echo "$jumlah"; ?></span>
                      <?php
                      $data_pasien = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar,reg_periksa.kd_poli
                      FROM pasien 
                      INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                      WHERE pasien.jk='P'  AND reg_periksa.status_lanjut='Ranap' AND reg_periksa.kd_poli='IGDK'
                     AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($data_pasien);
                      ?>
                      <span class="count_top"><i class="fa fa-female"></i> <?php echo "$jumlah"; ?></span>
                    </small>
                  </h5>
                </div>
                <!-- /.info-box-content -->
              </div>
              <!-- /.info-box -->
            </div>
            <!-- /.col -->
            <!-- /.col -->
            <div class="col-md-2 col-sm-2 col-12">
              <div class="info-box">
                <span class="info-box-icon bg-warning"><i class="fa fa-ambulance"></i></span>
                <div class="info-box-content">
                  <span class="info-box-text">Rujukan Keluar</span>
                  <?php
                  $data_rujukan = mysqli_query($koneksi, "SELECT * FROM rujuk,reg_periksa
                  WHERE rujuk.no_rawat=reg_periksa.no_rawat AND reg_periksa.kd_poli='IGDK' AND rujuk.tgl_rujuk=CURDATE()");
                  $jumlah_rujukan = mysqli_num_rows($data_rujukan);
                  ?>
                  <span class="info-box-number"><?php echo "$jumlah_rujukan"; ?></span>
                  <!-- <?php echo var_dump($data_rujukan) ?> -->
                  <h5>
                    <small>
                      <?php
                      $data_pasien = mysqli_query($koneksi, "SELECT * FROM rujuk,reg_periksa,pasien
                      WHERE rujuk.no_rawat=reg_periksa.no_rawat AND reg_periksa.no_rkm_medis=pasien.no_rkm_medis
                      AND reg_periksa.kd_poli='IGDK' 
                      AND rujuk.tgl_rujuk=CURDATE() 
                      AND pasien.jk='L'");
                      $jumlah = mysqli_num_rows($data_pasien);
                      ?>
                      <span class="count_top"><i class="fa fa-male" data-toggle="tooltip" data-placement="top" title="Laki-Laki"></i> <?php echo "$jumlah"; ?></span>
                      <?php
                      $data_pasien = mysqli_query($koneksi, "SELECT * FROM rujuk,reg_periksa,pasien
                      WHERE rujuk.no_rawat=reg_periksa.no_rawat AND reg_periksa.no_rkm_medis=pasien.no_rkm_medis
                      AND reg_periksa.kd_poli='IGDK' 
                      AND rujuk.tgl_rujuk=CURDATE() 
                      AND pasien.jk='P'");
                      $jumlah = mysqli_num_rows($data_pasien);
                      ?>
                      <span class="count_top"><i class="fa fa-female" data-toggle="tooltip" data-placement="top" title="Perempuan"></i> <?php echo "$jumlah"; ?></span>
                    </small>
                  </h5>
                </div>
                <!-- /.info-box-content -->
              </div>
              <!-- /.info-box -->
            </div>
            <!-- /.col -->
            <div class="col-md-2 col-sm-2 col-12">
              <div class="info-box">
                <span class="info-box-icon bg-success"><i class="fa fa-desktop"></i></span>

                <div class="info-box-content">
                  <span class="info-box-text">Rujukan Masuk</span>
                  <?php
                  $kamar = mysqli_query($koneksi, "SELECT * FROM rujuk_masuk,reg_periksa,pasien
                  WHERE rujuk_masuk.no_rawat=reg_periksa.no_rawat AND reg_periksa.no_rkm_medis=pasien.no_rkm_medis
                  AND reg_periksa.kd_poli='IGDK'
                  AND reg_periksa.tgl_registrasi=CURDATE() ");
                  $jumlah = mysqli_num_rows($kamar);
                  ?>
                  <span class="info-box-number"> <?php echo "$jumlah"; ?></span>
                  <h5>
                    <small>
                      <?php
                      $kamar = mysqli_query($koneksi, "SELECT * FROM rujuk_masuk,reg_periksa,pasien
                      WHERE rujuk_masuk.no_rawat=reg_periksa.no_rawat AND reg_periksa.no_rkm_medis=pasien.no_rkm_medis
                      AND reg_periksa.kd_poli='IGDK'
                      AND reg_periksa.tgl_registrasi=CURDATE() 
                      AND pasien.jk='P'");
                      $jumlah = mysqli_num_rows($kamar);
                      ?>
                      <span class="count_top"><i class="fa fa-male" data-toggle="tooltip" data-placement="top" title="Laki-Laki!"></i><?php echo "$jumlah"; ?></span>
                      <?php
                      $kamar = mysqli_query($koneksi, "SELECT * FROM rujuk_masuk,reg_periksa,pasien
                      WHERE rujuk_masuk.no_rawat=reg_periksa.no_rawat AND reg_periksa.no_rkm_medis=pasien.no_rkm_medis
                      AND reg_periksa.kd_poli='IGDK'
                      AND reg_periksa.tgl_registrasi=CURDATE() 
                      AND pasien.jk='L'");
                      $jumlah = mysqli_num_rows($kamar);
                      ?>
                      <span class="count_top"><i class="fa fa-female" data-toggle="tooltip" data-placement="top" title="Perempuan!"></i><?php echo "$jumlah"; ?></span>
                    </small>
                  </h5>
                </div>
                <!-- /.info-box-content -->
              </div>
              <!-- /.info-box -->
            </div>
            <div class="col-md-2 col-sm-2 col-12">
              <div class="info-box">
                <span class="info-box-icon bg-info"><i class="fa fa-desktop"></i></span>

                <div class="info-box-content">
                  <span class="info-box-text">SISTRUE</span>
                  <?php
                  $kamar = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar,reg_periksa.kd_poli
                  FROM pasien 
                  INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                  WHERE pasien.jk='L'  AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='RND'
                  AND reg_periksa.tgl_registrasi=CURDATE()");
                  $jumlah = mysqli_num_rows($kamar);
                  ?>
                  <span class="info-box-number"> <?php echo "$jumlah"; ?></span>
                  <h5>
                    <small>
                      <?php
                      $kamar = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar,reg_periksa.kd_poli
                      FROM pasien 
                      INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                      WHERE  pasien.jk='p' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='RND'
                      AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($kamar);
                      ?>
                      <span class="count_top"><i class="fa fa-male" data-toggle="tooltip" data-placement="top" title="Laki-Laki!"></i><?php echo "$jumlah"; ?></span>
                      <?php
                      $kamar = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar,reg_periksa.kd_poli
                      FROM pasien 
                      INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                      WHERE pasien.jk='L' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='RND'
                      AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($kamar);
                      ?>
                      <span class="count_top"><i class="fa fa-female" data-toggle="tooltip" data-placement="top" title="Perempuan!"></i><?php echo "$jumlah"; ?></span>
                    </small>
                  </h5>
                </div>
                <!-- /.info-box-content -->
              </div>
              <!-- /.info-box -->
            </div>
            <!-- /.col -->
          </div>

          <!-- // baris keduas -->
          <div class="row">
            <div class="col-md-2 col-sm-2 col-12">
              <div class="info-box">
                <span class="info-box-icon bg-info"><i class="fa fa-desktop"></i></span>
                <div class="info-box-content">
                  <span class="info-box-text">Pasien D.O.A</span>
                  <?php
                  $data_pasien = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar,reg_periksa.kd_poli
                                                          FROM pasien 
                                                          INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                                                          WHERE reg_periksa.kd_poli='IGDK'
                                                          AND reg_periksa.tgl_registrasi=CURDATE()");
                  $jumlah = mysqli_num_rows($data_pasien);
                  ?>
                  <span class="info-box-number">0</span>
                  <h5>
                    <small>
                      <?php
                      $data_pasien = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar,reg_periksa.kd_poli
                                                  FROM pasien 
                                                  INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                                                  WHERE pasien.jk='L'  AND reg_periksa.kd_poli='IGDK'
                                         AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($data_pasien);
                      ?>
                      <span class="count_top"><i class="fa fa-male"></i> 0</span>
                      <?php
                      $data_pasien = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar,reg_periksa.kd_poli
                      FROM pasien 
                      INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                      WHERE pasien.jk='P' AND reg_periksa.kd_poli='IGDK'
                      AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($data_pasien);
                      ?>
                      <span class="count_top"><i class="fa fa-female"></i> 0</span>
                    </small>
                  </h5>
                </div>
                <!-- /.info-box-content -->
              </div>
              <!-- /.info-box -->
            </div>
            <!-- /.col -->
            <div class="col-md-2 col-sm-2 col-12">
              <div class="info-box">
                <span class="info-box-icon bg-info"><i class="fa fa-desktop"></i></span>
                <div class="info-box-content">
                  <span class="info-box-text">Pasien Meninggal</span>
                  <?php
                  $data_pasien = mysqli_query($koneksi, "SELECT * FROM reg_periksa,pasien_mati
                  WHERE reg_periksa.no_rkm_medis=pasien_mati.no_rkm_medis
                  AND reg_periksa.kd_poli='IGDK'
                  AND pasien_mati.temp_meninggal='Rumah Sakit'
                  AND reg_periksa.tgl_registrasi=CURDATE()");
                  $jumlah = mysqli_num_rows($data_pasien);
                  ?>
                  <span class="info-box-number"><?php echo "$jumlah"; ?></span>
                  <h5>
                    <small>
                      <?php
                      $data_pasien = mysqli_query($koneksi, "SELECT * FROM reg_periksa,pasien_mati,pasien
                      WHERE reg_periksa.no_rkm_medis=pasien_mati.no_rkm_medis AND pasien.no_rkm_medis=pasien_mati.no_rkm_medis
                      AND reg_periksa.kd_poli='IGDK'
                      AND pasien_mati.temp_meninggal='Rumah Sakit'
                      AND pasien.jk='L'
                      AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($data_pasien);
                      ?>
                      <span class="count_top"><i class="fa fa-male"></i> <?php echo "$jumlah"; ?></span>
                      <?php
                      $data_pasien = mysqli_query($koneksi, "SELECT * FROM reg_periksa,pasien_mati,pasien
                      WHERE reg_periksa.no_rkm_medis=pasien_mati.no_rkm_medis AND pasien.no_rkm_medis=pasien_mati.no_rkm_medis
                      AND reg_periksa.kd_poli='IGDK'
                      AND pasien_mati.temp_meninggal='Rumah Sakit'
                      AND pasien.jk='P'
                      AND reg_periksa.tgl_registrasi=CURDATE();");
                      $jumlah = mysqli_num_rows($data_pasien);
                      ?>
                      <span class="count_top"><i class="fa fa-female"></i> <?php echo "$jumlah"; ?></span>
                    </small>
                  </h5>

                </div>
                <!-- /.info-box-content -->
              </div>
              <!-- /.info-box -->
            </div>
            <!-- /.col -->
            <div class="col-md-2 col-sm-2 col-12">
              <div class="info-box">
                <span class="info-box-icon bg-danger"><i class="fa fa-desktop"></i></span>
                <div class="info-box-content">
                  <span class="info-box-text">Pasien ICU</span>
                  <?php
                  $data_pasien = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar,reg_periksa.kd_poli
                  FROM pasien 
                  INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                  WHERE reg_periksa.status_lanjut='Ranap' AND reg_periksa.kd_poli='IGDK'
                  AND reg_periksa.tgl_registrasi=CURDATE()");
                  $jumlah = mysqli_num_rows($data_pasien);
                  ?>
                  <span class="info-box-number">0</span>
                  <!-- <?php echo var_dump($data_pasien) ?> -->
                  <h5>
                    <small>
                      <?php
                      $data_pasien = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar,reg_periksa.kd_poli
                      FROM pasien 
                      INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                      WHERE pasien.jk='L' and reg_periksa.status_lanjut='Ranap' AND reg_periksa.kd_poli='IGDK'
                      AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($data_pasien);
                      ?>
                      <span class="count_top"><i class="fa fa-male"></i> 0</span>
                      <?php
                      $data_pasien = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar,reg_periksa.kd_poli
                      FROM pasien 
                      INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                      WHERE pasien.jk='P'  AND reg_periksa.status_lanjut='Ranap' AND reg_periksa.kd_poli='IGDK'
                     AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($data_pasien);
                      ?>
                      <span class="count_top"><i class="fa fa-female"></i> 0</span>
                    </small>
                  </h5>
                </div>
                <!-- /.info-box-content -->
              </div>
              <!-- /.info-box -->
            </div>
            <!-- /.col -->
            <!-- /.col -->
            <div class="col-md-2 col-sm-2 col-12">
              <div class="info-box">
                <span class="info-box-icon bg-info"><i class="fa fa-ambulance"></i></span>
                <div class="info-box-content">
                  <span class="info-box-text">Pasien PICU</span>
                  <?php
                  $data_rujukan = mysqli_query($koneksi, "SELECT *
                  FROM bridging_rujukan_bpjs,bridging_sep,reg_periksa
                  WHERE bridging_rujukan_bpjs.poliRujukan='THT' AND reg_periksa.kd_poli='THT'
                  AND bridging_rujukan_bpjs.no_sep=bridging_sep.no_sep AND reg_periksa.no_rawat=bridging_sep.no_rawat
                  
                  ");
                  $jumlah_rujukan = mysqli_num_rows($data_rujukan);
                  ?>
                  <span class="info-box-number">0</span>
                  <!-- <?php echo var_dump($data_rujukan) ?> -->
                  <h5>
                    <small>
                      <?php
                      $data_pasien = mysqli_query($koneksi, "SELECT bridging_sep.no_sep,bridging_sep.nomr,bridging_sep.nama_pasien,bridging_sep.jkel,bridging_rujukan_bpjs.tglRujukan,bridging_rujukan_bpjs.nm_ppkDirujuk,bridging_rujukan_bpjs.nama_poliRujukan,
                      bridging_rujukan_bpjs.poliRujukan,bridging_rujukan_bpjs.diagRujukan,bridging_rujukan_bpjs.nama_diagRujukan
                      FROM bridging_rujukan_bpjs,bridging_sep
                      WHERE bridging_rujukan_bpjs.poliRujukan='IGD' AND bridging_rujukan_bpjs.tglRujukan=CURDATE() AND bridging_sep.jkel='L'
                      AND bridging_rujukan_bpjs.no_sep=bridging_sep.no_sep;");
                      $jumlah = mysqli_num_rows($data_pasien);
                      ?>
                      <span class="count_top"><i class="fa fa-male" data-toggle="tooltip" data-placement="top" title="Laki-Laki"></i>0</span>
                      <?php
                      $data_pasien = mysqli_query($koneksi, "SELECT bridging_sep.no_sep,bridging_sep.nomr,bridging_sep.nama_pasien,bridging_sep.jkel,bridging_rujukan_bpjs.tglRujukan,bridging_rujukan_bpjs.nm_ppkDirujuk,bridging_rujukan_bpjs.nama_poliRujukan,
                      bridging_rujukan_bpjs.poliRujukan,bridging_rujukan_bpjs.diagRujukan,bridging_rujukan_bpjs.nama_diagRujukan
                      FROM bridging_rujukan_bpjs,bridging_sep
                      WHERE bridging_rujukan_bpjs.poliRujukan='IGD' AND bridging_rujukan_bpjs.tglRujukan=CURDATE() AND bridging_sep.jkel='P'
                      AND bridging_rujukan_bpjs.no_sep=bridging_sep.no_sep;");
                      $jumlah = mysqli_num_rows($data_pasien);
                      ?>
                      <span class="count_top"><i class="fa fa-female" data-toggle="tooltip" data-placement="top" title="Perempuan"></i> 0</span>
                    </small>
                  </h5>
                </div>
                <!-- /.info-box-content -->
              </div>
              <!-- /.info-box -->
            </div>
            <!-- /.col -->
            <div class="col-md-2 col-sm-2 col-12">
              <div class="info-box">
                <span class="info-box-icon bg-info"><i class="fa fa-desktop"></i></span>

                <div class="info-box-content">
                  <span class="info-box-text">Pasien NICU</span>
                  <?php
                  $kamar = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar,reg_periksa.kd_poli
                  FROM pasien 
                  INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                  WHERE pasien.jk='L'  AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='RND'
                  AND reg_periksa.tgl_registrasi=CURDATE()");
                  $jumlah = mysqli_num_rows($kamar);
                  ?>
                  <span class="info-box-number"> <?php echo "$jumlah"; ?></span>
                  <h5>
                    <small>
                      <?php
                      $kamar = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar,reg_periksa.kd_poli
                      FROM pasien 
                      INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                      WHERE  pasien.jk='p' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='RND'
                      AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($kamar);
                      ?>
                      <span class="count_top"><i class="fa fa-male" data-toggle="tooltip" data-placement="top" title="Laki-Laki!"></i><?php echo "$jumlah"; ?></span>
                      <?php
                      $kamar = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar,reg_periksa.kd_poli
                      FROM pasien 
                      INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                      WHERE pasien.jk='L' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='RND'
                      AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($kamar);
                      ?>
                      <span class="count_top"><i class="fa fa-female" data-toggle="tooltip" data-placement="top" title="Perempuan!"></i><?php echo "$jumlah"; ?></span>
                    </small>
                  </h5>
                </div>
                <!-- /.info-box-content -->
              </div>
              <!-- /.info-box -->
            </div>
            <div class="col-md-2 col-sm-2 col-12">
              <div class="info-box">
                <span class="info-box-icon bg-info"><i class="fa fa-desktop"></i></span>

                <div class="info-box-content">
                  <span class="info-box-text">Bedah Cyto</span>
                  <?php
                  $kamar = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar,reg_periksa.kd_poli
                  FROM pasien 
                  INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                  WHERE pasien.jk='L'  AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='RND'
                  AND reg_periksa.tgl_registrasi=CURDATE()");
                  $jumlah = mysqli_num_rows($kamar);
                  ?>
                  <span class="info-box-number"> <?php echo "$jumlah"; ?></span>
                  <h5>
                    <small>
                      <?php
                      $kamar = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar,reg_periksa.kd_poli
                      FROM pasien 
                      INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                      WHERE  pasien.jk='p' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='RND'
                      AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($kamar);
                      ?>
                      <span class="count_top"><i class="fa fa-male" data-toggle="tooltip" data-placement="top" title="Laki-Laki!"></i><?php echo "$jumlah"; ?></span>
                      <?php
                      $kamar = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar,reg_periksa.kd_poli
                      FROM pasien 
                      INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                      WHERE pasien.jk='L' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='RND'
                      AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($kamar);
                      ?>
                      <span class="count_top"><i class="fa fa-female" data-toggle="tooltip" data-placement="top" title="Perempuan!"></i><?php echo "$jumlah"; ?></span>
                    </small>
                  </h5>
                </div>
                <!-- /.info-box-content -->
              </div>
              <!-- /.info-box -->
            </div>
            <!-- /.col -->
          </div>
          <!-- // kolom baris ketiga :) -->

          <div class="col-md-12 ">
            <figure class="highcharts-figure">
              <div id="umur"></div>
            </figure>
          </div>
          <div class="col-md-12">
            <div class="card">
              <div class="card-header">
                <h3 class="card-title">Jenis Penyakit Pasien IGD</h3>
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <table id="example1" class="table table-bordered table-striped">
                  <thead>
                    <tr>
                      <th>NO</th>
                      <th>ICD</th>
                      <th>Diagnosa</th>
                      <th>Status</th>
                      <th>Jumlah</th>

                    </tr>
                  </thead>
                  <tbody>
                    <?php
                    $no = 0;
                    $result = mysqli_query($koneksi, "SELECT diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit,diagnosa_pasien.`status`, COUNT(diagnosa_pasien.no_rawat) AS Jumlah 
                    FROM diagnosa_pasien,penyakit,reg_periksa 
                    WHERE diagnosa_pasien.no_rawat=reg_periksa.no_rawat 
                    AND diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit
                    AND diagnosa_pasien.`status`='Ralan'
                    AND reg_periksa.kd_poli='IGDK'
                    GROUP BY diagnosa_pasien.kd_penyakit
                    ");
                    //urutab tampilkan hasil query dari database
                    while ($row = mysqli_fetch_array($result)) {
                      $no++;
                    ?>
                      <tr>
                        <td><?php echo $no; ?></td>
                        <td><?php echo $row['kd_penyakit']; ?></td>
                        <td><?php echo $row['nm_penyakit']; ?></td>
                        <td> <?php echo $row['status']; ?></td>
                        <td> <?php echo $row['Jumlah']; ?></td>

                      </tr>
                    <?php } ?>
                  </tbody>
                  <tfoot>
                    <tr>
                      <th>NO</th>
                      <th>ICD</th>
                      <th>Diagnosa</th>
                      <th>Status</th>
                      <th>Jumlah</th>

                    </tr>
                  </tfoot>
                </table>
              </div>
              <!-- /.card-body -->
            </div>
          </div>
          <div class="row">
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
            <div class="col-md-12 ">
              <figure class="highcharts-figure">
                <div id="chart_pertanggal"></div>
              </figure>
            </div>
            <div class="col-md-12 ">
              <figure class="highcharts-figure">
                <div id="chart_mingguan"></div>
              </figure>
            </div>
            <div class="col-md-12 ">
              <figure class="highcharts-figure">
                <div id="chart_bulanan"></div>
              </figure>
            </div>
            <div class="col-md-12 ">
              <figure class="highcharts-figure">
                <div id="chart_tahunan"></div>
              </figure>
            </div>


            <!-- // kolom baris kedua :) -->
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
  if (isset($_POST['tgl_awal']) && isset($_POST['tgl_akhir'])) {
    $tgl_awal = date('Y-m-d', strtotime($_POST["tgl_awal"]));
    $tgl_akhir = date('Y-m-d', strtotime($_POST["tgl_akhir"]));
  }
  ?>

  <?php
  $jumlah_pasein_igd_pertanggal = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_poli,reg_periksa.status_lanjut,reg_periksa.stts,reg_periksa.tgl_registrasi, 
  COUNT(DISTINCT reg_periksa.no_rkm_medis) AS Jumlah
  FROM reg_periksa
  WHERE reg_periksa.tgl_registrasi BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'  AND reg_periksa.kd_poli='IGDK' 
  GROUP BY (reg_periksa.tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tgl_periksa_igd[] = $row[3];
    $jumlah_pasein_igd_pertanggal[] = $row[4];
  }
  ?>



  <?php
  $jumlah_pasein_igd_mingguan = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_poli,reg_periksa.status_lanjut,reg_periksa.stts,reg_periksa.tgl_registrasi, 
  COUNT(DISTINCT reg_periksa.no_rkm_medis) AS Jumlah
  FROM reg_periksa
  WHERE reg_periksa.tgl_registrasi BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'  AND reg_periksa.kd_poli='IGDK' 
  GROUP BY YEARWEEK(reg_periksa.tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tgl_periksa_igd_mingguan[] = $row[3];
    $jumlah_pasein_igd_mingguan[] = $row[4];
  }
  ?>


  <?php
  $jumlah_pasein_igd_bulanan = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_poli,reg_periksa.status_lanjut,reg_periksa.stts,DATE_FORMAT(reg_periksa.tgl_registrasi,'%M %Y'), 
  COUNT(DISTINCT reg_periksa.no_rkm_medis) AS Jumlah
  FROM reg_periksa
  WHERE reg_periksa.tgl_registrasi BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND reg_periksa.kd_poli='IGDK' 
  GROUP BY DATE_FORMAT(tgl_registrasi,'%M %Y') order BY tgl_registrasi ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tgl_periksa_igd_bulanan[] = $row[3];
    $jumlah_pasein_igd_bulanan[] = $row[4];
  }
  ?>

  <?php
  $jumlah_pasein_igd_tahunan = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_poli,reg_periksa.status_lanjut,reg_periksa.stts,DATE_FORMAT(reg_periksa.tgl_registrasi,'%Y'), 
  COUNT(DISTINCT reg_periksa.no_rkm_medis) AS Jumlah
  FROM reg_periksa
  WHERE reg_periksa.tgl_registrasi BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND reg_periksa.kd_poli='IGDK' 
  GROUP BY YEAR(reg_periksa.tgl_registrasi)ASC;");
  while ($row = mysqli_fetch_array($result)) {
    $tgl_periksa_igd_tahunan[] = $row[3];
    $jumlah_pasein_igd_tahunan[] = $row[4];
  }
  ?>
  <!-- umur-->
  <?php
  $Jumlah_umur_dibawah_25th_pria = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_poli,pasien.jk,COUNT(reg_periksa.no_rkm_medis) AS jumlah_Pendaftar_Umur_0_sampai_25
  FROM reg_periksa,pasien
  WHERE  reg_periksa.no_rkm_medis=pasien.no_rkm_medis
  AND reg_periksa.umurdaftar <=25 
  AND reg_periksa.kd_poli='IGDK'
  AND pasien.jk='l'
  AND reg_periksa.tgl_registrasi=CURDATE();");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_umur_dibawah_25th_pria[] = $row[2];
  }
  ?>
  <?php
  $Jumlah_umur_dibawah_25th_wanita = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_poli,pasien.jk,COUNT(reg_periksa.no_rkm_medis) AS jumlah_Pendaftar_Umur_0_sampai_25
  FROM reg_periksa,pasien
  WHERE  reg_periksa.no_rkm_medis=pasien.no_rkm_medis
  AND reg_periksa.umurdaftar <=25 
  AND reg_periksa.kd_poli='IGDK'
  AND pasien.jk='P'
  AND reg_periksa.tgl_registrasi=CURDATE();");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_umur_dibawah_25th_wanita[] = $row[2];
  }
  ?>

  <?php
  $Jumlah_umur_26_30_pria = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_poli,pasien.jk,COUNT(reg_periksa.no_rkm_medis) AS jumlah_Pendaftar_Umur_26_sampai_30
  FROM reg_periksa,pasien
  WHERE  reg_periksa.no_rkm_medis=pasien.no_rkm_medis
  AND reg_periksa.umurdaftar BETWEEN  26 AND 30 
  AND reg_periksa.kd_poli='IGDK'
  AND pasien.jk='l'
  AND reg_periksa.tgl_registrasi=CURDATE()");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_umur_26_30_pria[] = $row[2];
  }
  ?>
  <?php
  $Jumlah_umur_26_30_wanita = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_poli,pasien.jk,COUNT(reg_periksa.no_rkm_medis) AS jumlah_Pendaftar_Umur_26_sampai_30
  FROM reg_periksa,pasien
  WHERE  reg_periksa.no_rkm_medis=pasien.no_rkm_medis
  AND reg_periksa.umurdaftar BETWEEN  26 AND 30 
  AND reg_periksa.kd_poli='IGDK'
  AND pasien.jk='P'
  AND reg_periksa.tgl_registrasi=CURDATE()");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_umur_26_30_wanita[] = $row[2];
  }
  ?>

  <?php
  $Jumlah_umur_31_35_pria = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_poli,pasien.jk,COUNT(reg_periksa.no_rkm_medis) AS jumlah_Pendaftar_Umur_31_sampai_35
  FROM reg_periksa,pasien
  WHERE  reg_periksa.no_rkm_medis=pasien.no_rkm_medis
  AND reg_periksa.umurdaftar BETWEEN  31 AND 35 
  AND reg_periksa.kd_poli='IGDK'
  AND pasien.jk='L'
  AND reg_periksa.tgl_registrasi=CURDATE()");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_umur_31_35_pria[] = $row[2];
  }
  ?>
  <?php
  $Jumlah_umur_31_35_wanita = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_poli,pasien.jk,COUNT(reg_periksa.no_rkm_medis) AS jumlah_Pendaftar_Umur_31_sampai_35
  FROM reg_periksa,pasien
  WHERE  reg_periksa.no_rkm_medis=pasien.no_rkm_medis
  AND reg_periksa.umurdaftar BETWEEN  31 AND 35 
  AND reg_periksa.kd_poli='IGDK'
  AND pasien.jk='P'
  AND reg_periksa.tgl_registrasi=CURDATE()");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_umur_31_35_wanita[] = $row[2];
  }
  ?>

  <?php
  $Jumlah_umur_36_40_pria = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_poli,pasien.jk,COUNT(reg_periksa.no_rkm_medis) AS jumlah_Pendaftar_Umur_36_sampai_40
  FROM reg_periksa,pasien
  WHERE  reg_periksa.no_rkm_medis=pasien.no_rkm_medis
  AND reg_periksa.umurdaftar BETWEEN  36 AND 40 
  AND reg_periksa.kd_poli='IGDK'
  AND pasien.jk='L'
  AND reg_periksa.tgl_registrasi=CURDATE()");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_umur_36_40_pria[] = $row[2];
  }
  ?>
  <?php
  $Jumlah_umur_36_40_wanita = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_poli,pasien.jk,COUNT(reg_periksa.no_rkm_medis) AS jumlah_Pendaftar_Umur_36_sampai_40
  FROM reg_periksa,pasien
  WHERE  reg_periksa.no_rkm_medis=pasien.no_rkm_medis
  AND reg_periksa.umurdaftar BETWEEN  36 AND 40 
  AND reg_periksa.kd_poli='IGDK'
  AND pasien.jk='P'
  AND reg_periksa.tgl_registrasi=CURDATE()");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_umur_36_40_wanita[] = $row[2];
  }
  ?>

  <?php
  $Jumlah_umur_41_45_pria = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_poli,pasien.jk,COUNT(reg_periksa.no_rkm_medis) AS jumlah_Pendaftar_Umur_41_sampai_45
  FROM reg_periksa,pasien
  WHERE  reg_periksa.no_rkm_medis=pasien.no_rkm_medis
  AND reg_periksa.umurdaftar BETWEEN  41 AND 45 
  AND reg_periksa.kd_poli='IGDK'
  AND pasien.jk='L'
  AND reg_periksa.tgl_registrasi=CURDATE()");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_umur_41_45_pria[] = $row[2];
  }
  ?>
  <?php
  $Jumlah_umur_41_45_wanita = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_poli,pasien.jk,COUNT(reg_periksa.no_rkm_medis) AS jumlah_Pendaftar_Umur_41_sampai_45
  FROM reg_periksa,pasien
  WHERE  reg_periksa.no_rkm_medis=pasien.no_rkm_medis
  AND reg_periksa.umurdaftar BETWEEN  41 AND 45 
  AND reg_periksa.kd_poli='IGDK'
  AND pasien.jk='P'
  AND reg_periksa.tgl_registrasi=CURDATE()");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_umur_41_45_wanita[] = $row[2];
  }
  ?>

  <?php
  $Jumlah_umur_46_50_pria = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_poli,pasien.jk,COUNT(reg_periksa.no_rkm_medis) AS jumlah_Pendaftar_Umur_46_sampai_50
  FROM reg_periksa,pasien
  WHERE  reg_periksa.no_rkm_medis=pasien.no_rkm_medis
  AND reg_periksa.umurdaftar BETWEEN  45 AND 50 
  AND reg_periksa.kd_poli='IGDK'
  AND pasien.jk='L'
  AND reg_periksa.tgl_registrasi=CURDATE()");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_umur_46_50_pria[] = $row[2];
  }
  ?>
  <?php
  $Jumlah_umur_46_50_wanita = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_poli,pasien.jk,COUNT(reg_periksa.no_rkm_medis) AS jumlah_Pendaftar_Umur_46_sampai_50
  FROM reg_periksa,pasien
  WHERE  reg_periksa.no_rkm_medis=pasien.no_rkm_medis
  AND reg_periksa.umurdaftar BETWEEN  46 AND 50 
  AND reg_periksa.kd_poli='IGDK'
  AND pasien.jk='P'
  AND reg_periksa.tgl_registrasi=CURDATE()");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_umur_46_50_wanita[] = $row[2];
  }
  ?>

  <?php
  $Jumlah_umur_51_55_pria = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_poli,pasien.jk,COUNT(reg_periksa.no_rkm_medis) AS jumlah_Pendaftar_Umur_51_sampai_55
  FROM reg_periksa,pasien
  WHERE  reg_periksa.no_rkm_medis=pasien.no_rkm_medis
  AND reg_periksa.umurdaftar BETWEEN  51 AND 55 
  AND reg_periksa.kd_poli='IGDK'
  AND pasien.jk='L'
  AND reg_periksa.tgl_registrasi=CURDATE()");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_umur_51_55_pria[] = $row[2];
  }
  ?>
  <?php
  $Jumlah_umur_51_55_wanita = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_poli,pasien.jk,COUNT(reg_periksa.no_rkm_medis) AS jumlah_Pendaftar_Umur_51_sampai_55
  FROM reg_periksa,pasien
  WHERE  reg_periksa.no_rkm_medis=pasien.no_rkm_medis
  AND reg_periksa.umurdaftar BETWEEN  51 AND 55 
  AND reg_periksa.kd_poli='IGDK'
  AND pasien.jk='P'
  AND reg_periksa.tgl_registrasi=CURDATE()");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_umur_51_55_wanita[] = $row[2];
  }
  ?>

  <?php
  $Jumlah_umur_56_pria = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_poli,pasien.jk,COUNT(reg_periksa.no_rkm_medis) AS jumlah_Pendaftar_Umur_56
  FROM reg_periksa,pasien
  WHERE  reg_periksa.no_rkm_medis=pasien.no_rkm_medis
  AND reg_periksa.umurdaftar = 56 
  AND reg_periksa.kd_poli='IGDK'
  AND pasien.jk='L'
  AND reg_periksa.tgl_registrasi=CURDATE()");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $Jumlah_umur_56_pria[] = $row[2];
  }
  ?>
  <?php
  $Jumlah_umur_56_wanita = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_poli,pasien.jk,COUNT(reg_periksa.no_rkm_medis) AS jumlah_Pendaftar_Umur_56
  FROM reg_periksa,pasien
  WHERE  reg_periksa.no_rkm_medis=pasien.no_rkm_medis
  AND reg_periksa.umurdaftar = 56 
  AND reg_periksa.kd_poli='IGDK'
  AND pasien.jk='P'
  AND reg_periksa.tgl_registrasi=CURDATE()");
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

</body>

</html>



<script>
  Highcharts.chart("chart_pertanggal", {

    chart: {
      type: 'areaspline'
    },
    title: {
      text: 'Grafik IGD Pertanggal'
    },
    subtitle: {
      text: "Status sudah terdaftar",
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
        '<?php echo  implode("','", $tgl_periksa_igd) ?> '
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
          name: 'IGD',
          data: [
            <?php foreach ($jumlah_pasein_igd_pertanggal as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>
    ]
  });
</script>

<script>
  Highcharts.chart("chart_mingguan", {

    chart: {
      type: 'areaspline'
    },
    title: {
      text: 'Grafik Mingguan'
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
      categories: [
        '<?php echo  implode("','", $tgl_periksa_igd_mingguan) ?> '
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
          name: 'IGD',
          data: [
            <?php foreach ($jumlah_pasein_igd_mingguan as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>
    ]
  });
</script>

<script>
  Highcharts.chart("chart_bulanan", {

    chart: {
      type: 'areaspline'
    },
    title: {
      text: 'Grafik Bulanan'
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
      categories: [
        '<?php echo  implode("','", $tgl_periksa_igd_bulanan) ?> '
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
          name: 'IGD',
          data: [
            <?php foreach ($jumlah_pasein_igd_bulanan as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>
    ]
  });
</script>

<script>
  Highcharts.chart("chart_tahunan", {

    chart: {
      type: 'areaspline'
    },
    title: {
      text: 'Grafik Tahunan'
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
      categories: [
        '<?php echo  implode("','", $tgl_periksa_igd_tahunan) ?> '
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
          name: 'IGD',
          data: [
            <?php foreach ($jumlah_pasein_igd_tahunan as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>
    ]
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
      text: 'Kunjungan berdasarkan Umur Hari ini',
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