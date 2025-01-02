package lt.a.gaigalas.OnlineShop.repository;

import lt.a.gaigalas.OnlineShop.model.Products;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends CrudRepository<Products, Integer> {
}
