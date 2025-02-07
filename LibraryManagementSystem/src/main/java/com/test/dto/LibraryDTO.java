package com.test.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data 
public class LibraryDTO { 
	@NotEmpty
	@Size(min = 2,message = "Name should be atleast 2 characters minimum")
    private String name; 
    private Integer library_id; 
    private List<BookDTO> books; 
} 