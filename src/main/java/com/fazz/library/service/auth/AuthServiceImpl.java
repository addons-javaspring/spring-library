package com.fazz.library.service.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fazz.library.model.dto.request.UserRequest;
import com.fazz.library.model.dto.response.ResponseData;
import com.fazz.library.model.entity.User;
import com.fazz.library.repository.UserRepository;
import com.fazz.library.validator.UserValidator;

@Service
public class AuthServiceImpl implements AuthService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserValidator userValidator;

  private ResponseData responseData;

  @Override
  public ResponseData loginService(UserRequest request) throws Exception {
    // TODO Auto-generated method stub
    Optional<User> userFind = userRepository.findByEmail(request.getEmail());

    // validasi
    userValidator.validateUserNotFound(userFind);

    User user = userFind.get();

    // validasi untuk pengecekan request dengan db
    userValidator.validateWrongPassword(request.getPassword(), user.getPassword());

    responseData = new ResponseData(200, "success", user);
    return responseData;
  }

  @Override
  public ResponseData registerService(UserRequest request) throws Exception {
    // TODO Auto-generated method stub
    Optional<User> userFind = userRepository.findByEmail(request.getEmail());

    // validasi
    userValidator.validateUserFoundRegister(userFind);

    User user = new User(request.getEmail(), request.getPassword());

    // save to db
    userRepository.save(user);

    // response data
    responseData = new ResponseData(201, "success", user);
    return responseData;
  }

}
