CREATE TABLE Guests(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    middle_name VARCHAR(50),
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(20) UNIQUE NOT NULL,
    is_deleted BOOLEAN DEFAULT FALSE
);

CREATE TABLE Room_Types(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    type_name VARCHAR(50) NOT NULL,
    capacity INT NOT NULL,
    price_per_night DECIMAL(10,2) NOT NULL,
    pets_allowed BOOLEAN NOT NULL
);

CREATE TABLE Rooms(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    room_number VARCHAR(10) UNIQUE NOT NULL,
    floor INT NOT NULL,
    room_status VARCHAR(30) NOT NULL,
    room_type_id BIGINT NOT NULL,
    balcony BOOLEAN NOT NULL,

    CONSTRAINT fk_room_roomtype
        FOREIGN KEY(room_type_id)
        REFERENCES Room_Types(id)
);

CREATE TABLE Reservations(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    status VARCHAR(30) NOT NULL,
    check_in_date DATE NOT NULL,
    check_out_date DATE NOT NULL,
    guest_id BIGINT NOT NULL,
    payment_method VARCHAR(30) NOT NULL,
    deposit_amount DECIMAL(10,2),
    total_amount DECIMAL(10,2),

    CONSTRAINT fk_reservation_guest
        FOREIGN KEY(guest_id)
        REFERENCES Guests(id)
);

CREATE TABLE Guest_Persons(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    birth_date DATE NOT NULL,
    guest_type VARCHAR(30) NOT NULL,
    reservation_id BIGINT NOT NULL,

    CONSTRAINT fk_guestperson_reservation
        FOREIGN KEY(reservation_id)
        REFERENCES Reservations(id)
);

CREATE TABLE Reservation_Rooms(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    reservation_id BIGINT NOT NULL,
    room_id BIGINT NOT NULL,

    CONSTRAINT fk_rr_reservation
        FOREIGN KEY(reservation_id)
        REFERENCES Reservations(id),

    CONSTRAINT fk_rr_room
        FOREIGN KEY(room_id)
        REFERENCES Rooms(id)
);