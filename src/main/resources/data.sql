INSERT INTO Guests(first_name, middle_name, last_name, email, phone_number, is_deleted)
VALUES
('Ivan','Petrov','Ivanov','ivan@mail.com','0888123456',FALSE),
('Maria','Georgieva','Petrova','maria@mail.com','0888456789',FALSE),
('Nikolay',NULL,'Dimitrov','nikolay@mail.com','0899123456',FALSE);

INSERT INTO Room_Types(type_name,capacity,price_per_night,pets_allowed)
VALUES
('Single',1,80,FALSE),
('Double',2,120,TRUE),
('Deluxe',3,220,TRUE),
('Apartment',5,350,TRUE);

INSERT INTO Rooms(room_number,floor,room_status,room_type_id,balcony)
VALUES
('101',1,'AVAILABLE',1,FALSE),
('102',1,'AVAILABLE',2,TRUE),
('201',2,'OCCUPIED',3,TRUE),
('202',2,'CLEANING',2,FALSE),
('301',3,'MAINTENANCE',4,TRUE),
('302',3,'AVAILABLE',4,TRUE);

INSERT INTO Reservations(status,check_in_date,check_out_date,guest_id,payment_method,deposit_amount,total_amount)
VALUES
('CONFIRMED','2026-08-10','2026-08-15',1,'CARD',100,600),

('CHECKED_IN','2026-08-05','2026-08-08',2,'CASH',50,360),

('CANCELLED','2026-09-01','2026-09-03',3,'BANK_TRANSFER',0,240);

INSERT INTO Guest_Persons(first_name,last_name,birth_date,guest_type,reservation_id)
VALUES
('Ivan','Ivanov','1995-02-10','ADULT',1),
('Petya','Ivanova','1998-08-15','ADULT',1),

('Maria','Petrova','1992-11-05','ADULT',2),
('Georgi','Petrov','2016-04-01','CHILD',2),
('Nikolay','Dimitrov','1988-07-20','ADULT',3);

INSERT INTO Reservation_Rooms(reservation_id,room_id)
VALUES
(1,2),
(2,3),
(3,1);