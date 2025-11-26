<?php
defined('BASEPATH') or exit('No direct script access allowed');
include_once APPPATH . 'controllers/Restglobal_Controller.php';

class V1 extends Restglobal_Controller
{
    public function __construct()
    {
        parent::__construct();
        $this->load->model('Api_model');
        $this->Xtoken = $this->input->get_request_header('x-token', true);
        $this->Api_user = $this->input->get_request_header('Api-User', true);
        $this->Api_key = $this->input->get_request_header('Api-Key', true);
        $this->load->database();
    }
    function createtoken($username, $password)
    {
        $Cekuser = $this->Api_model->cekUser($username, $password);
        if ($Cekuser->num_rows() > 0) {
            $token = [
                'iss' => $Cekuser->row('iss'),
                'aud' => $Cekuser->row('aud'),
                'iat' => time(),
                'exp' => $Cekuser->row('exp'),
                'data' => [
                    'username' => $username,
                ],
            ];
            $gtoken = encode_jwt($token, privateKey());
            $response = [
                'response' => [
                    'token' => $gtoken,
                ],
                'metaData' => [
                    'message' => 'Ok',
                    'code' => 200,
                ],
            ];
            $status = REST_Controller::HTTP_OK;
            $in['username'] = $username;
            $in['last_access'] = date('Y-m-d H:i:s');
            $this->Api_model->updateData('tm_bridgeuser', 'username', $in);
        } else {
            $response = [
                'metaData' => [
                    'message' => 'Access denied, please check username or password',
                    'code' => 201,
                ],
            ];
            $status = REST_Controller::HTTP_CREATED;
        }
        $this->set_response($response, $status);
    }
    function cektoken($token)
    {
        try {
            if (
                decode_jwt($token, privateKey(), [
                    'typ' => 'JWT',
                    'alg' => 'HS256',
                ])
            ) {
                return true;
            }
        } catch (Exception $e) {
            $response = [
                'metaData' => [
                    'message' => $e->getMessage(),
                    'code' => 201,
                ],
            ];
            $status = REST_Controller::HTTP_CREATED;
            $this->set_response($response, $status);
        }
    }
    function viewtoken($token)
    {
        try {
            if (
                decode_jwt($token, privateKey(), [
                    'typ' => 'JWT',
                    'alg' => 'HS256',
                ])
            ) {
                return decode_jwt($token, privateKey(), [
                    'typ' => 'JWT',
                    'alg' => 'HS256',
                ]);
            }
        } catch (Exception $e) {
            $response = [
                'metaData' => [
                    'message' => $e->getMessage(),
                    'code' => 201,
                ],
            ];
            $status = REST_Controller::HTTP_CREATED;
            $this->set_response($response, $status);
        }
    }

    function serviceNotavailable()
    {
        $response = [
            'metaData' => [
                'message' => 'Services Not Available',
                'code' => 404,
            ],
        ];
        $status = REST_Controller::HTTP_NOT_FOUND;
        $this->set_response($response, $status);
    }
    function logaccsess($username, $access, $status)
    {
        $in['user'] = $username;
        $in['log'] = '[' . $status . '] ' . $access;
        $in['ip'] = $this->getIPAddress();
        $in['datetime'] = date('Y-m-d H:i:s');
        $this->Api_model->saveData('tt_logaccess', $in);
    }
    function getIPAddress()
    {
        //whether ip is from the share internet
        if (!empty($_SERVER['HTTP_CLIENT_IP'])) {
            $ip = $_SERVER['HTTP_CLIENT_IP'];
        }
        //whether ip is from the proxy
        elseif (!empty($_SERVER['HTTP_X_FORWARDED_FOR'])) {
            $ip = $_SERVER['HTTP_X_FORWARDED_FOR'];
        }
        //whether ip is from the remote address
        else {
            $ip = $_SERVER['REMOTE_ADDR'];
        }
        return $ip;
    }
}
