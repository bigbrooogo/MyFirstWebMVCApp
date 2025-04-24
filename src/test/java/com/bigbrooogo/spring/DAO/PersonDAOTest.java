package com.bigbrooogo.spring.DAO;

import com.bigbrooogo.spring.Model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@PropertySource("classpath:database.properties")
class PersonDAOTest {

    private PersonDAO personDAO;

    PersonDAOTest() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/myfirstwebmvcapp");
        dataSource.setUsername("admin");
        dataSource.setPassword("admin");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        personDAO = new PersonDAO(jdbcTemplate);
    }


    @Test
    void getPeople() {
        List<Person> people = personDAO.getPeople();
        assertEquals(4, people.size());
    }

    @Test
    void getPersonFIO() {
        Person person = personDAO.getPeople().get(3);
        assertEquals("Zhuravlev Sergey Evgenevich", person.getFIO());
    }
    @Test
    void getPersonYear() {
        Person person = personDAO.getPeople().get(3);
        assertEquals(1971, person.getYear());
    }
    @Test
    void getPersonId() {
        Person person = personDAO.getPeople().get(3);
        assertEquals(4, person.getPersonId());
    }
    @Test
    void getId() {
        Person person = personDAO.getPerson(3);
        assertEquals(3, person.getPersonId());
    }
}