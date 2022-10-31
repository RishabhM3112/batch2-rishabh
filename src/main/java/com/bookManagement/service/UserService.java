package com.bookManagement.service;

import com.bookManagement.CustomMapper.UserMapper;
import com.bookManagement.model.Book;
import com.bookManagement.model.User;
import com.bookManagement.repo.BookRepo;
import com.bookManagement.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserRepo ur ;
    @Autowired
    BookRepo br ;


    public Iterable<User> allUsers(){
        return ur.findAll() ;
    }


    public UserMapper oneUser(Integer id){
        Optional<User> uo = ur.findById(id);
        User u = uo.get();
        UserMapper um = new UserMapper();
        um.userId = u.getUserId();
        um.name = u.getName() ;
        for (Book b: u.getBooks()) {
            um.books.add(b.getId() + " - " + b.getName());
        }
        return um;
    }

    public User createUser(User u){
        return ur.save(u);
    }


    public Book addBook(Integer userId, Book b){
        Optional<User> u = ur.findById(userId);
        br.save(b);
        if(u.isPresent()){
            User user = u.get();
            user.addBook(b);
            ur.save(user);
            return b;
        }
        return b ;
    }

    public UserMapper deleteBook(Integer userId, Integer bookId) {
        Optional<User> u = ur.findById(userId);
        if(u.isPresent()){
            User user = u.get();
            Set<Book> sb = user.removeBook(bookId);
            user.setBooks(sb);
            ur.save(user);
            UserMapper um = new UserMapper();
            um.userId = user.getUserId();
            um.name = user.getName() ;
            for (Book b: user.getBooks()) {
                um.books.add(b.getId() + " - " + b.getName());
            }
            return um;
        }
        return null ;
    }
}
