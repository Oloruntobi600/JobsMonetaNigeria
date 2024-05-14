package com.jobsmonetanigeria.Controller;

import com.jobsmonetanigeria.Model.JobModel;
import com.jobsmonetanigeria.Service.JobService;
import com.jobsmonetanigeria.Service.JobServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {


    @Autowired
    private JobServiceImpl jobServiceimpl;

    @GetMapping("/searchJobs")
    public String searchJobs(@RequestParam(required = false) String keyword,
                             @RequestParam(required = false) String location,
                             @RequestParam(required = false) String category,
                             Model model) {
        List<JobModel> searchResults = jobServiceimpl.searchJobs(keyword, location, category);
        model.addAttribute("jobs", searchResults);
        return "search_results";
    }
}
