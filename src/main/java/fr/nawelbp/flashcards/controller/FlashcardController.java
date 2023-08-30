package fr.nawelbp.flashcards.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.nawelbp.flashcards.model.Category;
import fr.nawelbp.flashcards.model.Flashcard;
import fr.nawelbp.flashcards.repository.CategoryRepository;
import fr.nawelbp.flashcards.repository.FlashcardRepository;

@CrossOrigin("*")
@RestController
public class FlashcardController {
	
	@Autowired
	private FlashcardRepository flashcardRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	CategoryController categoryController;
	
	@RequestMapping(value="/newflashcard", method=RequestMethod.POST)
	public void createNewFlashcard(@RequestBody Flashcard newFlashcard) {
		Flashcard card= new Flashcard();
		card.setQuestion(newFlashcard.getQuestion());
		card.setAnswer(newFlashcard.getAnswer());
		
		categoryController.createNewCategory(newFlashcard.getCategory());
		Category name = categoryController.getCategoryByName(newFlashcard.getCategory().getName()).get();
		System.out.println("GET CATEGORY BY NAME : "+ name);
		card.setCategory(name);
		System.out.println("GET FLASHCARD DETAILS : "+ card.toString());
		flashcardRepository.save(card);

	}
	
	@GetMapping(path="/flashcards")
	public List<Flashcard> getAllFlashcard() {
		return flashcardRepository.findAll();
	}
	
	@GetMapping(path="/flashcards/{id}")
	public Optional<Flashcard> getAllFlashcard(@PathVariable Long id) {
		return flashcardRepository.findById(id);
	}
	@GetMapping(path="/flashcards/category/{naame}")
	public List<Flashcard> getAllFlashcradByCategory(@PathVariable String naame){
		System.out.println("NAME : "+naame);
		Optional<Category> cat =categoryController.getCategoryByName(naame); 
		Category category= cat.get();
		return flashcardRepository.findAllByCategory(category);
	}
	@PutMapping(path="/flashcards/{id}")
	public Flashcard updateFlashcard(@PathVariable Long id, @RequestBody Flashcard updatedFlashcard) {
		return flashcardRepository.save(updatedFlashcard);
	}

	@DeleteMapping(path="/flashcards/{id}")
	public void deleteFlashcardById(@PathVariable Long id) {
		Flashcard card= getAllFlashcard(id).get();
		Category category= card.getCategory();
		flashcardRepository.deleteById(id);
		List <Flashcard>cards=getAllFlashcradByCategory(category.getName());	
		System.out.println("DELETE CATEGORY IS EMPTY : "+cards.toString());
		if(cards.isEmpty()) {
			categoryController.deleteCategoryById(category.getId());
		}
		
	}
	
//	@GetMapping(path="/flashcards/category/{categoryId}")
//	public List<Flashcard> getFlashcardByCategory(@PathVariable Long categoryId){
//		Category category= categoryRepository.findById(categoryId).get();
//		return flashcardRepository.findAllByCategory(category.getName());
//	}
	
	@GetMapping(path="/flashcards/quiz")
	public List<Flashcard> getRandomAccessToFlashCardList(@RequestBody List<Flashcard> flashcarListToShuffle) {
		Collections.shuffle(flashcarListToShuffle);
		return flashcarListToShuffle;  
	}
}
