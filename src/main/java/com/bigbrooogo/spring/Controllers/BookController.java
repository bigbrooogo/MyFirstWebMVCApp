package com.bigbrooogo.spring.Controllers;

import com.bigbrooogo.spring.Models.Book;
import com.bigbrooogo.spring.Models.Person;
import com.bigbrooogo.spring.Services.BookService;
import com.bigbrooogo.spring.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final PersonService personService;

    @Autowired
    public BookController(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }

    @GetMapping
    public String getBooks(Model model) {
        model.addAttribute("books", bookService.getBooks());
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
        bookService.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, @ModelAttribute("person") Person person,
                           Model bookModel, Model personModel, Model personBook) {
        Book book = bookService.getBook(id);
        bookModel.addAttribute("book", book);
        personModel.addAttribute("people", personService.getPeople());
        personBook.addAttribute("personBook", book.getOwner());
        return "books/show";
    }

    @GetMapping("/{id}/edit")
    public String showBookEdit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.getBook(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@PathVariable("id") int id, @Valid @ModelAttribute("book") Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookService.updateBook(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @PatchMapping("{id}/edit/setPerson")
    public String setPerson(@PathVariable("id") int id, Person person) {
        bookService.takeBook(person, id);
        return "redirect:/books/{id}";
    }
    @PatchMapping("{id}/edit/clearPerson")
    public String setPerson(@PathVariable("id") int id) {
        bookService.freeBook(id);
        return "redirect:/books/{id}";
    }
}
