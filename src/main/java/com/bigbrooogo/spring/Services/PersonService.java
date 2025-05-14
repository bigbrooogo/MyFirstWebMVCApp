package com.bigbrooogo.spring.Services;

import com.bigbrooogo.spring.Models.Person;
import com.bigbrooogo.spring.Repositories.PersonRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepository personRepository;
    private final EntityManager em;

    @Autowired
    public PersonService(PersonRepository personRepository, EntityManager em) {
        this.personRepository = personRepository;
        this.em = em;
    }

    public List<Person> getPeople() {
        return personRepository.findAll();
    }

    public Person getPerson(int id) {
        Session session = em.unwrap(Session.class);
        Person person = (Person) session.get(Person.class, id);
        Hibernate.initialize(person.getBooks());
        return person;
    }

    @Transactional
    public void savePerson(Person person) {
        personRepository.save(person);
    }

    @Transactional
    public void update(Person updatedPerson, int id) {
        Session session = em.unwrap(Session.class);
        Person oldPerson = (Person) session.get(Person.class, id);
        oldPerson.setFIO(updatedPerson.getFIO());
        oldPerson.setYear(updatedPerson.getYear());
    }

    @Transactional
    public void delete(int id) {
        personRepository.deleteById(id);
    }

    public EntityManager getEm() {
        return em;
    }
}
