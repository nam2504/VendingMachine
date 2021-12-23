package itv.momo;

import java.util.*;

interface IMoneyController {
    public void add(int  coin) throws Exception;
    public void add(int  coin, int cnt) throws Exception;
    public void remove(int  coin, int cnt);
    //public Integer getCoins();
    public HashMap<Integer, Integer> refund(int amount);
}


public class MoneyController implements IMoneyController{
    private HashMap<Integer, Integer> data = new HashMap<>();
    public void add(int coin) throws Exception {
        add(coin, 1);
    }

    @Override
    public void add(int coin, int cnt) throws Exception {
        if (!Coin.isValid(coin)) {
            throw new Exception(ExceptionMsg.InsertCoinInValid(coin));
        }
        Integer total = data.getOrDefault(coin, 0) + cnt;
        data.put(coin, total);
    }

    @Override
    public void remove(int coin, int cnt) {
        Integer total = data.getOrDefault(coin, 0) - cnt;
        data.put(coin, total);
    }


    public HashMap<Integer, Integer> refund(int amount) {
        HashMap<Integer, Integer> refundCoin = new HashMap<>();
        int remain = amount;
        SortedSet<Integer> keys = new TreeSet<Integer>(data.keySet()).descendingSet();
        for (Integer coin : keys) {
            if (remain >= coin) {
                int cnt = data.get(coin);
                int rCnt = Math.min(remain / coin, cnt);
                remain -= coin * rCnt;
                data.put(coin, cnt - rCnt);
                refundCoin.put(coin, rCnt);
            }
        }
        return refundCoin;
    }
}
