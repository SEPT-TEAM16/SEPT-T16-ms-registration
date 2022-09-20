package com.rmit.sept.msregistration.unit.exception;

import com.rmit.sept.msregistration.exception.ErrorModel;
import com.rmit.sept.msregistration.exception.GlobalExceptionHandler;
import com.rmit.sept.msregistration.exception.UserIdException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.RollbackException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import static org.mockito.Mockito.mock;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler handler;

    @Mock
    private HttpServletRequest request;

    @Test
    public void handleInvalidRequest_MissingParameterException() {
        // given I have mocked the exception class behaviour
        RollbackException exception = mock(RollbackException.class);

        // when the exception is called in the global exception handler
        // I want to store the response in the error model
        ResponseEntity<ErrorModel> errorResponse = handler.handleMissingParameter(exception, request);

        // then ensure the error response is returning the correct fields declared in the global
        // exception handler
        assertAll("errorResponse",
                () -> {
            // it is asserting that the expected status should match the actual status of "BAD REQUEST"
            assertThat(errorResponse.getStatusCode(), is(HttpStatus.BAD_REQUEST));
            assertNotNull(errorResponse.getBody());
            ErrorModel body = errorResponse.getBody();
            // it is asserting that the expected error details should match the custom error message
            assertAll("body",
                    () -> assertThat(body.getErrorDetails(), is("Request has failed due to invalid method argument, please check your json response body"))

            );
                }
        );
    }

    @Test
    public void handleInvalidRequest_ConstraintViolation() {

        ConstraintViolationException exception = mock(ConstraintViolationException.class);

        ResponseEntity<ErrorModel> errorResponse = handler.handleConstraintViolation(exception, request);
        assertAll("errorResponse",
                () -> {
                    assertThat(errorResponse.getStatusCode(), is(HttpStatus.BAD_REQUEST));
                    assertNotNull(errorResponse.getBody());
                    ErrorModel body = errorResponse.getBody();
                    assertAll("body",
                            () -> assertThat(body.getErrorDetails(), is("Request has failed due to duplicate attribute, please check your json response body"))

                    );
                }
        );
    }

    @Test
    public void handleInvalidRequest_UserId() {
        // given I have mocked the exception class behaviour
        UserIdException exception = mock(UserIdException.class);

        // when the exception is called in the global exception handler
        // I want to store the response in the error model
        ResponseEntity<ErrorModel> errorResponse = handler.handleInvalidUserId(exception, request);

        // then ensure the error response is returning the correct fields declared in the global
        // exception handler
        assertAll("errorResponse",
                () -> {
                    // it is asserting that the expected HTTP status should match the actual status of "NOT FOUND"
                    assertThat(errorResponse.getStatusCode(), is(HttpStatus.NOT_FOUND));
                    assertNotNull(errorResponse.getBody());
                    ErrorModel body = errorResponse.getBody();
                    // it is asserting that the expected error details should match the custom error message
                    assertAll("body",
                            () -> assertThat(body.getErrorDetails(), is("Request has failed due to invalid request, please check your json input"))

                    );
                }
        );
    }


}
