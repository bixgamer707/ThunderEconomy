package me.bixgamer707.thundereconomy.bank.helper;

public enum TransactionType {
    WITHDRAW_SERVER(1),
    DEPOSIT_SERVER(1),
    DEPOSIT_PLAYER(2),
    WITHDRAW_PLAYER(2),
    TRANSFER_PLAYER(2),
    SET_BALANCE(1);

    /*

    *   The int type defines from where it is executed, CONSOLE OR PLAYER.
    *
    *   @param type 1 for console and 2 for player
    *
    *   @return Returns an integer, 1 or 2 depending on the situation
    *
     */

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
