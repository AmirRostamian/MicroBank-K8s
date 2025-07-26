package app.demo.payment.wallet.api;

import app.demo.payment.auth.sdk.authority.CanCreateWallet;
import app.demo.payment.auth.sdk.user.JwtPrincipal;
import app.demo.payment.auth.sdk.user.ServicePrincipal;
import app.demo.payment.auth.sdk.controller.ServiceApiController;
import app.demo.payment.wallet.api.domain.model.CreateWalletRequest;
import app.demo.payment.wallet.api.domain.model.WalletInfo;
import app.demo.payment.wallet.application.WalletApplicationService;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@RequiredArgsConstructor
@ServiceApiController
@Observed
public class WalletServiceApiController implements WalletServiceApi {

    private final WalletApplicationService walletApplicationService;

    @Override
    @CanCreateWallet
    public WalletInfo createWallet(@JwtPrincipal ServicePrincipal principal,
                                   @Validated @RequestBody CreateWalletRequest request) {
        return walletApplicationService.createWallet(request);
    }

}
