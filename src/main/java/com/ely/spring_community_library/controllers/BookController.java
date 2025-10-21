package com.ely.spring_community_library.controllers;

import com.ely.spring_community_library.dtos.BookDtos.CreateBookDto;
import com.ely.spring_community_library.dtos.BookDtos.UpdateBookDto;
import com.ely.spring_community_library.entities.Book;
import com.ely.spring_community_library.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    private ResponseEntity<Book> createBook(@RequestBody @Valid CreateBookDto createBookDto) {

        return bookService.createBook(createBookDto);
    }

    @GetMapping
    private ResponseEntity<List<Book>> getAllBooks() {

        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    private ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {

        return bookService.getBookById(id);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Book> updateBookById(@PathVariable("id") Long id,
                                                @RequestBody @Valid UpdateBookDto updateBookDto) {

        return bookService.updateBookById(id, updateBookDto);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteBookById(@PathVariable("id") Long id) {

        return bookService.deleteBookById(id);
    }
}
