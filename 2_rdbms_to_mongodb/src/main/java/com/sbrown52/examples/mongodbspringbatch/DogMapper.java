package com.sbrown52.examples.mongodbspringbatch;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DogMapper implements RowMapper<Dog> {

    public static final String NAME_COLUMN = "name";
    public static final String AGE_COLUMN = "age";
    public static final String BREED_COLUMN = "breed";
    public static final String COLOUR_COLUMN = "colour";


    public Dog mapRow(ResultSet rs, int rowNum) throws SQLException {
        Dog dog = new Dog();

        dog.setName(rs.getString(NAME_COLUMN));
        dog.setAge(rs.getInt(AGE_COLUMN));
        dog.setBreed(rs.getString(BREED_COLUMN));
        dog.setColour(rs.getString(COLOUR_COLUMN));

        return dog;
    }
}
