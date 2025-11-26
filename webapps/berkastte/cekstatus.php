<?php

include("tte_config.php");
if (GATEWAYAPI == true) {
    $url    = isset($_GET['url']) ? $_GET['url'] : '/';
    $nik    = $_GET['nik'];
    $url    = explode("/", $url);
    $header = apache_request_headers();
    $method = $_SERVER['REQUEST_METHOD'];
    if ($method == 'GET') {
        $host = HOSTGATEWAY;
        $url = $host . "user_status/$nik";
        $curl = curl_init($url);
        curl_setopt($curl, CURLOPT_URL, $url);
        curl_setopt($curl, CURLOPT_SSL_VERIFYPEER, 0);
        curl_setopt($curl, CURLOPT_SSL_VERIFYHOST, 0);
        curl_setopt($curl, CURLOPT_ENCODING, "");
        curl_setopt($curl,  CURLOPT_MAXREDIRS, 10);
        curl_setopt($curl,  CURLOPT_TIMEOUT, 0);
        curl_setopt($curl,  CURLOPT_FOLLOWLOCATION, true);
        curl_setopt($curl, CURLOPT_HTTP_VERSION, CURL_HTTP_VERSION_1_1);
        curl_setopt($curl, CURLOPT_CUSTOMREQUEST, "GET");
        curl_setopt($curl, CURLOPT_FAILONERROR, true);
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
        $response = curl_exec($curl);
        echo $response;
    }
}
