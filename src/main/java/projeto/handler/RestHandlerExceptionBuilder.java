package projeto.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import projeto.error.ResourceNotFoundException;
import projeto.error.ResourceNotFoundExceptionDetail;

import java.util.Date;

@RestControllerAdvice
public class RestHandlerExceptionBuilder {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException resourceNotFoudException) {
        ResourceNotFoundExceptionDetail resourceNotFoundDetails = ResourceNotFoundExceptionDetail.ResourceBuilder.builder()
                .title("The resource wasn't found !")
                .detail(resourceNotFoudException.getMessage())
                .moment(new Date()
                        .getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .developerMessage(resourceNotFoudException.getClass().getName()).build();

        return new ResponseEntity<>(resourceNotFoundDetails, HttpStatus.NOT_FOUND);


    }
}