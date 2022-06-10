package ua.goit.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.goit.model.dao.VendorDao;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface VendorRepository extends CrudRepository<VendorDao, UUID> {

    @Query("SELECT v FROM VendorDao v WHERE v.name IN (?1)")
    List<VendorDao> findByNameList(String name);

    Optional<VendorDao> findByName(String name);

    @Query("SELECT v FROM VendorDao v WHERE v.id IN (?1)")
    Set<VendorDao> findByIds(Set<Integer> ids);

    @Query("SELECT v FROM VendorDao v")
    List<VendorDao> findAllVendors();
}
