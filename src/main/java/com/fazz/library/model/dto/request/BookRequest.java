package com.fazz.library.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {
  @NotBlank(message = "Title is required")
  private String judul;

  @NotBlank(message = "Author is required")
  private String penulis;

  @NotNull(message = "Publisher is required")
  private Integer idPenerbit;

  private String sinopsis;
}
