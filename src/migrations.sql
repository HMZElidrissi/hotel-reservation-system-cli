CREATE TYPE room_type AS ENUM ('SINGLE', 'DOUBLE', 'SUITE');
CREATE TYPE reservation_status AS ENUM ('ACTIVE', 'CANCELLED', 'COMPLETED');
CREATE TYPE event_type AS ENUM ('CONFERENCE', 'WEDDING', 'BIRTHDAY', 'NONE');

CREATE TABLE clients
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(255)        NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(20)         NOT NULL
);

CREATE TABLE rooms
(
    id          SERIAL PRIMARY KEY,
    room_number VARCHAR(20) UNIQUE NOT NULL,
    room_type   room_type          NOT NULL,
    price       DECIMAL(10, 2)     NOT NULL
);

CREATE TABLE reservations
(
    id        SERIAL PRIMARY KEY,
    client_id INT REFERENCES clients (id),
    room_id   INT REFERENCES rooms (id),
    check_in  DATE               NOT NULL,
    check_out DATE               NOT NULL,
    status    reservation_status NOT NULL,
    event     event_type
);

CREATE INDEX idx_reservations_client_id ON reservations (client_id);
CREATE INDEX idx_reservations_room_id ON reservations (room_id);
CREATE INDEX idx_reservations_check_in ON reservations (check_in);
CREATE INDEX idx_reservations_check_out ON reservations (check_out);

INSERT INTO clients (name, email, phone)
VALUES ('Mohammed Alaoui', 'mohammed.alaoui@example.com', '0661-234567'),
       ('Fatima Bennouna', 'fatima.bennouna@example.com', '0707-891234'),
       ('Youssef El Fassi', 'youssef.elfassi@example.com', '0668-567890'),
       ('Sarah Thompson', 'sarah.thompson@example.com', '+44-7911-123456');

INSERT INTO rooms (room_number, room_type, price)
VALUES ('Fes101', 'SINGLE', 800.00),
       ('Marrakech102', 'DOUBLE', 1200.00),
       ('Casablanca103', 'SINGLE', 850.00),
       ('Rabat201', 'DOUBLE', 1300.00),
       ('Sahara202', 'SUITE', 2500.00);

INSERT INTO reservations (client_id, room_id, check_in, check_out, status, event)
VALUES (1, 1, '2024-05-15', '2024-05-20', 'ACTIVE', 'CONFERENCE'),
       (2, 2, '2024-07-18', '2024-07-22', 'ACTIVE', 'WEDDING'),
       (3, 5, '2024-09-10', '2024-09-15', 'ACTIVE', 'NONE'),
       (4, 3, '2024-12-22', '2024-12-28', 'ACTIVE', 'NONE'),
       (1, 4, '2025-01-10', '2025-01-15', 'ACTIVE', 'CONFERENCE'),
       (2, 1, '2024-04-01', '2024-04-05', 'COMPLETED', 'NONE'),
       (3, 2, '2024-10-15', '2024-10-20', 'CANCELLED', 'CONFERENCE');