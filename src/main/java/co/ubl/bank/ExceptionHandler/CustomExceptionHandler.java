package co.ubl.bank.ExceptionHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    // Handle exception with message, HTTP code, and optionally an object or list of objects
    @ExceptionHandler(CustomResponse.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomResponse ex) {
        Object data = ex.getData(); // Can be null, single object, or a list of objects

        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                ex.getHttpStatus().value(),
                data
        );
        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }
}
