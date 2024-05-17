package com.jobsmonetanigeria.Model;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class JobApplicationTest {

    @Test
    void testDefaultConstructor() {
        // Create a JobApplication object using the default constructor
        JobApplication jobApplication = new JobApplication();

        // Assert that the application date is set to the current date
        assertNotNull(jobApplication.getApplicationDate());
    }

    @Test
    void testParameterizedConstructor() {
        // Mock JobModel
        JobModel jobModel = mock(JobModel.class);

        // Create a JobApplication object using the parameterized constructor
        String applicantName = "John Doe";
        JobApplication jobApplication = new JobApplication(jobModel, applicantName);

        // Assert that the fields are set correctly
        assertEquals(jobModel, jobApplication.getJob());
        assertEquals(applicantName, jobApplication.getApplicantName());
        assertNotNull(jobApplication.getApplicationDate());
    }

    @Test
    void testGettersAndSetters() {
        // Mock JobModel
        JobModel jobModel = mock(JobModel.class);

        // Create a JobApplication object
        JobApplication jobApplication = new JobApplication();

        // Set values using setters
        jobApplication.setId(1L);
        jobApplication.setJob(jobModel);
        jobApplication.setApplicantName("John Doe");
        Date applicationDate = new Date();
        jobApplication.setApplicationDate(applicationDate);

        // Assert that values are retrieved correctly using getters
        assertEquals(1L, jobApplication.getId());
        assertEquals(jobModel, jobApplication.getJob());
        assertEquals("John Doe", jobApplication.getApplicantName());
        assertEquals(applicationDate, jobApplication.getApplicationDate());
    }
    @Test
    void testGetId() {
        // Create a JobApplication object
        JobApplication jobApplication = new JobApplication();

        // Set an ID
        jobApplication.setId(1L);

        // Assert that the getter retrieves the correct ID
        assertEquals(1L, jobApplication.getId());
    }

    @Test
    void testSetId() {
        // Create a JobApplication object
        JobApplication jobApplication = new JobApplication();

        // Set an ID
        jobApplication.setId(1L);

        // Assert that the ID is set correctly
        assertEquals(1L, jobApplication.getId());
    }

    @Test
    void testGetJob() {
        JobModel jobModel = mock(JobModel.class);

        JobApplication jobApplication = new JobApplication();
        jobApplication.setJob(jobModel);
        assertEquals(jobModel, jobApplication.getJob());
    }

    @Test
    void testSetJob() {
        JobModel jobModel = mock(JobModel.class);

        JobApplication jobApplication = new JobApplication();
        jobApplication.setJob(jobModel);
        assertEquals(jobModel, jobApplication.getJob());
    }

}
