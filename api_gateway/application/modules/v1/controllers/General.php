<?php
defined('BASEPATH') or exit('No direct script access allowed');
include_once("V1.php");

class General extends V1
{
    function auth_post()
    {
        $data = json_decode(file_get_contents('php://input'), true);
        $username = $data['username'];
        $password = $data['password'];
        $user = $this->Api_model->getSelect('bridge_user', 'username', $username);
        $this->createtoken($username, $password);
    }
    function get_token_post()
    {
        $data = json_decode(file_get_contents('php://input'), true);
        $timestamp = strtotime(date("Y/m/d H:i:s"));
        $username = $data['username'];
        $password = $data['password'];
        $in['username'] = $username;
        //$in['password']=$password;
        $in['last_access'] = date("Y-m-d H:i:s");
        $in['token'] = getToken($username, $password, $timestamp);
        $data = $this->Api_model->getSelect('bridge_user', 'username', $username);
        if ($data->num_rows() > 0) {
            $this->Api_model->updateData('bridge_user', 'username', $in);
            $response = ["token" => getToken($username, $password, $timestamp)];
            $metadata = ["message" => "Ok", "code" => 200];
            $status = REST_Controller::HTTP_OK;
        } else {
            $response = "Anda tidak memiliki aksess";
            $metadata = ["message" => "Gagal", "code" => 401];
            $status = REST_Controller::HTTP_UNAUTHORIZED;
        }
        $message = ["response" => $response, "metadata" => $metadata,];
        $this->set_response($message, $status);
    }
    function validationParam($param = null, $data = null, $triger = null, $length = 0)
    {
        $messagesEmpty = array(
            "nomorkartu" => 'BPJS number cannot be empty !',
            'nik' => 'NIK number cannot be empty !',
            'nomorkk' => 'KK number cannot be empty !',
            'nama' => 'Name cannot be empty !',
            'nohp' => 'Handphone number cannot be empty !',
            'alamat' => 'Address cannot be empty !',
            'kodeprop' => 'Province code cannot be empty !',
            'namaprop' => 'Province name cannot be empty !',
            'kodedati2' => 'Regency code cannot be empty !',
            'namadati2' => 'Regency name cannot be empty !',
            'kodekec' => 'District code cannot be empty !',
            'namakec' => 'District name cannot be empty !',
            'kodekel' => 'Village code cannot be empty !',
            'namakel' => 'Village name cannot be empty !',
            'rw' => 'RW cannot be empty !',
            'rt' => 'RT cannot be empty !'
        );
        $messagesLength = array(
            "nomorkartu" => 'BPJS number must be 13 digits !',
            'nik' => 'NIK number must be 16 digits !',
            'nomorkk' => 'KK number must be 16 digits !',
        );
        switch ($triger) {
            case "Empty":
                if (empty($data)) {
                    $response = ["keterangan" => $messagesEmpty[$param]];
                    $metadata = ["message" => "Failed", "code" => 201];
                    $message = [
                        "metadata" => $metadata,
                        "response" => $response
                    ];
                    $status = REST_Controller::HTTP_CREATED;
                    $this->set_response($message, $status);
                    return false;
                } else {
                    return true;
                }
                break;
            case "Length":
                if (strlen($data) <> $length) {
                    $response = ["keterangan" => $messagesLength[$param]];
                    $metadata = ["message" => "Failed", "code" => 201];
                    $message = [
                        "metadata" => $metadata,
                        "response" => $response
                    ];
                    $status = REST_Controller::HTTP_CREATED;
                    $this->set_response($message, $status);
                    return false;
                } else {
                    return true;
                }
                break;
            case "Date Format":
                if (preg_match("/^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/", $data)) {
                    return true;
                } else {
                    $response = ["keterangan" => "Incorrect date format YYYY-mm-dd"];
                    $metadata = ["message" => "Failed", "code" => 201];
                    $message = [
                        "metadata" => $metadata,
                        "response" => $response
                    ];
                    $status = REST_Controller::HTTP_CREATED;
                    $this->set_response($message, $status);
                    return false;
                }
                break;
        }
    }
}
