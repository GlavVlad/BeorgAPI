package ru.beorg.test.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.beorg.test.exception.PriceException;

/**
 * Created by Vlad on 05.02.2017.
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String FAILURE = "Error_type";
    private static final String ERROR_CODE = "Error_code";
    private static final String ERROR_MSG = "Error_msg";
    private final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler({PriceException.class})
    public ResponseEntity<Object> handlePrice(PriceException ex, WebRequest request) {
        log.error("Error {} during processing request {}", ex, request);
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .header(FAILURE, ex.getCode().name())
                .header(ERROR_CODE, String.valueOf(ex.getCode().getCode()))
                .header(ERROR_MSG, ex.getCode().getMsg())
                .body(null);
    }
}
