<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="csss/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="fontawesome-free-5.6.3-web/csss/all.css">
    <link href="csss/gijgo.min.css" rel="stylesheet" type="text/css" />
</head>

<body>
<center>
   <h1>Menampilkan Modal Bootstrap!</h1>
   <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">Tampilkan!</button>
</center>	

<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog">
   <div class="modal-dialog" role="document">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Tutup"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">Judul modal</h4>
         </div>

         <div class="modal-body">
            Isi dari modal yang akan ditampilkan, letakkan di sini...
         </div>

         <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Tutup</button>
            <button type="button" class="btn btn-primary">Aksi</button>
         </div>
      </div>
   </div>
</div>

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>