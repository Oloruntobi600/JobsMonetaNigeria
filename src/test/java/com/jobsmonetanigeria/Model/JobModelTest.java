//package com.jobsmonetanigeria.Model;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Date;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@SpringBootTest
//class JobModelTest {
//
//    @Test
//    void gettersAndSettersTest() {
//        JobModel jobModel = new JobModel();
//
//
//        jobModel.setId(1L);
//        jobModel.setJobDetails("Job Details");
//        jobModel.setDatePosted(20220520);
//        jobModel.setCompanyName("Company Name");
//        jobModel.setLocation("Location");
//        jobModel.setRequirements("Requirements");
//        jobModel.setDescription("Description");
//        Date createdAt = new Date();
//        jobModel.setCreatedAt(createdAt);
//        jobModel.setImageFileName("ImageFileName");
//
//        assertEquals(1L, jobModel.getId());
//        assertEquals("Job Details", jobModel.getJobDetails());
//        assertEquals(20220520, jobModel.getDatePosted());
//        assertEquals("Company Name", jobModel.getCompanyName());
//        assertEquals("Location", jobModel.getLocation());
//        assertEquals("Requirements", jobModel.getRequirements());
//        assertEquals("Description", jobModel.getDescription());
//        assertEquals(createdAt, jobModel.getCreatedAt());
//        assertEquals("ImageFileName", jobModel.getImageFileName());
//    }
//
//    @Test
//    void userGetterAndSetterTest() {
//
//        JobModel jobModel = new JobModel();
//        Users user = new Users();
//        user.setId(1L);
//        jobModel.setUser(user);
//
//        assertNotNull(jobModel.getUser());
//        assertEquals(1L, jobModel.getUser().getId());
//    }
//}
