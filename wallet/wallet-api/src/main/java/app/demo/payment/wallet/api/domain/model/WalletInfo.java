package app.demo.payment.wallet.api.domain.model;

import java.math.BigDecimal;

public record WalletInfo(String address, BigDecimal balance) {

}
