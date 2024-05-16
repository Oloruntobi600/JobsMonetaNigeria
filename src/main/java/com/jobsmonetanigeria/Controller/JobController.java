
package com.jobsmonetanigeria.Controller;

import com.jobsmonetanigeria.Model.JobModel;
import com.jobsmonetanigeria.Service.JobServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {

    public static final String USER_LOGIN = "userLogin";
    private final JobServiceImpl jobService;


    @GetMapping
    public String getJobPage(Model model,
                             @AuthenticationPrincipal UserDetails userDetails) {

        String username = userDetails.getUsername();
        System.out.println(userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());

        model.addAttribute(USER_LOGIN, username);

        List<JobModel> jobs = jobService.getAllJobsByLogin(username);

        model.addAttribute("userJobs", jobs);
        return "job_page";
    }

    @GetMapping("/create")
    public String getCreateJobPage(Model model) {
        model.addAttribute("newJob", new JobModel());
        return "create_job_page";
    }

    @PostMapping("/create_job")
    public String createJob(@ModelAttribute("newJob") JobModel jobModel) {
//        jobService.save(jobModel);
//        return "redirect:/jobs";
        if (jobModel.getCompanyName() != null && !jobModel.getCompanyName().isEmpty()) {
            jobService.save(jobModel);
        }
        return "redirect:/jobs";
    }

    @GetMapping("/edit/{title}")
    @PreAuthorize("hasRole('ADMIN')")
    public String getEditJobPage(Model model, @PathVariable String title) {
        JobModel byTitle = jobService.findByTitle(title);
        model.addAttribute("jobToEdit", byTitle);
        return "edit_job_page";
    }

    @PostMapping("/editJob")
    public String editJob(@ModelAttribute JobModel job) {
        jobService.edit(job);
        return "redirect:/jobs";
    }

    @GetMapping("/delete/{title}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable String title) {
        jobService.delete(title);
        return "redirect:/jobs";
    }

    @PostMapping("/browse")
    public String browseJob() {
        return "redirect:/browse-job";
    }

    @GetMapping("/browse-job")
    public String browseJobPage() {
        return "browse-job";
    }



}
