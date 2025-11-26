/*
 Navicat Premium Data Transfer

 Source Server         : server nextcloud
 Source Server Type    : MySQL
 Source Server Version : 50568 (5.5.68-MariaDB)
 Source Host           : 192.168.7.252:3306
 Source Schema         : sik_bridging_lab

 Target Server Type    : MySQL
 Target Server Version : 50568 (5.5.68-MariaDB)
 File Encoding         : 65001

 Date: 19/09/2023 09:50:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for LIS_ORDER
-- ----------------------------
DROP TABLE IF EXISTS `LIS_ORDER`;
CREATE TABLE `LIS_ORDER`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `MESSAGE_DT` datetime NULL DEFAULT NULL,
  `ORDER_CONTROL` enum('NW','RP','CA') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `PID` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `PNAME` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `ADDRESS1` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `ADDRESS2` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `ADDRESS3` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `ADDRESS4` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `PTYPE` enum('IN','OP') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `BIRTH_DT` date NULL DEFAULT NULL,
  `SEX` enum('1','2') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `ONO` varchar(17) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `REQUEST_DT` datetime NULL DEFAULT NULL,
  `SOURCE` varchar(80) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `CLINICIAN` varchar(80) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `ROOM_NO` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `PRIORITY` enum('R','U') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `COMMENT` varchar(80) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `VISITNO` varchar(17) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `ORDER_TESTID` varchar(2000) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `FLAG` enum('0','1') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `ONO`(`ONO`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 18 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for LIS_ORDER_DETAIL
-- ----------------------------
DROP TABLE IF EXISTS `LIS_ORDER_DETAIL`;
CREATE TABLE `LIS_ORDER_DETAIL`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `MESSAGE_DT` datetime NULL DEFAULT NULL,
  `ORDER_CONTROL` enum('NW','RP','CA') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `PID` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `PNAME` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `ADDRESS1` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `ADDRESS2` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `ADDRESS3` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `ADDRESS4` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `PTYPE` enum('IN','OP') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `BIRTH_DT` date NULL DEFAULT NULL,
  `SEX` enum('1','2') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `ONO` varchar(17) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `REQUEST_DT` datetime NULL DEFAULT NULL,
  `SOURCE` varchar(80) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `CLINICIAN` varchar(80) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `ROOM_NO` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `PRIORITY` enum('R','U') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `COMMENT` varchar(80) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `VISITNO` varchar(17) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `ORDER_TESTID` varchar(2000) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `FLAG` enum('0','1') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `ONO`(`ONO`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for RESDT
-- ----------------------------
DROP TABLE IF EXISTS `RESDT`;
CREATE TABLE `RESDT`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ONO` varchar(17) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `TEST_CD` char(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `TEST_NM` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `DATA_TYP` enum('NM','ST','FT') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `RESULT_VALUE` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `RESULT_FT` varchar(8192) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `UNIT` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `FLAG` enum('L','H','LL','HH') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `REF_RANGE` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `STATUS` enum('D','F') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `TEST_COMMENT` varchar(300) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `VALIDATE_BY` varchar(60) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `VALIDATE_ON` datetime NULL DEFAULT NULL,
  `DISP_SEQ` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `ORDER_TESTID` varchar(6) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `ORDER_TESTNM` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `TEST_GROUP` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `ITEM_PARENT` varchar(6) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`, `ONO`, `TEST_CD`) USING BTREE,
  UNIQUE INDEX `ONO`(`ONO`, `TEST_CD`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for RESHD
-- ----------------------------
DROP TABLE IF EXISTS `RESHD`;
CREATE TABLE `RESHD`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PID` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `APID` varchar(16) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `PNAME` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `ONO` varchar(17) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `LNO` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `REQUEST_DT` datetime NULL DEFAULT NULL,
  `SOURCE_CD` varchar(6) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `SOURCE_NM` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `CLINICIAN_CD` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `CLINICIAN_NM` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `PRIORITY` enum('R','U') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `COMMENT` varchar(80) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `VISITNO` varchar(17) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`, `ONO`) USING BTREE,
  UNIQUE INDEX `ONO`(`ONO`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for detail_hasil_lab
-- ----------------------------
DROP TABLE IF EXISTS `detail_hasil_lab`;
CREATE TABLE `detail_hasil_lab`  (
  `noorder` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `kd_jenis_prw` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `id_template` int(11) NOT NULL,
  `nilai` varchar(60) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `nilai_rujukan` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `keterangan` varchar(60) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`noorder`, `kd_jenis_prw`, `id_template`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for detail_permintaan_lab
-- ----------------------------
DROP TABLE IF EXISTS `detail_permintaan_lab`;
CREATE TABLE `detail_permintaan_lab`  (
  `noorder` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `kd_jenis_prw` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `id_template` int(11) NOT NULL,
  PRIMARY KEY (`noorder`, `kd_jenis_prw`, `id_template`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for h_item_pemeriksaan
-- ----------------------------
DROP TABLE IF EXISTS `h_item_pemeriksaan`;
CREATE TABLE `h_item_pemeriksaan`  (
  `h_registrasi_no_lab` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `waktu_pemeriksaan_di_isi` datetime NULL DEFAULT NULL,
  `waktu_verifikasi` datetime NULL DEFAULT NULL,
  `hasil_pemeriksaan` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `keterangan` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `nilai_rujukan_tampilan_nilai_rujukan` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `item_pemeriksaan_kode` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `item_pemeriksaan_nama` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `item_pemeriksaan_satuan` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `item_pemeriksaan_metode` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `item_pemeriksaan_no_urut` int(11) NULL DEFAULT NULL,
  `item_pemeriksaan_jenis_input` varchar(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `item_pemeriksaan_is_tampilkan_waktu_periksa` tinyint(4) NULL DEFAULT NULL,
  `kategori_pemeriksaan_nama` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `kategori_pemeriksaan_kode` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `kategori_pemeriksaan_no_urut` int(11) NULL DEFAULT NULL,
  `sub_kategori_pemeriksaan_nama` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `sub_kategori_pemeriksaan_kode` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `sub_kategori_pemeriksaan_no_urut` int(11) NULL DEFAULT NULL,
  `flag_nama` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `flag_kode` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `flag_warna` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `flag_jenis_pewarnaan` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `status_lis_simrs` tinyint(1) NULL DEFAULT NULL,
  INDEX `h_item`(`h_registrasi_no_lab`) USING BTREE,
  INDEX `h_item_pemeriksaan`(`item_pemeriksaan_kode`) USING BTREE,
  CONSTRAINT `h_item_pemeriksaan_ibfk_2` FOREIGN KEY (`h_registrasi_no_lab`) REFERENCES `h_registrasi` (`no_lab`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `h_item_pemeriksaan_ibfk_3` FOREIGN KEY (`item_pemeriksaan_kode`) REFERENCES `mapping_item_adamlab` (`kode_lis`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for h_registrasi
-- ----------------------------
DROP TABLE IF EXISTS `h_registrasi`;
CREATE TABLE `h_registrasi`  (
  `no_lab` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `no_reg_rs` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `diagnosa_awal` text CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL,
  `keterangan_klinis` text CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL,
  `keterangan_hasil` text CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL,
  `expertise` text CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL,
  `waktu_expertise` datetime NULL DEFAULT NULL,
  `waktu_terbit` datetime NULL DEFAULT NULL,
  `waktu_registrasi` datetime NULL DEFAULT NULL,
  `total_bayar` bigint(20) NULL DEFAULT NULL,
  `pasien_no_rm` varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `pasien_nama` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `pasien_jenis_kelamin` enum('L','P') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `pasien_tanggal_lahir` date NULL DEFAULT NULL,
  `pasien_alamat` text CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL,
  `pasien_no_telphone` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `pasien_umur_hari` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `pasien_umur_bulan` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `pasien_umur_tahun` varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `dokter_pengirim_nama` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `dokter_pengirim_kode` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `dokter_pengirim_alamat` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `dokter_pengirim_no_telphone` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `dokter_pengirim_spesialis_nama` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `dokter_pengirim_spesialis_kode` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `unit_asal_nama` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `unit_asal_kode` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `unit_asal_kelas` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `unit_asal_keterangan` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `unit_asal_jenis_nama` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `unit_asal_jenis_kode` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `penjamin_nama` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `penjamin_kode` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `penjamin_jenis_nama` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `penjamin_jenis_kode` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `pasien_nik` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  INDEX `no _lab`(`no_lab`) USING BTREE,
  INDEX `no_reg_rs`(`no_reg_rs`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mapping_item_adamlab
-- ----------------------------
DROP TABLE IF EXISTS `mapping_item_adamlab`;
CREATE TABLE `mapping_item_adamlab`  (
  `id_template` int(11) NOT NULL,
  `kode_lis` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id_template`) USING BTREE,
  INDEX `kode_lis`(`kode_lis`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for permintaan_lab
-- ----------------------------
DROP TABLE IF EXISTS `permintaan_lab`;
CREATE TABLE `permintaan_lab`  (
  `noorder` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `no_rawat` varchar(17) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `no_rkm_medis` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `nm_pasien` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `email` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `jk` enum('L','P') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `tmp_lahir` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `tgl_lahir` date NULL DEFAULT NULL,
  `alamat` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `tgl_permintaan` date NOT NULL,
  `jam_permintaan` time NOT NULL,
  `kode_dokter_perujuk` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `dokter_perujuk` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `status` enum('ralan','ranap') CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `kode_ruang` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `nama_ruang` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `kode_carabayar` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `nama_carabayar` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `informasi_tambahan` varchar(60) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `diagnosa_klinis` varchar(80) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`noorder`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for registrasi
-- ----------------------------
DROP TABLE IF EXISTS `registrasi`;
CREATE TABLE `registrasi`  (
  `no_registrasi` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `waktu_registrasi` datetime NULL DEFAULT NULL,
  `unit_asal_kode` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `unit_asal_jenis` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `unit_asal_nama` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `dokter_pengirim_kode` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `dokter_pengirim_nama` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `pasien_no_rm` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `pasien_nama` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `pasien_jenis_kelamin` varchar(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `pasien_alamat` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `pasien_tanggal_lahir` date NULL DEFAULT NULL,
  `pasien_no_telphone` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `penjamin_kode` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `penjamin_nama` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `pasien_nik` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `kode_icdt` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `nama_icdt` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `kecamatan_id` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `kabupaten_id` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `provinsi_id` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `jenis_registrasi` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`no_registrasi`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tindakan
-- ----------------------------
DROP TABLE IF EXISTS `tindakan`;
CREATE TABLE `tindakan`  (
  `registrasi_no_registrasi` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `kode_tindakan` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `nama_tindakan` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  INDEX `registrasi_no_registrasi`(`registrasi_no_registrasi`) USING BTREE,
  CONSTRAINT `tindakan_ibfk_1` FOREIGN KEY (`registrasi_no_registrasi`) REFERENCES `registrasi` (`no_registrasi`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
