package app.demo.payment.user.client.component;

import app.demo.payment.auth.sdk.error.ApiClientErrorDecoder;
import app.demo.payment.user.api.domain.exception.UserApiException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserClientErrorDecoder extends ApiClientErrorDecoder<UserApiException> {

    public UserClientErrorDecoder() {
        super(UserApiException.class);
    }

}
