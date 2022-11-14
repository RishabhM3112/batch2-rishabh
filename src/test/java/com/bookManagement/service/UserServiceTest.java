package com.bookManagement.service;

import com.bookManagement.model.Book;
import com.bookManagement.model.Category;
import com.bookManagement.model.User;
import com.bookManagement.repo.BookRepo;
import com.bookManagement.repo.UserRepo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    @Mock
    private UserRepo ur;
    @Mock
    private BookRepo br;
    @InjectMocks
    private UserService us;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void allUsers(){
        User user1 = new User(1 ,"Rishabh") ;
        User user2 = new User(2 ,"Sourabh") ;
        List<User> uml = Arrays.asList(user1,user2);

        when(ur.findAll()).thenReturn(uml);

        assertEquals(2 , us.allUsers().spliterator().estimateSize()) ;
        assertEquals("com.bookManagement.CustomMapper.UserMapper" , us.allUsers().iterator().next().getClass().getName());
    }

    @Test
    public void oneUser(){
        User user1 = new User(1 ,"Rishabh") ;
        user1.addBook(new Book( 1 , "Harry Potter" , new Category("Magic") ));
        when(ur.findById(1)).thenReturn(Optional.of(user1));

        assertEquals("Rishabh" , us.oneUser(1).name) ;
        assertEquals(1 , us.oneUser(1).userId);
        assertEquals("1 - Harry Potter  - Magic" , us.oneUser(1).books.iterator().next());

    }

    @Test
    public void createUser(){

        User user1 = new User(1 ,"Rishabh") ;
        when(ur.save(user1)).thenReturn(user1);

        assertEquals(1 , us.createUser(user1).userId) ;
        assertEquals("Rishabh" , us.createUser(user1).name);
    }

    @Test
    public void addBook(){

        User user1 = new User(1 ,"Rishabh") ;
        Book b = new Book( 1 , "Harry Potter" , new Category("Magic") );

        when(ur.findById(1)).thenReturn(Optional.of(user1));
        when(br.save(b)).thenReturn(b);
        when(ur.save(user1)).thenReturn(user1);

        assertEquals(1 , us.addBook(1 , b).userId) ;
        assertEquals("Rishabh" , us.addBook(1 , b).name);
        assertEquals("1 - Harry Potter  - Magic" , us.addBook(1 , b).books.iterator().next());
    }

    @Test
    public void putBook(){

        User user1 = new User(1 ,"Rishabh") ;
        Book b = new Book( 1 , "Harry Potter" , new Category("Magic") );

        when(ur.findById(1)).thenReturn(Optional.of(user1));
        when(br.findById(1)).thenReturn(Optional.of(b));
        when(ur.save(user1)).thenReturn(user1);

        assertEquals(1 , us.putBook(1 , 1).userId) ;
        assertEquals("Rishabh" , us.putBook(1 , 1).name);
        assertEquals("1 - Harry Potter  - Magic" , us.putBook(1 , 1).books.iterator().next());
    }

    @Test
    public void deleteBook(){

        User user1 = new User(1 ,"Rishabh") ;
        Book b = new Book( 1 , "Harry Potter" , new Category("Magic") );
        user1.addBook(b);
        when(ur.findById(1)).thenReturn(Optional.of(user1));
        when(ur.save(user1)).thenReturn(user1);

        assertEquals(1 , us.deleteBook(1 , 1).userId) ;
        assertEquals("Rishabh" , us.deleteBook(1 , 1).name);
        assertTrue( us.deleteBook(1 , 1).books.isEmpty());
    }

    
}