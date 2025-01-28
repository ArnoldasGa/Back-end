package lt.a.gaigalas.OnlineShop.services;

import lt.a.gaigalas.OnlineShop.dtos.AddProductDto;
import lt.a.gaigalas.OnlineShop.model.Categorys;
import lt.a.gaigalas.OnlineShop.model.Products;
import lt.a.gaigalas.OnlineShop.repository.CategoryRepository;
import lt.a.gaigalas.OnlineShop.repository.ProductsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    private final ProductsRepository productsRepository;

    private final CategoryRepository categoryRepository;

    public ProductsService(ProductsRepository productsRepository, CategoryRepository categoryRepository) {
        this.productsRepository = productsRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Products> allProducts() {
        List<Products> products = new ArrayList<>();
        productsRepository.findAll().forEach(products::add);
        return products;
    }

    public Products add(AddProductDto product) {
        Categorys category = categoryRepository.findById(product.getCategory_id())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Products products = new Products();
        products.setName(product.getName());
        products.setDescription(product.getDescription());
        products.setCategory(category);

        return productsRepository.save(products);
    }

    public Optional<Products> getById(int id) {
        Optional<Products> products = Optional.of(new Products());
        products = productsRepository.findById(id);
        return products;
    }
}
