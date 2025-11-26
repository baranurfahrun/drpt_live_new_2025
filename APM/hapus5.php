<?php



 include ('config.php');
 $location_counter = "loket.txt";
			 $location_date = "date.txt";
			 $itis = date ("d");
			 
			 // Hari baru?    
			$aday = join('', file($location_date));
			trim($aday);
			$nilai=2;
			$nilai1=1;
			$nilai2=2;
			$nilai3=1;
			$nilai4=0;
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
				
				window.location='pasien_proritas_loket5.php';
				</script>";
?>
