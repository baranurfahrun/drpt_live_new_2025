<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <link rel="icon" href="../assets/logo.ico" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RSU MALAHAYATI BIREUEN</title>
    <link rel="stylesheet" href="../libs/bootstrap/bootstrap.css">
    <link rel="stylesheet" href="../libs/bootstrap/bootstrap-icons.css">
    <link rel="stylesheet" href="../libs/fontawesome/fontawesome-all.css">
    <script src="../libs/jquery/jquery-3.5.1.js"></script>
    <script src="../libs/bootstrap/bootstrap.bundle.js"></script>
    <script src="../libs/momentjs/moment.min.js"></script>
    <script src="../libs/momentjs/moment-with-locales.min.js"></script>
    <style>
        .footer {
            border-top-left-radius: 150px;
            border-top-right-radius: 150px;
            font-size: 20px;
            /* font-weight: bold; */
            color: white;
        }


        @media print {

            #back *,
            #content {
                display: none;
            }

            #divcetak,
            #divcetak * {
                display: block;
            }
        }

        .swal2-container.swal2-center>.swal2-popup {
            grid-column: 2;
            grid-row: 2;
            align-self: center;
            justify-self: center;
            width: 90%;
            height: 80vh;
        }

        .swiper {
            width: 100%;
            height: 83vh;
            background: transparent !important;
            padding-left: 10px;
            padding-right: 10px;
        }

        .text-color-primary {
            color: #00aa8e !important;

        }

        .bg-color-primary {
            background: #00aa8e !important;

        }

        .bg-card {
            background: #ECFFFC;
            display: block !important;
        }

        .bg-card .card {
            min-height: 110px;
        }

        .pilihya {
            background: rgb(40, 177, 138) !important;
            border-radius: 19px;
            color: white;
        }

        .pilihya h6 {
            color: #ECFFFC !important;
        }

        .pilihtidak {
            background: white !important;
            border-radius: 19px;
            color: black;
        }

        .pilihya h6 {
            color: #ECFFFC !important;
        }
    </style>
</head>

<script>
    $(document).ready(function() {
        moment.locale('id');
        const today = new Date();
        const tanggal = moment(today).format("dddd Do MMMM YYYY");
        document.getElementById('tanggal').innerHTML = tanggal;

    });

    function startTime() {
        const today = new Date();
        let h = today.getHours();
        let m = today.getMinutes();
        let s = today.getSeconds();
        h = checkTime(h);
        m = checkTime(m);
        s = checkTime(s);
        document.getElementById('jam').innerHTML = h + ":" + m + ":" + s;
        setTimeout(startTime, 1000);
    }

    function checkTime(i) {
        if (i < 10) {
            i = "0" + i
        }; // add zero in front of numbers < 10
        return i;
    }
</script>

<body id="body" onload="startTime()">
    <div id="back" style="background: url('../assets/wavesOpacity.svg'); z-index:-1000; background-size:100%; width: 100%; background-repeat: no-repeat;height: 200px;position: absolute;top: 0;">
    </div>