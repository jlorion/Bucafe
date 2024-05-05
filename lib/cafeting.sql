-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 06, 2023 at 09:23 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.0.19

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
-- Table structure for table `admin_access`
--

CREATE TABLE `admin_access` (
  `id` int(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `access_code` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin_access`
--

INSERT INTO `admin_access` (`id`, `name`, `access_code`) VALUES
(1, 'jl', 3061);

-- --------------------------------------------------------

--
-- Table structure for table `categorization`
--

CREATE TABLE `categorization` (
  `id` int(100) NOT NULL,
  `Class_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
-- Table structure for table `menu`
--

CREATE TABLE `menu` (
  `id` int(100) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `price` int(100) NOT NULL,
  `inventory` int(100) NOT NULL,
  `classifier` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`id`, `Name`, `price`, `inventory`, `classifier`) VALUES
(14, 'AMERICANO', 120, 84, 'ESPRESSO'),
(15, 'CAPPUCCINO', 135, 91, 'ESPRESSO'),
(18, 'CARAMEL MACCHIATO', 150, 92, 'ESPRESSO'),
(21, 'CAFFE MOCHAS', 160, 105, 'ESPRESSO'),
(27, 'LATTE', 150, 92, 'ESPRESSO'),
(28, 'RED VELVET', 150, 94, 'CAKES'),
(29, 'DARK MOCHA CAKE', 150, 96, 'CAKES'),
(30, 'CARROT CAKE', 110, 93, 'CAKES'),
(31, 'OREO CAKE', 110, 91, 'CAKES'),
(32, 'CHEESE CAKE', 120, 100, 'CAKES'),
(33, 'CHOCO MELT', 115, 99, 'BREADS'),
(34, 'BANANA BREAD', 115, 100, 'BREADS'),
(35, 'CORNEDBEEF', 115, 100, 'BREADS'),
(36, 'ENSAYMADA', 120, 100, 'BREADS'),
(38, 'GLAZE DOUGHNUT', 120, 100, 'BREADS'),
(39, 'STRAWBERRIES N CREAM', 180, 100, 'BLENDED CREAM'),
(40, 'COOKIES N CREAM', 185, 96, 'BLENDED CREAM'),
(41, 'VANILLA', 185, 97, 'BLENDED CREAM'),
(43, 'MANGO', 185, 96, 'BLENDED CREAM'),
(44, 'CHOCOLATE', 185, 100, 'BLENDED CREAM'),
(45, 'LEMONADE', 150, 100, 'FRUIT BLENDS'),
(46, 'MANGO PASSION', 150, 100, 'FRUIT BLENDS'),
(47, 'BERRY SENSATION', 150, 100, 'FRUIT BLENDS');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `purchase_id` int(100) NOT NULL,
  `menu_id` int(100) NOT NULL,
  `amount` int(100) NOT NULL,
  `item_total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`purchase_id`, `menu_id`, `amount`, `item_total`) VALUES
(23, 28, 1, 150),
(24, 15, 1, 135),
(24, 31, 1, 110),
(25, 21, 1, 150),
(25, 21, 0, 0),
(25, 21, 1, 150),
(26, 14, 5, 600),
(26, 31, 5, 550),
(27, 15, 1, 135),
(28, 15, 3, 405),
(28, 28, 3, 450),
(28, 41, 3, 555),
(29, 14, 2, 240),
(29, 30, 2, 220),
(30, 14, 2, 240),
(30, 28, 2, 300),
(31, 14, 1, 120),
(32, 18, 2, 300),
(32, 40, 2, 370),
(32, 43, 2, 370),
(33, 18, 2, 300),
(33, 40, 2, 370),
(33, 43, 2, 370),
(34, 27, 1, 150),
(34, 33, 1, 115),
(34, 29, 1, 150),
(35, 27, 1, 150),
(35, 18, 1, 150),
(35, 31, 1, 110),
(36, 14, 1, 120),
(36, 27, 1, 150),
(37, 14, 1, 120),
(37, 27, 1, 150),
(37, 30, 1, 110),
(37, 31, 1, 110),
(38, 18, 1, 150),
(38, 27, 1, 150),
(39, 15, 1, 135),
(39, 27, 2, 300),
(40, 18, 2, 300),
(40, 27, 1, 150),
(41, 14, 2, 240),
(41, 30, 4, 440),
(42, 14, 2, 240),
(42, 15, 2, 270),
(43, 15, 1, 135),
(43, 31, 1, 110),
(43, 29, 3, 450);

-- --------------------------------------------------------

--
-- Table structure for table `purchases`
--

CREATE TABLE `purchases` (
  `id` int(11) NOT NULL,
  `total` double NOT NULL,
  `cash` double NOT NULL,
  `moneyback` double NOT NULL,
  `datentime` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `purchases`
--

INSERT INTO `purchases` (`id`, `total`, `cash`, `moneyback`, `datentime`) VALUES
(22, 110, 300, 190, '2023-10-02 16:45:11'),
(23, 150, 200, 50, '2023-10-02 17:28:56'),
(24, 245, 300, 55, '2023-10-04 19:41:44'),
(25, 300, 300, 0, '2023-10-05 14:19:05'),
(26, 1150, 2000, 850, '2023-10-05 14:52:55'),
(27, 135, 150, 15, '2023-10-05 14:56:54'),
(28, 1410, 1500, 90, '2023-10-05 20:45:35'),
(29, 460, 500, 40, '2023-10-05 21:19:49'),
(30, 540, 600, 60, '2023-10-05 21:21:36'),
(31, 120, 120, 0, '2023-10-06 05:36:41'),
(32, 1040, 1050, 10, '2023-10-06 05:44:20'),
(33, 1040, 1050, 10, '2023-10-06 05:44:22'),
(34, 415, 500, 85, '2023-10-06 05:46:29'),
(35, 410, 500, 90, '2023-10-06 05:50:32'),
(36, 270, 300, 30, '2023-10-06 05:52:14'),
(37, 490, 500, 10, '2023-10-06 05:55:29'),
(38, 300, 300, 0, '2023-10-06 05:56:41'),
(39, 435, 500, 65, '2023-10-06 06:03:44'),
(40, 450, 500, 50, '2023-10-06 08:41:52'),
(41, 680, 680, 0, '2023-10-06 08:43:21'),
(42, 510, 600, 90, '2023-10-06 08:44:59'),
(43, 695, 700, 5, '2023-10-06 15:18:21');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin_access`
--
ALTER TABLE `admin_access`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `categorization`
--
ALTER TABLE `categorization`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD KEY `purchase_id` (`purchase_id`),
  ADD KEY `menu_id` (`menu_id`);

--
-- Indexes for table `purchases`
--
ALTER TABLE `purchases`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin_access`
--
ALTER TABLE `admin_access`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `categorization`
--
ALTER TABLE `categorization`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `menu`
--
ALTER TABLE `menu`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT for table `purchases`
--
ALTER TABLE `purchases`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`purchase_id`) REFERENCES `purchases` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
