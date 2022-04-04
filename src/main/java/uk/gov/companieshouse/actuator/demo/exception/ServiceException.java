package uk.gov.companieshouse.actuator.demo.exception;

public class ServiceException extends Exception {

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
