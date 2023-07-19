package fr.nawelbp.flashcards.repository;

import org.springframework.stereotype.Repository;

import fr.nawelbp.flashcards.model.Category;
import fr.nawelbp.flashcards.model.Flashcard;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {

	List<Flashcard> findByCategory(Category category);


	

}
