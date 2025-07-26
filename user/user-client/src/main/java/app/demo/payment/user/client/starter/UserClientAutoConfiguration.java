package app.demo.payment.user.client.starter;

import app.demo.payment.user.client.UserClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnMissingBean({UserClient.class})
@Import({UserClientFeignConfiguration.class})
public class UserClientAutoConfiguration {
}
