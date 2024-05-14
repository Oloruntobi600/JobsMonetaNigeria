package com.jobsmonetanigeria.dao;

import com.jobsmonetanigeria.Model.JobModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class JobDaoImpl implements JobDao {
    private final EntityManager entityManager;
    @Autowired

    public JobDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public void save(JobModel jobModel) {
        entityManager.merge(jobModel);

    }

}
