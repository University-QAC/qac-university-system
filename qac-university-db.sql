-- DROP DATABASE qac_university_db;
CREATE DATABASE qac_university_db;
USE qac_university_db;

SET GLOBAL time_zone = '+00:00';

CREATE TABLE account (
    UUID BINARY(16) PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    image_path VARCHAR(255),
    is_verified BOOLEAN DEFAULT FALSE,
    verification_code VARCHAR(4),
    code_expired_at DATETIME,
    activated_until DATETIME
);

CREATE TABLE university (
    UUID BINARY(16) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    logo_path VARCHAR(255)
);

CREATE TABLE collage (
    UUID BINARY(16) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    logo_path VARCHAR(255),
    university BINARY(16),
    FOREIGN KEY (university) REFERENCES university(UUID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE program (
    UUID BINARY(16) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    collage BINARY(16),
    FOREIGN KEY (collage) REFERENCES collage(UUID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE requirement (
    UUID BINARY(16) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE template (
    UUID BINARY(16) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    template_path VARCHAR(255),
    requirement BINARY(16),
    FOREIGN KEY (requirement) REFERENCES requirement(UUID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE university_accounts(
    account_uuid BINARY(16),
    university_uuid BINARY(16),
    PRIMARY KEY (account_uuid, university_uuid),
    FOREIGN KEY (account_uuid) REFERENCES account(UUID) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (university_uuid) REFERENCES university(UUID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE collage_accounts(
    account_uuid BINARY(16),
    collage_uuid BINARY(16),
    role ENUM('COLLAGE_MANAGER', 'SENDER', 'QAC_REVIEWER') NOT NULL,
    PRIMARY KEY (account_uuid, collage_uuid),
    FOREIGN KEY (account_uuid) REFERENCES account(UUID) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (collage_uuid) REFERENCES collage(UUID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE program_accounts(
    account_uuid BINARY(16),
    program_uuid BINARY(16),
    role ENUM('SENDER', 'QAC_REVIEWER') NOT NULL,
    PRIMARY KEY (account_uuid, program_uuid),
    FOREIGN KEY (account_uuid) REFERENCES account(UUID) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (program_uuid) REFERENCES program(UUID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE collage_requirements(
    requirement_uuid BINARY(16),
    collage_uuid BINARY(16),
    deadline_from DATETIME,
    deadline_to DATETIME,
    is_fulfilled BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (requirement_uuid, collage_uuid),
    FOREIGN KEY (requirement_uuid) REFERENCES requirement(UUID) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (collage_uuid) REFERENCES collage(UUID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE collage_requirements_reviews(
    requirement_uuid BINARY(16),
    collage_uuid BINARY(16),
    account_uuid BINARY(16),
    score DECIMAL(4,2),
    notes TEXT,
    PRIMARY KEY (requirement_uuid, collage_uuid, account_uuid),
    FOREIGN KEY (requirement_uuid, collage_uuid) REFERENCES collage_requirements(requirement_uuid, collage_uuid) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (account_uuid) REFERENCES account(UUID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE program_requirements(
    requirement_uuid BINARY(16),
    program_uuid BINARY(16),
    deadline_from DATETIME,
    deadline_to DATETIME,
    is_fulfilled BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (requirement_uuid, program_uuid),
    FOREIGN KEY (requirement_uuid) REFERENCES requirement(UUID) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (program_uuid) REFERENCES program(UUID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE program_requirements_reviews(
    requirement_uuid BINARY(16),
    program_uuid BINARY(16),
    account_uuid BINARY(16),
    score DECIMAL(4,2),
    notes TEXT,
    PRIMARY KEY (requirement_uuid, program_uuid, account_uuid),
    FOREIGN KEY (requirement_uuid, program_uuid) REFERENCES program_requirements(requirement_uuid, program_uuid) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (account_uuid) REFERENCES account(UUID) ON UPDATE CASCADE ON DELETE CASCADE
);