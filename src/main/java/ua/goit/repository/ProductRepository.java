package ua.goit.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.goit.model.dao.ProductDao;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends CrudRepository<ProductDao, UUID> {

    List<ProductDao> findByName(String name);
    @Query("SELECT p FROM ProductDao p")
    List<ProductDao> findAllProducts();
}
