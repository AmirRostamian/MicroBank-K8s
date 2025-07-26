package app.demo.payment.wallet.api;

import app.demo.payment.auth.sdk.api.ServiceApi;
import app.demo.payment.auth.sdk.user.JwtPrincipal;
import app.demo.payment.auth.sdk.user.ServicePrincipal;
import app.demo.payment.wallet.api.domain.exception.WalletAlreadyCreatedException;
import app.demo.payment.wallet.api.domain.model.CreateWalletRequest;
import app.demo.payment.wallet.api.domain.model.WalletInfo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@ServiceApi
public interface WalletServiceApi {

    @PostMapping(WalletApiMethodDictionary.WALLET_CREATE)
    WalletInfo createWallet(@JwtPrincipal ServicePrincipal user,
                            @Validated @RequestBody CreateWalletRequest request) throws WalletAlreadyCreatedException;
}
