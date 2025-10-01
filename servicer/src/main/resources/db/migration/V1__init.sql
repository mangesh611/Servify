-- Initial schema for servicer
CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS services (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    base_price DECIMAL(12,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS service_providers (
    id BIGINT PRIMARY KEY,
    occupation VARCHAR(100) NOT NULL,
    experience INT NOT NULL,
    description TEXT,
    CONSTRAINT fk_sp_user FOREIGN KEY (id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS provider_services (
    provider_id BIGINT NOT NULL,
    service_id BIGINT NOT NULL,
    PRIMARY KEY (provider_id, service_id),
    CONSTRAINT fk_ps_provider FOREIGN KEY (provider_id) REFERENCES service_providers(id),
    CONSTRAINT fk_ps_service FOREIGN KEY (service_id) REFERENCES services(id)
);

CREATE TABLE IF NOT EXISTS bookings (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    provider_id BIGINT NOT NULL,
    service_id BIGINT NOT NULL,
    booking_date DATETIME NOT NULL,
    status VARCHAR(20) NOT NULL,
    CONSTRAINT fk_b_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_b_provider FOREIGN KEY (provider_id) REFERENCES service_providers(id),
    CONSTRAINT fk_b_service FOREIGN KEY (service_id) REFERENCES services(id)
);

CREATE TABLE IF NOT EXISTS payments (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    booking_id BIGINT NOT NULL UNIQUE,
    amount DECIMAL(12,2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    payment_date DATETIME,
    CONSTRAINT fk_p_booking FOREIGN KEY (booking_id) REFERENCES bookings(id)
);

CREATE TABLE IF NOT EXISTS reviews (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    booking_id BIGINT NOT NULL UNIQUE,
    rating INT NOT NULL,
    comment TEXT,
    CONSTRAINT fk_r_booking FOREIGN KEY (booking_id) REFERENCES bookings(id)
);


