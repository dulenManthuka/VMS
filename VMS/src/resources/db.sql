CREATE DATABASE VMS;

USE VMS;

CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL,
                       password VARCHAR(50) NOT NULL,
                       role ENUM('Admin', 'Volunteer') NOT NULL
);

-- Add some test users
INSERT INTO users (username, password, role) VALUES ('admin', 'admin123', 'Admin');
INSERT INTO users (username, password, role) VALUES ('volunteer', 'vol123', 'Volunteer');

CREATE TABLE events (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(100),
                        location VARCHAR(100),
                        date VARCHAR(50)
);

CREATE TABLE volunteers (
                            volunteer_id INT PRIMARY KEY AUTO_INCREMENT,
                            firstName VARCHAR(50),
                            lastName VARCHAR(50),
                            email VARCHAR(100),
                            userName VARCHAR(50),
                            password VARCHAR(50),
                            address VARCHAR(255),
                            currentstatus VARCHAR(50),
                            skills VARCHAR(255)
);

