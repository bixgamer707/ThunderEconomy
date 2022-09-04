package me.bixgamer707.thundereconomy.user;

import java.math.BigDecimal;

public interface UserData {

    BigDecimal getBalance();

    BigDecimal addBalance(BigDecimal balance);

    BigDecimal removeBalance(BigDecimal balance);

    void setBalance(BigDecimal balance);

}
