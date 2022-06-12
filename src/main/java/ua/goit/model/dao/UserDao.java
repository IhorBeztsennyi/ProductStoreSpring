package ua.goit.model.dao;

import ua.goit.model.RolesEnum;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserDao {
    private UUID id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private RolesEnum role;

    public UserDao() {
    }

    public UserDao(UUID id, String email, String password, String firstName, String lastName, RolesEnum role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    @Id
    @GeneratedValue
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    public RolesEnum getRole() {
        return role;
    }

    public void setRole(RolesEnum role) {
        this.role = role;
    }
}
