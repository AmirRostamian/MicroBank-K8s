package app.demo.payment.wallet.api;

import app.demo.payment.auth.sdk.api.PublicApi;
import app.demo.payment.wallet.api.domain.model.TransferFundsRequest;
import app.demo.payment.wallet.api.domain.model.WalletInfo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import app.demo.payment.auth.sdk.user.JwtPrincipal;
import app.demo.payment.auth.sdk.user.UserPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@PublicApi
public interface WalletPublicApi {

    @GetMapping(WALLET_INFO)
    WalletInfo getInfo(@JwtPrincipal UserPrincipal user);

    @GetMapping(WALLET_TOPUP)
    WalletInfo topUp(@JwtPrincipal UserPrincipal user);

    @PostMapping(WALLET_TRANSFER_FUNDS)
    WalletInfo transferFunds(@JwtPrincipal UserPrincipal user,
                             @Validated @RequestBody TransferFundsRequest request);
}
