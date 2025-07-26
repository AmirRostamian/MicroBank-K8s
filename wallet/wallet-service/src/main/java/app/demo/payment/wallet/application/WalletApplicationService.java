package app.demo.payment.wallet.application;

import app.demo.payment.wallet.api.domain.exception.WalletNotFoundException;

import app.demo.payment.auth.sdk.user.UserPrincipal;
import app.demo.payment.wallet.api.domain.model.CreateWalletRequest;
import app.demo.payment.wallet.api.domain.model.TransferFundsRequest;
import app.demo.payment.wallet.api.domain.model.WalletInfo;
import app.demo.payment.wallet.domain.model.Wallet;
import app.demo.payment.wallet.domain.service.WalletService;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Observed
public class WalletApplicationService {

    private final WalletService walletService;

    public WalletInfo getInfo(UserPrincipal user) {
        Optional<Wallet> walletOptional = walletService.findByUser(user.getUser());
        Wallet wallet = walletOptional.orElseGet(() ->
                walletService.createWallet(user.getUser(), BigDecimal.ZERO));
        return new WalletInfo(wallet.getAddress(), wallet.getBalance());
    }

    public WalletInfo createWallet(CreateWalletRequest request) {
        Wallet wallet = walletService.createWallet(request.user(),
                request.initialBalance());

        return new WalletInfo(wallet.getAddress(), wallet.getBalance());
    }

    public void topUp(UserPrincipal user, BigDecimal amount) {
        Wallet wallet = walletService.findByUser(user.getUser())
                .orElseThrow(WalletNotFoundException::new);

        walletService.topUp(wallet, amount);
    }

    public void transferFunds(UserPrincipal user,
                              TransferFundsRequest request) {

        Wallet fromWallet = walletService.findByUser(user.getUser())
                .orElseThrow(WalletNotFoundException::new);

        Wallet toWallet = walletService.findByAddress(request.to())
                .orElseThrow(WalletNotFoundException::new);

        walletService.transferFunds(fromWallet, toWallet, request.amount());
    }
}
