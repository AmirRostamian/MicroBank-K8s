package app.demo.payment.wallet.domain.service;

import app.demo.payment.wallet.api.domain.exception.BillingNotAvailableException;
import app.demo.payment.wallet.domain.model.Wallet;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Service
public class BillingService {

    private final RestTemplate restTemplate;

    private final String billingUrl;

    public BillingService(RestTemplate template,
                         @Value("${billing.url}") String billingUrl) {
        this.restTemplate = template;
        this.billingUrl = billingUrl;
    }

    public void execute(@NotNull Wallet wallet) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.USER_AGENT, wallet.getAddress());
            HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
            restTemplate.exchange(billingUrl, HttpMethod.GET, httpEntity, String.class);
        } catch (Exception e) {
            throw new BillingNotAvailableException();
        }
    }
}
