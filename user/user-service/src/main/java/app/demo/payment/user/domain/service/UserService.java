package app.demo.payment.user.domain.service;

import app.demo.payment.auth.sdk.user.ServicePrincipal;
import app.demo.payment.user.domain.model.User;
import app.demo.payment.user.repository.UserRepository;

import app.demo.payment.wallet.api.domain.exception.WalletAlreadyCreatedException;
import app.demo.payment.wallet.api.domain.model.CreateWalletRequest;
import app.demo.payment.wallet.client.WalletClient;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Observed
public class UserService {

    private final UserRepository userRepository;

    private final WalletClient walletClient;

    private final ServicePrincipal servicePrincipal;

    public Optional<User> getUser(@NotNull String user) {
        User wallet = userRepository.findByUser(user);
        return Optional.ofNullable(wallet);
    }

    public User createUser(@NotNull String userId,
                           @NotNull String passwordHash,
                           @NotNull String name) {
        User user = new User(userId, name, true, passwordHash);

        userRepository.save(user);

        CreateWalletRequest request = new CreateWalletRequest(userId, BigDecimal.ZERO);

        try {
            walletClient.createWallet(servicePrincipal, request);
        } catch (WalletAlreadyCreatedException e) {
            log.warn("User {} wallet already created", userId);
        } catch (AccessDeniedException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }

        return user;
    }
}
