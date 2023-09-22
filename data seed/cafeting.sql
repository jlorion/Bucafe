-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 22, 2023 at 06:28 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.0.28

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
(2, 'breads'),
(3, 'espresso'),
(4, 'cake');

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
(1, 'Red Velvet Cake', 300, 100, 'cake'),
(2, 'Chocolate Cake', 150, 100, 'cake'),
(3, 'Machiatto', 50, 100, 'espresso'),
(4, 'Iced Americano ', 140, 100, 'espresso'),
(5, 'Croissant ', 150, 100, 'breads'),
(6, 'chocolanay ', 55, 100, 'breads');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `purchase_id` int(100) NOT NULL,
  `menu_id` int(100) NOT NULL,
  `amount` int(100) NOT NULL,
  `item_total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`purchase_id`, `menu_id`, `amount`, `item_total`) VALUES
(1, 1, 1, 0),
(1, 2, 1, 0),
(1, 3, 1, 0),
(2, 2, 1, 0),
(2, 3, 4, 0),
(6, 3, 1, 50),
(6, 4, 2, 280),
(6, 6, 2, 110),
(6, 6, 2, 110),
(7, 5, 1, 150),
(8, 1, 1, 300),
(9, 3, 1, 50),
(10, 4, 1, 140);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `purchases`
--

INSERT INTO `purchases` (`id`, `total`, `cash`, `moneyback`, `datentime`) VALUES
(1, 500, 1000, 500, '2023-09-06 14:40:10'),
(2, 350, 500, 150, '2023-09-06 14:40:10'),
(4, 701, 1000, -701, '2023-09-11 23:23:05'),
(5, 301, 500, 199, '2023-09-12 00:04:04'),
(6, 551, 500, -51, '2023-09-21 15:28:35'),
(7, 151, 151, 0, '2023-09-21 15:54:27'),
(8, 301, 400, 99, '2023-09-21 16:28:02'),
(9, 51, 51, 0, '2023-09-21 16:28:48'),
(10, 141, 150, 9, '2023-09-21 16:30:06');

--
-- Indexes for dumped tables
--

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
-- AUTO_INCREMENT for table `categorization`
--
ALTER TABLE `categorization`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `menu`
--
ALTER TABLE `menu`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `purchases`
--
ALTER TABLE `purchases`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

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
