package ua.goit.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.goit.model.dao.VendorDao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
@ActiveProfiles("test")
public class VendorRepositoryTest {

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findByNameMustReturnVendorDao() {
        //given
        createAndPersistVendorDao();
        //when
        VendorDao vendor = vendorRepository.findByName("Dell").get();
        //then
        assertThat(vendor.getName(), is("Dell"));
    }

    private void createAndPersistVendorDao() {
        VendorDao vendor = new VendorDao(null, "Dell");
        entityManager.persist(vendor);
        entityManager.flush();
    }
}
