package com.bookManagement.controllers;
import com.bookManagement.model.Category;
import com.bookManagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "app/category")
public class CategoryController {
    @Autowired
    CategoryService cs ;

    @GetMapping(value = "/all")
    public Iterable<Category> getAll(){
        return cs.getAll();
    }

    @GetMapping(value = "/all/present")
    public Iterable<Category> getAllPresent(){
        return cs.getAllPresent();
    }

    @GetMapping(value = "/all/notp")
    public Iterable<Category> getAllNotPresent(){
        return cs.getAllNotPresent();
    }

    @DeleteMapping(value = "/delete/allnp")
    public String deleteAll(){
        cs.deleteAll();
        return "all category with no books deleted";
    }
}