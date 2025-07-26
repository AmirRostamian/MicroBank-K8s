package app.demo.payment.wallet.client.starter;

import app.demo.payment.wallet.client.WalletClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnMissingBean({WalletClient.class})
@Import({WalletClientFeignConfiguration.class})
public class WalletClientAutoConfiguration {
}
