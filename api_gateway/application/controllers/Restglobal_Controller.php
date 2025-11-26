<?php
defined('BASEPATH') OR exit('No direct script access allowed');
// require APPPATH . 'libraries/REST_Controller.php';

class Restglobal_Controller extends REST_Controller {
 public  function __construct()
    {
        // Construct the parent class
        parent::__construct();
        $this->load->database();
        // $this->load->model('Wsrsudbridge_model');
         $this->load->helper('xml');

        // Configure limits on our controller methods
        // Ensure you have created the 'limits' table and enabled 'limits' within application/config/rest.php
        $this->methods['users_get']['limit'] = 500000; // 500 requests per hour per user/key
        $this->methods['users_post']['limit'] = 500000; // 100 requests per hour per user/key
        $this->methods['users_delete']['limit'] = 10; // 50 requests per hour per user/key
      
        $this->Xuser=$this->input->get_request_header('X-User-id', TRUE);
        $this->XTimestamp=$this->input->get_request_header('X-Timestamp', TRUE);
        $this->XSignature=$this->input->get_request_header('X-Signature', TRUE);
        $this->XToken=$this->input->get_request_header('x-token', TRUE);

    }
    
    
	
	
}
