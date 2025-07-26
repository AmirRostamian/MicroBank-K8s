package app.demo.payment.auth.application;

import app.demo.payment.auth.api.domain.model.AuthRequest;
import app.demo.payment.auth.api.domain.model.AuthToken;
import app.demo.payment.auth.domain.model.JwtToken;
import app.demo.payment.auth.domain.service.AuthService;
import app.demo.payment.auth.component.ServiceTokenProvider;
import io.micrometer.observation.annotation.Observed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
@Observed
public class AuthApplicationService {

    private final AuthService authService;

    private final ServiceTokenProvider serviceTokenProvider;

    public AuthToken handleLogin(AuthRequest request) {
        JwtToken token = authService.authUser(request.user(),
                request.password());
        return new AuthToken(token.token());
    }

    public AuthToken handleService(@NotNull String key) {
        var token = serviceTokenProvider.getServiceToken(key);
        return new AuthToken(token.token());
    }

    public AuthToken handleSignup(AuthRequest request) {
        JwtToken token = authService.createUser(request.user(),
                request.password());
        return new AuthToken(token.token());
    }
}
