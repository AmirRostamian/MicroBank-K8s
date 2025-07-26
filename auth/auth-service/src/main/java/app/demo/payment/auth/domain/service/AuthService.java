package app.demo.payment.auth.domain.service;

import app.demo.payment.auth.api.domain.exception.InvalidCredentialsException;
import app.demo.payment.auth.api.domain.exception.UserAlreadyExistsException;
import app.demo.payment.auth.configuration.JwtConfiguration;
import app.demo.payment.auth.domain.model.JwtToken;
import app.demo.payment.auth.sdk.user.ServicePrincipal;
import app.demo.payment.auth.sdk.util.JwtTokenProvider;
import app.demo.payment.auth.sdk.authority.Authority;

import app.demo.payment.user.api.domain.exception.UserNotFoundException;
import app.demo.payment.user.api.domain.model.CreateUserRequest;
import app.demo.payment.user.client.UserClient;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Slf4j
@Service
@RequiredArgsConstructor
@Observed
public class AuthService {

    private final UserClient userClient;

    private final JwtTokenProvider jwtTokenProvider;

    private final ServicePrincipal servicePrincipal;

    private final JwtConfiguration jwtConfiguration;

    private final PasswordEncoder passwordEncoder;

    public JwtToken createUser(String user, String password) {

        if (isUserExists(user)) {
            throw new UserAlreadyExistsException();
        }

        var request = new CreateUserRequest(user, passwordEncoder.encode(password));

        userClient.createUser(servicePrincipal, request);

        var token = jwtTokenProvider.createToken(user, jwtConfiguration.getJwtTTL(),
                Authority.ROLE_USER);

        return new JwtToken(token);
    }

    public JwtToken authUser(String user, String password) {

        var passwordHash = getPasswordHash(user);

        if (!passwordEncoder.matches(password, passwordHash)) {
            throw new InvalidCredentialsException();
        }

        var token = jwtTokenProvider.createToken(user, jwtConfiguration.getJwtTTL(),
                Authority.ROLE_USER);

        return new JwtToken(token);
    }

    private boolean isUserExists(String user) {
        try {
            userClient.getUserInfo(servicePrincipal, user);
            return true;
        } catch (UserNotFoundException e) {
            return false;
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    private String getPasswordHash(String user) {
        try {
            return userClient.getPasswordHash(servicePrincipal, user);
        } catch (UserNotFoundException e) {
            throw new InvalidCredentialsException();
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
