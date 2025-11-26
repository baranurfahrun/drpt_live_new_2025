<?php
defined('BASEPATH') or exit('No direct script access allowed');
include_once 'Api_model.php';

class Tte_model extends Api_model
{
    function getSelectLog($startDate, $lastDate)
    {
        $this->db->select('*');
        $this->db->from('log_berkas_tte');
        $this->db->where("date(tanggal) between '$startDate' and '$lastDate'");
        $query = $this->db->get();
        return $query;
    }
    function getDataImageTTE($nik)
    {
        $this->db->select('*');
        $this->db->from('akun_tte');
        $this->db->where("nik", $nik);
        $query = $this->db->get();
        return $query;
    }
}
