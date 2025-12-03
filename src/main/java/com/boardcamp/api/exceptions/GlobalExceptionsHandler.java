package com.boardcamp.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler({ GameConflictException.class })
    public ResponseEntity<String> handleGameConflictException(GameConflictException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ NegativeValueException.class })
    public ResponseEntity<String> handleNegativeValueException(NegativeValueException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ ConflictException.class })
    public ResponseEntity<String> handleConflictException(ConflictException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ EmptyFieldException.class })
    public ResponseEntity<String> handleEmptyFieldException(EmptyFieldException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ ResourceNotFoundException.class })
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ InvalidGameIdException.class })
    public ResponseEntity<String> handleInvalidGameIdException(InvalidGameIdException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ UnprocessableEntityException.class })
    public ResponseEntity<String> handleUnprocessableEntityException(UnprocessableEntityException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ InvalidCpfException.class })
    public ResponseEntity<String> handleIllegalArgumentException(InvalidCpfException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
