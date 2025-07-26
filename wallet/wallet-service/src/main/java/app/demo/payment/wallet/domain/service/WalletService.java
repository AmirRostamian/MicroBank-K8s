package app.demo.payment.wallet.domain.service;

import app.demo.payment.auth.sdk.user.ServicePrincipal;
import app.demo.payment.user.api.domain.exception.UserNotFoundException;
import app.demo.payment.user.api.domain.model.UserInfo;
import app.demo.payment.user.client.UserClient;
import app.demo.payment.wallet.domain.model.Wallet;
import app.demo.payment.wallet.repository.WalletRepository;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import app.demo.payment.common.util.RandomStringUtil;
import app.demo.payment.wallet.api.domain.exception.InvalidRecipientException;
import app.demo.payment.wallet.api.domain.exception.WalletAlreadyCreatedException;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Observed
public class WalletService {

    private final WalletRepository walletRepository;

    private final BillingService billing;

    private final UserClient userClient;

    private final ServicePrincipal servicePrincipal;

    private final NotificationService notificationService;

    public Optional<Wallet> findByAddress(@NotNull String address) {
        Wallet wallet = walletRepository.findByAddress(address);
        return Optional.ofNullable(wallet);
    }

    public Optional<Wallet> findByUser(@NotNull String user) {
        Wallet wallet = walletRepository.findByUser(user);
        return Optional.ofNullable(wallet);
    }

    public Wallet createWallet(@NotNull String user,
                               @NotNull BigDecimal initialBalance) {

        Optional.ofNullable(walletRepository.findByUser(user))
                .ifPresent(this::throwWalletAlreadyCreatedException);

        String address = generateUniqueAddress();
        Wallet wallet = new Wallet(address, initialBalance, user);
        return walletRepository.save(wallet);
    }

    public void topUp(@NotNull Wallet wallet, @NotNull BigDecimal amount) {
        billing.execute(wallet);
        wallet.topUp(amount);
    }

    public void transferFunds(@NotNull Wallet fromWallet,
                              @NotNull Wallet toWallet,
                              @NotNull BigDecimal amount) {

        String recipient = getUserFullName(toWallet.getUser());

        String sender = getUserFullName(fromWallet.getUser());

        fromWallet.transferFunds(toWallet, amount);

        String message = String.format("Dear, %s! %s sent you %s$",
                recipient, sender, amount.toPlainString());

        notificationService.notify(toWallet.getUser(), message);
    }

    private String getUserFullName(String user) throws UserNotFoundException {
        try {
            UserInfo recipientInfo = userClient
                    .getUserInfo(servicePrincipal, user);

            if (!recipientInfo.enabled()) {
                throw new InvalidRecipientException();
            }

        } catch (UserNotFoundException e) {
            throw new InvalidRecipientException();
        }

        return user;
    }

    private String generateUniqueAddress() {
        return RandomStringUtil.generate();
    }

    private void throwWalletAlreadyCreatedException(Wallet wallet) {
        throw new WalletAlreadyCreatedException();
    }
}
