package com.test.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import com.test.dto.BookDTO;
import com.test.dto.LibraryDTO;
import com.test.entity.Book;
import com.test.entity.Library;
import com.test.exception.ResourseNotFoundException;
import com.test.repository.BookRepository;
import com.test.repository.LibraryRepository;
import com.test.service.BookService;
import com.test.service.LibraryService;

@Service
public class LibraryServiceImpl implements LibraryService {

	@Autowired
	private LibraryRepository libraryRepository;

	@Autowired
	private BookServiceImpl bookServiceImpl;

	@Override
	public LibraryDTO saveLibrary(LibraryDTO libraryDTO) {
		Library library = libraryRepository.save(mapDtoToEntity(libraryDTO));
		return mapEntityToDto(library);
	}

	public Library mapDtoToEntity(LibraryDTO libraryDTO) {
		Library library = new Library();
		// library.setLibrary_id(libraryDTO.);
		library.setName(libraryDTO.getName());
		library.setLibrary_id(libraryDTO.getLibrary_id());

		return library;

	}

	public LibraryDTO mapEntityToDto(Library library) {

		LibraryDTO libraryDTO = new LibraryDTO();
		libraryDTO.setLibrary_id(library.getLibrary_id());
		libraryDTO.setName(library.getName());
		List<Book> list = library.getBooks();

		List<BookDTO> BooksDTO = list.stream().map(comment -> bookServiceImpl.mapEntityToDto(comment)).toList();

		libraryDTO.setBooks(BooksDTO);

		return libraryDTO;

	}

	@Override
	public Library findLibraryById(Integer library_id) {
		Optional<Library> byId = libraryRepository.findById(library_id);
		Library library = byId
				.orElseThrow(() -> new ResourseNotFoundException("findLibraryById", "findById", library_id));
		return library;
	}

	@Override
	public List<LibraryDTO> getAllLibraries(PageRequest pageRequest) {

		Page<Library> pagination = libraryRepository.findAll(pageRequest);
		List<Library> allLibraries = pagination.getContent();

		return allLibraries.stream().map(convert -> mapEntityToDto(convert)).toList();
	}

	@Override
	public LibraryDTO getLibraryById(Integer libraryid) {
		Optional<Library> byId = libraryRepository.findById(libraryid);
		Library library = byId
				.orElseThrow(() -> new ResourseNotFoundException("getLibraryById", "findById", libraryid));
		return mapEntityToDto(library);
	}

	@Override
	public LibraryDTO updateLibrary(LibraryDTO libraryDTO) {
		Optional<Library> byId = libraryRepository.findById(libraryDTO.getLibrary_id());
		Library library = byId.orElseThrow(
				() -> new ResourseNotFoundException("updateLibrary", "findById", libraryDTO.getLibrary_id()));
		if (library != null) {
			Library save = libraryRepository.save(mapDtoToEntity(libraryDTO));
			return mapEntityToDto(save);
		} else {
			return null;
		}
	}

	@Override
	public String deleteLibraryById(Integer libraryId) {
		Optional<Library> byId = libraryRepository.findById(libraryId);
		Library library = byId
				.orElseThrow(() -> new ResourseNotFoundException("getLibraryById", "findById", libraryId));

		if (library != null) {
			libraryRepository.delete(library);
			return "Successfull";
		} else {
			return "Unsuccessfull";
		}

	}

}
