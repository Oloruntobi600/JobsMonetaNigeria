package com.jobsmonetanigeria.Service;

import com.jobsmonetanigeria.Model.UserRole;
import com.jobsmonetanigeria.Model.Users;
import com.jobsmonetanigeria.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

//    private static List<Users> users = new ArrayList<>();

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @PostConstruct
    public void postConstruct() {
        Optional<Users> existingAdmin = userRepository.findByUsername("admin");
        if (existingAdmin.isEmpty()) {
            Users user = new Users();
            user.setRole(UserRole.ADMIN);
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("abc"));
            userRepository.save(user);
        }
    }

    public void register(Users user) {
        user.setRole(UserRole.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public Users findByLogin(String login) {
        return userRepository.findByUsername(login)
                .orElse(null);
    }
    @Autowired
    private UserRepository repo;
    public Users login(String username, String password) {
        Optional<Users> userOptional = repo.findByUsername(username);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}