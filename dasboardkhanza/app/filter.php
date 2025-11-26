<!DOCTYPE html>
<html lang="en">
<!-- file header dashboard -->
<?php include('header.php'); ?>
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
          <div class="container">
            <br>
            <h4>Pencarian Data Berdasarkan Tanggal</h4>

            <form action="<?php echo $_SERVER["PHP_SELF"]; ?>" method="post">

              <div class="form-group">
                <label>Tanggal Awal</label>
                <div class="input-group date">
                  <div class="input-group-addon">
                    <span class="glyphicon glyphicon-th"></span>
                  </div>
                  <input id="tgl_mulai" placeholder="masukkan tanggal Awal" type="text" class="form-control datepicker" name="tgl_awal" value="<?php if (isset($_POST['tgl_awal'])) echo $_POST['tgl_awal']; ?>">
                </div>
              </div>
              <div class="form-group">
                <label>Tanggal Awal</label>
                <div class="input-group date">
                  <div class="input-group-addon">
                    <span class="glyphicon glyphicon-th"></span>
                  </div>
                  <input id="tgl_akhir" placeholder="masukkan tanggal Akhir" type="text" class="form-control datepicker" name="tgl_akhir" value="<?php if (isset($_POST['tgl_akhir'])) echo $_POST['tgl_akhir']; ?>">
                </div>
              </div>

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
              <div class="form-group">
                <input type="submit" class="btn btn-info" value="Cari">
              </div>
            </form>


            <table class="table table-bordered table-hover">
              <br>
              <thead>
                <tr>
                  <th>No</th>
                  <th>NIK</th>
                  <th>Nama</th>
                  <th>Jenis Kelamin</th>
                  <th>Tanggal Lahir</th>
                  <th>Jurusan</th>
                </tr>
              </thead>
              <?php

              include "koneksi.php";
              if (isset($_POST['tgl_awal']) && isset($_POST['tgl_akhir'])) {

                $tgl_awal = date('Y-m-d', strtotime($_POST["tgl_awal"]));
                $tgl_akhir = date('Y-m-d', strtotime($_POST["tgl_akhir"]));


                $sql = "select * from mahasiswa where tanggal_lhr between '" . $tgl_awal . "' and '" . $tgl_akhir . "' order by nim asc";
              } else {
                $sql = "select * from mahasiswa order by nim asc";
              }

              $hasil = mysqli_query($kon, $sql);
              $no = 0;
              while ($data = mysqli_fetch_array($hasil)) {
                $no++;
              ?>
                <tbody>
                  <tr>
                    <td><?php echo $no; ?></td>
                    <td><?php echo $data["nim"]; ?></td>
                    <td><?php echo $data["nama"];   ?></td>
                    <td><?php echo $data["jenis_kelamin"];   ?></td>
                    <td><?php echo date('d-m-Y', strtotime($data["tanggal_lhr"]));   ?></td>
                    <td><?php echo $data["jurusan"];   ?></td>

                  </tr>
                </tbody>
              <?php
              }
              ?>
            </table>
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
  $jumlah_pasein_lab_bulanan = array();
  $result = mysqli_query($koneksi, "SELECT periksa_lab.no_rawat,DATE_FORMAT(periksa_lab.tgl_periksa,'%Y %M'), COUNT(DISTINCT periksa_lab.no_rawat) AS Jumlah
  FROM periksa_lab
  WHERE MONTH(periksa_lab.tgl_periksa)
  GROUP BY YEAR(periksa_lab.tgl_periksa)ASC, MONTH(periksa_lab.tgl_periksa) ASC;");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $no_rawat[] = $row[0];
    $tgl_periksa_lab_bulanan[] = $row[1];
    $jumlah_pasein_lab_bulanan[] = $row[2];
  }
  ?>

  <?php
  $jumlah_pasein_rad_bulanan = array();
  $result = mysqli_query($koneksi, "SELECT periksa_radiologi.no_rawat,date_format(periksa_radiologi.tgl_periksa,'%Y %M'), COUNT(DISTINCT periksa_radiologi.no_rawat) AS Jumlah
  FROM periksa_radiologi 
  WHERE MONTH(periksa_radiologi.tgl_periksa)
  GROUP BY YEAR(periksa_radiologi.tgl_periksa) asc,MONTH(periksa_radiologi.tgl_periksa) asc");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $no_rawat[] = $row[0];
    $tgl_periksa_rad_bulanan[] = $row[1];
    $jumlah_pasein_rad_bulanan[] = $row[2];
  }
  ?>

</body>

</html>


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
        '<?php echo  implode("','", $tgl_periksa_lab_tahunan) ?> ',
        '<?php echo  implode("','", $tgl_periksa_rad_tahunan) ?> '
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
          name: 'Laboratrium',
          data: [
            <?php foreach ($jumlah_pasein_lab_tahunan as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Radiologi',
          data: [
            <?php foreach ($jumlah_pasein_rad_tahunan as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>
    ]
  });
</script>