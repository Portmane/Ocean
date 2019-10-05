-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 21, 2019 at 10:25 AM
-- Server version: 5.7.24-log
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gaaacha`
--

-- --------------------------------------------------------

--
-- Table structure for table `addresses`
--

CREATE TABLE `addresses` (
  `address_id` int(11) NOT NULL,
  `users_id` int(11) NOT NULL,
  `address_line_1` varchar(250) DEFAULT NULL,
  `address_line_2` varchar(250) DEFAULT NULL,
  `address_line_3` varchar(250) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `state_province` varchar(100) DEFAULT NULL,
  `zip` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `addresses`
--

INSERT INTO `addresses` (`address_id`, `users_id`, `address_line_1`, `address_line_2`, `address_line_3`, `city`, `state_province`, `zip`) VALUES
(1, 1, '79 Beach Rd', 'Mouille Point', '', 'Cape Town', 'Western Cape', '8005'),
(2, 15, '2 Gray St', 'Knysna Central', '', 'Knysna', 'Western Cape', '6571'),
(3, 9, 'Bentley Office Park', '70 Wessel Road', 'Rivonia', 'Johannesburg', 'Gauteng', '2191'),
(4, 14, 'Apartment 21', '2461 desert Road', 'Yellow Sands', 'Tatooine Villa', 'Tatooine', '908709'),
(5, 10, '59 Albert Embankment', '', 'Lambeth', 'London', 'UK', 'SE1 7TP'),
(6, 8, '742 Evergreen Terrace', '', '', 'Springfield', 'ORE', '97001'),
(7, 14, 'Queens Palace', 'Naboo Main Road', 'Naboo City', 'Naboo', 'NAB', '71992'),
(8, 12, '28201 E. Bonanza St.', 'South Park', '', 'South Park City', 'CAL', '28201'),
(9, 12, '635 Avenue de Los Mexicanos', 'South Park', '', 'South Park City', 'CAL', '28201');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `address_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`order_id`, `product_id`, `user_id`, `address_id`) VALUES
(1, 2, 14, 7),
(2, 6, 14, 7),
(3, 13, 9, 1),
(4, 12, 8, 6);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `product_id` int(11) NOT NULL,
  `product_name` varchar(100) NOT NULL,
  `product_description` varchar(500) DEFAULT NULL,
  `product_img` varchar(100) DEFAULT NULL,
  `product_stock` int(11) DEFAULT NULL,
  `product_price` decimal(18,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`product_id`, `product_name`, `product_description`, `product_img`, `product_stock`, `product_price`) VALUES
(1, 'Cannon 600D camera', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis posuere eget erat eu placerat. Phasellus pretium leo varius est blandit consequat. Pellentesque rutrum velit dui, congue ultrices lectus feugiat ac. Nullam ac sem vestibulum, imperdiet nulla sit amet, auctor ipsum.', 'imgs/products/imgname.jpg', 10, '349.99'),
(2, 'Lightsaber - Blue', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis posuere eget erat eu placerat. Phasellus pretium leo varius est blandit consequat. Pellentesque rutrum velit dui, congue ultrices lectus feugiat ac. Nullam ac sem vestibulum, imperdiet nulla sit amet, auctor ipsum.', 'imgs/products/lightsaber.jpg', 5, '39999.99'),
(3, 'Lightsaber - Red', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis posuere eget erat eu placerat. Phasellus pretium leo varius est blandit consequat. Pellentesque rutrum velit dui, congue ultrices lectus feugiat ac. Nullam ac sem vestibulum, imperdiet nulla sit amet, auctor ipsum.', 'imgs/products/lightsaber-red.jpg', 1, '79999.99'),
(4, '2017 Apple Macbook Pro - 256gb', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis posuere eget erat eu placerat. Phasellus pretium leo varius est blandit consequat. Pellentesque rutrum velit dui, congue ultrices lectus feugiat ac. Nullam ac sem vestibulum, imperdiet nulla sit amet, auctor ipsum.', 'imgs/products/macbookpro.jpg', 50, '1989.99'),
(5, 'Spiced Biltong - 5kg', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis posuere eget erat eu placerat. Phasellus pretium leo varius est blandit consequat. Pellentesque rutrum velit dui, congue ultrices lectus feugiat ac. Nullam ac sem vestibulum, imperdiet nulla sit amet, auctor ipsum.', 'imgs/products/spiced-biltong.jpg', 500, '48.99'),
(6, 'Storm Trooper Uniform', 'A standard 2015 Storm Trooper uniform. Use it as a disguise to infiltrate the enemy, or as a cool costume for a dress up party', 'imgs/products/storm-trooper-uniform.jpg', 5, '399.90'),
(7, 'Pink Doughnuts', 'Perfectly glazed pink doughnut', 'imgs/products/pink-doughnut.jpg', 12, '3.50'),
(8, 'Nunchucks - Wooden', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis posuere eget erat eu placerat. Phasellus pretium leo varius est blandit consequat. Pellentesque rutrum velit dui, congue ultrices lectus feugiat ac. Nullam ac sem vestibulum, imperdiet nulla sit amet, auctor ipsum.', 'imgs/products/wooden-chucks.jpg', 67, '29.90'),
(9, 'Gravestone - Granite', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis posuere eget erat eu placerat. Phasellus pretium leo varius est blandit consequat. Pellentesque rutrum velit dui, congue ultrices lectus feugiat ac. Nullam ac sem vestibulum, imperdiet nulla sit amet, auctor ipsum.', 'imgs/products/granite-gravestone.jpg', 4, '699.00'),
(10, 'Blue Yeti Mircrophone - silver', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis posuere eget erat eu placerat. Phasellus pretium leo varius est blandit consequat. Pellentesque rutrum velit dui, congue ultrices lectus feugiat ac. Nullam ac sem vestibulum, imperdiet nulla sit amet, auctor ipsum.', 'imgs/products/blue-yeti-microphone-silver.jpg', 8, '249.90'),
(11, 'Death Star Miniature Replica', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis posuere eget erat eu placerat. Phasellus pretium leo varius est blandit consequat. Pellentesque rutrum velit dui, congue ultrices lectus feugiat ac. Nullam ac sem vestibulum, imperdiet nulla sit amet, auctor ipsum.', 'imgs/products/deathstarreplica.jpg', 13, '24.00'),
(12, 'Hazmat Suit', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis posuere eget erat eu placerat. Phasellus pretium leo varius est blandit consequat. Pellentesque rutrum velit dui, congue ultrices lectus feugiat ac. Nullam ac sem vestibulum, imperdiet nulla sit amet, auctor ipsum.', 'imgs/products/hazmat-suit.jpg', 2, '849.99'),
(13, 'facebook idea', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis posuere eget erat eu placerat. Phasellus pretium leo varius est blandit consequat. Pellentesque rutrum velit dui, congue ultrices lectus feugiat ac. Nullam ac sem vestibulum, imperdiet nulla sit amet, auctor ipsum.', '', 1, '0.99');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(150) NOT NULL,
  `password` varchar(35) NOT NULL,
  `user_email` varchar(150) NOT NULL,
  `first_name` varchar(150) DEFAULT NULL,
  `last_name` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `user_email`, `first_name`, `last_name`) VALUES
(1, 'quentin92', 'password123', 'business@quentinwatt.com', 'Quentin', 'Watt'),
(2, 'sean', 'pass123', 'sean@hiscompany.co.za', '', ''),
(3, 'sibu', 's!bu', 'sibu@sibuthelegend.co.za', 'Sibu', ''),
(4, 'ryan', 'ryebread', 'ryan@shweshwe.co.za', 'Ryan', 'Schwulst'),
(5, 'kristi87', 'kr!5t!', 'kristi@fakemail.com', 'Kristi', 'Dunsk'),
(6, 'SuzelleDIY', 'suzzles1989', 'suzelle@thediychannel.com', 'Suzelle', ''),
(7, 'jackie', 'therealjackiechan', 'jackie@therealjackiechan.com', 'Jackie', 'Chan'),
(8, 'homer', 'doh!', 'homer@thesimpsons.com', 'Homer', 'Simpson'),
(9, 'thezuckmeister', 'zucker!@9)BNa', 'markzuckerberg@facebook.com', 'Mark', 'Zuckerberg'),
(10, 'james', 'jamesbond007', 'james@mi6.gov.uk', 'James', 'Bond'),
(11, 'kenny', 'foreverdead', 'kenny@southpark.show', 'Kenny', ''),
(12, 'cartman', 'thecoon!', 'cartman@southpark.show', 'Cartman', ''),
(13, 'greg0812', 'sup3rstr0ngp455!', 'greg@fakemail.com', 'Greg', 'Coltman'),
(14, 'thejedimasterluke', 'thef0rc3', 'lukeskywalker@thejedicouncil.galaxy', 'Luke', 'Skywalker'),
(15, 'wreckitralph', 'imgonnawreck!t', 'ralph@wreck.it', 'Ralph', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `addresses`
--
ALTER TABLE `addresses`
  ADD PRIMARY KEY (`address_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `addresses`
--
ALTER TABLE `addresses`
  MODIFY `address_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
