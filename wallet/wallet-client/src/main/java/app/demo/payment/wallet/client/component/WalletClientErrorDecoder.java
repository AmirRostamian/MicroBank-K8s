package app.demo.payment.wallet.client.component;

import app.demo.payment.auth.sdk.error.ApiClientErrorDecoder;
import app.demo.payment.wallet.api.domain.exception.WalletApiException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WalletClientErrorDecoder extends ApiClientErrorDecoder<WalletApiException> {

    public WalletClientErrorDecoder() {
        super(WalletApiException.class);
    }

}
