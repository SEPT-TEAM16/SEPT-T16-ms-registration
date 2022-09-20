package com.rmit.sept.msregistration.unit.service;

import com.rmit.sept.msregistration.model.User;
import com.rmit.sept.msregistration.repository.UserRepository;
import com.rmit.sept.msregistration.service.RegistrationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

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
    private UserRepository repository;
    
    @Test
    public void getUserId_validRequest_returnSuccess() {

        User userDetails = User.builder()
                .userId(1)
                .build();

        when(repository.findById(1)).thenReturn(Optional.of(userDetails));

        Optional<User> result = service.getExistingUserDetails(1);

        assertThat(result.get().getUserId(), is(1));
        verify(repository).findById(1);
    }
}
