<?php include('../conf/config.php'); ?>
<section class="content">
    <div class="container-fluid">
        <!-- Small boxes (Stat box) -->
        <div class="col-md-12">
            <div class="card card-info shadow-none">
                <div class="card-header">
                    <h3 class="card-title">Indikator Harian</h3>
                    <div class="card-tools">
                        <button type="button" class="btn btn-tool" data-card-widget="collapse">
                            <i class="fas fa-minus"></i>
                        </button>
                    </div>
                    <!-- /.card-tools -->
                </div>
                <!-- /.card-header -->
                <div class="card-body">
                    <div class="row">
                        <div class="col-sm-2 border-right">
                            <div class="description-block">
                                <?php
                                $result = mysqli_query($koneksi, "SELECT reg_periksa.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,concat(kamar_inap.kd_kamar,' ',bangsal.nm_bangsal) as kamar,
                                    kamar_inap.tgl_masuk,if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar) 
                                    as tgl_keluar,kamar_inap.lama,kamar_inap.stts_pulang,SUM(kamar_inap.lama) jumlah
                                    from kamar_inap 
                                    inner join reg_periksa 
                                    inner join pasien inner join kamar 
                                    inner join bangsal
                                    on kamar_inap.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis
                                    
                                    and kamar_inap.kd_kamar=kamar.kd_kamar and kamar.kd_bangsal=bangsal.kd_bangsal
                                    where kamar_inap.tgl_keluar=current_date()");
                                // $jumlah_bor = mysqli_num_rows($total_bor);
                                $tot = 0;
                                while ($row = mysqli_fetch_array($result)) {
                                    $tot = $row[8];
                                }
                                $bor = ($tot / (336 * 1)) * 0.01;
                                // $persentasi = round(($tot / (336 * 1)) * 0.01);
                                ?>

                                <!-- Perhitungan BOR 	: (780.0/(336 X 3)) X 100%	=		77.38 %	 -->
                                <h5 class="description-header"><?php echo  number_format($bor, 6, '.', ''); ?>%</h5>
                                <!-- <h5 class="description-header"><?php echo  number_format($bor) ?>%</h5> -->
                                <!-- <h5 class="description-header"><?php echo "$persentasi %"; ?></h5> -->


                                <span class="description-text"><i class="fa fa-users" aria-hidden="true"></i>&nbsp;BOR</span>
                            </div>
                            <!-- /.description-block -->
                        </div>
                        <!-- /.col -->
                        <div class="col-sm-2 border-right">
                            <div class="description-block">
                                <?php
                                $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,concat(kamar_inap.kd_kamar,' ',bangsal.nm_bangsal) as kamar,
                                kamar_inap.tgl_masuk,if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar) 
                                as tgl_keluar,kamar_inap.lama,kamar_inap.stts_pulang,SUM(kamar_inap.lama) jumlah
                                from kamar_inap 
                                inner join reg_periksa 
                                inner join pasien inner join kamar 
                                inner join bangsal
                                on kamar_inap.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis
                                and kamar_inap.kd_kamar=kamar.kd_kamar and kamar.kd_bangsal=bangsal.kd_bangsal
                                where kamar_inap.tgl_keluar=current_date()");
                                // $jumlah_bor = mysqli_num_rows($total_bor);
                                $totav = 0;
                                while ($row = mysqli_fetch_array($result)) {
                                    $totav = $row[8];
                                }
                                $avlos = ($totav / (336 * 1)) * 0.01;
                                // $persentasi = round(($tot / (336 * 1)) * 0.01);
                                ?>
                                 <!-- Perhitungan BOR 	: (780.0/(336 X 3)) X 100%	=		77.38 %	 -->
                                 <h5 class="description-header"><?php echo  number_format($avlos, 6, '.', ''); ?>%</h5>
                                <!-- <h5 class="description-header"><?php echo  number_format($avlos) ?>%</h5> -->
                                <!-- <h5 class="description-header"><?php echo "$persentasi %"; ?></h5> -->

                                <!-- <h5 class="description-header"><?php echo "$avlos" ?></h5> -->
                                <span class="description-text"><i class="fa fa-users" aria-hidden="true"></i>&nbsp;AVLOS</span>
                            </div>
                            <!-- /.description-block -->
                        </div>
                        <div class="col-sm-2 border-right">
                            <div class="description-block">
                                <?php
                                $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                                    FROM reg_periksa,dokter,poliklinik
                                    WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                                    AND reg_periksa.stts='Sudah' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='ANA' AND reg_periksa.tgl_registrasi=CURDATE()");
                                $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                                ?>
                                <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                                <span class="description-text"><i class="fa fa-users" aria-hidden="true"></i>&nbsp;TOI</span>
                            </div>
                            <!-- /.description-block -->
                        </div>
                        <div class="col-sm-2 border-right">
                            <div class="description-block">
                                <?php
                                $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                                    FROM reg_periksa,dokter,poliklinik
                                    WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                                    AND reg_periksa.stts='Sudah' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='ANA' AND reg_periksa.tgl_registrasi=CURDATE()");
                                $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                                ?>
                                <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                                <span class="description-text"><i class="fa fa-users" aria-hidden="true"></i>&nbsp;BTO</span>
                            </div>
                            <!-- /.description-block -->
                        </div>
                        <div class="col-sm-2 border-right">
                            <div class="description-block">
                                <?php
                                $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                                    FROM reg_periksa,dokter,poliklinik
                                    WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                                    AND reg_periksa.stts='Sudah' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='ANA' AND reg_periksa.tgl_registrasi=CURDATE()");
                                $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                                ?>
                                <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                                <span class="description-text"><i class="fa fa-users" aria-hidden="true"></i>&nbsp;NDR</span>
                            </div>
                            <!-- /.description-block -->
                        </div>
                        <div class="col-sm-2 border-right">
                            <div class="description-block">
                                <?php
                                $total_pasien_dilayani_hari_ini = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,nm_poli,reg_periksa.status_lanjut,reg_periksa.stts
                                    FROM reg_periksa,dokter,poliklinik
                                    WHERE reg_periksa.kd_dokter=dokter.kd_dokter AND poliklinik.kd_poli=reg_periksa.kd_poli
                                    AND reg_periksa.stts='Sudah' AND reg_periksa.status_lanjut='Ralan' AND reg_periksa.kd_poli='ANA' AND reg_periksa.tgl_registrasi=CURDATE()");
                                $jumlah = mysqli_num_rows($total_pasien_dilayani_hari_ini);
                                ?>
                                <h5 class="description-header"><?php echo "$jumlah" ?></h5>
                                <span class="description-text"><i class="fa fa-users" aria-hidden="true"></i>&nbsp;GDR</span>
                            </div>
                            <!-- /.description-block -->
                        </div>

                        <span class="count_top "><i class="fa fa-stethoscope "></i> <small><?php echo "Update " . date("Y/m/d")  ?></small></span>
                    </div>
                    <!-- /.card-body -->
                </div>
                <!-- /.card -->
            </div>
        </div>


        <!-- AREA CHART cara bayar -->
        <div class="col-md-12">
            <figure class="highcharts-figure">
                <div id="cara-bayar"></div>
            </figure>
        </div>
        <div class="col-md-12 ">
            <figure class="highcharts-figure">
                <div id="golongan"></div>
            </figure>
        </div>
        <div class="col-md-12 ">
            <figure class="highcharts-figure">
                <div id="satuan"></div>
            </figure>
        </div>
        <div class="col-md-12 ">
            <figure class="highcharts-figure">
                <div id="jabatan"></div>
            </figure>
        </div>

        <div class="col-md-12">
            <div class="card">
                <div class="card-header">
                    <h3 class="card-title">10 Penyakit Terbesar Rawat Jalan</h3>
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
                            $result = mysqli_query($koneksi, "SELECT diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit,diagnosa_pasien.`status`, COUNT(DISTINCT diagnosa_pasien.no_rawat) AS Jumlah
                        FROM diagnosa_pasien,penyakit
                        WHERE diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit AND diagnosa_pasien.`status`='Ralan' AND diagnosa_pasien.kd_penyakit 
                        IN ('K04.1','E11','I10','K30','K02.1','I11','H60','A15.0','H61.2','E14.9')
                        GROUP BY diagnosa_pasien.kd_penyakit DESC");
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
        <div class="col-md-12">
            <figure class="highcharts-figure">
                <div id="container"></div>
            </figure>
        </div>

        <!-- /.card -->
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">
                    <h3 class="card-title">10 Penyakit Terbesar Rawat Rawat Inap</h3>
                </div>
                <!-- /.card-header -->
                <div class="card-body">
                    <table id="ranap" class="table table-bordered table-striped">
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
                            $result = mysqli_query($koneksi, "SELECT diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit,diagnosa_pasien.`status`, COUNT(DISTINCT diagnosa_pasien.no_rawat) AS Jumlah
                        FROM diagnosa_pasien,penyakit
                        WHERE diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit AND diagnosa_pasien.`status`='Ranap' AND diagnosa_pasien.kd_penyakit 
                        IN ('A09.9','K30','A01','J06.9','E11','I10','K29.1','J40','A90','A15.0')
                        GROUP BY diagnosa_pasien.kd_penyakit DESC");
                            //urutab tampilkan hasil query dari database
                            while ($APATONG = mysqli_fetch_array($result)) {
                                $no++;
                            ?>
                                <tr>
                                    <td><?php echo $no; ?></td>
                                    <td><?php echo $APATONG['kd_penyakit']; ?></td>
                                    <td><?php echo $APATONG['nm_penyakit']; ?></td>
                                    <td> <?php echo $APATONG['status']; ?></td>
                                    <td> <?php echo $APATONG['Jumlah']; ?></td>

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

        <div class="col-md-12">
            <figure class="highcharts-figure">
                <div id="ranapchart"></div>
            </figure>
        </div>
    </div>
</section>


<!-- qwery ralan -->
<?php
$k041 = mysqli_query($koneksi, "SELECT diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit,diagnosa_pasien.`status`
FROM diagnosa_pasien,penyakit
WHERE diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit AND diagnosa_pasien.`status`='Ralan' AND diagnosa_pasien.kd_penyakit 
IN ('K04.1')");
$jumlah_k041 = mysqli_num_rows($k041);
?>

<?php
$E11 = mysqli_query($koneksi, "SELECT diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit,diagnosa_pasien.`status`
FROM diagnosa_pasien,penyakit
WHERE diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit AND diagnosa_pasien.`status`='Ralan' AND diagnosa_pasien.kd_penyakit 
IN ('E11')");
$jumlah_E11 = mysqli_num_rows($E11);
?>

<?php
$I10 = mysqli_query($koneksi, "SELECT diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit,diagnosa_pasien.`status`
FROM diagnosa_pasien,penyakit
WHERE diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit AND diagnosa_pasien.`status`='Ralan' AND diagnosa_pasien.kd_penyakit 
IN ('I10')");
$jumlah_I10 = mysqli_num_rows($I10);
?>

<?php
$K30 = mysqli_query($koneksi, "SELECT diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit,diagnosa_pasien.`status`
FROM diagnosa_pasien,penyakit
WHERE diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit AND diagnosa_pasien.`status`='Ralan' AND diagnosa_pasien.kd_penyakit 
IN ('K30')");
$jumlah_K30 = mysqli_num_rows($K30);
?>

<?php
$K021 = mysqli_query($koneksi, "SELECT diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit,diagnosa_pasien.`status`
FROM diagnosa_pasien,penyakit
WHERE diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit AND diagnosa_pasien.`status`='Ralan' AND diagnosa_pasien.kd_penyakit 
IN ('K02.1')");
$jumlah_K021 = mysqli_num_rows($K021);
?>

<?php
$I11 = mysqli_query($koneksi, "SELECT diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit,diagnosa_pasien.`status`
FROM diagnosa_pasien,penyakit
WHERE diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit AND diagnosa_pasien.`status`='Ralan' AND diagnosa_pasien.kd_penyakit 
IN ('I11')");
$jumlah_I11 = mysqli_num_rows($I11);
?>

<?php
$H60 = mysqli_query($koneksi, "SELECT diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit,diagnosa_pasien.`status`
FROM diagnosa_pasien,penyakit
WHERE diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit AND diagnosa_pasien.`status`='Ralan' AND diagnosa_pasien.kd_penyakit 
IN ('H60')");
$jumlah_H60 = mysqli_num_rows($H60);
?>

<?php
$A150 = mysqli_query($koneksi, "SELECT diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit,diagnosa_pasien.`status`
FROM diagnosa_pasien,penyakit
WHERE diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit AND diagnosa_pasien.`status`='Ralan' AND diagnosa_pasien.kd_penyakit 
IN ('A15.0')");
$jumlah_A150 = mysqli_num_rows($A150);
?>

<?php
$H612 = mysqli_query($koneksi, "SELECT diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit,diagnosa_pasien.`status`
FROM diagnosa_pasien,penyakit
WHERE diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit AND diagnosa_pasien.`status`='Ralan' AND diagnosa_pasien.kd_penyakit 
IN ('H61.2')");
$jumlah_H612 = mysqli_num_rows($H612);
?>

<?php
$E149 = mysqli_query($koneksi, "SELECT diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit,diagnosa_pasien.`status`
FROM diagnosa_pasien,penyakit
WHERE diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit AND diagnosa_pasien.`status`='Ralan' AND diagnosa_pasien.kd_penyakit 
IN ('E14.9')");
$jumlah_E149 = mysqli_num_rows($E149);
?>

<!-- qwery ranp -->
<?php
$A099 = mysqli_query($koneksi, "SELECT diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit,diagnosa_pasien.`status`
FROM diagnosa_pasien,penyakit
WHERE diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit AND diagnosa_pasien.`status`='Ranap' AND diagnosa_pasien.kd_penyakit 
IN ('A09.9')");
$jumlah_A099 = mysqli_num_rows($A099);
?>

<?php
$K30 = mysqli_query($koneksi, "SELECT diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit,diagnosa_pasien.`status`
FROM diagnosa_pasien,penyakit
WHERE diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit AND diagnosa_pasien.`status`='Ranap' AND diagnosa_pasien.kd_penyakit 
IN ('K30')");
$jumlah_K30 = mysqli_num_rows($K30);
?>

<?php
$A01 = mysqli_query($koneksi, "SELECT diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit,diagnosa_pasien.`status`
FROM diagnosa_pasien,penyakit
WHERE diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit AND diagnosa_pasien.`status`='Ranap' AND diagnosa_pasien.kd_penyakit 
IN ('A01')");
$jumlah_A01 = mysqli_num_rows($A01);
?>

<?php
$J069 = mysqli_query($koneksi, "SELECT diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit,diagnosa_pasien.`status`
FROM diagnosa_pasien,penyakit
WHERE diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit AND diagnosa_pasien.`status`='Ranap' AND diagnosa_pasien.kd_penyakit 
IN ('J06.9')");
$jumlah_J069 = mysqli_num_rows($J069);
?>

<?php
$E11 = mysqli_query($koneksi, "SELECT diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit,diagnosa_pasien.`status`
FROM diagnosa_pasien,penyakit
WHERE diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit AND diagnosa_pasien.`status`='Ranap' AND diagnosa_pasien.kd_penyakit 
IN ('E11')");
$jumlah_E11 = mysqli_num_rows($E11);
?>

<?php
$I10 = mysqli_query($koneksi, "SELECT diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit,diagnosa_pasien.`status`
FROM diagnosa_pasien,penyakit
WHERE diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit AND diagnosa_pasien.`status`='Ranap' AND diagnosa_pasien.kd_penyakit 
IN ('I10')");
$jumlah_I10 = mysqli_num_rows($I10);
?>

<?php
$K291 = mysqli_query($koneksi, "SELECT diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit,diagnosa_pasien.`status`
FROM diagnosa_pasien,penyakit
WHERE diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit AND diagnosa_pasien.`status`='Ranap' AND diagnosa_pasien.kd_penyakit 
IN ('K29.1')");
$jumlah_K291 = mysqli_num_rows($K291);
?>

<?php
$J40 = mysqli_query($koneksi, "SELECT diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit,diagnosa_pasien.`status`
FROM diagnosa_pasien,penyakit
WHERE diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit AND diagnosa_pasien.`status`='Ranap' AND diagnosa_pasien.kd_penyakit 
IN ('J40')");
$jumlah_J40 = mysqli_num_rows($J40);
?>

<?php
$A90 = mysqli_query($koneksi, "SELECT diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit,diagnosa_pasien.`status`
FROM diagnosa_pasien,penyakit
WHERE diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit AND diagnosa_pasien.`status`='Ranap' AND diagnosa_pasien.kd_penyakit 
IN ('A90')");
$jumlah_A90 = mysqli_num_rows($A90);
?>

<?php
$A150 = mysqli_query($koneksi, "SELECT diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit,diagnosa_pasien.`status`
FROM diagnosa_pasien,penyakit
WHERE diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit AND diagnosa_pasien.`status`='Ranap' AND diagnosa_pasien.kd_penyakit 
IN ('A15.0')");
$jumlah_A150 = mysqli_num_rows($A150);
?>

<!-- /chart cara bayar BPJS-->
<?php
$jumlah_pasien_bayar = array();
$result = mysqli_query($koneksi, "SELECT reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.kd_pj,COUNT(reg_periksa.kd_pj)
FROM reg_periksa WHERE stts='sudah' AND  kd_pj='BPJ' and tgl_registrasi=CURDATE()");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
    $jumlah_pasien_bayar[] = $row[0];
    $status_lanjut[] = $row[1];
    $kd_pj[] = $row[2];
    $jumlah_bayar_BPJS[] = $row[3];
}
?>
<!-- /chart cara bayar Umum-->
<?php
$jumlah_pasien_bayar = array();
$result = mysqli_query($koneksi, "SELECT reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.kd_pj,COUNT(reg_periksa.kd_pj)
FROM reg_periksa WHERE stts='sudah' AND  kd_pj='A09' AND status_bayar='Sudah Bayar' and tgl_registrasi=CURDATE()");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
    $jumlah_pasien_bayar[] = $row[0];
    $status_lanjut[] = $row[1];
    $kd_pj[] = $row[2];
    $jumlah_bayar_umum[] = $row[3];
}
?>

<!-- /chart cara bayar LAINNya-->
<?php
$jumlah_pasien_bayar = array();
$result = mysqli_query($koneksi, "SELECT reg_periksa.stts,reg_periksa.status_lanjut,reg_periksa.kd_pj,COUNT(reg_periksa.kd_pj)
FROM reg_periksa WHERE stts='sudah' AND  kd_pj='A86' AND status_bayar='Sudah Bayar' and tgl_registrasi=CURDATE()");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
    $jumlah_pasien_bayar[] = $row[0];
    $status_lanjut[] = $row[1];
    $kd_pj[] = $row[2];
    $jumlah_bayar_lain[] = $row[3];
}
?>

<?php
$jumlah_golongan = array();
$result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,pasien_tni.golongan_tni,golongan_tni.nama_golongan, COUNT(reg_periksa.no_rkm_medis) AS jumlah
FROM reg_periksa,pasien_tni,golongan_tni
WHERE reg_periksa.no_rkm_medis=pasien_tni.no_rkm_medis AND golongan_tni.id=pasien_tni.golongan_tni and tgl_registrasi=CURDATE()
GROUP BY golongan_tni.nama_golongan;");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
    $nama_golongan[] = $row[2];
    $jumlah_golongan[] = $row[3];
}
?>

<?php
$jumlah_satuan = array();
$result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,pasien_tni.satuan_tni,satuan_tni.nama_satuan, COUNT(reg_periksa.no_rkm_medis) AS jumlah
FROM reg_periksa,pasien_tni,satuan_tni
WHERE reg_periksa.no_rkm_medis=pasien_tni.no_rkm_medis AND satuan_tni.id=pasien_tni.satuan_tni and tgl_registrasi=CURDATE()
GROUP BY satuan_tni.nama_satuan");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
    $nama_satuan[] = $row[2];
    $jumlah_satuan[] = $row[3];
}
?>

<?php
$jumlah_jabatan = array();
$result = mysqli_query($koneksi, "SELECT reg_periksa.tgl_registrasi,pasien_tni.jabatan_tni,jabatan_tni.nama_jabatan, COUNT(reg_periksa.no_rkm_medis) AS jumlah
FROM reg_periksa,pasien_tni,jabatan_tni
WHERE reg_periksa.no_rkm_medis=pasien_tni.no_rkm_medis AND jabatan_tni.id=pasien_tni.jabatan_tni and tgl_registrasi=CURDATE()
GROUP BY jabatan_tni.nama_jabatan");
//urutab tampilkan hasil query dari database
while ($row = mysqli_fetch_array($result)) {
    $nama_jabatan[] = $row[2];
    $jumlah_jabatan[] = $row[3];
}
?>`
<script>
    Highcharts.chart('container', {
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: 'Grafik 10 Penyakit Terbesar Rawat Jalan',
            align: 'left'
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
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            name: 'Jumlah',
            colorByPoint: true,
            data: [{
                name: 'Necrosis of pulp (K04.1)',
                y: <?php echo "$jumlah_k041" ?>,
                sliced: true,
                selected: true
            }, {
                name: 'Non-insulin-dependent diabetes mellitus',
                y: <?php echo "$jumlah_E11" ?>
            }, {
                name: 'Essential (primary) hypertension',
                y: <?php echo "$jumlah_I10" ?>
            }, {
                name: 'Functional dyspepsia',
                y: <?php echo "$jumlah_K30" ?>
            }, {
                name: 'Caries of dentine',
                y: <?php echo "$jumlah_K021" ?>
            }, {
                name: 'Hypertensive heart disease',
                y: <?php echo "$jumlah_I11" ?>
            }, {
                name: 'Other acute gastritis',
                y: <?php echo "$jumlah_K291" ?>
            }, {
                name: 'Bronchitis, not specified as acute or chronic',
                y: <?php echo "$jumlah_J40" ?>
            }, {
                name: 'Impacted cerumen',
                y: <?php echo "$jumlah_H612" ?>
            }, {
                name: 'Unspecified diabetes mellitus without complications',
                y: <?php echo "$jumlah_E149" ?>
            }]
        }]
    });
</script>

<script>
    Highcharts.chart('ranapchart', {
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: 'Grafik 10 Penyakit Terbesar Rawat Ranap',
            align: 'left'
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
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            name: 'Jumlah',
            colorByPoint: true,
            data: [{
                name: 'Gastroenteritis and colitis of unspecified origin',
                y: <?php echo "$jumlah_A099" ?>,
                sliced: true,
                selected: true
            }, {
                name: 'Functional dyspepsia',
                y: <?php echo "$jumlah_K30" ?>
            }, {
                name: 'Typhoid and paratyphoid fevers',
                y: <?php echo "$jumlah_A01" ?>
            }, {
                name: 'Acute upper respiratory infection, unspecified',
                y: <?php echo "$jumlah_J069" ?>
            }, {
                name: 'Non-insulin-dependent diabetes mellitus',
                y: <?php echo "$jumlah_E11" ?>
            }, {
                name: 'Essential (primary) hypertension',
                y: <?php echo "$jumlah_I10" ?>
            }, {
                name: 'Other acute gastritis',
                y: <?php echo "$jumlah_K291" ?>
            }, {
                name: 'Bronchitis, not specified as acute or chronic',
                y: <?php echo "$jumlah_J40" ?>
            }, {
                name: 'Dengue fever [classical dengue]',
                y: <?php echo "$jumlah_A90" ?>
            }, {
                name: 'Tuberculosis of lung, confirmed by sputum microscopy with or without culture',
                y: <?php echo "$jumlah_A150" ?>
            }]
        }]
    });
</script>

<script>
    Highcharts.chart('cara-bayar', {
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: 'Jenis Bayar'
        },
        subtitle: {
        text:
        '<?php echo "Update " . date("Y/m/d")  ?>'
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
                y: <?php echo  implode("','", $jumlah_bayar_BPJS) ?>,
                sliced: true,
                selected: true
            }, {
                name: 'UMUM',
                y: <?php echo  implode("','", $jumlah_bayar_umum) ?>
            }, {
                name: 'Lainnya',
                y: <?php echo  implode("','", $jumlah_bayar_lain) ?>
            }]

        }]
    });
</script>
<script>
    Highcharts.chart('jenis_kunjungan', {
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: 'Jenis Bayar'
        },subtitle: {
        text:
        '<?php echo "Update " . date("Y/m/d")  ?>'
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
                y: <?php echo  implode("','", $jumlah_bayar_BPJS) ?>,
                sliced: true,
                selected: true
            }, {
                name: 'UMUM',
                y: <?php echo  implode("','", $jumlah_bayar_umum) ?>
            }, {
                name: 'Lainnya',
                y: <?php echo  implode("','", $jumlah_bayar_lain) ?>
            }]

        }]
    });
</script>

<script>
    Highcharts.chart("golongan", {

        chart: {
            type: 'areaspline'
        },

        title: {
            text: 'Kunjungan TNI Berdasarkan Golongan'
        },
        subtitle: {
        text:
        '<?php echo "Update " . date("Y/m/d")  ?>'
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
                '<?php echo  implode("','", $nama_golongan) ?> '
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
            valueSuffix: ' Orang'
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
                    name: 'Jumlah',
                    data: [
                        <?php foreach ($jumlah_golongan as $cle => $valeur) { ?>
                            <?php echo ($valeur); ?>,
                        <?php } ?>
                    ]
                },
            <?php } ?>
        ]
    });
</script>

<script>
    Highcharts.chart("satuan", {

        chart: {
            type: 'areaspline'
        },

        title: {
            text: 'Kunjungan TNI Berdasarkan Satuan'
        },
        subtitle: {
        text:
        '<?php echo "Update " . date("Y/m/d")  ?>'
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
                '<?php echo  implode("','", $nama_satuan) ?> '
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
            valueSuffix: ' Orang'
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
                    name: 'Jumlah',
                    data: [
                        <?php foreach ($jumlah_satuan as $cle => $valeur) { ?>
                            <?php echo ($valeur); ?>,
                        <?php } ?>
                    ]
                },
            <?php } ?>
        ]
    });
</script>

<script>
    Highcharts.chart("jabatan", {

        chart: {
            type: 'areaspline'
        },

        title: {
            text: 'Kunjungan TNI Berdasarkan Jabatan'
        },
        subtitle: {
        text:
        '<?php echo "Update " . date("Y/m/d")  ?>'
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
                '<?php echo  implode("','", $nama_jabatan) ?> '
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
            valueSuffix: ' Orang'
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
                    name: 'Jumlah',
                    data: [
                        <?php foreach ($jumlah_jabatan as $cle => $valeur) { ?>
                            <?php echo ($valeur); ?>,
                        <?php } ?>
                    ]
                },
            <?php } ?>
        ]
    });
</script>