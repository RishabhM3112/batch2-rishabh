package com.bookManagement.service;
import com.bookManagement.CustomMapper.UserMapper;
import com.bookManagement.model.Book;
import com.bookManagement.model.User;
import com.bookManagement.repo.BookRepo;
import com.bookManagement.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserRepo ur ;
    @Autowired
    BookRepo br ;

    public Iterable<UserMapper> allUsers(){
        List<User> lu = ur.findAll() ;
        Set<UserMapper> sum = new HashSet<>();
        if(!lu.isEmpty())
            for (User u:
                    lu) {
                sum.add(utility(u));
            }

        return sum ;
    }

    public UserMapper oneUser(Integer id){
       User uo = ur.findById(id).orElse(new User());
        //User uo = ur.findById(id).get();
        return utility(uo);
    }

    public UserMapper createUser(User u){
        return utility(ur.save(u));
    }

    public UserMapper addBook(Integer userId, Book b){
        User user = ur.findById(userId).orElse(null);
        br.save(b);
        user.addBook(b);
        ur.save(user);
        return utility(user);
    }

    public UserMapper putBook(Integer userId, Integer bookId) {
        User user = ur.findById(userId).orElse(null);
        Book b = br.findById(bookId).orElse(null);
        user.addBook(b);
        ur.save(user);
        return utility(user);
    }

    public UserMapper deleteBook(Integer userId, Integer bookId) {
        User user = ur.findById(userId).orElse(null);
        user.removeBook(bookId);
        ur.save(user);
        return utility(user);
    }

    public static UserMapper utility(User user){
        UserMapper um = new UserMapper();
        if(user != null) {
            um.userId = user.getUserId();
            um.name = user.getName();
            for (Book b : user.getBooks()) {
                um.books.add(b.getId() + " - " + b.getName() + "  - " + b.getCategory().getName());
            }
        }
        return um ;
    }
}
