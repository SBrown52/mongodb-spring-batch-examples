DROP TABLE DOGS IF EXISTS;

CREATE TABLE DOGS  (
    id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    name VARCHAR(20),
    age INT,
    breed VARCHAR(20),
    colour VARCHAR(20)
);

INSERT INTO DOGS VALUES
('1', 'Dot', '7', 'Collie', 'Black/White'),
('2', 'Charlie', '8', 'Retriever', 'Golden'),
('3', 'Radish', '6', 'Springer Spaniel', 'Tan/Liver'),
('4', 'Monty', '3', 'Daschund', 'Brown')