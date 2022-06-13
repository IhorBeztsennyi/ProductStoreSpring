package ua.goit.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.goit.model.RolesEnum;
import ua.goit.model.dao.UserDao;
import ua.goit.model.dao.VendorDao;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource({"classpath:application-test.properties"})
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    public void findByNameMustReturnVendorDao() {
        //given
        persistUserDao();
        //when
        List<UserDao> users = usersRepository.findUserByEmail("user@gmail.com");
        //then
        assertThat(users.size(), equalTo(1));
        UserDao user = users.stream().filter(b -> b.getEmail().equals("user@gmail.com")).findFirst().get();
        assertThat(user.getId(), is(notNullValue()));
        assertThat(user.getFirstName(), is("Name"));
        assertThat(user.getLastName(), is("Name"));
        assertThat(user.getEmail(), is("user@gmail.com"));
        assertThat(user.getRole().getRole(), is("ROLE_ADMIN"));
    }

    private UserDao createUserDao() {
        UserDao user = new UserDao();
        user.setId(null);
        user.setFirstName("Name");
        user.setLastName("Name");
        user.setEmail("user@gmail.com");
        user.setRole(RolesEnum.ROLE_ADMIN);
        user.setPassword("1");
        return user;
    }

    private void persistUserDao(){
        entityManager.persist(createUserDao());
        entityManager.flush();
    };
}
