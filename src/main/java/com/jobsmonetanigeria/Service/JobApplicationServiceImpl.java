package com.jobsmonetanigeria.Service;

import com.jobsmonetanigeria.Model.JobApplication;
import com.jobsmonetanigeria.Model.JobModel;
import com.jobsmonetanigeria.Repository.JobApplicationRepository;
import com.jobsmonetanigeria.Repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobRepository jobRepository;
    private final JobApplicationRepository jobApplicationRepository;

    @Autowired
    public JobApplicationServiceImpl(JobRepository jobRepository, JobApplicationRepository jobApplicationRepository) {
        this.jobRepository = jobRepository;
        this.jobApplicationRepository = jobApplicationRepository;
    }

    @Override
    public String applyForJob(String jobId) {

        return null;
    }

    @Override
    public String applyForJob(String jobDetails, String applicantName) {
        List<JobModel> jobs = jobRepository.findByJobDetails(jobDetails);

        if (!jobs.isEmpty()) {
            for (JobModel job : jobs) {
                System.out.println("Applying for job: " + job.getJobDetails());
                System.out.println("Company: " + job.getCompanyName());
                System.out.println("Location: " + job.getLocation());
                System.out.println("Requirements: " + job.getRequirements());

                JobApplication jobApplication = new JobApplication(job, applicantName);
                jobApplicationRepository.save(jobApplication);
            }
            return "Applied for job: " + jobDetails + " by " + applicantName;
        } else {
            return "Job not found!";
        }
    }
}