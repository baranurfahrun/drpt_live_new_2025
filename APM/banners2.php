 <?php
 include ('config.php');
$tgl=date('d-m-Y');
$tgl2=date('Y-m-d');
$query = "delete from antrian_cs where postdate < DATE_SUB(NOW() , INTERVAL 1 DAY)";
//$query2 = "DELETE FROM antrian_cs WHERE kd IN (SELECT * FROM (SELECT kd FROM antrian_cs GROUP BY noantrian HAVING (COUNT(*) > 1)) AS A)";
$hasil = mysqli_query($connection,$query);
//$hasil2 = mysqli_query($connection,$query2);
 
$query = "SELECT distinct noantrian FROM antrian_cs where postdate='$tgl2'";
$result = mysqli_query($connection,$query);
$data = array();				
while(($row = mysqli_fetch_array($result)) != null){
$data[] = $row;
}
$count = count($data);
echo "$count";
?>