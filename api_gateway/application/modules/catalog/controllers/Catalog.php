<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Catalog extends CI_Controller {

	public function index()
	{
		
		$this->load->view('master_header');
	 	$this->load->view('master_navigation');
		$this->load->view('home/index');
		$this->load->view('master_footer');
	 	//$this->load->view('aktifitas/footer');
	}
}
