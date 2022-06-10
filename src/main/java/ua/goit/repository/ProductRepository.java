package ua.goit.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.goit.model.dao.ProductDao;
import ua.goit.model.dao.VendorDao;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ProductRepository extends CrudRepository<ProductDao, UUID> {

    @Query("SELECT p FROM ProductDao p")
    List<ProductDao> findAllProducts();

    @Query("SELECT p FROM ProductDao p WHERE p.name IN (?1)")
    List<ProductDao> findByNameList(String name);

    Optional<ProductDao> findByName(String name);

    @Query("SELECT v FROM VendorDao v WHERE v.id IN (?1)")
    Set<ProductDao> findByIds(Set<Integer> ids);


}
