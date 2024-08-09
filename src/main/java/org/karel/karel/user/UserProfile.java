package org.karel.karel.user;

import org.karel.karel.submission.Submission;

import java.util.List;

public class UserProfile {
    private final String firstName;
    private final String lastName;
    private final String username;
    private final List<Submission> submissions;

    public UserProfile(String firstName, String lastName, String username, List<Submission> submissions) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.submissions = submissions;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public List<Submission> getSubmissions() {
        return submissions;
    }
}
