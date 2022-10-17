package com.rmit.sept.msregistration.unit.controller;

import com.rmit.sept.msregistration.constants.AppRole;
import com.rmit.sept.msregistration.controller.RegistrationController;
import com.rmit.sept.msregistration.model.User;
import com.rmit.sept.msregistration.service.DoctorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpHeaders;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ControllerTest {

    @InjectMocks
    private RegistrationController controller;

    @Mock
    private DoctorService service;

    private HttpHeaders httpHeaders;


    @BeforeEach
    public void setUp() throws Exception{
        httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "APPLICATION/JSON");
    }

    @Test
    public void postResponseBody_validRequest_returnSuccess() throws Exception {

        // given I have used lombok builder to build the user entity class
        // and passed through a few attributes
        User userDetails = User.builder()
                .email("johnford23@gmail.com")
                .firstName("John")
                .lastName("Ford")
                .role(AppRole.DOCTOR)
                .build();
        // when calling the service layer
        // I pass through the created user model
        controller.registerNewUser(userDetails);
        // then I verify the service was called once by passing through any argument
        verify(service, times(1)).saveNewDoctorDetails(any());
    }
}
