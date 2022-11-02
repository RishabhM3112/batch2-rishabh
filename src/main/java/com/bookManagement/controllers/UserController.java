package com.bookManagement.controllers;
import com.bookManagement.CustomMapper.UserMapper;
import com.bookManagement.model.Book;
import com.bookManagement.model.User;
import com.bookManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "app/user")
public class UserController {
    @Autowired
    UserService us ;

    @GetMapping(value = "/all")
    Iterable<UserMapper> allUsers(){
        return us.allUsers();
    }

    @GetMapping(value= "/{id}")
    UserMapper oneUser(@PathVariable Integer id){
        return us.oneUser(id);
    }

    @PostMapping(value = "/create")
    UserMapper createUser(@RequestBody User u){
        return us.createUser(u);
    }

    @PostMapping(value = "{userId}/book")
    UserMapper addBook(@PathVariable Integer userId ,@RequestBody Book b ){
        return us.addBook(userId,b);
    }

    @GetMapping(value = "{userId}/book/{bookId}")
    UserMapper putBook(@PathVariable Integer userId ,@PathVariable Integer bookId ){
        UserMapper u = us.putBook(userId,bookId);
        return u;
    }

    @DeleteMapping(value = "{userId}/book/{bookId}")
    UserMapper deleteBook(@PathVariable Integer userId ,@PathVariable Integer bookId ){
        return us.deleteBook(userId,bookId);
    }


}
