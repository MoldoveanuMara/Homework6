package com.db.bookstore.service;

import com.db.bookstore.model.Book;
import com.db.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public List<Book> getBooksList() { //pentru a afisa lista de carti
        return bookRepository.findAll();
    }

    public void insertBooks(Book newBook) { //functie pentru a adauga carti in lista
        bookRepository.save(newBook);
    }
}
