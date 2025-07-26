package app.demo.payment.wallet.client.configuration;

import app.demo.payment.common.client.configuration.CommonFeignClientConfiguration;
import app.demo.payment.wallet.client.component.WalletClientErrorDecoder;
import app.demo.payment.wallet.client.component.WalletClientRequestInterceptor;
import feign.codec.ErrorDecoder;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class WalletClientConfiguration extends CommonFeignClientConfiguration {

    @Bean
    @Override
    public ErrorDecoder errorDecoder() {
        return new WalletClientErrorDecoder();
    }

    @Bean
    @Override
    public RequestInterceptor requestInterceptor() {
        return new WalletClientRequestInterceptor();
    }
}
