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
import ua.goit.model.dao.VendorDao;
import ua.goit.repository.VendorRepository;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@ActiveProfiles({"test"})
public class VendorControllerTest {

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private VendorRepository vendorRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(roles = "USER")
    void testFindVendorByNameMustReturnVendorDao() throws Exception {
        VendorDao vendor = createVendorDao();
        when(vendorRepository.findByNameList("Dell")).thenReturn(List.of(vendor));

        mockMvc.perform(MockMvcRequestBuilders.get("/vendors/name/?name=Dell"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("findVendor"))
                .andExpect(MockMvcResultMatchers.model().attribute("vendors", hasSize(1)))
                .andExpect(MockMvcResultMatchers.model().attribute("vendors", hasItem(allOf(
                        hasProperty("id", equalTo(vendor.getId())),
                        hasProperty("name", equalTo(vendor.getName()))
                ))));
    }

    private VendorDao createVendorDao() {
        VendorDao vendor = new VendorDao();
        vendor.setId(UUID.fromString("0c3dfbd0-1b8e-4ec1-99a4-3d302aaa3ceb"));
        vendor.setName("Dell");
        return vendor;
    }

    @Test
    @WithMockUser(roles = "USER")
    void testGetVendorFormMustReturnVendorForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/vendors/form/find"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("findVendorForm"));
    }

    @Test
    void testGetVendorFormWithIncorrectUserMustReturnHttpStatus302AndRedirectToLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/vendors/form/find"))
                .andExpect(MockMvcResultMatchers.status().is(302))
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
    }


}
