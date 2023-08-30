package fr.nawelbp.flashcards.controller;


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
import fr.nawelbp.flashcards.repository.CategoryRepository;

@CrossOrigin("*")
@RestController
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	
	
	@PostMapping(path="/newcategory")
	public void createNewCategory(@RequestBody Category category){
		
		List<Category> allCategories=getAllCategories();
		try {
			List<Category> categoryName = categoryRepository.findAllByName(category.getName());
			if (categoryName.isEmpty() || categoryName == null) {
				Category cat= new Category(category.getName().toLowerCase());
				categoryRepository.save(cat);
			}
	        else if (categoryName.size() == 1) 
	        	System.out.println("Category already exist !");
		} catch (NullPointerException e) {
			System.out.println("all categ is empty");		}
		
        
	}
	
	@GetMapping(path="/categories")
	public List<Category> getAllCategories(){
		return categoryRepository.findAll();
	}
	
	@GetMapping(path="/categories/{id}")
	public Optional<Category> getCategoryById(@PathVariable Long id){
		return categoryRepository.findById(id);
	}
	
	@GetMapping(path="/categories/name/{name}")
	public Optional<Category> getCategoryByName(@PathVariable String name){
		name= name.toLowerCase();
		System.out.println("SPRING GET CATEGORY BY NAME");
		return categoryRepository.findByName(name);
	}
	
	@PutMapping(path="/categories/{id}")
	public Category updateCategory(@PathVariable Long id, @RequestBody Category updatedCategorie) {
		return categoryRepository.save(updatedCategorie);
	}
	
	@DeleteMapping(path="/categories/{id}")
	public void deleteCategoryById(@PathVariable Long id) {
				categoryRepository.deleteById(id);
		}
	
	
}
