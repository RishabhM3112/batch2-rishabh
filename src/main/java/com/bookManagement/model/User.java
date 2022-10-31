package com.bookManagement.model;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "userTable")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId ;
    private String name ;

    @ManyToMany
    @JoinTable(
            name="user_book",
            joinColumns = @JoinColumn(name="user_id" ),
            inverseJoinColumns = @JoinColumn(name="book_id")
    )
    private Set<Book> books = new HashSet<>();

    public void addBook(Book b) {
        books.add(b) ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks() {
        for (Book b : this.books  ) {
            b.setUser(null);
        }
    }

    public User(Integer userId, String name) {
        this.userId = userId;
        this.name = name;
    }
    public User() {
    }


    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public Set<Book> removeBook(Integer bookId) {
        if(!this.books.isEmpty())
        for (Book b:
             this.books) {
            if(b.getId() == bookId){
                this.books.remove(b);
            }
        }
        return this.books;
    }
}
