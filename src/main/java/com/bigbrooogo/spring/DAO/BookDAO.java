package com.bigbrooogo.spring.DAO;

import com.bigbrooogo.spring.Model.Book;
import com.bigbrooogo.spring.Model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooks() {
        return jdbcTemplate.query("select * from books", new BeanPropertyRowMapper<>(Book.class));
    }

    public Optional<Book> getBook(int id) {
        return jdbcTemplate.query("select * from books where id = ?", new BeanPropertyRowMapper<>(Book.class), Integer.valueOf(id))
                .stream()
                .findAny();
    }

    public void saveBook(Book book) {
        jdbcTemplate.update("insert into books (title, author, year) values(?, ?, ?)", book.getTitle(), book.getAuthor(), book.getYear());
    }

    public void updateBook(int id, Book book) {
        jdbcTemplate.update("update books set title = ?, author = ?, year = ? where id = ? ",
                book.getTitle(), book.getAuthor(), book.getYear(), id);
    }

    public void deleteBook(int id) {
        jdbcTemplate.update("delete from books where id = ?", id);
    }

    public void takeBook(int personId, int bookId) {
        jdbcTemplate.update("update books set user_id = ? where id = ?", personId, bookId);
    }

    public void freeBook(int bookId) {
        jdbcTemplate.update("update books set user_id = null where id = ?", bookId);
    }


}
