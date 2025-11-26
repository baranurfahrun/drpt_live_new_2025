<?php

		$koneksi = mysqli_connect("localhost", "user_database", "pass_database", "Nama_database");

		// Check connection
		if (mysqli_connect_errno()) {
			echo "Koneksi database gagal : " . mysqli_connect_error();
		}

		