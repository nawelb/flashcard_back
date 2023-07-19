package fr.nawelbp.flashcards.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin("*")
@RestController
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	
	
	@PostMapping(path="/newcategory")
	public void createNewCategory(@RequestBody Category category){
		categoryRepository.save(category);
	}
	
	@GetMapping(path="/categories")
	public List<Category> getAllCategories(){
		return categoryRepository.findAll();
	}
	
	@GetMapping(path="/categories/{id}")
	public Optional<Category> getCategoryById(@PathVariable Long id){
		return categoryRepository.findById(id);
	}
	
	@GetMapping(path="/categories/{id}/flashcards")
	public List<Flashcard>getAllFlashcardByCategory(@PathVariable Long id){
		return getCategoryById(id).get().getFlashcards();
	}
	
	@PutMapping(path="/categories/{id}")
	public Category updateCategory(@PathVariable Long id, @RequestBody Category updatedCategorie) {
		return categoryRepository.save(updatedCategorie);
	}
	
	@DeleteMapping(path="/categories/{id}")
	public void deleteCategoryById(@PathVariable Long id) {
		Optional<Category> category = getCategoryById(id);
		List<Flashcard>flashcardList=new ArrayList();
		flashcardList=category.get().getFlashcards();
		if(flashcardList.isEmpty()) {
			categoryRepository.deleteById(id);
		}else {
			System.out.println("Impossible de supprimer cette catégorie, car elle a des Flashcards associées !");
		}
	}
	
}
