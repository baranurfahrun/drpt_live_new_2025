# Host: localhost  (Version 5.5.5-10.1.38-MariaDB)
# Date: 2023-11-25 03:12:09
# Generator: MySQL-Front 6.0  (Build 2.20)


#
# Structure for table "simrs_ambil_antrian_loket"
#

DROP TABLE IF EXISTS `simrs_ambil_antrian_loket`;
CREATE TABLE `simrs_ambil_antrian_loket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kd_poli` varchar(5) DEFAULT NULL,
  `kd_dokter` varchar(10) DEFAULT NULL,
  `kode` varchar(3) DEFAULT NULL,
  `jumlah` int(11) DEFAULT NULL,
  `tgl` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=294 DEFAULT CHARSET=latin1;

#
# Structure for table "simrs_kode_panggil"
#

DROP TABLE IF EXISTS `simrs_kode_panggil`;
CREATE TABLE `simrs_kode_panggil` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kd_poli` varchar(5) DEFAULT NULL,
  `kd_dokter` varchar(15) DEFAULT NULL,
  `kode` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

#
# Structure for table "simrs_panggil_loket"
#

DROP TABLE IF EXISTS `simrs_panggil_loket`;
CREATE TABLE `simrs_panggil_loket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loket` varchar(200) DEFAULT NULL,
  `kode` varchar(3) DEFAULT NULL,
  `nomor` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

#
# Structure for table "simrs_sudah_panggil_loket"
#

DROP TABLE IF EXISTS `simrs_sudah_panggil_loket`;
CREATE TABLE `simrs_sudah_panggil_loket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loket` varchar(200) DEFAULT NULL,
  `kode` varchar(3) DEFAULT NULL,
  `nomor` varchar(20) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=172 DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;
