package ua.goit.model.dto;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class ProductDto {
    private UUID id;
    private String name;
    private BigDecimal price;
    private VendorDto vendor;

    public ProductDto() {
    }

    public ProductDto(UUID id, String name, BigDecimal price, VendorDto vendor) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.vendor = vendor;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public VendorDto getVendor() {
        return vendor;
    }

    public void setVendor(VendorDto vendor) {
        this.vendor = vendor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(price, that.price) && Objects.equals(vendor, that.vendor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, vendor);
    }
}
