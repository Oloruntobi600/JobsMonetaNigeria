package com.jobsmonetanigeria.Repository;

import com.jobsmonetanigeria.Model.JobModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<JobModel, Long> {
    @Query("SELECT j FROM JobModel j WHERE j.jobDetails = :title")
    Optional<JobModel> findByTitle(@Param("title") String title);
    @Query("SELECT j FROM JobModel j WHERE j.datePosted > :year")
    List<JobModel> findByYearGreaterThan(@Param("year") int year);
    List<JobModel> findByJobDetails(String title);
}
