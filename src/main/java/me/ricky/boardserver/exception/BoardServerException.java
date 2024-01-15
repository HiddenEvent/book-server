package me.ricky.boardserver.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class BoardServerException extends RuntimeException {
    private HttpStatus code;
    private String msg;
}
