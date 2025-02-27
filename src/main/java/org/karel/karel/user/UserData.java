package org.karel.karel.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserData {

    @NotNull(message = "Username must be not empty")
    @Size(min=3, max=15, message = "Username must be 3 to 15 letters")
    @Pattern(regexp = "[a-zA-Z]+[a-zA-Z0-9]+", message = "Username contains invalid characters")
    @UniqueUsername(message = "Username is already used")
    private String username;

    @Size(min=3, max=15, message = "Password must be 3 to 15 symbols")
    private String password;

    @NotNull(message = "Email must be not empty")
    @Email(message = "Invalid email format")
    @UniqueEmail(message = "Email is already used")
    private String email;

    private String phone;

    @NotNull(message = "First Name must be not empty")
    @Pattern(regexp = "[a-zA-Z]+", message = "First Name contains invalid symbols")
    private String firstName;

    @NotNull(message = "Last Name must be not empty")
    @Pattern(regexp = "[a-zA-Z]+", message = "Last Name contains invalid symbols")
    private String lastName;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
