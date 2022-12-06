package com.fazz.library.service.auth;

import com.fazz.library.model.dto.request.UserRequest;
import com.fazz.library.model.dto.response.ResponseData;

public interface AuthService {
  ResponseData loginService(UserRequest request) throws Exception;

  ResponseData registerService(UserRequest request) throws Exception;
}
