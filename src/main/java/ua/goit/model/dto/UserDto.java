package ua.goit.model.dto;

import ua.goit.model.RolesEnum;

import javax.validation.constraints.Email;
import java.util.UUID;

public class UserDto {
    private UUID id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private RolesEnum role;

    public UserDto() {
    }

    public UserDto(UUID id, String email, String password, String firstName, String lastName, RolesEnum role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public RolesEnum getRole() {
        return role;
    }

    public void setRole(RolesEnum role) {
        this.role = role;
    }
}
