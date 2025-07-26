package app.demo.payment.user.client;

import app.demo.payment.user.api.UserApi;
import app.demo.payment.user.client.configuration.UserClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        value = "user-client",
        url = "${user.url}",
        configuration = {UserClientConfiguration.class}
)
public interface UserClient extends UserApi {

}
