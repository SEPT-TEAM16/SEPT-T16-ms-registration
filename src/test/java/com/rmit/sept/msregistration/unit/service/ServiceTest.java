package com.rmit.sept.msregistration.unit.service;

import com.rmit.sept.msregistration.model.User;
import com.rmit.sept.msregistration.repository.UserRepository;
import com.rmit.sept.msregistration.service.UserService;
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
    private UserService service;
    
    @Mock
    private UserRepository repository;
    
    @Test
    public void getUserEmail_validRequest_returnSuccess() throws Exception {

        // given I have used lombok builder to build the user entity class
        // and passed through the user id
        User userDetails = User.builder()
                .email("dangao233@gmail.com")
                .build();

        // when I call the repository with the method: 'findById'
        // it should return the created user entity class
        when(repository.findByEmail("dangao233@gmail.com")).thenReturn(userDetails);

        // then service method is stored in the user result
       User result = service.getExistingUserDetails("dangao233@gmail.com");

        // it is asserting that the passed through user id in result
        // is actually the id 48
        assertThat(result.getEmail(), is("dangao233@gmail.com"));
        // it is verifying the method in the repo has been called
        verify(repository).findByEmail("dangao233@gmail.com");
    }
}
