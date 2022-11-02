package com.bookManagement.controllers;
import com.bookManagement.CustomMapper.BookMapper;
import com.bookManagement.model.Book;
import com.bookManagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "app/book")
public class BookController {
    @Autowired
    BookService bs ;

    @PostMapping(value = "/create")
    BookMapper createBook(@RequestBody Book b){
        return bs.createBook(b);
    }

    @GetMapping(value = "/all")
    Iterable<BookMapper> allBooks(){
        return bs.allBooks() ;
    }

    @GetMapping(value = "/{id}")
    BookMapper oneBook(@PathVariable Integer id){
        return bs.oneBook(id);
    }

    @DeleteMapping(value = "/{id}")
    String deleteBook(@PathVariable Integer id){
        Boolean b = bs.deleteBook(id);
        if(b)
            return "Book Deleted Successfully";
        return "Book Is Under Use";
    }

    @PutMapping(value = "/{id}")
    BookMapper updateBook(@PathVariable Integer id , @RequestBody Book b){
        return bs.updateBook(id , b);
    }

}