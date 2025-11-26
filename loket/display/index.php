<?php require_once('../template/header.php'); ?>
<div id="content" style="padding:0 30px !important; height: 100vh;  background: transparent !important;">
    <div class="row mb-4">
        <div class="col-3">
            <img src="../assets/logos.png" alt="" width="80" height="80">
        </div>
        <div class="col-6 text-center mt-2">
            <div style="font-weight: bold; font-size: 34px;" class="text-color-primary">LOKET RSU MALAHAYATI BIREUEN
            </div>
            <div style="font-weight: bold; font-size:20px;"><span id="tanggal"></span><span style="margin-left: 20px;" id="jam"></span></div>
        </div>
        <div class="col-3 mt-2 text-end">
            <div style=" font-weight: bold; font-size: 20px;" class="text-color-primary">
            </div>
        </div>
    </div>
    <div class="row mt-2">
        <div class="col-md-8 pb-3 ">
            <div class="card mx-auto p-2 shadow-lg rounded-4" style="min-height: 685px;">
                <video id="vid" class="my-auto" loop="true" autoplay="autoplay" muted controls style="width:100%">
                    <source src="../assets/ProfilRSUM.mp4" type="video/mp4">
                  <source src="../assets/mobilejkn.mp4" type="video/mp4">
                </video>
                <div id="display" style="display:none; text-align:center; width:100%; margin : 70px auto 0 auto;"></div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="row">
                <div class="col-12 mb-3">
                    <div class="card mx-auto my-auto shadow rounded-4">
                        <div class="card-body text-center">
                            <h5 class="card-title" style="font-size: 40px; font-weight: bold;">NOMOR ANTRIAN</h5>
                            <h6 class="card-subtitle mb-2 text-muted" style="font-size: 30px; font-weight: bold;">
                            </h6>
                            <p class="loket1 card-text" style="font-size: 140px; line-height:215px; font-weight: bold;" id="nomor">0</p>
                            <h6 class="sisa card-subtitle mb-2 text-muted" style="font-size: 30px; font-weight: bold;">SILAHKAN KE

                            </h6>
                            <!-- <h5 class="card-title" style="font-size: 40px; font-weight: bold;">PENDAFTARAN</h5> -->
                        </div>
                    </div>
                </div>
                <div class="col-12 mb-3">
                    <div class="card mx-auto my-auto shadow rounded-4">
                        <div class="card-body text-center">
                            <h5 class="card-title" style="font-size: 50px; font-weight: bold;">LOKET</h5>
                            <h6 class="card-subtitle mb-2 text-muted" style="font-size: 30px; font-weight: bold;">
                            </h6>
                            <p class="loket1 card-text" style="font-size: 200px; line-height:215px; font-weight: bold;" id="loket">0</p>

                            <!-- <h5 class="card-title" style="font-size: 40px; font-weight: bold;">PENDAFTARAN</h5> -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <?php require_once('../template/footer.php'); ?>

    </body>
    <script src="../libs/responsivevoice/responsivevoice.js"></script>
    <script>
        var display = document.getElementById("display");
        var vid = document.getElementById("vid");
        let index = 0;
        var videos = [
            "../assets/ProfilRSUM.mp4",
            "../assets/mobilejkn.mp4",
        ];
        $(document).ready(function() {
            vid.volume = 0.5
            playArray(index, vid, videos)
            intervalPanggil = setInterval(function() {
                getDataPanggilLoket();
            }, 1000)

        });

        let data = 0;

        function playArray(index, ele, array) {
            console.log('play')
            if (index >= array.length) {
                index = 0;
            }
            ele.src = array[index];
            ele.load();
            ele.play();
            ele.addEventListener('ended', function() {
                index++
                console.log(index)
                playArray(index, ele, array);
            }, false);
        }

        function getDataPanggilLoket() {
            var res = "";
            $.ajax({
                type: "get",
                url: "../controller.php?get_panggil_loket",
                dataType: "json",
                success: function(result) {
                    console.log(result)
                    if (result.message == "success" && result.data != null) {
                        setDataLoket(result.data[0])
                    }
                },
                error(e) {
                    console.log(e.responseText);
                }
            });
        }

        function delDataPanggilLoket(id) {
            var res = "";
            $.ajax({
                type: "get",
                url: "../controller.php?del_panggil_loket&id=" + id,
                dataType: "json",
                success: function(result) {
                    if (result.message == "success") {
                        console.log(result.data);
                    }
                },
                error(e) {
                    console.log(e.responseText);
                }
            });
        }

        function setDataLoket(val) {
            clearInterval(intervalPanggil)
            if (val) {
                document.getElementById('nomor').innerHTML = val.kode + "-" + val.nomor
                document.getElementById('loket').innerHTML = val.loket
                let html = '<h5 class="card-title" style="font-size: 60px; font-weight: bold; line-height:60px">Perhatian!</h5><span class="card-title mb-4" style="font-size: 45px; font-weight: bold; line-height:30px">Untuk antrian loket dengan nomor:</span><div class="row mx-4 my-4 justify-content-center ">' +
                    '<div class="col-4 card py-4 px-1 shadow rounded-4" >' +
                    '            <span style="font-size:60px; font-weight:bold">' + val.kode + '-' + val.nomor + '</span>' +
                    '</div></div>';
                html += '<span class="card-title mb-4" style="font-size: 45px; font-weight: bold;">Silahkan ke loket ' + val.loket + '</span><div class="row mx-4 my-4">';
                display.innerHTML = html;
                display.style.display = 'block'
                vid.pause();
                vid.style.display = 'none'
                console.log("ID", val.id)
                delDataPanggilLoket(val.id)
                var audio = new Audio('../assets/airport_bell.wav');
                audio.play();
                setTimeout(() => {
                    play(val.kode, val.nomor, val.loket);
                }, 3000);

                setTimeout(() => {
                    display.style.display = 'none'
                    vid.style.display = 'block'
                    vid.play();
                    intervalPanggil = setInterval(function() {
                        getDataPanggilLoket();
                    }, 1000)
                }, 12000);
            }

        }

        function play(kode, nomor, loket) {
            responsiveVoice.speak("mohon perhatian, untuk antrian loket dengan nomor, " +
                kode + "," +
                nomor + ", silahkan ke loket " + loket, "Indonesian Female", {
                    pitch: 1,
                    rate: 0.9,
                    volume: 5
                });
        }
    </script>

    </html>