package ua.goit.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.goit.model.dao.ProductDao;
import ua.goit.model.dao.VendorDao;
import ua.goit.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@ActiveProfiles("test")
public class ProductControllerTest {
    @Autowired
    private WebApplicationContext context;

    @MockBean
    private ProductRepository productRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    void testFindProductByNameMustReturnProductDao() throws Exception {
        ProductDao product = createProductDao();
        when(productRepository.findByNameList("X67")).thenReturn(List.of(product));

        mockMvc.perform(MockMvcRequestBuilders.get("/products/name/?name=X67"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("findProduct"))
                .andExpect(MockMvcResultMatchers.model().attribute("products", hasSize(1)))
                .andExpect(MockMvcResultMatchers.model().attribute("products", hasItem(allOf(
                        hasProperty("id", equalTo(product.getId())),
                        hasProperty("name", equalTo(product.getName())),
                        hasProperty("price", equalTo(product.getPrice()))
                ))));
    }

    @Test
    @WithMockUser(roles = "USER")
    void testGetProductFormMustReturnProductForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products/form/find"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("findProductForm"));
    }

    @Test
    void testGetProductFormWithIncorrectUserMustReturnHttpStatus302AndRedirectToLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products/form/find"))
                .andExpect(MockMvcResultMatchers.status().is(302))
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
    }


    private ProductDao createProductDao(){
        ProductDao product = new ProductDao();
        product.setId(UUID.randomUUID());
        product.setName("X67");
        product.setPrice(BigDecimal.valueOf(300.99));
        product.setVendor(createVendorDao());
        return product;
    }

    private VendorDao createVendorDao() {
        VendorDao vendor = new VendorDao();
        vendor.setId(UUID.fromString("0c3dfbd0-1b8e-4ec1-99a4-3d302aaa3ceb"));
        vendor.setName("Dell");
        return vendor;
    }
}
