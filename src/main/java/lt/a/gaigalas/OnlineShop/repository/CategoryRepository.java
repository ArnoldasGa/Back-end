package lt.a.gaigalas.OnlineShop.repository;

import lt.a.gaigalas.OnlineShop.model.Categorys;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CategoryRepository extends CrudRepository<Categorys, Integer> {

}
