-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mer. 24 avr. 2019 à 09:23
-- Version du serveur :  5.7.23
-- Version de PHP :  7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `projetweb`
--

-- --------------------------------------------------------

--
-- Structure de la table `article`
--

DROP TABLE IF EXISTS `article`;
CREATE TABLE IF NOT EXISTS `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL,
  `stock` int(11) NOT NULL,
  `prix` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `article`
--

INSERT INTO `article` (`id`, `nom`, `stock`, `prix`) VALUES
(1, 'test', 2, 15);

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

DROP TABLE IF EXISTS `commande`;
CREATE TABLE IF NOT EXISTS `commande` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `utilisateur_ref_id` int(11) NOT NULL,
  `article_ref_id` int(11) NOT NULL,
  `quantite` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `FK_Article` (`article_ref_id`),
  KEY `FK_Utilisateur` (`utilisateur_ref_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`id`, `utilisateur_ref_id`, `article_ref_id`, `quantite`) VALUES
(1, 1, 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Prenom` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Pseudo` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Mdp` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `role` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `Nom`, `Prenom`, `Pseudo`, `Mdp`, `role`) VALUES
(1, 'Saunier', 'Guillaume', 'fujine', 'fujine', 0),
(4, 'Test', 'Test', 'test', 'test', 1),
(5, 'dsqd', 'dsq', 'qsdq', 'dqsd', 1),
(6, 'test', 'test', 'test', 'test', 1),
(7, 'test', 'test', 'ezfsf', 'dfsf', 1),
(8, 'Willaime', 'Noah', 'toto', 'toto', 1);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `FK_Article` FOREIGN KEY (`article_ref_id`) REFERENCES `article` (`id`),
  ADD CONSTRAINT `FK_Utilisateur` FOREIGN KEY (`utilisateur_ref_id`) REFERENCES `utilisateur` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
