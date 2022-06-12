package ua.goit.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ua.goit.converter.ProductConverter;
import ua.goit.converter.UserConverter;
import ua.goit.converter.VendorConverter;
import ua.goit.repository.ProductRepository;
import ua.goit.repository.UsersRepository;
import ua.goit.repository.VendorRepository;
import ua.goit.service.ProductService;
import ua.goit.service.UserService;
import ua.goit.service.VendorService;

@TestConfiguration
public class ConfigurationTest {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private ProductRepository productRepository;

    @Bean
    public UserConverter userConverter(){
        return new UserConverter();
    }

    @Bean
    public VendorConverter vendorConverter() {
        return new VendorConverter();
    }

    @Bean
    public ProductConverter productConverter(){
        return new ProductConverter(vendorConverter());
    }

    @Bean
    public VendorService vendorService() {
        return new VendorService(vendorRepository, vendorConverter());
    }

    @Bean
    public UserService userService(){
        return new UserService(usersRepository, userConverter());
    }

    @Bean
    public ProductService productService(){
        return new ProductService(productRepository, productConverter());
    }
}
