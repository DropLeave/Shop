CREATE TABLE `orderitem` (
  `itemid` int(11) NOT NULL AUTO_INCREMENT,
  `count` int(11) DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `oid` int(11) DEFAULT NULL,
  PRIMARY KEY (`itemid`),
  KEY `FKE8B2AB6166C01961` (`oid`),
  KEY `FKE8B2AB6171DB7AE4` (`pid`),
  CONSTRAINT `FKE8B2AB6166C01961` FOREIGN KEY (`oid`) REFERENCES `orders` (`oid`),
  CONSTRAINT `FKE8B2AB6171DB7AE4` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;