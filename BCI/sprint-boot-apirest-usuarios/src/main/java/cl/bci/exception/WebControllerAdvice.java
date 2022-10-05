package cl.bci.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class WebControllerAdvice extends ResponseEntityExceptionHandler {

  public static final String GENERAL_ERROR_MESSAGE = "mensaje de error general";

  @ExceptionHandler(value = KunturApiException.class)
  ResponseEntity<ErrorResponse> handleRestTemplateException(KunturApiException ex, HttpServletRequest request) {
    log.error("An error happened while calling API: {}", ex.getError());
    String message = ex.getError().split(",")[2].split(":")[1].replaceAll("\"", "");
    return new ResponseEntity<>(new ErrorResponse(ex.getStatusCode(), message.isEmpty()?GENERAL_ERROR_MESSAGE:message, request.getRequestURI()), ex.getStatusCode());
  }

  @ExceptionHandler(value = KunturGeneralException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResponse resourceGeneralException(KunturGeneralException ex, HttpServletRequest request) {
    log.error("An error happened while calling API: {}", ex.getMessage());
    return new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getRequestURI());
  }

}
