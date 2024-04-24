package com.example.Project06.Dto;

import com.example.Project06.Entity.User;
import com.example.Project06.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LoginDtoTest {

        @InjectMocks
        private LoginDto loginDto;

        @Mock
        private LoginRequest loginRequest;

        @Test
    public void testLogin()
        {
            String username;
            username = loginDto.setEmail("riya@gmail.com");
            User user = Mockito.when(loginRequest.getUsername()).thenReturn(user);

            assertEquals(user, );
        }

}