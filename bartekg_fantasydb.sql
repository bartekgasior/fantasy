-- phpMyAdmin SQL Dump
-- version 4.0.10.18
-- https://www.phpmyadmin.net
--
-- Host: localhost:3306
-- Czas wygenerowania: 22 Sty 2018, 08:26
-- Wersja serwera: 5.6.35
-- Wersja PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Baza danych: `bartekg_fantasydb`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `country`
--

CREATE TABLE IF NOT EXISTS `country` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `country_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `league`
--

CREATE TABLE IF NOT EXISTS `league` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `starting_money` int(11) NOT NULL DEFAULT '0',
  `league_admin_id` int(11) NOT NULL,
  `slots` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=26 ;

--
-- Zrzut danych tabeli `league`
--

INSERT INTO `league` (`id`, `name`, `starting_money`, `league_admin_id`, `slots`) VALUES
(21, 'user', 400, 4, 2),
(22, 'user1', 456, 5, 3),
(23, 'aaa', 111, 4, 5),
(24, 'League2', 500, 4, 10),
(25, 'League6', 1000, 4, 3);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `league_users_team`
--

CREATE TABLE IF NOT EXISTS `league_users_team` (
  `league_id` int(11) NOT NULL,
  `users_team_id` int(11) NOT NULL,
  `users_team_user_id` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`,`league_id`,`users_team_id`,`users_team_user_id`),
  KEY `fk_league_has_users_team_users_team1_idx` (`users_team_id`,`users_team_user_id`),
  KEY `fk_league_has_users_team_league1_idx` (`league_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=25 ;

--
-- Zrzut danych tabeli `league_users_team`
--

INSERT INTO `league_users_team` (`league_id`, `users_team_id`, `users_team_user_id`, `id`) VALUES
(21, 21, 4, 14),
(21, 22, 5, 15),
(21, 29, 6, 22),
(21, 30, 7, 23),
(22, 23, 5, 16),
(22, 24, 4, 17),
(22, 31, 8, 24),
(23, 25, 4, 18),
(23, 26, 5, 19),
(24, 27, 4, 20),
(24, 28, 5, 21);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `match_player`
--

CREATE TABLE IF NOT EXISTS `match_player` (
  `match_id` int(11) NOT NULL,
  `player_id` int(11) NOT NULL,
  `player_result_id` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`,`match_id`,`player_id`),
  KEY `fk_match_has_player_player1_idx` (`player_id`),
  KEY `fk_match_has_player_match1_idx` (`match_id`),
  KEY `fk_match_player_player_result1_idx` (`player_result_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=96 ;

--
-- Zrzut danych tabeli `match_player`
--

INSERT INTO `match_player` (`match_id`, `player_id`, `player_result_id`, `id`) VALUES
(144, 15, 1, 59),
(144, 16, 1, 60),
(144, 55, 1, 64),
(144, 56, 1, 65),
(149, 47, 1, 69),
(149, 52, 1, 74),
(149, 38, 1, 75),
(149, 42, 1, 79),
(149, 44, 1, 81),
(152, 22, 1, 87),
(152, 24, 1, 89),
(152, 27, 1, 92),
(152, 29, 1, 94),
(137, 2, 2, 51),
(144, 14, 2, 58),
(137, 4, 3, 52),
(144, 12, 3, 56),
(149, 48, 3, 70),
(149, 40, 3, 77),
(149, 45, 3, 82),
(152, 18, 3, 83),
(152, 19, 3, 84),
(137, 3, 4, 54),
(144, 13, 4, 57),
(144, 17, 4, 61),
(144, 53, 4, 62),
(149, 46, 4, 68),
(149, 51, 4, 73),
(152, 21, 4, 86),
(152, 23, 4, 88),
(152, 26, 4, 91),
(137, 1, 5, 53),
(144, 54, 5, 63),
(144, 57, 5, 66),
(149, 49, 5, 71),
(149, 50, 5, 72),
(149, 41, 5, 78),
(152, 20, 5, 85),
(152, 25, 5, 90),
(152, 28, 5, 93),
(144, 58, 6, 67),
(149, 39, 6, 76),
(149, 43, 6, 80),
(152, 30, 6, 95),
(137, 5, 7, 55);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `match_tab`
--

CREATE TABLE IF NOT EXISTS `match_tab` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `home_team_id` int(11) NOT NULL,
  `away_team_id` int(11) NOT NULL,
  `home_team_score` int(11) NOT NULL,
  `away_team_score` int(11) NOT NULL,
  `result_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`home_team_id`,`away_team_id`),
  KEY `fk_match_real_team2_idx` (`home_team_id`),
  KEY `fk_match_real_team3_idx` (`away_team_id`),
  KEY `fk_match_result1_idx` (`result_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=155 ;

--
-- Zrzut danych tabeli `match_tab`
--

INSERT INTO `match_tab` (`id`, `home_team_id`, `away_team_id`, `home_team_score`, `away_team_score`, `result_id`) VALUES
(137, 4, 2, 0, 0, 1),
(144, 12, 10, 1, 3, 3),
(149, 9, 8, 1, 1, 1),
(152, 14, 13, 1, 2, 3),
(154, 11, 14, 0, 0, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `player`
--

CREATE TABLE IF NOT EXISTS `player` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `player_fee` double NOT NULL DEFAULT '0',
  `position` varchar(45) NOT NULL,
  `real_team_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`real_team_id`),
  KEY `fk_player_real_team1_idx` (`real_team_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=59 ;

--
-- Zrzut danych tabeli `player`
--

INSERT INTO `player` (`id`, `name`, `surname`, `player_fee`, `position`, `real_team_id`) VALUES
(1, 'aa', 'aa', 123, 'GoalKeepers', 2),
(2, 'cc', 'cc', 12, 'Midfielders', 4),
(3, 'c', 'c', 10, 'GoalKeepers', 2),
(4, 'd', 'f', 22, 'Forwards', 4),
(5, 'asd', 'dffd', 8, 'Defenders', 2),
(6, 'Abc', 'Qwe', 15, 'Midfielders', 2),
(12, 'Nick', 'Pope', 4, 'GoalKeepers', 12),
(13, 'Matthew', 'Lowton', 7, 'Defenders', 12),
(14, 'Charlie', 'Taylor', 3, 'Defenders', 12),
(15, 'Jack', 'Cork', 6, 'Midfielders', 12),
(16, 'Ashley', 'Barnes', 8, 'Midfielders', 12),
(17, 'Sam', 'Vokes', 5, 'Forwards', 12),
(18, 'Jordan', 'Pickford', 6, 'GoalKeepers', 14),
(19, 'Ashley', 'Williams', 7, 'Defenders', 14),
(20, 'Phil', 'Jagielka', 5, 'Defenders', 14),
(21, 'Morgan', 'Schneiderlin', 6, 'Midfielders', 14),
(22, 'Yannick', 'Bolasie', 8, 'Forwards', 14),
(23, 'Gylfi', 'Sigurdsson', 9, 'Midfielders', 14),
(24, 'Wayne', 'Rooney', 6, 'Forwards', 14),
(25, 'Kasper', 'Schmeichel', 9, 'GoalKeepers', 13),
(26, 'Wes', 'Morgan', 3, 'Defenders', 13),
(27, 'Robert', 'Huth', 3, 'Defenders', 13),
(28, 'Riyad', 'Mahrez', 7, 'Midfielders', 13),
(29, 'Demarai', 'Gray', 5, 'Midfielders', 13),
(30, 'Jamie', 'Vardy', 7, 'Forwards', 13),
(31, 'Simon', 'Mignolet', 4, 'GoalKeepers', 11),
(32, 'Dejan', 'Lovren', 4, 'Defenders', 11),
(33, 'Joel', 'Matip', 4, 'Defenders', 11),
(34, 'James', 'Milner', 5, 'Midfielders', 11),
(35, 'Emre', 'Can', 8, 'Midfielders', 11),
(36, 'Mohamed', 'Salah', 11, 'Forwards', 11),
(37, 'Sadio', 'Mane', 9, 'Forwards', 11),
(38, 'Claudio', 'Bravo', 5, 'GoalKeepers', 8),
(39, 'Ederson', 'Moraes', 8, 'GoalKeepers', 8),
(40, 'Kyle', 'Walker', 7, 'Defenders', 8),
(41, 'Nicolas', 'Otamendi', 10, 'Defenders', 8),
(42, 'Vincent', 'Kompany', 5, 'Defenders', 8),
(43, 'Kevin', 'De Bruyne', 12, 'Midfielders', 8),
(44, 'Leroy', 'Sane', 9, 'Midfielders', 8),
(45, 'Gabriel', 'Jesus', 8, 'Forwards', 8),
(46, 'David', 'De Gea', 10, 'GoalKeepers', 9),
(47, 'Phil', 'Jones', 6, 'Defenders', 9),
(48, 'Eric', 'Bailly', 5, 'Defenders', 9),
(49, 'Paul', 'Pogba', 11, 'Midfielders', 9),
(50, 'Romelu', 'Lukaku', 8, 'Forwards', 9),
(51, 'Anthony', 'Martial', 8, 'Forwards', 9),
(52, 'Jesse', 'Lingard', 9, 'Midfielders', 9),
(53, 'Hugo', 'Lloris', 8, 'GoalKeepers', 10),
(54, 'Davinson', 'Sanchez', 9, 'Defenders', 10),
(55, 'Danny', 'Rose', 7, 'Defenders', 10),
(56, 'Erik', 'Lamela', 8, 'Midfielders', 10),
(57, 'Dele', 'Alli', 10, 'Midfielders', 10),
(58, 'Harry', 'Kane', 12, 'Forwards', 10);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `player_result`
--

CREATE TABLE IF NOT EXISTS `player_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `result_type` varchar(45) NOT NULL,
  `points` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Zrzut danych tabeli `player_result`
--

INSERT INTO `player_result` (`id`, `result_type`, `points`) VALUES
(1, '0', 0),
(2, '1', 1),
(3, '2', 2),
(4, '3', 3),
(5, '4', 4),
(6, '5', 5),
(7, '6', 6);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `real_team`
--

CREATE TABLE IF NOT EXISTS `real_team` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Zrzut danych tabeli `real_team`
--

INSERT INTO `real_team` (`id`, `name`) VALUES
(2, 'Arsenal'),
(4, 'Chelsea'),
(8, 'Manchester City'),
(9, 'Manchester United'),
(10, 'Tottenham Hotspur'),
(11, 'Liverpool'),
(12, 'Burnley'),
(13, 'Leicester City'),
(14, 'Everton');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `result`
--

CREATE TABLE IF NOT EXISTS `result` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `result_type` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Zrzut danych tabeli `result`
--

INSERT INTO `result` (`id`, `result_type`) VALUES
(1, 'HOME TEAM WON'),
(2, 'DRAW'),
(3, 'AWAY TEAM WON');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `user_role` varchar(45) DEFAULT 'ROLE_USER',
  `enabled` int(11) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Zrzut danych tabeli `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `user_role`, `enabled`) VALUES
(1, 'admin', 'admin', 'ROLE_ADMIN', 1),
(4, 'user', 'user', 'ROLE_USER', 1),
(5, 'user1', 'user1', 'ROLE_USER', 1),
(6, 'user4', 'user4', 'ROLE_USER', 1),
(7, 'user5', 'user5', 'ROLE_USER', 1),
(8, 'user6', 'user6', 'ROLE_USER', 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users_team`
--

CREATE TABLE IF NOT EXISTS `users_team` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `user_id` int(11) NOT NULL,
  `score` int(11) NOT NULL DEFAULT '0',
  `teamSelected` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`,`user_id`),
  KEY `fk_users_team_user1_idx` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=32 ;

--
-- Zrzut danych tabeli `users_team`
--

INSERT INTO `users_team` (`id`, `name`, `user_id`, `score`, `teamSelected`) VALUES
(21, 'asd', 4, 0, 0),
(22, 'user1team', 5, 0, 0),
(23, 'user1team', 5, 0, 0),
(24, 'userteam', 4, 0, 0),
(25, 'w', 4, 0, 0),
(26, 'a', 5, 0, 0),
(27, 'TeamUser', 4, 0, 0),
(28, 'team2', 5, 0, 0),
(29, 'user4Team', 6, 0, 0),
(30, 'user5Team', 7, 0, 0),
(31, 'user6Team', 8, 0, 0);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users_team_player`
--

CREATE TABLE IF NOT EXISTS `users_team_player` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `users_team_id` int(11) NOT NULL,
  `player_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`users_team_id`,`player_id`),
  KEY `fk_users_team_has_player_users_team1_idx` (`users_team_id`),
  KEY `fk_users_team_has_player_player1_idx` (`player_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=70 ;

--
-- Zrzut danych tabeli `users_team_player`
--

INSERT INTO `users_team_player` (`id`, `users_team_id`, `player_id`) VALUES
(31, 21, 25),
(32, 21, 39),
(33, 21, 19),
(34, 21, 40),
(35, 21, 41),
(36, 21, 54),
(37, 21, 21),
(38, 21, 43),
(39, 21, 49),
(40, 21, 56),
(41, 21, 17),
(42, 21, 36),
(43, 21, 50),
(44, 21, 58),
(25, 22, 1),
(26, 22, 5),
(27, 22, 4),
(28, 23, 5),
(29, 23, 2),
(30, 23, 6),
(23, 24, 3),
(24, 24, 2),
(45, 28, 18),
(46, 28, 38),
(47, 28, 46),
(48, 28, 53),
(49, 28, 18),
(50, 28, 38),
(51, 28, 46),
(52, 28, 53),
(53, 28, 13),
(54, 28, 20),
(55, 28, 27),
(56, 28, 32),
(57, 28, 41),
(58, 28, 47),
(59, 28, 55),
(60, 28, 15),
(61, 28, 16),
(62, 28, 34),
(63, 28, 44),
(64, 28, 49),
(65, 28, 4),
(66, 28, 36),
(67, 28, 37),
(68, 28, 50),
(69, 28, 58);

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `league_users_team`
--
ALTER TABLE `league_users_team`
  ADD CONSTRAINT `fk_league_has_users_team_league1` FOREIGN KEY (`league_id`) REFERENCES `league` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_league_has_users_team_users_team1` FOREIGN KEY (`users_team_id`, `users_team_user_id`) REFERENCES `users_team` (`id`, `user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `match_player`
--
ALTER TABLE `match_player`
  ADD CONSTRAINT `fk_match_has_player_match1` FOREIGN KEY (`match_id`) REFERENCES `match_tab` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_match_has_player_player1` FOREIGN KEY (`player_id`) REFERENCES `player` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_match_player_player_result1` FOREIGN KEY (`player_result_id`) REFERENCES `player_result` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `match_tab`
--
ALTER TABLE `match_tab`
  ADD CONSTRAINT `fk_match_real_team2` FOREIGN KEY (`home_team_id`) REFERENCES `real_team` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_match_real_team3` FOREIGN KEY (`away_team_id`) REFERENCES `real_team` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_match_result1` FOREIGN KEY (`result_id`) REFERENCES `result` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `player`
--
ALTER TABLE `player`
  ADD CONSTRAINT `fk_player_real_team1` FOREIGN KEY (`real_team_id`) REFERENCES `real_team` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `users_team_player`
--
ALTER TABLE `users_team_player`
  ADD CONSTRAINT `fk_users_team_has_player_player1` FOREIGN KEY (`player_id`) REFERENCES `player` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_users_team_has_player_users_team1` FOREIGN KEY (`users_team_id`) REFERENCES `users_team` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
