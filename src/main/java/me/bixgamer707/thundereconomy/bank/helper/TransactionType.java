package me.bixgamer707.thundereconomy.bank.helper;

public enum TransactionType {
    WITHDRAW_SERVER(1),
    DEPOSIT_SERVER(1),
    DEPOSIT_PLAYER(2),
    WITHDRAW_PLAYER(2),
    TRANSFER_PLAYER(2),
    SET_BALANCE(1);

    private final int type;
    TransactionType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    @Override
    public String toString() {
        return "type="+type;
    }
}
