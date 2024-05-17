package com.jobsmonetanigeria.Controller;

import com.jobsmonetanigeria.Model.JobModel;
import com.jobsmonetanigeria.Model.Users;
import com.jobsmonetanigeria.Service.JobServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SearchControllerTest {

    @Mock
    private JobServiceImpl jobService;

    @InjectMocks
    private SearchController searchController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getRegistrationPage() {
        // Mock Model
        Model model = mock(Model.class);

        // Test method
        String viewName = searchController.getRegistrationPage(model);

        // Verify interactions
        verify(model, times(1)).addAttribute("user", new Users());
        assert viewName.equals("search_results");
    }

    @Test
    void registerUser() {
        // Mock parameters
        String keyword = "Java";
        String location = "New York";
        String category = "Software Development";

        // Mock Model
        Model model = mock(Model.class);

        // Mock JobModel list
        List<JobModel> searchJobs = new ArrayList<>();
        when(jobService.searchJobs(keyword, location, category)).thenReturn(searchJobs);

        // Test method
        String viewName = searchController.registerUser(keyword, location, category, model);

        // Verify interactions
        verify(jobService, times(1)).searchJobs(keyword, location, category);
        verify(model, times(1)).addAttribute("newJobs", searchJobs);
        assert viewName.equals("redirect:/searchJobs");
    }
    @Test
    void registerUserWithNoJobsFound() {
        // Mock parameters
        String keyword = "Java";
        String location = "New York";
        String category = "Software Development";

        // Mock Model
        Model model = mock(Model.class);

        // Mock JobModel list
        List<JobModel> searchJobs = new ArrayList<>();

        // Mock Service Method
        when(jobService.searchJobs(keyword, location, category)).thenReturn(searchJobs);

        // Test method
        String viewName = searchController.registerUser(keyword, location, category, model);

        // Verify interactions
        verify(jobService, times(1)).searchJobs(keyword, location, category);
        verify(model, times(1)).addAttribute("newJobs", searchJobs);
        assertEquals("redirect:/searchJobs", viewName);
    }

    @Test
    void registerUserWithJobsFound() {
        String keyword = "Java";
        String location = "New York";
        String category = "Software Development";


        Model model = mock(Model.class);

        List<JobModel> searchJobs = new ArrayList<>();
        searchJobs.add(new JobModel()); // Adding a dummy job


        when(jobService.searchJobs(keyword, location, category)).thenReturn(searchJobs);

        String viewName = searchController.registerUser(keyword, location, category, model);

        verify(jobService, times(1)).searchJobs(keyword, location, category);
        verify(model, times(1)).addAttribute("newJobs", searchJobs);
        assertEquals("redirect:/searchJobs", viewName);
    }
}
