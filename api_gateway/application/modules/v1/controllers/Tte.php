
<?php
defined('BASEPATH') or exit('No direct script access allowed');
include_once 'General.php';

class Tte extends General
{
    public function __construct()
    {
        parent::__construct();
        $this->load->model('Tte_model');
    }

    function user_status_get($nik)
    {
        $tteRest = json_decode(tteCekUser($nik), true);

        if ($tteRest['status_code'] == 1111) {
            $response = [
                "metadata" => [
                    "code" => $tteRest['status_code'],
                    "status" => $tteRest['status']
                ],
                "response" => [
                    "message" => $tteRest['message']
                ]
            ];
            $status = REST_Controller::HTTP_OK;
        } else if ($tteRest['status_code'] == 1110) {
            $response = [
                "metadata" => [
                    "code" => $tteRest['status_code'],
                    "status" => $tteRest['status']
                ],
                "response" => [
                    "message" => $tteRest['message']
                ]
            ];
            $status = REST_Controller::HTTP_OK;
        } else if ($tteRest['status_code'] == 1006) {
            $response = [
                "metadata" => [
                    "code" => $tteRest['status_code'],
                    "status" => $tteRest['status']
                ],
                "response" => [
                    "message" => $tteRest['message']
                ]
            ];
            $status = REST_Controller::HTTP_OK;
        } else if ($tteRest['status_code'] == 2021) {
            $response = [
                "metadata" => [
                    "code" => $tteRest['status_code'],
                    "status" => $tteRest['status']
                ],
                "response" => [
                    "message" => $tteRest['message']
                ]
            ];
            $status = REST_Controller::HTTP_OK;
        } else {
            $response = [
                "metadata" => [
                    "code" => $tteRest['status_code'],
                    "status" => explode(":", $tteRest['error'])[1]
                ],
                "response" => [
                    "message" => explode(":", $tteRest['error'])[0]
                ]
            ];
            $status = REST_Controller::HTTP_BAD_REQUEST;
        }
        $this->set_response($response, $status);
    }
    function signlog_post()
    {
        $data = json_decode(file_get_contents('php://input'), true);
        $startDate = $data['start_date'];
        $lastDate = $data['last_date'];
        $dataLog = $this->Tte_model->getSelectLog($startDate, $lastDate);
        if ($dataLog->num_rows() > 0) {
            foreach ($dataLog->result_array() as $_dataLog) {
                $listLog[] = [
                    "nik" => $_dataLog['no_ktp'],
                    "tanggal" => $_dataLog['tanggal'],
                    "no_rawat" => $_dataLog['no_rawat'],
                    "kode" => $_dataLog['kode_berkas'],
                    "nama_berkas" => $_dataLog['nama_berkas'],
                    "status_code" => $_dataLog['status_code'],
                    "lokasi_file" => $_dataLog['lokasi_file'],
                    "status" => $_dataLog['status']
                ];
            }
            $response = [
                "metadata" => [
                    "code" => 200,
                    "status" => "Suksess"
                ],
                "response" => [
                    "list" => $listLog
                ]
            ];
            $status = REST_Controller::HTTP_OK;
        } else {
            $response = [
                "metadata" => [
                    "code" => 201,
                    "status" => "Failed"
                ],
                "response" => [
                    "message" => "Failed"
                ]
            ];
            $status = REST_Controller::HTTP_CREATED;
        }
        $this->set_response($response, $status);
    }
    function signfile_post()
    {
        $data = $this->input->post();
        if (empty($data['nik'])) {
        } else if (empty($data['passphrase'])) {
        } else if (empty($data['location'])) {
        } else if (empty($data['image'])) {
        } else if (empty($data['tag'])) {
        } else {


            $tempimageTTD = $_FILES['imageTTD']['tmp_name'];
            $tempFile = $_FILES['file']['tmp_name'];
            $fileName = $_FILES['file']['name'];
            $fileType = $_FILES['file']['type'];
            $fileSize = $_FILES['file']['size'];
            $NameimageTTE = $this->Tte_model->getDataImageTTE($data['nik'])->row()->sign_image;
            $imageTTE = $_SERVER['DOCUMENT_ROOT'] . "/api_gateway/resources/image_tte/".$NameimageTTE;
            $response = tteSign($data['nik'],  $data['passphrase'], $tempFile, $data['tag'], $data['image'], $imageTTE);
            switch ($data['applications']) {
                case "simrs":
                    $fp = FCPATH . "/resources/path_simrs/" . $data['location'] . "/" . $fileName;
                    file_put_contents($fp, $response);
                    break;
            }

            if (json_decode($response, true)['metadata']['code'] == 400) {
                $in['no_ktp'] = $data['nik'];
                $in['tanggal'] = date("Y-m-d H:i:s");
                $in['status'] = "Gagal";
                $in['status_code'] = "400 [" . json_decode($response, true)['response'] . "]";
                $in['lokasi_file'] = $data['location'] . "/" . $fileName;
                $in['nama_berkas'] = $fileName;
                $in['applications'] = $data['applications'];
                $this->Tte_model->saveData('log_berkas_tte', $in);
            } else {
                $in['no_ktp'] = $data['nik'];
                $in['tanggal'] = date("Y-m-d H:i:s");
                $in['status'] = "Sukses";
                $in['status_code'] = 200;
                $in['lokasi_file'] = $data['location'] . "/" . $fileName;
                $in['nama_berkas'] = $fileName;
                $in['applications'] = $data['applications'];
                $this->Tte_model->saveData('log_berkas_tte', $in);
            }

            echo $response;
        }
    }
    function signfilewithimage_post()
    {
        $data = $this->input->post();
        $tempFile = $_FILES['file']['tmp_name'];
        $tempimageTTD = $_FILES['imageTTD']['tmp_name'];
        $fileName = $_FILES['file']['name'];
        $fileType = $_FILES['file']['type'];
        $fileSize = $_FILES['file']['size'];
        echo tteSignV1($data['nik'],  $data['passphrase'], $tempFile, $data['image']);
        //echo tteSignV1($data['nik'],  $data['passphrase'], $tempFile, $data['image'],$data['tampilan'], $tempimageTTD);
    }
    function signfileV1_post()
    {
        $data = $this->input->post();
        $tempFile = $_FILES['file']['tmp_name'];
        $fileName = $_FILES['file']['name'];
        $fileType = $_FILES['file']['type'];
        $fileSize = $_FILES['file']['size'];
        $NameimageTTE = $this->Tte_model->getDataImageTTE($data['nik'])->row()->sign_image;
        $imageTTE = $_SERVER['DOCUMENT_ROOT'] . "/api_gateway/resources/image_tte/".$NameimageTTE;
        //$imageTTE = $_SERVER['DOCUMENT_ROOT'] . "/api_gateway/resources/image_tte/cobaTTE.png";
        //echo $imageTTE;
        $response = tteSignV1($data['nik'],  $data['passphrase'], $tempFile, $data['image'], $data['tampilan'], $data['xAxis'], $data['yAxis'], $data['height'], $data['width'], $data['page'], $data['tag'], $imageTTE);
        if (json_decode($response, true) == null) {

            $in['no_ktp'] = $data['nik'];
            $in['tanggal'] = date("Y-m-d H:i:s");
            $in['status'] = "Sukses";
            $in['status_code'] = 200;
            $in['lokasi_file'] =  $fileName;
            $in['nama_berkas'] = $fileName;
            $this->Tte_model->saveData('log_berkas_tte', $in);
        } else {
            if (json_decode($response, true)['metadata']['code'] == 400) {
                $in['no_ktp'] = $data['nik'];
                $in['tanggal'] = date("Y-m-d H:i:s");
                $in['status'] = "Gagal";
                $in['status_code'] = "400 [" . json_decode($response, true)['response'] . "]";
                $in['lokasi_file'] =  $fileName;
                $in['nama_berkas'] = $fileName;
                $this->Tte_model->saveData('log_berkas_tte', $in);
            } else if (json_decode($response, true)['metadata']['code'] == 2031) {
                $in['no_ktp'] = $data['nik'];
                $in['tanggal'] = date("Y-m-d H:i:s");
                $in['status'] = "Gagal";
                $in['status_code'] = "2031 [" . json_decode($response, true)['response'] . "]";
                $in['lokasi_file'] =  $fileName;
                $in['nama_berkas'] = $fileName;
                $this->Tte_model->saveData('log_berkas_tte', $in);
            } else if (json_decode($response, true)['metadata']['code'] == 2011) {
                $in['no_ktp'] = $data['nik'];
                $in['tanggal'] = date("Y-m-d H:i:s");
                $in['status'] = "Gagal";
                $in['status_code'] = "2011 [" . json_decode($response, true)['response'] . "]";
                $in['lokasi_file'] =  $fileName;
                $in['nama_berkas'] = $fileName;
                $this->Tte_model->saveData('log_berkas_tte', $in);
            } else {
                $in['no_ktp'] = $data['nik'];
                $in['tanggal'] = date("Y-m-d H:i:s");
                $in['status'] = "Gagal";
                $in['status_code'] = 2011;
                $in['lokasi_file'] =  $fileName;
                $in['nama_berkas'] = $fileName;
                $this->Tte_model->saveData('log_berkas_tte', $in);
            }
        }

        echo $response;
    }
}
//webappsnya
//webapps local mas
//mash ya mas
//masih mas