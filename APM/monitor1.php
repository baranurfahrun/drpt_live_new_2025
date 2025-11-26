<?php
	//Initialisasi nilai untuk nomor loket

?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
<script src="jquery-1.7.2.js" type="text/javascript"></script>
  <script src="jquery-latest.js"></script> 
		<script>
		var refreshId = setInterval(function()
		{
			 $('#tempat').load('antrian_lama.php');
		}, 1000);
		</script>	
		<script>
		var refreshId = setInterval(function()
		{
			 $('#tempattt').load('antrian_baru.php');
		}, 1000);
		</script>	
  
  </head>
<body>
	<div align="center" class="style9 style21 style23">ANTRIAN PENDAFTARAN PASIEN</div>    
            <table width="1080" height="613"  align="center">
              <tr>
                <td width="521"  bordercolor="#CCCCCC"><div class="kotak2 kotak22">
                  
                  <div align="center">
                    <table width="586" height="489" >
                      <tr>
                        <td width="576" height="34">&nbsp;<img src="image/video.png" width="39" height="22" />&nbsp;<span class="style20 style22">Video Informasi</span></td>
                      </tr>
                      <tr>
                        <td rowspan="2"><video autoplay=99999 muted src="image/iklan2.mp4" controls width="576" auto height="447" loop=0 type="video/mp4">
                          <div align="left"></div>
                        </video></td>
                      </tr>
                    </table>
                  </div>
                </div></td>
				<td width="200" rowspan="2"><div class="kotak2c">
			      <div align="center"></div></td>
                <td width="480"  rowspan="2"bordercolor="#CCCCCC"><div class="kotak2a kotak22">
                  
                  <div align="center">
                    <table width="581" >
                      <tr>
                        <td height="30" colspan="2"><span class="style20">&nbsp;&nbsp;&nbsp;<img src="image/antrian.png" width="36" height="29" />&nbsp;&nbsp;<span class="style23">Status Antrian</span></span><span class="style20 style23"></span></td>
                      </tr>
                      <tr>
                        <td width="316"><div class="kotak kotak1">
                          <div class="style12 style15">
                            <div align="left">L<span id="tempat"></span></div>
                          </div>
                        </div></td>
				        <td width="253"><div align="center"><span class="style6 style8 style24">PASIEN LAMA </span></div></td>
                      </tr>
                      <br>
                      <tr>
                          
                        <td><div class="kotak kotak1">
                          <div class="style12 style15">
                            <div align="left">B<span id="tempattt"></div>
                          </div>
                        </div></td>
				        <td><div align="center"><span class="style6 style8 style24">PASIEN BARU </span></div></td>
                      </tr>
                     
                    </table>
                  </div>
                </div></td>
              </tr>
              <tr>
                <td height="84" bordercolor="#CCCCCC"><div class="kotak2b kotak22">
                <marquee>
                <div align="center"><span class="style8 style23">Biaya Kamar BPJS <font color='red'>VIP:Rp. 550.000,</font> <font color='blue'> Kelas I:Rp. 350.000, </font> <font color='green'>Kelas II: Rp. 250.000,</font> <font color='yellow'>Kelas III: Rp. 150.000</font></span>                </div>
                </div>
                </marquee></td>
              </tr>
</table>
</body>
</html>
