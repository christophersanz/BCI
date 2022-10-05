package cl.bci.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class KunturApiException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  private final HttpStatus statusCode;
  private final String error;

  public KunturApiException(HttpStatus statusCode, String error) {
    this.statusCode = statusCode;
    this.error = error;
  }

}
