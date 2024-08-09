package org.karel.karel.user;

import org.karel.karel.submission.Submission;
import org.karel.karel.submission.SubmissionRepository;
import org.karel.karel.submission.SubmissionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final SubmissionRepository submissionRepository;

    public UserService(UserRepository repository, SubmissionRepository submissionService) {
        this.repository = repository;
        this.submissionRepository = submissionService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.getUser(username);
        if (user == null) {
            throw new UsernameNotFoundException("Unknown user "+ username);
        }
        return user;
    }

    public void saveUser(UserData user) {
        repository.saveUser(new User(user));
    }

    public UserProfile getUserProfile() {
        String username = getCurrentUsername();
        User user = repository.getUser(username);
        if (user == null) {
            throw new UsernameNotFoundException("Unknown user "+ username);
        }
        int user_id = user.getUser_id();
        List<Submission> submissions = submissionRepository.getByUser(user_id);
        return new UserProfile(user.getFirstName(), user.getLastName(), user.getUsername(), submissions);
    }

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public boolean isAuthenticated() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public int getCurrentUserId() {
        return repository.getUser(getCurrentUsername()).getUser_id();
    }
}