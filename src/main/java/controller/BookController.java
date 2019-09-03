package controller;

import components.Book;
import components.BookDAOImpl;
import components.ConnectionToDB;
import dao.DAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("books")
public class BookController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private DAO<Book> bookDao = new BookDAOImpl(new ConnectionToDB());

    public BookController() {
        logger.info("Main controller created...");
    }

    @ResponseBody
    @RequestMapping()
    public String getAll() {

        logger.info("Method getAll()");

        List<Book> list = bookDao.getAll();
        if (list == null) return "DB is empty!";

        StringBuilder sb = new StringBuilder();
        for (Book book : list)
            sb.append(book.toString());

        return sb.toString();
    }

    @ResponseBody
    @RequestMapping("/{id}")
    public String find(@PathVariable("id") long id) {

        logger.info("Method find()");

        Book book = bookDao.findById(id);

        if (book != null) return book.toString();
        return "Book is not found!";
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public String save(String name) {

        logger.info("Method save()");

        Book book = new Book();
        book.setName(name);
        bookDao.save(book);

        return "Book saved!";
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String update(String name, @PathVariable("id") long id) {

        logger.info("Method update()");

        Book book = new Book(id, name);
        bookDao.update(book);

        return "Book updated!";
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") long id) {

        logger.info("Method delete()");

        Book book = new Book();
        book.setId(id);

        if (bookDao.delete(book) > 0) return "Book deleted!";
        return "Book is not found!";
    }
}