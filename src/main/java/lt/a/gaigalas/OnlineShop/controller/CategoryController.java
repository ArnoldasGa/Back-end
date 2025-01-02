package lt.a.gaigalas.OnlineShop.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lt.a.gaigalas.OnlineShop.dtos.AddCategoryDto;
import lt.a.gaigalas.OnlineShop.model.Categorys;
import lt.a.gaigalas.OnlineShop.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/categories")
@RestController
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Categorys>> getAllCategory() {
        List<Categorys> category = categoryService.allCategories();

        return ResponseEntity.ok(category);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<Categorys> addCategory(@RequestBody AddCategoryDto category) {
        Categorys addCategory = categoryService.add(category);
        return ResponseEntity.ok(addCategory);
    }
}
