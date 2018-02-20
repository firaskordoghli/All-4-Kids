-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mar 20 Février 2018 à 23:52
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `allkids`
--

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

CREATE TABLE IF NOT EXISTS `commentaire` (
  `id_commentaire` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `good` int(11) NOT NULL,
  `bad` int(11) NOT NULL,
  PRIMARY KEY (`id_commentaire`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `covoiturage`
--

CREATE TABLE IF NOT EXISTS `covoiturage` (
  `id_covoiturage` int(11) NOT NULL,
  `type` varchar(255) NOT NULL,
  `time` varchar(255) NOT NULL,
  `depart` varchar(255) NOT NULL,
  `arrive` varchar(255) NOT NULL,
  `nbr_place` int(11) NOT NULL,
  `prix` float NOT NULL,
  `etat` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  PRIMARY KEY (`id_covoiturage`),
  KEY `id_user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `etablissement`
--

CREATE TABLE IF NOT EXISTS `etablissement` (
  `id_etablissement` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `region` varchar(255) NOT NULL,
  `ville` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_etablissement`),
  KEY `id_user` (`id_user`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Contenu de la table `etablissement`
--

INSERT INTO `etablissement` (`id_etablissement`, `nom`, `type`, `region`, `ville`, `description`, `image`, `id_user`) VALUES
(1, 'test', 'test', 'test', 'test', 'test', '', NULL),
(9, 'esen', 'lycée', 'manouba', 'ecole primaire', 'colége', 'src/icons/11esen.png', NULL),
(10, 'kkkkkkk', 'Garderie', 'jardin d''enfant', 'ecole primaire', 'hhhhhhhhhhhhhhhhhhhhhhhhh', 'src/icons/11kkkkkkk.png', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

CREATE TABLE IF NOT EXISTS `evenement` (
  `id_evenement` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `lieu` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `type` varchar(255) NOT NULL,
  `nbr_participation` int(11) NOT NULL,
  `etat` tinyint(1) NOT NULL,
  `id_user` int(11) NOT NULL,
  `photo` varchar(255) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  PRIMARY KEY (`id_evenement`),
  KEY `id_user` (`id_user`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Contenu de la table `evenement`
--

INSERT INTO `evenement` (`id_evenement`, `nom`, `lieu`, `date`, `type`, `nbr_participation`, `etat`, `id_user`, `photo`, `latitude`, `longitude`) VALUES
(12, 'sisi', 'ss', '2018-03-01', 'Parck', 69, 0, 6, 'src/icons/sisi.png', 36.586085205855966, 9.7623586695114),
(14, 'mahdi', 'sdfvsd', '2018-03-01', 'cinema', 23, 0, 6, 'src/icons/mahdi.png', 36.85397335488383, 10.172953605651855);

-- --------------------------------------------------------

--
-- Structure de la table `participevenement`
--

CREATE TABLE IF NOT EXISTS `participevenement` (
  `id_evenement` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  KEY `id_evenement` (`id_evenement`),
  KEY `id_user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `trasnsport`
--

CREATE TABLE IF NOT EXISTS `trasnsport` (
  `id_transport` int(11) NOT NULL AUTO_INCREMENT,
  `region` varchar(250) NOT NULL,
  `ville` varchar(250) NOT NULL,
  `depart` varchar(250) NOT NULL,
  `arrivé` varchar(250) NOT NULL,
  `description` varchar(250) NOT NULL,
  `telephone` varchar(250) NOT NULL,
  `place` varchar(250) NOT NULL,
  `frais` varchar(250) NOT NULL,
  `type` int(250) DEFAULT NULL,
  PRIMARY KEY (`id_transport`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `trasnsport`
--

INSERT INTO `trasnsport` (`id_transport`, `region`, `ville`, `depart`, `arrivé`, `description`, `telephone`, `place`, `frais`, `type`) VALUES
(1, 'nabeul', 'kelibia', 'dsdsd', 'ghazela', 'no smoking', '54000673', '4', 'gratuis', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `cin` varchar(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `pass` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `picture` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=33 ;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`id_user`, `cin`, `nom`, `prenom`, `pass`, `email`, `date`, `picture`, `role`) VALUES
(4, '0747', 'hamdi', 'slim', '22', 'slim', '2018-02-07', 'dde', '1'),
(5, 'dgh', 'dhd', 'dfg', '', 'fvdgghgf', '2018-02-07', 'sgsg', '1'),
(6, 'dgh', 'dhd', 'dfg', '', 'fvdg', '2018-02-07', 'sgsg', '1'),
(8, '65465', 'kjkj', 'kjbjkk', '123', 'rayen@mailm.com', '2018-02-15', '1', '1'),
(14, '85596655', 'dst', 'dg', '123', 'fdg', '2018-02-17', '1', '2'),
(15, '85596655', 'dst', 'dg', '123', 'fdgssd@gmail.com', '2018-02-17', '1', '2'),
(17, '5166515', 'fhfcd', 'vcxhfch', '666', 'rayen@esprit.tn', '2018-02-10', '1', '2'),
(20, '26565665', 'cherif', 'faten', '123', 'faten@gmail.cm', '2018-02-09', '1', '3'),
(21, '58562625', 'dfgh', 'dfgh', '123', 'tdvyg@gmil.com', '2018-02-08', '1', '1'),
(22, '5269989', 'fdf', 'sqf', '123', 'rayen@gmail.com', '2018-02-17', '1', '1'),
(23, '04965692', 'cherif', 'rayen', 'e703edc69afd31d053a04fa577c5c260', 'rayen.cherif@esprit.tn', '1994-05-17', '1', '2'),
(24, '0000000', 'hhh', 'hhh', '123', 'hhhh@gmail.com', '2018-02-15', '1', '1'),
(25, '5149585', 'aaaa', 'aaaa', '$2y$10$OiwFTnIppzRN/.hb2ilzl.EasccqqjrRe0cgPTfohuvMnBIRCACSe', 'aaaa@gmail.com', '2018-02-17', '1', '1'),
(26, '2595866', 'zzzzzzzzz', 'zzzzzzzzz', '$2y$10$I9KkTHc12R4OroJU6/s71.vVAH9NF33pEsOpHqDAuZLDKU9a4Yc0G', 'zzzzzz@gmail.com', '2018-02-10', '1', '1'),
(27, '35165596', 'kkkkk', 'kkkkk', '$2y$10$m/uy3H586v1M0ip4Viry7O.ooLKUNLcdDyEx23Im305fp3wdTDHGq', 'kkkk@gmail.com', '2018-02-24', '1', '2'),
(29, '5556666', 'yyyyy', 'yyyy', '$2y$10$ZW71L3tEXj2D1TbSB1tMzOoWtmcCiCdG63Ba6OBjBhtiSoQ7NY966', 'yyyy@gmail.com', '2018-02-23', '1', '2'),
(30, '5625965', 'ttttt', 'ttttt', '$2y$10$4BumfQIB4VZea/GGT3N0b.H8ZXpinEkfGK2Yur4RhBpIH3Ta6TM5e', 'tttt@gmail.com', '2018-02-22', '1', '2'),
(31, '562565665', 'rrrrrrrrr', 'rrrrrrrrr', '$2y$10$9mtoa/h3QrbnOLdqqJFXAunifrwctwufMMOaBSOcIiX7lUZYOTAou', 'rrrrr@gmail.com', '2018-02-16', '1', '1'),
(32, '365965699', 'mmmm', 'mmmm', '$2y$10$skCnOBAloGY/ahC3wyc0guU/Ahwdz/bIO13I7WzjtDwF/asZmQFWa', 'mmmm@gmail.com', '2018-02-17', '1', '1');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `etablissement`
--
ALTER TABLE `etablissement`
  ADD CONSTRAINT `etablissement_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `evenement`
--
ALTER TABLE `evenement`
  ADD CONSTRAINT `evenement_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `participevenement`
--
ALTER TABLE `participevenement`
  ADD CONSTRAINT `participevenement_ibfk_1` FOREIGN KEY (`id_evenement`) REFERENCES `evenement` (`id_evenement`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `participevenement_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
