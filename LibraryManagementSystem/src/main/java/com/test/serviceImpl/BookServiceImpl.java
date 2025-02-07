package com.test.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dto.BookDTO;
import com.test.entity.Book;
import com.test.entity.Library;
import com.test.exception.ResourseNotFoundException;
import com.test.repository.BookRepository;
import com.test.service.BookService;
import com.test.service.LibraryService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private LibraryService libraryService;
	@Override
	public BookDTO saveBooks(BookDTO bookDTO) {
		Library library= libraryService.findLibraryById(bookDTO.getLibrary_id());
		if(library.getLibrary_id()!=null)
		{
		Book save = bookRepository.save(mapDtoToEntity(bookDTO));
		return mapEntityToDto(save);
		}
		else {
		    throw  new ResourseNotFoundException("saveBooks", "findLibraryById", library.getLibrary_id());
		    
		}
	}

	public Book mapDtoToEntity(BookDTO bookDTO) {
		Library library= libraryService.findLibraryById(bookDTO.getLibrary_id());
		
		
		Book book = new Book();
		book.setIsbn(bookDTO.getIsbn());
		book.setTitle(bookDTO.getTitle());
		book.setId(bookDTO.getId());
		book.setLib(library);

//		Library library=libr
//		
//		book.setLibrary(null);

		return book;

	}

	public BookDTO mapEntityToDto(Book book) {
		Library library= libraryService.findLibraryById(book.getLib().getLibrary_id());
		BookDTO bookDTO = new BookDTO();
		bookDTO.setIsbn(book.getIsbn());
		bookDTO.setTitle(book.getTitle());
		bookDTO.setId(book.getId());
		bookDTO.setLibrary_id(library.getLibrary_id());

		return bookDTO;

	}

	@Override
	public List<BookDTO> getAllBooks() {
		List<Book> allBooks = bookRepository.findAll();
		return allBooks.stream().map(convert -> mapEntityToDto(convert)).toList();
	}

	@Override
	public BookDTO getBooksById(Integer bookid) {
		Optional<Book> byId = bookRepository.findById(bookid);
		// Book book=byId.orElseThrow(()-> new ResourceNot)
		Book book = byId.orElseThrow(() -> new ResourseNotFoundException("getBooksById", "findById", bookid));

		return mapEntityToDto(book);
	}

	@Override
	public BookDTO updateBook(BookDTO bookDTO) {

		Optional<Book> book = bookRepository.findById(bookDTO.getId());

		Book bookdata = book
				.orElseThrow(() -> new ResourseNotFoundException("updateBook", "findById", bookDTO.getId()));

		if (bookdata != null) {
			Book save = bookRepository.save(mapDtoToEntity(bookDTO));
			return mapEntityToDto(save);
		} else {
			return null;
		}
	}

	@Override
	public String deleteBookById(Integer bookid) {

		Optional<Book> book = bookRepository.findById(bookid);

		Book bookdata = book.orElseThrow(() -> new ResourseNotFoundException("deleteBookById", "findById", bookid));

		if (bookdata != null) {
			bookRepository.deletedataByLibraryId(bookdata.getId());
			return "Successfull";
		} else {
			return "Unsuccessfull";
		}
	}
}
