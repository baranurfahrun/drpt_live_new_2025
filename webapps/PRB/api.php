<?php
// api.php - API endpoint for patient operations
header('Content-Type: application/json');
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS');
header('Access-Control-Allow-Headers: Content-Type, Authorization');

// Handle preflight OPTIONS request
if ($_SERVER['REQUEST_METHOD'] == 'OPTIONS') {
    exit(0);
}

require_once 'patient_controller.php';

try {
    $controller = new PatientController();
    $method = $_SERVER['REQUEST_METHOD'];
    $request = json_decode(file_get_contents('php://input'), true);
    
    // Get action from URL parameter or request body
    $action = $_GET['action'] ?? $request['action'] ?? '';
    
    switch ($method) {
        case 'GET':
            switch ($action) {
                case 'list':
                    $page = $_GET['page'] ?? 1;
                    $limit = $_GET['limit'] ?? 10;
                    $search = $_GET['search'] ?? '';
                    $result = $controller->getPatients($page, $limit, $search);
                    jsonResponse($result);
                    break;
                    
                case 'get':
                    $id = $_GET['id'] ?? 0;
                    if ($id) {
                        $result = $controller->getPatient($id);
                        jsonResponse($result);
                    } else {
                        jsonResponse(['success' => false, 'message' => 'ID is required'], 400);
                    }
                    break;
                    
                case 'export':
                    $result = $controller->exportPatients();
                    jsonResponse($result);
                    break;
                    
                default:
                    jsonResponse(['success' => false, 'message' => 'Invalid action for GET method'], 400);
            }
            break;
            
        case 'POST':
            switch ($action) {
                case 'create':
                    if ($request) {
                        $result = $controller->createPatient($request);
                        jsonResponse($result);
                    } else {
                        jsonResponse(['success' => false, 'message' => 'Invalid request data'], 400);
                    }
                    break;
                    
                default:
                    jsonResponse(['success' => false, 'message' => 'Invalid action for POST method'], 400);
            }
            break;
            
        case 'PUT':
            switch ($action) {
                case 'update':
                    $id = $_GET['id'] ?? $request['id'] ?? 0;
                    if ($id && $request) {
                        $result = $controller->updatePatient($id, $request);
                        jsonResponse($result);
                    } else {
                        jsonResponse(['success' => false, 'message' => 'ID and request data are required'], 400);
                    }
                    break;
                    
                default:
                    jsonResponse(['success' => false, 'message' => 'Invalid action for PUT method'], 400);
            }
            break;
            
        case 'DELETE':
            switch ($action) {
                case 'delete':
                    $id = $_GET['id'] ?? $request['id'] ?? 0;
                    if ($id) {
                        $result = $controller->deletePatient($id);
                        jsonResponse($result);
                    } else {
                        jsonResponse(['success' => false, 'message' => 'ID is required'], 400);
                    }
                    break;
                    
                default:
                    jsonResponse(['success' => false, 'message' => 'Invalid action for DELETE method'], 400);
            }
            break;
            
        default:
            jsonResponse(['success' => false, 'message' => 'Method not allowed'], 405);
    }
    
} catch (Exception $e) {
    jsonResponse([
        'success' => false,
        'message' => 'Server error: ' . $e->getMessage()
    ], 500);
}
?>