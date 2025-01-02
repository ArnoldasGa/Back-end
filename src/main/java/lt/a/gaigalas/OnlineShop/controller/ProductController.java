package lt.a.gaigalas.OnlineShop.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lt.a.gaigalas.OnlineShop.dtos.AddProductDto;
import lt.a.gaigalas.OnlineShop.model.Products;
import lt.a.gaigalas.OnlineShop.services.ProductsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/products")
@RestController
public class ProductController {
    private final ProductsService productsService;

    public ProductController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Products>> getAllProducts() {
        List<Products> products = productsService.allProducts();
        return ResponseEntity.ok(products);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@Valid @RequestBody AddProductDto product) {
        try {
            Products addedProduct = productsService.add(product);
            return ResponseEntity.ok(addedProduct);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
