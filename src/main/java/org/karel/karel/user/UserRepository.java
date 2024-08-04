package org.karel.karel.user;

public interface UserRepository {
    User getUser(String username);
    void saveUser(User user);
}
