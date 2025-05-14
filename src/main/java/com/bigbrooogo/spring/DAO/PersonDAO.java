//package com.bigbrooogo.spring.DAO;
//
//import com.bigbrooogo.spring.Models.Person;
//import com.bigbrooogo.spring.Mappers.Mappers.PersonMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//import java.util.List;
//
//
//@Component
//public class PersonDAO {
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public PersonDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Person> getPeople() {
//        return jdbcTemplate.query("select * from person", new PersonMapper());
//    }
//
//    public Person getPerson(int id) {
//        return jdbcTemplate.query("select * from person where id = ?", new PersonMapper(), id)
//                .stream()
//                .findAny()
//                .orElse(null);
//    }
//
//    public void savePerson(Person person) {
//        jdbcTemplate.update("insert into person (FIO, year) values(?, ?)",
//                person.getFIO(), person.getYear());
//    }
//
//    public void update(Person person, int id) {
//        jdbcTemplate.update("update person set FIO = ?, year = ? where id = ?", person.getFIO(), person.getYear(), id);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("delete from person where id = ?", id);
//    }
//}
