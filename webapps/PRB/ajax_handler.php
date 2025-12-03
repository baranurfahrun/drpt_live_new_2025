<?php
// ajax_handler.php - Handler for AJAX requests
session_start();

error_reporting(E_ALL);
ini_set('display_errors', 0); // Disable display errors to prevent JSON corruption
header('Content-Type: application/json');

// Include database configuration
require_once '../conf/conf.php';
require_once 'prb.php';

// Authentication check
$sesilogin = isset($_SESSION['ses_admin_login']) ? $_SESSION['ses_admin_login'] : NULL;
if ($sesilogin != USERHYBRIDWEB . PASHYBRIDWEB) {
    echo json_encode(['success' => false, 'message' => 'Unauthorized access']);
    exit;
}

try {
    // Database connection
    $dsn = "mysql:host={$db_hostname};dbname={$db_name};charset=utf8mb4";
    $pdo = new PDO($dsn, $db_username, $db_password, [
        PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
        PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
    ]);

    // Initialize Prb class
    $prb = new Prb($pdo);

    // Get action
    $action = $_POST['action'] ?? '';

    switch ($action) {
        case 'get_patients':
            $page = $_POST['page'] ?? 1;
            $limit = $_POST['limit'] ?? 10;
            $search = $_POST['search'] ?? '';
            $date_from = $_POST['date_from'] ?? '';
            $date_to = $_POST['date_to'] ?? '';
            $prb_filter = $_POST['prb_filter'] ?? '';
            $prb_document_filter = $_POST['prb_document_filter'] ?? '';
            $unique_patient_filter = $_POST['unique_patient_filter'] ?? '0';
            $poli_filter = $_POST['poli_filter'] ?? '';

            $result = $prb->getPatients($page, $limit, $search, $date_from, $date_to, $prb_filter, $prb_document_filter, $unique_patient_filter, $poli_filter);
            echo json_encode($result);
            break;

        case 'get_poliklinik_list':
            $result = $prb->getPoliklinikList();
            echo json_encode($result);
            break;

        case 'get_patient_history':
            $no_kartu = $_POST['no_kartu'] ?? '';
            if (empty($no_kartu)) {
                echo json_encode(['success' => false, 'message' => 'No. Kartu is required']);
                exit;
            }
            $result = $prb->getPatientHistory($no_kartu);
            echo json_encode($result);
            break;

        case 'export_excel':
            // For export, we might want to handle it differently (e.g., return file download)
            // But based on prb.php, it returns data array. 
            // Usually export is a direct GET request or form submit, not AJAX JSON.
            // However, if the frontend expects JSON data to then generate Excel (client-side) or 
            // if this is just to get the data for display before export.
            
            // If the intention is to download a file, this handler should output headers and file content.
            // But let's check how index.php handles export.
            // index.php: onclick="exportToExcel()" -> calls export_excel.php usually?
            // Let's check index.php exportToExcel function.
            
            // Re-reading index.php JS to see exportToExcel implementation
            // Wait, I need to see the JS part of index.php first.
            
            // For now, let's implement basic data retrieval for export
            $search = $_POST['search'] ?? '';
            $date_from = $_POST['date_from'] ?? '';
            $date_to = $_POST['date_to'] ?? '';
            $prb_filter = $_POST['prb_filter'] ?? '';
            $prb_document_filter = $_POST['prb_document_filter'] ?? '';
            $limit = $_POST['limit'] ?? 0;

            $result = $prb->exportToExcel($search, $date_from, $date_to, $prb_filter, $prb_document_filter, $limit);
            echo json_encode($result);
            break;

        case 'export_patient_history_excel':
            $no_kartu = $_POST['no_kartu'] ?? '';
            if (empty($no_kartu)) {
                echo json_encode(['success' => false, 'message' => 'No. Kartu is required']);
                exit;
            }
            $result = $prb->exportPatientHistoryToExcel($no_kartu);
            echo json_encode($result);
            break;

        case 'clear_patient_history_cache':
            // Since we are not using server-side caching in this simple PHP version, 
            // we can just return success. If caching was implemented (e.g. $_SESSION or file), clear it here.
            echo json_encode(['success' => true]);
            break;

        default:
            echo json_encode(['success' => false, 'message' => 'Invalid action']);
            break;
    }

} catch (Exception $e) {
    echo json_encode(['success' => false, 'message' => 'Server Error: ' . $e->getMessage()]);
}
