package com.bookManagement.service;
import com.bookManagement.CustomMapper.BookMapper;
import com.bookManagement.model.Book;
import com.bookManagement.model.User;
import com.bookManagement.repo.BookRepo;
import com.bookManagement.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.HashSet;
import java.util.Set;

@Service
public class BookService {

    @Autowired
    BookRepo br ;

    @Autowired
    CategoryRepo cr;

    public BookMapper createBook(Book b){
        return utility(br.save(b));
    }

    public Iterable<BookMapper> allBooks(){
        Set<BookMapper> sbm = new HashSet<>();

        for (Book b: br.findAll() ) {
            sbm.add(utility(b));
        }

        return  sbm;
    }

    public BookMapper oneBook(@PathVariable Integer id){
        return utility(br.findById(id).orElse(new Book()));
    }

    public Boolean deleteBook(Integer id) {
        Book b = br.findById(id).orElse(new Book());

        if (b.getUser().isEmpty()){
            br.deleteById(id);
            return true ;
        }

        return false ;
    }

    public BookMapper updateBook(Integer id , Book b){
        Book book = br.findById(id).orElse(new Book());
        if(b.getName() != null) {
            book.setName(b.getName());
        }
        if(b.getCategory() != null) {
            book.setCategory(b.getCategory());
        }
        br.save(book);
        return utility(book);
    }

    public BookMapper utility (Book b){
        BookMapper bm = new BookMapper() ;
        bm.bookId = b.getId();
        bm.name = b.getName();

        if (b.getCategory()!=null)
            bm.category = b.getCategory();


        for (User u: b.getUser()) {
            bm.users.add(u.getUserId() + " - " +u.getName());
        }

        return bm ;
    }

}
