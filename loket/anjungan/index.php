<?php require_once("../template/header.php"); ?>
<div id="content" class="container-xxl px-4" style="height: 100vh;  background: transparent !important;">
    <div class="row mb-4">
        <div class="col-3">
            <img src="../assets/logos.png" alt="" width="80" height="80">
        </div>
        <div class="col-6 text-center mt-2">
            <div style="font-weight: bold; font-size: 24px;" class="text-color-primary">ANJUNGAN RSU MALAHAYATI BIREUEN
            </div>
            <div style="font-weight: bold; font-size:20px;"><span id="tanggal"></span><span style="margin-left: 20px;" id="jam"></span></div>
        </div>
        <div class="col-3 mt-2 text-end">
            <div style=" font-weight: bold; font-size: 20px;" class="text-color-primary">
            </div>
        </div>
    </div>
    <div class="row mt-4 pt-2">
        <div class="col-md-4 ">
            <span id="kd_poli" style="display: none;"></span>
            <span id="kd_dokter" style="display: none;"></span>
            <span id="hari_kerja" style="display: none;"></span>
            <span id="kode" style="display: none;"></span>
            <span id="jumlah" style="display: none;"></span>
            <div id="list_dokter" style="background-color:white !important;display:block;height: 79vh !important; overflow:auto; padding:10px;"></div>
        </div>


        <div class="col-md-4 ">
            <div class="row">
                <div class="col-md-12 ">

                    <div class="card mx-auto my-auto shadow-lg rounded-4" style="min-height: 350px;">
                        <div class="card-body text-center">
                            <h5 class="card-title" style="font-size: 40px; font-weight: bold;">SISA KUOTA </h5>
                            <h6 id="nm_poli" class="card-subtitle mb-4 text-muted" style="font-size: 26px; font-weight: bold;">
                                Silahkan pilih poliklinik...
                            </h6>
                            <p id="kuota" class="jum card-text" style="font-size: 90px; font-weight: bold;">0</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-12 ">

                    <div class="card mx-auto my-auto shadow-lg rounded-4 mt-3" style="min-height: 140px;">
                        <div class="card-body text-center">
                            <h5 class="card-title text-muted" style="font-size: 30px; font-weight: bold;">KUOTA HARI INI </h5>
                            <p id="kuotahari" class="jum card-text text-muted" style="font-size: 70px; line-height:50px; font-weight: bold;">0</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-12 ">

                    <div class="card mx-auto my-auto shadow-lg rounded-4 mt-3" style="min-height: 140px;">
                        <div class="card-body text-center">
                            <h5 class="card-title text-muted" style="font-size: 30px; font-weight: bold;">SUDAH REGISTRASI</h5>
                            <p id="regis" class="jum card-text text-muted" style="font-size: 70px; line-height:50px; font-weight: bold;">0</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4 ">
            <div class="card mx-auto my-auto shadow-lg rounded-4" style="min-height: 350px;">
                <div class="card-body text-center">
                    <h5 class="card-title" style="font-size: 40px; font-weight: bold;">AMBIL ANTRIAN</h5>
                    <h6 class="card-subtitle mb-3 text-muted" style="font-size: 30px; font-weight: bold;">NOMOR
                    </h6>
                    <p class="nomor card-text" style="font-size: 90px; font-weight: bold;">-</p>

                    <!-- <button class="btn btn-primary px-4" onclick="refreshNomor()">Re</button> -->
                </div>
            </div>
            <div id="cetak" style="display:none" class="card mx-auto my-3 shadow-lg rounded-4">
                <!-- <div class="card-body text-center"> -->
                <button class="btn btn-primary px-4" style="min-height: 135px; border-radius:16px;font-weight:bold; font-size:26px" onclick="cetak()">CETAK</button>
                <!-- </div> -->
            </div>
        </div>
    </div>
    <!-- <div id="footer" class="fixed-bottom px-5 d-none d-md-block">

        <div class="footer px-2 py-2 mx-auto">
            <div class="d-flex">
                <div class="mx-auto">
                    <span><i class="fa fa-globe"></i> www.rstelagabunda.com</span>
                    <span style="margin-left: 50px;"><i class="fab fa-instagram"></i> rs.telagabunda</span>
                    <span style="margin-left: 50px; font-size:17px"><i class="fa fa-phone-square"></i> Pendaftaran
                        Poli:
                        0853 6115 0515</span>
                    <span style="margin-left: 50px; font-size:17px"><i class="fa fa-phone-square"></i> UGD: 0852
                        7540
                        2682</span>
                </div>
            </div>
        </div>
    </div> -->
    <?php require_once('../template/footer.php'); ?>
</div>
<div id="divcetak" class="mx-auto" style="display:none;text-align:center; padding:10px; height:200px; width:200px; left:calc(50% - 100px); background:white">
    <div style="font-weight:bold; font-size:18px; color:black;">PELAYANAN LOKET</div>
    <div style="padding-bottom: 5px; border-bottom:2px solid #000; color:black; font-size:14px">Rumah Sakit Umum Malahayati</div>
    <div style="padding-bottom: 25px; font-size:12px; color:black; ">Antrian Loket Tanggal</div>
    <div class="nomor" style="padding-bottom: 30px;font-weight:bold; color:black; line-height:40px; font-size:50px">-</div>
    <div class="sisa" style="padding-top: 5px; color:black; border-top:2px solid #000; font-size:10px; margin-bottom:50px">Terima kasih atas kunjungan Anda, silahkam menunggu sampai dipanggil oleh petugas</div>
    <div class="sisa" style="padding-top: 5px; color:black; font-size:8px; margin-bottom:50px">--</div>

</div>
</body>
<script>
    let lop = null
    $(document).ready(function() {
        getJadwal()
        lop = setInterval(() => {
            // console.log('getsisa')
            getSisa($('#hari_kerja').html(), $('#kd_dokter').html(), $('#kd_poli').html())
            getData($('#kd_poli').html(), $('#kd_dokter').html(), $('#kode').html());
        }, 1000);
    });

    function pilih(e, nm_poli, kuota, kd_poli, kd_dokter, hari_kerja, kode) {
        let el = document.querySelectorAll('.mylist')
        el.forEach(element => {
            element.classList.remove('pilihya')
        });
        if (e.classList.contains('pilihya')) {
            e.classList.add('pilihtidak')
            e.classList.remove('pilihya')
        } else {
            e.classList.add('pilihya')
            e.classList.remove('pilihtidak')
        }
        document.getElementById("nm_poli").innerHTML = nm_poli
        document.getElementById("kuota").innerHTML = kuota
        document.getElementById("kd_poli").innerHTML = kd_poli
        document.getElementById("kd_dokter").innerHTML = kd_dokter
        document.getElementById("hari_kerja").innerHTML = hari_kerja
        document.getElementById("kode").innerHTML = kode
        document.getElementById("kuotahari").innerHTML = kuota
        console.log(nm_poli, kuota)
        // getSisa(hari_kerja, kd_dokter, kd_poli)
        // getData(kd_poli, kode);
        let sisa = document.getElementById("kuota").innerHTML
        if (sisa > 0) {
            $("#cetak").show()
        } else {
            $("#cetak").show()
            $(".nomor").html("-");
        }
        console.log(sisa)
    }

    function getData(kd_poli, kd_dokter, kode) {
        console.log('jum', kode)
        var res = "";
        $.ajax({
            type: "get",
            url: "../controller.php?get_ambil_antrian_loket&kd_poli=" + kd_poli + "&kd_dokter=" + kd_dokter,
            dataType: "json",
            success: function(result) {
                if (result.message == "success" && result.data.length > 0) {
                    let jum = parseInt(result.data[0].jumlah) + 1
                    $("#jumlah").html(jum);
                    let sisa = document.getElementById("kuota").innerHTML
                    if (sisa > 0) {
                        $(".nomor").html(kode + "-" + jum);
                    } else {
                        $(".nomor").html("-");
                    }


                } else {
                    if (kode.length > 0) {
                        $("#jumlah").html('1');
                        $(".nomor").html(kode + '-1');
                    } else {
                        $("#jumlah").html('');
                        $(".nomor").html('-');
                    }
                }
            },
            error(e) {
                console.log(e.responseText);
            }
        });
    }

    function getSisa(hari_kerja, kd_dokter, kd_poli) {
        var res = "";
        console.log("sisa", "../controller.php?get_sisa_kuota&hari_kerja=" + hari_kerja + "&kd_dokter=" + kd_dokter + "&kd_poli=" + kd_poli)
        $.ajax({
            type: "get",
            url: "../controller.php?get_sisa_kuota&hari_kerja=" + hari_kerja + "&kd_dokter=" + kd_dokter + "&kd_poli=" + kd_poli,
            dataType: "json",
            success: function(result) {
                // console.log(result)
                if (result.message == "success" && result.data.length > 0) {
                    console.log(result)
                    $("#kuota").html(result.data[0].sisa);
                    $("#regis").html(result.data[0].jum);
                    // console.log(result.data[0].sisa)
                    if (result.data[0].sisa > 0) {
                        $("#cetak").show()
                    }
                    if (result.data[0].sisa < 1) {
                        $("#cetak").hide()
                        $(".nomor").html("-");
                        $("#kuota").html("0");
                    }
                }
            },
            error(e) {
                console.log(e.responseText);
            }
        });
    }

    function getJadwal() {
        const d = new Date();
        let day = d.getDay();
        if (day == 0) {
            day = 'AKHAD';
        }
        if (day == 1) {
            day = 'SENIN';
        }
        if (day == 2) {
            day = 'SELASA';
        }
        if (day == 3) {
            day = 'RABU';
        }
        if (day == 4) {
            day = 'KAMIS';
        }
        if (day == 5) {
            day = 'JUMAT';
        }
        if (day == 6) {
            day = 'SABTU';
        }
        var res = "";
        $.ajax({
            type: "get",
            url: "../controller.php?get_jadwal&hari_kerja=" + day,
            dataType: "json",
            success: function(result) {
                console.log(result)
                if (result.message == "success" && result.data != null) {
                    result.data.forEach(element => {
                        // console.log(element)
                        res += '<div class="card mx-auto my-auto mb-2 mylist rounded-4" onclick="pilih(this, \'' + element.nm_poli + '\', \'' + element.kuota + '\', \'' + element.kd_poli + '\', \'' + element.kd_dokter + '\', \'' + element.hari_kerja + '\', \'' + element.kode + '\')">' +
                            '<div class="card-body py-2">' +
                            '<h5 class="card-title" style="font-size: 20px; font-weight: bold; ">' + element.nm_poli + '</h5>' +
                            '<h6 class="card-subtitle mb-2 text-muted" style=" line-height:20px; font-size: 16px; font-weight: bold;">' +
                            element.nm_dokter + '  ' +
                            // element.jam_mulai + '-' + element.jam_selesai +
                            '</h6>' +
                            '</div>' +
                            '</div>'
                    })
                    // console.log(res)
                    document.getElementById("list_dokter").innerHTML = res
                }
            },
            error(e) {
                console.log(e.responseText);
            }
        });
    }

    function cetak() {
        clearInterval(lop)
        document.getElementById('divcetak').style.display = "block";
        window.print();
        document.getElementById('divcetak').style.display = "none";
        var data = JSON.stringify({
            kd_poli: $('#kd_poli').html(),
            kd_dokter: $('#kd_dokter').html(),
            jumlah: $('#jumlah').html(),
            kode: $('#kode').html()
        });
        $.ajax({
            type: "post",
            url: "../controller.php?post_ambil_antrian_loket",
            data: data,
            dataType: 'json',
            success: function(result) {
                // console.log(result)
                if (result.message == "success") {
                    // getSisa($('#hari_kerja').html(), $('#kd_dokter').html(), $('#kd_poli').html())
                    // getData($('#kd_poli').html(), $('#kode').html())
                    lop = setInterval(() => {
                        // console.log('getsisa')
                        getSisa($('#hari_kerja').html(), $('#kd_dokter').html(), $('#kd_poli').html())
                        getData($('#kd_poli').html(), $('#kd_dokter').html(), $('#kode').html())
                    }, 500);
                } else {

                }
            },
            error(e) {
                console.log(e.responseText);
            }
        });
    }
</script>

</html>