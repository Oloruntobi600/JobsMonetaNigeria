package com.jobsmonetanigeria.Model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UsersTest {

//    @Test
//    void idGetterAndSetterTest() {
//        Users user = new Users();
//
//        user.setId(1L);
//        assertEquals(1L, user.getId());
//    }

    @Test
    void usernameGetterAndSetterTest() {
        Users user = new Users();

        user.setUsername("testuser");
        assertEquals("testuser", user.getUsername());
    }

    @Test
    void passwordGetterAndSetterTest() {
        Users user = new Users();

        user.setPassword("password123");
        assertEquals("password123", user.getPassword());
    }

//    @Test
//    void jobsGetterAndSetterTest() {
//        Users user = new Users();
//
//        List<JobModel> jobs = new ArrayList<>();
//        JobModel job1 = new JobModel();
//        JobModel job2 = new JobModel();
//        jobs.add(job1);
//        jobs.add(job2);
//
//        user.setJobs(jobs);
//        assertNotNull(user.getJobs());
//        assertEquals(2, user.getJobs().size());
//    }

//    @Test
//    void noArgsConstructorTest() {
//        Users user = new Users();
//        assertNotNull(user);
//    }

//    @Test
//    void equalsAndHashCodeTest() {
//        Users user1 = new Users();
//        user1.setId(1L);
//        user1.setUsername("testuser");
//        user1.setPassword("password123");
//
//        Users user2 = new Users();
//        user2.setId(1L);
//        user2.setUsername("testuser");
//        user2.setPassword("password123");
//
//
//        assertEquals(user1, user2);
//        assertEquals(user1.hashCode(), user2.hashCode());
//    }

    @Test
    void toStringTest() {
        Users user = new Users();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("password123");


        String expectedString = "Users(id=1, username=testuser, password=password123, role=null, jobs=null)";
        assertEquals(expectedString, user.toString());
    }
}
