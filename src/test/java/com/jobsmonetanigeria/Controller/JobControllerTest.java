package com.jobsmonetanigeria.Controller;

import com.jobsmonetanigeria.Model.JobModel;
import com.jobsmonetanigeria.Service.JobServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class JobControllerTest {

    @Mock
    private JobServiceImpl jobService;

    @InjectMocks
    private JobController jobController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(jobController).build();
    }

    @Test
    void getJobPage() {
        // Mock UserDetails
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add((GrantedAuthority) () -> "ROLE_USER");
        UserDetails userDetails = new User("testUser", "password", authorities);


        List<JobModel> jobs = new ArrayList<>();
        when(jobService.getAllJobsByLogin(anyString())).thenReturn(jobs);


        Model model = mock(Model.class);


        jobController.getJobPage(model, userDetails);

        verify(jobService, times(1)).getAllJobsByLogin("testUser");
        verify(model, times(1)).addAttribute("userLogin", "testUser");
        verify(model, times(1)).addAttribute("userJobs", jobs);
    }
    @Test
    void getCreateJobPage() {
        // Mock Model
        Model model = mock(Model.class);

        // Test method
        String viewName = jobController.getCreateJobPage(model);

        // Verify interactions
        verify(model, times(1)).addAttribute("newJob", new JobModel());
        assert viewName.equals("create_job_page");
    }

    @Test
    void createJob_ValidInput() {
        // Mock JobModel
        JobModel jobModel = new JobModel();
        jobModel.setCompanyName("Test Company");

        // Test method
        String viewName = jobController.createJob(jobModel);

        // Verify interactions
        verify(jobService, times(1)).save(jobModel);
        assert viewName.equals("redirect:/jobs");
    }

    @Test
    void createJob_NullInput() {
        JobModel jobModel = new JobModel();


        String viewName = jobController.createJob(jobModel);


        verifyNoInteractions(jobService);
        assert viewName.equals("redirect:/jobs");
    }
    @Test
    void getEditJobPage() {
        JobModel jobModel = new JobModel();
        when(jobService.findByTitle(anyString())).thenReturn(jobModel);


        Model model = mock(Model.class);

        String viewName = jobController.getEditJobPage(model, "Test Title");

        verify(jobService, times(1)).findByTitle("Test Title");
        verify(model, times(1)).addAttribute("jobToEdit", jobModel);
        assert viewName.equals("edit_job_page");
    }

    @Test
    void editJob() {

        JobModel jobModel = new JobModel();

        String viewName = jobController.editJob(jobModel);

        verify(jobService, times(1)).edit(jobModel);
        assert viewName.equals("redirect:/jobs");
    }

    @Test
    void delete() {
        String viewName = jobController.delete("Test Title");

        verify(jobService, times(1)).delete("Test Title");
        assert viewName.equals("redirect:/jobs");
    }

    @Test
    void browseJob() {
        String viewName = jobController.browseJob();

        assert viewName.equals("redirect:/browse-job");
    }

    @Test
    void browseJobPage() {
        String viewName = jobController.browseJobPage();

        assert viewName.equals("browse-job");
    }

}
