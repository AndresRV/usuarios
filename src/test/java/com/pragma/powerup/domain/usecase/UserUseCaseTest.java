package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.exception.InvalidAdultAge;
import com.pragma.powerup.domain.exception.InvalidEmailException;
import com.pragma.powerup.domain.exception.InvalidPhoneNumber;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {
    @Mock
    private IUserPersistencePort userPersistencePort;

    @InjectMocks
    private UserUseCase userUseCase;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(
                1L,
                "xx",
                "xx",
                123,
                "+123456789123",
                LocalDate.of(2006, 1, 15),
                "x@x.xx",
                "xx",
                1L
        );
    }

    @Test
    void saveUser() {
        userUseCase.saveUser(user);

        verify(userPersistencePort).saveUser(user);
    }

    @Test
    void saveUserInvalidEmail() {
        user.setEmail("@.x");

        Exception exception = Assertions.assertThrows(InvalidEmailException.class, () -> userUseCase.saveUser(user));

        Assertions.assertEquals("Invalid email", exception.getMessage());
    }

    @Test
    void saveUserInvalidPhoneNumber() {
        user.setPhoneNumber("+123");

        Exception exception = Assertions.assertThrows(InvalidPhoneNumber.class, () -> userUseCase.saveUser(user));

        Assertions.assertEquals("Invalid number phone", exception.getMessage());
    }

    @Test
    void saveUserInvalidAge() {
        user.setBirthDate(LocalDate.now().plusDays(1));

        Exception exception = Assertions.assertThrows(InvalidAdultAge.class, () -> userUseCase.saveUser(user));

        Assertions.assertEquals("Invalid age", exception.getMessage());
    }
}