<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <!-- slidergezz.js and slidergezz.css -->
 <script type="text/javascript" src="js/slidergezz.1.3.8.js"></script>
 <link rel="stylesheet" type="text/css" href="css/slidergezz.css" />
 <!-- /slidergezz.js and slidergezz.css -->
</head>
<body>
 <div id="slider_container_2">
   <div id="SliderName_2" class="SliderName_2">
    <img src="images/slide.png" width="1000px" height="236px" alt="Selamat Datang" title="Selamat Datang"/>
     <div class="SliderName_2Description">Featured model: <strong>Charlize Theron</strong></div>
    <img src="images/slide2.png" width="1000px" height="236px" alt="Gezz Fashion Style" title="Gezz Fashion Style" />
     <div class="SliderName_2Description">Featured model: <strong>Charlize Theron</strong></div>
    <img src="images/slide3.png" width="700" height="450" alt="Selamat Berbelanja" title="Selamat Berbelanja" />
     <div class="SliderName_2Description">Featured model: <strong>Charlize Theron</strong></div>
   </div>
             
   <script type="text/javascript">
    effectsSlide = 'rain,stairs,fade,blinds';
    var gezzSlider = Slidergezz.slider({container: 'SliderName_2', width: 1000, height: 236, effects: effectsSlide,
     display: {
      autoplay: 4000,
      loading: {background: '#ffffff', opacity: 0.1, image: 'images/loading-slide.gif'},
      buttons: {hide: true, opacity: 1, prev: {className: 'SliderNamePrev_2', label: ''}, next: {className: 'SliderNameNext_2', label: ''}}
      }
    });
    </script>
 </div>
</body>
</html>