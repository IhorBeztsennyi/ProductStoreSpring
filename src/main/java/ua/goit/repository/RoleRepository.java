package ua.goit.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.goit.model.dao.RoleDao;

import java.util.UUID;

@Repository
public interface RoleRepository extends CrudRepository<RoleDao, UUID> {

    RoleDao findByName(String name);
}
