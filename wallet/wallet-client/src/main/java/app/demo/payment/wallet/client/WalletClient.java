package app.demo.payment.wallet.client;

import app.demo.payment.wallet.api.WalletApi;
import org.springframework.cloud.openfeign.FeignClient;
import app.demo.payment.wallet.client.configuration.WalletClientConfiguration;

@FeignClient(
        value = "wallet-client",
        url = "${wallet.url}",
        configuration = {WalletClientConfiguration.class}
)
public interface WalletClient extends WalletApi {

}
