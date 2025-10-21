package com.ely.spring_community_library.services;

import com.ely.spring_community_library.dtos.BookDtos.CreateBookDto;
import com.ely.spring_community_library.dtos.BookDtos.UpdateBookDto;
import com.ely.spring_community_library.entities.Book;
import com.ely.spring_community_library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("all")
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public ResponseEntity<Book> createBook(CreateBookDto createBookDto) {


        final Book book = new Book(
                null,
                createBookDto.title(),
                createBookDto.author(),
                createBookDto.isbn(),
                createBookDto.availableCopies(),
                createBookDto.totalCopies()
        );

        return ResponseEntity.ok(bookRepository.save(book));
    }

    public ResponseEntity<List<Book>> getAllBooks() {

        return ResponseEntity.ok(bookRepository.findAll());
    }

    public ResponseEntity<Book> getBookById(Long id) {

        final Optional<Book> book = bookRepository.findById(id);

        if(book.isPresent()) {

            return ResponseEntity.ok(book.get());
        } else {

            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Book> updateBookById(Long id, UpdateBookDto updateBookDto) {

        final Optional<Book> oldBook = bookRepository.findById(id);

        if(oldBook.isPresent()) {

            final Book updatedBook = oldBook.get();

            if(updateBookDto.title() != null) {

                updatedBook.setTitle(updateBookDto.title());
            }

            if(updateBookDto.author() != null) {

                updatedBook.setAuthor(updateBookDto.author());
            }

            if(updateBookDto.isbn() != null) {

                updatedBook.setIsbn(updateBookDto.isbn());
            }

            if(updateBookDto.availableCopies() != null) {

                updatedBook.setAvailableCopies(updateBookDto.availableCopies());
            }

            if(updateBookDto.totalCopies() != null) {

                updatedBook.setTotalCopies(updateBookDto.totalCopies());
            }

            return ResponseEntity.ok(bookRepository.save(updatedBook));
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> deleteBookById(Long id) {

        final Optional<Book> bookToDelete = bookRepository.findById(id);

        if(bookToDelete.isPresent()) {

            bookRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {

            return ResponseEntity.notFound().build();
        }
    }
}
