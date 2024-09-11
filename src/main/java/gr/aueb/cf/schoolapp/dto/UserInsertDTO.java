package gr.aueb.cf.schoolapp.dto;

public class UserInsertDTO extends BaseUserDTO {


    public UserInsertDTO() {

    }

    public UserInsertDTO(String username, String password, String confirmedPassword) {
        super(username, password, confirmedPassword);
    }
}
