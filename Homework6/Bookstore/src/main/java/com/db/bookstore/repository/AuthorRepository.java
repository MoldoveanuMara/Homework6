package com.db.bookstore.repository;


import com.db.bookstore.model.Author;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface AuthorRepository {

    public Author findAuthorByName(String name);

    //Pentru a selecta numele unui autor
    @Query(value="SELECT name FROM Author")
    Set<String> getAuthorsNames();

}
