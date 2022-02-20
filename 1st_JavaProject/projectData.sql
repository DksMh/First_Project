-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.3.32-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- projdata 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `projdata` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `projdata`;

-- 테이블 projdata.gamedata 구조 내보내기
CREATE TABLE IF NOT EXISTS `gamedata` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(20) NOT NULL,
  `ansgame1` int(11) DEFAULT NULL,
  `totalgame1` int(11) DEFAULT NULL,
  `ansgame2` int(11) DEFAULT NULL,
  `totalgame2` int(11) DEFAULT NULL,
  `ansgame3` int(11) DEFAULT NULL,
  `totalgame3` int(11) DEFAULT NULL,
  `ansgame4` int(11) DEFAULT NULL,
  `totalgame4` int(11) DEFAULT NULL,
  `ansgame5` int(11) DEFAULT NULL,
  `totalgame5` int(11) DEFAULT NULL,
  `day` date NOT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;

-- 테이블 데이터 projdata.gamedata:~60 rows (대략적) 내보내기
DELETE FROM `gamedata`;
/*!40000 ALTER TABLE `gamedata` DISABLE KEYS */;
INSERT INTO `gamedata` (`no`, `id`, `ansgame1`, `totalgame1`, `ansgame2`, `totalgame2`, `ansgame3`, `totalgame3`, `ansgame4`, `totalgame4`, `ansgame5`, `totalgame5`, `day`) VALUES
	(1, 'user1', 17, 20, 17, 20, 27, 30, 9, 10, 26, 30, '2022-01-02'),
	(2, 'user1', 16, 20, 27, 30, 17, 20, 18, 20, 18, 20, '2022-01-03'),
	(3, 'user1', 15, 20, 11, 20, 18, 20, 19, 20, 25, 30, '2022-01-04'),
	(4, 'user1', 18, 20, 9, 10, 28, 30, 17, 20, 21, 30, '2022-01-05'),
	(5, 'user1', 18, 20, 19, 20, 25, 30, 27, 30, 11, 20, '2022-01-07'),
	(6, 'user2', 15, 20, 17, 20, 17, 20, 15, 20, 8, 10, '2022-01-04'),
	(7, 'user2', 14, 20, 17, 20, 23, 30, 16, 20, 16, 20, '2022-01-05'),
	(8, 'user2', 17, 20, 25, 30, 17, 20, 19, 20, 27, 30, '2022-01-06'),
	(9, 'user2', 12, 20, 16, 20, 25, 30, 17, 20, 25, 30, '2022-01-07'),
	(10, 'user2', 25, 30, 9, 10, 7, 10, 18, 20, 14, 20, '2022-01-08'),
	(11, 'user3', 7, 10, 16, 20, 17, 20, 8, 10, 16, 20, '2022-01-05'),
	(12, 'user3', 17, 20, 18, 20, 18, 20, 17, 20, 15, 20, '2022-01-06'),
	(13, 'user3', 18, 20, 17, 20, 19, 20, 18, 20, 16, 20, '2022-01-07'),
	(14, 'user3', 26, 30, 26, 30, 26, 30, 18, 20, 16, 20, '2022-01-08'),
	(15, 'user3', 19, 20, 16, 20, 25, 30, 27, 30, 18, 20, '2022-01-10'),
	(17, 'user88', 8, 9, 7, 10, 8, 12, 9, 14, 30, 30, '2022-02-03'),
	(18, 'user1', 13, 16, 6, 7, 17, 20, 18, 20, 7, 18, '2022-02-03'),
	(19, 'user88', 8, 12, 9, 10, 13, 16, 12, 16, 16, 20, '2022-02-04'),
	(20, 'user1', 9, 10, 8, 10, 12, 14, 13, 18, 7, 14, '2022-02-05'),
	(21, 'user2', 8, 10, 7, 10, 9, 10, 12, 14, 7, 10, '2022-02-05'),
	(22, 'user3', 12, 16, 10, 14, 8, 10, 13, 16, 9, 10, '2022-02-05'),
	(24, 'user88', 7, 12, 14, 18, 8, 10, 10, 16, 7, 12, '2022-02-05'),
	(25, 'user88', 9, 14, 12, 18, 12, 14, 9, 10, 7, 8, '2022-02-07'),
	(26, 'user1', 12, 14, 11, 14, 12, 18, 9, 10, 4, 8, '2022-02-07'),
	(28, 'user2', 10, 10, 10, 14, 9, 12, 8, 12, 11, 16, '2022-02-07'),
	(29, 'user3', 12, 16, 11, 14, 9, 14, 14, 20, 12, 18, '2022-02-07'),
	(30, 'user4', 9, 12, 7, 8, 14, 16, 12, 18, 10, 10, '2022-02-07'),
	(33, 'user1', 12, 18, 7, 16, 19, 20, 12, 14, 11, 16, '2022-02-08'),
	(34, 'user2', 10, 12, 9, 10, 10, 10, 14, 18, 18, 24, '2022-02-08'),
	(35, 'user3', 8, 10, 9, 10, 12, 14, 12, 18, 9, 12, '2022-02-08'),
	(36, 'user4', 8, 12, 10, 10, 12, 14, 15, 18, 9, 10, '2022-02-08'),
	(38, 'user88', 9, 10, 10, 12, 10, 14, 20, 24, 19, 20, '2022-02-08'),
	(41, 'user1', 7, 10, 7, 12, 9, 10, 9, 10, 8, 10, '2022-02-09'),
	(42, 'user2', 8, 10, 8, 10, 12, 18, 18, 20, 12, 20, '2022-02-09'),
	(43, 'user3', 12, 12, 18, 20, 8, 10, 14, 18, 12, 16, '2022-02-09'),
	(44, 'user88', 15, 18, 14, 18, 12, 18, 9, 10, 8, 10, '2022-02-09'),
	(48, 'user1', 9, 10, 12, 14, 18, 20, 12, 16, 6, 10, '2022-02-10'),
	(49, 'user2', 8, 10, 6, 8, 10, 14, 20, 24, 13, 16, '2022-02-10'),
	(50, 'user3', 9, 10, 12, 14, 12, 16, 11, 12, 9, 10, '2022-02-10'),
	(51, 'user4', 8, 10, 5, 8, 10, 12, 5, 8, 12, 14, '2022-02-10'),
	(52, 'user88', 9, 10, 12, 14, 11, 16, 9, 12, 12, 18, '2022-02-10'),
	(53, 'user1', 10, 12, 8, 10, 13, 18, 9, 10, 12, 14, '2022-02-11'),
	(54, 'user2', 9, 12, 7, 10, 8, 14, 12, 20, 18, 20, '2022-02-11'),
	(55, 'user88', 8, 14, 18, 22, 16, 18, 15, 20, 9, 10, '2022-02-11'),
	(58, 'user3', 9, 14, 20, 26, 12, 22, 12, 18, 15, 20, '2022-02-11'),
	(59, 'user4', 10, 16, 9, 10, 13, 16, 18, 22, 12, 16, '2022-02-11'),
	(60, 'user1', 14, 18, 15, 20, 14, 20, 8, 12, 8, 18, '2022-02-13'),
	(61, 'user3', 10, 14, 20, 30, 8, 14, 17, 22, 9, 16, '2022-02-13'),
	(63, 'user2', 18, 24, 8, 12, 9, 10, 12, 16, 9, 12, '2022-02-13'),
	(64, 'user1', 9, 10, 8, 12, 7, 10, 12, 16, 6, 14, '2022-02-14'),
	(65, 'user2', 8, 14, 10, 20, 9, 12, 14, 18, 10, 18, '2022-02-14'),
	(66, 'user3', 7, 16, 8, 18, 10, 16, 9, 10, 7, 16, '2022-02-14'),
	(67, 'user88', 8, 14, 12, 20, 5, 10, 10, 20, 12, 18, '2022-02-14'),
	(68, 'user4', 9, 10, 12, 16, 8, 16, 9, 16, 15, 20, '2022-02-14'),
	(69, 'user1', 7, 14, 6, 12, 8, 10, 9, 16, 8, 14, '2022-02-15'),
	(70, 'user2', 9, 12, 8, 14, 9, 10, 7, 18, 12, 20, '2022-02-15'),
	(71, 'user1', 12, 20, 8, 16, 8, 10, 12, 16, 5, 10, '2022-02-16'),
	(72, 'user1', 9, 12, 8, 14, 8, 12, 9, 16, 10, 14, '2022-02-17'),
	(74, 'user1', 6, 10, 7, 10, 8, 12, 7, 10, 6, 12, '2022-02-18');
/*!40000 ALTER TABLE `gamedata` ENABLE KEYS */;

-- 테이블 projdata.user 구조 내보내기
CREATE TABLE IF NOT EXISTS `user` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `id` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `age` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `no` (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- 테이블 데이터 projdata.user:~6 rows (대략적) 내보내기
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`no`, `name`, `id`, `password`, `age`) VALUES
	(1, 'saram1', 'user1', 'user1', 62),
	(2, 'saram2', 'user2', 'user2', 75),
	(3, 'saram3', 'user3', 'user3', 92),
	(6, 'saram4', 'user4', 'user4', 87),
	(9, 'saram88', 'user88', 'user88', 88);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
