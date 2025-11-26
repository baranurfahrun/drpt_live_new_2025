<?php
defined('BASEPATH') or exit('No direct script access allowed');
if (!function_exists('tte_helper')) {
    function tteCekUser($nik = null)
    {
        $host = config_item("UrlBSSN") . "/api/user/status/" . $nik;
        $username =  config_item("UsernameBSSN");
        $password = config_item("PasswordBSSN");
        $curl = curl_init($host);
        curl_setopt($curl, CURLOPT_URL, $host);
        curl_setopt($curl, CURLOPT_USERPWD, $username . ":" . $password);
        curl_setopt($curl, CURLOPT_SSL_VERIFYPEER, 0);
        curl_setopt($curl, CURLOPT_SSL_VERIFYHOST, 0);
        curl_setopt($curl, CURLOPT_ENCODING, "");
        curl_setopt($curl, CURLOPT_MAXREDIRS, 10);
        curl_setopt($curl, CURLOPT_TIMEOUT, 0);
        curl_setopt($curl, CURLOPT_FOLLOWLOCATION, true);
        curl_setopt($curl, CURLOPT_HTTP_VERSION, CURL_HTTP_VERSION_1_1);
        curl_setopt($curl, CURLOPT_CUSTOMREQUEST, "GET");
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
        $response = curl_exec($curl);
        // print_r(curl_error($curl));
        curl_close($curl);
        return $response;
    }
    function tteSignV2($nik = null, $passphrase = null, $tempFile = null, $tag = '#', $image = false, $imageFile = null)
    {
        $host = config_item("UrlBSSN") . "/api/sign/pdf";
        $username =  config_item("UsernameBSSN");
        $password = config_item("PasswordBSSN");
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
        curl_setopt($curl, CURLOPT_USERPWD, $username . ":" . $password);
        curl_setopt($curl, CURLOPT_ENCODING, "");
        curl_setopt($curl,  CURLOPT_MAXREDIRS, 10);
        curl_setopt($curl,  CURLOPT_TIMEOUT, 0);
        curl_setopt($curl,  CURLOPT_FOLLOWLOCATION, true);
        curl_setopt($curl, CURLOPT_HTTP_VERSION, CURL_HTTP_VERSION_1_1);
        curl_setopt($curl, CURLOPT_CUSTOMREQUEST, "POST");
        curl_setopt($curl, CURLOPT_POSTFIELDS, $fields);
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
        $response = curl_exec($curl);
        if (json_decode($response, true) == null) {
            $responseFile = $response;
        } else {
            if (json_decode($response, true)['status_code'] == "2031") {
                $result = json_decode($response, true);
                $hasil = [
                    "metadata" => [
                        "code" =>  $result['status_code'],
                        "message" =>  $result['error']
                    ],
                    "response" =>  $result['error']
                ];
                $responseFile = json_encode($hasil, true);
            } else  if (json_decode($response, true)['status_code'] == "2011") {
                $result = json_decode($response, true);
                $hasil = [
                    "metadata" => [
                        "code" => $result['status_code'],
                        "message" =>  $result['error']
                    ],
                    "response" =>  $result['error']
                ];
                $responseFile = json_encode($hasil, true);
            } else {
                $responseFile = $response;
            }
        }
        curl_close($curl);
        return  $responseFile;
    }
    function tteSignV1($nik = null, $passphrase = null, $tempFile = null, $image = false, $tampilan = 'invisible', $xAxis = '10', $yAxis = '10', $height = '100', $width = '100', $page = '1', $tag = "#", $imageFile = null)
    {
        $host = config_item("UrlBSSN") . "/api/sign/pdf";
        $username =  config_item("UsernameBSSN");
        $password = config_item("PasswordBSSN");
        $curl = curl_init($host);
        if ($image  == "true") {
            $fields = array(
                'file' => new CurlFile(@$tempFile, 'application/pdf'),
                'nik' => $nik,
                'passphrase' => $passphrase,
                'imageTTD' => new CurlFile(@$imageFile, 'image/png'),
                'tampilan' => 'visible',
                'image' => 'true',
                'linkQR' => 'https://google.com',
                'width' => $width,
                'height' => $height,
                'tag_koordinat' => "#"
                // 'page'=>1,

            );
        } else {
            $fields = array(
                'file' => new CurlFile(@$tempFile, 'application/pdf'),
                'nik' => $nik,
                'passphrase' => $passphrase,
                'tampilan' => $tampilan,
                'linkQR' => 'https://google.com',
                'image' => 'false',
                'width' => $width,
                'height' => $height,
                'tag_koordinat' => $tag,
                "page"=>1
            );
        }
        curl_setopt($curl, CURLOPT_URL, $host);
        curl_setopt($curl, CURLOPT_SSL_VERIFYPEER, 0);
        curl_setopt($curl, CURLOPT_SSL_VERIFYHOST, 0);
        curl_setopt($curl, CURLOPT_USERPWD, $username . ":" . $password);
        curl_setopt($curl, CURLOPT_ENCODING, "");
        curl_setopt($curl,  CURLOPT_MAXREDIRS, 10);
        curl_setopt($curl,  CURLOPT_TIMEOUT, 0);
        curl_setopt($curl,  CURLOPT_FOLLOWLOCATION, true);
        curl_setopt($curl, CURLOPT_HTTP_VERSION, CURL_HTTP_VERSION_1_1);
        curl_setopt($curl, CURLOPT_CUSTOMREQUEST, "POST");
        curl_setopt($curl, CURLOPT_POSTFIELDS, $fields);
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
        $response = curl_exec($curl);
        if (json_decode($response, true) == null) {
            $responseFile = $response;
        } else {
            if (json_decode($response, true)['status_code'] == "2031") {
                $result = json_decode($response, true);
                $hasil = [
                    "metadata" => [
                        "code" =>  $result['status_code'],
                        "message" =>  $result['error']
                    ],
                    "response" =>  $result['error']
                ];
                $responseFile = json_encode($hasil, true);
            } else  if (json_decode($response, true)['status_code'] == "2011") {
                $result = json_decode($response, true);
                $hasil = [
                    "metadata" => [
                        "code" => $result['status_code'],
                        "message" =>  $result['error']
                    ],
                    "response" =>  $result['error']
                ];
                $responseFile = json_encode($hasil, true);
            } else {
                $responseFile = $response;
            }
        }
        curl_close($curl);
        return  $responseFile;
    }
    function tteSignVerify($nik = null, $passphrase = null, $tempFile = null, $tag = '#', $image = false, $imageFile = null)
    {
        $host = config_item("UrlBSSN") . "/tte/api/sign/verify";
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
        $header = ['token' => 'Authorization:Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxLCJlbWFpbCI6ImFkbWluQHR0ZS5jb20iLCJuaWsiOiIxMTIyMzMxMTIyMzM1NTIyIiwiZXhwIjoxNzA1NDk3NTU0fQ.rvhu_9pZEzIodlv20iHSra0KYDt0szNRAsQr_kuTnsQ'];
        curl_setopt($curl, CURLOPT_URL, $host);
        curl_setopt($curl, CURLOPT_SSL_VERIFYPEER, 0);
        curl_setopt($curl, CURLOPT_SSL_VERIFYHOST, 0);
        curl_setopt($curl, CURLOPT_HTTPHEADER, $header);
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
            $responseFile = $response;
        }
        curl_close($curl);
        return  $responseFile;
    }
    function tteSign($nik = null, $passphrase = null, $tempFile = null, $tag = '#', $image = false, $imageFile = null)
    {
        $host = config_item("UrlBSSN") . "/api/sign/pdf";
        $curl = curl_init($host);
        $username =  config_item("UsernameBSSN");
        $password = config_item("PasswordBSSN");
        if ($image == true) {
            $fields = array(
                'file' => new CurlFile(@$tempFile, 'application/pdf'),
                'imageTTD' => new CurlFile(@$imageFile, 'image/png'),
                'nik' => $nik, 'passphrase' => $passphrase,
                'tampilan' => 'visible',
                'image' => 'true',
                'linkQR' => 'https://google.com',
                'width' => '200',
                'height' => '150',
                'tag_koordinat' => $tag
            );
        } else {
            $fields = array(
                'file' => new CurlFile(@$tempFile, 'application/pdf'),
                'nik' => $nik, 'passphrase' => $passphrase,
                'tampilan' => 'invisible',
                'image' => 'false',
                'linkQR' => 'https://google.com',
                'width' => '200',
                'height' => '150',
                'tag_koordinat' => $tag
            );
        }
        curl_setopt($curl, CURLOPT_URL, $host);
        curl_setopt($curl, CURLOPT_SSL_VERIFYPEER, 0);
        curl_setopt($curl, CURLOPT_SSL_VERIFYHOST, 0);
        curl_setopt($curl, CURLOPT_USERPWD, $username . ":" . $password);
        curl_setopt($curl, CURLOPT_ENCODING, "");
        curl_setopt($curl,  CURLOPT_MAXREDIRS, 10);
        curl_setopt($curl,  CURLOPT_TIMEOUT, 0);
        curl_setopt($curl,  CURLOPT_FOLLOWLOCATION, true);

        curl_setopt($curl, CURLOPT_HTTP_VERSION, CURL_HTTP_VERSION_1_1);
        curl_setopt($curl, CURLOPT_CUSTOMREQUEST, "POST");

        curl_setopt($curl, CURLOPT_POSTFIELDS, $fields);
        // curl_setopt ( $curl, CURLOPT_HTTPHEADER, $arrheader );
        // curl_setopt($curl, CURLOPT_FAILONERROR, true);
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
        // curl_setopt($curl, CURLOPT_BINARYTRANSFER, true);
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
        } else if (json_decode($response, true)['status_code'] == 2031) {
            $result = json_decode($response, true);
            $hasil = [
                "metadata" => [
                    "code" => json_decode($response, true)['status_code'],
                    "message" => "Error"
                ],
                "response" =>   $result['error']
            ];
            $responseFile = json_encode($hasil, true);
        } else {
            $responseFile = $response;
        }
        curl_close($curl);
        return  $responseFile;
    }
}
