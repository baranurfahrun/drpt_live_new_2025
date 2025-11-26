<?php
	//Initialisasi nilai untuk nomor loket
clearstatcache();
              filesize("antrian_lama.php");
             	filesize("antrian_baru.php");
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
   <script type="text/javascript" src="slide/js/slidergezz.1.3.8.js"></script>
 <link rel="stylesheet" type="text/css" href="slide/css/slidergezz.css" />
  
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
                <td width="521"  bordercolor="#CCCCCC">
				<div class="kotak2 kotak22">
                  
                  <div align="left">
                    <table width="586" height="489" >
                     
                      <tr>
                    
					    <div id="slider_container_2">
						<div id="SliderName_2" class="SliderName_2">
							<img src="slide/images/icu1.png" width="600px" height="500px" alt="Selamat Datang" title="Selamat Datang"/>
							<div class="SliderName_2Description">Featured model: <strong>Charlize Theron</strong></div>
							<img src="slide/images/kelas1.png" width="600px" height="500px" alt="Gezz Fashion Style" title="Gezz Fashion Style" />
     						<div class="SliderName_2Description">Featured model: <strong>Charlize Theron</strong></div>
    						<img src="slide/images/kelas2.png" width="600px" height="500px" alt="Selamat Berbelanja" title="Selamat Berbelanja" />
    						<div class="SliderName_2Description">Featured model: <strong>Charlize Theron</strong></div>
                            <img src="slide/images/kelas3.png" width="600px" height="500px" alt="Selamat Berbelanja" title="Selamat Berbelanja" />
    						<div class="SliderName_2Description">Featured model: <strong>Charlize Theron</strong></div>
                          	<img src="slide/images/lab1.png" width="600px" height="500px" alt="Selamat Berbelanja" title="Selamat Berbelanja" />
    						<div class="SliderName_2Description">Featured model: <strong>Charlize Theron</strong></div>
                            <img src="slide/images/rad1.png" width="600px" height="500px" alt="Selamat Berbelanja" title="Selamat Berbelanja" />
    						<div class="SliderName_2Description">Featured model: <strong>Charlize Theron</strong></div>
                         	<img src="slide/images/perinalogi.png" width="600px" height="500px" alt="Selamat Berbelanja" title="Selamat Berbelanja" />
    						<div class="SliderName_2Description">Featured model: <strong>Charlize Theron</strong></div>
                         	<img src="slide/images/hcu2.png" width="600px" height="500px" alt="Selamat Datang" title="Selamat Datang"/>
							<div class="SliderName_2Description">Featured model: <strong>Charlize Theron</strong></div>
							<img src="slide/images/kelas12.png" width="600px" height="500px" alt="Gezz Fashion Style" title="Gezz Fashion Style" />
     						<div class="SliderName_2Description">Featured model: <strong>Charlize Theron</strong></div>
    						<img src="slide/images/kelas22.png" width="600px" height="500px" alt="Selamat Berbelanja" title="Selamat Berbelanja" />
    						<div class="SliderName_2Description">Featured model: <strong>Charlize Theron</strong></div>
                          	<img src="slide/images/lab2.png" width="600px" height="500px" alt="Selamat Berbelanja" title="Selamat Berbelanja" />
    						<div class="SliderName_2Description">Featured model: <strong>Charlize Theron</strong></div>
                            <img src="slide/images/rad2.png" width="600px" height="500px" alt="Selamat Berbelanja" title="Selamat Berbelanja" />
    						<div class="SliderName_2Description">Featured model: <strong>Charlize Theron</strong></div>
						</div>
			 				</div>
					
                      </tr>
                    </table>
                  </div>
                
				</div>
				</td>
				<td width="200" rowspan="2"><div class="kotak2c">
			      <div align="center"></div></div></td>
                <td width="480"  rowspan="2"bordercolor="#CCCCCC"><div class="kotak2a kotak22">
                  
                  <div align="center">
                    <table width="581" >
                     <tr>
                        <td height="30" colspan="2"><span class="style20">&nbsp;&nbsp;&nbsp;</span><span class="style20 style23"></span></td>
                      </tr>
                      <tr>
                         <td width="253"><div align="center"><span class="style6 style8 style24">PASIEN LAMA</span></div></td>
                        </tr>
                      <tr>
                        
                        <td  width="316" align="center" ><div class="kotak kotak1">
                          <div class="style12 style15" align="center">
                          L<span id="tempat" ></span>
                        </div></div></td>
                        
                      </tr>
                      
                   <tr>
                         <td><div align="center"><span class="style6 style8 style24"><br>PASIEN BARU </span></div></td>
                        </tr>
                      <tr>
                      
                         <td  width="316" align="center" ><div class="kotak kotak1">
                          <div class="style12 style15" align="center">
                          B<span  id="tempattt" ></span>
                        </div></div></td>
				       
                        
                        
                        
                        
                        
                        
                      </tr>
                    
                      <tr>
                       
                    </table>
                  </div>
                </div></td>
              </tr>
              <tr>
                <td height="84" bordercolor="#CCCCCC"><div class="kotak2b kotak22">
                <marquee>
                 <div align="center"><span class="style8 style23">Biaya Kamar BPJS <font color='red'>VIP:Rp. 550.000,</font> <font color='blue'> Kelas I:Rp. 350.000, </font> <font color='green'>Kelas II: Rp. 250.000,</font> <font color='yellow'>Kelas III: Rp. 150.000</font></span></div>
                </marquee></div></td>
              </tr>
<?php
	//Initialisasi nilai untuk nomor loket
	clearstatcache();
              filesize("antrian_lama.php");
             	filesize("antrian_baru.php");
              
?>
</table>
   <script type="text/javascript">
    effectsSlide = 'rain,stairs,fade,blinds';
    var gezzSlider = Slidergezz.slider({container: 'SliderName_2', width: 600, height: 500, effects: effectsSlide,
     display: {
      autoplay: 4000,
      loading: {background: '#ffffff', opacity: 0.1, image: 'images/loading-slide.gif'},
      buttons: {hide: true, opacity: 1, prev: {className: 'SliderNamePrev_2', label: ''}, next: {className: 'SliderNameNext_2', label: ''}}
      }
    });
    </script>
</body>
</html>
