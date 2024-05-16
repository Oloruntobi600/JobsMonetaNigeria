package com.jobsmonetanigeria.Controller;

import com.jobsmonetanigeria.Service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JobApplicationController {
    private final JobApplicationService jobApplicationService;

    @Autowired
    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

        @GetMapping("/jobs/apply/{jobDetails}")
        public String applyForJob(@PathVariable String jobDetails, Model model) {
            String applicationStatus = jobApplicationService.applyForJob(jobDetails);
            model.addAttribute("applicationStatus", applicationStatus);
            return "application_confirmation";
        }
}
