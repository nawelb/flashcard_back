package fr.nawelbp.flashcards.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
@Table
@Entity
public class Category {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	Long categoryId; 
	
	String name; 


	public Category(String name) {
		this.name = name;	}

	public Category() {	}
	public Category(Long categoryId, String name) {
		super();
		this.categoryId = categoryId;
		this.name = name;
	}

	public Long getId() {
		return categoryId;
	}

	public void setId(Long id) {
		this.categoryId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "Category  :  id=" + categoryId + ", name=" + name ;
	}
	
	
}
