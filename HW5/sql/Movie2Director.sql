CREATE TABLE `movie2director` (
  `moviesDirected_ID` int(11) NOT NULL, `directors_ID` int(11) NOT NULL,
  PRIMARY KEY (`moviesDirected_ID`,`directors_ID`),
  KEY `FK_Movie2Director_directors_ID` (`directors_ID`),
  CONSTRAINT `FK_Movie2Director_directors_ID` FOREIGN KEY (`directors_ID`) REFERENCES `person` (`ID`),
  CONSTRAINT `FK_Movie2Director_moviesDirected_ID` FOREIGN KEY (`moviesDirected_ID`) REFERENCES `movie` (`ID`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
