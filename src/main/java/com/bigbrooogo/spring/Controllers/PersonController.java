package com.bigbrooogo.spring.Controllers;

import com.bigbrooogo.spring.Models.Person;
import com.bigbrooogo.spring.Services.BookService;
import com.bigbrooogo.spring.Services.PersonService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonService personService;


    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String getPeople(Model model) {
        model.addAttribute("peoples", personService.getPeople());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable("id") int id, Model personModel, Model bookModel) {
        Person person = personService.getPerson(id);
        personModel.addAttribute("person", person);
        bookModel.addAttribute("books", person.getBooks());
        return "people/show";
    }

    @GetMapping("/new")
    public String showPerson(Model model) {
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping()
    public String postPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "people/new";
        }
        personService.savePerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String showPersonEdit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personService.getPerson(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String editPerson(@PathVariable("id") int id, @ModelAttribute @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        personService.update(person, id);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}/delete")
    public String deletePerson(@PathVariable("id") int id) {
        personService.delete(id);
        return "redirect:/people";
    }

}
