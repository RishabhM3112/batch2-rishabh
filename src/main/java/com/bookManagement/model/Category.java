package com.bookManagement.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId ;
    private String name ;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    Set<Book> books = new HashSet<>();

    public String getName() {
        return name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }


    public Category(String name) {
        this.name = name;
    }
    public Category() {
    }

}
