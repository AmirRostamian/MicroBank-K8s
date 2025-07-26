package app.demo.payment.user.api;

import app.demo.payment.auth.sdk.user.JwtPrincipal;
import app.demo.payment.auth.sdk.user.UserPrincipal;
import app.demo.payment.auth.sdk.controller.PublicApiController;
import app.demo.payment.user.api.domain.model.UserInfo;
import app.demo.payment.user.application.UserApplicationService;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@PublicApiController
@Observed
public class UserPublicApiController implements UserPublicApi {

    private final UserApplicationService userApplicationService;

    @Override
    public UserInfo getMyInfo(@JwtPrincipal UserPrincipal user) {
        return userApplicationService.getUserInfo(user);
    }
}
