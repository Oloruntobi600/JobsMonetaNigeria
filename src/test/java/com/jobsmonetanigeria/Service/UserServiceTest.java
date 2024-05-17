package com.jobsmonetanigeria.Service;

import com.jobsmonetanigeria.Model.UserRole;
import com.jobsmonetanigeria.Model.Users;
import com.jobsmonetanigeria.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPostConstruct() {
        Users adminUser = new Users();
        adminUser.setUsername("admin");
        adminUser.setPassword("encodedPassword");
        adminUser.setRole(UserRole.ADMIN);

        when(userRepository.findByUsername("admin")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("abc")).thenReturn("encodedPassword");

        userService.postConstruct();

        verify(userRepository, times(1)).findByUsername("admin");
        verify(passwordEncoder, times(1)).encode("abc");
        verify(userRepository, times(1)).save(any(Users.class));
    }

    @Test
    void testPostConstructAdminExists() {
        Users existingAdmin = new Users();
        existingAdmin.setUsername("admin");

        when(userRepository.findByUsername("admin")).thenReturn(Optional.of(existingAdmin));

        userService.postConstruct();

        verify(userRepository, times(1)).findByUsername("admin");
        verify(userRepository, times(0)).save(any(Users.class));
    }

    @Test
    void testRegister() {
        Users user = new Users();
        user.setUsername("user");
        user.setPassword("plainPassword");

        when(passwordEncoder.encode("plainPassword")).thenReturn("encodedPassword");

        userService.register(user);

        assertEquals(UserRole.USER, user.getRole());
        assertEquals("encodedPassword", user.getPassword());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testFindByLogin() {
        Users user = new Users();
        user.setUsername("user");

        when(userRepository.findByUsername("user")).thenReturn(Optional.of(user));

        Users result = userService.findByLogin("user");

        assertEquals("user", result.getUsername());
        verify(userRepository, times(1)).findByUsername("user");
    }

    @Test
    void testFindByLoginNotFound() {
        when(userRepository.findByUsername("nonExistentUser")).thenReturn(Optional.empty());

        Users result = userService.findByLogin("nonExistentUser");

        assertNull(result);
        verify(userRepository, times(1)).findByUsername("nonExistentUser");
    }

    @Test
    void testLoginSuccess() {
        Users user = new Users();
        user.setUsername("user");
        user.setPassword("encodedPassword");

        when(userRepository.findByUsername("user")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("plainPassword", "encodedPassword")).thenReturn(true);

        Users result = userService.login("user", "plainPassword");

        verify(userRepository, times(1)).findByUsername("user");
    }

    @Test
    void testLoginFailWrongPassword() {
        Users user = new Users();
        user.setUsername("user");
        user.setPassword("encodedPassword");

        when(userRepository.findByUsername("user")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrongPassword", "encodedPassword")).thenReturn(false);

        Users result = userService.login("user", "wrongPassword");

        assertNull(result);
        verify(userRepository, times(1)).findByUsername("user");
    }

    @Test
    void testLoginUserNotFound() {
        when(userRepository.findByUsername("nonExistentUser")).thenReturn(Optional.empty());

        Users result = userService.login("nonExistentUser", "anyPassword");

        assertNull(result);
        verify(userRepository, times(1)).findByUsername("nonExistentUser");
    }
}
