CREATE TABLE IF NOT EXISTS users (
    email VARCHAR(100) NOT NULL PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR (50) NOT NULL,
    birthDate DATE NOT NULL,
    address VARCHAR(100),
    phoneNumber VARCHAR(15)
    );