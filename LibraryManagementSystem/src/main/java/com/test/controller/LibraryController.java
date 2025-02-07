package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.dto.BookDTO;
import com.test.dto.LibraryDTO;
import com.test.service.LibraryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/library/api/")
public class LibraryController {
	
	@Autowired
	private LibraryService libraryService;
	@PostMapping("/save/library")
	 @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<LibraryDTO> saveLibrary(@Valid  @RequestBody LibraryDTO libraryDTO)
	{
		LibraryDTO savebooks=libraryService.saveLibrary(libraryDTO);
		return new ResponseEntity(savebooks,HttpStatus.OK);
	}
	
	@GetMapping("/getall/libraries")
	public ResponseEntity<LibraryDTO> getLibraries(
			@RequestParam(name = "pageNo", defaultValue = "0", required = false) Integer pageNo,
			@RequestParam(name = "pageSize", defaultValue = "2", required = false) Integer pageSize,
			@RequestParam(name = "sortBy", defaultValue = "customerId", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir)
	{
		Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		PageRequest pageRequest = PageRequest.of(pageNo, pageSize,sort);
		List<LibraryDTO> getLibraries=libraryService.getAllLibraries(pageRequest);
		return new ResponseEntity(getLibraries,HttpStatus.OK);
	}
	@GetMapping("/getlibrary/{libraryid}")
	public ResponseEntity<LibraryDTO> getLibrariesById(@PathVariable("libraryid") Integer libraryid)
	{
		LibraryDTO getLibrariesById=libraryService.getLibraryById(libraryid);
		return new ResponseEntity(getLibrariesById,HttpStatus.OK);
	}
	
	@PutMapping("/update/library")
	 @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<LibraryDTO>updateLibrary(@RequestBody LibraryDTO libraryDTO)
	{
		LibraryDTO updateLibrary=libraryService.updateLibrary(libraryDTO);
		
		return new ResponseEntity(updateLibrary,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{libraryid}/library")
	 @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<LibraryDTO>deleteLibrary(@PathVariable("libraryid") Integer libraryid )
	{
		
		String library=libraryService.deleteLibraryById(libraryid);
		return new ResponseEntity(library,HttpStatus.OK);
	}
}
