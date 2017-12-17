CREATE TABLE account (
    id bigint not null,
    name text not null,
    surname text not null,
    login text not null,
    email text not null,
    pass text not null
);

INSERT INTO account (id, name, surname, login, email, pass)
VALUES (1, 'Bartek', 'Nazwisko', 'user', 'user@user.pl', '$2a$10$QJ0m7Et9uvKranL2m/X6guMQGUdpw2vHHM5r399pm2N/RfO7/0a7C');