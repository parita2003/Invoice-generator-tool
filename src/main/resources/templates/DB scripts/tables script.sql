CREATE DATABASE notification_db;

SELECT datname
FROM pg_database;

CREATE TABLE users
(
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(255) UNIQUE,
    mobile VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE templates
(
    id BIGSERIAL PRIMARY KEY,
    template_code VARCHAR(100) UNIQUE,
    subject VARCHAR(255),
    body TEXT,
    channel VARCHAR(20),
    active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE notifications
(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT,
    template_id BIGINT,
    status VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_user
        FOREIGN KEY(user_id)
        REFERENCES users(id),

    CONSTRAINT fk_template
        FOREIGN KEY(template_id)
        REFERENCES templates(id)
);


CREATE TABLE email_configs
(
    id BIGSERIAL PRIMARY KEY,
    provider VARCHAR(50),
    sender_email VARCHAR(255),
    active BOOLEAN
);

CREATE TABLE audit_logs
(
    id BIGSERIAL PRIMARY KEY,
    action VARCHAR(100),
    entity_name VARCHAR(100),
    entity_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

