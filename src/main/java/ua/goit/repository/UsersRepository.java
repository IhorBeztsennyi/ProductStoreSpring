package ua.goit.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.goit.model.dao.UserDao;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersRepository extends CrudRepository<UserDao, UUID> {

    Optional<UserDao> findByEmail(String email);


}
