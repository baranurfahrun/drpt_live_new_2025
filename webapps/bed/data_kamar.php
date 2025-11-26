<?php
 require_once('conf/conf.php');
 header("Expires: Mon, 26 Jul 1997 05:00:00 GMT"); 
 header("Last-Modified: ".gmdate("D, d M Y H:i:s")." GMT"); 
 header("Cache-Control: no-store, no-cache, must-revalidate"); 
 header("Cache-Control: post-check=0, pre-check=0", false);
 header("Pragma: no-cache"); // HTTP/1.0
 date_default_timezone_set("Asia/Bangkok");
 $tanggal= mktime(date("m"),date("d"),date("Y"));
 $jam=date("H:i");
 
 
  $_sql="Select kd_bangsal,kelas,kode_kelas_aplicare from aplicare_ketersediaan_kamar" ;  
                          $hasil=bukaquery($_sql);

                          while ($data = mysqli_fetch_array ($hasil)){
                           
                                     
                                     $d1=mysqli_fetch_array(bukaquery("select count(kelas) from kamar where statusdata='1' and kelas='".$data['kelas']."' and kd_bangsal='".$data['kd_bangsal']."'"));
                                     
                                     $d2=mysqli_fetch_array(bukaquery("select count(kelas) from kamar where statusdata='1' and (status='ISI' or status='DIBOOKING' or status='DIBERSIHKAN') and kelas='".$data['kelas']."'  and kd_bangsal='".$data['kd_bangsal']."' "));
                                     
                                     $d3=mysqli_fetch_array(bukaquery("select count(kelas) from kamar where statusdata='1'  and status='KOSONG' and kelas='".$data['kelas']."'  and kd_bangsal='".$data['kd_bangsal']."' "));
                                    

                              $d4=bukaquery("update aplicare_ketersediaan_kamar set kapasitas='".$d1[0]."', tersedia='".$d3[0]."', tersediapriawanita='".$d3[0]."' where  kelas='".$data['kelas']."' and kd_bangsal='".$data['kd_bangsal']."' ");
                                     
                          }
                          
?>

<br>
         <div class="col-sm-7 ">
<div style="color:#f00; font-family:'Orbitron', sans-serif;font-size:30px;"><center>JADWAL OPERASI HARI INI</center></div>
                    <div class="col-sm-12"> 

                    <div class="queue well text-center " style="background: rgb(3,91,200);
background: linear-gradient(36deg, rgba(3,91,200,1) 0%, rgba(110,255,251,1) 100%);border-color:#3c8dbc;min-height:50px;height:50px;padding:0;margin:0 0 0 0">
                        
                        <div class="text-center" style="display:inline-block;padding:13px 0">
                            <h3 class="token" style="color:#FFF;">No. RAWAT </h3>
                        </div>
                        
                    <div class="text-center" style="width:180px; height:100%;float:right;background: rgb(3,91,200);
background: linear-gradient(36deg, rgba(3,91,200,1) 0%, rgba(97,154,244,1) 100%);color:#FFF"><h3>SELESAI</h3></div>
                    <div class="text-center" style="width:180px; height:100%;float:right;background: rgb(3,91,200);
background: linear-gradient(36deg, rgba(3,91,200,1) 0%, rgba(97,154,244,1) 100%);color:#FFF"><h3>MULAI</h3></div>
                    <div class="text-center" style="width:180px; height:100%;float:right;background: rgb(3,91,200);
background: linear-gradient(36deg, rgba(3,91,200,1) 0%, rgba(97,154,244,1) 100%);color:#FFF"><h3>STATUS</h3></div>
                </div>


    <?php  
$tanggal=date('Y-m-d');
                          $_sql="Select * from booking_operasi where tanggal='$tanggal' order by tanggal" ;  
                          $hasil=bukaquery($_sql);
 
                          while ($data = mysqli_fetch_array ($hasil)){
                         
                ?>
                

 <div class=" well text-center " style="
border:0px solid #fff ;
 
background: rgb(126,249,243);
background: linear-gradient(36deg, rgba(126,249,243,1) 0%, rgba(145,193,250,0.9416141456582633) 100%);
border-color:#3c8dbc;min-height:50px;height:50px;padding:0;margin:0 0 0 0">
                        
                        <div  style="display:inline-block;padding-bottom:10px 0">
                            <h4 class="token" style="color:#000; "> 
<?php
 echo $data[0];
?>
 </h4>
                        </div>
                        
                    <div class="text-center" style="width:180px; background: rgb(126,249,243);
background: linear-gradient(36deg, rgba(126,249,243,1) 0%, rgba(145,193,250,0.9416141456582633) 100%); height:100%;float:right; color:#000; "><h4 class="token" >
<?php
 echo $data['jam_selesai'];
?> WIB</h4></div>
                    <div class="text-center" style="width:180px; background: rgb(126,249,243);
background: linear-gradient(36deg, rgba(126,249,243,1) 0%, rgba(145,193,250,0.9416141456582633) 100%); height:100%;float:right; color:#000; "><h4 class="token" >
<?php
 echo $data['jam_mulai'];
?> WIB</h4></div>
                    <div class="text-center" style="width:180px; background: rgb(126,249,243);
background: linear-gradient(36deg, rgba(126,249,243,1) 0%, rgba(145,193,250,0.9416141456582633) 100%); height:100%;float:right; color:#000; "><h4 class="token" >
<?php
 echo $data['status'];
?></h4></div>
                </div>

<?php

} 

?>
<!--

 <div class="queue well text-center " style=" border:0px solid #fff; background: rgb(126,249,243);
background: linear-gradient(36deg, rgba(126,249,243,1) 0%, rgba(145,193,250,0.9416141456582633) 100%);
border-color:#3c8dbc;min-height:50px;height:50px;padding:0;margin:0 0 0 0">
                        
                        <div class="text-center" style=";display:inline-block;padding:10px 0">
                            <h3 class="token" style="color:#000; "> <center><b>TIDAK ADA JADWAL TINDAKAN OPERASI</b> </center></h3>
                        </div>
                         
                </div>

-->
                </div>
                </div>
                







         
         
         <div class="col-sm-5 ">

<div style="color:#f00; font-family:'Orbitron', sans-serif;font-size:30px;"><center>INFORMASI KAMAR</center></div>

                    <div class="col-sm-12"> 
                    <div class="queue well text-center " style="background-color:#6094d4; border:0px #46e9fc solid;min-height:50px;height:50px;padding:0;margin:0 0 0 0">
                        
                        <div class="text-center" style="display:inline-block;padding:13px 0">
                            <h3 class="token" style="color:#fff;">KAMAR</h3>
                        </div>
                        
                    <div class="text-center" style="width:100px; height:100%;float:right;background:#1b68c7;color:#fff"><h3>TOTAL</h3></div>
                    <div class="text-center" style="width:120px; height:100%;float:right;background:#3b7dcf;color:#fff"><h3>SEDIA</h3></div>
                </div>
                </div>
                
                <?php  
                          $_sql="Select kode_kelas_aplicare,kelas from aplicare_ketersediaan_kamar group by kode_kelas_aplicare order by kelas asc" ;  
                          $hasil=bukaquery($_sql);

                          while ($data = mysqli_fetch_array ($hasil)){
                         
                ?>
                
                
                    <div class="col-sm-12"> 
                    <div class="queue well " style="background: rgb(126,249,243);
background: linear-gradient(36deg, rgba(126,249,243,1) 0%, rgba(145,193,250,0.9416141456582633) 100%);

border:0px #46e9fc solid; color:#000; min-height:50px;height:50px;padding:0;margin:0 0 0 0">
                        
                    <div class="text-left" style="display:inline-block;padding-left:15px;">
                    <h2 class="token"> 
                    <?php
                     $n=$data['kode_kelas_aplicare'];
                     if($n=='ICU'){
                     echo 'Kamar ICU';
                     } else if($n=='ISO') {
                       echo 'Ruang ISOLASI';
                      } else if($n=='KL1') {
                       echo 'Kamar Kelas 1';
                      } else if($n=='KL2') {
                       echo 'Kamar Kelas 2';
                      } else if($n=='KL3') {
                       echo 'Kamar Kelas 3';
                      } else if($n=='NIC') {
                       echo 'NICU';
                      } else if($n=='PIC') {
                       echo 'PICU';
                      } else if($n=='SAL') {
                       echo 'Kamar Bersalin';
                      } else if($n=='UTM') {
                       echo 'Kamar Perina';
                      } else if($n=='VIP') {
                       echo 'Kamar VIP';
                      } else if($n=='VVP') {
                       echo 'Kamar VVIP';
                      }

                    ?>
                     </h2>
                    </div>  
                    <div class="text-center" style="padding-top:10px; width:100px; height:100%;  float:right;
background: rgb(126,249,243);
background: linear-gradient(36deg, rgba(126,249,243,1) 0%, rgba(145,193,250,0.9416141456582633) 100%);
color:#000"><h2 class="token">

                    <?php
                     
                    $data2=mysqli_fetch_array(bukaquery("select sum(kapasitas) from aplicare_ketersediaan_kamar where kode_kelas_aplicare='$data[kode_kelas_aplicare]' "));
                    echo $data2[0];
              ?>


                    </h2></div>
                    
                    <div class="text-center" style="padding-top:10px; width:120px; height:100%; bottom:10px;float:right;
background: rgb(126,249,243);
background: linear-gradient(36deg, rgba(126,249,243,1) 0%, rgba(145,193,250,0.9416141456582633) 100%);
color:#000"><h2 class="token">
                    <?php
                     
                     $data2=mysqli_fetch_array(bukaquery("select sum(tersedia) from aplicare_ketersediaan_kamar where kode_kelas_aplicare='$data[kode_kelas_aplicare]' "));
                     echo $data2[0];
               ?>
                    </h2></div>
                  </div>
                </div>
                
                <?php }
                ?> 
                   
                </div>
                
                
                
      </div>
 
