-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 16-11-2013 a las 01:18:56
-- Versión del servidor: 5.5.32
-- Versión de PHP: 5.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";

--
-- Base de datos: `control_caja`
--
CREATE DATABASE IF NOT EXISTS `control_caja` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;
USE `control_caja`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asociado`
--

CREATE TABLE IF NOT EXISTS `asociado` (
  `idAsociado` int(11) NOT NULL AUTO_INCREMENT,
  `idCoop` int(11) DEFAULT NULL,
  `legajo` int(11) NOT NULL,
  `cuit` varchar(14) COLLATE utf8_spanish_ci NOT NULL,
  `dni` int(9) DEFAULT NULL,
  `apellido` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombre` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ingreso` date DEFAULT NULL,
  `telefono` varchar(20) COLLATE utf8_spanish_ci DEFAULT NULL,
  `estado` bit(1) DEFAULT NULL,
  PRIMARY KEY (`idAsociado`),
  UNIQUE KEY `legajo` (`legajo`),
  UNIQUE KEY `cuit` (`cuit`),
  KEY `idCoop` (`idCoop`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=5 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoriacomprobante`
--

CREATE TABLE IF NOT EXISTS `categoriacomprobante` (
  `codigoCategoria` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `estado` bit(1) DEFAULT NULL,
  PRIMARY KEY (`codigoCategoria`),
  UNIQUE KEY `descripcion` (`descripcion`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=5 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE IF NOT EXISTS `cliente` (
  `idCliente` int(11) NOT NULL AUTO_INCREMENT,
  `cuit` varchar(14) COLLATE utf8_spanish_ci  NULL,
  `razonSocial` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `domicilio` varchar(200) COLLATE utf8_spanish_ci DEFAULT NULL,
  `estado` bit(1) NOT NULL,
  `telefono` varchar(20) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`idCliente`)
  ) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comprobante`
--

CREATE TABLE IF NOT EXISTS `comprobante` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numeroSerie` varchar(18) COLLATE utf8_spanish_ci DEFAULT NULL,
  `numeroSerieIzq` bigint(4) DEFAULT NULL,
  `numeroSerieDer` bigint(10) DEFAULT NULL,
  `idEntidad` int(11) NOT NULL,
  `tipoPersona` int(2) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `codigoTipoComprob` int(11) DEFAULT NULL,
  `tipoProceso` int(2) DEFAULT NULL,
  `ejercicioEconomico` date DEFAULT NULL,
  `estado` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `numeroSerie` (`numeroSerie`),
  KEY `codigoTipoComprob` (`codigoTipoComprob`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=34 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comprobanteconcepto`
--

CREATE TABLE IF NOT EXISTS `comprobanteconcepto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idComprobante` int(11) DEFAULT NULL,
  `idConcepto` int(11) DEFAULT NULL,
  `monto` double DEFAULT NULL,
  `estado` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idConcepto` (`idConcepto`),
  KEY `idComprobante` (`idComprobante`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=34 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `concepto`
--

CREATE TABLE IF NOT EXISTS `concepto` (
  `codigoConcepto` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `estado` bit(1) DEFAULT NULL,
  PRIMARY KEY (`codigoConcepto`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=13 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cooperativa`
--

CREATE TABLE IF NOT EXISTS `cooperativa` (
  `idCoop` int(11) NOT NULL AUTO_INCREMENT,
  `matricula` int(11) NOT NULL,
  `cuit` varchar(14) COLLATE utf8_spanish_ci NOT NULL,
  `razonSocial` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `inicioActividad` date DEFAULT NULL,
  `telefono` varchar(20) COLLATE utf8_spanish_ci DEFAULT NULL,
  `domicilio` varchar(200) COLLATE utf8_spanish_ci DEFAULT NULL,
  `inicioCaja` double DEFAULT NULL,
  `ingresoBruto` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `estado` bit(1) DEFAULT NULL,
  PRIMARY KEY (`idCoop`),
  UNIQUE KEY `matricula` (`matricula`),
  UNIQUE KEY `cuit` (`cuit`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE IF NOT EXISTS `proveedor` (
  `idProveedor` int(11) NOT NULL AUTO_INCREMENT,
  `cuit` varchar(14) COLLATE utf8_spanish_ci  NULL,
  `razonSocial` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `domicilio` varchar(200) COLLATE utf8_spanish_ci DEFAULT NULL,
  `telefono` varchar(20) COLLATE utf8_spanish_ci DEFAULT NULL,
  `estado` bit(1) NOT NULL,
  PRIMARY KEY (`idProveedor`)
  
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipocomprobante`
--

CREATE TABLE IF NOT EXISTS `tipocomprobante` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `codigoCategoria` int(11) DEFAULT NULL,
  `formulario` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `referencia` varchar(4) COLLATE utf8_spanish_ci DEFAULT NULL,
  `numeroSerieIzq` bigint(4) DEFAULT NULL,
  `numeroSerieDer` bigint(10) DEFAULT NULL,
  `estado` bit(1) DEFAULT NULL,
  `operacion` int(11) NOT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `formulario` (`formulario`),
  KEY `codigoCategoria` (`codigoCategoria`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=9 ;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `asociado`
--
ALTER TABLE `asociado`
  ADD CONSTRAINT `asociado_ibfk_1` FOREIGN KEY (`idCoop`) REFERENCES `cooperativa` (`idCoop`);

--
-- Filtros para la tabla `comprobante`
--
ALTER TABLE `comprobante`
  ADD CONSTRAINT `comprobante_ibfk_1` FOREIGN KEY (`codigoTipoComprob`) REFERENCES `tipocomprobante` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `comprobanteconcepto`
--
ALTER TABLE `comprobanteconcepto`
  ADD CONSTRAINT `comprobanteconcepto_ibfk_1` FOREIGN KEY (`idConcepto`) REFERENCES `concepto` (`codigoConcepto`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `comprobanteconcepto_ibfk_2` FOREIGN KEY (`idComprobante`) REFERENCES `comprobante` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `tipocomprobante`
--
ALTER TABLE `tipocomprobante`
  ADD CONSTRAINT `tipocomprobante_ibfk_1` FOREIGN KEY (`codigoCategoria`) REFERENCES `categoriacomprobante` (`codigoCategoria`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE tipocomprobante AUTO_INCREMENT=1;
ALTER TABLE comprobanteconcepto AUTO_INCREMENT=1;
ALTER TABLE comprobante AUTO_INCREMENT=1;
ALTER TABLE asociado AUTO_INCREMENT=1;
ALTER TABLE concepto AUTO_INCREMENT=1;
ALTER TABLE proveedor AUTO_INCREMENT=1;
ALTER TABLE cliente AUTO_INCREMENT=1;
ALTER TABLE categoriacomprobante AUTO_INCREMENT=1;
ALTER TABLE cooperativa AUTO_INCREMENT=1;