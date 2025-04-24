package com.bigbrooogo.spring.Mappers.Mappers;

import com.bigbrooogo.spring.Model.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setPersonId(rs.getInt("id"));
        person.setFIO(rs.getString("FIO"));
        person.setYear(rs.getInt("year"));
        return person;
    }
}
