package com.bigbrooogo.spring.Controllers;

import com.bigbrooogo.spring.DAO.BookDAO;
import com.bigbrooogo.spring.DAO.PersonDAO;
import com.bigbrooogo.spring.Model.Book;
import com.bigbrooogo.spring.Model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping
    public String getBooks(Model model) {
        model.addAttribute("books", bookDAO.getBooks());
        return "books/index";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());
        return "books/new";
    }

    @PostMapping
    public String addBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        bookDAO.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, @ModelAttribute("person") Person person,
                           Model bookModel, Model personModel, Model personBook) {
        bookModel.addAttribute("book", bookDAO.getBook(id));
        personModel.addAttribute("people", personDAO.getPeople());
        personBook.addAttribute("personBook", personDAO.getPerson(bookDAO.getBook(id).getPersonId()));
        return "books/show";
    }

    @GetMapping("/{id}/edit")
    public String showBookEdit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.getBook(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@PathVariable("id") int id, @Valid @ModelAttribute("book") Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookDAO.updateBook(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookDAO.deleteBook(id);
        return "redirect:/books";
    }

    @PatchMapping("{id}/edit/setPerson")
    public String setPerson(@PathVariable("id") int id, Person person) {
        System.out.println(person.getFIO());
        bookDAO.takeBook(person.getPersonId(), id);
        return "redirect:/books/{id}";
    }
    @PatchMapping("{id}/edit/clearPerson")
    public String setPerson(@PathVariable("id") int id) {
        bookDAO.freeBook(id);
        return "redirect:/books/{id}";
    }
}
