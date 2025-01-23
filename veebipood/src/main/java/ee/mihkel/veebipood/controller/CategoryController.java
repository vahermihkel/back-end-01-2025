package ee.mihkel.veebipood.controller;

import ee.mihkel.veebipood.entity.Category;
import ee.mihkel.veebipood.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("categories")
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(categoryRepository.findAll());
    }

    @PostMapping("categories")
    public ResponseEntity<List<Category>> addCategory(@RequestBody Category category) {
        if (category.getId() != null) {
            throw new RuntimeException("Ei tohi lisada ID võtit");
        }
        categoryRepository.save(category);
        return ResponseEntity.status(201).body(categoryRepository.findAll());
    }

    @DeleteMapping("categories/{id}")
    public ResponseEntity<List<Category>> deleteCategory(@PathVariable Long id) {
        try {
            categoryRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Ei saa kustutada kategooriat mille sees on tooted");
        }
        return ResponseEntity.ok(categoryRepository.findAll());
    }
}
