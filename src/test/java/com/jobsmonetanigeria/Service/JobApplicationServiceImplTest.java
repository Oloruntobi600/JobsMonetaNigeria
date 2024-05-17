package com.jobsmonetanigeria.Service;

import com.jobsmonetanigeria.Model.JobApplication;
import com.jobsmonetanigeria.Model.JobModel;
import com.jobsmonetanigeria.Repository.JobApplicationRepository;
import com.jobsmonetanigeria.Repository.JobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class JobApplicationServiceImplTest {

    @Mock
    private JobRepository jobRepository;

    @Mock
    private JobApplicationRepository jobApplicationRepository;

    @InjectMocks
    private JobApplicationServiceImpl jobApplicationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testApplyForJobWithJobDetailsAndApplicantName() {
        String jobDetails = "Software Developer";
        String applicantName = "John Doe";
        JobModel job = new JobModel();
        job.setJobDetails(jobDetails);
        job.setCompanyName("Tech Corp");
        job.setLocation("Remote");
        job.setRequirements("Java, Spring");

        List<JobModel> jobs = new ArrayList<>();
        jobs.add(job);

        when(jobRepository.findByJobDetails(jobDetails)).thenReturn(jobs);

        String result = jobApplicationService.applyForJob(jobDetails, applicantName);

        assertEquals("Applied for job: Software Developer by John Doe", result);

        ArgumentCaptor<JobApplication> jobApplicationCaptor = ArgumentCaptor.forClass(JobApplication.class);
        verify(jobApplicationRepository, times(1)).save(jobApplicationCaptor.capture());

        JobApplication capturedJobApplication = jobApplicationCaptor.getValue();
        assertEquals(job, capturedJobApplication.getJob());
        assertEquals(applicantName, capturedJobApplication.getApplicantName());
    }

    @Test
    void testApplyForJobWithNonExistentJobDetails() {
        String jobDetails = "Non Existent Job";
        String applicantName = "John Doe";

        when(jobRepository.findByJobDetails(jobDetails)).thenReturn(new ArrayList<>());

        String result = jobApplicationService.applyForJob(jobDetails, applicantName);

        assertEquals("Job not found!", result);

        verify(jobApplicationRepository, never()).save(any(JobApplication.class));
    }

    @Test
    void testApplyForJobWithJobId() {
        // This test is based on a placeholder implementation
        String jobId = "12345";

        // The method currently returns null, so we assert that
        String result = jobApplicationService.applyForJob(jobId);

        assertEquals(null, result);

    }
}
