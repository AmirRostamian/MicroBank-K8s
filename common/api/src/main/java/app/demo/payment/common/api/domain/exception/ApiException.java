package app.demo.payment.common.api.domain.exception;

public abstract class ApiException extends RuntimeException {

    protected ApiException(String message) {
        super(message);
    }

    public abstract String getType();
}
