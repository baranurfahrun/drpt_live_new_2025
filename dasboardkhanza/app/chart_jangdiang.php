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
          <h5 class="mb-2">jangdiag</h5>
          <div class="row">
            <div class="col-md-3 col-sm-6 col-12">
              <div class="info-box">
                <span class="info-box-icon bg-info"><i class="fa fa-thermometer-quarter"></i></span>
                <div class="info-box-content">
                  <span class="info-box-text">Total Kunjungan Laboratrium</span>
                  <?php
                  $data_pasien = mysqli_query($koneksi, "SELECT periksa_lab.no_rawat,periksa_lab.tgl_periksa,periksa_lab.`status`,periksa_lab.kategori 
                  FROM periksa_lab
                  WHERE periksa_lab.tgl_periksa=CURDATE()");
                  $jumlah = mysqli_num_rows($data_pasien);
                  ?>
                  <span class="info-box-number">
                    <h1><strong><?php echo "$jumlah"; ?></strong></h1>
                  </span>
                </div>
                <!-- /.info-box-content -->
              </div>
              <!-- /.info-box -->
            </div>
            <!-- /.col -->
            <div class="col-md-3 col-sm-6 col-12">
              <div class="info-box">
                <span class="info-box-icon bg-success"><i class="fa fa-desktop"></i></span>
                <div class="info-box-content">
                  <span class="info-box-text">Total Kunjungan Radiologi</span>
                  <?php
                  $data_pasien = mysqli_query($koneksi, "SELECT periksa_radiologi.no_rawat,periksa_radiologi.tgl_periksa,periksa_radiologi.status
                  FROM periksa_radiologi
                  WHERE periksa_radiologi.tgl_periksa=CURDATE()
                  GROUP BY periksa_radiologi.no_rawat");
                  $jumlah = mysqli_num_rows($data_pasien);
                  ?>
                  <span class="info-box-number">
                    <h1><strong><?php echo "$jumlah"; ?></h1></strong>
                  </span>

                </div>
                <!-- /.info-box-content -->
              </div>
              <!-- /.info-box -->
            </div>
            <!-- /.col -->

            <!-- <div class="col-md-3 col-sm-6 col-12">
              <div class="info-box">
                <span class="info-box-icon bg-warning"><i class="fa fa-bed"></i></span>
                <div class="info-box-content">
                  <span class="info-box-text">Pasien Cek lab Bulan Ini</span>
                  <?php
                  $data_pasien = mysqli_query($koneksi, "SELECT periksa_radiologi.no_rawat,periksa_radiologi.tgl_periksa,periksa_radiologi.status
                  FROM periksa_radiologi
                  WHERE periksa_radiologi.tgl_periksa=CURDATE()
                  GROUP BY periksa_radiologi.no_rawat");
                  $jumlah = mysqli_num_rows($data_pasien);
                  ?>
                  <span class="info-box-number"><?php echo "$jumlah"; ?></span>
                  <h5>
                    <small>
                      <?php
                      $data_pasien = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar
                            FROM pasien 
                            INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                            WHERE pasien.jk='L' and reg_periksa.stts='Dirawat' AND reg_periksa.status_lanjut='Ranap' AND reg_periksa.stts_daftar='Lama'");
                      $jumlah = mysqli_num_rows($data_pasien);
                      ?>
                      <span class="count_top"><i class="fa fa-male"></i> <?php echo "$jumlah"; ?></span>
                      <?php
                      $data_pasien = mysqli_query($koneksi, "SELECT pasien.nm_pasien,pasien.jk,reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.stts_daftar
                            FROM pasien 
                            INNER JOIN reg_periksa on pasien.no_rkm_medis=reg_periksa.no_rkm_medis 
                            WHERE pasien.jk='P' and reg_periksa.stts='Dirawat' AND reg_periksa.status_lanjut='Ranap' AND reg_periksa.stts_daftar='Lama'");
                      $jumlah = mysqli_num_rows($data_pasien);
                      ?>
                      <span class="count_top"><i class="fa fa-female"></i> <?php echo "$jumlah"; ?></span>
                    </small>
                  </h5>
                </div>
              </div>
            </div> -->

            <!-- /.col -->
            
          </div>

          <!-- // kolom baris kedua :) -->
          <div class="row">
            <div class="col-md-12 ">
              <figure class="highcharts-figure">
                <div id="jangdiag"></div>
              </figure>
            </div>
            <div class="col-md-6 ">
              <figure class="highcharts-figure">
                <div id="radiologi"></div>
              </figure>
            </div>
            <div class="col-md-6 ">
              <figure class="highcharts-figure">
                <div id="laboratrium"></div>
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
  <!-- FILTER  -->
  <?php
  if (isset($_POST['tgl_awal']) && isset($_POST['tgl_akhir'])) {
    $tgl_awal = date('Y-m-d', strtotime($_POST["tgl_awal"]));
    $tgl_akhir = date('Y-m-d', strtotime($_POST["tgl_akhir"]));
  }
  ?>
  <!-- FILE QWERY -->
  <?php
  $jumlah_kunjungan_lab = array();
  $result = mysqli_query($koneksi, "SELECT periksa_lab.no_rawat,periksa_lab.tgl_periksa,periksa_lab.`status`,periksa_lab.kategori , COUNT(periksa_lab.no_rawat) AS jumlah
  FROM periksa_lab
  WHERE periksa_lab.tgl_periksa=CURDATE()");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $kd_no_rawat[] = $row[0];
    $tgl_periksa[] = $row[1];
    $status[] = $row[2];
    $kategori[] = $row[3];
    $jumlah_kunjungan_lab[] = $row[4];
  }
  ?>

  <?php
  $jumlah_kunjungan_radiologi = array();
  $result = mysqli_query($koneksi, "SELECT periksa_radiologi.no_rawat,periksa_radiologi.tgl_periksa,periksa_radiologi.`status`, COUNT(periksa_radiologi.no_rawat) AS Jumlah
  FROM periksa_radiologi
  WHERE periksa_radiologi.tgl_periksa=CURDATE() ");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $kd_no_rawat[] = $row[0];
    $tgl_periksa[] = $row[1];
    $status[] = $row[2];
    $jumlah_kunjungan_radiologi[] = $row[3];
  }
  ?>

  <?php
  $jumlah_kunjungan_lab_ralan = array();
  $result = mysqli_query($koneksi, "                  
  SELECT periksa_lab.no_rawat,periksa_lab.tgl_periksa,periksa_lab.`status`,periksa_lab.kategori, COUNT(periksa_lab.no_rawat) AS Jumlah
                    FROM periksa_lab
                    WHERE periksa_lab.tgl_periksa=CURDATE() AND periksa_lab.status='Ralan';
                   ");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_kunjungan_lab_ralan[] = $row[4];
  }
  ?>

  <?php
  $jumlah_kunjungan_lab_ranap = array();
  $result = mysqli_query($koneksi, "                  
  SELECT periksa_lab.no_rawat,periksa_lab.tgl_periksa,periksa_lab.`status`,periksa_lab.kategori, COUNT(periksa_lab.no_rawat) AS Jumlah
                    FROM periksa_lab
                    WHERE periksa_lab.tgl_periksa=CURDATE() AND periksa_lab.status='Ranap';
                   ");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_kunjungan_lab_ranap[] = $row[4];
  }
  ?>

  <?php
  $jumlah_kunjungan_rad_ralan = array();
  $result = mysqli_query($koneksi, "SELECT periksa_radiologi.no_rawat,periksa_radiologi.tgl_periksa,periksa_radiologi.`status`, COUNT(periksa_radiologi.no_rawat) AS Jumlah
  FROM periksa_radiologi
  WHERE periksa_radiologi.tgl_periksa=CURDATE() AND periksa_radiologi.`status`='Ralan'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_kunjungan_rad_ralan[] = $row[3];
  }
  ?>

  <?php
  $jumlah_kunjungan_rad_ranap = array();
  $result = mysqli_query($koneksi, "SELECT periksa_radiologi.no_rawat,periksa_radiologi.tgl_periksa,periksa_radiologi.`status`, COUNT(periksa_radiologi.no_rawat) AS Jumlah
  FROM periksa_radiologi
  WHERE periksa_radiologi.tgl_periksa=CURDATE() AND periksa_radiologi.`status`='Ranap'");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $jumlah_kunjungan_rad_ranap[] = $row[3];
  }
  ?>

  <?php
  $jumlah_pasein_lab_mingguan = array();
  $result = mysqli_query($koneksi, "SELECT periksa_lab.no_rawat,periksa_lab.tgl_periksa, COUNT(DISTINCT periksa_lab.no_rawat) AS Jumlah
  FROM periksa_lab
  WHERE periksa_lab.tgl_periksa BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
  GROUP BY YEARWEEK(periksa_lab.tgl_periksa) asc");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $no_rawat[] = $row[0];
    $tgl_periksa_lab[] = $row[1];
    $jumlah_pasein_lab_mingguan[] = $row[2];
  }
  ?>

  <?php
  $jumlah_pasein_rad_mingguan = array();
  $result = mysqli_query($koneksi, "SELECT periksa_radiologi.no_rawat,periksa_radiologi.tgl_periksa, COUNT(DISTINCT periksa_radiologi.no_rawat) AS Jumlah
  FROM periksa_radiologi 
  WHERE periksa_radiologi.tgl_periksa BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
  GROUP BY YEARWEEK(periksa_radiologi.tgl_periksa) asc");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $no_rawat[] = $row[0];
    $tgl_periksa_rad[] = $row[1];
    $jumlah_pasein_rad_mingguan[] = $row[2];
  }
  ?>

  <?php
  $jumlah_pasein_lab_bulanan = array();
  $result = mysqli_query($koneksi, "SELECT periksa_lab.no_rawat,DATE_FORMAT(periksa_lab.tgl_periksa,'%Y %M'), COUNT(DISTINCT periksa_lab.no_rawat) AS Jumlah
  FROM periksa_lab
  WHERE periksa_lab.tgl_periksa BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
  GROUP BY MONTH(periksa_lab.tgl_periksa)ASC");
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
  WHERE periksa_radiologi.tgl_periksa BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
  GROUP BY MONTH(periksa_radiologi.tgl_periksa) asc");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $no_rawat[] = $row[0];
    $tgl_periksa_rad_bulanan[] = $row[1];
    $jumlah_pasein_rad_bulanan[] = $row[2];
  }
  ?>

  <?php
  $jumlah_pasein_lab_tahunan = array();
  $result = mysqli_query($koneksi, "SELECT periksa_lab.no_rawat,DATE_FORMAT(periksa_lab.tgl_periksa,'%Y'), COUNT(DISTINCT periksa_lab.no_rawat) AS Jumlah
  FROM periksa_lab
  WHERE periksa_lab.tgl_periksa BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
  GROUP BY YEAR(periksa_lab.tgl_periksa)ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $no_rawat[] = $row[0];
    $tgl_periksa_lab_tahunan[] = $row[1];
    $jumlah_pasein_lab_tahunan[] = $row[2];
  }
  ?>

  <?php
  $jumlah_pasein_rad_tahunan = array();
  $result = mysqli_query($koneksi, "SELECT periksa_radiologi.no_rawat,DATE_FORMAT(periksa_radiologi.tgl_periksa,'%Y'), COUNT(DISTINCT periksa_radiologi.no_rawat) AS Jumlah
  FROM periksa_radiologi
  WHERE periksa_radiologi.tgl_periksa BETWEEN  '" . $tgl_awal . "' AND '" . $tgl_akhir . "'
  GROUP BY YEAR(periksa_radiologi.tgl_periksa)ASC");
  //urutab tampilkan hasil query dari database
  while ($row = mysqli_fetch_array($result)) {
    $no_rawat[] = $row[0];
    $tgl_periksa_rad_tahunan[] = $row[1];
    $jumlah_pasein_rad_tahunan[] = $row[2];
  }
  ?>
</body>

</html>

<script>
  const chart = Highcharts.chart('jangdiag', {
    title: {
      text: 'Kunjungan Hari ini'
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

        'laboratrium',
        'Radiologi'
      ]
    },

    series: [{
      type: 'column',
      name: 'Jumlah',
      colorByPoint: true,
      data: [<?php echo  implode("','", $jumlah_kunjungan_lab) ?>,
        <?php echo  implode("','", $jumlah_kunjungan_radiologi) ?>
      ],
      showInLegend: false
    }]
  });
</script>

<script>
  // Data retrieved from https://netmarketshare.com/
  // Build the chart
  Highcharts.chart('laboratrium', {
    chart: {
      plotBackgroundColor: null,
      plotBorderWidth: null,
      plotShadow: false,
      type: 'pie'
    },
    title: {
      text: 'Kunjungan Laboratrium'
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
      name: 'Jumlah',
      colorByPoint: true,
      data: [{
        name: 'Rawat Jalan',
        y: <?php echo  implode("','", $jumlah_kunjungan_lab_ralan) ?>,
        sliced: true,
        selected: true
      }, {
        name: 'Rawat Inap',
        y: <?php echo  implode("','", $jumlah_kunjungan_lab_ranap) ?>

      }]
    }]
  });
</script>

<script>
  // Data retrieved from https://netmarketshare.com/
  // Build the chart
  Highcharts.chart('radiologi', {
    chart: {
      plotBackgroundColor: null,
      plotBorderWidth: null,
      plotShadow: false,
      type: 'pie'
    },
    title: {
      text: 'Kunjungan Radiologi'
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
      name: 'Jumlah',
      colorByPoint: true,
      data: [{
        name: 'Rawat Jalan',
        y: <?php echo  implode("','", $jumlah_kunjungan_rad_ralan) ?>,
        sliced: true,
        selected: true
      }, {
        name: 'Rawat Inap',
        y: <?php echo  implode("','", $jumlah_kunjungan_rad_ranap) ?>
      }]
    }]
  });
</script>

<script>
  Highcharts.chart('container', {
    chart: {
      type: 'area'
    },
    title: {
      text: 'Greenhouse gases from Norwegian economic activity'
    },
    subtitle: {
      text: 'Source: ' +
        '<a href="https://www.ssb.no/en/statbank/table/09288/"' +
        'target="_blank">SSB</a>'
    },
    yAxis: {
      title: {
        useHTML: true,
        text: 'Million tonnes CO<sub>2</sub>-equivalents'
      }
    },
    tooltip: {
      shared: true,
      headerFormat: '<span style="font-size:12px"><b>{point.key}</b></span><br>'
    },
    plotOptions: {
      series: {
        pointStart: 2012
      },
      area: {
        stacking: 'normal',
        lineColor: '#666666',
        lineWidth: 1,
        marker: {
          lineWidth: 1,
          lineColor: '#666666'
        }
      }
    },
    series: [{
      name: 'Ocean transport',
      data: [13234, 12729, 11533, 17798, 10398, 12811, 15483, 16196, 16214]
    }, {
      name: 'Households',
      data: [6685, 6535, 6389, 6384, 6251, 5725, 5631, 5047, 5039]

    }, {
      name: 'Agriculture and hunting',
      data: [4752, 4820, 4877, 4925, 5006, 4976, 4946, 4911, 4913]
    }, {
      name: 'Air transport',
      data: [3164, 3541, 3898, 4115, 3388, 3569, 3887, 4593, 1550]

    }, {
      name: 'Construction',
      data: [2019, 2189, 2150, 2217, 2175, 2257, 2344, 2176, 2186]
    }]
  });
</script>

<script>
  Highcharts.chart("chart_mingguan", {

    chart: {
      type: 'areaspline'
    },
    title: {
      text: 'Grafik Perminggu'
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
        '<?php echo  implode("','", $tgl_periksa_lab) ?> ',
        '<?php echo  implode("','", $tgl_periksa_rad) ?> '
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
            <?php foreach ($jumlah_pasein_lab_mingguan as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>

      <?php {  ?> {
          name: 'Radiologi',
          data: [
            <?php foreach ($jumlah_pasein_rad_mingguan as $cle => $valeur) { ?>
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