package fr.nawelbp.flashcards.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Table
@Entity
public class Category {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	Long id; 
	
	String name; 
	
	@OneToMany @JoinColumn(name= "CATEGORY_ID")
	List <Flashcard> flashcards;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Flashcard> getFlashcards() {
		return flashcards;
	}

	public void setFlashcards(List<Flashcard> flashcards) {
		this.flashcards = flashcards;
	}

	@Override
	public String toString() {
		return "Category  :  id=" + id + ", name=" + name + ", flashcards=" + flashcards ;
	}
	
	
}
