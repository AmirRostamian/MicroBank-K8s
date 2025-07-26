package app.demo.payment.auth.api;

import app.demo.payment.auth.api.domain.model.AuthRequest;
import app.demo.payment.auth.api.domain.model.AuthToken;
import app.demo.payment.auth.application.AuthApplicationService;
import app.demo.payment.auth.sdk.controller.UnprotectedApiController;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@RequiredArgsConstructor
@UnprotectedApiController
@Observed
public class AuthApiController implements AuthApi {

    private final AuthApplicationService authApplicationService;

    @Override
    public AuthToken login(@Validated @RequestBody AuthRequest request) {
        return authApplicationService.handleLogin(request);
    }

    @Override
    public AuthToken signup(@Validated @RequestBody AuthRequest request) {
        return authApplicationService.handleSignup(request);
    }

    @Override
    public AuthToken serviceToken(String key) {
        return authApplicationService.handleService(key);
    }
}
