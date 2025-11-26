 <?php
 include ('config.php');

$query = "SELECT *FROM antrian_monitor_prioritas ";
$result = mysqli_query($connection,$query);
				
while(($row = mysqli_fetch_array($result))){
	echo $row['prioritas'];

}

?>