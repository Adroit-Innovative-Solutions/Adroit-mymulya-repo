package com.mymulya.globaltables.service;


import com.mymulya.globaltables.dto.LoginResponse;
import com.mymulya.globaltables.dto.Payload;
import com.mymulya.globaltables.dto.User;
import com.mymulya.globaltables.model.GlobalUser;
import com.mymulya.globaltables.repository.GlobalUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
public class GlobalUserService {

    @Autowired
    private GlobalUserRepository globalUserRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public GlobalUser save(GlobalUser globalUser) {
        return globalUserRepository.save(globalUser);
    }

    public LoginResponse hostLogin(String email, String password) throws Exception {
        String domain = email.split("@")[1];

        GlobalUser mapping = globalUserRepository.findByDomain(domain);
        if (mapping == null) {
            throw new Exception("No organization found for this domain");
        }

        User user = getUserDetailsFromOrganization(mapping.getSchemaName(), mapping.getTableName(), email);
        if (user == null) {
            throw new Exception("User not found");
        }

        String role = getUserRole(user.getUserId(), mapping.getSchemaName());
        if (role == null) {
            throw new Exception("User role not found");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new Exception("Invalid password");
        }

        // Get current timestamp
        String loginTimestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        // Update the login timestamp in the user's table
        updateLoginTimestamp(mapping.getSchemaName(), mapping.getTableName(), user.getUserId(), loginTimestamp);

        // Create payload with timestamp
        Payload payload = new Payload(
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                role,
                mapping.getOrganization(),
                loginTimestamp
        );

        return new LoginResponse(true, "Login successful", payload, null);
    }

    private User getUserDetailsFromOrganization(String schema, String table, String email) {
        String query = String.format("SELECT * FROM %s.%s WHERE email = ?", schema, table);
        List<Map<String, Object>> result = jdbcTemplate.queryForList(query, new Object[]{email});

        if (result.isEmpty()) {
            return null;
        }

        Map<String, Object> row = result.get(0);
        User user = new User();
        user.setUserId((String) row.get("user_id"));
        user.setUserName((String) row.get("user_name"));
        user.setEmail((String) row.get("email"));
        user.setPassword((String) row.get("password"));

        return user;
    }

    private String getUserRole(String userId, String schema) {
        String userRoleQuery = String.format("SELECT role_id FROM %s.user_roles WHERE user_id = ?", schema);
        List<Map<String, Object>> result = jdbcTemplate.queryForList(userRoleQuery, new Object[]{userId});

        if (result.isEmpty()) {
            return null;
        }

        Long roleId = (Long) result.get(0).get("role_id");

        String roleQuery = String.format("SELECT name FROM %s.roles WHERE id = ?", schema);
        List<Map<String, Object>> roleResult = jdbcTemplate.queryForList(roleQuery, new Object[]{roleId});

        if (roleResult.isEmpty()) {
            return null;
        }

        return (String) roleResult.get(0).get("name");
    }

    private void updateLoginTimestamp(String schema, String table, String userId, String loginTimestamp) {
        String updateQuery = String.format("UPDATE %s.%s SET last_login_time = ? WHERE user_id = ?", schema, table);
        jdbcTemplate.update(updateQuery, loginTimestamp, userId);
    }
}
