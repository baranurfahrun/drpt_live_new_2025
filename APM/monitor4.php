<?php
	//Initialisasi nilai untuk nomor loket
clearstatcache();
?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
        
  
<meta http-equiv="Content-Type" content="text/php; charset=utf-8" />

<title>Aplikasi Antrrian RSUD Bantaeng</title>
 	<link href="assets/css/bootstrap.css" rel="stylesheet" />
    <!-- FONT AWESOME ICONS  -->
    <link href="assets/css/font-awesome.css" rel="stylesheet" />
    <!-- CUSTOM STYLE  -->
	<link href="css/style.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
<!--
body {
	background-color: #2955d9;
	
}

}
.style22 {
	color: #FFFFFF;
	font-family: Geneva, Arial, Helvetica, sans-serif;
}
.style23 {font-family: Geneva, Arial, Helvetica, sans-serif}
.style24 {font-size: 24px}
-->

</style>

<script src="jquery-latest.js"></script> 
		<script>
		var refreshId = setInterval(function()
		{
			 $('#tempatt').load('antrian_proritas.php');
		}, 1000);
		</script>	
  </head>
<body>
	<div align="center" class="style9 style21 style23">ANTRIAN PENDAFTARAN PASIEN</div>   <center> 
           <table width="1080" height="613"  align="center" border='0' class="kotak2aa kotak22">
              <tr align="center">
                
                  
                 
                    
                      <tr>
                        <td height="30" colspan="2"> <span class="style20">&nbsp;&nbsp;&nbsp;<span class="style23"></span></span><span class="style20 style23"></span></td>
                      </tr>
                      
                      <tr>
				         <td>
 						  <div align="center"><b><span  class="style6 style8 style24">PASIEN PRIORITAS </span></b></div></td>
                      </tr>
                      <tr>
                        <td align ="center"><div>
                          <div class="style12 style15">
                            <h1><div align="center" id="div4"><b>P<span id="tempatt"></span></b></div></h1>
                          </div>
                        </div></td>
					  </tr>
                   
                      <tr><td></td></tr>
                   
                    
               
               
              </tr>
                   <tr>
                     <td height="84" bordercolor="#CCCCCC"><div class="kotak2b kotak22">
                <marquee>
                <div align="center"><span class="style8 style23">Biaya Kamar BPJS <font color='red'>VIP:Rp. 550.000,</font> <font color='blue'> Kelas I:Rp. 350.000, </font> <font color='green'>Kelas II: Rp. 250.000,</font> <font color='yellow'>Kelas III: Rp. 150.000</font></span></div>
                </marquee></div>
                </td>
                
              </tr>
             <?php
	//Initialisasi nilai untuk nomor loket
clearstatcache();
				filesize("antrian_proritas.php");
?> 
</table><center>
</body>
</html>
