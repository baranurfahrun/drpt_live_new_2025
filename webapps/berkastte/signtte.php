<?php

include("tte_config.php");
if (GATEWAYAPI == true) {
    $url    = isset($_GET['url']) ? $_GET['url'] : '/';
    $url    = explode("/", $url);
    $header = apache_request_headers();
    $method = $_SERVER['REQUEST_METHOD'];
    if ($method == 'GET') {
    }
    if ($method == 'POST') {
        $konten = trim(file_get_contents("php://input"));
        $decode = json_decode($konten, true);
        $nik = $decode['nik'];
        $tag = $decode['tag'];
        $location = $decode['location'];
        $passphrase = $decode['passphrase'];

        $host = HOSTGATEWAY;
        $url = $host . "signfileV1";
        // echo $url;
        $curl = curl_init($url);
        $myvars = [
            'file' => new CURLFILE($_SERVER['DOCUMENT_ROOT'] . "/webapps/" . $location . "/" . $decode['document'], 'application/pdf'),
            'nik' => $nik,
            'passphrase' => $passphrase,
            'tag' => $tag,
            'image' => 'true',
            'tampilan'=>'visible',
            'xAxis'=>'230', //tinggal d atur dsni bang qr posisinya ya
            'yAxis'=>'200',//tinggal d atur dsni bang qr posisinya ya
            'width'=>'100',
            'height'=>'100',
            'page'=>'1'

            // 'location' => $location, udh sukses yaa w klose ya
            // 'applications' => "simrs"
        ];
        $arrheader = [
            "Content-Type" => "multipart/form-data"
        ];
        // print_r($myvars);
        curl_setopt($curl, CURLOPT_URL, $url);
        curl_setopt($curl, CURLOPT_SSL_VERIFYPEER, 0);
        curl_setopt($curl, CURLOPT_SSL_VERIFYHOST, 0);
        curl_setopt($curl, CURLOPT_ENCODING, "");
        curl_setopt($curl,  CURLOPT_MAXREDIRS, 10);
        curl_setopt($curl,  CURLOPT_TIMEOUT, 0);
        curl_setopt($curl,  CURLOPT_FOLLOWLOCATION, true);
        curl_setopt($curl, CURLOPT_HTTP_VERSION, CURL_HTTP_VERSION_1_1);
        curl_setopt($curl, CURLOPT_CUSTOMREQUEST, "POST");
        curl_setopt($curl, CURLOPT_POSTFIELDS, $myvars);
        curl_setopt($curl, CURLOPT_HTTPHEADER, $arrheader);
        curl_setopt($curl, CURLOPT_FAILONERROR, true);
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
        $response = curl_exec($curl);
         $hasil = json_decode($response, true);

        if (json_decode($response, true)) {
            $hasil = array(
                'metadata' => array(
                    'message' => 'Dokumen Gagal ditanda tangani secara digital, ' . json_decode($response, true)['response'],
                    'code' => 201
                )
            );
            echo json_encode($hasil);
        } else if (json_decode($response, true)['metadata']["code"] == "208") {
            $hasil = array(
                'metadata' => array(
                    'message' => 'Dokumen Gagal ditanda tangani secara digital, ' . json_decode($response, true)['response']['error'],
                    'code' => 201
                )
            );
            echo json_encode($hasil);
        } else {
            $fp = $_SERVER['DOCUMENT_ROOT'] . "/webapps/" . $location . "/" . $decode['document'];
            file_put_contents($fp, $response);
            header("Content-Type: application/json");
            $hasil = array(
                'metadata' => array(
                    'message' => 'Dokumen berhasil ditanda tangani secara digital',
                    'code' => 200,
                    'datetime' => date("Y-m-d H:i:s")
                )
            );
            echo json_encode($hasil);
        }
    }
} else {
    $url    = isset($_GET['url']) ? $_GET['url'] : '/';
    $url    = explode("/", $url);
    $header = apache_request_headers();
    $method = $_SERVER['REQUEST_METHOD'];
    $konten = trim(file_get_contents("php://input"));

    $host = BSREAPI;
    $curl = curl_init($host);
    if ($image == true) {
        $fields = array(
            'file' => new CurlFile(@$tempFile, 'application/pdf'),
            'imageTTD' => new CurlFile(@$imageFile, 'image/png'),
            'nik' => $nik, 'passphrase' => $passphrase,
            'tampilan' => 'visible',
            'image' => 'true',
            'linkQR' => 'https://google.com',
            'width' => '200',
            'height' => '100',
            'tag_koordinat' => $tag
        );
    } else {
        $fields = array(
            'file' => new CurlFile(@$tempFile, 'application/pdf'),
            'nik' => $nik, 'passphrase' => $passphrase,
            'tampilan' => 'visible',
            'image' => 'false',
            'linkQR' => 'https://google.com',
            'width' => '200',
            'height' => '100',
            'tag_koordinat' => $tag
        );
    }
    curl_setopt($curl, CURLOPT_URL, $host);
    curl_setopt($curl, CURLOPT_SSL_VERIFYPEER, 0);
    curl_setopt($curl, CURLOPT_SSL_VERIFYHOST, 0);
    curl_setopt($curl, CURLOPT_USERPWD, USERNAMEBSREAPI . ":" . PASSWORDBSREAPI);
    //curl_setopt($ch, CURLOPT_PROXY, "10.15.3.20:80");
    curl_setopt($curl, CURLOPT_ENCODING, "");
    curl_setopt($curl,  CURLOPT_MAXREDIRS, 10);
    curl_setopt($curl,  CURLOPT_TIMEOUT, 0);
    curl_setopt($curl,  CURLOPT_FOLLOWLOCATION, true);
    curl_setopt($curl, CURLOPT_HTTP_VERSION, CURL_HTTP_VERSION_1_1);
    curl_setopt($curl, CURLOPT_CUSTOMREQUEST, "POST");
    curl_setopt($curl, CURLOPT_POSTFIELDS, $fields);
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
    $response = curl_exec($curl);
    if (json_decode($response, true)['status_code'] == 400) {
        $result = json_decode($response, true);
        $hasil = [
            "metadata" => [
                "code" => 400,
                "message" => explode(":", $result['error'])[0]
            ],
            "response" => explode(":", $result['error'])[1]
        ];
        $responseFile = json_encode($hasil, true);
    } else {
        $fp = $_SERVER['DOCUMENT_ROOT'] . "/webapps/berkastte/" . $decode['document'];
        file_put_contents($fp, $response);
    }
    curl_close($curl);
}
