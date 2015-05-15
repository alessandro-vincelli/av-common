package it.av.common.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenericException extends RuntimeException {

    private static Logger log = LogManager.getLogger();

    public GenericException() {
    }

    public GenericException(String s) {
        super(s);
        log.error(s);
    }

    public GenericException(String s, Throwable throwable) {
        super(s, throwable);
        log.error(s);
    }

    public GenericException(Throwable throwable) {
        super(throwable);
        log.error("AV Error", throwable);
    }
}
