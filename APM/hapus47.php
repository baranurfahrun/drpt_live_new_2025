<?php
 include ('config.php');
 $location_counter = "konter.txt";
			 $location_date = "date.txt";
			 $itis = date ("d");
			 
			 // Hari baru?    
			$aday = join('', file($location_date));
			trim($aday);
			$nilai=1;
			if("$aday"=="$itis"){
				//Cari hari ini
				$tcounter = join('', file($location_counter));
				trim($tcounter);
				$tcounter -=$nilai;
			
				
				$fp = fopen($location_counter,"w");
				fputs($fp, $tcounter);
				fclose($fp);
			}else{
				
			}
			echo "<script>
				
				window.location='pasien_baru_loket2.php';
				</script>";
?>
