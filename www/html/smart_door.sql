-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Client :  localhost:3306
-- Généré le :  Mar 19 Mars 2019 à 14:26
-- Version du serveur :  10.1.38-MariaDB-0ubuntu0.18.04.1
-- Version de PHP :  7.2.15-0ubuntu0.18.04.1

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
(46, '2019-03-12 15:05:15', 'L\'utilisateur Lucky RAHERINIAINA a retirer un utilisateur du serveur', NULL);

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
(1, 'aobrochta', '$2y$10$ol7wkHWWluT4DLEDP6fP7eMXxPzYSOFrNl5T67LWo5vTsN.YsyKEW', 'André', 'OBROCHTA', 'profile_img/aobrochta.jpeg', 30),
(2, 'lraheriniaina', '$2y$10$uNxpsTkirdFH.VLAu4Sbe.mULbXFGOscJ9z3/iqM95.vSOL/X9MZ2', 'Lucky', 'RAHERINIAINA', 'profile_img/lraheriniaina.jpeg', 0);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `logs`
--
ALTER TABLE `logs`
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
-- AUTO_INCREMENT pour la table `logs`
--
ALTER TABLE `logs`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;
--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
