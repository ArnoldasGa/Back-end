package lt.a.gaigalas.OnlineShop.repository;

import lt.a.gaigalas.OnlineShop.model.Role;
import lt.a.gaigalas.OnlineShop.model.RoleEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findByName(RoleEnum name);
}