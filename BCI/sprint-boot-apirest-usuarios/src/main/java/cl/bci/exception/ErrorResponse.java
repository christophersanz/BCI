package cl.bci.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private String timestamp;
  private int status;
  private String error;
  private String message;
  private String path;

  public ErrorResponse(HttpStatus httpStatus, String message, String path) {
    this.timestamp = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now());
    this.status = httpStatus.value();
    this.error = httpStatus.getReasonPhrase();
    this.message = message;
    this.path = path;
  }

}
