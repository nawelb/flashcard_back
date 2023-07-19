package fr.nawelbp.flashcards.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table
@Entity
public class Flashcard {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	Long id;
	
	String question;
	
	String answer;

	
	@ManyToOne @JoinColumn(name="CATEGORY_ID")
	Category category; 
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@JsonIgnore
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	

	public Flashcard() {
		super();
	}

	public Flashcard(Long id, String question, String answer, Category category) {
		super();
		this.id = id;
		this.question = question;
		this.answer = answer;
		this.category = category;
	}

	@Override
	public String toString() {
		return "Flashcard : id=" + id + ", question=" + question + ", answer=" + answer + ", category=" + category;
	}

	 
	
	
	
	
}
