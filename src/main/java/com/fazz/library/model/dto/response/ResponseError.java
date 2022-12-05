package com.fazz.library.model.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseError {
  private Integer status;
  private LocalDateTime timestamp;
  private String message;
  private Object error;
}
