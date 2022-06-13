package ua.goit.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.goit.model.RolesEnum;
import ua.goit.model.dao.UserDao;
import ua.goit.model.dao.VendorDao;
import ua.goit.repository.UsersRepository;
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
public class UserControllerTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @MockBean
    private UsersRepository usersRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testFindUserByNameMustReturnUserDao() throws Exception {
       UserDao user = createUserDao();
        when(usersRepository.findUserByEmail("user@gmail.com")).thenReturn(List.of(user));

        mockMvc.perform(MockMvcRequestBuilders.get("/users/name/?email=user@gmail.com"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("findUser"))
                .andExpect(MockMvcResultMatchers.model().attribute("users", hasSize(1)))
                .andExpect(MockMvcResultMatchers.model().attribute("users", hasItem(allOf(
                        hasProperty("id", equalTo(user.getId())),
                        hasProperty("firstName", equalTo(user.getFirstName())),
                        hasProperty("lastName", equalTo(user.getLastName())),
                        hasProperty("email", equalTo(user.getEmail())),
                        hasProperty("role", equalTo(user.getRole()))
                ))));
    }

    private UserDao createUserDao() {
        UserDao user = new UserDao();
        user.setId(UUID.randomUUID());
        user.setFirstName("Name");
        user.setLastName("Name");
        user.setEmail("user@gmail.com");
        user.setRole(RolesEnum.ROLE_ADMIN);
        user.setPassword(passwordEncoder.encode("1"));
        return user;
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testGetUserFormMustReturnUserForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/form/find"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("findUserForm"));
    }

    @Test
    void testGetUserFormWithIncorrectUserMustReturnHttpStatus302AndRedirectToLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/form/find"))
                .andExpect(MockMvcResultMatchers.status().is(302))
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
    }
}
