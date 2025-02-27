package org.karel.karel.user;

public interface UserRepository {
    User getUser(String username);
    User getUserByEmail(String email);
    void saveUser(User user);
    void activateByToken(String token);
}
