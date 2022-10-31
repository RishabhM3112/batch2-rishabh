package com.bookManagement.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreType
@Entity
@Table(name = "bookTable")
public class Book {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer bookId ;
   private String name ;

   @JsonIgnore
   @ManyToMany(mappedBy = "books")
   private Set<User> users = new HashSet<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUser() {
        return users;
    }

    public void setUser(Set<User> user) {
        this.users = user;
    }

    public Integer getId() {
        return bookId;
    }

    public Book() {
    }

    public Book(Integer bookId, String name) {
        this.bookId = bookId;
        this.name = name;
    }
    public Book(String name, Set<User> users) {
        this.name = name;
        this.users = users;
    }

}
