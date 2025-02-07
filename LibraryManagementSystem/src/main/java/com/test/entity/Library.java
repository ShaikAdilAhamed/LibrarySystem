package com.test.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity 
@Data 

public class Library{ 
 @Id 
 @GeneratedValue(strategy = GenerationType.IDENTITY) 
 private Integer library_id; 
 private String name; 

 @OneToMany(mappedBy = "lib",fetch = FetchType.EAGER, cascade = CascadeType.ALL) 
 private List<Book> books=new ArrayList<>(); 
} 
