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
          <h5 class="mb-2">Filter Form</h5>


          <!-- // kolom baris kedua :) -->
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

              <div class="col-md-1">
                <div class="col text-center">
                  <button type="submit" class="btn btn-block btn-primary">Cari</button>
                </div>
              </div>
            </div>

            <div style="margin-top:10px;">
              <div class="row">
                <div class="col-md-12 ">
                  <figure class="highcharts-figure">
                    <div id="chart_bulanan"></div>
                  </figure>
                </div>
              </div>

              <!-- Main content -->
            </div>
            <!-- /.error-page -->
        </div>
        </form>
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
  $jumlah_pasein_lab_bulanan = array();
  $result = mysqli_query($koneksi, "SELECT periksa_lab.no_rawat,periksa_lab.tgl_periksa, COUNT(DISTINCT periksa_lab.no_rawat) AS Jumlah
  FROM periksa_lab
  WHERE periksa_lab.tgl_periksa BETWEEN '" . $tgl_awal . "' and '" . $tgl_akhir . "' 
  GROUP BY  periksa_lab.tgl_periksa ASC");
  while ($row = mysqli_fetch_array($result)) {
    $tgl_periksa_lab_bulanan[] = $row[1];
    $jumlah_pasein_lab_bulanan[] = $row[2];
  }
  // print_r($jumlah_pasein_lab_bulanan);
  // var_dump($tgl_awal);
  ?>

  <?php
  $jumlah_pasein_rad_bulanan = array();
  $result = mysqli_query($koneksi, "SELECT periksa_radiologi.no_rawat,periksa_radiologi.tgl_periksa, COUNT(DISTINCT periksa_radiologi.no_rawat) AS Jumlah
  FROM periksa_radiologi
  WHERE periksa_radiologi.tgl_periksa BETWEEN '" . $tgl_awal . "' and '" . $tgl_akhir . "' 
  GROUP BY  periksa_radiologi.tgl_periksa ASC");
  while ($row = mysqli_fetch_array($result)) {
    $tgl_periksa_rad_bulanan[] = $row[1];
    $jumlah_pasein_rad_bulanan[] = $row[2];
  }
  // print_r($jumlah_pasein_lab_bulanan);
  // var_dump($tgl_awal);
  ?>

</body>

</html>

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
  $(document).ready(function() {
    $('#date').datetimepicker({
      defaultDate: new Date()
    });
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
        '<?php echo  implode("','", $tgl_periksa_lab_bulanan) ?> ',
        '<?php echo  implode("','", $tgl_periksa_rad_bulanan) ?> '
      ]
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
          name: 'Laboratrium',
          data: [
            <?php foreach ($jumlah_pasein_lab_bulanan as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Radiologi',
          data: [
            <?php foreach ($jumlah_pasein_rad_bulanan as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>
    ]
  });
</script>