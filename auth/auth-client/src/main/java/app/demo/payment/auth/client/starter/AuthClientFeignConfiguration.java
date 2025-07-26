package app.demo.payment.auth.client.starter;

import app.demo.payment.auth.client.AuthClient;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(
        basePackageClasses = {AuthClient.class}
)
@ImportAutoConfiguration({FeignAutoConfiguration.class})
@Configuration
public class AuthClientFeignConfiguration {

}
