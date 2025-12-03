<?php
// export_patient_history_pdf.php - Export patient history to PDF
session_start();

error_reporting(E_ALL);
ini_set('display_errors', 1);

// Include database configuration
require_once '../conf/conf.php';
require_once 'prb.php';

// Authentication check
$sesilogin = isset($_SESSION['ses_admin_login']) ? $_SESSION['ses_admin_login'] : NULL;
if ($sesilogin != USERHYBRIDWEB . PASHYBRIDWEB) {
    http_response_code(401);
    die('Unauthorized access');
}

// Check if TCPDF library is available
if (!file_exists('../phpqrcode/bindings/tcpdf/tcpdf.php')) {
    http_response_code(500);
    die('TCPDF library not found. Please install TCPDF library to enable PDF export functionality.');
}

die('PDF export functionality is temporarily disabled. Please use Excel export instead.');
?>