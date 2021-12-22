package itv.momo;

import java.util.*;

interface IMoneyController {
    public void add(Integer coin) throws Exception;
    public void add(Integer coin, Integer cnt) throws Exception;
    public void remove(Integer coin, Integer cnt);
    //public Integer getCoins();
    public HashMap<Integer, Integer> refund(Integer amount);
}


public class MoneyController implements IMoneyController{
    private HashMap<Integer, Integer> data = new HashMap<>();
    public void add(Integer coin) throws Exception {
        add(coin, 1);
    }

    @Override
    public void add(Integer coin, Integer cnt) throws Exception {
        if (!Coin.isValid(coin)) {
            throw new Exception(ExceptionMsg.InsertCoinInValid(coin));
        }
        Integer total = data.getOrDefault(coin, 0) + cnt;
        data.put(coin, total);
    }

    @Override
    public void remove(Integer coin, Integer cnt) {
        Integer total = data.getOrDefault(coin, 0) - cnt;
        data.put(coin, total);
    }


    public HashMap<Integer, Integer> refund(Integer amount) {
        HashMap<Integer, Integer> refundCoin = new HashMap<>();

        SortedSet<Integer> keys = new TreeSet<Integer>(data.keySet());
        for (Integer coin : keys) {
            if (amount > coin) {
                Integer cnt = data.get(coin);
                Integer rCnt = Math.min(amount / coin, cnt);
                amount -= coin * rCnt;
                refundCoin.put(coin, cnt);
            }
        }
        return refundCoin;
    }
}
