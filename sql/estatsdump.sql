-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: mysql
-- Generation Time: Feb 28, 2023 at 06:19 PM
-- Server version: 8.0.31
-- PHP Version: 8.0.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `estatsdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `estats`
--

DROP TABLE IF EXISTS `estats`;
CREATE TABLE `estats` (
    `region` varchar(20) NOT NULL,
    `verbrauchAnteil` decimal(20,4) NOT NULL,
    `verbrauch` decimal(20,4) NOT NULL,
    `preis` bigint NOT NULL,
    `emission` decimal(20,4) NOT NULL,
    `import` decimal(20,4) NOT NULL,
    `export` decimal(20,4) NOT NULL,
    `date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `voraussagen`;
CREATE TABLE voraussagen(
    region               VARCHAR(20) NOT NULL
    ,vorverbrauch        decimal(20,4) NOT NULL
    ,vorpreis            BIGINT NOT NULL
    ,voremission         decimal(20,4) NOT NULL
    ,vorimport           decimal(20,4) NOT NULL
    ,vorexport           decimal(20,4) NOT NULL
    ,hour				 INT NOT NULL
    ,PRIMARY KEY(hour)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `estats`
--

INSERT INTO `estats` (`region`, `verbrauchAnteil`, `verbrauch`, `preis`, `emission`, `import`, `export`, `date`) VALUES
('Burgenland', '3.6323', '42.6575', 15, '7.6457', '3.0195', '2.0828', '2023-02-28 17:03:23'),
('Burgenland', '4.0943', '47.6637', 15, '5.7292', '2.9660', '2.5493', '2023-02-28 17:41:40'),
('Kaernten', '6.1366', '72.0679', 20, '22.5424', '8.3476', '4.9803', '2023-02-28 17:03:23'),
('Kaernten', '9.3675', '109.0509', 13, '19.8559', '12.7471', '6.1814', '2023-02-28 17:41:40'),
('Niederoesterreich', '20.7385', '243.5514', 16, '47.9072', '29.4160', '12.7849', '2023-02-28 17:03:23'),
('Niederoesterreich', '20.0614', '233.5430', 14, '39.9602', '15.8864', '11.9569', '2023-02-28 17:41:40'),
('Oberoesterreich', '23.2620', '273.1868', 17, '34.7097', '18.3619', '11.3848', '2023-02-28 17:03:23'),
('Oberoesterreich', '19.2747', '224.3848', 16, '58.4883', '26.0375', '12.2146', '2023-02-28 17:41:40'),
('Oesterreich', '100.0000', '1174.3932', 16, '217.7304', '117.0317', '63.3527', '2023-02-28 17:03:23'),
('Oesterreich', '100.0000', '1164.1397', 14, '238.8000', '126.2116', '62.3650', '2023-02-28 17:41:40'),
('Salzburg', '5.5316', '64.9625', 20, '9.0669', '7.5033', '3.9008', '2023-02-28 17:03:23'),
('Salzburg', '6.5912', '76.7305', 12, '15.3114', '6.8400', '3.4650', '2023-02-28 17:41:40'),
('Steiermark', '17.6011', '206.7058', 16, '31.4604', '19.5883', '10.2698', '2023-02-28 17:03:23'),
('Steiermark', '14.8670', '173.0722', 16, '34.4990', '22.4602', '8.6427', '2023-02-28 17:41:40'),
('Tirol', '6.9473', '81.5881', 16, '14.6209', '9.1077', '3.9897', '2023-02-28 17:03:23'),
('Tirol', '9.0157', '104.9556', 17, '16.9230', '8.3441', '3.8174', '2023-02-28 17:41:40'),
('Vorarlberg', '5.3622', '62.9735', 12, '12.1275', '7.5376', '2.9871', '2023-02-28 17:03:23'),
('Vorarlberg', '5.1912', '60.4327', 14, '8.9520', '6.2247', '3.0452', '2023-02-28 17:41:40'),
('Wien', '10.7885', '126.6997', 13, '37.6497', '14.1498', '10.9725', '2023-02-28 17:03:23'),
('Wien', '11.5370', '134.3063', 13, '39.0810', '24.7056', '10.4925', '2023-02-28 17:41:40');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `estats`
--
ALTER TABLE `estats`
    ADD PRIMARY KEY (`region`,`date`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
