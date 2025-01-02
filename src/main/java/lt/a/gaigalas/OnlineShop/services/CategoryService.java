package lt.a.gaigalas.OnlineShop.services;

import lt.a.gaigalas.OnlineShop.dtos.AddCategoryDto;
import lt.a.gaigalas.OnlineShop.model.Categorys;
import lt.a.gaigalas.OnlineShop.repository.CategoryRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categotyRepository) {
        this.categoryRepository = categotyRepository;
    }

    public List<Categorys> allCategories() {
        List<Categorys> category = new ArrayList<>();

        categoryRepository.findAll().forEach(category::add);

        return category;
    }

    public Categorys add(AddCategoryDto category) {
        Categorys categorys = new Categorys();
        categorys.setCategory(category.getName());
        return categoryRepository.save(categorys);
    }
}
