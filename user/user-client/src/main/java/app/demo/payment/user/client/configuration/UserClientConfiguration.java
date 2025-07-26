package app.demo.payment.user.client.configuration;

import app.demo.payment.common.client.configuration.CommonFeignClientConfiguration;
import app.demo.payment.user.client.component.UserClientErrorDecoder;
import app.demo.payment.user.client.component.UserClientRequestInterceptor;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class UserClientConfiguration extends CommonFeignClientConfiguration {

    @Bean
    @Override
    public ErrorDecoder errorDecoder() {
        return new UserClientErrorDecoder();
    }

    @Bean
    @Override
    public RequestInterceptor requestInterceptor() {
        return new UserClientRequestInterceptor();
    }
}
