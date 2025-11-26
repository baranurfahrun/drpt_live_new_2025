	<?php

	include ('config.php');

			
	
	
$filename = 'loket.txt';
$newdata = $_POST['data'];
if (trim($newdata != '')) {
// open file
$fw = fopen($filename, 'w') or die('Could not open file!');
// write to file
// added stripslashes to $newdata
$fb = fwrite($fw,stripslashes($newdata)) or die('Could not write to file');
// close file
fclose($fw);
}
// open file
  $fh = fopen($filename, "r") or die("Could not open file!");
// read file contents
  $data = fread($fh, filesize($filename)) or die("Could not read file!");
// close file
  fclose($fh);
  
   $location_counter = "loket.txt";
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
				
				window.location='pasien_proritas_loket5.php';
				</script>";
     ?>					