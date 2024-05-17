package com.jobsmonetanigeria.Controller;

import com.jobsmonetanigeria.Model.JobModel;
import com.jobsmonetanigeria.Model.Users;
import com.jobsmonetanigeria.Service.JobServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SearchController {


    @Autowired
    private JobServiceImpl jobServiceimpl;

    @GetMapping("/search_results.html")
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new Users());
        return "search_results";
    }

    @PostMapping("/searchJobs")
    public String registerUser(@RequestParam(required = false) String keyword,
                               @RequestParam(required = false) String location,
                               @RequestParam(required = false) String category,
                               Model model) {
        List<JobModel> searchJobs = jobServiceimpl.searchJobs(keyword, location, category);
        model.addAttribute("newJobs", searchJobs);
        return "redirect:/searchJobs";
    }

}
