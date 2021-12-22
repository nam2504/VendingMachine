package itv.momo;

import scala.Int;

public class ExceptionMsg {
    // Product ex
    public static String FullItem = "Full item!!!";
    public static String AddInvalidItem = "Adding Invalid item!!!";
    public static String AddItemInExitedIndex(Integer idx) {
        return String.format("Adding item in exited Item. Idx: %d !!!", idx);
    }
    public static String UpdateInvalidItem = "Updating Invalid item!!!";
    public static String UpdateInvalidIndex(Integer idx) {
        return String.format("Updating invalid index %d!!!", idx);
    }


    // MoneyController
    public static String InsertCoinInValid(Integer coin) {
        return String.format("Invalid coin! coin : %,d ; Valid coin: %s", coin, Coin.asString);
    }


    // Cart
    public static String UserNotEnoughMoney(Integer budget, Integer price) {
        return String.format("Not Enough Money! Budget : %,d ; Price: %,d", budget, price);
    }

    public static String NoneItemInCartToRemove = "No item in Cart to remove!!";

    public static String SelectNonExitItem = "Product selected not exit!!";

    public static String NotEnoughItemToSelect(Integer selected, Integer remain) {
        return String.format("Not Enough Item! Selected : %,d ; Remain: %,d", selected, remain);
    }

}
