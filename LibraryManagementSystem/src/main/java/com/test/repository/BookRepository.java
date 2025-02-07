package com.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.test.entity.Book;

import jakarta.transaction.Transactional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
	@Transactional
	@Modifying
	@Query("delete from Book l where l.id =:id")
	void deletedataByLibraryId(Integer id);
	
	
}
