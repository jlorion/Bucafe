-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 05, 2024 at 01:10 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cafeting`
--

-- --------------------------------------------------------

--
-- Table structure for table `categorization`
--

CREATE TABLE `categorization` (
  `id` int(100) NOT NULL,
  `Class_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `categorization`
--

INSERT INTO `categorization` (`id`, `Class_name`) VALUES
(1, 'All Items'),
(24, 'ESPRESSO'),
(25, 'CAKES'),
(26, 'BREADS'),
(27, 'BLENDED CREAM'),
(28, 'FRUIT BLENDS');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `id` int(11) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  `access_code` int(11) DEFAULT NULL,
  `admin_access` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`id`, `last_name`, `first_name`, `address`, `access_code`, `admin_access`) VALUES
(1, 'Orion', 'Josef Leonard', 'Calinan, Davao City', 69420, 1);

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE `menu` (
  `id` int(100) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `price` int(100) NOT NULL,
  `inventory` int(100) NOT NULL,
  `classifier` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`id`, `Name`, `price`, `inventory`, `classifier`) VALUES
(14, 'AMERICANO', 100, 100, 'ESPRESSO'),
(15, 'CAPPUCCINO', 135, 100, 'ESPRESSO'),
(18, 'CARAMEL MACCHIATO', 150, 100, 'ESPRESSO'),
(21, 'CAFFE MOCHA', 150, 100, 'ESPRESSO'),
(27, 'LATTE', 150, 100, 'ESPRESSO'),
(28, 'RED VELVET', 150, 100, 'CAKES'),
(29, 'DARK MOCHA CAKE', 150, 100, 'CAKES'),
(30, 'CARROT CAKE', 110, 100, 'CAKES'),
(31, 'OREO CAKE', 110, 100, 'CAKES'),
(32, 'CHEESE CAKE', 120, 100, 'CAKES'),
(33, 'CHOCO MELT', 115, 100, 'BREADS'),
(34, 'BANANA BREAD', 115, 100, 'BREADS'),
(35, 'CORNEDBEEF', 115, 100, 'BREADS'),
(36, 'ENSAYMADA', 120, 100, 'BREADS'),
(38, 'GLAZE DOUGHNUT', 120, 100, 'BREADS'),
(39, 'STRAWBERRIES N CREAM', 180, 99, 'BLENDED CREAM'),
(40, 'COOKIES N CREAM', 185, 100, 'BLENDED CREAM'),
(41, 'VANILLA', 185, 100, 'BLENDED CREAM'),
(43, 'MANGO', 185, 100, 'BLENDED CREAM'),
(44, 'CHOCOLATE', 185, 100, 'BLENDED CREAM'),
(45, 'LEMONADE', 150, 100, 'FRUIT BLENDS'),
(46, 'MANGO PASSION', 150, 100, 'FRUIT BLENDS'),
(47, 'BERRY SENSATION', 150, 100, 'FRUIT BLENDS');

-- --------------------------------------------------------

--
-- Table structure for table `purchases`
--

CREATE TABLE `purchases` (
  `id` int(11) NOT NULL,
  `employeeId` int(11) NOT NULL,
  `total` double NOT NULL,
  `cash` double NOT NULL,
  `moneyback` double NOT NULL,
  `datentime` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `purchases`
--

INSERT INTO `purchases` (`id`, `employeeId`, `total`, `cash`, `moneyback`, `datentime`) VALUES
(1, 1, 500, 1000, 500, '2023-09-06 14:40:10'),
(2, 1, 350, 500, 150, '2023-09-06 14:40:10'),
(4, 1, 701, 1000, -701, '2023-09-11 23:23:05'),
(5, 1, 301, 500, 199, '2023-09-12 00:04:04'),
(6, 1, 551, 500, -51, '2023-09-21 15:28:35'),
(7, 1, 151, 151, 0, '2023-09-21 15:54:27'),
(8, 1, 301, 400, 99, '2023-09-21 16:28:02'),
(9, 1, 51, 51, 0, '2023-09-21 16:28:48'),
(10, 1, 141, 150, 9, '2023-09-21 16:30:06'),
(11, 1, 301, 400, 99, '2023-09-27 20:37:08'),
(12, 1, 154, 200, 46, '2023-09-29 14:25:19'),
(13, 1, 333, 350, 17, '2023-09-30 11:19:18'),
(14, 1, 332, 350, 18, '2023-09-30 15:42:07'),
(15, 1, 88, 100, 12, '2023-09-30 15:45:10'),
(16, 1, 340, 350, 10, '2023-09-30 18:00:11'),
(17, 1, 1277, 1500, 223, '2023-09-30 18:30:32'),
(18, 1, 3320, 4000, 680, '2023-10-02 12:50:46'),
(19, 1, 6380, 7000, 620, '2023-10-02 12:52:30'),
(20, 1, 2335, 3000, 665, '2023-10-02 12:54:53'),
(21, 1, 6620, 7000, 380, '2023-10-02 12:55:36'),
(22, 1, 110, 300, 190, '2023-10-02 16:45:11'),
(23, 1, 150, 200, 50, '2023-10-02 17:28:56'),
(24, 1, 555, 600, 45, '2024-04-23 22:04:09'),
(25, 1, 330, 500, 170, '2024-05-05 15:37:56'),
(26, 1, 420, 500, 80, '2024-05-05 16:15:43');

-- --------------------------------------------------------

--
-- Table structure for table `purchase_items`
--

CREATE TABLE `purchase_items` (
  `purchase_id` int(100) NOT NULL,
  `menu_id` int(100) NOT NULL,
  `amount` int(100) NOT NULL,
  `item_total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `purchase_items`
--

INSERT INTO `purchase_items` (`purchase_id`, `menu_id`, `amount`, `item_total`) VALUES
(23, 28, 1, 150),
(24, 43, 1, 185),
(24, 40, 1, 185),
(24, 44, 1, 185),
(25, 39, 1, 180),
(25, 45, 1, 150),
(26, 14, 1, 120),
(26, 29, 2, 300);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categorization`
--
ALTER TABLE `categorization`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `purchases`
--
ALTER TABLE `purchases`
  ADD PRIMARY KEY (`id`),
  ADD KEY `employeeId` (`employeeId`);

--
-- Indexes for table `purchase_items`
--
ALTER TABLE `purchase_items`
  ADD KEY `purchase_id` (`purchase_id`),
  ADD KEY `menu_id` (`menu_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categorization`
--
ALTER TABLE `categorization`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `menu`
--
ALTER TABLE `menu`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT for table `purchases`
--
ALTER TABLE `purchases`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `purchases`
--
ALTER TABLE `purchases`
  ADD CONSTRAINT `purchases_ibfk_1` FOREIGN KEY (`employeeId`) REFERENCES `employee` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `purchase_items`
--
ALTER TABLE `purchase_items`
  ADD CONSTRAINT `purchase_items_ibfk_1` FOREIGN KEY (`purchase_id`) REFERENCES `purchases` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `purchase_items_ibfk_2` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
