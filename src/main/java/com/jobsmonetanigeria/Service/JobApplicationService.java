package com.jobsmonetanigeria.Service;

public interface JobApplicationService {
    String applyForJob(String jobId);

    String applyForJob(String jobDetails, String applicantName);
}
