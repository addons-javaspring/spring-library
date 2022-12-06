package com.fazz.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fazz.library.model.dto.request.UserRequest;
import com.fazz.library.model.dto.response.ResponseData;
import com.fazz.library.service.auth.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
  @Autowired
  private AuthService authService;

  private ResponseData responseData;

  @PostMapping("/register")
  public ResponseEntity<ResponseData> register(@RequestBody @Valid UserRequest request) throws Exception {
    responseData = authService.registerService(request);
    return ResponseEntity.status(responseData.getStatus()).body(responseData);
  }

  @PostMapping()
  public ResponseEntity<ResponseData> login(@RequestBody @Valid UserRequest request) throws Exception {
    responseData = authService.loginService(request);
    return ResponseEntity.status(responseData.getStatus()).body(responseData);
  }
}
