package app.demo.payment.wallet.client.starter;

import app.demo.payment.wallet.client.WalletClient;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(
        basePackageClasses = {WalletClient.class}
)
@ImportAutoConfiguration({FeignAutoConfiguration.class})
@Configuration
public class WalletClientFeignConfiguration {

}
