//=========================================================================
// Menampilkan data rumah sakit
$(document).ready(function () {
    $.ajax({
        url: "app/antrian.php?p=pengaturan",
        type: "GET",
        dataType: "json",
        success: function (data) {
            var email = $("#email");
            email.html(data.email);
            var namars = $("#namars");
            namars.html(data.nama_instansi);
            var namars2 = $("#namars2");
            namars2.html(data.nama_instansi);
            var text = $("#text");
            text.html(data.text);
        },
    });
});

// //==================pengaturan video ===============
const videoPlayer = document.getElementById("myVideo");
const videos = [
    "video/video-profil1.mp4",
    "video/video-profil2.mp4",
    "video/video-profil3.mp4",
];
let currentVideoIndex = 0;
function setInitialVideo() {
    videoPlayer.src = videos[currentVideoIndex];
}

videoPlayer.addEventListener("ended", () => {
    currentVideoIndex = (currentVideoIndex + 1) % videos.length;
    videoPlayer.src = videos[currentVideoIndex];
    videoPlayer.play();
});
videoPlayer.volume = 0.1;
videoPlayer.muted = false;
videoPlayer.poster = "video/poster.jpg";

setInitialVideo();

let isAudioPlaying = false;

function playNextAudio(audioList) {
    if (audioList.length > 0) {
        if (isAudioPlaying)
            return;

        var nextAudio = audioList.shift();
        isAudioPlaying = true;
        nextAudio.play();

        nextAudio.onended = function () {
            isAudioPlaying = false;
            playNextAudio(audioList);
        };
    }
}

function Suara() {
    $.ajax({
        url: "app/antrian.php?p=panggil",
        type: "GET",
        dataType: "json",
        success: function (data) {
            var nomorAntrian = $("#suara");
            nomorAntrian.empty();

            $.each(data, function (index, item) {
                var audioList = [
                    new Audio("audio/bell.wav"),
                    new Audio("audio/antri.wav"),
                ];

                // Ratusan
                if (item.nomor >= 100 && item.nomor < 1000) {
                    let ratusan = Math.floor(item.nomor / 100) * 100;
                    audioList.push(new Audio("audio/" + ratusan + ".wav"));

                    let puluhanDanSatuan = item.nomor % 100;

                    if (puluhanDanSatuan >= 1 && puluhanDanSatuan <= 20) {
                        audioList.push(new Audio("audio/" + puluhanDanSatuan + ".wav"));
                    } else if (puluhanDanSatuan === 0 || puluhanDanSatuan % 10 === 0) {
                        audioList.push(new Audio("audio/" + puluhanDanSatuan + ".wav"));
                    } else {
                        let puluhan = Math.floor(puluhanDanSatuan / 10) * 10;
                        let satuan = puluhanDanSatuan % 10;

                        audioList.push(new Audio("audio/" + puluhan + ".wav"));
                        if (satuan > 0) {
                            audioList.push(new Audio("audio/" + satuan + ".wav"));
                        }
                    }
                }
                // Nomor antrian 1-99
                else if (item.nomor >= 1 && item.nomor <= 99) {
                    if (
                            (item.nomor >= 1 && item.nomor <= 20) ||
                            item.nomor === 30 || item.nomor === 40 || item.nomor === 50 ||
                            item.nomor === 60 || item.nomor === 70 || item.nomor === 80 ||
                            item.nomor === 90
                            ) {
                        audioList.push(new Audio("audio/" + item.nomor + ".wav"));
                    } else if (item.nomor >= 21 && item.nomor <= 99) {
                        let puluhan = Math.floor(item.nomor / 10) * 10;
                        let satuan = item.nomor % 10;
                        1

                        audioList.push(new Audio("audio/" + puluhan + ".wav"));
                        if (satuan > 0) {
                            audioList.push(new Audio("audio/" + satuan + ".wav"));
                        }
                    }
                }

                audioList.push(new Audio("audio/masuk.wav"));
                audioList.push(new Audio("audio/" + item.loket.toLowerCase() + ".wav"));

                playNextAudio(audioList);
            });
        },
        error: function (xhr, status, error) {
            console.error("Error saat mengambil data antrian:", error);
        }
    });

    $.ajax({
        url: "app/antrian.php?p=nomor",
        type: "GET",
        dataType: "json",
        success: function (data) {
            var nomorAntrian = $("#nomor");
            nomorAntrian.empty();
            $.each(data, function (index, item) {
                var antrian = $("<div style='font-size: 40px;' class='btn-primary'>" + item.loket + "</div><b style='font-size: 80px;' class='text-danger'>" + item.nomor + "</b></br>");
                nomorAntrian.append(antrian);
            });
        },
        error: function (xhr, status, error) {
            console.error("Error saat mengambil nomor antrian:", error);
        }
    });

    $.ajax({
        url: "app/antrian.php?p=loket1",
        type: "GET",
        dataType: "json",
        success: function (data) {
            var swiperWrapper = $("#datapoli");
            swiperWrapper.empty();
            $.each(data, function (index, item) {
                var varpoli = $("<div class='col-lg-12 col-sm-12 text-center pb-1 pt-1'>" +
                        "<div class='card pt-2 border border-success'>" +
                        "<h4 class='text-danger'>" + item.loket + "</h4><h1><b>" + item.nomor + "</b></h1></div></div>");
                swiperWrapper.append(varpoli);
            });
        },
        error: function (xhr, status, error) {
            console.error("Error saat mengambil data loket:", error);
        }
    });
}


setInterval(Suara, 750);

$(document).ready(function () {
    Suara();
});


function updateClock() {
    var currentTime = new Date();
    var hours = currentTime.getHours();
    var minutes = currentTime.getMinutes();
    var seconds = currentTime.getSeconds();

    hours = (hours < 10 ? "0" : "") + hours;
    minutes = (minutes < 10 ? "0" : "") + minutes;
    seconds = (seconds < 10 ? "0" : "") + seconds;

    var timeString = "" + hours + ":" + minutes + ":" + seconds;

    document.getElementById("clock").innerHTML = timeString;
}

setInterval(updateClock, 1000);
