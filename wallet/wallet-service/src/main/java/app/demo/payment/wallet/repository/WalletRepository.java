package app.demo.payment.wallet.repository;

import app.demo.payment.wallet.domain.model.Wallet;

public interface WalletRepository {

    Wallet findByAddress(String address);

    Wallet findByUser(String user);

    Wallet save(Wallet wallet);
}
