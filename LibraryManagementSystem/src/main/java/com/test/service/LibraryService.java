package com.test.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.test.dto.LibraryDTO;
import com.test.entity.Library;

public interface LibraryService {

	LibraryDTO saveLibrary(LibraryDTO libraryDTO);

	Library findLibraryById(Integer library_id);

	List<LibraryDTO> getAllLibraries(PageRequest pageRequest);

	LibraryDTO getLibraryById(Integer libraryid);

	LibraryDTO updateLibrary(LibraryDTO libraryDTO);

	String deleteLibraryById(Integer libraryid);

}
