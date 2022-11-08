package com.bookManagement.CustomMapper;
import java.util.HashSet;
import java.util.Set;

public class UserMapper {
    public Integer userId;
    public String name;
    public Set<String> books = new HashSet<>();

    public UserMapper() {
    }

    public UserMapper(Integer userId, String name, Set<String> books) {
        this.userId = userId;
        this.name = name;
        this.books = books;
    }
}
