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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
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
        createVendorDao();
        //when
        List<VendorDao> vendors = vendorRepository.findByNameList("Dell");
        //then
        assertThat(vendors.size(), equalTo(1));
        VendorDao vendor = vendors.stream().filter(b -> b.getId().equals(UUID.fromString("709df06b-d59a-4634-bdc5-a888a978e459"))).findFirst().get();
        assertThat(vendor.getId(), is(UUID.fromString("709df06b-d59a-4634-bdc5-a888a978e459")));
        assertThat(vendor.getName(), is("book1"));

    }

    private void createVendorDao() {
        List<VendorDao> vendors = prepareBooksForDb();
        vendors.forEach(vendor -> {
            entityManager.persist(vendor);
        });
        entityManager.flush();
    }

   private List<VendorDao> prepareBooksForDb(){
       List<VendorDao> vendors = new ArrayList<>();
       vendors.add(new VendorDao(UUID.fromString("3e7adf62-d795-4a69-ade0-baa2bf62253a"), "Oppo"));
       vendors.add(new VendorDao(UUID.fromString("709df06b-d59a-4634-bdc5-a888a978e459"), "Dell"));
       return vendors;
   }

}
