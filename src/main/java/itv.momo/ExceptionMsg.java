package itv.momo;

import scala.Int;

public class ExceptionMsg {
    // Product ex
    public static String FullItem = "Full item!!!";
    public static String AddInvalidItem = "Adding Invalid item!!!";
    public static String AddItemInExitedIndex(int idx) {
        return String.format("Adding item in exited Item. Idx: %d !!!", idx);
    }
    public static String UpdateInvalidItem = "Updating Invalid item!!!";
    public static String UpdateInvalidIndex(int idx) {
        return String.format("Updating invalid index %d!!!", idx);
    }


    // MoneyController
    public static String InsertCoinInValid(int coin) {
        return String.format("Invalid coin! coin : %,d ; Valid coin: %s", coin, Coin.asString);
    }
    public static String NotEnoughMoneyToRefund(int money) {
        return String.format("Not Enough Money to refund! Remain Money: %,d. Please continue to buy something.", money);
    }

    // Cart
    public static String UserNotEnoughMoney(int budget, int price) {
        return String.format("Not Enough Money! Budget : %,d ; Price: %,d", budget, price);
    }

    public static String NoneItemInCartToRemove = "No item in Cart to remove!!";

    public static String SelectNonExitItem = "Item selected not exit!!";

    public static String NotEnoughItemToSelect(int selected, int remain) {
        return String.format("Not Enough Item! Selected : %,d ; Remain: %,d. Please continue to buy something.", selected, remain);
    }

}
