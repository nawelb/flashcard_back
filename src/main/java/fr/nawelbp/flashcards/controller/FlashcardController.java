package fr.nawelbp.flashcards.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.nawelbp.flashcards.model.Category;
import fr.nawelbp.flashcards.model.Flashcard;
import fr.nawelbp.flashcards.repository.CategoryRepository;
import fr.nawelbp.flashcards.repository.FlashcardRepository;

@RestController
public class FlashcardController {
	
	@Autowired
	private FlashcardRepository flashcardRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@PostMapping(path="/newflashcard")
	public void createNewFlashcard(@RequestBody Flashcard newFlashcard) {
		flashcardRepository.save(newFlashcard);
	}
	
	@GetMapping(path="/flashcards")
	public List<Flashcard> getAllFlashcard() {
		return flashcardRepository.findAll();
	}
	
	@GetMapping(path="/flashcards/{id}")
	public Optional<Flashcard> getAllFlashcard(@PathVariable Long id) {
		return flashcardRepository.findById(id);
	}
	
	// a revoir -> supprime les categories, verifier method PUT category
	@PutMapping(path="/flashcards/{id}")
	public Flashcard updateFlashcard(@PathVariable Long id, @RequestBody Flashcard updatedFlashcard) {
		return flashcardRepository.save(updatedFlashcard);
	}

	@DeleteMapping(path="/flashcards/{id}")
	public void deleteFlashcardById(@PathVariable Long id) {
		flashcardRepository.deleteById(id);
	}
	
	@GetMapping(path="/flashcards/category/{categoryId}")
	public List<Flashcard> getFlashcardByCategory(@PathVariable Long categoryId){
		Category category= categoryRepository.findById(categoryId).get();
		return flashcardRepository.findByCategory(category);
	}
	
	@GetMapping(path="/flashcards/quiz")
	public List<Flashcard> getRandomAccessToFlashCardList(@RequestBody List<Flashcard> flashcarListToShuffle) {
		Collections.shuffle(flashcarListToShuffle);
		return flashcarListToShuffle; 
	}
}
