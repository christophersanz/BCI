package cl.bci.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  private final HttpStatus statusCode;
  private final String error;

  public ApiException(HttpStatus statusCode, String error) {
    this.statusCode = statusCode;
    this.error = error;
  }

}
