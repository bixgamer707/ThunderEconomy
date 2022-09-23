package me.bixgamer707.thundereconomy.user;

import java.math.BigDecimal;
import java.util.UUID;

public interface UserData {

    UUID getUuid();

    String getName();

    BigDecimal getBalance();

    BigDecimal addBalance(BigDecimal balance);

    BigDecimal removeBalance(BigDecimal balance);

    void setBalance(BigDecimal balance);

}
