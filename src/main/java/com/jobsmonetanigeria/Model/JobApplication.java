package com.jobsmonetanigeria.Model;

import org.apache.catalina.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "job_applications")
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private JobModel job;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Assuming you have a User entity with a corresponding table
    private Users user;

    @Column(name = "applicant_name", nullable = false)
    private String applicantName;

    @Column(name = "application_date", nullable = false)
    private Date applicationDate;

    // Constructors, getters, and setters
    public JobApplication() {
        this.applicationDate = new Date(); // Set the current date as the default application date
    }

    public JobApplication(JobModel job, String applicantName) {
        this.job = job;
        this.applicantName = applicantName;
        this.applicationDate = new Date(); // Set the current date as the default application date
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JobModel getJob() {
        return job;
    }

    public void setJob(JobModel job) {
        this.job = job;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }
}
