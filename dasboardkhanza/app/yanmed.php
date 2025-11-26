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
          <h5 class="mb-2">Yanmed</h5>
          <div class="alert alert-info alert-dismissible">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <h5><i class="icon fas fa-info"></i>Informasi!</h5>
            Maaf masih dalam Pengembangan
          </div>
          <div class="row">
            <div class="col-md-3 col-sm-2 col-12">
              <div class="info-box">
                <span class="info-box-icon bg-info"><i class="fa fa-thermometer-quarter"></i></span>
                <div class="info-box-content">
                  <span class="info-box-text">Kelahiran Bayi</span>
                  <?php
                  $data_pasien = mysqli_query($koneksi, "SELECT * FROM pasien_bayi;");
                  $jumlah = mysqli_num_rows($data_pasien);
                  ?>
                  <span class="info-box-number"><?php echo "$jumlah"; ?></span>
                  <h5>
                    <small>
                      <?php
                      $data_pasien = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar,reg_periksa.kd_poli
                                                  FROM pasien 
                                                  INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                                                  WHERE pasien.jk='L' and  reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='IRM'
                                         AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($data_pasien);
                      ?>
                      <span class="count_top"><i class="fa fa-male"></i> <?php echo "$jumlah"; ?></span>
                      <?php
                      $data_pasien = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar,reg_periksa.kd_poli
                      FROM pasien 
                      INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                      WHERE pasien.jk='P' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='IRM'
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
                <span class="info-box-icon bg-danger"><i class="fa fa-desktop"></i></span>
                <div class="info-box-content">
                  <span class="info-box-text">Kematian

                  </span>
                  <?php
                  $data_pasien = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar,reg_periksa.kd_poli
                  FROM pasien 
                  INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                  WHERE  reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='HDL'
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
                                                  WHERE pasien.jk='L' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='HDL'
                                         AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($data_pasien);
                      ?>
                      <span class="count_top"><i class="fa fa-male"></i> <?php echo "$jumlah"; ?></span>
                      <?php
                      $data_pasien = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar,reg_periksa.kd_poli
                      FROM pasien 
                      INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                      WHERE pasien.jk='P' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='HDL'
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
                <span class="info-box-icon bg-dark"><i class="fa fa-desktop"></i></span>
                <div class="info-box-content">
                  <span class="info-box-text">MCU</span>
                  <?php
                  $data_pasien = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar,reg_periksa.kd_poli
                  FROM pasien 
                  INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                  WHERE reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='P20'
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
                      WHERE pasien.jk='L' and reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='P20'
                      AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($data_pasien);
                      ?>
                      <span class="count_top"><i class="fa fa-male"></i> <?php echo "$jumlah"; ?></span>
                      <?php
                      $data_pasien = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar,reg_periksa.kd_poli
                      FROM pasien 
                      INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                      WHERE pasien.jk='P'  AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='P20'
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
                <span class="info-box-icon bg-warning"><i class="fa fa-desktop"></i></span>
                <div class="info-box-content">
                  <span class="info-box-text">Catlab</span>
                  <?php
                  $data_pasien = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar,reg_periksa.kd_poli
                  FROM pasien 
                  INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                  WHERE reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='U0042'
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
                      WHERE pasien.jk='L'  AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='U0042'
                      AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($data_pasien);
                      ?>
                      <span class="count_top"><i class="fa fa-male" data-toggle="tooltip" data-placement="top" title="Laki-Laki"></i> <?php echo "$jumlah"; ?></span>
                      <?php
                      $data_pasien = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar,reg_periksa.kd_poli
                      FROM pasien 
                      INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                      WHERE pasien.jk='P' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='U0042'
                     AND reg_periksa.tgl_registrasi=CURDATE()");
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
            <div class="col-md-3 col-sm-2 col-12">
              <div class="info-box">
                <span class="info-box-icon bg-success"><i class="fa fa-desktop"></i></span>

                <div class="info-box-content">
                  <span class="info-box-text">Radioterapi</span>
                  <?php
                  $kamar = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar,reg_periksa.kd_poli
                  FROM pasien 
                  INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                  WHERE pasien.jk='L'  AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='RDT'
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
                      WHERE  pasien.jk='p' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='RDT'
                      AND reg_periksa.tgl_registrasi=CURDATE()");
                      $jumlah = mysqli_num_rows($kamar);
                      ?>
                      <span class="count_top"><i class="fa fa-male" data-toggle="tooltip" data-placement="top" title="Laki-Laki!"></i><?php echo "$jumlah"; ?></span>
                      <?php
                      $kamar = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar,reg_periksa.kd_poli
                      FROM pasien 
                      INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                      WHERE pasien.jk='L' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='RDT'
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

          <!-- // kolom baris kedua :) -->


          <div class="row">
            <div class="mx-auto" style="width: 200px;">
              &nbsp;
            </div>
            <div class="col-md-12">
            <div class="card">
              <div class="card-header">
                <h3 class="card-title">Laporan Kelahiran Anak</h3>
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <table id="laporan_kelahiran" class="table table-bordered table-striped">
                  <thead>
                    <tr>
                      <th>NO</th>
                      <th>NAMA</th>
                      <th>RM</th>
                      <th>TANGGAL LAHIR</th>
                      <th>JAM LAHIR</th>
                      <th>PROSES LAHIR</th>
                      <th>UMUR</th>
                    </tr>
                  </thead>
                  <tbody>
                    <?php
                    $no = 0;
                    $result = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien_bayi.no_rkm_medis,pasien.tgl_lahir,pasien_bayi.jam_lahir,pasien_bayi.proses_lahir,pasien.umur
                    FROM pasien_bayi
                    INNER JOIN pasien ON pasien_bayi.no_rkm_medis=pasien.no_rkm_medis
                    ");
                    //urutab tampilkan hasil query dari database
                    while ($row = mysqli_fetch_array($result)) {
                      $no++;
                    ?>
                      <tr>
                        <td><?php echo $no; ?></td>
                        <td><?php echo $row['nm_pasien']; ?></td>
                        <td><?php echo $row['no_rkm_medis']; ?></td>
                        <td><?php echo $row['tgl_lahir']; ?></td>
                        <td><?php echo $row['jam_lahir']; ?></td>
                        <td><?php echo $row['proses_lahir']; ?></td>
                        <td><?php echo $row['umur']; ?></td>
                      </tr>
                    <?php } ?>
                  </tbody>
                  <tfoot>
                    <tr>
                    <th>NO</th>
                      <th>NAMA</th>
                      <th>RM</th>
                      <th>TANGGAL LAHIR</th>
                      <th>JAM LAHIR</th>
                      <th>PROSES LAHIR</th>
                      <th>UMUR</th>
                    </tr>
                  </tfoot>
                </table>
              </div>
              <!-- /.card-body -->
            </div>
            
            `<div class="row">
              <div class="mx-auto" style="width: 200px;">
                &nbsp;
              </div>
                <div class="col-md-12">
                  <div class="card">
                    <div class="card-header">
                      <h3 class="card-title">Laporan Kematian & Penyebab</h3>
                    </div>
                    <!-- /.card-header -->
                    <div class="card-body">
                      <table id="laporan_kematian" class="table table-bordered table-striped">
                        <thead>
                          <tr>
                            <th>NO</th>
                            <th>TANGGAL</th>
                            <th>JAM</th>
                            <th>RM</th>
                            <th>NAMA PASIEN</th>
                            <th>JK</th>
                            <th>KETERANGAN</th>
                            <th>TEMPAT MENINGGAL</th>
                            <th>PENYEBAB</th>
                          </tr>
                        </thead>
                        <tbody>
                          <?php
                          $no = 0;
                          $resultx = mysqli_query($koneksi, "SELECT pasien_mati.tanggal,pasien_mati.jam,pasien_mati.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien_mati.keterangan,pasien_mati.temp_meninggal,pasien_mati.icd1
                          FROM pasien_mati 
                          INNER JOIN pasien ON pasien_mati.no_rkm_medis=pasien.no_rkm_medis
                          ");
                          //urutab tampilkan hasil query dari database
                          while ($row = mysqli_fetch_array($resultx)) {
                            $no++;
                          ?>
                            <tr>
                              <td><?php echo $no; ?></td>
                              <td><?php echo $row['tanggal']; ?></td>
                              <td><?php echo $row['jam']; ?></td>
                              <td><?php echo $row['no_rkm_medis']; ?></td>
                              <td><?php echo $row['nm_pasien']; ?></td>
                              <td><?php echo $row['jk']; ?></td>
                              <td><?php echo $row['keterangan']; ?></td>
                              <td><?php echo $row['temp_meninggal']; ?></td>
                              <td><?php echo $row['icd1']; ?></td>
                            </tr>
                          <?php } ?>
                        </tbody>
                        <tfoot>
                          <tr>
                            <th>NO</th>
                            <th>TANGGAL</th>
                            <th>JAM</th>
                            <th>RM</th>
                            <th>NAMA PASIEN</th>
                            <th>JK</th>
                            <th>KETERANGAN</th>
                            <th>TEMPAT MENINGGAL</th>
                            <th>PENYEBAB</th>
                          </tr>
                        </tfoot>
                      </table>
                    </div>
                    <!-- /.card-body -->
                  </div>
                </div>
          </div>
            <!-- <div class="col-md-12 ">
              <figure class="highcharts-figure">
                <div id="chart_bulanan"></div>
              </figure>
            </div>
            <div class="col-md-12 ">
              <figure class="highcharts-figure">
                <div id="chart_tahunan"></div>
              </figure>
            </div> -->


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
  $jumlah_pasein_rehabmedik_mingguan = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_poli,reg_periksa.status_lanjut,reg_periksa.stts,reg_periksa.tgl_registrasi, 
  COUNT(DISTINCT reg_periksa.no_rkm_medis) AS Jumlah
  FROM reg_periksa
  WHERE reg_periksa.tgl_registrasi BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'  AND reg_periksa.kd_poli='IRM' 
  GROUP BY YEARWEEK(reg_periksa.tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tgl_periksa_rehabmedik[] = $row[3];
    $jumlah_pasein_rehabmedik_mingguan[] = $row[4];
  }
  ?>

  <?php
  $jumlah_pasein_hemodelisa_mingguan = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_poli,reg_periksa.status_lanjut,reg_periksa.stts,reg_periksa.tgl_registrasi, 
  COUNT(DISTINCT reg_periksa.no_rkm_medis) AS Jumlah
  FROM reg_periksa
  WHERE reg_periksa.tgl_registrasi BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'  AND reg_periksa.kd_poli='HDL' 
  GROUP BY YEARWEEK(reg_periksa.tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tgl_periksa_hemodelisa[] = $row[3];
    $jumlah_pasein_hemodelisa_mingguan[] = $row[4];
  }
  ?>

  <?php
  $jumlah_pasein_mcu_mingguan = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_poli,reg_periksa.status_lanjut,reg_periksa.stts,reg_periksa.tgl_registrasi, 
  COUNT(DISTINCT reg_periksa.no_rkm_medis) AS Jumlah
  FROM reg_periksa
  WHERE reg_periksa.tgl_registrasi BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'  AND reg_periksa.kd_poli='P20' 
  GROUP BY YEARWEEK(reg_periksa.tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tgl_periksa_mcu[] = $row[3];
    $jumlah_pasein_mcu_mingguan[] = $row[4];
  }
  ?>

  <?php
  $jumlah_pasein_catlab_mingguan = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_poli,reg_periksa.status_lanjut,reg_periksa.stts,reg_periksa.tgl_registrasi, 
  COUNT(DISTINCT reg_periksa.no_rkm_medis) AS Jumlah
  FROM reg_periksa
  WHERE reg_periksa.tgl_registrasi BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'  AND reg_periksa.kd_poli='LAB' 
  GROUP BY YEARWEEK(reg_periksa.tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tgl_periksa_catlab[] = $row[3];
    $jumlah_pasein_catlab_mingguan[] = $row[4];
  }
  ?>

  <?php
  $jumlah_pasein_radioterapi_mingguan = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_poli,reg_periksa.status_lanjut,reg_periksa.stts,reg_periksa.tgl_registrasi, 
  COUNT(DISTINCT reg_periksa.no_rkm_medis) AS Jumlah
  FROM reg_periksa
  WHERE reg_periksa.tgl_registrasi BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'  AND reg_periksa.kd_poli='RDT' 
  GROUP BY YEARWEEK(reg_periksa.tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tgl_periksa_radioterapi[] = $row[3];
    $jumlah_pasein_radioterapi_mingguan[] = $row[4];
  }
  ?>

  <?php
  $jumlah_pasein_rehabmedik_bulanan = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_poli,reg_periksa.status_lanjut,reg_periksa.stts,reg_periksa.tgl_registrasi, 
  COUNT(DISTINCT reg_periksa.no_rkm_medis) AS Jumlah
  FROM reg_periksa
  WHERE reg_periksa.tgl_registrasi BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'  AND reg_periksa.kd_poli='IRM' 
  GROUP BY MONTH(reg_periksa.tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tgl_periksa_rehamedik_bulanan[] = $row[3];
    $jumlah_pasein_rehabmedik_bulanan[] = $row[4];
  }
  ?>

  <?php
  $jumlah_pasein_hemodelisa_bulanan = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_poli,reg_periksa.status_lanjut,reg_periksa.stts,reg_periksa.tgl_registrasi, 
  COUNT(DISTINCT reg_periksa.no_rkm_medis) AS Jumlah
  FROM reg_periksa
  WHERE reg_periksa.tgl_registrasi BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'  AND reg_periksa.kd_poli='HDL' 
  GROUP BY MONTH(reg_periksa.tgl_registrasi) ASC");
  while ($row = mysqli_fetch_array($result)) {
    $tgl_periksa_hemodelisa_bulanan[] = $row[3];
    $jumlah_pasein_hemodelisa_bulanan[] = $row[4];
  }
  ?>

  <?php
  $jumlah_pasein_mcu_bulanan = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_poli,reg_periksa.status_lanjut,reg_periksa.stts,reg_periksa.tgl_registrasi, 
  COUNT(DISTINCT reg_periksa.no_rkm_medis) AS Jumlah
  FROM reg_periksa
  WHERE reg_periksa.tgl_registrasi BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'  AND reg_periksa.kd_poli='P20' 
  GROUP BY MONTH(reg_periksa.tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {

    $tgl_periksa_mcu_bulanan[] = $row[3];
    $jumlah_pasein_mcu_bulanan[] = $row[4];
  }
  ?>

  <?php
  $jumlah_pasein_catlab_bulanan = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_poli,reg_periksa.status_lanjut,reg_periksa.stts,reg_periksa.tgl_registrasi, 
  COUNT(DISTINCT reg_periksa.no_rkm_medis) AS Jumlah
  FROM reg_periksa
  WHERE reg_periksa.tgl_registrasi BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'  AND reg_periksa.kd_poli='LAB' 
  GROUP BY MONTH(reg_periksa.tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {

    $tgl_periksa_catlab_bulanan[] = $row[3];
    $jumlah_pasein_catlab_bulanan[] = $row[4];
  }
  ?>

  <?php
  $jumlah_pasein_radioterapi_bulanan = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_poli,reg_periksa.status_lanjut,reg_periksa.stts,reg_periksa.tgl_registrasi, 
  COUNT(DISTINCT reg_periksa.no_rkm_medis) AS Jumlah
  FROM reg_periksa
  WHERE reg_periksa.tgl_registrasi BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "'  AND reg_periksa.kd_poli='RDT' 
  GROUP BY MONTH(reg_periksa.tgl_registrasi) ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {

    $tgl_periksa_radioterapi_bulanan[] = $row[3];
    $jumlah_pasein_radioterapi_bulanan[] = $row[4];
  }
  ?>


  <?php
  $jumlah_pasein_rehabmedik_tahunan = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_poli,reg_periksa.status_lanjut,reg_periksa.stts,DATE_FORMAT(reg_periksa.tgl_registrasi,'%Y'), 
  COUNT(DISTINCT reg_periksa.no_rkm_medis) AS Jumlah
  FROM reg_periksa
  WHERE reg_periksa.tgl_registrasi BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND reg_periksa.kd_poli='IRM' 
  GROUP BY YEAR(reg_periksa.tgl_registrasi)ASC;");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tgl_periksa_rehabmedik_tahunan[] = $row[3];
    $jumlah_pasein_rehabmedik_tahunan[] = $row[4];
  }
  ?>

  <?php
  $jumlah_pasein_hemodelisa_tahunan = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_poli,reg_periksa.status_lanjut,reg_periksa.stts,DATE_FORMAT(reg_periksa.tgl_registrasi,'%Y'), 
  COUNT(DISTINCT reg_periksa.no_rkm_medis) AS Jumlah
  FROM reg_periksa
  WHERE reg_periksa.tgl_registrasi BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND reg_periksa.kd_poli='HDL' 
  GROUP BY YEAR(reg_periksa.tgl_registrasi)ASC;");
  while ($row = mysqli_fetch_array($result)) {
    $tgl_periksa_hemodelisa_tahunan[] = $row[3];
    $jumlah_pasein_hemodelisa_tahunan[] = $row[4];
  }
  ?>

  <?php
  $jumlah_pasein_mcu_tahunan = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_poli,reg_periksa.status_lanjut,reg_periksa.stts,DATE_FORMAT(reg_periksa.tgl_registrasi,'%Y'), 
  COUNT(DISTINCT reg_periksa.no_rkm_medis) AS Jumlah
  FROM reg_periksa
  WHERE reg_periksa.tgl_registrasi BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND reg_periksa.kd_poli='P20'
  GROUP BY YEAR(reg_periksa.tgl_registrasi)ASC;");
  while ($row = mysqli_fetch_array($result)) {
    $tgl_periksa_mcu_tahunan[] = $row[3];
    $jumlah_pasein_mcu_tahunan[] = $row[4];
  }
  ?>

  <?php
  $jumlah_pasein_catlab_tahunan = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_poli,reg_periksa.status_lanjut,reg_periksa.stts,DATE_FORMAT(reg_periksa.tgl_registrasi,'%Y'), 
  COUNT(DISTINCT reg_periksa.no_rkm_medis) AS Jumlah
  FROM reg_periksa
  WHERE reg_periksa.tgl_registrasi BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND reg_periksa.kd_poli='RDO' 
  GROUP BY YEAR(reg_periksa.tgl_registrasi)ASC;");
  while ($row = mysqli_fetch_array($result)) {
    $tgl_periksa_catlab_tahunan[] = $row[3];
    $jumlah_pasein_catlab_tahunan[] = $row[4];
  }
  ?>

  <?php
  $jumlah_pasein_radioterapi_tahunan = array();
  $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_poli,reg_periksa.status_lanjut,reg_periksa.stts,DATE_FORMAT(reg_periksa.tgl_registrasi,'%Y'), 
  COUNT(DISTINCT reg_periksa.no_rkm_medis) AS Jumlah
  FROM reg_periksa
  WHERE reg_periksa.tgl_registrasi BETWEEN '" . $tgl_awal . "' AND '" . $tgl_akhir . "' AND reg_periksa.kd_poli='RDT'
  GROUP BY YEAR(reg_periksa.tgl_registrasi)ASC;");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $tgl_periksa_radioterapi_tahunan[] = $row[3];
    $jumlah_pasein_radioterapi_tahunan[] = $row[4];
  }
  ?>
</body>

</html>



<script>
  Highcharts.chart("chart_mingguan", {

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
        '<?php echo  implode("','", $tgl_periksa_rehabmedik) ?> ',
        '<?php echo  implode("','", $tgl_periksa_hemodelisa) ?> ',
        '<?php echo  implode("','", $tgl_periksa_mcu) ?> ',
        '<?php echo  implode("','", $tgl_periksa_catlab) ?> ',
        '<?php echo  implode("','", $tgl_periksa_radioterapi) ?> '
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
          name: 'Rehab Medik',
          data: [
            <?php foreach ($jumlah_pasein_rehabmedik_mingguan as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Hemodelisa',
          data: [
            <?php foreach ($jumlah_pasein_hemodelisa_mingguan as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'MCU',
          data: [
            <?php foreach ($jumlah_pasein_mcu_mingguan as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Catlab',
          data: [
            <?php foreach ($jumlah_pasein_catlab_mingguan as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Radioterapi',
          data: [
            <?php foreach ($jumlah_pasein_radioterapi_mingguan as $cle => $valeur) { ?>
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
        '<?php echo  implode("','", $tgl_periksa_rehamedik_bulanan) ?> ',
        '<?php echo  implode("','", $tgl_periksa_hemodelisa_bulanan) ?> ',
        '<?php echo  implode("','", $tgl_periksa_mcu_bulanan) ?> ',
        '<?php echo  implode("','", $tgl_periksa_catlab_bulanan) ?> ',
        '<?php echo  implode("','", $tgl_periksa_radioterapi_bulanan) ?> '
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
          name: 'Rehab Medik',
          data: [
            <?php foreach ($jumlah_pasein_rehabmedik_bulanan as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Hemodelisa',
          data: [
            <?php foreach ($jumlah_pasein_hemodelisa_bulanan as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'MCU',
          data: [
            <?php foreach ($jumlah_pasein_mcu_bulanan as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Catlab',
          data: [
            <?php foreach ($jumlah_pasein_catlab_bulanan as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Radioterapi',
          data: [
            <?php foreach ($jumlah_pasein_radioterapi_bulanan as $cle => $valeur) { ?>
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
        '<?php echo  implode("','", $tgl_periksa_rehabmedik_tahunan) ?> ',
        '<?php echo  implode("','", $tgl_periksa_hemodelisa_tahunan) ?> ',
        '<?php echo  implode("','", $tgl_periksa_mcu_tahunan) ?> ',
        '<?php echo  implode("','", $tgl_periksa_catlab_tahunan) ?> ',
        '<?php echo  implode("','", $tgl_periksa_radioterapi_tahunan) ?> '
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
          name: 'Rehab Medik',
          data: [
            <?php foreach ($jumlah_pasein_rehabmedik_tahunan as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Hemodelisa',
          data: [
            <?php foreach ($jumlah_pasein_hemodelisa_tahunan as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'MCU',
          data: [
            <?php foreach ($jumlah_pasein_mcu_tahunan as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Catlab',
          data: [
            <?php foreach ($jumlah_pasein_catlab_tahunan as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Radioterapi',
          data: [
            <?php foreach ($jumlah_pasein_radioterapi_tahunan as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>
    ]
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

