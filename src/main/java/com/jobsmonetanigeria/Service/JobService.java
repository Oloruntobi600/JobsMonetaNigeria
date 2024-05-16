package com.jobsmonetanigeria.Service;

import com.jobsmonetanigeria.Model.JobModel;

import java.util.List;

public interface JobService {
    List<JobModel> getAllJobsByLogin(String login);
    List<JobModel> searchJobs(String keyword, String location, String category);
    void save(JobModel jobModel);
    JobModel findByTitleAndDelete(String title);
    JobModel findByTitle(String title);
    void edit(JobModel jobs);
    void delete(String title);


}
