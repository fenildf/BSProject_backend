package bsproject.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author yusong wu
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such word")
public class WordNotFoundException extends RuntimeException {
}
