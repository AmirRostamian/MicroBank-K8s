package app.demo.payment.auth.client.starter;

import app.demo.payment.auth.client.AuthClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnMissingBean({AuthClient.class})
@Import({AuthClientFeignConfiguration.class})
public class AuthClientAutoConfiguration {
}
