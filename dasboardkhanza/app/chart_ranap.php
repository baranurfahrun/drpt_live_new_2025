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

      <!-- Main content -->
      <section class="content">
        <div class="container-fluid">
          <h5 class="mb-2">Rawat Inap</h5>
          <div class="row">
            <div class="col-md-3 col-sm-6 col-12">
              <div class="info-box">
                <span class="info-box-icon bg-info"><i class="fa fa-bed"></i></span>
                <div class="info-box-content">
                  <span class="info-box-text">Pasien Rawat Inap</span>
                  <?php
                  $data_pasien = mysqli_query($koneksi, "SELECT * FROM reg_periksa WHERE  reg_periksa.status_lanjut='Ranap' AND  reg_periksa.tgl_registrasi=CURDATE()");
                  $jumlah = mysqli_num_rows($data_pasien);
                  ?>
                  <span class="info-box-number"><?php echo "$jumlah"; ?></span>
                  <h5>
                    <small>
                      <?php
                      $data_pasien = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut 
                            FROM pasien 
                            INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                            WHERE pasien.jk='L' AND  reg_periksa.status_lanjut='Ranap'and reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($data_pasien);
                      ?>
                      <span class="count_top"><i class="fa fa-male"></i> <?php echo "$jumlah"; ?></span>
                      <?php
                      $data_pasien = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut 
                            FROM pasien 
                            INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                            WHERE pasien.jk='P' AND  reg_periksa.status_lanjut='Ranap'and reg_periksa.tgl_registrasi=CURDATE()");
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
            <div class="col-md-3 col-sm-6 col-12">
              <div class="info-box">
                <span class="info-box-icon bg-success"><i class="fa fa-bed"></i></span>
                <div class="info-box-content">
                  <span class="info-box-text">Pasein Baru</span>
                  <?php
                  $data_pasien = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar
                      FROM pasien 
                      INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                      WHERE  reg_periksa.status_lanjut='Ranap' AND reg_periksa.stts_daftar='Baru' and reg_periksa.tgl_registrasi=CURDATE()");
                  $jumlah = mysqli_num_rows($data_pasien);
                  ?>
                  <span class="info-box-number"><?php echo "$jumlah"; ?></span>
                  <h5>
                    <small>
                      <?php
                      $data_pasien = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar
                            FROM pasien 
                            INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                            WHERE pasien.jk='L'  AND reg_periksa.status_lanjut='Ranap' AND reg_periksa.stts_daftar='Baru' and reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($data_pasien);
                      ?>
                      <span class="count_top"><i class="fa fa-male"></i> <?php echo "$jumlah"; ?></span>
                      <?php
                      $data_pasien = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar
                            FROM pasien 
                            INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                            WHERE pasien.jk='P'AND reg_periksa.status_lanjut='Ranap' AND reg_periksa.stts_daftar='Baru'and reg_periksa.tgl_registrasi=CURDATE()");
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
            <div class="col-md-3 col-sm-6 col-12">
              <div class="info-box">
                <span class="info-box-icon bg-warning"><i class="fa fa-bed"></i></span>
                <div class="info-box-content">
                  <span class="info-box-text">Pasien Lama</span>
                  <?php
                  $data_pasien = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar
                      FROM pasien 
                      INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                      WHERE reg_periksa.status_lanjut='Ranap' AND reg_periksa.stts_daftar='Lama' and reg_periksa.tgl_registrasi=CURDATE()");
                  $jumlah = mysqli_num_rows($data_pasien);
                  ?>
                  <span class="info-box-number"><?php echo "$jumlah"; ?></span>
                  <h5>
                    <small>
                      <?php
                      $data_pasien = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar
                            FROM pasien 
                            INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                            WHERE pasien.jk='L'  AND reg_periksa.status_lanjut='Ranap' AND reg_periksa.stts_daftar='Lama' and reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($data_pasien);
                      ?>
                      <span class="count_top"><i class="fa fa-male"></i> <?php echo "$jumlah"; ?></span>
                      <?php
                      $data_pasien = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar
                            FROM pasien 
                            INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                            WHERE pasien.jk='P' AND reg_periksa.status_lanjut='Ranap' AND reg_periksa.stts_daftar='Lama' and reg_periksa.tgl_registrasi=CURDATE()");
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
            <div class="col-md-3 col-sm-6 col-12">
              <div class="info-box">
                <span class="info-box-icon bg-danger"><i class="fa fa-bed"></i></span>

                <div class="info-box-content">
                  <span class="info-box-text">Jumlah Kamar</span>
                  <?php
                  $kamar = mysqli_query($koneksi, "SELECT kd_kamar FROM kamar");
                   $jumlah = mysqli_num_rows($kamar);
                  ?>
                  <span class="info-box-number"> <?php echo "$jumlah"; ?></span>
                  <h5>
                     <small>
                      <?php
                      $kamar = mysqli_query($koneksi, "SELECT  kamar.`status` from kamar WHERE kamar.status='ISI'");
                      $jumlah = mysqli_num_rows($kamar);
                      ?>
                      <span class="count_top"><i class="fa fa-user-plus" data-toggle="tooltip" data-placement="top" title="Isi!"></i><?php echo "$jumlah"; ?></span>
                      <?php
                      $kamar = mysqli_query($koneksi, "SELECT  kamar.`status` from kamar WHERE kamar.status='KOSONG'");
                      $jumlah = mysqli_num_rows($kamar);
                      ?>
                      <span class="count_top"><i class="fa fa-user-times" data-toggle="tooltip" data-placement="top" title="Kosong!"></i> <?php echo "$jumlah"; ?></span>
                      <?php
                      $kamar = mysqli_query($koneksi, "SELECT  kamar.`status` from kamar WHERE kamar.status='BOOKING'");
                      $jumlah = mysqli_num_rows($kamar);
                      ?>
                      <span class="count_top"><i class="fa fa-book" data-toggle="tooltip" data-placement="top" title="Dibooking!"></i> <?php echo "$jumlah"; ?></span>
                      <?php
                      $kamar = mysqli_query($koneksi, "SELECT  kamar.`status` from kamar WHERE kamar.status='DIBERSIHKAN'");
                      $jumlah = mysqli_num_rows($kamar);
                      ?>
                      <span class="count_top"><i class="fa fa-trash" data-toggle="tooltip" data-placement="top" title="Dibersihkan!"></i> <?php echo "$jumlah"; ?></span>

                    </small> 
                  </h5>
                </div>
                <!-- /.info-box-content -->
              </div>
              <!-- /.info-box -->
            </div>
            <!-- /.col -->
          </div>

          <!-- // kolom baris kedua :) -->
          <div class="row">
            <div class="col-md-3 col-sm-6 col-12">
              <div class="info-box">
                <span class="info-box-icon bg-dark"><i class="fa  fa-child"></i></span>
                <div class="info-box-content">
                  <span class="info-box-text">Pasien Keluar Hari ini</span>
                  <?php
                  $data_pasien = mysqli_query($koneksi, "SELECT pasien.jk,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar
                  FROM reg_periksa,pasien,kamar_inap
                  WHERE kamar_inap.stts_pulang='Sembuh' and kamar_inap.tgl_keluar=CURDATE()
                  AND kamar_inap.no_rawat=reg_periksa.no_rawat AND pasien.no_rkm_medis=reg_periksa.no_rkm_medis");
                  $jumlah = mysqli_num_rows($data_pasien);
                  ?>
                  <span class="info-box-number"><?php echo "$jumlah"; ?></span>
                  <h5>
                    <small>
                      <?php
                      $data_pasien = mysqli_query($koneksi, "SELECT pasien.jk,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar
                      FROM reg_periksa,pasien,kamar_inap
                      WHERE kamar_inap.stts_pulang='Sembuh' AND pasien.jk='L' and kamar_inap.tgl_keluar=CURDATE()
                      AND kamar_inap.no_rawat=reg_periksa.no_rawat AND pasien.no_rkm_medis=reg_periksa.no_rkm_medis");
                      $jumlahL = mysqli_num_rows($data_pasien);
                      ?>
                      <span class="count_top"><i class="fa fa-male"></i> <?php echo "$jumlahL"; ?></span>
                      <?php
                      $data_pasien = mysqli_query($koneksi, "SELECT pasien.jk,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar      
                      FROM reg_periksa,pasien,kamar_inap
                      WHERE kamar_inap.stts_pulang='Sembuh' AND pasien.jk='P' and kamar_inap.tgl_keluar=CURDATE()
                      AND kamar_inap.no_rawat=reg_periksa.no_rawat AND pasien.no_rkm_medis=reg_periksa.no_rkm_medis");
                      $jumlahP = mysqli_num_rows($data_pasien);
                      ?>
                      <span class="count_top"><i class="fa fa-female"></i> <?php echo "$jumlahP"; ?></span>
                    </small>
                  </h5>
                </div>
                <!-- /.info-box-content -->
              </div>
              <!-- /.info-box -->
            </div>
            <!-- /.col -->
            <div class="col-md-3 col-sm-6 col-12">
              <div class="info-box">
                <span class="info-box-icon bg-dark"><i class="fa  fa-child"></i></span>
                <div class="info-box-content">
                  <span class="info-box-text">Bayi Lahir Hari ini</span>
                  <?php
                  $data_pasien = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar
                      FROM pasien 
                      INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                      WHERE reg_periksa.stts='Dirawat' AND reg_periksa.status_lanjut='Ranap' AND reg_periksa.stts_daftar='Baru' and reg_periksa.tgl_registrasi=CURDATE()");
                  $jumlah = mysqli_num_rows($data_pasien);
                  ?>
                  <span class="info-box-number"><?php echo "$jumlah"; ?></span>
                  <h5>
                    <small>
                      <?php
                      $data_pasien = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar
                            FROM pasien 
                            INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                            WHERE pasien.jk='L' and reg_periksa.stts='Dirawat' AND reg_periksa.status_lanjut='Ranap' AND reg_periksa.stts_daftar='Baru'and reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($data_pasien);
                      ?>
                      <span class="count_top"><i class="fa fa-male"></i> <?php echo "$jumlah"; ?></span>
                      <?php
                      $data_pasien = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar
                            FROM pasien 
                            INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                            WHERE pasien.jk='P' and reg_periksa.stts='Dirawat' AND reg_periksa.status_lanjut='Ranap' AND reg_periksa.stts_daftar='Baru' and reg_periksa.tgl_registrasi=CURDATE()");
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
            <div class="col-md-3 col-sm-6 col-12">
              <div class="info-box">
                <span class="info-box-icon bg-dark"><i class="fa fa-ambulance"></i></span>
                <div class="info-box-content">
                  <span class="info-box-text">Pasien Status Meninggal</span>
                  <?php
                  $data_pasien = mysqli_query($koneksi, " SELECT pasien.jk,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang
                  FROM reg_periksa,pasien,kamar_inap
                  WHERE kamar_inap.stts_pulang='Meninggal' AND kamar_inap.tgl_keluar=CURDATE()
                  AND kamar_inap.no_rawat=reg_periksa.no_rawat AND pasien.no_rkm_medis=reg_periksa.no_rkm_medis");
                  $jumlah = mysqli_num_rows($data_pasien);
                  ?>
                  <span class="info-box-number"><?php echo "$jumlah"; ?></span>
                  <h5>
                    <small>
                      <?php
                      $data_pasien = mysqli_query($koneksi, "SELECT pasien.jk,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang
                      FROM reg_periksa,pasien,kamar_inap
                      WHERE kamar_inap.stts_pulang='Meninggal' AND pasien.jk='L' AND kamar_inap.tgl_keluar=CURDATE()
                      AND kamar_inap.no_rawat=reg_periksa.no_rawat AND pasien.no_rkm_medis=reg_periksa.no_rkm_medis");
                      $jumlah = mysqli_num_rows($data_pasien);
                      ?>
                      <span class="count_top"><i class="fa fa-male"></i> <?php echo "$jumlah"; ?></span>
                      <?php
                      $data_pasien = mysqli_query($koneksi, "SELECT pasien.jk,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang
                      FROM reg_periksa,pasien,kamar_inap
                      WHERE kamar_inap.stts_pulang='Meninggal' AND pasien.jk='P' AND kamar_inap.tgl_keluar=CURDATE()
                      AND kamar_inap.no_rawat=reg_periksa.no_rawat AND pasien.no_rkm_medis=reg_periksa.no_rkm_medis");
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
            <div class="col-md-3 col-sm-6 col-12">
              <div class="info-box">
                <span class="info-box-icon bg-dark"><i class="fa fa-bed"></i></span>

                <div class="info-box-content">
                  <span class="info-box-text">Pasien Rujuk</span>
                  <?php
                  $kamar = mysqli_query($koneksi, "SELECT pasien.jk,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang
                  FROM reg_periksa,pasien,kamar_inap
                  WHERE kamar_inap.stts_pulang='Rujuk' AND kamar_inap.tgl_keluar=CURDATE()
                  AND kamar_inap.no_rawat=reg_periksa.no_rawat AND pasien.no_rkm_medis=reg_periksa.no_rkm_medis");
                  $jumlah = mysqli_num_rows($kamar);
                  ?>
                  <span class="info-box-number"> <?php echo "$jumlah"; ?></span>
                  <h5>
                    <small>
                      <?php
                      $data_pasien = mysqli_query($koneksi, "SELECT pasien.jk,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang
                      FROM reg_periksa,pasien,kamar_inap
                      WHERE kamar_inap.stts_pulang='Rujuk' AND pasien.jk='L' AND kamar_inap.tgl_keluar=CURDATE()
                      AND kamar_inap.no_rawat=reg_periksa.no_rawat AND pasien.no_rkm_medis=reg_periksa.no_rkm_medis");
                      $jumlah = mysqli_num_rows($data_pasien);
                      ?>
                      <span class="count_top"><i class="fa fa-male"></i> <?php echo "$jumlah"; ?></span>
                      <?php
                      $data_pasien = mysqli_query($koneksi, "SELECT pasien.jk,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang
                      FROM reg_periksa,pasien,kamar_inap
                      WHERE kamar_inap.stts_pulang='Rujuk' AND pasien.jk='P' AND kamar_inap.tgl_keluar=CURDATE()
                      AND kamar_inap.no_rawat=reg_periksa.no_rawat AND pasien.no_rkm_medis=reg_periksa.no_rkm_medis");
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
          </div>
          <!-- // kolom baris kedua :) -->
          <!-- Main content -->


          <div class="row">
            <div class="col-md-6 ">
              <figure class="highcharts-figure">
                <div id="kamar"></div>
              </figure>
            </div>

            <div class="col-md-6 ">
              <figure class="highcharts-figure">
                <div id="kamar-pasien"></div>
              </figure>
            </div>



            <div class="col-md-6 ">
              <figure class="highcharts-figure">
                <div id="chart-status"></div>
              </figure>
            </div>

            <div class="col-md-6 ">
              <figure class="highcharts-figure">
                <div id="chart-cara-bayar"></div>
              </figure>
            </div>

            <div class="col-md-6 ">
              <figure class="highcharts-figure">
                <div id="chart-cara-Pulang"></div>
              </figure>
            </div>

            <div class="col-md-6 ">
              <figure class="highcharts-figure">
                <div id="chart-ranap-pasien-baru"></div>
              </figure>
            </div>

            <div class="col-md-12 ">
              <figure class="highcharts-figure">
                <div id="chart-ranap-pasien-lama"></div>
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
              <figure class="highcharts-figure">
                <div id="chart_ranap_mingguan"></div>
              </figure>
            </div>

            <div class="col-md-12">
              <figure class="highcharts-figure">
                <div id="chart_ranap_bulanan"></div>
              </figure>
            </div>

            <div class="col-md-12">
              <figure class="highcharts-figure">
                <div id="chart_ranap_tahun"></div>
              </figure>
            </div>

            <!-- <div class="col-md-6">
              <figure class="highcharts-figure">
                <select class="mod">
                  <option value=''>Mod</option>
                  <option value="CT">CT</option>
                  <option value="MRI">MRI</option>
                  <option value="PET">PET</option>
                </select>
                <select class="body">
                  <option value=''>bodyPart</option>
                  <option value="brain">brain</option>
                  <option value="arm">arm</option>
                  <option value="wrist">wrist</option>
                  <option value="thyroid">thyroid</option>
                  <option value="heart">heart</option>
                  <div id="sampel"></div>
              </figure>
            </div> -->

            <div class="col-md-6">
              <figure class="highcharts-figure">
                <div id="container11"></div>
              </figure>
            </div>

            <!-- /.card -->
          </div>
          <!-- /.col (RIGHT) -->
        </div>
        <!-- /.row -->
        <!-- /.container-fluid -->





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


</body>

</html>
<!-- filter  -->
<?php
if (isset($_POST['tgl_awal']) && isset($_POST['tgl_akhir'])) {
  $tgl_awal = date('Y-m-d', strtotime($_POST["tgl_awal"]));
  $tgl_akhir = date('Y-m-d', strtotime($_POST["tgl_akhir"]));
}
?>

<!-- QWERY JUMLAH PASIEN RANAP -->
<?php
$jumlah_pasien_ranap = array();
$result = mysqli_query($koneksi, "SELECT reg_periksa.stts,reg_periksa.status_lanjut,DATE_FORMAT(tgl_registrasi,'%M'),COUNT(reg_periksa.no_rawat) AS Jumlah 
FROM reg_periksa
WHERE stts='Dirawat' AND status_lanjut='Ranap' and reg_periksa.tgl_registrasi=CURDATE();  ");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $jumlah_pasien_ranap[] = $row[0];
  $status_ranap[] = $row[1];
  $tgl_bulan_ranap_bro[] = $row[2];
  $jumlah_ranap[] = $row[3];
  // $tahun_bulan_tgl_mingguan[] = $row[3];
}
?>

<?php
$jumlah_pasien_ranap = array();
$result = mysqli_query($koneksi, "SELECT reg_periksa.stts,reg_periksa.status_lanjut,DATE_FORMAT(tgl_registrasi,'%M'),COUNT(reg_periksa.no_rawat) AS Jumlah 
FROM reg_periksa
WHERE stts='Sudah' AND status_lanjut='Ranap' and reg_periksa.tgl_registrasi=CURDATE();  ");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $jumlah_pasien_ranap[] = $row[0];
  $status_ranap[] = $row[1];
  $tgl_bulan_ranap_bro[] = $row[2];
  $jumlah_ranap_sudah[] = $row[3];
  // $tahun_bulan_tgl_mingguan[] = $row[3];
}
?>
<!-- /QWERY JUMLAH PASIEN RANAP -->

<!-- QWERY STATUS KAMAR-->
<?php
$jumlah_kamar_anyelir = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.statusdata,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0008'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar[] = $row[0];
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_anyelir[] = $row[3];
}
?>

<?php
$jumlah_kamar_anyelir_isi = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0008' AND kamar.`status`='ISI'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {

  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_anyelir_isi[] = $row[3];
}
?>

<?php
$jumlah_kamar_anyelir_kosong = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0008' AND kamar.`status`='KOSONG'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {

  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_anyelir_kosong[] = $row[3];
}
?>

<?php
$jumlah_kamar_anyelir_dibersihkan = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0008' AND kamar.`status`='DIBERSIHKAN'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {

  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_anyelir_dibersihkan[] = $row[3];
}
?>

<?php
$jumlah_kamar_adelia = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.statusdata,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0005'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_adelia[] = $row[0];
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_adelia[] = $row[3];
}
?>

<?php
$jumlah_kamar_adelia_isi = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0005' AND kamar.`status`='ISI'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_adelia_isi[] = $row[3];
}
?>

<?php
$jumlah_kamar_adelia_kosong = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0005' AND kamar.`status`='KOSONG'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_adelia_kosong[] = $row[3];
}
?>

<?php
$jumlah_kamar_adelia_dibersihkan = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0005' AND kamar.`status`='DIBERSIHKAN'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_adelia_dibersihkan[] = $row[3];
}
?>

<?php
$jumlah_kamar_asoka = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.statusdata,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0032'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_asoka[] = $row[0];
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_asoka[] = $row[3];
}
?>

<?php
$jumlah_kamar_asoka_isi = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0032' AND kamar.`status`='ISI'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_asoka_isi[] = $row[3];
}
?>

<?php
$jumlah_kamar_asoka_kosong = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0032' AND kamar.`status`='KOSONG'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_asoka_kosong[] = $row[3];
}
?>

<?php
$jumlah_kamar_asoka_dibersihkan = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0032' AND kamar.`status`='DIBERSIHKAN'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_asoka_dibersihkan[] = $row[3];
}
?>

<?php
$jumlah_kamar_aster = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.statusdata,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0007'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_aster[] = $row[0];
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_aster[] = $row[3];
}
?>

<?php
$jumlah_kamar_aster_isi = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0007' AND kamar.`status`='ISI'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_aster_isi[] = $row[3];
}
?>

<?php
$jumlah_kamar_aster_kosong = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0007' AND kamar.`status`='KOSONG'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_aster_kosong[] = $row[3];
}
?>

<?php
$jumlah_kamar_aster_dibersihkan = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0007' AND kamar.`status`='DIBERSIHKAN'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_aster_dibersihkan[] = $row[3];
}
?>

<?php
$jumlah_kamar_bayi = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.statusdata,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0020'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_bayi[] = $row[0];
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_bayi[] = $row[3];
}
?>

<?php
$jumlah_kamar_bayi_isi = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0020' AND kamar.`status`='ISI'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_bayi_isi[] = $row[3];
}
?>

<?php
$jumlah_kamar_bayi_kosong = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0020' AND kamar.`status`='KOSONG'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_bayi_kosong[] = $row[3];
}
?>

<?php
$jumlah_kamar_bayi_dibersihkan = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0020' AND kamar.`status`='DIBERSIHKAN'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_bayi_dibersihkan[] = $row[3];
}
?>

<?php
$jumlah_kamar_cempaka = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.statusdata,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0019'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_cempaka[] = $row[0];
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_cempaka[] = $row[3];
}
?>

<?php
$jumlah_kamar_cempaka_isi = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0019' AND kamar.`status`='ISI'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_cempaka_isi[] = $row[3];
}
?>

<?php
$jumlah_kamar_cempaka_kosong = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0019' AND kamar.`status`='KOSONG'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_cempaka_kosong[] = $row[3];
}
?>

<?php
$jumlah_kamar_cempaka_dibersihkan = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0019' AND kamar.`status`='DIBERSIHKAN'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_cempaka_dibersihkan[] = $row[3];
}
?>

<?php
$jumlah_kamar_dahlia = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.statusdata,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0017'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_dahlia[] = $row[0];
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_dahlia[] = $row[3];
}
?>

<?php
$jumlah_kamar_dahlia_isi = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0017' AND kamar.`status`='ISI'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_dahlia_isi[] = $row[3];
}
?>

<?php
$jumlah_kamar_dahlia_kosong = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0017' AND kamar.`status`='KOSONG'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_dahlia_kosong[] = $row[3];
}
?>

<?php
$jumlah_kamar_dahlia_dibersihkan = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0017' AND kamar.`status`='DIBERSIHKAN'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_dahlia_dibersihkan[] = $row[3];
}
?>

<?php
$jumlah_kamar_bedahkls1 = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.statusdata,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0012'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_bedahkls1[] = $row[0];
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_bedahkls1[] = $row[3];
}
?>

<?php
$jumlah_kamar_bedahkelas1_isi = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0012' AND kamar.`status`='ISI'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_bedahkelas1_isi[] = $row[3];
}
?>

<?php
$jumlah_kamar_bedahkls1_kosong = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0012' AND kamar.`status`='KOSONG'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_bedahkls1_kosong[] = $row[3];
}
?>

<?php
$jumlah_kamar_bedahkls1_dibersihkan = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0012' AND kamar.`status`='DIBERSIHKAN'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_bedahkls1_dibersihkan[] = $row[3];
}
?>

<?php
$jumlah_kamar_hcu = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.statusdata,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0009'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_hcu[] = $row[0];
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_hcu[] = $row[3];
}
?>

<?php
$jumlah_kamar_hcu_isi = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0009' AND kamar.`status`='ISI'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_hcu_isi[] = $row[3];
}
?>

<?php
$jumlah_kamar_hcu_kosong = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0009' AND kamar.`status`='KOSONG'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_hcu_kosong[] = $row[3];
}
?>

<?php
$jumlah_kamar_hcu_dibersihkan = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0009' AND kamar.`status`='DIBERSIHKAN'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_hcu_dibersihkan[] = $row[3];
}
?>

<?php
$jumlah_kamar_icu = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.statusdata,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0014'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_icu[] = $row[0];
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_icu[] = $row[3];
}
?>

<?php
$jumlah_kamar_icu_isi = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0014' AND kamar.`status`='ISI'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_icu_isi[] = $row[3];
}
?>

<?php
$jumlah_kamar_icu_kosong = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0014' AND kamar.`status`='KOSONG'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_icu_kosong[] = $row[3];
}
?>

<?php
$jumlah_kamar_icu_dibersihkan = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0014' AND kamar.`status`='DIBERSIHKAN'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_icu_dibersihkan[] = $row[3];
}
?>

<?php
$jumlah_kamar_interna = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.statusdata,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0085'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_interna[] = $row[0];
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_interna[] = $row[3];
}
?>

<?php
$jumlah_kamar_interna_isi = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0085' AND kamar.`status`='ISI'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_interna_isi[] = $row[3];
}
?>

<?php
$jumlah_kamar_interna_kosong = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0085' AND kamar.`status`='KOSONG'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_interna_kosong[] = $row[3];
}
?>

<?php
$jumlah_kamar_interna_dibersihkan = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0085' AND kamar.`status`='DIBERSIHKAN'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_interna_dibersihkan[] = $row[3];
}
?>

<?php
$jumlah_kamar_bersalin = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.statusdata,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0026'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_bersalin[] = $row[0];
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_bersalin[] = $row[3];
}
?>

<?php
$jumlah_kamar_bersalin_isi = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0026' AND kamar.`status`='ISI'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_bersalin_isi[] = $row[3];
}
?>

<?php
$jumlah_kamar_bersalin_kosong = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0026' AND kamar.`status`='KOSONG'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_bersalin_kosong[] = $row[3];
}
?>

<?php
$jumlah_kamar_bersalin_dibersihkan = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0026' AND kamar.`status`='DIBERSIHKAN'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_bersalin_dibersihkan[] = $row[3];
}
?>

<?php
$jumlah_kamar_mawar = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.statusdata,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0004'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_mawar[] = $row[0];
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_mawar[] = $row[3];
}
?>

<?php
$jumlah_kamar_mawar_isi = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0004' AND kamar.`status`='ISI'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_mawar_isi[] = $row[3];
}
?>

<?php
$jumlah_kamar_mawar_kosong = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0004' AND kamar.`status`='KOSONG'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_mawar_kosong[] = $row[3];
}
?>

<?php
$jumlah_kamar_mawar_dibersihkan = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0004' AND kamar.`status`='DIBERSIHKAN'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_mawar_dibersihkan[] = $row[3];
}
?>

<?php
$jumlah_kamar_melati = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.statusdata,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0015'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_melati[] = $row[0];
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_melati[] = $row[3];
}
?>

<?php
$jumlah_kamar_melati_isi = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0015' AND kamar.`status`='ISI'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_melati_isi[] = $row[3];
}
?>

<?php
$jumlah_kamar_melati_kosong = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0015' AND kamar.`status`='KOSONG'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_melati_kosong[] = $row[3];
}
?>

<?php
$jumlah_kamar_melati_dibersihkan = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0015' AND kamar.`status`='DIBERSIHKAN'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_melati_dibersihkan[] = $row[3];
}
?>

<?php
$jumlah_kamar_sakura = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.statusdata,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0010'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_sakura[] = $row[0];
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_sakura[] = $row[3];
}
?>

<?php
$jumlah_kamar_sakura_isi = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0010' AND kamar.`status`='ISI'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_sakura_isi[] = $row[3];
}
?>

<?php
$jumlah_kamar_sakura_kosong = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0010' AND kamar.`status`='KOSONG'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_sakura_kosong[] = $row[3];
}
?>

<?php
$jumlah_kamar_sakura_dibersihkan = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0010' AND kamar.`status`='DIBERSIHKAN'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_sakura_dibersihkan[] = $row[3];
}
?>

<?php
$jumlah_kamar_seruni = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.statusdata,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0021'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_seruni[] = $row[0];
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_seruni[] = $row[3];
}
?>

<?php
$jumlah_kamar_seruni_isi = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0021' AND kamar.`status`='ISI'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_seruni_isi[] = $row[3];
}
?>

<?php
$jumlah_kamar_seruni_kosong = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0021' AND kamar.`status`='KOSONG'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_seruni_kosong[] = $row[3];
}
?>

<?php
$jumlah_kamar_seruni_dibersihkan = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0021' AND kamar.`status`='DIBERSIHKAN'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_seruni_dibersihkan[] = $row[3];
}
?>

<?php
$jumlah_kamar_adelia = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.statusdata,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0005'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_adelia[] = $row[0];
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_adelia[] = $row[3];
}
?>

<?php
$jumlah_kamar_adelia_isi = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0005' AND kamar.`status`='ISI'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_adelia_isi[] = $row[3];
}
?>

<?php
$jumlah_kamar_adelia_kosong = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0005' AND kamar.`status`='KOSONG'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_adelia_kosong[] = $row[3];
}
?>

<?php
$jumlah_kamar_adelia_dibersihkan = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0005' AND kamar.`status`='DIBERSIHKAN'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_adelia_dibersihkan[] = $row[3];
}
?>

<?php
$jumlah_kamar_anggrek = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.statusdata,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0006'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_anggrek[] = $row[0];
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_anggrek[] = $row[3];
}
?>

<?php
$jumlah_kamar_anggrek_isi = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0006' AND kamar.`status`='ISI'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_anggrek_isi[] = $row[3];
}
?>

<?php
$jumlah_kamar_anggrek_kosong = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0006' AND kamar.`status`='KOSONG'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_anggrek_kosong[] = $row[3];
}
?>

<?php
$jumlah_kamar_anggrek_dibersihkan = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0006' AND kamar.`status`='DIBERSIHKAN'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_anggrek_dibersihkan[] = $row[3];
}
?>

<?php
$jumlah_kamar_flamboyan = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.statusdata,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0013'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_flamboyan[] = $row[0];
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_flamboyan[] = $row[3];
}
?>

<?php
$jumlah_kamar_flamboyan_isi = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0013' AND kamar.`status`='ISI'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_flamboyan_isi[] = $row[3];
}
?>

<?php
$jumlah_kamar_flamboyan_kosong = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0013' AND kamar.`status`='KOSONG'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_flamboyan_kosong[] = $row[3];
}
?>

<?php
$jumlah_kamar_flamboyan_dibersihkan = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0013' AND kamar.`status`='DIBERSIHKAN'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $jumlah_kamar_flamboyan_dibersihkan[] = $row[3];
}
?>
<!-- /QWERY STATUS KAMAR isi, kosong, dibersihkan-->

<!-- QWERY Jumlah pasien KAMAR-PASIEN option tanggal -->
<?php
$total_kamar_anyelir = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0008' AND kamar.`status`='ISI'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_anyelir[] = $row[0];
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $total_kamar_anyelir[] = $row[3];
}
?>

<?php
$total_kamar_adelia = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0005' AND kamar.`status`='ISI'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_total_adelia[] = $row[0];
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $total_kamar_adelia[] = $row[3];
}
?>

<?php
$total_kamar_asoka = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0032' AND kamar.`status`='ISI'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_total_asoka[] = $row[0];
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $total_kamar_asoka[] = $row[3];
}
?>

<?php
$total_kamar_aster = array();
$result = mysqli_query($koneksi, "SELECT kamar.kd_kamar,kamar.kd_bangsal,kamar.`status`,COUNT(kamar.kd_kamar) AS Jumlah FROM
kamar WHERE kamar.statusdata ='1' AND kamar.kd_bangsal='B0007' AND kamar.`status`='ISI'");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_total_aster[] = $row[0];
  $kd_bangsal[] = $row[1];
  $status[] = $row[2];
  $total_kamar_aster[] = $row[3];
}
?>
<!-- QWERY /Jumlah pasien KAMAR-PASIEN -->
<!-- /chart cara bayar BPJS-->
<?php
$jumlah_pasein_bayar_ranap = array();
$result = mysqli_query($koneksi, "SELECT reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.kd_pj,COUNT(reg_periksa.kd_pj)
FROM reg_periksa WHERE stts='sudah' AND status_lanjut='Ranap' AND kd_pj='BPJ' and tgl_registrasi=CURDATE()");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $jumlah_pasein_bayar_ranap[] = $row[0];
  $status_lanjut[] = $row[1];
  $kd_pj[] = $row[2];
  $jumlah_bayar_BPJS_ranap[] = $row[3];
}
?>

<!-- /chart cara bayar BPJS ketenaga kerjaan-->
<?php
$jumlah_pasein_bayar_ranap = array();
$result = mysqli_query($koneksi, "SELECT reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.kd_pj,COUNT(reg_periksa.kd_pj)
FROM reg_periksa WHERE stts='sudah' AND status_lanjut='Ranap' AND kd_pj='A88' and tgl_registrasi=CURDATE()");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $jumlah_pasein_bayar_ranap[] = $row[0];
  $status_lanjut[] = $row[1];
  $kd_pj[] = $row[2];
  $jumlah_bayar_BPJS_ketenagakerjaan[] = $row[3];
}
?>

<!-- /chart cara bayar Mandiri -->
<?php
$jumlah_pasein_bayar_ranap = array();
$result = mysqli_query($koneksi, "SELECT reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.kd_pj,COUNT(reg_periksa.kd_pj)
FROM reg_periksa WHERE stts='sudah' AND status_lanjut='Ranap' AND kd_pj='INH' and tgl_registrasi=CURDATE()");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $jumlah_pasein_bayar_ranap[] = $row[0];
  $status_lanjut[] = $row[1];
  $kd_pj[] = $row[2];
  $jumlah_bayar_mandiri[] = $row[3];
}
?>

<!-- /chart cara bayar PLN -->
<?php
$jumlah_pasein_bayar_ranap = array();
$result = mysqli_query($koneksi, "SELECT reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.kd_pj,COUNT(reg_periksa.kd_pj)
FROM reg_periksa WHERE stts='sudah' AND status_lanjut='Ranap' AND kd_pj='A30' and tgl_registrasi=CURDATE()");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $jumlah_pasein_bayar_ranap[] = $row[0];
  $status_lanjut[] = $row[1];
  $kd_pj[] = $row[2];
  $jumlah_bayar_pln[] = $row[3];
}
?>

<!-- /chart cara bayar telkom -->
<?php
$jumlah_pasein_bayar_ranap = array();
$result = mysqli_query($koneksi, "SELECT reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.kd_pj,COUNT(reg_periksa.kd_pj)
FROM reg_periksa WHERE stts='sudah' AND status_lanjut='Ranap' AND kd_pj='A41' and tgl_registrasi=CURDATE()");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $jumlah_pasein_bayar_ranap[] = $row[0];
  $status_lanjut[] = $row[1];
  $kd_pj[] = $row[2];
  $jumlah_bayar_telkom[] = $row[3];
}
?>

<!-- /chart cara bayar Umum-->
<?php
$jumlah_pasien_bayar_ranap = array();
$result = mysqli_query($koneksi, "SELECT reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.kd_pj,COUNT(reg_periksa.kd_pj)
FROM reg_periksa WHERE stts='sudah' AND status_lanjut='Ranap' AND kd_pj='A09' AND status_bayar='Sudah Bayar' and tgl_registrasi=CURDATE()");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $jumlah_pasien_bayar_ranap[] = $row[0];
  $status_lanjut[] = $row[1];
  $kd_pj[] = $row[2];
  $jumlah_bayar_umum_ranap[] = $row[3];
}
?>

<!-- /chart cara bayar LAINNya-->
<?php
$jumlah_pasien_bayar_ranap = array();
$result = mysqli_query($koneksi, "SELECT reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.kd_pj,COUNT(reg_periksa.kd_pj)
FROM reg_periksa WHERE stts='sudah' AND status_lanjut='Ranap' AND kd_pj='A86' AND status_bayar='Sudah Bayar' and tgl_registrasi=CURDATE()");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $jumlah_pasien_bayar_ranap[] = $row[0];
  $status_lanjut[] = $row[1];
  $kd_pj[] = $row[2];
  $jumlah_bayar_lain_ranap[] = $row[3];
}
?>
<!-- /qwery jenis bayar pasien ranap -->

<!-- qwery jenis pasien pulang -->
<?php
$Jumlah_pasien_pulang_atas_permintaan_sendiri = array();
$result = mysqli_query($koneksi, "SELECT tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang, COUNT(kamar_inap.no_rawat) AS Jumlah 
FROM reg_periksa,pasien,kamar_inap
WHERE kamar_inap.tgl_keluar=CURDATE() AND kamar_inap.stts_pulang='Atas Permintaan senditi'
AND kamar_inap.no_rawat=reg_periksa.no_rawat AND pasien.no_rkm_medis=reg_periksa.no_rkm_medis");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $Jumlah_pasien_pulang_atas_permintaan_sendiri[] = $row[3];
}
?>
<?php
$Jumlah_pasien_pulang_atas_persetujuan_dokter = array();
$result = mysqli_query($koneksi, "SELECT tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang, COUNT(kamar_inap.no_rawat) AS Jumlah 
FROM reg_periksa,pasien,kamar_inap
WHERE kamar_inap.tgl_keluar=CURDATE() AND kamar_inap.stts_pulang='Atas Persetujuan dokter'
AND kamar_inap.no_rawat=reg_periksa.no_rawat AND pasien.no_rkm_medis=reg_periksa.no_rkm_medis");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $Jumlah_pasien_pulang_atas_persetujuan_dokter[] = $row[3];
}
?>
<?php
$Jumlah_pasien_pulang_sembuh = array();
$result = mysqli_query($koneksi, "SELECT tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang, COUNT(kamar_inap.no_rawat) AS Jumlah 
FROM reg_periksa,pasien,kamar_inap
WHERE kamar_inap.tgl_keluar=CURDATE() AND kamar_inap.stts_pulang='Sembuh'
AND kamar_inap.no_rawat=reg_periksa.no_rawat AND pasien.no_rkm_medis=reg_periksa.no_rkm_medis");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $Jumlah_pasien_pulang_sembuh[] = $row[3];
}
?>
<?php
$Jumlah_pasien_pulang_meninggal = array();
$result = mysqli_query($koneksi, "SELECT tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang, COUNT(kamar_inap.no_rawat) AS Jumlah 
FROM reg_periksa,pasien,kamar_inap
WHERE kamar_inap.tgl_keluar=CURDATE() AND kamar_inap.stts_pulang='Meninggal'
AND kamar_inap.no_rawat=reg_periksa.no_rawat AND pasien.no_rkm_medis=reg_periksa.no_rkm_medis");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $Jumlah_pasien_pulang_meninggal[] = $row[3];
}
?>
<?php
$Jumlah_pasien_pulang_rujuk = array();
$result = mysqli_query($koneksi, "SELECT tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang, COUNT(kamar_inap.no_rawat) AS Jumlah 
FROM reg_periksa,pasien,kamar_inap
WHERE kamar_inap.tgl_keluar=CURDATE() AND kamar_inap.stts_pulang='Rujuk'
AND kamar_inap.no_rawat=reg_periksa.no_rawat AND pasien.no_rkm_medis=reg_periksa.no_rkm_medis");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $Jumlah_pasien_pulang_rujuk[] = $row[3];
}
?>
<!-- /qwery jenis pasien pulang -->

<!-- Chart Jenis Pasien Baru TNI -->
<?php
$jumlah_gol_tni = array();
$result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,reg_periksa.status_bayar,
pasien_tni.golongan_tni,reg_periksa.status_lanjut,reg_periksa.stts,reg_periksa.stts_daftar,count(reg_periksa.no_rkm_medis)AS Jumlah_pasien 
FROM reg_periksa,pasien_tni
WHERE reg_periksa.status_lanjut='Ranap' and reg_periksa.stts_daftar='Baru'  and pasien_tni.no_rkm_medis=reg_periksa.no_rkm_medis 
 AND pasien_tni.golongan_tni='3' GROUP BY golongan_tni");
while ($row = mysqli_fetch_array($result)) {
  $jumlah_gol_tni[] = $row[6];
}
?>

<!-- /chart jenis pasien PNS TNI AD->
<?php
$jumlah_gol_pns = array();
$result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,reg_periksa.status_bayar,
pasien_tni.golongan_tni,reg_periksa.status_lanjut,reg_periksa.stts,reg_periksa.stts_daftar,count(reg_periksa.no_rkm_medis)AS Jumlah_pasien 
FROM reg_periksa,pasien_tni
WHERE reg_periksa.status_lanjut='Ranap' AND reg_periksa.stts_daftar='Baru' and pasien_tni.no_rkm_medis=reg_periksa.no_rkm_medis 
 AND pasien_tni.golongan_tni='5' GROUP BY golongan_tni");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $jumlah_gol_pns[] = $row[6];
}
?>

<!-/chart jenis pasien  KEULUARGA PNS TNI-->
<?php
$jumlah_gol_keluarga_pns = array();
$result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,reg_periksa.status_bayar,
pasien_tni.golongan_tni,reg_periksa.status_lanjut,reg_periksa.stts,reg_periksa.stts_daftar,count(reg_periksa.no_rkm_medis)AS Jumlah_pasien 
FROM reg_periksa,pasien_tni
WHERE reg_periksa.status_lanjut='Ranap' AND reg_periksa.stts_daftar='Baru' and pasien_tni.no_rkm_medis=reg_periksa.no_rkm_medis 
 AND pasien_tni.golongan_tni='11' GROUP BY golongan_tni");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $jumlah_gol_keluarga_pns[] = $row[6];
}
?>

<!-- /chart jenis pasien Baru UMUM-->
<?php
$jumlah_pasein_umum = array();
$result = mysqli_query($koneksi, "SELECT pasien.no_rkm_medis,reg_periksa.status_lanjut,reg_periksa.stts_daftar,reg_periksa.stts ,reg_periksa.tgl_registrasi, 
COUNT(reg_periksa.no_rkm_medis) AS JUMLAH_PASEIN_UMUM
FROM pasien,reg_periksa
WHERE reg_periksa.status_lanjut='Ranap' AND   reg_periksa.kd_pj='A09'
AND pasien.no_rkm_medis=reg_periksa.no_rkm_medis");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $jumlah_pasein_umum[] = $row[5];
}
?>
<!-- /qwery chart pasein baru ranap -->

<!-- CHART PASIEN LAMA RANAP -->
<?php
$jumlah_gol_tni_Lama = array();
$result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,reg_periksa.status_bayar,
pasien_tni.golongan_tni,reg_periksa.status_lanjut,reg_periksa.stts,reg_periksa.stts_daftar,count(reg_periksa.no_rkm_medis)AS Jumlah_pasien 
FROM reg_periksa,pasien_tni
WHERE reg_periksa.status_lanjut='Ranap' AND reg_periksa.stts_daftar='Lama' and pasien_tni.no_rkm_medis=reg_periksa.no_rkm_medis 
 AND pasien_tni.golongan_tni='3' GROUP BY golongan_tni");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $jumlah_gol_tni_Lama[] = $row[6];
}
?>

<!-- /chart jenis pasien PNS TNI-->
<?php
$jumlah_gol_pns_lama = array();
$result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,reg_periksa.status_bayar,
pasien_tni.golongan_tni,reg_periksa.status_lanjut,reg_periksa.stts,reg_periksa.stts_daftar,count(reg_periksa.no_rkm_medis)AS Jumlah_pasien 
FROM reg_periksa,pasien_tni
WHERE reg_periksa.status_lanjut='Ranap' AND reg_periksa.stts_daftar='Lama' and pasien_tni.no_rkm_medis=reg_periksa.no_rkm_medis 
 AND pasien_tni.golongan_tni='5' GROUP BY golongan_tni");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $jumlah_gol_pns_lama[] = $row[6];
}
?>

<!-- /chart jenis pasien  KEULUARGA PNS TNI-->
<?php
$jumlah_gol_keluarga_pns_lama = array();
$result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,reg_periksa.status_bayar,
pasien_tni.golongan_tni,reg_periksa.status_lanjut,reg_periksa.stts,reg_periksa.stts_daftar,count(reg_periksa.no_rkm_medis)AS Jumlah_pasien 
FROM reg_periksa,pasien_tni
WHERE reg_periksa.status_lanjut='Ranap' AND reg_periksa.stts_daftar='Lama' and pasien_tni.no_rkm_medis=reg_periksa.no_rkm_medis 
 AND pasien_tni.golongan_tni='11' GROUP BY golongan_tni");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $jumlah_gol_keluarga_pns_lama[] = $row[6];
}
?>

<!-- /chart jenis pasien Baru UMUM-->
<?php
$jumlah_pasein_umum_lama = array();
$result = mysqli_query($koneksi, "SELECT pasien.no_rkm_medis,reg_periksa.status_lanjut,reg_periksa.stts_daftar,reg_periksa.stts ,reg_periksa.tgl_registrasi, 
COUNT(reg_periksa.no_rkm_medis) AS JUMLAH_PASEIN_UMUM
FROM pasien,reg_periksa
WHERE reg_periksa.status_lanjut='Ranap' AND reg_periksa.stts_daftar='Lama' AND reg_periksa.stts='Dirawat' 
AND pasien.no_rkm_medis=reg_periksa.no_rkm_medis");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $jumlah_pasein_umum_lama[] = $row[5];
}
?>

<!-- qwery ranap Mingguan -->
<?php
$jumlah_pasein_kamar_mingguan_anyelir = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,kamar_inap.tgl_masuk, COUNT(kamar_inap.no_rawat) AS Jumlah
  FROM kamar_inap,kamar
  WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0008' AND
  kamar_inap.tgl_masuk BETWEEN   '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
  GROUP BY YEARWEEK(kamar_inap.tgl_masuk) ASC");
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_mingguan[] = $row[0];
  $kd_Bangsal_mingguan[] = $row[1];
  $tgl_keluar_mingguan[] = $row[2];
  $jumlah_pasein_kamar_mingguan_anyelir[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_mingguan_adelia = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,kamar_inap.tgl_masuk, COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0005' AND
kamar_inap.tgl_masuk BETWEEN   '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
GROUP BY YEARWEEK(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_mingguan[] = $row[0];
  $kd_Bangsal_mingguan[] = $row[1];
  $tgl_keluar_mingguan[] = $row[2];
  $jumlah_pasein_kamar_mingguan_adelia[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_mingguan_asoka = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,kamar_inap.tgl_masuk, COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0032' AND
kamar_inap.tgl_masuk BETWEEN   '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
GROUP BY YEARWEEK(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_mingguan[] = $row[0];
  $kd_Bangsal_mingguan[] = $row[1];
  $tgl_keluar_mingguan[] = $row[2];
  $jumlah_pasein_kamar_mingguan_asoka[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_mingguan_aster = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,kamar_inap.tgl_masuk, COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0007' AND
kamar_inap.tgl_masuk BETWEEN   '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
GROUP BY YEARWEEK(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_mingguan[] = $row[0];
  $kd_Bangsal_mingguan[] = $row[1];
  $tgl_keluar_mingguan[] = $row[2];
  $jumlah_pasein_kamar_mingguan_aster[] = $row[3];
}
?>


<?php
$jumlah_pasein_kamar_mingguan_bayi = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,kamar_inap.tgl_masuk, COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0020' AND
kamar_inap.tgl_masuk BETWEEN   '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
GROUP BY YEARWEEK(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_mingguan[] = $row[0];
  $kd_Bangsal_mingguan[] = $row[1];
  $tgl_keluar_mingguan[] = $row[2];
  $jumlah_pasein_kamar_mingguan_bayi[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_mingguan_cempaka = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,kamar_inap.tgl_masuk, COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0019' AND
kamar_inap.tgl_masuk BETWEEN   '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
GROUP BY YEARWEEK(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_mingguan[] = $row[0];
  $kd_Bangsal_mingguan[] = $row[1];
  $tgl_keluar_mingguan[] = $row[2];
  $jumlah_pasein_kamar_mingguan_cempaka[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_mingguan_dahlia = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,kamar_inap.tgl_masuk, COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0017' AND
kamar_inap.tgl_masuk BETWEEN   '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
GROUP BY YEARWEEK(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_mingguan[] = $row[0];
  $kd_Bangsal_mingguan[] = $row[1];
  $tgl_keluar_mingguan[] = $row[2];
  $jumlah_pasein_kamar_mingguan_dahlia[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_mingguan_bedahkl1 = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,kamar_inap.tgl_masuk, COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0012' AND
kamar_inap.tgl_masuk BETWEEN   '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
GROUP BY YEARWEEK(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_mingguan[] = $row[0];
  $kd_Bangsal_mingguan[] = $row[1];
  $tgl_keluar_mingguan[] = $row[2];
  $jumlah_pasein_kamar_mingguan_bedahkl1[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_mingguan_hcu = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,kamar_inap.tgl_masuk, COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0009' AND
kamar_inap.tgl_masuk BETWEEN   '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
GROUP BY YEARWEEK(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_mingguan[] = $row[0];
  $kd_Bangsal_mingguan[] = $row[1];
  $tgl_keluar_mingguan[] = $row[2];
  $jumlah_pasein_kamar_mingguan_hcu[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_mingguan_kamar_icu = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,kamar_inap.tgl_masuk, COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0014' AND
kamar_inap.tgl_masuk BETWEEN   '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
GROUP BY YEARWEEK(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_mingguan[] = $row[0];
  $kd_Bangsal_mingguan[] = $row[1];
  $tgl_keluar_mingguan[] = $row[2];
  $jumlah_pasein_kamar_mingguan_kamar_icu[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_mingguan_kamar_interna = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,kamar_inap.tgl_masuk, COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0085' AND
kamar_inap.tgl_masuk BETWEEN   '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
GROUP BY YEARWEEK(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_mingguan[] = $row[0];
  $kd_Bangsal_mingguan[] = $row[1];
  $tgl_keluar_mingguan[] = $row[2];
  $jumlah_pasein_kamar_mingguan_kamar_interna[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_mingguan_kamar_bersalin = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,kamar_inap.tgl_masuk, COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0026' AND
kamar_inap.tgl_masuk BETWEEN   '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
GROUP BY YEARWEEK(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_mingguan[] = $row[0];
  $kd_Bangsal_mingguan[] = $row[1];
  $tgl_keluar_mingguan[] = $row[2];
  $jumlah_pasein_kamar_mingguan_kamar_bersalin[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_mingguan_kamar_mawar = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,kamar_inap.tgl_masuk, COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0004' AND
kamar_inap.tgl_masuk BETWEEN   '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
GROUP BY YEARWEEK(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_mingguan[] = $row[0];
  $kd_Bangsal_mingguan[] = $row[1];
  $tgl_keluar_mingguan[] = $row[2];
  $jumlah_pasein_kamar_mingguan_kamar_mawar[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_mingguan_kamar_melati = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,kamar_inap.tgl_masuk, COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0015' AND
kamar_inap.tgl_masuk BETWEEN   '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
GROUP BY YEARWEEK(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_mingguan[] = $row[0];
  $kd_Bangsal_mingguan[] = $row[1];
  $tgl_keluar_mingguan[] = $row[2];
  $jumlah_pasein_kamar_mingguan_kamar_melati[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_mingguan_kamar_sakura = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,kamar_inap.tgl_masuk, COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0010' AND
kamar_inap.tgl_masuk BETWEEN   '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
GROUP BY YEARWEEK(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_mingguan[] = $row[0];
  $kd_Bangsal_mingguan[] = $row[1];
  $tgl_keluar_mingguan[] = $row[2];
  $jumlah_pasein_kamar_mingguan_kamar_sakura[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_mingguan_kamar_seruni = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,kamar_inap.tgl_masuk, COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0021' AND
kamar_inap.tgl_masuk BETWEEN   '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
GROUP BY YEARWEEK(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_mingguan[] = $row[0];
  $kd_Bangsal_mingguan[] = $row[1];
  $tgl_keluar_mingguan[] = $row[2];
  $jumlah_pasein_kamar_mingguan_kamar_seruni[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_mingguan_anggrek = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,kamar_inap.tgl_masuk, COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0006' AND
kamar_inap.tgl_masuk BETWEEN   '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
GROUP BY YEARWEEK(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_mingguan[] = $row[0];
  $kd_Bangsal_mingguan[] = $row[1];
  $tgl_keluar_mingguan[] = $row[2];
  $jumlah_pasein_kamar_mingguan_kamar_anggrek[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_mingguan_flamboyan = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,kamar_inap.tgl_masuk, COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0013' AND
kamar_inap.tgl_masuk BETWEEN   '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
GROUP BY YEARWEEK(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_mingguan[] = $row[0];
  $kd_Bangsal_mingguan[] = $row[1];
  $tgl_keluar_mingguan[] = $row[2];
  $jumlah_pasein_kamar_mingguan_flamboyan[] = $row[3];
}
?>
<!-- /qwery ranap Mingguan -->

<!-- qwery ranap tahunan -->
<?php
$jumlah_pasein_kamar_bulanan_anyelir = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,DATE_FORMAT(kamar_inap.tgl_masuk,'%M %Y'), COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0008' AND
kamar_inap.tgl_masuk BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
GROUP BY MONTH(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_bulanan[] = $row[0];
  $kd_Bangsal_bulanan[] = $row[1];
  $tgl_keluar_bulanan[] = $row[2];
  $jumlah_pasein_kamar_bulanan_anyelir[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_bulanan_adelia = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,DATE_FORMAT(kamar_inap.tgl_masuk,'%M %Y'), COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0005' AND
kamar_inap.tgl_masuk BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
GROUP BY MONTH(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_bulanan[] = $row[0];
  $kd_Bangsal_bulanan[] = $row[1];
  $tgl_keluar_bulanan[] = $row[2];
  $jumlah_pasein_kamar_bulanan_adelia[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_bulanan_asoka = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,DATE_FORMAT(kamar_inap.tgl_masuk,'%M %Y'), COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0032' AND
kamar_inap.tgl_masuk BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
GROUP BY MONTH(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_bulanan[] = $row[0];
  $kd_Bangsal_bulanan[] = $row[1];
  $tgl_keluar_bulanan[] = $row[2];
  $jumlah_pasein_kamar_bulanan_asoka[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_bulanan_aster = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,DATE_FORMAT(kamar_inap.tgl_masuk,'%M %Y'), COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0007' AND
kamar_inap.tgl_masuk BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
GROUP BY MONTH(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_bulanan[] = $row[0];
  $kd_Bangsal_bulanan[] = $row[1];
  $tgl_keluar_bulanan[] = $row[2];
  $jumlah_pasein_kamar_bulanan_aster[] = $row[3];
}
?>


<?php
$jumlah_pasein_kamar_bulanan_bayi = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,DATE_FORMAT(kamar_inap.tgl_masuk,'%M %Y'), COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0020' AND
kamar_inap.tgl_masuk BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
GROUP BY MONTH(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_bulanan[] = $row[0];
  $kd_Bangsal_bulanan[] = $row[1];
  $tgl_keluar_bulanan[] = $row[2];
  $jumlah_pasein_kamar_bulanan_bayi[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_bulanan_cempaka = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,DATE_FORMAT(kamar_inap.tgl_masuk,'%M %Y'), COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0019' AND
kamar_inap.tgl_masuk BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
GROUP BY MONTH(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_bulanan[] = $row[0];
  $kd_Bangsal_bulanan[] = $row[1];
  $tgl_keluar_bulanan[] = $row[2];
  $jumlah_pasein_kamar_bulanan_cempaka[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_bulanan_dahlia = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,DATE_FORMAT(kamar_inap.tgl_masuk,'%M %Y'), COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0017' AND
kamar_inap.tgl_masuk BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
GROUP BY MONTH(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_bulanan[] = $row[0];
  $kd_Bangsal_bulanan[] = $row[1];
  $tgl_keluar_bulanan[] = $row[2];
  $jumlah_pasein_kamar_bulanan_dahlia[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_bulanan_bedahkl1 = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,DATE_FORMAT(kamar_inap.tgl_masuk,'%M %Y'), COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0012' AND
kamar_inap.tgl_masuk BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
GROUP BY MONTH(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_bulanan[] = $row[0];
  $kd_Bangsal_bulanan[] = $row[1];
  $tgl_keluar_bulanan[] = $row[2];
  $jumlah_pasein_kamar_bulanan_bedahkl1[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_bulanan_hcu = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,DATE_FORMAT(kamar_inap.tgl_masuk,'%M %Y'), COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0020' AND
kamar_inap.tgl_masuk BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
GROUP BY MONTH(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_bulanan[] = $row[0];
  $kd_Bangsal_bulanan[] = $row[1];
  $tgl_keluar_bulanan[] = $row[2];
  $jumlah_pasein_kamar_bulanan_hcu[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_bulanan_kamar_icu = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,DATE_FORMAT(kamar_inap.tgl_masuk,'%M %Y'), COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0009' AND
kamar_inap.tgl_masuk BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
GROUP BY MONTH(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_bulanan[] = $row[0];
  $kd_Bangsal_bulanan[] = $row[1];
  $tgl_keluar_bulanan[] = $row[2];
  $jumlah_pasein_kamar_bulanan_kamar_icu[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_bulanan_kamar_interna = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,DATE_FORMAT(kamar_inap.tgl_masuk,'%M %Y'), COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0085' AND
kamar_inap.tgl_masuk BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
GROUP BY MONTH(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_bulanan[] = $row[0];
  $kd_Bangsal_bulanan[] = $row[1];
  $tgl_keluar_bulanan[] = $row[2];
  $jumlah_pasein_kamar_bulanan_kamar_interna[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_bulanan_kamar_bersalin = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,DATE_FORMAT(kamar_inap.tgl_masuk,'%M %Y'), COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0026' AND
kamar_inap.tgl_masuk BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
GROUP BY MONTH(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_bulanan[] = $row[0];
  $kd_Bangsal_bulanan[] = $row[1];
  $tgl_keluar_bulanan[] = $row[2];
  $jumlah_pasein_kamar_bulanan_kamar_bersalin[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_bulanan_kamar_mawar = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,DATE_FORMAT(kamar_inap.tgl_masuk,'%M %Y'), COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0004' AND
kamar_inap.tgl_masuk BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
GROUP BY MONTH(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_bulanan[] = $row[0];
  $kd_Bangsal_bulanan[] = $row[1];
  $tgl_keluar_bulanan[] = $row[2];
  $jumlah_pasein_kamar_bulanan_kamar_mawar[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_bulanan_kamar_melati = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,DATE_FORMAT(kamar_inap.tgl_masuk,'%M %Y'), COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0015' AND
kamar_inap.tgl_masuk BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
GROUP BY MONTH(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_bulanan[] = $row[0];
  $kd_Bangsal_bulanan[] = $row[1];
  $tgl_keluar_bulanan[] = $row[2];
  $jumlah_pasein_kamar_bulanan_kamar_melati[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_bulanan_kamar_sakura = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,DATE_FORMAT(kamar_inap.tgl_masuk,'%M %Y'), COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0010' AND
kamar_inap.tgl_masuk BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
GROUP BY MONTH(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_bulanan[] = $row[0];
  $kd_Bangsal_bulanan[] = $row[1];
  $tgl_keluar_bulanan[] = $row[2];
  $jumlah_pasein_kamar_bulanan_kamar_sakura[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_bulanan_kamar_seruni = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,DATE_FORMAT(kamar_inap.tgl_masuk,'%M %Y'), COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0021' AND
kamar_inap.tgl_masuk BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
GROUP BY MONTH(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_bulanan[] = $row[0];
  $kd_Bangsal_bulanan[] = $row[1];
  $tgl_keluar_bulanan[] = $row[2];
  $jumlah_pasein_kamar_bulanan_kamar_seruni[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_bulanan_anggrek = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,DATE_FORMAT(kamar_inap.tgl_masuk,'%M %Y'), COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0006' AND
kamar_inap.tgl_masuk BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
GROUP BY MONTH(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_bulanan[] = $row[0];
  $kd_Bangsal_bulanan[] = $row[1];
  $tgl_keluar_bulanan[] = $row[2];
  $jumlah_pasein_kamar_bulanan_kamar_anggrek[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_bulanan_flamboyan = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,DATE_FORMAT(kamar_inap.tgl_masuk,'%M %Y'), COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0013' AND
kamar_inap.tgl_masuk BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
GROUP BY MONTH(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_bulanan[] = $row[0];
  $kd_Bangsal_bulanan[] = $row[1];
  $tgl_keluar_bulanan[] = $row[2];
  $jumlah_pasein_kamar_bulanan_flamboyan[] = $row[3];
}
?>
<!-- /qwery ranap Bulanan -->

<!-- qwery ranap tahun -->
<?php
$jumlah_pasein_kamar_tahun_anyelir = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,DATE_FORMAT(kamar_inap.tgl_masuk, '%Y'), COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0008' AND
kamar_inap.tgl_masuk BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'  
  GROUP BY YEAR(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_tahun[] = $row[0];
  $kd_Bangsal_tahun[] = $row[1];
  $tgl_keluar_tahun[] = $row[2];
  $jumlah_pasein_kamar_tahun_anyelir[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_tahun_adelia = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,DATE_FORMAT(kamar_inap.tgl_masuk, '%Y'), COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0005' AND
kamar_inap.tgl_masuk BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'  
  GROUP BY YEAR(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_tshun[] = $row[0];
  $kd_Bangsal_tshun[] = $row[1];
  $tgl_keluar_tshun[] = $row[2];
  $jumlah_pasein_kamar_tahun_adelia[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_tahun_asoka = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,DATE_FORMAT(kamar_inap.tgl_masuk,'%Y'), COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0032' AND
kamar_inap.tgl_masuk BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'  
GROUP BY YEAR(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_tshun[] = $row[0];
  $kd_Bangsal_tshun[] = $row[1];
  $tgl_keluar_tshun[] = $row[2];
  $jumlah_pasein_kamar_tahun_asoka[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_tahun_aster = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,DATE_FORMAT(kamar_inap.tgl_masuk,'%Y'), COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0007' AND
kamar_inap.tgl_masuk BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'  
GROUP BY YEAR(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_tahun[] = $row[0];
  $kd_Bangsal_tahun[] = $row[1];
  $tgl_keluar_tahun[] = $row[2];
  $jumlah_pasein_kamar_tahun_aster[] = $row[3];
}
?>


<?php
$jumlah_pasein_kamar_tahun_bayi = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,DATE_FORMAT(kamar_inap.tgl_masuk,'%Y'), COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0020' AND
kamar_inap.tgl_masuk BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'  
GROUP BY YEAR(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_tahun[] = $row[0];
  $kd_Bangsal_tahun[] = $row[1];
  $tgl_keluar_tahun[] = $row[2];
  $jumlah_pasein_kamar_tahun_bayi[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_tahun_cempaka = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,DATE_FORMAT(kamar_inap.tgl_masuk,'%Y'), COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0019' AND
kamar_inap.tgl_masuk BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'  
GROUP BY YEAR(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_tahun[] = $row[0];
  $kd_Bangsal_tahun[] = $row[1];
  $tgl_keluar_tahun[] = $row[2];
  $jumlah_pasein_kamar_tahun_cempaka[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_tahun_dahlia = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,DATE_FORMAT(kamar_inap.tgl_masuk,'%Y'), COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0017' AND
kamar_inap.tgl_masuk BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'  
GROUP BY YEAR(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_tahun[] = $row[0];
  $kd_Bangsal_tahun[] = $row[1];
  $tgl_keluar_tahun[] = $row[2];
  $jumlah_pasein_kamar_tahun_dahlia[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_tahun_bedahkl1 = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,DATE_FORMAT(kamar_inap.tgl_masuk,'%Y'), COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0012' AND
kamar_inap.tgl_masuk BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'  
GROUP BY YEAR(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_tahun[] = $row[0];
  $kd_Bangsal_tahun[] = $row[1];
  $tgl_keluar_tahun[] = $row[2];
  $jumlah_pasein_kamar_tahun_bedahkl1[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_tahun_hcu = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,DATE_FORMAT(kamar_inap.tgl_masuk,'%Y'), COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0009' AND
kamar_inap.tgl_masuk BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'  
GROUP BY YEAR(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_tahun[] = $row[0];
  $kd_Bangsal_tahun[] = $row[1];
  $tgl_keluar_tahun[] = $row[2];
  $jumlah_pasein_kamar_tahun_hcu[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_tahun_kamar_icu = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,DATE_FORMAT(kamar_inap.tgl_masuk,'%Y'), COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0014' AND
kamar_inap.tgl_masuk BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'  
GROUP BY YEAR(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_tahun[] = $row[0];
  $kd_Bangsal_tahun[] = $row[1];
  $tgl_keluar_tahun[] = $row[2];
  $jumlah_pasein_kamar_tahun_kamar_icu[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_tahun_kamar_interna = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,DATE_FORMAT(kamar_inap.tgl_masuk,'%Y'), COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0085' AND
kamar_inap.tgl_masuk BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'  
GROUP BY YEAR(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_tahun[] = $row[0];
  $kd_Bangsal_tahun[] = $row[1];
  $tgl_keluar_tahun[] = $row[2];
  $jumlah_pasein_kamar_tahun_kamar_interna[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_tahun_kamar_bersalin = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,DATE_FORMAT(kamar_inap.tgl_masuk,'%Y'), COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0026' AND
kamar_inap.tgl_masuk BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'  
GROUP BY YEAR(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_tahun[] = $row[0];
  $kd_Bangsal_tahun[] = $row[1];
  $tgl_keluar_tahun[] = $row[2];
  $jumlah_pasein_kamar_tahun_kamar_bersalin[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_tahun_kamar_mawar = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,DATE_FORMAT(kamar_inap.tgl_masuk,'%Y'), COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0004' AND
kamar_inap.tgl_masuk BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'  
GROUP BY YEAR(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_tahun[] = $row[0];
  $kd_Bangsal_tahun[] = $row[1];
  $tgl_keluar_tahun[] = $row[2];
  $jumlah_pasein_kamar_tahun_kamar_mawar[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_tahun_kamar_melati = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,DATE_FORMAT(kamar_inap.tgl_masuk,'%Y'), COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0015' AND
kamar_inap.tgl_masuk BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'  
GROUP BY YEAR(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_tahun[] = $row[0];
  $kd_Bangsal_tahun[] = $row[1];
  $tgl_keluar_tahun[] = $row[2];
  $jumlah_pasein_kamar_tahun_kamar_melati[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_tahun_kamar_sakura = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,DATE_FORMAT(kamar_inap.tgl_masuk,'%Y'), COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0010' AND
kamar_inap.tgl_masuk BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'  
GROUP BY YEAR(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_tahun[] = $row[0];
  $kd_Bangsal_tahun[] = $row[1];
  $tgl_keluar_tahun[] = $row[2];
  $jumlah_pasein_kamar_tahun_kamar_sakura[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_tahun_kamar_seruni = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,DATE_FORMAT(kamar_inap.tgl_masuk,'%Y'), COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0021' AND
kamar_inap.tgl_masuk BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'  
GROUP BY YEAR(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_tahun[] = $row[0];
  $kd_Bangsal_tahun[] = $row[1];
  $tgl_keluar_tahun[] = $row[2];
  $jumlah_pasein_kamar_tahun_kamar_seruni[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_tahun_anggrek = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,DATE_FORMAT(kamar_inap.tgl_masuk,'%Y'), COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0006' AND
kamar_inap.tgl_masuk BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'  
GROUP BY YEAR(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_tahun[] = $row[0];
  $kd_Bangsal_tahun[] = $row[1];
  $tgl_keluar_tahun[] = $row[2];
  $jumlah_pasein_kamar_tahun_kamar_anggrek[] = $row[3];
}
?>

<?php
$jumlah_pasein_kamar_tahun_flamboyan = array();
$result = mysqli_query($koneksi, "SELECT kamar_inap.kd_kamar,kamar.kd_bangsal ,DATE_FORMAT(kamar_inap.tgl_masuk,'%Y'), COUNT(kamar_inap.no_rawat) AS Jumlah
FROM kamar_inap,kamar
WHERE kamar_inap.kd_kamar=kamar.kd_kamar AND kamar.kd_bangsal='B0013' AND
kamar_inap.tgl_masuk BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'  
GROUP BY YEAR(kamar_inap.tgl_masuk) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $kd_kamar_tahun[] = $row[0];
  $kd_Bangsal_tahun[] = $row[1];
  $tgl_keluar_tahun[] = $row[2];
  $jumlah_pasein_kamar_tahun_flamboyan[] = $row[3];
}
?>
<!-- /qwery ranap tahunan -->



<!-- ================================================================================= -->
<script>
  Highcharts.chart('kamar', {
    chart: {
      type: 'column'
    },
    title: {
      text: 'Ketersediaan Tempat Tidur'
    },
    subtitle: {
      text: ''
    },
    xAxis: {
      categories: [

        '<?php echo  implode("','", $kd_kamar_anyelir) ?>',
        '<?php echo  implode("','", $kd_kamar_asoka) ?>',
        '<?php echo  implode("','", $kd_kamar_aster) ?>',
        '<?php echo  implode("','", $kd_kamar_bedahkls1) ?>',
        '<?php echo  implode("','", $kd_kamar_bayi) ?>',
        '<?php echo  implode("','", $kd_kamar_cempaka) ?>',
        '<?php echo  implode("','", $kd_kamar_dahlia) ?>',
        '<?php echo  implode("','", $kd_kamar_hcu) ?>',
        '<?php echo  implode("','", $kd_kamar_icu) ?>',
        '<?php echo  implode("','", $kd_kamar_interna) ?>',
        '<?php echo  implode("','", $kd_kamar_bersalin) ?>',
        '<?php echo  implode("','", $kd_kamar_mawar) ?>',
        '<?php echo  implode("','", $kd_kamar_melati) ?>',
        '<?php echo  implode("','", $kd_kamar_sakura) ?>',
        '<?php echo  implode("','", $kd_kamar_seruni) ?>',
        '<?php echo  implode("','", $kd_kamar_adelia) ?>',
        '<?php echo  implode("','", $kd_kamar_anggrek) ?>',
        '<?php echo  implode("','", $kd_kamar_flamboyan) ?>'

      ],
      crosshair: true
    },
    yAxis: {
      title: {
        useHTML: true,
        text: 'Jumlah'
      }
    },
    tooltip: {
      headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
      pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
        '<td style="padding:0"><b>{point.y:.1f}</b></td></tr>',
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
      name: 'TEMPAT TIDUR',
      data: [<?php echo  implode("','", $jumlah_kamar_anyelir) ?>,
        <?php echo  implode("','", $jumlah_kamar_asoka) ?>,
        <?php echo  implode("','", $jumlah_kamar_aster) ?>,
        <?php echo  implode("','", $jumlah_kamar_bedahkls1) ?>,
        <?php echo  implode("','", $jumlah_kamar_bayi) ?>,
        <?php echo  implode("','", $jumlah_kamar_cempaka) ?>,
        <?php echo  implode("','", $jumlah_kamar_dahlia) ?>,
        <?php echo  implode("','", $jumlah_kamar_hcu) ?>,
        <?php echo  implode("','", $jumlah_kamar_icu) ?>,
        <?php echo  implode("','", $jumlah_kamar_interna) ?>,
        <?php echo  implode("','", $jumlah_kamar_bersalin) ?>,
        <?php echo  implode("','", $jumlah_kamar_mawar) ?>,
        <?php echo  implode("','", $jumlah_kamar_melati) ?>,
        <?php echo  implode("','", $jumlah_kamar_sakura) ?>,
        <?php echo  implode("','", $jumlah_kamar_seruni) ?>,
        <?php echo  implode("','", $jumlah_kamar_adelia) ?>,
        <?php echo  implode("','", $jumlah_kamar_anggrek) ?>,
        <?php echo  implode("','", $jumlah_kamar_flamboyan) ?>
      ]

    }, {
      name: 'ISI',
      data: [<?php echo  implode("','", $jumlah_kamar_anyelir_isi) ?>,
        <?php echo  implode("','", $jumlah_kamar_asoka_isi) ?>,
        <?php echo  implode("','", $jumlah_kamar_aster_isi) ?>,
        <?php echo  implode("','", $jumlah_kamar_bedahkelas1_isi) ?>,
        <?php echo  implode("','", $jumlah_kamar_bayi_isi) ?>,
        <?php echo  implode("','", $jumlah_kamar_cempaka_isi) ?>,
        <?php echo  implode("','", $jumlah_kamar_dahlia_isi) ?>,
        <?php echo  implode("','", $jumlah_kamar_hcu_isi) ?>,
        <?php echo  implode("','", $jumlah_kamar_icu_isi) ?>,
        <?php echo  implode("','", $jumlah_kamar_interna_isi) ?>,
        <?php echo  implode("','", $jumlah_kamar_bersalin_isi) ?>,
        <?php echo  implode("','", $jumlah_kamar_mawar_isi) ?>,
        <?php echo  implode("','", $jumlah_kamar_melati_isi) ?>,
        <?php echo  implode("','", $jumlah_kamar_sakura_isi) ?>,
        <?php echo  implode("','", $jumlah_kamar_seruni_isi) ?>,
        <?php echo  implode("','", $jumlah_kamar_adelia_isi) ?>,
        <?php echo  implode("','", $jumlah_kamar_anggrek_isi) ?>,
        <?php echo  implode("','", $jumlah_kamar_flamboyan_isi) ?>
      ]

    }, {
      name: 'KOSONG',
      data: [<?php echo  implode("','", $jumlah_kamar_anyelir_kosong) ?>,
        <?php echo  implode("','", $jumlah_kamar_asoka_kosong) ?>,
        <?php echo  implode("','", $jumlah_kamar_aster_kosong) ?>,
        <?php echo  implode("','", $jumlah_kamar_bedahkls1_kosong) ?>,
        <?php echo  implode("','", $jumlah_kamar_bayi_kosong) ?>,
        <?php echo  implode("','", $jumlah_kamar_cempaka_kosong) ?>,
        <?php echo  implode("','", $jumlah_kamar_dahlia_kosong) ?>,
        <?php echo  implode("','", $jumlah_kamar_hcu_kosong) ?>,
        <?php echo  implode("','", $jumlah_kamar_icu_kosong) ?>,
        <?php echo  implode("','", $jumlah_kamar_interna_kosong) ?>,
        <?php echo  implode("','", $jumlah_kamar_bersalin_kosong) ?>,
        <?php echo  implode("','", $jumlah_kamar_mawar_kosong) ?>,
        <?php echo  implode("','", $jumlah_kamar_melati_kosong) ?>,
        <?php echo  implode("','", $jumlah_kamar_sakura_kosong) ?>,
        <?php echo  implode("','", $jumlah_kamar_seruni_kosong) ?>,
        <?php echo  implode("','", $jumlah_kamar_adelia_kosong) ?>,
        <?php echo  implode("','", $jumlah_kamar_anggrek_kosong) ?>,
        <?php echo  implode("','", $jumlah_kamar_flamboyan_kosong) ?>
      ]

    }, {
      name: 'DIBERSIHKAN',
      data: [<?php echo  implode("','", $jumlah_kamar_anyelir_dibersihkan) ?>,
        <?php echo  implode("','", $jumlah_kamar_asoka_dibersihkan) ?>,
        <?php echo  implode("','", $jumlah_kamar_aster_dibersihkan) ?>,
        <?php echo  implode("','", $jumlah_kamar_bedahkls1_dibersihkan) ?>,
        <?php echo  implode("','", $jumlah_kamar_bayi_dibersihkan) ?>,
        <?php echo  implode("','", $jumlah_kamar_cempaka_dibersihkan) ?>,
        <?php echo  implode("','", $jumlah_kamar_dahlia_dibersihkan) ?>,
        <?php echo  implode("','", $jumlah_kamar_hcu_dibersihkan) ?>,
        <?php echo  implode("','", $jumlah_kamar_icu_dibersihkan) ?>,
        <?php echo  implode("','", $jumlah_kamar_interna_dibersihkan) ?>,
        <?php echo  implode("','", $jumlah_kamar_bersalin_dibersihkan) ?>,
        <?php echo  implode("','", $jumlah_kamar_mawar_dibersihkan) ?>,
        <?php echo  implode("','", $jumlah_kamar_melati_dibersihkan) ?>,
        <?php echo  implode("','", $jumlah_kamar_sakura_dibersihkan) ?>,
        <?php echo  implode("','", $jumlah_kamar_seruni_dibersihkan) ?>,
        <?php echo  implode("','", $jumlah_kamar_adelia_dibersihkan) ?>,
        <?php echo  implode("','", $jumlah_kamar_anggrek_dibersihkan) ?>,
        <?php echo  implode("','", $jumlah_kamar_flamboyan_dibersihkan) ?>
      ]

    }]
  });
</script>

<script>
  const chart = Highcharts.chart('kamar-pasien', {
    title: {
      text: 'Pasien Rawat Inap'
    },
    subtitle: {
      text: ''
    },

    rangeSelector: {
      selected: 2
    },

    xAxis: [{
      type: 'datetime'
    }],
    xAxis: {
      categories: [

        '<?php echo  implode("','", $kd_kamar_anyelir) ?>',
        '<?php echo  implode("','", $kd_kamar_asoka) ?>',
        '<?php echo  implode("','", $kd_kamar_aster) ?>',
        '<?php echo  implode("','", $kd_kamar_bedahkls1) ?>',
        '<?php echo  implode("','", $kd_kamar_bayi) ?>',
        '<?php echo  implode("','", $kd_kamar_cempaka) ?>',
        '<?php echo  implode("','", $kd_kamar_dahlia) ?>',
        '<?php echo  implode("','", $kd_kamar_hcu) ?>',
        '<?php echo  implode("','", $kd_kamar_icu) ?>',
        '<?php echo  implode("','", $kd_kamar_interna) ?>',
        '<?php echo  implode("','", $kd_kamar_bersalin) ?>',
        '<?php echo  implode("','", $kd_kamar_mawar) ?>',
        '<?php echo  implode("','", $kd_kamar_melati) ?>',
        '<?php echo  implode("','", $kd_kamar_sakura) ?>',
        '<?php echo  implode("','", $kd_kamar_seruni) ?>',
        '<?php echo  implode("','", $kd_kamar_adelia) ?>',
        '<?php echo  implode("','", $kd_kamar_anggrek) ?>',
        '<?php echo  implode("','", $kd_kamar_flamboyan) ?>'

      ]
    },
    series: [{
      type: 'column',
      name: 'Jumlah',
      colorByPoint: true,
      data: [<?php echo  implode("','", $jumlah_kamar_anyelir_isi) ?>,
        <?php echo  implode("','", $jumlah_kamar_asoka_isi) ?>,
        <?php echo  implode("','", $jumlah_kamar_aster_isi) ?>,
        <?php echo  implode("','", $jumlah_kamar_bedahkelas1_isi) ?>,
        <?php echo  implode("','", $jumlah_kamar_bayi_isi) ?>,
        <?php echo  implode("','", $jumlah_kamar_cempaka_isi) ?>,
        <?php echo  implode("','", $jumlah_kamar_dahlia_isi) ?>,
        <?php echo  implode("','", $jumlah_kamar_hcu_isi) ?>,
        <?php echo  implode("','", $jumlah_kamar_icu_isi) ?>,
        <?php echo  implode("','", $jumlah_kamar_interna_isi) ?>,
        <?php echo  implode("','", $jumlah_kamar_bersalin_isi) ?>,
        <?php echo  implode("','", $jumlah_kamar_mawar_isi) ?>,
        <?php echo  implode("','", $jumlah_kamar_melati_isi) ?>,
        <?php echo  implode("','", $jumlah_kamar_sakura_isi) ?>,
        <?php echo  implode("','", $jumlah_kamar_seruni_isi) ?>,
        <?php echo  implode("','", $jumlah_kamar_adelia_isi) ?>,
        <?php echo  implode("','", $jumlah_kamar_anggrek_isi) ?>,
        <?php echo  implode("','", $jumlah_kamar_flamboyan_isi) ?>
      ],
      showInLegend: false
    }]
  });
</script>




<script>
  // Data retrieved from https://netmarketshare.com/
  // Build the chart
  Highcharts.chart('chart-status', {
    chart: {
      plotBackgroundColor: null,
      plotBorderWidth: null,
      plotShadow: false,
      type: 'pie'
    },
    title: {
      text: 'Chart Rawat Inap'
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
      name: 'Brands',
      colorByPoint: true,
      data: [{
        name: 'Dirawat',
        y: <?php echo  implode("','", $jumlah_ranap) ?>,
        sliced: true,
        selected: true
      }, {
        name: 'Sudah',
        y: <?php echo  implode("','", $jumlah_ranap_sudah) ?>
      }]
    }]
  });
</script>

<script>
  Highcharts.chart('chart-cara-bayar', {
    chart: {
      plotBackgroundColor: null,
      plotBorderWidth: null,
      plotShadow: false,
      type: 'pie'
    },
    title: {
      text: 'Jenis Bayar'
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
        name: 'BPIS',
        y: <?php echo  implode("','", $jumlah_bayar_BPJS_ranap) ?>,
        sliced: true,
        selected: true
      }, {
        name: 'BPJS KETENAJA KERJAAN',
        y: <?php echo  implode("','", $jumlah_bayar_BPJS_ketenagakerjaan) ?>
      }, {
        name: 'MANDIRI INHEALTH',
        y: <?php echo  implode("','", $jumlah_bayar_mandiri) ?>
      }, {
        name: 'PLM',
        y: <?php echo  implode("','", $jumlah_bayar_pln) ?>
      }, {
        name: 'TELKOM',
        y: <?php echo  implode("','", $jumlah_bayar_telkom) ?>
      }, {
        name: 'UMUM',
        y: <?php echo  implode("','", $jumlah_bayar_umum_ranap) ?>
      }, {
        name: 'Lainnya',
        y: <?php echo  implode("','", $jumlah_bayar_lain_ranap) ?>
      }]

    }]
  });
</script>

<script>
  Highcharts.chart('chart-cara-Pulang', {
    chart: {
      plotBackgroundColor: null,
      plotBorderWidth: null,
      plotShadow: false,
      type: 'pie'
    },
    title: {
      text: 'CARA PULANG'
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
      name: 'STATUS PULANG',
      colorByPoint: true,
      data: [{
        name: 'Atas Permintaan Sendiri',
        y: <?php echo  implode("','", $Jumlah_pasien_pulang_atas_permintaan_sendiri) ?>,
        sliced: true,
        selected: true
      }, {
        name: 'Persetujuan Dokter',
        y: <?php echo  implode("','", $Jumlah_pasien_pulang_atas_persetujuan_dokter) ?>
      }, {
        name: 'Sembuh',
        y: <?php echo  implode("','", $Jumlah_pasien_pulang_sembuh) ?>
      }, {
        name: 'Meninggal',
        y: <?php echo  implode("','", $Jumlah_pasien_pulang_meninggal) ?>
      }, {
        name: 'Rujuk',
        y: <?php echo  implode("','", $Jumlah_pasien_pulang_rujuk) ?>
      }]

    }]
  });
</script>

<script>
  Highcharts.chart('chart-ranap-pasien-baru', {
    chart: {
      plotBackgroundColor: null,
      plotBorderWidth: null,
      plotShadow: false,
      type: 'pie'
    },
    title: {
      text: 'Chart Jenis Pasien Baru'
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
        name: 'TNI',
        y: <?php echo  implode("','", $jumlah_gol_tni) ?>,
        sliced: true,
        selected: true
      }, {
        name: 'PNS',
        y: <?php echo  implode("','", $jumlah_gol_pns) ?>,

      }, {
        name: 'KELUARGA PNS',
        y: <?php echo  implode("','", $jumlah_gol_keluarga_pns) ?>,

      }, {
        name: 'UMUM',
        y: <?php echo  implode("','", $jumlah_pasein_umum) ?>
      }]
    }]
  });
</script>

<script>
  Highcharts.chart('chart-ranap-pasien-lama', {
    chart: {
      plotBackgroundColor: null,
      plotBorderWidth: null,
      plotShadow: false,
      type: 'pie'
    },
    title: {
      text: 'Chart Jenis Pasien Lama'
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
        name: 'TNI',
        y: <?php echo  implode("','", $jumlah_gol_tni_Lama) ?>,
      }, {
        name: 'PNS',
        y: <?php echo  implode("','", $jumlah_gol_pns_lama) ?>,

      }, {
        name: 'KELUARGA PNS',
        y: <?php echo  implode("','", $jumlah_gol_keluarga_pns_lama) ?>,

      }, {
        name: 'UMUM',
        y: <?php echo  implode("','", $jumlah_pasein_umum_lama) ?>
      }]
    }]
  });
</script>

<!-- CHART JUMLAH PASIEN RANAP -->
<script>
  Highcharts.chart("chart_ranap_mingguan", {

    chart: {
      type: 'areaspline'
    },
    title: {
      text: 'Grafik Rawat Inap Perminggu'
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
        '<?php echo  implode("','", $tgl_keluar_mingguan) ?> ',
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
          name: 'Anyelir',
          data: [
            <?php foreach ($jumlah_pasein_kamar_mingguan_anyelir as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Adelia',
          data: [
            <?php foreach ($jumlah_pasein_kamar_mingguan_adelia as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Asoka',
          data: [
            <?php foreach ($jumlah_pasein_kamar_mingguan_asoka as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Aster',
          data: [
            <?php foreach ($jumlah_pasein_kamar_mingguan_aster as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Bayi',
          data: [
            <?php foreach ($jumlah_pasein_kamar_mingguan_bayi as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Cempaka',
          data: [
            <?php foreach ($jumlah_pasein_kamar_mingguan_cempaka as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Dahlia',
          data: [
            <?php foreach ($jumlah_pasein_kamar_mingguan_dahlia as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Bedah KLS 1',
          data: [
            <?php foreach ($jumlah_pasein_kamar_mingguan_bedahkl1 as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'HCU',
          data: [
            <?php foreach ($jumlah_pasein_kamar_mingguan_hcu as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'ICU',
          data: [
            <?php foreach ($jumlah_pasein_kamar_mingguan_kamar_icu as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Kamar Interna',
          data: [
            <?php foreach ($jumlah_pasein_kamar_mingguan_kamar_interna as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Kamar Bersalin',
          data: [
            <?php foreach ($jumlah_pasein_kamar_mingguan_kamar_bersalin as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Kamar Mawar',
          data: [
            <?php foreach ($jumlah_pasein_kamar_mingguan_kamar_mawar as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Kamar melati',
          data: [
            <?php foreach ($jumlah_pasein_kamar_mingguan_kamar_melati as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Kamar Sakura',
          data: [
            <?php foreach ($jumlah_pasein_kamar_mingguan_kamar_sakura as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Kamar Seruni',
          data: [
            <?php foreach ($jumlah_pasein_kamar_mingguan_kamar_seruni as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Kamar Anggrek',
          data: [
            <?php foreach ($jumlah_pasein_kamar_mingguan_anggrek as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Kamar Flamboyan',
          data: [
            <?php foreach ($jumlah_pasein_kamar_mingguan_flamboyan as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>
    ]
  });
</script>

<script>
  Highcharts.chart("chart_ranap_bulanan", {

    chart: {
      type: 'areaspline'
    },
    title: {
      text: 'Grafik Rawat Inap Bulanan'
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
        '<?php echo  implode("','", $tgl_keluar_bulanan) ?> ',
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
          name: 'Anyelir',
          data: [
            <?php foreach ($jumlah_pasein_kamar_bulanan_anyelir as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Adelia',
          data: [
            <?php foreach ($jumlah_pasein_kamar_bulanan_adelia as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Asoka',
          data: [
            <?php foreach ($jumlah_pasein_kamar_bulanan_asoka as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Aster',
          data: [
            <?php foreach ($jumlah_pasein_kamar_bulanan_aster as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Bayi',
          data: [
            <?php foreach ($jumlah_pasein_kamar_bulanan_bayi as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Cempaka',
          data: [
            <?php foreach ($jumlah_pasein_kamar_bulanan_cempaka as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Dahlia',
          data: [
            <?php foreach ($jumlah_pasein_kamar_bulanan_dahlia as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Bedah KLS 1',
          data: [
            <?php foreach ($jumlah_pasein_kamar_bulanan_bedahkl1 as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'HCU',
          data: [
            <?php foreach ($jumlah_pasein_kamar_bulanan_hcu as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'ICU',
          data: [
            <?php foreach ($jumlah_pasein_kamar_bulanan_kamar_icu as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Kamar Interna',
          data: [
            <?php foreach ($jumlah_pasein_kamar_bulanan_kamar_interna as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Kamar Bersalin',
          data: [
            <?php foreach ($jumlah_pasein_kamar_bulanan_kamar_bersalin as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Kamar Mawar',
          data: [
            <?php foreach ($jumlah_pasein_kamar_bulanan_kamar_mawar as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Kamar melati',
          data: [
            <?php foreach ($jumlah_pasein_kamar_bulanan_kamar_melati as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Kamar Sakura',
          data: [
            <?php foreach ($jumlah_pasein_kamar_bulanan_kamar_sakura as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Kamar Seruni',
          data: [
            <?php foreach ($jumlah_pasein_kamar_bulanan_kamar_seruni as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Kamar Anggrek',
          data: [
            <?php foreach ($jumlah_pasein_kamar_bulanan_anggrek as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Kamar Flamboyan',
          data: [
            <?php foreach ($jumlah_pasein_kamar_bulanan_flamboyan as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>
    ]
  });
</script>

<script>
  Highcharts.chart("chart_ranap_tahun", {

    chart: {
      type: 'areaspline'
    },
    title: {
      text: 'Grafik Rawat Inap Tahunan'
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
        '<?php echo  implode("','", $tgl_keluar_tahun) ?> ',
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
          name: 'Anyelir',
          data: [
            <?php foreach ($jumlah_pasein_kamar_tahun_anyelir as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Adelia',
          data: [
            <?php foreach ($jumlah_pasein_kamar_tahun_adelia as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Asoka',
          data: [
            <?php foreach ($jumlah_pasein_kamar_tahun_asoka as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Aster',
          data: [
            <?php foreach ($jumlah_pasein_kamar_tahun_aster as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Bayi',
          data: [
            <?php foreach ($jumlah_pasein_kamar_tahun_bayi as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Cempaka',
          data: [
            <?php foreach ($jumlah_pasein_kamar_tahun_cempaka as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Dahlia',
          data: [
            <?php foreach ($jumlah_pasein_kamar_tahun_dahlia as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Bedah KLS 1',
          data: [
            <?php foreach ($jumlah_pasein_kamar_tahun_bedahkl1 as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'HCU',
          data: [
            <?php foreach ($jumlah_pasein_kamar_tahun_hcu as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'ICU',
          data: [
            <?php foreach ($jumlah_pasein_kamar_tahun_kamar_icu as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Kamar Interna',
          data: [
            <?php foreach ($jumlah_pasein_kamar_tahun_kamar_interna as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Kamar Bersalin',
          data: [
            <?php foreach ($jumlah_pasein_kamar_tahun_kamar_bersalin as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Kamar Mawar',
          data: [
            <?php foreach ($jumlah_pasein_kamar_tahun_kamar_mawar as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Kamar melati',
          data: [
            <?php foreach ($jumlah_pasein_kamar_tahun_kamar_melati as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Kamar Sakura',
          data: [
            <?php foreach ($jumlah_pasein_kamar_tahun_kamar_sakura as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Kamar Seruni',
          data: [
            <?php foreach ($jumlah_pasein_kamar_tahun_kamar_seruni as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Kamar Anggrek',
          data: [
            <?php foreach ($jumlah_pasein_kamar_tahun_anggrek as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Kamar Flamboyan',
          data: [
            <?php foreach ($jumlah_pasein_kamar_tahun_flamboyan as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>
    ]
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