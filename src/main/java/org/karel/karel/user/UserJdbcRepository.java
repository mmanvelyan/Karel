package org.karel.karel.user;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserJdbcRepository implements UserRepository {

    private final JdbcClient jdbcClient;
    private final PasswordEncoder passwordEncoder;

    private final RowMapper<User> userRowMapper = (rs, rowNum) ->
            new User(rs.getInt("user_id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("firstname"),
                    rs.getString("lastname"));

    public UserJdbcRepository(JdbcClient jdbcClient, PasswordEncoder passwordEncoder) {
        this.jdbcClient = jdbcClient;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUser(String username) {
        String query = "select user_id, username, password, email, phone, firstname, lastname from karel.users where username = :username";
        return jdbcClient
                .sql(query)
                .param("username", username)
                .query(userRowMapper)
                .single();
    }

    public void saveUser(User user) {
        String update = "insert into karel.users(username, password, email, phone, firstname, lastname) " +
                "values(:username, :password, :email, :phone, :firstname, :lastname)";
        Integer rowsAffected = jdbcClient
                .sql(update)
                .param("username", user.getUsername())
                .param("password", passwordEncoder.encode(user.getPassword()))
                .param("email", user.getEmail())
                .param("phone", user.getPhone())
                .param("firstname", user.getFirstName())
                .param("lastname", user.getLastName())
                .update();
    }
}














