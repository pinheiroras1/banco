CREATE TABLE  `Banco`.`conta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titular` int(11) NOT NULL,
  `saldo` double NOT NULL,
  `limite` double NOT NULL,
  `tipo` char(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1
