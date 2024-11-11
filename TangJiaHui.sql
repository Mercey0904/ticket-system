-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 03, 2023 at 05:56 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.0.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `assignment2_TangJiaHui`
--

-- --------------------------------------------------------

--
-- Table structure for table `memberList`
--

CREATE TABLE `memberList` (
  `memberName` text NOT NULL,
  `memberPhoneNo` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `memberList`
--

INSERT INTO `memberList` (`memberName`, `memberPhoneNo`) VALUES
('Lee', '01234567890'),
('Lim S-Z', '02020202020'),
('Wei', '09090909090'),
('Tang J-J', '08080808080'),
('Lee', '98989898988'),
('Emmy', '56756756756'),
('Winston', '09809809809'),
('Cristina', '23423423423'),
('Lily', '07070707070'),
('Jess', '23523523523'),
('Jia', '32112332123'),
('Owen', '37373737373'),
('lisa', '12121212121');

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `ticketDate` text NOT NULL,
  `transactionId` int(11) NOT NULL,
  `memberPhoneNo` text NOT NULL,
  `totalPrice` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`ticketDate`, `transactionId`, `memberPhoneNo`, `totalPrice`) VALUES
('230923', 100, '01234567890', 260),
('230923', 100, '02020202020', 260),
('050525', 101, '02020202020', 360),
('050525', 101, '56756756756', 360),
('020224', 102, '01234567890', 160),
('020224', 102, '02020202020', 160),
('200823', 103, '09809809809', 180),
('211023', 104, '56756756756', 80),
('240923', 105, '56756756756', 80),
('210523', 106, '56756756756', 80),
('210923', 107, ' - ', 100),
('230623', 108, '01234567890', 160),
('230623', 108, '07070707070', 160),
('290929', 109, ' - ', 100),
('221225', 110, '08080808080', 440),
('311223', 111, '23523523523', 180),
('220929', 112, '23523523523', 80),
('220923', 113, ' - ', 100),
('220928', 114, '-', 300),
('230725', 115, '98989898988', 180),
('270776', 116, '32112332123', 180),
('221224', 117, '-', 100),
('220925', 118, '07070707070', 180),
('240925', 119, '23423423423', 260),
('210823', 120, '-', 200),
('220224', 121, '-', 200),
('030523', 122, '01234567890', 180);

-- --------------------------------------------------------

--
-- Table structure for table `transactionReport`
--

CREATE TABLE `transactionReport` (
  `ticketDate` text NOT NULL,
  `transactionId` int(11) NOT NULL,
  `noTicket` text NOT NULL,
  `totalPrice` int(11) NOT NULL,
  `dd` int(11) NOT NULL,
  `mm` int(11) NOT NULL,
  `yy` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transactionReport`
--

INSERT INTO `transactionReport` (`ticketDate`, `transactionId`, `noTicket`, `totalPrice`, `dd`, `mm`, `yy`) VALUES
('230923', 100, '3', 260, 23, 9, 23),
('050525', 101, '4', 360, 29, 9, 29),
('020224', 102, '2', 160, 2, 2, 24),
('200823', 103, '2', 180, 20, 8, 23),
('270727', 104, '1', 80, 27, 7, 27),
('240923', 105, '1', 80, 24, 9, 23),
('210523', 106, '1', 80, 21, 5, 23),
('210923', 107, '1', 100, 21, 9, 23),
('230623', 108, '2', 160, 23, 6, 23),
('290929', 109, '1', 100, 29, 9, 29),
('221225', 110, '5', 440, 0, 0, 0),
('311223', 111, '2', 180, 31, 12, 23),
('220929', 112, '1', 80, 22, 9, 29),
('220923', 113, '1', 100, 22, 9, 23),
('220928', 114, '3', 300, 22, 9, 28),
('230725', 115, '2', 180, 23, 7, 25),
('270776', 116, '2', 180, 27, 7, 76),
('221224', 117, '1', 100, 22, 12, 24),
('220925', 118, '2', 180, 22, 9, 25),
('240925', 119, '3', 260, 24, 9, 25),
('210823', 120, '2', 200, 21, 8, 23),
('220224', 121, '2', 200, 0, 0, 0),
('030523', 122, '2', 180, 3, 5, 23);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
