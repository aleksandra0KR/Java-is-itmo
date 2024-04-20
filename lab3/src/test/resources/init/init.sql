CREATE TABLE People
(
    person_id BIGINT PRIMARY KEY,
    name      VARCHAR(255) NOT NULL,
    birthdate DATE         NOT NULL
);

CREATE TABLE Cats
(
    id       BIGINT PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    birthday DATE         NOT NULL,
    breed    breeds,
    color    colors,
    owner_id BIGINT,
    FOREIGN KEY (owner_id) REFERENCES People (person_id)
);

CREATE TABLE friends
(
    cat_id    BIGINT REFERENCES Cats (id),
    friend_id BIGINT references Cats (id)
)
