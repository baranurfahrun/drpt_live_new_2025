<?php
defined('BASEPATH') or exit('No direct script access allowed');

class Api_model extends CI_Model
{
    public function __construct()
    {
        // Load default database
        $this->load->database();
        // Load database
    }
    public function cekUser($username, $password)
    {
        $this->db->select('*');
        $this->db->from('tm_bridgeuser');
        $this->db->where('username', $username);
        $this->db->where('password', $password);
        $query = $this->db->get();
        return $query;
    }
    public function getData($table)
    {
        $this->db->select('*');
        $this->db->from($table);
        $query = $this->db->get();
        return $query;
    }
    public function getSelect($table, $field, $select)
    {
        $this->db->select('*');
        $this->db->from($table);
        $this->db->where($field, $select);
        $query = $this->db->get();
        return $query;
    }
    public function updateData($table, $field, $in)
    {
        $this->db->where($field, $in[$field]);
        $this->db->update($table, $in);
    }
    public function saveData($table, $in)
    {
        $query = $this->db->insert($table, $in);
        return $query;
    }
    public function getUIDAntrian()
    {
        $this->db->select('uid_antrian');
        $this->db->from('antrian_poli');
        $this->db->order_by('RIGHT(uid_antrian,5) DESC');
        $this->db->limit('1');
        $query = $this->db->get();
        return $query;
        // ifnull(MAX(CONVERT(no_reg,signed)),0)
    }
}
