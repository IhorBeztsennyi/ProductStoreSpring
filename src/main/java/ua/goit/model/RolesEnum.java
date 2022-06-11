package ua.goit.model;

public enum RolesEnum {

    ROLE_ADMIN("ADMIN"),
    ROLE_USER("USER");

    private String role;

    RolesEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
