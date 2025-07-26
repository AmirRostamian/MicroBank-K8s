package app.demo.payment.user.api.domain.exception;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import app.demo.payment.common.api.domain.exception.ApiException;

import java.io.Serializable;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type",
        include = JsonTypeInfo.As.EXISTING_PROPERTY
)
@JsonSubTypes({
        @JsonSubTypes.Type(
                name = UserNotFoundException.TYPE,
                value = UserNotFoundException.class
        ),
})
public abstract class UserApiException extends ApiException implements Serializable {

    protected UserApiException(String message) {
        super(message);
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
    public abstract String getType();

}
