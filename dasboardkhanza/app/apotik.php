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
          <div class="row">
            <div class="mx-auto" style="width: 200px;">
              &nbsp;
            </div>
            <div class="col-md-12">
              <div class="card">
                <div class="card-header">
                  <h3 class="card-title">DARURAT STOK GUDANG OBAT</h3>
                </div>
                <!-- /.card-header -->
                <div class="card-body">
                  <table id="laporan_gudang_obat" class="table table-bordered table-striped">
                    <thead>
                      <tr>
                        <th>NO</th>
                        <th>KODE BARANG</th>
                        <th>NAMA OBAT</th>
                        <th>SATUAN</th>
                        <th>JENIS</th>
                        <th>STOK MINIMAL</th>
                        <th>STOK SAAT INI</th>
                      </tr>
                    </thead>
                    <tbody>
                      <?php
                      $no = 0;
                      $result = mysqli_query($koneksi, "SELECT 
                        databarang.kode_brng, 
                        databarang.nama_brng,
                        kodesatuan.satuan,
                        jenis.nama,
                        databarang.stokminimal,
                        gudangbarang.stok AS stok_saat_ini
                        FROM 
                            databarang
                            INNER JOIN kodesatuan ON databarang.kode_sat = kodesatuan.kode_sat
                            INNER JOIN jenis ON databarang.kdjns = jenis.kdjns
                            LEFT JOIN gudangbarang ON databarang.kode_brng = gudangbarang.kode_brng
                        WHERE 
                            databarang.status = '1'
                            AND gudangbarang.kd_bangsal = 'B0028'
                        ORDER BY 
                            databarang.nama_brng");
                      //urutab tampilkan hasil query dari database
                      while ($row = mysqli_fetch_array($result)) {
                        $no++;
                        ?>
                        <tr>
                          <td>
                            <?php echo $no; ?>
                          </td>
                          <td>
                            <?php echo $row['kode_brng']; ?>
                          </td>
                          <td>
                            <?php echo $row['nama_brng']; ?>
                          </td>
                          <td>
                            <?php echo $row['satuan']; ?>
                          </td>
                          <td>
                            <?php echo $row['nama']; ?>
                          </td>
                          <td>
                            <?php echo $row['stokminimal']; ?>
                          </td>
                          <td>
                            <?php echo $row['stok_saat_ini']; ?>
                          </td>
                        </tr>
                      <?php } ?>
                    </tbody>
                    <tfoot>
                      <tr>
                        <th>NO</th>
                        <th>KODE BARANG</th>
                        <th>NAMA OBAT</th>
                        <th>SATUAN</th>
                        <th>JENIS</th>
                        <th>STOK MINIMAL</th>
                        <th>STOK SAAT INI</th>
                      </tr>
                    </tfoot>
                  </table>
                </div>
                <!-- /.card-body -->
              </div>

              <div class="row">
                <div class="mx-auto" style="width: 200px;">
                  &nbsp;
                </div>
                <div class="col-md-12">
                  <div class="card">
                    <div class="card-header">
                      <h3 class="card-title">DARURAT STOK APOTIK TERPADU</h3>
                    </div>
                    <!-- /.card-header -->
                    <div class="card-body">
                      <table id="laporan_apotik_terbaru" class="table table-bordered table-striped">
                        <thead>
                          <tr>
                            <th>NO</th>
                            <th>KODE BARANG</th>
                            <th>NAMA OBAT</th>
                            <th>SATUAN</th>
                            <th>JENIS</th>
                            <th>STOK MINIMAL</th>
                            <th>STOK SAAT INI</th>
                          </tr>
                        </thead>
                        <tbody>
                          <?php
                          $no = 0;
                          $resultx = mysqli_query($koneksi, "SELECT 
                          databarang.kode_brng, 
                          databarang.nama_brng,
                          kodesatuan.satuan,
                          jenis.nama,
                          databarang.stokminimal,
                          gudangbarang.stok AS stok_saat_ini
                         FROM 
                          databarang
                         INNER JOIN kodesatuan ON databarang.kode_sat = kodesatuan.kode_sat
                         INNER JOIN jenis ON databarang.kdjns = jenis.kdjns
                         LEFT JOIN gudangbarang ON databarang.kode_brng = gudangbarang.kode_brng
                         WHERE 
                          databarang.status = '1' AND gudangbarang.kd_bangsal = 'B0098'
                         ORDER BY 
                          databarang.nama_brng");
                          //urutab tampilkan hasil query dari database
                          while ($row = mysqli_fetch_array($resultx)) {
                            $no++;
                            ?>
                            <tr>
                              <td>
                                <?php echo $no; ?>
                              </td>
                              <td>
                                <?php echo $row['kode_brng']; ?>
                              </td>
                              <td>
                                <?php echo $row['nama_brng']; ?>
                              </td>
                              <td>
                                <?php echo $row['satuan']; ?>
                              </td>
                              <td>
                                <?php echo $row['nama']; ?>
                              </td>
                              <td>
                                <?php echo $row['stokminimal']; ?>
                              </td>
                              <td>
                                <?php echo $row['stok_saat_ini']; ?>
                              </td>
                            </tr>
                          <?php } ?>
                        </tbody>
                        <tfoot>
                          <tr>
                            <th>NO</th>
                            <th>KODE BARANG</th>
                            <th>NAMA OBAT</th>
                            <th>SATUAN</th>
                            <th>JENIS</th>
                            <th>STOK MINIMAL</th>
                            <th>STOK SAAT INI</th>
                          </tr>
                        </tfoot>
                      </table>
                    </div>
                    <!-- /.card-body -->
                  </div>
                </div>
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
                        <table id="obat_dokter" class="table table-bordered table-striped">
                          <thead>
                            <tr>
                              <th>NO</th>
                              <th>NAMA DOKTER</th>
                              <th>NAMA PASIEN</th>
                              <th>OBAT</th>
                              <th>TANGGAL PERAWATAN</th>
                              <th>BIAYA OBAT</th>
                              <th>JUMLAH</th>
                              <th>TOTAL</th>
                            </tr>
                          </thead>
                          <tbody>
                            <?php
                            $no = 0;
                            $resultx = mysqli_query($koneksi, "SELECT dokter.nm_dokter,pasien.nm_pasien,databarang.nama_brng,detail_pemberian_obat.tgl_perawatan,detail_pemberian_obat.biaya_obat, 
                            SUM(detail_pemberian_obat.jml) AS jml,
                            (SUM(detail_pemberian_obat.total)- SUM(detail_pemberian_obat.embalase+detail_pemberian_obat.tuslah)) AS total
                            FROM dokter, pasien, detail_pemberian_obat, databarang, reg_periksa
                            WHERE detail_pemberian_obat.`status`='Ralan' 
                            AND detail_pemberian_obat.tgl_perawatan BETWEEN '2023-01-01' AND '2023-01-02'
                            AND detail_pemberian_obat.kode_brng=databarang.kode_brng 
                            AND reg_periksa.no_rkm_medis=pasien.no_rkm_medis 
                            AND reg_periksa.kd_dokter=dokter.kd_dokter 
                            AND reg_periksa.no_rawat=detail_pemberian_obat.no_rawat
                            GROUP BY detail_pemberian_obat.tgl_perawatan,reg_periksa.kd_dokter,databarang.nama_brng");
                            //urutab tampilkan hasil query dari database
                            while ($row = mysqli_fetch_array($resultx)) {
                              $no++;
                              ?>
                              <tr>
                                <td>
                                  <?php echo $no; ?>
                                </td>
                                <td>
                                  <?php echo $row['nm_dokter']; ?>
                                </td>
                                <td>
                                  <?php echo $row['nm_pasien']; ?>
                                </td>
                                <td>
                                  <?php echo $row['nama_brng']; ?>
                                </td>
                                <td>
                                  <?php echo $row['tgl_perawatan']; ?>
                                </td>
                                <td>
                                  <?php echo $row['biaya_obat']; ?>
                                </td>
                                <td>
                                  <?php echo $row['jml']; ?>
                                </td>
                                <td>
                                  <?php echo $row['total']; ?>
                                </td>
                              </tr>
                            <?php } ?>
                          </tbody>
                          <tfoot>
                            <tr>
                                <th colspan="7" style="text-align:right">Total:</th>
                                <th></th>
                            </tr>
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