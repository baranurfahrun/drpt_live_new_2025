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

      <!-- Main content -->
      <section class="content">
        <div class="container-fluid">
          <div class="row">
            <div class="col-md-6">
              <!-- AREA CHART hari ini & bulanan kolom -->
              <figure class="highcharts-figure">
                <div id="chart_ralan"></div>
                <p class="highcharts-description">
                </p>
              </figure>
              <!-- AREA CHART bulanan -->
              <figure class="highcharts-figure">
                <div id="chart_bulanan"></div>
                <p class="highcharts-description">
                </p>
              </figure>
              <!-- AREA CHART perbandingan bulanan -->
              <figure class="highcharts-figure">
                <div id="chart_perbandingan_bulanan"></div>
                <p class="highcharts-description">
                </p>
              </figure>

              <!-- AREA CHART dokter now -->
              <figure class="highcharts-figure">
                <div id="chart_dokter_now"></div>
                <p class="highcharts-description">
                </p>
              </figure>
            </div>

            <div class="col-md-6">
              <!-- AREA CHART mingguan -->
              <figure class="highcharts-figure">
                <div id="chart_ralan1"></div>
                <p class="highcharts-description">
                </p>
              </figure>

              <!-- AREA CHART tahunan-->

              <figure class="highcharts-figure">
                <div id="chart_tahunan"></div>
                <p class="highcharts-description">
                </p>
              </figure>

              <!-- AREA CHART perbandingan tahunan-->
              <figure class="highcharts-figure">
                <div id="chart_perbandingan_tahunan"></div>
                <p class="highcharts-description">
                </p>
              </figure>

              <!-- tombol pencarian dokter -->
              <div class="col-md-10 offset-md-1">
                <div class="row">
                  <div class="col-6">
                    <div class="form-group">
                      <label>Pilih dokter</label>
                      <select name="nm_dokter" class="custom-select" style="width: 100%;">
                        <?php
                        $result = mysqli_query($koneksi, "SELECT reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.kd_poli,poliklinik.nm_poli 
                          AS Poli 
                          FROM reg_periksa,poliklinik,dokter
                          WHERE status_lanjut='Ralan' AND stts='Sudah' AND reg_periksa.kd_dokter
                          and poliklinik.kd_poli=reg_periksa.kd_poli AND reg_periksa.kd_dokter=dokter.kd_dokter
                          GROUP BY reg_periksa.kd_dokter ");
                        //urutab tampilkan hasil query dari database
                        while ($data = mysqli_fetch_array($result)) {
                        ?>
                          <option value="<?= $data['nm_dokter']; ?>"><?php echo $data['nm_dokter']; ?></option>
                        <?php
                        }
                        ?>
                      </select>
                    </div>
                  </div>

                  <div class="col-3">
                    <!-- select -->
                    <div class="form-group">
                      <label>Kunjungan</label>
                      <select class="custom-select">
                        <option>Mingguan</option>
                        <option>Bulanan</option>
                        <option>Tahunan</option>
                      </select>
                    </div>
                  </div>
                </div>
              </div>

              <!-- Chart_cari_dokter -->
              <figure class="highcharts-figure">
                <div id="chart_cari_dokter"></div>
                <p class="highcharts-description">
                </p>
              </figure>
            </div>


            <div class="card-body">
              <div class="chart">
                <canvas id="lineChart" style="min-height: 250px; height: 250px; max-height: 250px; max-width: 100%;"></canvas>
              </div>
            </div>
          </div>

        </div>

        <!-- /.content -->
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


<!-- qwery Status ralan sudah periksa hari ini -->
<?php
$tglregistrasi = array();
$kdpoli = array();
$norekam = array();
$result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,  poliklinik.nm_poli, count(reg_periksa.kd_poli) jumlah 
FROM reg_periksa, poliklinik 
WHERE reg_periksa.stts='Sudah' and reg_periksa.status_lanjut ='ralan' and reg_periksa.tgl_registrasi = curdate() and reg_periksa.kd_poli=poliklinik.kd_poli 
GROUP BY reg_periksa.kd_poli ");
//tampilkan hasil query
while ($row = mysqli_fetch_array($result)) {

  $tglregistrasi[] = $row[0];
  $kdpoli[] = $row[1];
  $norekam[] = $row[2];
  // $character = implode(",", $names);
  // var_dump("tglregs".$tglregistrasi);

}
?>

<!-- qwery Mingguan -->
<?php
$jumlah_pasien_uro = array();
$result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis)AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi 
                                      AS Tahun_bulan_Tgl
                                      FROM reg_periksa,poliklinik
                                      WHERE status_lanjut='Ralan' AND stts='Sudah' AND poliklinik.kd_poli in ('URO',ANA)
                                      and poliklinik.kd_poli=reg_periksa.kd_poli  
                                      GROUP BY YEARWEEK(tgl_registrasi) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $jumlah_pasien_uro[] = $row[0];
  $kdpolim_uro[] = $row[1];
  $nm_poli_uro[] = $row[2];
  $tahun_bulan_tgl_mingguan[] = $row[3];
}
?>

<!-- QWERY BULANAN -->
<?php
$jumlah_bulan_anak = array();
$result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis)AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%M %Y') 
                                      AS Tahun_bulan_Tgl
                                      FROM reg_periksa,poliklinik
                                      WHERE status_lanjut='Ralan' AND stts='Sudah' AND poliklinik.kd_poli='ANA'
                                      and poliklinik.kd_poli=reg_periksa.kd_poli  
                                      GROUP BY MONTH(tgl_registrasi) DESC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $jumlah_bulan_anak[] = $row[0];
  $kdpolim_bulan_anak[] = $row[1];
  $nm_poli_bulan_anak[] = $row[2];
  $bulanan[] = $row[3];
}
?>



<!-- qwery TAHUNAN-->
<?php
$jumlah_tahun_anak = array();
$result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis)AS Jumlah_pasien,reg_periksa.kd_poli,poliklinik.nm_poli,DATE_FORMAT(tgl_registrasi,'%Y') 
                                      AS Tahun_bulan_Tgl
                                      FROM reg_periksa,poliklinik
                                      WHERE status_lanjut='Ralan' AND stts='Sudah' AND poliklinik.kd_poli='ANA'
                                      and poliklinik.kd_poli=reg_periksa.kd_poli  
                                      GROUP BY YEAR(tgl_registrasi) ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $jumlah_tahun_anak[] = $row[0];
  $kdpolim_tahun_anak[] = $row[1];
  $nm_poli_tahun_anak[] = $row[2];
  $tahunan[] = $row[3];
}
?>


<!-- qwery DOKTER now-->
<?php
$jumlah_pasien_now_dokter = array();
$result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis)AS Jumlah_pasien,reg_periksa.kd_dokter,dokter.nm_dokter
,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi 
                                    AS Tahun_bulan_Tgl
                                    FROM reg_periksa,poliklinik,dokter
                                    WHERE status_lanjut='Ralan' AND stts='Sudah' AND reg_periksa.tgl_registrasi = curdate() 
                                    and poliklinik.kd_poli=reg_periksa.kd_poli AND reg_periksa.kd_dokter=dokter.kd_dokter
                                    GROUP BY reg_periksa.kd_poli ");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $jumlah_pasien_now_dokter[] = $row[0];
  $kddokter_now[] = $row[1];
  $nmdokter_now[] = $row[2];
  $kdpoli_now[] = $row[3];
  $nm_poli_now[] = $row[4];
  $dokternow[] = $row[5];
}
?>

<!-- qwery cari DOKTER-->
<?php
$jumlah_pasien_cari_dokter = array();
$result = mysqli_query($koneksi, "SELECT COUNT(reg_periksa.no_rkm_medis)AS Jumlah_pasien,reg_periksa.kd_dokter,dokter.nm_dokter
,reg_periksa.kd_poli,poliklinik.nm_poli,tgl_registrasi 
                                    AS Tahun_bulan_Tgl
                                    FROM reg_periksa,poliklinik,dokter
                                    WHERE status_lanjut='Ralan' AND stts='Sudah' AND reg_periksa.kd_dokter='D000016'
                                    and poliklinik.kd_poli=reg_periksa.kd_poli AND reg_periksa.kd_dokter=dokter.kd_dokter
                                    GROUP BY reg_periksa.kd_poli ORDER BY tgl_registrasi ASC");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
  $jumlah_pasien_cari_dokter[] = $row[0];
  $kddokter_cari[] = $row[1];
  $nmdokter_cari[] = $row[2];
  $kdpoli_cari[] = $row[3];
  $nm_poli_cari[] = $row[4];
  $tgldoktercari[] = $row[5];
}
?>

<script>
  // chart hari ini  
  Highcharts.chart('chart_ralan', {
    chart: {
      type: 'area'
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


  //chart Mingguan
  Highcharts.chart("chart_ralan1", {

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
        fillOpacity: 0.5
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
      type: 'spline'
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
      text: 'Grafik Kunjungan Perbandingan Dokter'
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
      categories: ['<?php echo  implode("','", $tgldoktercari) ?>'],
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
          name: 'dr. A. Weri Sompa, Sp.S.,M.Kes',
          data: [
            <?php foreach ($jumlah_pasien_cari_dokter as $cle => $valeur) { ?>
              <?php echo ($valeur); ?>,
            <?php } ?>
          ]
        },
      <?php } ?>
    ]
  });
  //
</script>