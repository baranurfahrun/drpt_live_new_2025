<?php
if (isset($_SERVER['HTTP_ORIGIN'])) {
    header("Access-Control-Allow-Origin: {$_SERVER['HTTP_ORIGIN']}");
    header('Access-Control-Allow-Credentials: true');
    header('Access-Control-Max-Age: 86400');
}
if ($_SERVER['REQUEST_METHOD'] == 'OPTIONS') {

    if (isset($_SERVER['HTTP_ACCESS_CONTROL_REQUEST_METHOD']))
        header("Access-Control-Allow-Methods: GET, POST, OPTIONS");

    if (isset($_SERVER['HTTP_ACCESS_CONTROL_REQUEST_HEADERS']))
        header("Access-Control-Allow-Headers:        {$_SERVER['HTTP_ACCESS_CONTROL_REQUEST_HEADERS']}");

    exit(0);
}

date_default_timezone_set("Asia/Jakarta");
ini_set('max_execution_time', 50000);
ini_set('max_input_time', 50000);
$connInfo = '';
$connMysql = mysqli_connect("IP-SERVER", "USERNAME", '', 'DB_NAME', '3306');
setlocale(LC_ALL, 'id_ID');
if ($connMysql) {
    $connInfo .=  " & MySQL Connection established";
} else {
    $connInfo .= "& MySQL Connection could not be established";
    // die(print_r(sqlsrv_errors(), true));
}
