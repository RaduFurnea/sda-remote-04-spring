package ro.sda.spring.boot.dto;

import javax.validation.constraints.NotBlank;

public class UserRegisterDTO {

    @NotBlank(message = "Username must not be blank.")
    private String username;

    @NotBlank(message = "Password must not be blank.")
    private String password;

    @NotBlank(message = "Confirm password must not be blank.")
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String login) {
        this.username = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }


}
