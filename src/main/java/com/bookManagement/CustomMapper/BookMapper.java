package com.bookManagement.CustomMapper;


import java.util.HashSet;
import java.util.Set;

public class BookMapper {
    public Integer bookId ;
    public String name ;
    public Set<String> users = new HashSet<>();
}
