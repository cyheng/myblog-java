SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id`            VARCHAR(255) NOT NULL,
  `create_time`   DATETIME     DEFAULT NULL,
  `update_time`   DATETIME     DEFAULT NULL,
  `category_name` VARCHAR(255) DEFAULT NULL,
  `is_delete`     BIT(1)       DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id`            VARCHAR(255) NOT NULL,
  `create_time`   DATETIME     DEFAULT NULL,
  `update_time`   DATETIME     DEFAULT NULL,
  `allow_comment` BIT(1)       NOT NULL,
  `content`       VARCHAR(255) NOT NULL,
  `show_toc`      BIT(1)       NOT NULL,
  `status`        VARCHAR(10)  NOT NULL,
  `summary`       VARCHAR(255) DEFAULT NULL,
  `title`         VARCHAR(255) NOT NULL,
  `category_id`   VARCHAR(255) NOT NULL,
  `is_delete`     BIT(1)       DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKy5kkohbk00g0w88fi05k2hcw` (`category_id`),
  CONSTRAINT `FKy5kkohbk00g0w88fi05k2hcw` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
