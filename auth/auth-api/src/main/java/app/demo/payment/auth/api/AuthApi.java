package app.demo.payment.auth.api;

import app.demo.payment.auth.api.domain.model.AuthRequest;
import app.demo.payment.auth.api.domain.model.AuthToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthApi {

    @PostMapping(AuthApiMethodDictionary.AUTH_LOGIN)
    AuthToken login(@Validated @RequestBody AuthRequest request);

    @PostMapping(AuthApiMethodDictionary.AUTH_SIGNUP)
    AuthToken signup(@Validated @RequestBody AuthRequest request);

    @GetMapping(AuthApiMethodDictionary.SERVICE_TOKEN)
    AuthToken serviceToken(@PathVariable("key") String key);
}
