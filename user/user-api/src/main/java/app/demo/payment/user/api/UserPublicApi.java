package app.demo.payment.user.api;

import app.demo.payment.auth.sdk.api.PublicApi;
import app.demo.payment.user.api.domain.model.UserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import app.demo.payment.auth.sdk.user.JwtPrincipal;
import app.demo.payment.auth.sdk.user.UserPrincipal;

@PublicApi
public interface UserPublicApi {

    @GetMapping(UserApiMethodDictionary.GET_MY_INFO)
    UserInfo getMyInfo(@JwtPrincipal UserPrincipal user);
}
