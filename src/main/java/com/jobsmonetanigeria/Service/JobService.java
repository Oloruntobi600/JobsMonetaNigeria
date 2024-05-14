package com.jobsmonetanigeria.Service;

import com.jobsmonetanigeria.Model.JobModel;

import java.util.List;

public interface JobService {
    List<JobModel> getAllJobsByLogin(String login);
    void save(JobModel jobModel);
    JobModel findByTitleAndDelete(String title);
    void edit(JobModel jobs);
    void delete(String title);
}
