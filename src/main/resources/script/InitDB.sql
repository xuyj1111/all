CREATE
DATABASE IF NOT EXISTS xu CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci;
USE
xu;

DROP TABLE IF EXISTS jpa;
CREATE TABLE jpa
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT,
    `name`         varchar(50) DEFAULT NULL,
    `date_created` datetime NOT NULL,
    `last_updated` datetime NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
INSERT INTO jpa(name, date_created, last_updated)
VALUES ('张三', now(), now());

DROP TABLE IF EXISTS mybatis_plus;
CREATE TABLE mybatis_plus
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT,
    `name`         varchar(50) DEFAULT NULL,
    `date_created` datetime NOT NULL,
    `last_updated` datetime NOT NULL,
    `version`      bigint(20) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO mybatis_plus(name, date_created, last_updated, version)
VALUES ('张三', now(), now(), 1);

DROP TABLE IF EXISTS message;
CREATE TABLE message
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT,
    `content`      varchar(50) DEFAULT NULL,
    `date_created` datetime NOT NULL,
    `last_updated` datetime NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;