package ua.goit.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.goit.model.dao.ProductDao;
import ua.goit.model.dao.VendorDao;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource({"classpath:application-test.properties"})
@ActiveProfiles("test")
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findByNameShouldReturnProduct() {
        //given
        prepareProduct();
        //when
        ProductDao product = productRepository.findByName("X67").get();
        //then

        assertThat(product.getId(), notNullValue());
        assertThat(product.getName(), is("X67"));
        assertThat(product.getVendor().getId(), notNullValue());
        assertThat(product.getVendor().getName(), is("Dell"));
    }

    private void prepareProduct() {
        VendorDao vendor = new VendorDao(null, "Dell");
        ProductDao product = new ProductDao(null, "X67", BigDecimal.valueOf(300), vendor);
        entityManager.persist(product.getVendor());
        entityManager.persist(product);
        entityManager.flush();
    }
}
