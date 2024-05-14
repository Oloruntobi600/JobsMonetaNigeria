package com.jobsmonetanigeria.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/Error";

    @RequestMapping(value = PATH)
    public String handleError(HttpServletRequest request) {
        // used to get the error status code
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            // used to handle different error status codes
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "Error/404"; // Return a custom 404 page
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "Error/500"; // Return a custom 500 page
            }
        }

        return "Error/generic";
    }


}

