package ua.goit.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.model.dao.ProductDao;
import ua.goit.model.dto.ProductDto;

@Service
public class ProductConverter implements Converter<ProductDao, ProductDto> {

    private final VendorConverter vendorConverter;

    @Autowired
    public ProductConverter(VendorConverter vendorConverter) {
        this.vendorConverter = vendorConverter;
    }

    @Override
    public ProductDto daoToDto(ProductDao type) {
        ProductDto product = new ProductDto();
        product.setId(type.getId());
        product.setName(type.getName());
        product.setPrice(type.getPrice());
        product.setVendor(vendorConverter.daoToDto(type.getVendor()));
        return product;
    }

    @Override
    public ProductDao dtoToDao(ProductDto type) {
        ProductDao product = new ProductDao();
        product.setId(type.getId());
        product.setName(type.getName());
        product.setPrice(type.getPrice());
        product.setVendor(vendorConverter.dtoToDao(type.getVendor()));
        return product;
    }
}
