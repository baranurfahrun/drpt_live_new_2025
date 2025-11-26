 <?php
 include ('config.php');

$query = "SELECT *FROM antrian_monitor_lama ";
$result = mysqli_query($connection,$query);
				
while(($row = mysqli_fetch_array($result))){
	echo $row['lama'];

}

?>