package ua.goit.model.dto;

import java.util.Set;
import java.util.UUID;

public class VendorDto {
    private UUID id;
    private String name;
//    private Set<ProductDto> products;

    public VendorDto() {
    }

    public VendorDto(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return id +
                "," + name;
    }

//    public Set<ProductDto> getProducts() {
//        return products;
//    }
//
//    public void setProducts(Set<ProductDto> products) {
//        this.products = products;
//    }
}
