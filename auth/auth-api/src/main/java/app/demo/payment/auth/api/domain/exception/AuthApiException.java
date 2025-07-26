package app.demo.payment.auth.api.domain.exception;

import app.demo.payment.common.api.domain.exception.ApiException;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type",
        include = JsonTypeInfo.As.EXISTING_PROPERTY
)
@JsonSubTypes({
        @JsonSubTypes.Type(
                name = InvalidCredentialsException.TYPE,
                value = InvalidCredentialsException.class
        ),
        @JsonSubTypes.Type(
                name = UserAlreadyExistsException.TYPE,
                value = UserAlreadyExistsException.class
        ),
})
public abstract class AuthApiException extends ApiException implements Serializable {

    protected AuthApiException(String message) {
        super(message);
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
    public abstract String getType();

}
