package app.demo.payment.auth.client;

import app.demo.payment.auth.api.AuthApi;
import org.springframework.cloud.openfeign.FeignClient;
import app.demo.payment.auth.client.configuration.AuthClientConfiguration;

@FeignClient(
        value = "auth-client",
        url = "${auth.url}",
        configuration = {AuthClientConfiguration.class}
)
public interface AuthClient extends AuthApi {

}
