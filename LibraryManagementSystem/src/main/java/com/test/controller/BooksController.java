package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.dto.BookDTO;
import com.test.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/library/api/")
public class BooksController {

	@Autowired
	private BookService bookService;
	
	@PostMapping("/save/books")
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<BookDTO> saveBooks(@Valid @RequestBody BookDTO bookDTO)
	{
		try {
		BookDTO savebooks=bookService.saveBooks(bookDTO);
		return new ResponseEntity(savebooks,HttpStatus.OK);
		}
	  catch (Exception e) {
	        // Handle the exception and return an error response
	        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // Or you can include the exception message in the response body
	    }
	}
	
	@GetMapping("/getall/books")
	public ResponseEntity<BookDTO> getBooks()
	{
		List<BookDTO> getBooks=bookService.getAllBooks();
		return new ResponseEntity(getBooks,HttpStatus.OK);
	}
	@GetMapping("/getbooks/{bookid}")
	public ResponseEntity<BookDTO> getBooksById(@PathVariable("bookid") Integer bookid)
	{
	  BookDTO getBooksById=bookService.getBooksById(bookid);
		return new ResponseEntity(getBooksById,HttpStatus.OK);
	}
	
	@PutMapping("/update/books")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<BookDTO>updateBook(@RequestBody BookDTO bookDTO)
	{
		BookDTO updatebook= bookService.updateBook(bookDTO);
		
		return new ResponseEntity(updatebook,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{bookid}/book")
	 @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<BookDTO>deleteBook(@PathVariable("bookid") Integer bookid )
	{
		
		String bookStatus=bookService.deleteBookById(bookid);
		return new ResponseEntity(bookStatus,HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * @GetMapping("/customer/getall")
	public ResponseEntity<String> getAllCustomer(
			@RequestParam(name = "pageNo", defaultValue = "0", required = false) Integer pageNo,
			@RequestParam(name = "pageSize", defaultValue = "2", required = false) Integer pageSize,
			@RequestParam(name = "sortBy", defaultValue = "customerId", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir)
	{
 
		Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		PageRequest pageRequest = PageRequest.of(pageNo, pageSize,sort);
 
		List<CustomerDTO> customer = customerService.getAllCustomer(pageRequest);
 
		return new ResponseEntity(customer, HttpStatus.CREATED);
 
	}
	 */
}
