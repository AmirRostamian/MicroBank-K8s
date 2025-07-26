package app.demo.payment.auth.sdk.feign;

import app.demo.payment.auth.sdk.user.AuthPrincipal;
import feign.Param;

public class UserPrincipalTokenExpander implements Param.Expander {

    @Override
    public String expand(Object value) {
        if (value instanceof AuthPrincipal principal) {
            return "Bearer " + principal.getToken();
        }
        return null;
    }
}
