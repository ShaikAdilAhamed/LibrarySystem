package com.test.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.test.dto.BookDTO;

public interface BookService {

	BookDTO saveBooks(BookDTO bookDTO);

	List<BookDTO> getAllBooks();

	BookDTO getBooksById(Integer bookid);

	BookDTO updateBook(BookDTO bookDTO);

	String deleteBookById(Integer bookid);

}
