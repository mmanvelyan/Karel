package org.karel.karel.user;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collections;
import java.util.UUID;

public class User extends org.springframework.security.core.userdetails.User {
    private int user_id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private String token;
    private String role;

    public User(int user_id, String username, String password, String email, String phone, String firstName, String lastName, String token, String role) {
        super(username, password, Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+role)));
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.token = token;
        this.role = role;
    }

    public User(UserData data){
        super(data.getUsername(), data.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_NOT_ACTIVATED")));
        this.email = data.getEmail();
        this.username = data.getUsername();
        this.password = data.getPassword();
        this.firstName = data.getFirstName();
        this.lastName = data.getLastName();
        this.phone = data.getPhone();
        this.token = UUID.randomUUID().toString();
        this.role = "NOT_ACTIVATED";
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

    public String getToken() {
        return token;
    }

    public String getRole() {
        return role;
    }
}
