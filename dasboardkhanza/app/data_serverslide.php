<?php

 $sql_details = array(
    'user' => 'client',
    'pass' => 'P3L4monia108',
    'db'   => 'db_pelamonia_dev',
    'host' => '192.168.108.246:3108'
    ,'charset' => 'utf8' // Depending on your PHP and MySQL config, you may need this
);

/*
 * DataTables example server-side processing script.
 *
 * Please note that this script is intentionally extremely simple to show how
 * server-side processing can be implemented, and probably shouldn't be used as
 * the basis for a large complex system. It is suitable for simple use cases as
 * for learning.
 *
 * See https://datatables.net/usage/server-side for full details on the server-
 * side processing requirements of DataTables.
 *
 * @license MIT - https://datatables.net/license_mit
 */
 
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Easy set variables
 */
 
 
// DB table to use
$table = 'pasien';

// $table = <<<EOT
//     (
//         SELECT pasien.no_rkm_medis,
//         pasien.nm_pasien,
//         pasien.pekerjaan,
//         pasien.tmp_lahir
//         FROM pasien
//         ) temp
//     EOT;
 
// Table's primary key
$primaryKey = 'no_rkm_medis';
 
// Array of database columns which should be read and sent back to DataTables.
// The `db` parameter represents the column name in the database, while the `dt`
// parameter represents the DataTables column identifier. In this case simple
// indexes
$columns = array(
    array( 'db' => 'no_rkm_medis', 'dt' => 0 ),
    array( 'db' => 'nm_pasien',  'dt' => 1 ),
    array( 'db' => 'pekerjaan',   'dt' => 2 ),
    array( 'db' => 'tmp_lahir',   'dt' => 3 ),
    // array( 
    //     'db'        => 'tmp_lahir', 
    //     'dt'        => 3, 
    //     'formatter' => function( $d, $row ) { 
    //         return date( 'jS M Y', strtotime($d)); 
    //     } 
    // ), 
    // array(
    //     'db'        => 'salary',
    //     'dt'        => 5,
    //     'formatter' => function( $d, $row ) {
    //         return '$'.number_format($d);
    //     }
    // )
);
 
// SQL server connection information
// require('../conf/config.php');

 
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * If you just want to use the basic configuration for DataTables with PHP
 * server-side, there is no need to edit below this line.
 */

//  include_once("../conf/config.php");
require('ssp.class.php');
 
echo json_encode(
    SSP::simple( $_GET, $sql_details, $table, $primaryKey, $columns )
);
