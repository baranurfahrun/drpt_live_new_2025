<?php
$sql_details = array(
    'user' => 'client',
    'pass' => 'P3L4monia108',
    'db' => 'db_pelamonia',
    'host' => '192.168.108.246:3108',
    'charset' => 'utf8' // Depending on your PHP and MySQL config, you may need this
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
// $table = 'pasien';

$table = <<<EOT
    (
        SELECT pasien.no_rkm_medis,dokter.nm_dokter,pasien.nm_pasien,databarang.nama_brng,detail_pemberian_obat.tgl_perawatan,detail_pemberian_obat.biaya_obat 
	                          FROM dokter, pasien, detail_pemberian_obat, databarang, reg_periksa
	                          WHERE detail_pemberian_obat.`status`='Ralan' 
	                          AND detail_pemberian_obat.kode_brng=databarang.kode_brng 
									  AND reg_periksa.no_rkm_medis=pasien.no_rkm_medis 
									  AND reg_periksa.kd_dokter=dokter.kd_dokter 
									  AND reg_periksa.no_rawat=detail_pemberian_obat.no_rawat
	                          GROUP BY detail_pemberian_obat.tgl_perawatan,reg_periksa.kd_dokter,databarang.nama_brng;
    ) temp
EOT;

// Table's primary key
$primaryKey = 'no_rkm_medis';

// Array of database columns which should be read and sent back to DataTables.
// The `db` parameter represents the column name in the database, while the `dt`
// parameter represents the DataTables column identifier. In this case simple
// indexes
$columns = array(
    array('db' => 'no_rkm_medis', 'dt' => 0),
    array('db' => 'nm_dokter', 'dt' => 1),
    array('db' => 'nm_pasien', 'dt' => 2),
    array('db' => 'nama_brng', 'dt' => 3),
    // array('db' => 'tgl_perawatan', 'dt' => 3),
    array( 
        'db'        => 'tgl_perawatan', 
        'dt'        => 4, 
        'formatter' => function( $d, $row ) { 
            return date( 'jS M Y', strtotime($d)); 
        } 
    ), 
    array('db' => 'biaya_obat','dt' => 5),
    // array('db' => 'jml','dt' => 6),
    // array('db' => 'total','dt' => 7)
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
    SSP::simple($_GET, $sql_details, $table, $primaryKey, $columns)
);
