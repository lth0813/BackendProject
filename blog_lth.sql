CREATE DATABASE IF NOT EXISTS `blog_lth`;
USE `blog_lth`;

CREATE TABLE IF NOT EXISTS `bigtopic` (
  `bigseq` bigint NOT NULL AUTO_INCREMENT,
  `bigtopic` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bigseq`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT IGNORE INTO `bigtopic` (`bigseq`, `bigtopic`) VALUES
	(10, '고양이'),
	(11, '강아지');

CREATE TABLE IF NOT EXISTS `boardanswer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` text,
  `seq` bigint NOT NULL,
  `DATE` varchar(50) DEFAULT NULL,
  `writer` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_boardanswer_seq` (`seq`),
  CONSTRAINT `fk_boardanswer_seq` FOREIGN KEY (`seq`) REFERENCES `boardlist` (`seq`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT IGNORE INTO `boardanswer` (`id`, `content`, `seq`, `DATE`, `writer`) VALUES
	(14, '귀여워요', 32, '2023-12-03 03:51:47', '임태환'),
	(15, '생동감 넘쳐요', 40, '2023-12-03 03:52:00', '임태환'),
	(16, '멋있어요', 44, '2023-12-03 03:52:10', '임태환'),
	(17, '오드아이 멋있어', 25, '2023-12-03 03:52:27', '임태환'),
	(18, '이것도 귀여워요', 37, '2023-12-03 03:52:43', '임태환'),
	(19, '똘망똘망', 45, '2023-12-03 03:52:56', '임태환'),
	(20, '하얀 뽀시래기', 26, '2023-12-03 03:53:09', '임태환'),
	(21, '한복 입은거 너무 귀엽다', 46, '2023-12-03 03:53:34', '임태환'),
	(22, '강아지 삼형제', 31, '2023-12-03 03:53:48', '임태환'),
	(23, '옷 입은거 귀엽당', 47, '2023-12-03 03:54:04', '임태환'),
	(24, '친숙한 고양이', 24, '2023-12-03 03:57:52', '윤여록'),
	(25, '이거 귀여워요', 45, '2023-12-03 03:58:06', '윤여록'),
	(26, '얘도 귀여워요', 38, '2023-12-03 03:58:20', '윤여록'),
	(27, 'ㄹㅇ 정말 귀엽습니다', 46, '2023-12-03 04:00:27', '박정현'),
	(28, '복실복실', 39, '2023-12-03 04:00:42', '박정현');

CREATE TABLE IF NOT EXISTS `boardlist` (
  `seq` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `smallseq` bigint NOT NULL,
  `DATE` varchar(50) DEFAULT NULL,
  `search_count` int DEFAULT '1',
  `imagename` varchar(5000) DEFAULT NULL,
  `image` varchar(5000) DEFAULT '/images/CATANDDOG.jpg',
  PRIMARY KEY (`seq`),
  KEY `fk_boardlist_smallseq` (`smallseq`),
  CONSTRAINT `fk_boardlist_smallseq` FOREIGN KEY (`smallseq`) REFERENCES `smalltopic` (`smallseq`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT IGNORE INTO `boardlist` (`seq`, `title`, `content`, `smallseq`, `DATE`, `search_count`, `imagename`, `image`) VALUES
	(24, '고양이 1', '고양이 1', 14, '2023-12-03 03:38:16', 5, 'cat1.jpg', '/images/cat1.jpg'),
	(25, '고양이 2', '고양이 2', 15, '2023-12-03 03:38:34', 4, 'cat2.png', '/images/cat2.png'),
	(26, '강아지 1', '강아지 1', 16, '2023-12-03 03:38:52', 4, 'dog1.jpg', '/images/dog1.jpg'),
	(27, '강아지 2', '강아지 2', 17, '2023-12-03 03:39:06', 3, 'dog2.jpg', '/images/dog2.jpg'),
	(28, '고양이 3', '고양이 3', 14, '2023-12-03 03:39:19', 3, 'cat3.jpg', '/images/cat3.jpg'),
	(29, '고양이 4', '고양이 4', 15, '2023-12-03 03:39:30', 3, 'cat4.jpg', '/images/cat4.jpg'),
	(30, '강아지 3', '강아지 3', 16, '2023-12-03 03:39:47', 4, 'dog3.jpg', '/images/dog3.jpg'),
	(31, '강아지 4', '강아지 4', 17, '2023-12-03 03:40:00', 4, 'dog4.jpg', '/images/dog4.jpg'),
	(32, '고양이 5', '고양이 5', 14, '2023-12-03 03:40:18', 5, 'cat5.jpg', '/images/cat5.jpg'),
	(33, '고양이 6', '고양이 6', 15, '2023-12-03 03:40:32', 3, 'cat6.jpg', '/images/cat6.jpg'),
	(34, '강아지 5', '강아지 5', 16, '2023-12-03 03:40:45', 3, 'dog5.png', '/images/dog5.png'),
	(35, '강아지 6', '강아지 6', 17, '2023-12-03 03:41:00', 3, 'dog6.jpg', '/images/dog6.jpg'),
	(36, '고양이 7', '고양이 7', 14, '2023-12-03 03:41:11', 4, 'cat7.jpg', '/images/cat7.jpg'),
	(37, '고양이 8', '고양이 8', 15, '2023-12-03 03:41:22', 4, 'cat8.png', '/images/cat8.png'),
	(38, '강아지 7', '강아지 7', 16, '2023-12-03 03:41:36', 5, 'dog7.jpg', '/images/dog7.jpg'),
	(39, '강아지 8', '강아지 8', 17, '2023-12-03 03:41:48', 6, 'dog8.jpg', '/images/dog8.jpg'),
	(40, '고양이 9', '고양이 9', 14, '2023-12-03 03:41:58', 4, 'cat9.jpg', '/images/cat9.jpg'),
	(41, '고양이 10', '고양이 10', 15, '2023-12-03 03:42:08', 4, 'cat10.jpg', '/images/cat10.jpg'),
	(42, '강아지 9', '강아지 9', 16, '2023-12-03 03:43:03', 5, 'dog9.jpg', '/images/dog9.jpg'),
	(43, '강아지 10', '강아지 10', 17, '2023-12-03 03:43:20', 3, 'dog10.jpg', '/images/dog10.jpg'),
	(44, '고양이 11', '고양이 11', 14, '2023-12-03 03:43:34', 4, 'cat11.jpg', '/images/cat11.jpg'),
	(45, '고양이 12', '고양이 12', 15, '2023-12-03 03:43:46', 6, 'cat12.jpg', '/images/cat12.jpg'),
	(46, '강아지 11', '강아지 11', 16, '2023-12-03 03:44:02', 7, 'dog11.jpg', '/images/dog11.jpg'),
	(47, '강아지 12', '강아지 12', 17, '2023-12-03 03:44:12', 9, 'dog12.jpg', '/images/dog12.jpg');

CREATE TABLE IF NOT EXISTS `boardnotice` (
  `seq` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `DATE` varchar(50) DEFAULT NULL,
  `search_count` int DEFAULT '1',
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT IGNORE INTO `boardnotice` (`seq`, `title`, `content`, `DATE`, `search_count`) VALUES
	(10, '블로그 실행 전 읽어주세요', '같이 동봉 된 쿼리 파일을 실행하여 데이터베이스 및 기본적으로 넣어놓은 데이터를 추가해주세요.\r\n\r\n사용자 root, 비밀번호 1234, 세션 root를 사용합니다.\r\n\r\n기본으로 설정되어있는 DATABASE 이름은 blog_lth 입니다.', '2023-12-03 04:03:55', 5),
	(11, '블로그 실행 전 읽어주세요 2', '해당 과제의 업로드 및 다운로드 경로는 C:/Kepco A/blog_lth/src/main/resources/static/images/ 입니다.\r\n\r\nblog_lth 폴더를 C 드라이브의 Kepco A 폴더 안에 넣고 실행하시거나\r\n\r\nDBController와 DownloadController에 지정되어있는 경로를 \r\n\r\n원하는 경로로 수정하여 주세요. (DB 2개, Download 1개로 총 3개)', '2023-12-03 04:04:18', 4);

CREATE TABLE IF NOT EXISTS `smalltopic` (
  `smallseq` bigint NOT NULL AUTO_INCREMENT,
  `smalltopic` varchar(255) DEFAULT NULL,
  `bigseq` bigint NOT NULL,
  PRIMARY KEY (`smallseq`),
  KEY `fk_smalltopic_bigseq` (`bigseq`),
  CONSTRAINT `fk_smalltopic_bigseq` FOREIGN KEY (`bigseq`) REFERENCES `bigtopic` (`bigseq`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT IGNORE INTO `smalltopic` (`smallseq`, `smalltopic`, `bigseq`) VALUES
	(14, '고양이 1', 10),
	(15, '고양이 2', 10),
	(16, '강아지 1', 11),
	(17, '강아지 2', 11);

CREATE TABLE IF NOT EXISTS `userlist` (
  `id` varchar(20) NOT NULL,
  `PASSWORD` varchar(20) NOT NULL,
  `NAME` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phonenumber` varchar(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT IGNORE INTO `userlist` (`id`, `PASSWORD`, `NAME`, `email`, `phonenumber`) VALUES
	('jsh', '1234', '장성호', 'jsh@gmail.com', '01045678901'),
	('kkh', '1234', '강건희', 'kkh@gmail.com', '01034567890'),
	('ksh', '1234', '김수호', 'ksh@gmail.com', '01056789012'),
	('lth0813', '1234', '임태환', 'lth0813@gmail.com', '01067165133'),
	('pjh', '1234', '박정현', 'pjh@gmail.com', '01023456789'),
	('yyr', '1234', '윤여록', 'yyr@gmail.com', '01012345678');