package ro.sda.spring.thymeleaf.entity;

import ro.sda.spring.thymeleaf.entity.enums.UserAuthorityEnum;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class User extends BaseEntity {

    @Column(unique = true, updatable = false, length = 32, nullable = false)
    private String username;

    @Column(length = 64, nullable = false)
    private String password;

    @Column(length = 32, nullable = false)
    private String firstName;

    @Column(length = 32, nullable = false)
    private String lastName;

    @Column(length = 64)
    private String email;

    @Column(length = 32)
    private String country;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 16)
    private UserAuthorityEnum userAuthorityEnum;

    public User() {
    }

    public User(String username, String password, String firstName, String lastName, String email, String country, UserAuthorityEnum userAuthorityEnum) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.userAuthorityEnum = userAuthorityEnum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public UserAuthorityEnum getUserAuthorityEnum() {
        return userAuthorityEnum;
    }

    public void setUserAuthorityEnum(UserAuthorityEnum userAuthorityEnum) {
        this.userAuthorityEnum = userAuthorityEnum;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", userAuthorityEnum=" + userAuthorityEnum +
                ", id=" + id +
                ", createdTime=" + createdTime +
                ", lastModifiedTime=" + lastModifiedTime +
                '}';
    }
}