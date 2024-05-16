package com.jobsmonetanigeria.Repository;

import com.jobsmonetanigeria.Model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    // You can add custom query methods here if needed
}
