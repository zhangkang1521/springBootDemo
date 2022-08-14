CREATE TABLE `tb_user` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT,
                        `username` varchar(20) DEFAULT NULL,
                        `gmt_create` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                        PRIMARY KEY (`id`)
);