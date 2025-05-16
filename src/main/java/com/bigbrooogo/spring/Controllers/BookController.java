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
    public String getIndexPage(Model model,
                           @RequestParam(name = "sort_by_year", required = false) Boolean sort_by_year,
                           @RequestParam(name = "page", required = false) Integer page,
                           @RequestParam(name = "books_per_page", required = false) Integer books_per_page) {
        if (page != null & books_per_page != null) {
            if (page < 0) page = 0;
            if (books_per_page < 0) books_per_page = 0;
            if (sort_by_year == null) sort_by_year = false;
            model.addAttribute("books", bookService.getBooks(page, books_per_page, sort_by_year));
        }
        else if (sort_by_year != null)
            model.addAttribute("books", bookService.getBooks(sort_by_year));
        else model.addAttribute("books", bookService.getBooks());
        return "books/index";
    }

    @GetMapping("/new")
    public String getNewBookPage(Model model) {
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
    public String getShowPage(@PathVariable("id") int id, @ModelAttribute("person") Person person,
                           Model bookModel, Model personModel, Model personBook) {
        Book book = bookService.getBook(id);
        bookModel.addAttribute("book", book);
        personModel.addAttribute("people", personService.getPeople());
        personBook.addAttribute("personBook", book.getOwner());
        return "books/show";
    }

    @GetMapping("/{id}/edit")
    public String getEditPage(@PathVariable("id") int id, Model model) {
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
        return "redirect:/books";
    }
    @PatchMapping("{id}/edit/clearPerson")
    public String setPerson(@PathVariable("id") int id) {
        bookService.freeBook(id);
        return "redirect:/books";
    }
    @GetMapping("/search")
    public String getSearchPage(@RequestParam(value = "title", required = false) String title, Model model) {
        if (title == null) return "books/search";
        else {
            model.addAttribute("books", bookService.findByTitleStartingWith(title));
            return "books/searchResult";
        }
    }

}
