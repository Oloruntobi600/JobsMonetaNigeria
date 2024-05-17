package com.jobsmonetanigeria.Controller;

import com.jobsmonetanigeria.Model.Users;
import com.jobsmonetanigeria.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getLoginPage() {
        String viewName = userController.getLoginPage();
        assertEquals("login_page", viewName);
    }

    @Test
    void getRegistrationPage() {
        // Mock Model
        Model model = mock(Model.class);

        // Test method
        String viewName = userController.getRegistrationPage(model);

        // Verify interactions
        verify(model, times(1)).addAttribute("user", new Users());
        assertEquals("register", viewName);
    }

    @Test
    void registerUser() {
        Users user = new Users();


        String viewName = userController.registerUser(user);

        verify(userService, times(1)).register(user);
        assertEquals("redirect:/login?success", viewName);
    }
}
