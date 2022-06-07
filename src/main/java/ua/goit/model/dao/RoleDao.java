package ua.goit.model.dao;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "role")
public class RoleDao {
    private UUID id;
    private String name;

    public RoleDao() {
    }

    public RoleDao(UUID id, String name) {
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
}
