package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.exception.InvalidAdultAge;
import com.pragma.powerup.domain.exception.InvalidEmailException;
import com.pragma.powerup.domain.exception.InvalidPhoneNumber;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.IUserPersistencePort;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserUseCase implements IUserServicePort {
    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final String PHONE_NUMBER_REGEX = "^\\+\\d{12}$";

    private static final int ADULT_AGE = 18;

    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void saveUser(User user) {
        validateEmail(user.getEmail());
        validatePhoneNumber(user.getPhoneNumber());
        validateAdultAge(user.getBirthDate());

        userPersistencePort.saveUser(user);
    }

    @Override
    public User getUser(Long id) {
        return userPersistencePort.findById(id);
    }

    @Override
    public User getUserByDocumentNumber(Integer documentNumber) {
        return userPersistencePort.findByDocumentNumber(documentNumber);
    }

    private static void validateEmail(String email) {
        Matcher matcher = Pattern.compile(EMAIL_REGEX).matcher(email);
        if (!matcher.matches())
            throw new InvalidEmailException("Invalid email");
    }

    private static void validatePhoneNumber(String phoneNumber) {
        Matcher matcher = Pattern.compile(PHONE_NUMBER_REGEX).matcher(phoneNumber);
        if (!matcher.matches())
            throw new InvalidPhoneNumber("Invalid number phone");
    }

    private static void validateAdultAge(LocalDate birthDate) {
        Period age = Period.between(birthDate, LocalDate.now());
        if (age.getYears() < ADULT_AGE)
            throw new InvalidAdultAge("Invalid age");
    }
}
