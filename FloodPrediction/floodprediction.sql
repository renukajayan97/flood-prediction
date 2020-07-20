-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 03, 2020 at 03:35 AM
-- Server version: 5.6.16
-- PHP Version: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `floodprediction`
--

-- --------------------------------------------------------

--
-- Table structure for table `rescuer`
--

CREATE TABLE IF NOT EXISTS `rescuer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `name` varchar(255) NOT NULL,
  `Lname` varchar(200) NOT NULL,
  `phone_no` varchar(13) NOT NULL,
  `location` varchar(200) NOT NULL,
  `latitude` varchar(100) NOT NULL,
  `longitude` varchar(100) NOT NULL,
  `status` varchar(100) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `rescuer`
--

INSERT INTO `rescuer` (`id`, `username`, `password`, `name`, `Lname`, `phone_no`, `location`, `latitude`, `longitude`, `status`, `email`) VALUES
(1, 'q', '12345', 'q', '', '7012656981', '', '9.955977', '76.241588', 'q', 'q'),
(6, 'duusdu', 'puzifd', 'xjxjdj', 'jxjx', 'uddi', '', '10.006813991176774', '76.30555865702738', '', 'hddu'),
(7, 'duusdu', '12345', 'xjxjdj', 'jxjx', '8157988437', '', '10.006327664926161', '76.30535842735729', '', 'hddu');

-- --------------------------------------------------------

--
-- Table structure for table `rescue_request`
--

CREATE TABLE IF NOT EXISTS `rescue_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `rid` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `phone` varchar(200) NOT NULL,
  `location` varchar(200) NOT NULL,
  `emergency` varchar(200) NOT NULL,
  `needs` varchar(200) NOT NULL,
  `hazards` varchar(200) NOT NULL,
  `ppl_count` varchar(10) NOT NULL,
  `pets_count` varchar(10) NOT NULL,
  `disabled_count` varchar(10) NOT NULL,
  `message` varchar(200) NOT NULL,
  `latitude` varchar(200) NOT NULL,
  `longitude` varchar(200) NOT NULL,
  `status` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `rescue_request`
--

INSERT INTO `rescue_request` (`id`, `uid`, `rid`, `name`, `phone`, `location`, `emergency`, `needs`, `hazards`, `ppl_count`, `pets_count`, `disabled_count`, `message`, `latitude`, `longitude`, `status`) VALUES
(1, 1, 1, 'User', '7012656981', 'kitty', 'Kick', 'First Aid, Clothing, ', 'Electrical, Tree Falling, ', '2', '0', '0', 'Gregg sexy lcd khuje fuck s dh', '9.955977', '76.241588', 'completed'),
(2, 1, 1, 'User', '7012656981', 'wards', 'think', 'First Aid, Clothing, ', 'Electrical, Others, ', '9', '0', '0', ' kk HH then forth jock', '9.955977', '76.241588', 'completed');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone_no` varchar(13) NOT NULL,
  `house_name` varchar(200) NOT NULL,
  `house_no` varchar(50) NOT NULL,
  `taluk` varchar(200) NOT NULL,
  `latitude` varchar(100) NOT NULL,
  `longitude` varchar(100) NOT NULL,
  `status` varchar(100) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `name`, `phone_no`, `house_name`, `house_no`, `taluk`, `latitude`, `longitude`, `status`, `email`) VALUES
(1, 'q', '12345', 'User', '7012656981', '', '', '', '10.006327664926161', '76.30535842735729', 'q', 'q'),
(2, 'test', 'test', 'name', 'test', 'test', 'test', 'test', '10.002112949205022', '76.30425420509508', '', 'test'),
(3, 'test', 'test', 'name', 'test', 'test', 'test', 'test', '10.00210573989369', '76.30424006329834', '', 'test'),
(4, 'test', 'test', 'test', 'test', 'test', 'test', 'test', '10.002103549625078', '76.3042536680865', '', 'test');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
