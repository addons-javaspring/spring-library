package com.fazz.library.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fazz.library.model.dto.response.ResponseData;

@RestController
public class GreetingController {
  private ResponseData responseData;

  // request params / query params
  @GetMapping("/hello")
  // /hello?name=femil
  public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
    return String.format("Hello %s!", name);
  }

  // path variable
  @GetMapping("/hello/{nama}/{id}")
  // /hello/femil
  public String hello2(@PathVariable("nama") String name, @PathVariable("id") Long id) {
    return String.format("Hello %s dari path variable!", name);
  }

  // request body
  @PostMapping("/hello-buddy")
  // /hello
  public ResponseEntity<ResponseData> hello3(@RequestBody String name) {
    responseData = new ResponseData(HttpStatus.OK.value(), "success",
        String.format("Hello %s dari request body!", name));
    return ResponseEntity.status(HttpStatus.OK).body(responseData);
  }

}
