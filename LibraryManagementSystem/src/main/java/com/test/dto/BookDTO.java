package com.test.dto;



import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookDTO {

	@NotEmpty
	@Size(min = 2, message = "Title should be atleast 2 characters minimum")
	private String title;

	@NotEmpty
	@Size(min = 2, message = "ISBN should be atleast 2 characters minimum")
	private String isbn;
	private Integer id;
	
	private Integer library_id;
}