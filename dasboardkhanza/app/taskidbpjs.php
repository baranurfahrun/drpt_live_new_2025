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
          <h5 class="mb-2">Apotik</h5>
          <div class="alert alert-info alert-dismissible">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <h5><i class="icon fas fa-info"></i>Informasi!</h5>
            Maaf masih dalam Pengembangan
          </div>
          <!-- // kolom baris kedua :) -->
          <section class="content-header">
            <div class="container-fluid">
              <div class="card card-default color-palette-box">
                <div class="card-header">
                  <h2 align="center"> Data Task ID BPJS</h2>
                </div>
                <div class="card-body">
                  <form method="post">
                    <div class="row g-3 align-items-center">
                      <!-- <label class="col-form-label">Periode</label> -->
                      <div class="row">

                        <div class="col-md-5 col-xs-6">
                          <input type="date" class="form-control" name="tanggal1" value="<?php echo $tanggal; ?>"
                            required>
                        </div>
                        <div class="col-md-5 col-xs-6">
                          <input type="date" class="form-control" name="tanggal2" value="<?php echo $tanggal; ?>"
                            required>
                        </div>
                        <div class="col-md-1">
                          <div class="d-grid">
                            <button class="btn btn-primary" name="tblfilter" type="submit"><i class="fa fa-filter"></i>
                              Filter</button>
                          </div>
                        </div>
                        
                      </div>
                    </div>
                  </form>

                  <div style="overflow-x:auto;">
                    <table id="taskid" class="table table-bordered table-striped">
                      <thead>
                        <tr>

                          <th>
                            <div align='center'><b>No</b></font>
                            </div>
                          </th>
                          <th>
                            <div align='center'><b>No Rawat</b></font>
                            </div>
                          </th>

                          <th>
                            <div align='center'><b>Task ID 1</b></font>
                            </div>
                          </th>
                          <th>
                            <div align='center'><b>Task ID 2</b></font>
                            </div>
                          </th>
                          <th>
                            <div align='center'><b>Task ID 3</b></font>
                            </div>
                          </th>
                          <th>
                            <div align='center'><b>Task ID 4</b></font>
                            </div>
                          </th>
                          <th>
                            <div align='center'><b>Task ID 5</b></font>
                            </div>
                          </th>
                          <th>
                            <div align='center'><b>Task ID 6</b></font>
                            </div>
                          </th>
                          <th>
                            <div align='center'><b>Task ID 7</b></font>
                            </div>
                          </th>
                          <th>
                            <div align='center'><b>Task ID 99</b></font>
                            </div>
                          </th>
                          <th>
                            <div align='center'><b>Poliklinik</b></font>
                            </div>
                          </th>
                          <th>
                            <div align='center'><b>No RM</b></font>
                            </div>
                          </th>

                        </tr>
                      </thead>
                      <tbody>
                        <?php
                        if (isset($_POST['tblfilter'])) {

                          $ambil3 = $koneksi->query("SELECT * FROM referensi_mobilejkn_bpjs_taskid WHERE taskid = '3' and waktu BETWEEN '" . $_POST['tanggal1'] . " " . "00:00:01" . "' and '" . $_POST['tanggal2'] . " " . "23:59:59" . "' ");

                        } else {
                          $ambil3 = $koneksi->query("SELECT * FROM referensi_mobilejkn_bpjs_taskid WHERE taskid = '3' and waktu BETWEEN '" . $tanggal . " " . "00:00:01" . "' and '" . $tanggal . " " . "23:59:59" . "' group by no_rawat order by no_rawat desc");

                        }
                        $no = 1;
                        while ($tampil3 = $ambil3->fetch_assoc()) {
                          $norawat = $tampil3['no_rawat'];

                          $ambil4 = $koneksi->query("SELECT * FROM referensi_mobilejkn_bpjs_taskid WHERE taskid = '4' and no_rawat='$norawat' group by no_rawat order by no_rawat desc");
                          $ambil5 = $koneksi->query("SELECT * FROM referensi_mobilejkn_bpjs_taskid WHERE taskid = '5' and no_rawat='$norawat' group by no_rawat order by no_rawat desc");
                          $ambil6 = $koneksi->query("SELECT * FROM referensi_mobilejkn_bpjs_taskid WHERE taskid = '6' and no_rawat='$norawat' group by no_rawat order by no_rawat desc");
                          $ambil7 = $koneksi->query("SELECT * FROM referensi_mobilejkn_bpjs_taskid WHERE taskid = '7' and no_rawat='$norawat'  group by no_rawat order by no_rawat desc");
                          $ambil99 = $koneksi->query("SELECT * FROM referensi_mobilejkn_bpjs_taskid WHERE taskid = '99' and no_rawat='$norawat'  group by no_rawat order by no_rawat desc");

                          $tampil4 = $ambil4->fetch_assoc();
                          $tampil5 = $ambil5->fetch_assoc();
                          $tampil6 = $ambil6->fetch_assoc();
                          $tampil7 = $ambil7->fetch_assoc();
                          $tampil99 = $ambil99->fetch_assoc();

                          $ambil = $koneksi->query("SELECT * FROM reg_periksa WHERE no_rawat='$norawat'");
                          $tampil = $ambil->fetch_assoc();
                          ?>

                          <tr>

                            <td>
                              <div align='center'><b>
                                  <?php echo $no++; ?>
                                </b></font>
                              </div>
                            </td>
                            <td >
                              <div align='center'><b>
                                  <?php echo $norawat; ?>
                                </b></font>
                              </div>
                            </td>
                            <td>
                              <div align='center'>
                                <font size='3' font color='green'><b><i class="fa fa-check"></i></b></font>
                              </div>
                            </td>
                            <td>
                              <div align='center'>
                                <font size='3' font color='green'><b><i class="fa fa-check"></i></b></font>
                              </div>
                            </td>

                            <td>
                              <div align='center'>
                                <font size='3'><b>
                                    <?php if ($tampil3['taskid'] != "") { ?><i class="fa fa-check"
                                        style="color: green;"></i>
                                    </b>
                                  <?php } else { ?><i class="fa fa-times" style="color: red;"></i>
                                  <?php } ?>
                                </font>
                              </div>
                            </td>

                            <td>
                              <div align='center'>
                                <font size='3'><b>
                                    <?php if ($tampil4['taskid'] != "") { ?><i class="fa fa-check"
                                        style="color: green;"></i>
                                    </b>
                                  <?php } else { ?><i class="fa fa-times" style="color: red;"></i>
                                  <?php } ?></b>
                                </font>
                              </div>
                            </td>
                            <td>
                              <div align='center'>
                                <font size='3'><b>
                                    <?php if ($tampil5['taskid'] != "") { ?><i class="fa fa-check"
                                        style="color: green;"></i>
                                    </b>
                                  <?php } else { ?><i class="fa fa-times" style="color: red;"></i>
                                  <?php } ?></b>
                                </font>
                              </div>
                            </td>
                            <td>
                              <div align='center'>
                                <font size='3'><b>
                                    <?php if ($tampil6['taskid'] != "") { ?><i class="fa fa-check"
                                        style="color: green;"></i>
                                    </b>
                                  <?php } else { ?><i class="fa fa-times" style="color: red;"></i>
                                  <?php } ?></b>
                                </font>
                              </div>
                            </td>
                            <td>
                              <div align='center'>
                                <font size='3'><b>
                                    <?php if ($tampil7['taskid'] != "") { ?><i class="fa fa-check"
                                        style="color: green;"></i>
                                    </b>
                                  <?php } else { ?><i class="fa fa-times" style="color: red;"></i>
                                  <?php } ?></b>
                                </font>
                              </div>
                            </td>
                            <td>
                              <div align='center'>
                                <font size='3'><b>
                                    <?php if ($tampil99['taskid'] != "") { ?><i class="fa fa-check"
                                        style="color: green;"></i>
                                    </b>
                                  <?php } else { ?><i class="fa fa-times" style="color: red;"></i>
                                  <?php } ?></b>
                                </font>
                              </div>
                            </td>

                            <td>
                              <div align='center'>
                                <font size='3'><b>
                                    <?php echo $tampil['kd_poli']; ?>
                                  </b></font>
                              </div>
                            </td>
                            <td >
                              <div align='center'>
                                <font size='3'><b>
                                    <?php echo $tampil['no_rkm_medis']; ?>
                                  </b></font>
                              </div>
                            </td>
                          </tr>
                        <?php } ?>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
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
