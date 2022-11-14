package com.bookManagement.controllers;

import com.bookManagement.CustomMapper.UserMapper;
import com.bookManagement.model.Book;
import com.bookManagement.model.Category;
import com.bookManagement.model.User;
import com.bookManagement.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
    private MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper() ;
    ObjectWriter objectWriter = objectMapper.writer() ;

    @Mock
    UserService userService ;

    @InjectMocks
    UserController userController ;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void getAllUser() throws Exception{
        UserMapper u1 = new UserMapper(1,
                "Rishabh",
                new HashSet<String>() {
                    {
                        add("Harry Potter");
                        add("Jurassic Park");
                    }
                }
        );
        UserMapper u2 = new UserMapper(2,
                "Aditya",
                new HashSet<String>() {
                    {
                        add("B");
                        add("A");
                    }
                }
        );
        List<UserMapper> lum = new ArrayList<>(Arrays.asList(u1,u2));
        when(userService.allUsers()).thenReturn(lum);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/app/user/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(/*MockMvcResultMatchers.*/jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[1].books[1]" , is("B"))
        );
    }

    @Test
    public void oneUser() throws Exception{
        UserMapper u1 = new UserMapper(1,
                "Rishabh",
                new HashSet<String>() {
                    {
                        add("Harry Potter");
                        add("Jurassic Park");
                    }
                }
        );
        UserMapper u2 = new UserMapper(2,
                "Aditya",
                new HashSet<String>() {
                    {
                        add("B");
                        add("A");
                    }
                }
        );
        when(userService.oneUser(1)).thenReturn(u1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/app/user/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(/*MockMvcResultMatchers.*/jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.name" , is("Rishabh"))
                );
    }

    @Test
    public void createUser() throws Exception{

        User u = new User("Rishabh") ;

        UserMapper u3 = UserService.utility(u);
        u3.userId = 1 ;
        System.out.println(objectWriter.writeValueAsString(u));
        System.out.println(objectWriter.writeValueAsString(u3));

        User n = new User("Sourabh");

        Mockito.when(userService.createUser(Mockito.any(User.class))).thenReturn(u3);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/app/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content((objectWriter.writeValueAsString(u)))
                       )
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$",notNullValue()))
                        .andExpect(jsonPath("$.name" , is("Rishabh")));
    }

    @Test
    public void addBook() throws Exception{
        Book b = new Book( "Harry Potter" , new Category("Magic") );

        HashSet<String> books = new HashSet<String>(){
            {
                add("1 - Harry Potter  - Magic");
            }
        };
        UserMapper u3 = new UserMapper(1,"Rishabh", books);


        System.out.println(objectWriter.writeValueAsString(b));
        System.out.println(objectWriter.writeValueAsString(u3));


        when(userService.addBook(Mockito.eq(1),Mockito.any(Book.class))).thenReturn(u3);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/app/user/1/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content((objectWriter.writeValueAsString(b)))
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.name" , is("Rishabh")));
    }

    @Test
    public void putBook() throws Exception{
        Book b = new Book( "Harry Potter" , new Category("Magic") );


        HashSet<String> s = new HashSet<String>(){
            {
                add("1 - Harry Potter  - Magic");
            }
        };
        UserMapper u3 = new UserMapper(1,"Rishabh", s);


        System.out.println(objectWriter.writeValueAsString(b));
        System.out.println(objectWriter.writeValueAsString(u3));

        when(userService.putBook(1,1)).thenReturn(u3);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/app/user/1/book/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.name" , is("Rishabh")));
    }
    @Test
    public void removeBook() throws Exception{
        HashSet<String> s = new HashSet<String>();
        UserMapper u3 = new UserMapper(1,"Rishabh", s);

        System.out.println(objectWriter.writeValueAsString(u3));

        when(userService.deleteBook(1,1)).thenReturn(u3);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/app/user/1/book/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.name" , is("Rishabh")));
    }

}
