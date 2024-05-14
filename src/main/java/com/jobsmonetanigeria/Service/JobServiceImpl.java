
package com.jobsmonetanigeria.Service;

import com.jobsmonetanigeria.Model.JobModel;
import com.jobsmonetanigeria.Repository.JobRepository;
import com.jobsmonetanigeria.dao.JobDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final JobDao jobDao;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository, JobDao jobDao) {
        this.jobRepository = jobRepository;
        this.jobDao = jobDao;
    }

    @Override
    public List<JobModel> getAllJobsByLogin(String login) {
        if (login != null) {
            return jobRepository.findAll();
        }

        // Example filtering condition, update as needed
        return jobRepository.findByYearGreaterThan(1951);
    }


    @Override
    public void save(JobModel jobModel) {
        JobModel jobmodel = new JobModel();
        jobmodel.setTitle(jobmodel.getTitle());
        jobmodel.setYear(jobModel.getYear());
        jobmodel.setDescription(jobmodel.getDescription());
        jobDao.save(jobModel);
    }

    @Override
    public JobModel findByTitleAndDelete(String title) {
        JobModel jobModel = jobRepository.findByTitle(title)
                .orElseThrow(() -> new IllegalArgumentException("Job not found"));
        jobRepository.delete(jobModel);
        return jobModel;
    }
    @Override
    public void edit(JobModel jobs) {
        save(jobs);
    }

    @Override
    public void delete(String title) {
        findByTitleAndDelete(title);
    }

}