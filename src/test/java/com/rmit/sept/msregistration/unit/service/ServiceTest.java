package com.rmit.sept.msregistration.unit.service;

import com.rmit.sept.msregistration.model.Doctor;
import com.rmit.sept.msregistration.model.User;
import com.rmit.sept.msregistration.repository.DoctorRepository;
import com.rmit.sept.msregistration.repository.UserRepository;
import com.rmit.sept.msregistration.service.RegistrationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import javax.print.Doc;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ServiceTest {
    
    @InjectMocks
    private RegistrationService service;
    
    @Mock
    private DoctorRepository repository;
    
    @Test
    public void getDoctorUserId_validRequest_returnSuccess() {

        // given I have used lombok builder to build the user entity class
        // and passed through the user id
        Doctor userDetails = Doctor.builder()
                .userId(48)
                .build();

        when(repository.findById(48)).thenReturn(Optional.of(userDetails));

        Optional<Doctor> result = service.getExistingDoctorDetails(48);

        assertThat(result.get().getUserId(), is(48));
        verify(repository).findById(48);
    }
}
