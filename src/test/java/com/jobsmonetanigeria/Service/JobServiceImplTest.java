package com.jobsmonetanigeria.Service;

import com.jobsmonetanigeria.Model.JobModel;
import com.jobsmonetanigeria.Repository.JobRepository;
import com.jobsmonetanigeria.dao.JobDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JobServiceImplTest {

    @Mock
    private JobRepository jobRepository;

    @Mock
    private JobDao jobDao;

    @Mock
    private EntityManager entityManager;

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Mock
    private CriteriaQuery<JobModel> criteriaQuery;

    @Mock
    private Root<JobModel> root;

    @Mock
    private Predicate keywordPredicate;

    @Mock
    private Predicate locationPredicate;

    @Mock
    private Predicate categoryPredicate;

    @Mock
    private Predicate searchPredicate;

    @Mock
    private TypedQuery<JobModel> typedQuery;

    @InjectMocks
    private JobServiceImpl jobService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock the behavior of entityManager and related objects
        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(JobModel.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(JobModel.class)).thenReturn(root);
    }

    @Test
    void testFindByTitle() {
        JobModel job = new JobModel();
        job.setJobDetails("Software Developer");

        when(jobRepository.findByTitle("Software Developer")).thenReturn(Optional.of(job));

        JobModel result = jobService.findByTitle("Software Developer");

        assertEquals("Software Developer", result.getJobDetails());
        verify(jobRepository, times(1)).findByTitle("Software Developer");
    }

    @Test
    void testFindByTitleNotFound() {
        when(jobRepository.findByTitle("Non Existent Job")).thenReturn(Optional.empty());

        JobModel result = jobService.findByTitle("Non Existent Job");

        assertNull(result);
        verify(jobRepository, times(1)).findByTitle("Non Existent Job");
    }

    @Test
    void testGetAllJobsByLogin() {
        JobModel job1 = new JobModel();
        JobModel job2 = new JobModel();

        when(jobRepository.findAll()).thenReturn(Arrays.asList(job1, job2));

        List<JobModel> result = jobService.getAllJobsByLogin("someLogin");

        assertEquals(2, result.size());
        verify(jobRepository, times(1)).findAll();
    }

    @Test
    void testGetAllJobsByLoginNull() {
        JobModel job1 = new JobModel();
        JobModel job2 = new JobModel();

        when(jobRepository.findByYearGreaterThan(1951)).thenReturn(Arrays.asList(job1, job2));

        List<JobModel> result = jobService.getAllJobsByLogin(null);

        assertEquals(2, result.size());
        verify(jobRepository, times(1)).findByYearGreaterThan(1951);
    }

    @Test
    void testSave() {
        JobModel jobModel = new JobModel();
        jobModel.setJobDetails("Details");
        jobModel.setCompanyName("Company");
        jobModel.setLocation("Location");
        jobModel.setRequirements("Requirements");
        jobModel.setDescription("Description");

        jobService.save(jobModel);

        verify(jobDao, times(1)).save(jobModel);
    }

    @Test
    void testSaveWithEmptyJobDetails() {
        JobModel jobModel = new JobModel();
        jobService.save(jobModel);

        verify(jobDao, times(1)).save(jobModel);
    }

    @Test
    void testFindByTitleAndDelete() {
        JobModel job = new JobModel();
        job.setJobDetails("Software Developer");

        when(jobRepository.findByTitle("Software Developer")).thenReturn(Optional.of(job));

        JobModel result = jobService.findByTitleAndDelete("Software Developer");

        assertEquals("Software Developer", result.getJobDetails());
        verify(jobRepository, times(1)).findByTitle("Software Developer");
        verify(jobRepository, times(1)).delete(job);
    }

    @Test
    void testFindByTitleAndDeleteNotFound() {
        when(jobRepository.findByTitle("Non Existent Job")).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> jobService.findByTitleAndDelete("Non Existent Job"));
        assertEquals("Job not found", exception.getMessage());
        verify(jobRepository, times(1)).findByTitle("Non Existent Job");
    }

    @Test
    void testEdit() {
        JobModel job = new JobModel();
        jobService.edit(job);
        verify(jobDao, times(1)).save(job);
    }

    @Test
    void testDelete() {
        JobModel job = new JobModel();
        job.setJobDetails("Software Developer");

        when(jobRepository.findByTitle("Software Developer")).thenReturn(Optional.of(job));

        jobService.delete("Software Developer");

        verify(jobRepository, times(1)).findByTitle("Software Developer");
        verify(jobRepository, times(1)).delete(job);
    }

}
