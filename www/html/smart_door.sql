-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Client :  localhost:3306
-- Généré le :  Mer 01 Mai 2019 à 12:54
-- Version du serveur :  10.1.38-MariaDB-0ubuntu0.18.04.1
-- Version de PHP :  7.2.17-0ubuntu0.18.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `smart_door`
--

-- --------------------------------------------------------

--
-- Structure de la table `allowed_people`
--

CREATE TABLE `allowed_people` (
  `id` int(11) NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `status` int(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `allowed_people`
--

INSERT INTO `allowed_people` (`id`, `image_url`, `status`) VALUES
(1, '/img/test.png', 1),
(2, '', 0);

-- --------------------------------------------------------

--
-- Structure de la table `logs`
--

CREATE TABLE `logs` (
  `id` int(10) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `text` text NOT NULL,
  `img` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `logs`
--

INSERT INTO `logs` (`id`, `date`, `text`, `img`) VALUES
(5, '2019-03-12 13:41:47', 'L\'utilisateur André OBROCHTA a refusé une personne', 'refuse_img/lraheriniaina.jpeg'),
(6, '2019-03-12 14:24:36', 'L\'utilisateur André OBROCHTA a refusé une personne', 'refuse_img/lraheriniaina.jpeg'),
(7, '2019-03-12 14:24:36', 'L\'utilisateur André OBROCHTA a refusé une personne', 'refuse_img/amnitibaa.jpeg'),
(8, '2019-03-12 14:26:58', 'L\'utilisateur André OBROCHTA a accepté une personne', 'accepted_img/aobrochta.jpeg'),
(9, '2019-03-12 14:26:59', 'L\'utilisateur André OBROCHTA a accepté une personne', 'accepted_img/akadri.jpeg'),
(10, '2019-03-12 14:27:00', 'L\'utilisateur André OBROCHTA a refusé une personne', 'refuse_img/lraheriniaina.jpeg'),
(11, '2019-03-12 14:27:00', 'L\'utilisateur André OBROCHTA a refusé une personne', 'refuse_img/amnitibaa.jpeg'),
(12, '2019-03-12 14:37:24', 'L\'utilisateur André OBROCHTA a whitelisté un utilisateur banni', 'accepted_img/amnitibaa.jpeg'),
(13, '2019-03-12 14:37:55', 'L\'utilisateur André OBROCHTA a banni un utilisateur', 'refuse_img/amnitibaa.jpeg'),
(14, '2019-03-12 14:38:03', 'L\'utilisateur André OBROCHTA a \'whitelisté\' un utilisateur banni', 'accepted_img/amnitibaa.jpeg'),
(15, '2019-03-12 14:40:56', 'L\'utilisateur André OBROCHTA a retirer un utilisateur du serveur', ''),
(16, '2019-03-12 14:41:47', 'L\'utilisateur André OBROCHTA a retirer un utilisateur du serveur', NULL),
(17, '2019-03-12 14:42:27', 'L\'utilisateur André OBROCHTA a retirer un utilisateur du serveur', NULL),
(18, '2019-03-12 14:42:36', 'L\'utilisateur André OBROCHTA a retirer un utilisateur du serveur', NULL),
(19, '2019-03-12 14:42:37', 'L\'utilisateur André OBROCHTA a retirer un utilisateur du serveur', NULL),
(20, '2019-03-12 14:42:38', 'L\'utilisateur André OBROCHTA a retirer un utilisateur du serveur', NULL),
(21, '2019-03-12 14:43:31', 'L\'utilisateur Lucky RAHERINIAINA a accepté une personne', 'accepted_img/aobrochta.jpeg'),
(22, '2019-03-12 14:43:32', 'L\'utilisateur Lucky RAHERINIAINA a accepté une personne', 'accepted_img/akadri.jpeg'),
(23, '2019-03-12 14:43:32', 'L\'utilisateur Lucky RAHERINIAINA a accepté une personne', 'accepted_img/lraheriniaina.jpeg'),
(24, '2019-03-12 14:43:33', 'L\'utilisateur Lucky RAHERINIAINA a accepté une personne', 'accepted_img/amnitibaa.jpeg'),
(25, '2019-03-12 14:43:47', 'L\'utilisateur Lucky RAHERINIAINA a banni un utilisateur', 'refuse_img/aobrochta.jpeg'),
(26, '2019-03-12 14:57:40', 'L\'utilisateur Lucky RAHERINIAINA a accepté une personne', 'accepted_img/aobrochta.jpeg'),
(27, '2019-03-12 14:57:41', 'L\'utilisateur Lucky RAHERINIAINA a accepté une personne', 'accepted_img/akadri.jpeg'),
(28, '2019-03-12 14:57:42', 'L\'utilisateur Lucky RAHERINIAINA a refusé une personne', 'refuse_img/lraheriniaina.jpeg'),
(29, '2019-03-12 14:57:42', 'L\'utilisateur Lucky RAHERINIAINA a refusé une personne', 'refuse_img/amnitibaa.jpeg'),
(30, '2019-03-12 14:58:04', 'L\'utilisateur Lucky RAHERINIAINA a retirer un utilisateur du serveur', NULL),
(31, '2019-03-12 14:58:13', 'L\'utilisateur Lucky RAHERINIAINA a retirer un utilisateur du serveur', NULL),
(32, '2019-03-12 14:58:18', 'L\'utilisateur Lucky RAHERINIAINA a whitelisté un utilisateur banni', 'accepted_img/lraheriniaina.jpeg'),
(33, '2019-03-12 14:58:26', 'L\'utilisateur Lucky RAHERINIAINA a retirer un utilisateur du serveur', NULL),
(34, '2019-03-12 14:58:28', 'L\'utilisateur Lucky RAHERINIAINA a retirer un utilisateur du serveur', NULL),
(35, '2019-03-12 15:01:57', 'L\'utilisateur Lucky RAHERINIAINA a accepté une personne', 'accepted_img/aobrochta.jpeg'),
(36, '2019-03-12 15:01:58', 'L\'utilisateur Lucky RAHERINIAINA a accepté une personne', 'accepted_img/akadri.jpeg'),
(37, '2019-03-12 15:01:58', 'L\'utilisateur Lucky RAHERINIAINA a accepté une personne', 'accepted_img/lraheriniaina.jpeg'),
(38, '2019-03-12 15:01:59', 'L\'utilisateur Lucky RAHERINIAINA a accepté une personne', 'accepted_img/amnitibaa.jpeg'),
(39, '2019-03-12 15:05:08', 'L\'utilisateur Lucky RAHERINIAINA a retirer un utilisateur du serveur', NULL),
(40, '2019-03-12 15:05:08', 'L\'utilisateur Lucky RAHERINIAINA a retirer un utilisateur du serveur', NULL),
(41, '2019-03-12 15:05:09', 'L\'utilisateur Lucky RAHERINIAINA a retirer un utilisateur du serveur', NULL),
(42, '2019-03-12 15:05:09', 'L\'utilisateur Lucky RAHERINIAINA a retirer un utilisateur du serveur', NULL),
(43, '2019-03-12 15:05:12', 'L\'utilisateur Lucky RAHERINIAINA a retirer un utilisateur du serveur', NULL),
(44, '2019-03-12 15:05:13', 'L\'utilisateur Lucky RAHERINIAINA a retirer un utilisateur du serveur', NULL),
(45, '2019-03-12 15:05:14', 'L\'utilisateur Lucky RAHERINIAINA a retirer un utilisateur du serveur', NULL),
(46, '2019-03-12 15:05:15', 'L\'utilisateur Lucky RAHERINIAINA a retirer un utilisateur du serveur', NULL),
(47, '2019-03-19 13:48:21', 'L\'utilisateur André OBROCHTA a accepté une personne', 'accepted_img/aobrochta.jpeg'),
(48, '2019-03-19 13:48:23', 'L\'utilisateur André OBROCHTA a accepté une personne', 'accepted_img/akadri.jpeg'),
(49, '2019-03-19 13:48:25', 'L\'utilisateur André OBROCHTA a accepté une personne', 'accepted_img/lraheriniaina.jpeg'),
(50, '2019-03-19 13:48:26', 'L\'utilisateur André OBROCHTA a accepté une personne', 'accepted_img/amnitibaa.jpeg'),
(51, '2019-03-19 13:51:31', 'L\'utilisateur André OBROCHTA a banni un utilisateur', 'refuse_img/aobrochta.jpeg'),
(52, '2019-03-19 13:53:02', 'L\'utilisateur André OBROCHTA a whitelisté un utilisateur banni', 'accepted_img/aobrochta.jpeg'),
(53, '2019-03-19 13:54:40', 'L\'utilisateur André OBROCHTA a banni un utilisateur', 'refuse_img/aobrochta.jpeg'),
(54, '2019-03-19 13:56:26', 'L\'utilisateur André OBROCHTA a banni un utilisateur', 'refuse_img/akadri.jpeg'),
(55, '2019-03-19 13:56:31', 'L\'utilisateur André OBROCHTA a whitelisté un utilisateur banni', 'accepted_img/aobrochta.jpeg'),
(56, '2019-03-19 13:56:33', 'L\'utilisateur André OBROCHTA a whitelisté un utilisateur banni', 'accepted_img/akadri.jpeg'),
(57, '2019-03-19 13:56:50', 'L\'utilisateur André OBROCHTA a banni un utilisateur', 'refuse_img/aobrochta.jpeg'),
(58, '2019-03-19 13:56:54', 'L\'utilisateur André OBROCHTA a whitelisté un utilisateur banni', 'accepted_img/aobrochta.jpeg'),
(59, '2019-03-19 13:59:07', 'L\'utilisateur André OBROCHTA a banni un utilisateur', 'refuse_img/aobrochta.jpeg'),
(60, '2019-03-19 13:59:09', 'L\'utilisateur André OBROCHTA a banni un utilisateur', 'refuse_img/akadri.jpeg'),
(61, '2019-03-19 13:59:11', 'L\'utilisateur André OBROCHTA a banni un utilisateur', 'refuse_img/lraheriniaina.jpeg'),
(62, '2019-03-19 13:59:13', 'L\'utilisateur André OBROCHTA a banni un utilisateur', 'refuse_img/amnitibaa.jpeg'),
(63, '2019-03-19 13:59:16', 'L\'utilisateur André OBROCHTA a retirer un utilisateur du serveur', NULL),
(64, '2019-03-19 13:59:18', 'L\'utilisateur André OBROCHTA a retirer un utilisateur du serveur', NULL),
(65, '2019-03-19 13:59:43', 'L\'utilisateur André OBROCHTA a retirer un utilisateur du serveur', NULL),
(66, '2019-03-19 13:59:45', 'L\'utilisateur André OBROCHTA a retirer un utilisateur du serveur', NULL),
(67, '2019-03-19 14:54:55', 'L\'utilisateur André OBROCHTA a accepté une personne', 'accepted_img/aobrochta.jpeg'),
(68, '2019-03-19 14:54:57', 'L\'utilisateur André OBROCHTA a accepté une personne', 'accepted_img/akadri.jpeg'),
(69, '2019-03-19 14:54:58', 'L\'utilisateur André OBROCHTA a refusé une personne', 'refuse_img/lraheriniaina.jpeg'),
(70, '2019-03-19 14:54:59', 'L\'utilisateur André OBROCHTA a refusé une personne', 'refuse_img/amnitibaa.jpeg'),
(71, '2019-03-19 15:05:11', 'L\'utilisateur André OBROCHTA a banni un utilisateur', 'refuse_img/aobrochta.jpeg'),
(72, '2019-03-19 15:05:13', 'L\'utilisateur André OBROCHTA a retirer un utilisateur du serveur', NULL),
(73, '2019-03-19 15:05:15', 'L\'utilisateur André OBROCHTA a retirer un utilisateur du serveur', NULL),
(74, '2019-03-19 15:05:16', 'L\'utilisateur André OBROCHTA a retirer un utilisateur du serveur', NULL),
(75, '2019-03-19 15:05:17', 'L\'utilisateur André OBROCHTA a retirer un utilisateur du serveur', NULL),
(76, '2019-04-01 20:41:49', 'L\'utilisateur 0 a ouvert la porte', NULL),
(77, '2019-04-01 20:41:51', 'L\'utilisateur 0 a ouvert la porte', NULL),
(78, '2019-04-01 20:41:52', 'L\'utilisateur 0 a ouvert la porte', NULL),
(79, '2019-04-01 20:41:54', 'L\'utilisateur 0 a ouvert la porte', NULL),
(80, '2019-04-01 20:42:57', 'L\'utilisateur 0 a ouvert la porte', NULL),
(81, '2019-04-01 20:43:12', 'L\'utilisateur 0 a ouvert la porte', NULL),
(82, '2019-04-01 20:43:19', 'L\'utilisateur 0 a ouvert la porte', NULL),
(83, '2019-04-01 20:43:25', 'L\'utilisateur 0 a ouvert la porte', NULL),
(84, '2019-04-01 20:46:22', 'L\'utilisateur 0 a ouvert la porte', NULL),
(85, '2019-04-01 20:46:28', 'L\'utilisateur 0 a ouvert la porte', NULL),
(86, '2019-04-01 20:46:34', 'L\'utilisateur 0 a ouvert la porte', NULL),
(87, '2019-04-01 20:46:41', 'L\'utilisateur 0 a ouvert la porte', NULL),
(88, '2019-04-01 20:46:47', 'L\'utilisateur 0 a ouvert la porte', NULL),
(89, '2019-04-01 20:46:54', 'L\'utilisateur 0 a ouvert la porte', NULL),
(90, '2019-04-01 20:47:01', 'L\'utilisateur 0 a ouvert la porte', NULL),
(91, '2019-04-01 20:47:15', 'L\'utilisateur 0 a ouvert la porte', NULL),
(92, '2019-04-01 20:47:21', 'L\'utilisateur 0 a ouvert la porte', NULL),
(93, '2019-04-01 20:47:28', 'L\'utilisateur 0 a ouvert la porte', NULL),
(94, '2019-04-01 20:47:34', 'L\'utilisateur 0 a ouvert la porte', NULL),
(95, '2019-04-01 20:47:41', 'L\'utilisateur 0 a ouvert la porte', NULL),
(96, '2019-04-01 20:47:47', 'L\'utilisateur 0 a ouvert la porte', NULL),
(97, '2019-04-01 20:47:53', 'L\'utilisateur 0 a ouvert la porte', NULL),
(98, '2019-04-01 20:47:59', 'L\'utilisateur 0 a ouvert la porte', NULL),
(99, '2019-04-01 20:48:06', 'L\'utilisateur 0 a ouvert la porte', NULL),
(100, '2019-04-01 20:48:12', 'L\'utilisateur 0 a ouvert la porte', NULL),
(101, '2019-04-01 20:48:19', 'L\'utilisateur 0 a ouvert la porte', NULL),
(102, '2019-04-01 20:48:25', 'L\'utilisateur 0 a ouvert la porte', NULL),
(103, '2019-04-01 20:48:32', 'L\'utilisateur 0 a ouvert la porte', NULL),
(104, '2019-04-01 20:48:39', 'L\'utilisateur 0 a ouvert la porte', NULL),
(105, '2019-04-01 20:48:45', 'L\'utilisateur 0 a ouvert la porte', NULL),
(106, '2019-04-01 20:50:01', 'L\'utilisateur %d a ouvert la porte', NULL),
(107, '2019-04-01 21:02:53', 'L\'utilisateur 0 a ouvert la porte', NULL),
(108, '2019-04-01 21:03:00', 'L\'utilisateur 0 a ouvert la porte', NULL),
(109, '2019-04-01 21:03:06', 'L\'utilisateur 0 a ouvert la porte', NULL),
(110, '2019-04-01 21:03:13', 'L\'utilisateur 0 a ouvert la porte', NULL),
(111, '2019-04-01 21:03:20', 'L\'utilisateur 0 a ouvert la porte', NULL),
(112, '2019-04-01 21:03:27', 'L\'utilisateur 0 a ouvert la porte', NULL),
(113, '2019-04-01 21:03:33', 'L\'utilisateur 0 a ouvert la porte', NULL),
(114, '2019-04-01 21:05:05', 'L\'utilisateur 0 a ouvert la porte', NULL),
(115, '2019-04-01 21:28:47', 'L\'utilisateur 0 a ouvert la porte', NULL),
(116, '2019-04-01 21:38:56', 'L\'utilisateur 0 a ouvert la porte', NULL),
(117, '2019-04-01 21:51:42', 'L\'utilisateur 0 a ouvert la porte', NULL),
(118, '2019-04-01 21:53:19', 'L\'utilisateur 0 a ouvert la porte', NULL),
(119, '2019-04-01 21:59:08', 'L\'utilisateur 10 a ouvert la porte', NULL),
(120, '2019-04-01 22:04:02', 'L\'utilisateur 0 a ouvert la porte', NULL),
(121, '2019-04-01 22:11:18', 'L\'utilisateur 0 a ouvert la porte', NULL),
(122, '2019-04-01 22:13:51', 'L\'utilisateur 0 a tentÃ© d\'ouvrir la porte', NULL),
(123, '2019-04-01 22:13:55', 'L\'utilisateur 0 a tentÃ© d\'ouvrir la porte', NULL),
(124, '2019-04-01 22:13:58', 'L\'utilisateur 0 a tentÃ© d\'ouvrir la porte', NULL),
(125, '2019-04-01 22:14:02', 'L\'utilisateur 0 a tentÃ© d\'ouvrir la porte', NULL),
(126, '2019-04-01 22:14:06', 'L\'utilisateur 0 a tentÃ© d\'ouvrir la porte', NULL),
(127, '2019-04-01 22:14:10', 'L\'utilisateur 0 a tentÃ© d\'ouvrir la porte', NULL),
(128, '2019-04-01 22:16:22', 'L\'utilisateur 0 a ouvert la porte', NULL),
(129, '2019-04-01 22:17:07', 'L\'utilisateur 0 a tentÃ© d\'ouvrir la porte', NULL),
(130, '2019-04-01 22:17:11', 'L\'utilisateur 0 a tentÃ© d\'ouvrir la porte', NULL),
(131, '2019-04-01 22:17:16', 'L\'utilisateur 0 a tentÃ© d\'ouvrir la porte', NULL),
(132, '2019-04-01 22:17:20', 'L\'utilisateur 0 a tentÃ© d\'ouvrir la porte', NULL),
(133, '2019-04-01 22:17:24', 'L\'utilisateur 0 a tentÃ© d\'ouvrir la porte', NULL),
(134, '2019-04-01 22:19:37', 'L\'utilisateur 0 a ouvert la porte', NULL),
(135, '2019-04-01 22:20:11', 'L\'utilisateur 0 a tentÃ© d\'ouvrir la porte', NULL),
(136, '2019-04-01 22:20:15', 'L\'utilisateur 0 a tentÃ© d\'ouvrir la porte', NULL),
(137, '2019-04-01 22:20:19', 'L\'utilisateur 0 a tentÃ© d\'ouvrir la porte', NULL),
(138, '2019-04-01 22:20:23', 'L\'utilisateur 0 a tentÃ© d\'ouvrir la porte', NULL),
(139, '2019-04-01 22:24:06', 'L\'utilisateur 10 a ouvert la porte', NULL),
(140, '2019-04-01 22:24:50', 'L\'utilisateur 10 a tentÃ© d\'ouvrir la porte', NULL),
(141, '2019-04-01 22:24:54', 'L\'utilisateur 10 a tentÃ© d\'ouvrir la porte', NULL),
(142, '2019-04-01 22:24:58', 'L\'utilisateur 10 a tentÃ© d\'ouvrir la porte', NULL),
(143, '2019-04-01 22:25:01', 'L\'utilisateur 10 a tentÃ© d\'ouvrir la porte', NULL),
(144, '2019-04-01 22:25:05', 'L\'utilisateur 10 a tentÃ© d\'ouvrir la porte', NULL),
(145, '2019-04-01 22:25:08', 'L\'utilisateur 10 a tentÃ© d\'ouvrir la porte', NULL),
(146, '2019-04-01 22:26:47', 'L\'utilisateur 10 a ouvert la porte', NULL),
(147, '2019-04-01 22:27:35', 'L\'utilisateur 0 a ouvert la porte', NULL),
(148, '2019-04-01 22:28:32', 'L\'utilisateur 0 a tentÃ© d\'ouvrir la porte', NULL),
(149, '2019-04-01 22:29:58', 'L\'utilisateur 0 a ouvert la porte', NULL),
(150, '2019-04-01 22:30:49', 'L\'utilisateur 0 a tentÃ© d\'ouvrir la porte', NULL),
(151, '2019-04-01 22:31:23', 'L\'utilisateur 0 a ouvert la porte', NULL),
(152, '2019-04-01 22:31:44', 'L\'utilisateur 0 a tentÃ© d\'ouvrir la porte', NULL),
(153, '2019-04-01 22:37:06', 'L\'utilisateur 0 a ouvert la porte', NULL),
(154, '2019-04-01 22:37:21', 'L\'utilisateur 0 a tentÃ© d\'ouvrir la porte', NULL),
(155, '2019-04-01 22:37:40', 'L\'utilisateur 0 a ouvert la porte', NULL),
(156, '2019-04-26 11:48:37', 'L\'utilisateur 0 a ouvert la porte', NULL),
(157, '2019-04-29 12:59:18', 'L\'utilisateur André OBROCHTA a accepté une personne', 'accepted_img/aobrochta.jpeg'),
(158, '2019-04-29 12:59:23', 'L\'utilisateur André OBROCHTA a banni un utilisateur', 'refuse_img/aobrochta.jpeg'),
(159, '2019-04-29 12:59:24', 'L\'utilisateur André OBROCHTA a whitelisté un utilisateur banni', 'accepted_img/aobrochta.jpeg'),
(160, '2019-04-29 12:59:56', 'L\'utilisateur André OBROCHTA a retirer un utilisateur du serveur', NULL),
(161, '2019-04-30 08:49:03', 'L\'utilisateur André OBROCHTA a accepté une personne', 'accepted_img/lraheriniaina.jpeg'),
(162, '2019-04-30 08:49:07', 'L\'utilisateur André OBROCHTA a retirer un utilisateur du serveur', NULL),
(163, '2019-04-30 09:46:08', 'L\'utilisateur André OBROCHTA a banni un utilisateur', 'refuse_img/220px-Emmanuel_Macron_in_Tallinn_Digital_Summit._Welcome_dinner_hosted_by_HE_Donald_Tusk._Handshake_(36669381364)_(cropped_2).jpg'),
(164, '2019-04-30 09:46:15', 'L\'utilisateur André OBROCHTA a accepté une personne', 'accepted_img/aobrochta.jpeg'),
(165, '2019-04-30 09:46:20', 'L\'utilisateur André OBROCHTA a accepté une personne', 'accepted_img/akadri.jpeg'),
(166, '2019-04-30 09:46:21', 'L\'utilisateur André OBROCHTA a accepté une personne', 'accepted_img/lraheriniaina.jpeg'),
(167, '2019-04-30 09:46:24', 'L\'utilisateur André OBROCHTA a accepté une personne', 'accepted_img/amnitibaa.jpeg'),
(168, '2019-04-30 09:46:27', 'L\'utilisateur André OBROCHTA a banni un utilisateur', 'refuse_img/aobrochta.jpeg'),
(169, '2019-04-30 09:48:01', 'L\'utilisateur André OBROCHTA a whitelisté un utilisateur banni', 'accepted_img/aobrochta.jpeg'),
(170, '2019-04-30 09:48:04', 'L\'utilisateur André OBROCHTA a retirer un utilisateur du serveur', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `test_user`
--

CREATE TABLE `test_user` (
  `id` int(11) NOT NULL,
  `user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `test_user`
--

INSERT INTO `test_user` (`id`, `user`) VALUES
(1, 0);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` int(10) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `img_url` varchar(255) NOT NULL,
  `notification` int(10) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `prenom`, `nom`, `img_url`, `notification`) VALUES
(1, 'aobrochta', '$2y$10$ol7wkHWWluT4DLEDP6fP7eMXxPzYSOFrNl5T67LWo5vTsN.YsyKEW', 'André', 'OBROCHTA', 'profile_img/aobrochta.jpeg', 170),
(2, 'lraheriniaina', '$2y$10$uNxpsTkirdFH.VLAu4Sbe.mULbXFGOscJ9z3/iqM95.vSOL/X9MZ2', 'Lucky', 'RAHERINIAINA', 'profile_img/lraheriniaina.jpeg', 75);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `allowed_people`
--
ALTER TABLE `allowed_people`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `logs`
--
ALTER TABLE `logs`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `test_user`
--
ALTER TABLE `test_user`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `un_username` (`username`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `allowed_people`
--
ALTER TABLE `allowed_people`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `logs`
--
ALTER TABLE `logs`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=171;
--
-- AUTO_INCREMENT pour la table `test_user`
--
ALTER TABLE `test_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
