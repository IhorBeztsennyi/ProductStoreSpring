package ua.goit.model.dao;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "product")
@Cacheable
public class ProductDao {
    private UUID id;
    private String name;
    private BigDecimal price;
    private VendorDao vendor;

    public ProductDao() {
    }

    public ProductDao(UUID id, String name, BigDecimal price, VendorDao vendor) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.vendor = vendor;
    }

    @Id
    @GeneratedValue
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "vendor_id", nullable = false)
    public VendorDao getVendor() {
        return vendor;
    }

    public void setVendor(VendorDao vendor) {
        this.vendor = vendor;
    }
}
