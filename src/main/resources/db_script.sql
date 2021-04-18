CREATE TABLE IF NOT EXISTS homework_1 (
    id        			integer PRIMARY KEY,
    station_to       VARCHAR(255) NOT NULL,
    date_from        date,
    date_to   			date,
    station_from     VARCHAR(255)
);

INSERT INTO "homework_1" VALUES
('0', 'Kiev', '2021-03-15', '2021-03-16', 'Odessa'),
('1', 'Odessa', '2021-03-15', '2021-03-16', 'Kiev'),
('2', 'Lviv', '2021-03-16', '2021-03-17', 'Kiev'),
('3', 'Odessa', '2021-03-15', '2021-03-17', 'Lviv');

