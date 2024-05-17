package com.jobsmonetanigeria.Controller;

import com.jobsmonetanigeria.Service.JobApplicationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class JobApplicationControllerTest {

    @Mock
    private JobApplicationService jobApplicationService;

    @Mock
    private Model model;

    @InjectMocks
    private JobApplicationController jobApplicationController;

    public JobApplicationControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testApplyForJob() {
        String jobDetails = "Software Engineer";
        String applicationStatus = "Application received";
        when(jobApplicationService.applyForJob(jobDetails)).thenReturn(applicationStatus);


        String viewName = jobApplicationController.applyForJob(jobDetails, model);

        assertEquals("application_confirmation", viewName);
        verify(jobApplicationService, times(1)).applyForJob(jobDetails);
        verify(model, times(1)).addAttribute("applicationStatus", applicationStatus);
    }
}
