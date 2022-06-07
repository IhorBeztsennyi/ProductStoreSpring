package ua.goit.model.dao;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "vendor")
public class VendorDao {
    private UUID id;
    private String name;
//    private Set<ProductDao> products;

    public VendorDao() {
    }

    public VendorDao(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

//    public Set<ProductDao> getProducts() {
//        return products;
//    }
//
//    public void setProducts(Set<ProductDao> products) {
//        this.products = products;
//    }
}
