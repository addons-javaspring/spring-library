package com.fazz.library.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
  @Email(message = "Input must be an email format!")
  @NotBlank(message = "Email is required!")
  private String email;

  @NotBlank(message = "Password is required")
  @Size(min = 8, message = "Password must be minimum 8 characters!")
  private String password;
}
