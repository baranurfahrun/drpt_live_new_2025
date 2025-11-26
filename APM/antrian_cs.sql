-- phpMyAdmin SQL Dump
-- version 4.4.15.10
-- https://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 24, 2022 at 12:41 PM
-- Server version: 10.1.40-MariaDB
-- PHP Version: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sik`
--

-- --------------------------------------------------------

--
-- Table structure for table `antrian_cs`
--

CREATE TABLE IF NOT EXISTS `antrian_cs` (
  `kd` int(50) NOT NULL,
  `noantrian` varchar(50) NOT NULL,
  `postdate` date NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6199 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `antrian_cs`
--

INSERT INTO `antrian_cs` (`kd`, `noantrian`, `postdate`) VALUES
(6140, '1', '2022-01-24'),
(6141, '2', '2022-01-24'),
(6142, '2', '2022-01-24'),
(6143, '3', '2022-01-24'),
(6144, '3', '2022-01-24'),
(6145, '4', '2022-01-24'),
(6146, '4', '2022-01-24'),
(6147, '5', '2022-01-24'),
(6148, '5', '2022-01-24'),
(6149, '6', '2022-01-24'),
(6150, '6', '2022-01-24'),
(6151, '7', '2022-01-24'),
(6152, '8', '2022-01-24'),
(6153, '8', '2022-01-24'),
(6154, '9', '2022-01-24'),
(6155, '10', '2022-01-24'),
(6156, '10', '2022-01-24'),
(6157, '11', '2022-01-24'),
(6158, '12', '2022-01-24'),
(6159, '12', '2022-01-24'),
(6160, '13', '2022-01-24'),
(6161, '14', '2022-01-24'),
(6162, '15', '2022-01-24'),
(6163, '15', '2022-01-24'),
(6164, '16', '2022-01-24'),
(6165, '16', '2022-01-24'),
(6166, '17', '2022-01-24'),
(6167, '17', '2022-01-24'),
(6168, '18', '2022-01-24'),
(6169, '18', '2022-01-24'),
(6170, '19', '2022-01-24'),
(6171, '20', '2022-01-24'),
(6172, '20', '2022-01-24'),
(6173, '21', '2022-01-24'),
(6174, '21', '2022-01-24'),
(6175, '22', '2022-01-24'),
(6176, '23', '2022-01-24'),
(6177, '23', '2022-01-24'),
(6178, '24', '2022-01-24'),
(6179, '24', '2022-01-24'),
(6180, '25', '2022-01-24'),
(6181, '25', '2022-01-24'),
(6182, '26', '2022-01-24'),
(6183, '26', '2022-01-24'),
(6184, '27', '2022-01-24'),
(6185, '27', '2022-01-24'),
(6186, '28', '2022-01-24'),
(6187, '28', '2022-01-24'),
(6188, '29', '2022-01-24'),
(6189, '29', '2022-01-24'),
(6190, '30', '2022-01-24'),
(6191, '31', '2022-01-24'),
(6192, '31', '2022-01-24'),
(6193, '32', '2022-01-24'),
(6194, '32', '2022-01-24'),
(6195, '33', '2022-01-24'),
(6196, '33', '2022-01-24'),
(6197, '34', '2022-01-24'),
(6198, '34', '2022-01-24');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `antrian_cs`
--
ALTER TABLE `antrian_cs`
  ADD PRIMARY KEY (`kd`) USING BTREE;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `antrian_cs`
--
ALTER TABLE `antrian_cs`
  MODIFY `kd` int(50) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6199;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
