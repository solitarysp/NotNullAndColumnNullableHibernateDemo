package org.example.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PostUserRQ {
  private String firstName;

  private String lastName;
}
