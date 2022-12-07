package com.bookManagement.service;

import com.bookManagement.model.Category;
import com.bookManagement.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Service
public class CategoryService {
    @Autowired
    CategoryRepo cr ;

    public Iterable<Category> getAll() {
        return cr.findAll();
    }

    public Iterable<Category> getAllPresent() {
        List<Category> lc = (List<Category>) getAll();

        if(Objects.nonNull(lc) && !lc.isEmpty()) {
            for (Category c : lc) {
                if (Objects.nonNull(c) && c.getBooks().isEmpty()) {
                    lc.remove(c);
                }
            }
        }
        return lc ;
    }

    public Iterable<Category> getAllNotPresent() {
        ArrayList<Category> lc = (ArrayList<Category>) cr.findAll();
        HashSet<Category> hs = new HashSet<>();
        for (Category c : lc){
            if(c.getBooks().isEmpty()){
                hs.add(c);
            }
        }
        return hs ;
    }

    public void deleteAll() {
        cr.deleteAll(getAllNotPresent());
    }
}
