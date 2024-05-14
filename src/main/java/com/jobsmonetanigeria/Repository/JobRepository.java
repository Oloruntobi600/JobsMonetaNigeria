package com.jobsmonetanigeria.Repository;

import com.jobsmonetanigeria.Model.JobModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<JobModel, Long> {
    @Query("SELECT j FROM JobModel j WHERE j.Title = :title")
    Optional<JobModel> findByTitle(@Param("title") String title);
    List<JobModel> findByYearGreaterThan(int year);
}
