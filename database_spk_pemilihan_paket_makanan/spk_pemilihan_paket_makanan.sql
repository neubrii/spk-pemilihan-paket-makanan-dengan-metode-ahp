-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 15, 2024 at 10:17 AM
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
-- Database: `spk_pemilihan_paket_makanan`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` char(3) NOT NULL,
  `namalengkap` varchar(20) DEFAULT NULL,
  `user` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `namalengkap`, `user`, `password`) VALUES
('001', 'Neubri Hidayah', 'admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `data_paket`
--

CREATE TABLE `data_paket` (
  `id_paket` varchar(5) NOT NULL,
  `nama_paket` varchar(30) NOT NULL,
  `jenis_menu` varchar(30) NOT NULL,
  `tingkat_vegetarian` varchar(20) NOT NULL,
  `jumlah_orang` varchar(18) NOT NULL,
  `menu_paket` varchar(100) NOT NULL,
  `harga_paket` int(3) NOT NULL,
  `jumlah_menu` varchar(20) NOT NULL,
  `tingkat_popularitas` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `data_paket`
--

INSERT INTO `data_paket` (`id_paket`, `nama_paket`, `jenis_menu`, `tingkat_vegetarian`, `jumlah_orang`, `menu_paket`, `harga_paket`, `jumlah_menu`, `tingkat_popularitas`) VALUES
('01', 'Paket 1', 'Western Food', 'Sedang', '50', 'Biryani	', 80, '4 Menu', 'Tidak Populer'),
('02', 'Paket 2', 'Western Food', 'Tinggi', '50', 'Sate', 60, '5 Menu', 'Sangat Populer'),
('03', 'Paket 3', 'Indian Food', 'Tidak sama sekali', '50', 'Ayam Bakar', 90, '5 Menu', 'Sangat Populer');

-- --------------------------------------------------------

--
-- Table structure for table `kriteria`
--

CREATE TABLE `kriteria` (
  `kd_kriteria` char(3) NOT NULL,
  `nama_kriteria` varchar(30) NOT NULL,
  `prioritas_kepentingan` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kriteria`
--

INSERT INTO `kriteria` (`kd_kriteria`, `nama_kriteria`, `prioritas_kepentingan`) VALUES
('K1', 'Tingkat Popularitas', 'Sangat Penting ke-1'),
('K2', 'Jumlah Menu', 'Penting ke-2'),
('K3', 'Harga Paket', 'Cukup Penting ke-3'),
('K4', 'Tingkat Vegetarian', 'Biasa ke-4');

-- --------------------------------------------------------

--
-- Table structure for table `register`
--

CREATE TABLE `register` (
  `id` int(3) NOT NULL,
  `email` varchar(50) NOT NULL,
  `user` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `register`
--

INSERT INTO `register` (`id`, `email`, `user`, `password`) VALUES
(1, 'mail@gmail.com', 'admin', 'admin'),
(2, '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `seleksi`
--

CREATE TABLE `seleksi` (
  `id_paket` char(3) NOT NULL,
  `nama_paket` varchar(30) NOT NULL,
  `jumlah_orang` varchar(18) NOT NULL,
  `hasil_penilaian` decimal(4,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `seleksi`
--

INSERT INTO `seleksi` (`id_paket`, `nama_paket`, `jumlah_orang`, `hasil_penilaian`) VALUES
('01', 'Paket 1', '50', 0.20),
('02', 'Paket 2', '50', 0.91),
('03', 'Paket 3', '50', 0.96);

-- --------------------------------------------------------

--
-- Table structure for table `sub_kriteria`
--

CREATE TABLE `sub_kriteria` (
  `no_sub` int(3) NOT NULL,
  `kd_kriteria` char(3) NOT NULL,
  `nama_kriteria` varchar(30) NOT NULL,
  `nama_sub_kriteria` varchar(30) NOT NULL,
  `prioritas_kepentingan` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sub_kriteria`
--

INSERT INTO `sub_kriteria` (`no_sub`, `kd_kriteria`, `nama_kriteria`, `nama_sub_kriteria`, `prioritas_kepentingan`) VALUES
(1, 'K4', 'Tingkat Vegetarian', 'Tinggi', 'Sangat Penting ke-1'),
(2, 'K4', 'Tingkat Vegetarian', 'Sedang', 'Penting ke-2'),
(3, 'K4', 'Tingkat Vegetarian', 'Rendah', 'Cukup Penting ke-3'),
(4, 'K4', 'Tingkat Vegetarian', 'Tidak sama sekali', 'Biasa ke-4'),
(5, 'K3', 'Harga Paket', 'Lebih Dari 8 Juta', 'Sangat Penting ke-1'),
(6, 'K3', 'Harga Paket', 'Lebih Dari 6 Juta', 'Cukup Penting ke-2'),
(7, 'K3', 'Harga Paket', 'Kurang Atau Sama Dengan 6 Juta', 'Biasa ke-3'),
(8, 'K2', 'Jumlah Menu', '5 Menu', 'Sangat Penting ke-1'),
(9, 'K2', 'Jumlah Menu', '4 Menu', 'Cukup Penting ke-2'),
(10, 'K2', 'Jumlah Menu', '3 Menu', 'Biasa ke-3'),
(11, 'K1', 'Tingkat Popularitas', 'Sangat Populer', 'Sangat Penting ke-1'),
(12, 'K1', 'Tingkat Popularitas', 'Cukup Populer', 'Penting ke-2'),
(13, 'K1', 'Tingkat Popularitas', 'Kurang Populer', 'Cukup Penting ke-3'),
(14, 'K1', 'Tingkat Popularitas', 'Tidak Populer', 'Biasa ke-4');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `data_paket`
--
ALTER TABLE `data_paket`
  ADD PRIMARY KEY (`id_paket`);

--
-- Indexes for table `kriteria`
--
ALTER TABLE `kriteria`
  ADD PRIMARY KEY (`kd_kriteria`);

--
-- Indexes for table `register`
--
ALTER TABLE `register`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `seleksi`
--
ALTER TABLE `seleksi`
  ADD UNIQUE KEY `id_paket` (`id_paket`);

--
-- Indexes for table `sub_kriteria`
--
ALTER TABLE `sub_kriteria`
  ADD PRIMARY KEY (`no_sub`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `register`
--
ALTER TABLE `register`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
