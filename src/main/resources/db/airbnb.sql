-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 14-10-2020 a las 01:02:51
-- Versión del servidor: 10.4.13-MariaDB
-- Versión de PHP: 7.4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `airbnb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `listings`
--

CREATE TABLE `listings` (
  `id` varchar(64) COLLATE utf8_spanish_ci NOT NULL,
  `owner_id` text COLLATE utf8_spanish_ci DEFAULT NULL,
  `name` text COLLATE utf8_spanish_ci NOT NULL,
  `slug` text COLLATE utf8_spanish_ci NOT NULL,
  `description` text COLLATE utf8_spanish_ci NOT NULL,
  `adults` int(11) NOT NULL,
  `children` int(11) NOT NULL,
  `is_pets_allowed` tinyint(1) NOT NULL,
  `base_price` double NOT NULL,
  `cleaning_fee` double NOT NULL,
  `image_url` text COLLATE utf8_spanish_ci NOT NULL,
  `weekly_discount` double NOT NULL,
  `monthly_discount` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Estructura de tabla para la tabla `special_prices`
--

CREATE TABLE `special_prices` (
  `id` varchar(64) COLLATE utf8_spanish_ci NOT NULL,
  `listing_id` text COLLATE utf8_spanish_ci NOT NULL,
  `date` date NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id` varchar(64) COLLATE utf8_spanish_ci NOT NULL,
  `name` text COLLATE utf8_spanish_ci NOT NULL,
  `email` text COLLATE utf8_spanish_ci NOT NULL,
  `password` text COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `password`) VALUES
('d290f1ee-6c54-4b01-90e6-d701748f0851', 'Kuchen, Germán', 'germankuchen@gmail.com', 'german');
COMMIT;


