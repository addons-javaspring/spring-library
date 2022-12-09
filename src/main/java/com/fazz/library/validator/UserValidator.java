package com.fazz.library.validator;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fazz.library.exception.custom.NotFoundException;
import com.fazz.library.exception.custom.UnauthorizedException;
import com.fazz.library.model.entity.User;

@Service
public class UserValidator {
  public void validateUserFoundRegister(Optional<User> userFind) throws Exception {
    if (userFind.isPresent()) {
      throw new Exception("User is found! Please sign in!");
    }
  }

  public void validateUserNotFound(Optional<User> userFind) throws Exception {
    if (userFind.isEmpty()) {
      throw new NotFoundException("User is not found! Please register!");
    }
  }

  public void validateWrongPassword(String rawPassword, String dbPassword) throws Exception {
    if (!dbPassword.equals(rawPassword)) {
      throw new UnauthorizedException("Unauthorized! Wrong password!");
    }
  }
}
