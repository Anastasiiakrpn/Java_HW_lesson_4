--liquibase formatted sql
--changeset anastasiiapanchenko:create-user-table

CREATE TABLE IF NOT EXISTS users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    email VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE=InnoDB;

--rollback DROP TABLE users;