package co.ubl.bank.ExceptionHandler;

import org.springframework.http.HttpStatus;

public class CustomResponse extends RuntimeException {

    private HttpStatus httpStatus;
    private Object data;

    public CustomResponse(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public CustomResponse(String message, HttpStatus httpStatus, Object data) {
        super(message);
        this.httpStatus = httpStatus;
        this.data = data;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Object getData() {
        return data;
    }
}

