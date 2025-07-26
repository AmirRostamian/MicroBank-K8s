package app.demo.payment.user.api;

import app.demo.payment.auth.sdk.authority.CanCreateUser;
import app.demo.payment.auth.sdk.authority.CanIntroinspectUser;
import app.demo.payment.auth.sdk.authority.CanViewUserPassword;
import app.demo.payment.auth.sdk.user.JwtPrincipal;
import app.demo.payment.auth.sdk.user.ServicePrincipal;
import app.demo.payment.auth.sdk.controller.ServiceApiController;
import app.demo.payment.user.api.domain.model.CreateUserRequest;
import app.demo.payment.user.api.domain.model.UserInfo;
import app.demo.payment.user.application.UserApplicationService;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RequiredArgsConstructor
@ServiceApiController
@Observed
public class UserServiceApiController implements UserServiceApi {

    private final UserApplicationService userApplicationService;

    @Override
    @CanCreateUser
    public UserInfo createUser(@JwtPrincipal ServicePrincipal service,
                               @Validated @RequestBody CreateUserRequest request) {
        return userApplicationService.createUser(request);
    }

    @Override
    @CanIntroinspectUser
    public UserInfo getUserInfo(ServicePrincipal service,
                                String user) {
        return userApplicationService.getUserInfo(user);
    }

    @Override
    @CanViewUserPassword
    public String getPasswordHash(ServicePrincipal service,
                                  String user) {
        return userApplicationService.getPasswordHash(user);
    }
}
