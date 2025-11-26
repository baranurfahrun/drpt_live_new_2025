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
              <div class="row">
                <div class="mx-auto" style="width: 200px;">
                  &nbsp;
                </div>
                <div class="col-md-12">
                  <div class="card">
                    <div class="card-header">
                      <h3 class="card-title">OBAT PER DOKTER RALAN</h3>
                    </div>
                    <!-- /.card-header -->
                    <div class="container-fluid">
                      <br>
                      <div class="row align-items-center">
                        <div class="col-md-2">
                          <div class="input-group date" id="reservationdate" data-target-input="nearest">
                            <input type="text" class="form-control pickdate date_range_filter" data-target="" name=""
                              value=" " />
                            <!-- <div class="input-group-append" data-target="#reservationdate" data-toggle="datetimepicker">                                                     -->
                          </div>
                        </div>
                        <div class="col-md-2">
                          <div class="input-group date" id="reservationdate" data-target-input="nearest">
                            <input type="text" class="form-control pickdate date_range_filter2" name="">
                            <div class="input-group-append" data-target="#reservationdate" data-toggle="datetimepicker">
                              <!-- <div class="input-group-append" data-target="#reservationdate" data-toggle="datetimepicker">                                -->
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="card-body">
                        <table id="userDataList" class="table table-bordered table-striped">
                          <thead>
                            <!-- <tr>
                              <th>NO</th>
                              <th>NAMA DOKTER</th>
                              <th>NAMA PASIEN</th>
                              <th>OBAT</th>
                              <th>TANGGAL PERAWATAN</th>
                              <th>BIAYA OBAT</th>
                              <th>JUMLAH</th>
                              <th>TOTAL</th>
                            </tr> -->
                            <tr>
                              <!-- <th>No</th> -->
                              <th>no rm</th>
                              <th>pasien</th>
                              <th>pekerjaan</th>
                              <th>tempat lahir</th>
                            </tr>
                          </thead>
                          <tfoot>
                            <!-- <tr>
                                <th colspan="7" style="text-align:right">Total:</th>
                                <th></th>
                            </tr> -->
                        </tfoot>
                        </table>
                      </div>
                      <!-- /.card-body -->
                    </div>
                  </div>
                </div>
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
</body>
</html>
