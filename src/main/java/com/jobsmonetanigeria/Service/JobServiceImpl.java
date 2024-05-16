
package com.jobsmonetanigeria.Service;

import com.jobsmonetanigeria.Model.JobModel;
import com.jobsmonetanigeria.Repository.JobRepository;
import com.jobsmonetanigeria.dao.JobDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
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
    public JobModel findByTitle(String title) {
        return jobRepository.findByTitle(title).orElse(null);
    }

    @Override
    public List<JobModel> getAllJobsByLogin(String login) {
        if (login != null) {
            return jobRepository.findAll();
        }

        return jobRepository.findByYearGreaterThan(1951);
    }


    @Override
    public void save(JobModel jobModel) {
        JobModel jobmodel = new JobModel();
        jobmodel.setJobDetails(jobmodel.getJobDetails());
        jobmodel.setDatePosted(jobModel.getDatePosted());
        jobmodel.setCompanyName(jobModel.getCompanyName());
        jobmodel.setLocation(jobModel.getLocation());
        jobmodel.setRequirements(jobModel.getLocation());
        jobmodel.setDescription(jobmodel.getDescription());
        jobmodel.setCreatedAt(new Date());
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

    @Autowired
    private EntityManager entityManager;
    @Override
    public List<JobModel> searchJobs(String jobDetails, String companyName, String location) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<JobModel> criteriaQuery = criteriaBuilder.createQuery(JobModel.class);
        Root<JobModel> root = criteriaQuery.from(JobModel.class);

        // How to Create predicates based on search criteria
        Predicate keywordPredicate = criteriaBuilder.like(root.get("keywords"), "%" + jobDetails + "%");
        Predicate locationPredicate = criteriaBuilder.like(root.get("location"), "%" + companyName + "%");
        Predicate categoryPredicate = criteriaBuilder.like(root.get("category"), "%" + location + "%");

        // How to Combine predicates with AND condition
        Predicate searchPredicate = criteriaBuilder.and(keywordPredicate, locationPredicate, categoryPredicate);

        //  How to Add where clause to the query
        criteriaQuery.where(searchPredicate);

        // How to Execute the query
        TypedQuery<JobModel> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

}