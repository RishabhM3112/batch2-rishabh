package com.bookManagement.service;

import com.bookManagement.CustomMapper.BookMapper;
import com.bookManagement.model.Book;
import com.bookManagement.model.User;
import com.bookManagement.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.Set;

@Service
public class BookService {

    @Autowired
    BookRepo br ;

    public Iterable<BookMapper> allBooks(){
        Set<BookMapper> sbm = new HashSet<>();
        for (Book b:
        br.findAll()) {
            sbm.add(utility(b));
        }
        return  sbm;
    }

    public BookMapper oneBook(@PathVariable Integer id){
        return utility(br.findById(id).orElse(new Book()));
    }

    public Book createBook(@RequestBody Book b){
        return br.save(b);
    }


    public BookMapper utility (Book b){
        BookMapper bm = new BookMapper() ;
        bm.bookId = b.getId();
        bm.name = b.getName();
        for (User u:
                b.getUser()) {
            bm.users.add(u.getName());
        }
        return bm ;
    }

    public Boolean deleteBook(Integer id) {
        Book b = br.findById(id).orElse(new Book());
        if (b.getUser().isEmpty()){
            br.deleteById(id);
            return true ;
        }
        return false ;
    }
}
