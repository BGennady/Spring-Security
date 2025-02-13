CREATE TABLE hibernate.PERSONS (

    name VARCHAR(25) NOT NULL,
    surname VARCHAR(35) NOT NULL,
    age INT NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    city_of_living VARCHAR(50) NOT NULL,
    PRIMARY KEY (name, surname, age)
);