CREATE USER 'warplayer'@'localhost' IDENTIFIED BY 'warplayer';
GRANT ALL PRIVILEGES ON *.* TO 'warplayer'@'localhost';
ALTER USER 'warplayer'@'localhost' IDENTIFIED WITH mysql_native_password BY 'warplayer';

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `game_tbl`;
SET FOREIGN_KEY_CHECKS=1;
CREATE TABLE `game_tbl` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
    `winner` varchar(45) DEFAULT NULL,
    `turnsToWin` int(11),
    `player1` varchar(45) DEFAULT 'player1',
    `player2` varchar(45) default 'player2',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `turn_tbl`;
SET FOREIGN_KEY_CHECKS=1;
CREATE TABLE `turn_tbl` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
    `player1Score` int(11) DEFAULT NULL,
    `player1GameDeck` int(11) DEFAULT NULL,
    `player1WinDeck` int(11) DEFAULT NULL,
    `player2Score` int(11)DEFAULT NULL,
	`player2GameDeck` int(11) DEFAULT NULL,
    `player2WinDeck` int(11) DEFAULT NULL,
    `game_id` int(11) DEFAULT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`game_id`) REFERENCES game_tbl (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;