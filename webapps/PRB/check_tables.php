<?php
include 'c:/laragon/www/webapps/conf/conf.php';

try {
    $pdo = new PDO('mysql:host=' . $db_hostname . ';dbname=' . $db_name, $db_username, $db_password, [
        PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION
    ]);
    
    // Check bridging_sep columns
    $stmt = $pdo->prepare('DESCRIBE bridging_sep');
    $stmt->execute();
    $columns = $stmt->fetchAll(PDO::FETCH_ASSOC);
    
    echo "bridging_sep columns:\n";
    foreach ($columns as $col) {
        echo '- ' . $col['Field'] . ' (' . $col['Type'] . ")\n";
    }
    
    echo "\nbridging_sep_internal columns:\n";
    $stmt = $pdo->prepare('DESCRIBE bridging_sep_internal');
    $stmt->execute();
    $columns = $stmt->fetchAll(PDO::FETCH_ASSOC);
    
    foreach ($columns as $col) {
        echo '- ' . $col['Field'] . ' (' . $col['Type'] . ")\n";
    }
    
    echo "\nbpjs_prb sample data:\n";
    $stmt = $pdo->prepare('SELECT prb FROM bpjs_prb LIMIT 10');
    $stmt->execute();
    $rows = $stmt->fetchAll(PDO::FETCH_ASSOC);
    
    foreach ($rows as $row) {
        echo '- ' . $row['prb'] . "\n";
    }
    
} catch (Exception $e) {
    echo 'Error: ' . $e->getMessage();
}
?>