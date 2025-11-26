<?php
include ('config.php');

//$lama = 1;
//$query = "DELETE FROM antrian_loket
        //  WHERE DATEDIFF(CURDATE(), postdate) > $lama";
//$query2 = "DELETE FROM antrian_loket WHERE kd IN (SELECT * FROM (SELECT kd FROM antrian_loket GROUP BY noantrian HAVING (COUNT(*) > 1)) AS A)";
//$hasil = mysqli_query($connection,$query);
//$hasil = mysqli_query($connection,$query2);
	//Initialisasi nilai untuk nomor loket

	$loket3="3";
	


?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Aplikasi Suara Antrian</title>
<link rel="stylesheet" href="syle.css" />
<script type="text/javascript" src="jquery-1.7.2.js"></script>
<script type="text/javascript" >
$(document).ready(function(){
	$("#play").click(function(){
		document.getElementById('suarabel').play();		
	});
	
	
});
$(document).ready(function(){
	$("#playy").click(function(){
		document.getElementById('suarabel2').play();		
	});
	
	
});
</script>

   <style>
.blink {
  animation: blink-animation 1s steps(5, start) infinite;
  -webkit-animation: blink-animation 1s steps(5, start) infinite;
}
@keyframes blink-animation {
  to {
    visibility: hidden;
  }
}
@-webkit-keyframes blink-animation {
  to {
    visibility: hidden;
  }

.kiri {
    position: right;
}   
}

</style>
  




<style>
.banner {width:0px; text-align:center; margin:0 auto; position: relative; -webkit-border-radius: 5px 0 5px 0px; -moz-border-radius: 0px 0 0px 0px; border-radius: 5px 0 5px 5px; padding:20px 20px;}
.banner:before {content: ""; position: absolute; top: 0; right: 0;border-width: 0 0px 0px 0; border-style: solid; border-color:#F0F0F0 #fff; -webkit-border-radius: 0px 0 0px 5px; -moz-border-radius: 0px 0 0px 5px; border-radius: 0px 0 0px 5px;}
#screen {width:100%;color: #555;"Century Gothic",CenturyGothic,AppleGothic,sans-serif;line-height:0px;}
.txt-title {font-size:2em;}
.txt-subtitle {font-size:1.2em;}
</style>
<script src="https://code.jquery.com/jquery-2.1.1.min.js" type="text/javascript"></script>
<script>
$(document).ready(function(){
 setInterval(function(){
  $("#screen3").load('banners3.php')
    }, 500);
});
</script>
  

  
  

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="csss/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="fontawesome-free-5.6.3-web/csss/all.css">
    <link href="csss/gijgo.min.css" rel="stylesheet" type="text/css" />
</head>
<body >
	<div align="center" class="kontainer" style="color:white; text-shadow: 2px 2px 4px #000000;">Sistem Antrian Loket</div>
   <center><span class="blink"><font color="brown" face="Maiandra GD" ><font size="4"> RSUD Prof DR H M Anwar Makkatutu Bantaeng <br> </font></font></span></center>



		<audio id="suarabel" src="Airport_Bell.wav"></audio>
		<audio id="suarabeel" src="Airport_Bell3.wav"></audio>
		<audio id="pasienlama" src="rekaman/Pasienlama.wav"></audio>
		<audio id="suarabelnomorurut" src="rekaman/no_urut.wav"  ></audio>  
		<audio id="p" src="rekaman/l.wav"  ></audio>  
		<audio id="suarabelsuarabelloket" src="rekaman/Diloket.wav"  ></audio> 
		<audio id="nol" src="rekaman/nol.wav"  ></audio> 
       
		<audio id="belas" src="rekaman/belas.wav"  ></audio> 
		<audio id="sebelas" src="rekaman/sebelas.wav"  ></audio> 
        <audio id="puluh" src="rekaman/puluh.wav"  ></audio> 
        <audio id="sepuluh" src="rekaman/sepuluh.wav"  ></audio> 
        <audio id="ratus" src="rekaman/ratus.wav"  ></audio> 
        <audio id="seratus" src="rekaman/seratus.wav"  ></audio> 
        <audio id="suarabelloket3" src="rekaman/<?php echo $loket3; ?>.wav"  ></audio>  
       
		<?php
			 $location_counter = "datacs.txt";
			 $location_date = "date.txt";
			 $itis = date ("d");
			 
			 // Hari baru?    
			$aday = join('', file($location_date));
			trim($aday);
		
			if("$aday"=="$itis"){
				//Cari hari ini
				$tcounter = join('', file($location_counter));
				trim($tcounter);
				$tcounter++;
				
				$fp = fopen($location_counter,"w");
				fputs($fp, $tcounter);
				fclose($fp);
			}else{
				//hari baru
				$fp = fopen($location_counter,"w");
				fputs($fp, 0);
				fclose($fp);
				$tcounter = join('', file($location_counter));
				trim($tcounter);
				$tcounter++;
				//tulis hari baru
				$fp = fopen($location_counter,"w");
				fputs($fp, $tcounter);
				fclose($fp);
				//tulis di date.txt
				$fp = fopen($location_date,"w");
				fputs($fp, $itis);
				fclose($fp);	
			}

			$panjang=strlen($tcounter);
			$antrian=$tcounter;
			 $query5 = "DELETE FROM antrian_monitor_lama";
						 $hasil4 = mysqli_query($connection,$query5);
						 $query3="INSERT INTO antrian_monitor_lama(lama) VALUE ($antrian)";	
			            $hasil3 = mysqli_query($connection,$query3);
			for($i=0;$i<$panjang;$i++){
		?>
        		<audio id="suarabel<?php echo $i; ?>" src="rekaman/<?php echo substr($tcounter,$i,1); ?>.wav" ></audio>   		        
        <?php
			}
		?>
		
	
		<div class="kontainer2">
		<div id="wrapper">
		
		
		
		
		
		
		
		
         <div class="modal-body">
           
			<table align="center" border="10" cellpadding="15" bgcolor="transparent">
			<tbody>
                          <tr>
                            <td style="background-color:#FFFF99" colspan='4' align='center'  scope="row"><b>LOKET 3</b></td>
							 
                          </tr>
						  <tr>
                            
							 <td style="background-color:#FFFF99" colspan='4' align='center'  scope="row">Pasien Lama</td>
                          </tr>
                          <tr>
                            <td style="background-color:#FFFF99" colspan='3' align='center' >
							<div class="kontainer">
							<div ><a id="konter"><?php echo 'L'.$antrian; ?></a>
							</div>
							</div>
							</td>
                             <td style="background-color:#FFFF99"><div align="center" ><input name="play" onclick="mulai3();" id="tombol" type="image" src="icon.png" value="Panggil"  /></div>
							 <br> <br>
								 <a  href="hapus36.php"/>
							 <img src="stop.png" width='32px'>
							</a></b>
							 </td>
              </tr>
                          <tr>
						    <td style="background-color:#FFFF99"><div><id="konter" id='screen3'>  
						  </a></div></td>
                             <td style="background-color:#FFFF99"><div ><a id="konter" href="hapus33.php">back</a></div></td>
						   <td style="background-color:#FFFF99"><div align="center" >
                             <?php
                              echo "
								<form action='pasienl3.php' method= 'post' >
									<input type='number' min='1' name='data' style='width: 3em'></input>
								<input type='image' src='enter.png'></input>
								</form>";
                             ?>
                             </div></td>
						   <td style="background-color:#FFFF99"><div ><a id="konter" href="pasien_lama_loket3.php">Next</a></div></td> </tr>

                       </tbody> </table>
					   
					  <table align="left" border="0" cellpadding="15" bgcolor="transparent">
						<tbody>
                          <tr>
						  <td style="background-color:#FFFF99"><div ><a id="konter" href="index.php">Kembali</a></div></td>
                            <td colspan='4' align='center'  scope="row"><b></b></td>
						</tr>
						  
                        </tbody></table>
					
         </div>
					
</body>
</html>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">



function mulai3(){
	//MAINKAN SUARA BEL PADA SAAT AWAL
	document.getElementById('suarabel').pause();
	document.getElementById('suarabel').currentTime=0;
	document.getElementById('suarabel').play();
			
	//SET DELAY UNTUK MEMAINKAN REKAMAN NOMOR URUT		
	totalwaktu=document.getElementById('suarabel').duration*900;	
	//MAINKAN SUARA Pasienlam
	setTimeout(function() {
			document.getElementById('pasienlama').pause();
			document.getElementById('pasienlama').currentTime=0;
			document.getElementById('pasienlama').play();
	}, totalwaktu);
	totalwaktu=totalwaktu+1500;
	
	
	
	//MAINKAN SUARA NOMOR URUT		
	//setTimeout(function() {
	//		document.getElementById('suarabelnomorurut').pause();
	//		document.getElementById('suarabelnomorurut').currentTime=0;
	//		document.getElementById('suarabelnomorurut').play();
//	}, totalwaktu);
//	totalwaktu=totalwaktu+1000;
	
	//MAINKAN SUARA P		
	setTimeout(function() {
			document.getElementById('p').pause();
			document.getElementById('p').currentTime=0;
			document.getElementById('p').play();
	}, totalwaktu);
	totalwaktu=totalwaktu+600;
	
	<?php
		//JIKA KURANG DARI 10 MAKA MAIKAN SUARA ANGKA1
		if($antrian<10){
	?>
			
			setTimeout(function() {
					document.getElementById('suarabel0').pause();
					document.getElementById('suarabel0').currentTime=0;
					document.getElementById('suarabel0').play();
				}, totalwaktu);
			
			totalwaktu=totalwaktu+680;
	<?php		
		}elseif($antrian ==10){
			//JIKA 10 MAKA MAIKAN SUARA SEPULUH
	?>  
				setTimeout(function() {
						document.getElementById('sepuluh').pause();
						document.getElementById('sepuluh').currentTime=0;
						document.getElementById('sepuluh').play();
					}, totalwaktu);
				totalwaktu=totalwaktu+680;
		<?php		
			}elseif($antrian ==11){
				//JIKA 11 MAKA MAIKAN SUARA SEBELAS
		?>  
				setTimeout(function() {
						document.getElementById('sebelas').pause();
						document.getElementById('sebelas').currentTime=0;
						document.getElementById('sebelas').play();
					}, totalwaktu);
				totalwaktu=totalwaktu+680;
		<?php		
			}elseif($antrian < 20){
				//JIKA 12-20 MAKA MAIKAN SUARA ANGKA2+"BELAS"
		?>  				
				setTimeout(function() {
						document.getElementById('suarabel1').pause();
						document.getElementById('suarabel1').currentTime=0;
						document.getElementById('suarabel1').play();
					}, totalwaktu);
				totalwaktu=totalwaktu+680;
				setTimeout(function() {
						document.getElementById('belas').pause();
						document.getElementById('belas').currentTime=0;
						document.getElementById('belas').play();
					}, totalwaktu);
				totalwaktu=totalwaktu+680;
		<?php		
			}elseif($antrian <= 99){				
				//JIKA PULUHAN MAKA MAINKAN SUARA ANGKA1+PULUH+AKNGKA2
		?>  
				setTimeout(function() {
						document.getElementById('suarabel0').pause();
						document.getElementById('suarabel0').currentTime=0;
						document.getElementById('suarabel0').play();
					}, totalwaktu);
				totalwaktu=totalwaktu+680;
				setTimeout(function() {
						document.getElementById('puluh').pause();
						document.getElementById('puluh').currentTime=0;
						document.getElementById('puluh').play();
					}, totalwaktu);
				totalwaktu=totalwaktu+680;
				setTimeout(function() {
						document.getElementById('suarabel1').pause();
						document.getElementById('suarabel1').currentTime=0;
						document.getElementById('suarabel1').play();
					}, totalwaktu);
				totalwaktu=totalwaktu+680;
				
				<?php		
			}elseif($antrian ==100 ){
				//JIKA 100 MAKA MAIKAN SUARA RATUS 
		?>
				setTimeout(function() {
						document.getElementById('suarabel0').pause();
						document.getElementById('suarabel0').currentTime=0;
						document.getElementById('suarabel0').play();
					}, totalwaktu);
				totalwaktu=totalwaktu+680;
				setTimeout(function() {
						document.getElementById('nol').pause();
						document.getElementById('nol').currentTime=0;
						document.getElementById('nol').play();
					}, totalwaktu);
				totalwaktu=totalwaktu+680;
				setTimeout(function() {
						document.getElementById('nol').pause();
						document.getElementById('nol').currentTime=0;
						document.getElementById('nol').play();
					}, totalwaktu);
				totalwaktu=totalwaktu+680;
		<?php
			}elseif($antrian <= 109){
				//JIKA 100 MAKA MAIKAN SUARA RATUS
		?>  
				setTimeout(function() {
						document.getElementById('suarabel0').pause();
						document.getElementById('suarabel0').currentTime=0;
						document.getElementById('suarabel0').play();
					}, totalwaktu);
					totalwaktu=totalwaktu+680;
					setTimeout(function() {
						document.getElementById('nol').pause();
						document.getElementById('nol').currentTime=0;
						document.getElementById('nol').play();
					}, totalwaktu);
					totalwaktu=totalwaktu+680;
				setTimeout(function() {
						document.getElementById('suarabel2').pause();
						document.getElementById('suarabel2').currentTime=0;
						document.getElementById('suarabel2').play();
					}, totalwaktu);
					totalwaktu=totalwaktu+680;
				
			
		<?php		
		}elseif($antrian ==110 or $antrian == 120 or $antrian == 130 or $antrian ==140 or $antrian ==150 or $antrian ==160 or $antrian ==170 or $antrian ==180 or $antrian ==190){
			//JIKA 10 MAKA MAIKAN SUARA SEPULUH
	?>  
				setTimeout(function() {
						document.getElementById('suarabel0').pause();
						document.getElementById('suarabel0').currentTime=0;
						document.getElementById('suarabel0').play();
					}, totalwaktu);
					totalwaktu=totalwaktu+680;
				setTimeout(function() {
						document.getElementById('suarabel1').pause();
						document.getElementById('suarabel1').currentTime=0;
						document.getElementById('suarabel1').play();
					}, totalwaktu);
				totalwaktu=totalwaktu+680;
				setTimeout(function() {
						document.getElementById('nol').pause();
						document.getElementById('nol').currentTime=0;
						document.getElementById('nol').play();
					}, totalwaktu);
				totalwaktu=totalwaktu+680;
				
				<?php		
				}elseif($antrian ==111){
			//JIKA 10 MAKA MAIKAN SUARA SEPULUH
				?>  
				setTimeout(function() {
						document.getElementById('suarabel0').pause();
						document.getElementById('suarabel0').currentTime=0;
						document.getElementById('suarabel0').play();
					}, totalwaktu);
					totalwaktu=totalwaktu+680;
				setTimeout(function() {
						document.getElementById('suarabel1').pause();
						document.getElementById('suarabel1').currentTime=0;
						document.getElementById('suarabel1').play();
					}, totalwaktu);
				totalwaktu=totalwaktu+680;
				setTimeout(function() {
						document.getElementById('suarabel2').pause();
						document.getElementById('suarabel2').currentTime=0;
						document.getElementById('suarabel2').play();
					}, totalwaktu);
				totalwaktu=totalwaktu+680;
				
				
				<?php		
				}elseif($antrian > 111){
			//JIKA 10 MAKA MAIKAN SUARA SEPULUH
				?>  
				setTimeout(function() {
						document.getElementById('suarabel0').pause();
						document.getElementById('suarabel0').currentTime=0;
						document.getElementById('suarabel0').play();
					}, totalwaktu);
					totalwaktu=totalwaktu+680;
				setTimeout(function() {
						document.getElementById('suarabel1').pause();
						document.getElementById('suarabel1').currentTime=0;
						document.getElementById('suarabel1').play();
					}, totalwaktu);
				totalwaktu=totalwaktu+680;
				setTimeout(function() {
						document.getElementById('suarabel2').pause();
						document.getElementById('suarabel2').currentTime=0;
						document.getElementById('suarabel2').play();
					}, totalwaktu);
				totalwaktu=totalwaktu+680;
				
				<?php		
				}elseif($antrian ==120 or $antrian ==130 or $antrian ==140 or $antrian ==150 or $antrian ==160 or $antrian ==170 or $antrian ==180 or $antrian ==190){
			//JIKA 10 MAKA MAIKAN SUARA SEPULUH
				?>  
				setTimeout(function() {
						document.getElementById('suarabel0').pause();
						document.getElementById('suarabel0').currentTime=0;
						document.getElementById('suarabel0').play();
					}, totalwaktu);
				totalwaktu=totalwaktu+680;
				setTimeout(function() {
						document.getElementById('suarabel1').pause();
						document.getElementById('suarabel1').currentTime=0;
						document.getElementById('suarabel1').play();
					}, totalwaktu);
				totalwaktu=totalwaktu+680;
				setTimeout(function() {
						document.getElementById('nol').pause();
						document.getElementById('nol').currentTime=0;
						document.getElementById('nol').play();
					}, totalwaktu);
				totalwaktu=totalwaktu+680;
				
				<?php		
				}elseif($antrian ==200 or $antrian ==300){
			//JIKA 10 MAKA MAIKAN SUARA SEPULUH
				?>  
				setTimeout(function() {
						document.getElementById('suarabel0').pause();
						document.getElementById('suarabel0').currentTime=0;
						document.getElementById('suarabel0').play();
					}, totalwaktu);
				totalwaktu=totalwaktu+680;
				setTimeout(function() {
						document.getElementById('nol').pause();
						document.getElementById('nol').currentTime=0;
						document.getElementById('nol').play();
					}, totalwaktu);
				totalwaktu=totalwaktu+680;
				setTimeout(function() {
						document.getElementById('nol').pause();
						document.getElementById('nol').currentTime=0;
						document.getElementById('nol').play();
					}, totalwaktu);
				totalwaktu=totalwaktu+680;
				
				<?php
			}elseif($antrian < 209){
				//JIKA 100 MAKA MAIKAN SUARA RATUS
		?>  
					setTimeout(function() {
						document.getElementById('suarabel0').pause();
						document.getElementById('suarabel0').currentTime=0;
						document.getElementById('suarabel0').play();
					}, totalwaktu);
					totalwaktu=totalwaktu+680;
				setTimeout(function() {
						document.getElementById('nol').pause();
						document.getElementById('nol').currentTime=0;
						document.getElementById('nol').play();
					}, totalwaktu);
					totalwaktu=totalwaktu+680;
				setTimeout(function() {
						document.getElementById('suarabel2').pause();
						document.getElementById('suarabel2').currentTime=0;
						document.getElementById('suarabel2').play();
					}, totalwaktu);
					totalwaktu=totalwaktu+680;
				<?php		
		}elseif($antrian ==210){
			//JIKA 10 MAKA MAIKAN SUARA SEPULUH
	?>  
				setTimeout(function() {
						document.getElementById('suarabel0').pause();
						document.getElementById('suarabel0').currentTime=0;
						document.getElementById('suarabel0').play();
					}, totalwaktu);
					totalwaktu=totalwaktu+680;
					setTimeout(function() {
						document.getElementById('suarabel1').pause();
						document.getElementById('suarabel1').currentTime=0;
						document.getElementById('suarabel1').play();
					}, totalwaktu);
				totalwaktu=totalwaktu+680;
				setTimeout(function() {
						document.getElementById('nol').pause();
						document.getElementById('nol').currentTime=0;
						document.getElementById('nol').play();
					}, totalwaktu);
				totalwaktu=totalwaktu+680;
				
				<?php		
				}elseif($antrian ==211 ){
			//JIKA 10 MAKA MAIKAN SUARA SEPULUH
				?>  
				setTimeout(function() {
						document.getElementById('suarabel0').pause();
						document.getElementById('suarabel0').currentTime=0;
						document.getElementById('suarabel0').play();
					}, totalwaktu);
					totalwaktu=totalwaktu+680;
					setTimeout(function() {
						document.getElementById('suarabel1').pause();
						document.getElementById('suarabel1').currentTime=0;
						document.getElementById('suarabel1').play();
					}, totalwaktu);
				totalwaktu=totalwaktu+680;
				setTimeout(function() {
						document.getElementById('suarabel2').pause();
						document.getElementById('suarabel2').currentTime=0;
						document.getElementById('suarabel2').play();
					}, totalwaktu);
				totalwaktu=totalwaktu+680;
				
				<?php		
				}elseif($antrian ==220 or $antrian ==230 or $antrian ==240 or $antrian ==250 or $antrian ==260 or $antrian ==270 or $antrian ==280 or $antrian ==290){
			//JIKA 10 MAKA MAIKAN SUARA SEPULUH
				?>  
				setTimeout(function() {
						document.getElementById('suarabel0').pause();
						document.getElementById('suarabel0').currentTime=0;
						document.getElementById('suarabel0').play();
					}, totalwaktu);
				totalwaktu=totalwaktu+680;
				setTimeout(function() {
						document.getElementById('suarabel1').pause();
						document.getElementById('suarabel1').currentTime=0;
						document.getElementById('suarabel1').play();
					}, totalwaktu);
				totalwaktu=totalwaktu+680;
				setTimeout(function() {
						document.getElementById('nol').pause();
						document.getElementById('nol').currentTime=0;
						document.getElementById('nol').play();
					}, totalwaktu);
				totalwaktu=totalwaktu+680;
		<?php
			}else{
				//JIKA LEBIH DARI 100 
				//Karena aplikasi ini masih sederhana maka logina konversi hanya sampai 100
				//Selebihnya akan langsung disebutkan angkanya saja 
				//tanpa kata "RATUS", "PULUH", maupun "BELAS"
		?>
		
		<?php 
			for($i=0;$i<$panjang;$i++){
		?>
		
		totalwaktu=totalwaktu+680;
		setTimeout(function() {
						document.getElementById('suarabel<?php echo $i; ?>').pause();
						document.getElementById('suarabel<?php echo $i; ?>').currentTime=0;
						document.getElementById('suarabel<?php echo $i; ?>').play();
					}, totalwaktu);
		<?php
			}
			}
		?>
		
		
		totalwaktu=totalwaktu+680;
		setTimeout(function() {
						document.getElementById('suarabelsuarabelloket').pause();
						document.getElementById('suarabelsuarabelloket').currentTime=0;
						document.getElementById('suarabelsuarabelloket').play();
					}, totalwaktu);
		
		totalwaktu=totalwaktu+680;
		setTimeout(function() {
						document.getElementById('suarabelloket<?php echo $loket3; ?>').pause();
						document.getElementById('suarabelloket<?php echo $loket3; ?>').currentTime=0;
						document.getElementById('suarabelloket<?php echo $loket3; ?>').play();
					}, totalwaktu);	
					totalwaktu=totalwaktu+1000;
		//setTimeout(function() {
		//				document.getElementById('suarabeel').pause();
		//				document.getElementById('suarabeel').currentTime=0;
		//				document.getElementById('suarabeel').play();
		//			}, totalwaktu);	
  var x = document.getElementById("tombol");
	x.disabled = true;
 	 setTimeout ( "setToBlack()", 10000 );
	}

	function setToBlack ( )
	{
	 var x = document.getElementById("tombol");
	x.disabled = false;
}
  
 
	


</script>