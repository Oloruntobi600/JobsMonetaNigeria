package com.jobsmonetanigeria.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CustomErrorControllerTest {

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private CustomErrorController customErrorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleError_404() {
        when(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)).thenReturn(404);

        String viewName = customErrorController.handleError(request);

        assertEquals("Error/404", viewName);
    }

    @Test
    void handleError_500() {
        when(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)).thenReturn(500);

        String viewName = customErrorController.handleError(request);

        assertEquals("Error/500", viewName);
    }

    @Test
    void handleError_Other() {
        when(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)).thenReturn(400);

        String viewName = customErrorController.handleError(request);

        assertEquals("Error/generic", viewName);
    }

    @Test
    void handleError_NoStatus() {
        when(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)).thenReturn(null);

        String viewName = customErrorController.handleError(request);

        assertEquals("Error/generic", viewName);
    }

    @Test
    void handleError_NonNumericStatus() {
        when(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)).thenReturn(12);

        String viewName = customErrorController.handleError(request);

        assertEquals("Error/generic", viewName);
    }

    @Test
    void handleError_MinStatusCode() {
        when(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)).thenReturn(Integer.MIN_VALUE);

        String viewName = customErrorController.handleError(request);

        assertEquals("Error/generic", viewName);
    }

    @Test
    void handleError_MaxStatusCode() {
        when(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)).thenReturn(Integer.MAX_VALUE);

        String viewName = customErrorController.handleError(request);

        assertEquals("Error/generic", viewName);
    }

    @Test
    void handleError_HttpStatusMethodNotAllowed() {
        when(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)).thenReturn(HttpStatus.METHOD_NOT_ALLOWED.value());

        String viewName = customErrorController.handleError(request);

        assertEquals("Error/generic", viewName);
    }
    @Test
    void testErrorPath() {
        String errorPath = customErrorController.handleError(request);
        assertEquals("Error/generic", errorPath);
    }
}
