package org.karel.karel.user;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collections;

public class User extends org.springframework.security.core.userdetails.User {
    private int user_id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;

    public User(int user_id, String username, String password, String email, String phone, String firstName, String lastName) {
        super(username, password, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User() {
        this(0, "#", "#", "#", "#", "#", "#");
    }

    public int getUser_id() {
        return user_id;
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

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

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
}
