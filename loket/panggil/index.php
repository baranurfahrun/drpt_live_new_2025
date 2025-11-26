<?php require_once('../template/header2.php'); ?>
<div class="d-flex align-items-center justify-content-center">
    <div id="content" class="align-items-center" style="padding:0 20px !important; ">
        <div class="row mt-2">
            <div class="col-md-12">
                <div class="row">
                    <div class="card">
                        <div class="card-body" style="position:relative">
                            <table>
                                <tbody>
                                    <tr>
                                        <td>
                                            <div class="mb-3">
                                                <label for="loket1">Loket</label>
                                                <select id="loket1" class="form-select" aria-label="Default select example">
                                                    <option value="">Pilih</option>
                                                    <option value="1">1</option>
                                                    <option value="2">2</option>
                                                    <option value="3">3</option>
                                                    <option value="4">4</option>
                                                </select>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="mb-3">
                                                <label for="kode1">Poli</label>
                                                <select id="kode1" class="form-select" aria-label="Default select example">


                                                </select>
                                            </div>
                                        </td>
                                        <td style="width: 15% !important;">
                                            <div class="mb-3">
                                                <label for="nomor1">Nomor</label>
                                                <input min="1" type="number" class="form-control" id="nomor1" placeholder="">
                                            </div>
                                        </td>
                                        <td style="width: 10% !important;">
                                            <button type="button" onclick="clickPanggil1()" class="btpanggil btn btn-block btn-primary mt-4 mb-3">Panggil</button>
                                        </td>
                                        <td style="width: 10px !important;">
                                            <button class="btrefresh btn btn-secondary  mt-2" onclick="refresh1()">
                                                <svg class="mb-1" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-clockwise" viewBox="0 0 16 16">
                                                    <path fill-rule="evenodd" d="M8 3a5 5 0 1 0 4.546 2.914.5.5 0 0 1 .908-.417A6 6 0 1 1 8 2v1z" />
                                                    <path d="M8 4.466V.534a.25.25 0 0 1 .41-.192l2.36 1.966c.12.1.12.284 0 .384L8.41 4.658A.25.25 0 0 1 8 4.466z" />
                                                </svg>
                                            </button>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <span id="info1" style="font-size: 12px; bottom:10px; position:absolute; color:red"></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    // let nomor1 = localStorage.getItem("nomor1");
    // let nomor2 = localStorage.getItem("nomor2");
    $(document).ready(function() {

        intervalPanggil = setInterval(function() {}, 1000)
        getPoli()
        $("#kode1").change(function() {
            if ($("#kode1").val().length > 0 && $("#loket1").val().length > 0) {
                refresh1();
            }
        })
        $("#loket1").change(function() {
            if ($("#kode1").val().length > 0 && $("#loket1").val().length > 0) {
                refresh1();
            }
        })

    });
    let data = 0;

    function refresh1() {
        if (!$("#loket1").val()) {
            $("#info1").html('* Loket belum dipilih')
        } else if (!$('#kode1').val()) {
            $("#info1").html('* Poliklinik belum dipilih')
        } else {
            // $("#info1").html('* Sedang memanggil')
            // $(".btpanggil").attr('disabled', false)
            // $(".btrefresh").attr('disabled', false)
            $.ajax({
                type: "get",
                url: "../controller.php?get_sudah_panggil_loket&loket=" + $("#loket1").val() + "&kode=" + $("#kode1").val(),
                dataType: "json",
                success: function(result) {
                    console.log(result)
                    if (result.message == "success" && result.data != null) {
                        $("#nomor1").val(result.data[0].nomor)
                        // setTimeout(() => {
                        $("#info1").html('')
                        // $(".btpanggil").attr('disabled', true)
                        // $(".btrefresh").attr('disabled', true)
                        $("#info1").html('* Nomor antrian ' + result.data[0].nomor + " sudah dipanggil")
                        // }, 12000);
                    }
                },
                error(e) {
                    console.log(e.responseText);
                }
            });
        }
    }

    function clickPanggil1() {
        if (!$("#loket1").val()) {
            $("#info1").html('* Loket belum dipilih')
        } else if (!$('#kode1').val()) {
            $("#info1").html('* Poliklinik belum dipilih')
        } else if (!$('#nomor1').val()) {
            $("#info1").html('* Nomor tidak boleh kosong')
        } else {
            $("#info1").html('* Sedang memanggil')
            $(".btpanggil").attr('disabled', true)
            $(".btrefresh").attr('disabled', true)
            // localStorage.setItem("nomor1", $("#nomor1").val())
            var data = JSON.stringify({
                loket: $('#loket1').val(),
                kode: $('#kode1').val(),
                nomor: $('#nomor1').val()
            });
            $.ajax({
                type: "post",
                url: "../controller.php?post_panggil_loket",
                data: data,
                dataType: 'json',
                success: function(result) {
                    console.log(result)
                    if (result.message == "success") {
                        console.log('panggil dari loket ' + $('#loket1').val())
                        setTimeout(() => {
                            $("#info1").html('')
                            $(".btpanggil").attr('disabled', false)
                            $(".btrefresh").attr('disabled', false)
                        }, 12000);
                    }
                },
                error(xhr) {
                    console.log(xhr);
                }
            });
        }
    }


    function getPoli() {
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
                    res += "<option value=''>Pilih Loket</option>"
                    result.data.forEach(element => {
                        // console.log(Poliklinik
                        res += '<option value="' + element.kode + '">' + element.nm_poli + '</option>'
                    })
                    // console.log(res)
                    document.getElementById("kode1").innerHTML = res
                }

            },
            error(e) {
                console.log(e.responseText);
            }
        });
    }
</script>

</html>