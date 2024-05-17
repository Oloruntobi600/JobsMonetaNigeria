package com.jobsmonetanigeria.dao;

import com.jobsmonetanigeria.Model.JobModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Date;

import static org.mockito.Mockito.*;

class JobDaoImplTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private JobDaoImpl jobDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        JobModel jobModel = new JobModel();


        EntityTransaction transaction = mock(EntityTransaction.class);
        when(entityManager.getTransaction()).thenReturn(transaction);

        jobDao.save(jobModel);

        verify(entityManager, times(1)).merge(jobModel);
    }
}
