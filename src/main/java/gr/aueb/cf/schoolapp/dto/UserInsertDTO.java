package gr.aueb.cf.schoolapp.dto;

public class UserInsertDTO extends BaseUserDTO {

    private String role;

    public UserInsertDTO() {

    }

    public UserInsertDTO(String username, String password, String confirmedPassword, String role) {
        super(username, password, confirmedPassword);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
