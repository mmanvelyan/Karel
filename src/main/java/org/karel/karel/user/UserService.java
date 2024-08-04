package org.karel.karel.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.getUser(username);
        if (user == null) {
            throw new UsernameNotFoundException("Unknown user "+ username);
        }
        return user;
    }

    public void saveUser(org.karel.karel.user.User user) {
        repository.saveUser(user);
    }
}