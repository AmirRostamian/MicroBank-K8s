package app.demo.payment.auth.client.component;

import app.demo.payment.auth.api.domain.exception.AuthApiException;
import app.demo.payment.auth.sdk.error.ApiClientErrorDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthClientErrorDecoder extends ApiClientErrorDecoder<AuthApiException> {

    public AuthClientErrorDecoder() {
        super(AuthApiException.class);
    }

}
