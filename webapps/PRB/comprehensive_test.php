<?php
// Comprehensive test for pagination and records per page functionality
require_once 'c:/laragon/www/webapps/conf/conf.php';
require_once 'prb.php';

try {
    $dsn = "mysql:host={$db_hostname};dbname={$db_name};charset=utf8mb4";
    $pdo = new PDO($dsn, $db_username, $db_password, [
        PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
        PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
    ]);

    $prb = new Prb($pdo);
    
    echo "Comprehensive test for pagination and records per page functionality...\n";
    
    // Test different page sizes
    $pageSizes = [10, 25, 50, 100];
    
    foreach ($pageSizes as $pageSize) {
        echo "\n--- Testing with $pageSize records per page ---\n";
        
        // Test page 1
        $result = $prb->getPatients(1, $pageSize);
        if ($result['success']) {
            echo "Page 1: " . count($result['data']) . " records\n";
            echo "Total records: " . $result['pagination']['total_records'] . "\n";
            echo "Total pages: " . $result['pagination']['total_pages'] . "\n";
            echo "Current page: " . $result['pagination']['current_page'] . "\n";
            echo "Limit: " . $result['pagination']['limit'] . "\n";
            
            // Check first few row numbers
            echo "First 5 row numbers should be: ";
            for ($i = 1; $i <= min(5, count($result['data'])); $i++) {
                echo "$i ";
            }
            echo "\n";
        } else {
            echo "Error: " . $result['message'] . "\n";
        }
        
        // Test page 2
        $result = $prb->getPatients(2, $pageSize);
        if ($result['success']) {
            echo "Page 2: " . count($result['data']) . " records\n";
            echo "Current page: " . $result['pagination']['current_page'] . "\n";
            echo "Limit: " . $result['pagination']['limit'] . "\n";
            
            // Check first few row numbers
            echo "First 5 row numbers should be: ";
            for ($i = 1; $i <= min(5, count($result['data'])); $i++) {
                $rowNumber = (($result['pagination']['current_page'] - 1) * $result['pagination']['limit']) + $i;
                echo "$rowNumber ";
            }
            echo "\n";
        } else {
            echo "Error: " . $result['message'] . "\n";
        }
    }
    
    // Test edge case: page beyond total pages
    echo "\n--- Testing edge case: page beyond total pages ---\n";
    $result = $prb->getPatients(999999, 10);
    if ($result['success']) {
        echo "Page 999999: " . count($result['data']) . " records\n";
        echo "Total records: " . $result['pagination']['total_records'] . "\n";
        echo "Total pages: " . $result['pagination']['total_pages'] . "\n";
        echo "Current page: " . $result['pagination']['current_page'] . "\n";
        echo "Limit: " . $result['pagination']['limit'] . "\n";
    } else {
        echo "Error: " . $result['message'] . "\n";
    }
    
} catch (Exception $e) {
    echo "Error: " . $e->getMessage() . "\n";
}
?>