<?php
session_start();
include('config.php');
$username = $_POST['username'];
$password = $_POST['password'];

$query = mysqli_query($koneksi, "SELECT * FROM pegawai WHERE nik='$username' AND nik='$password'");
if (mysqli_num_rows($query) == 1) {
    header('location:../app');
    $user = mysqli_fetch_array($query);
    $_SESSION['nama'] =  $user['nama'];
    $_SESSION['jnj_jabatan'] = $user['jnj_jabatan'];
    // echo "login berhasil";
} elseif ($username == '' || $password == '') {
    header('location:../index.php?error=2');
    // echo "login berhasil";
} else {
    header('location:../index.php?error=1');
    // echo "login gagal";
}
