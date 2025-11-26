<?php
session_start();
// unset($_SESSION['nama']);
// unset($_SESSION['jnj_jabatan']);
session_destroy();
header('location: ../');
