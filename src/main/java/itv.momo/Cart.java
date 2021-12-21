package itv.momo;

import java.util.HashMap;

interface ICart {
    public HashMap<Integer, Integer> product = new HashMap<>(); // product idx -> cnt

    public void addMoney(Integer coin);
    public void addProduct(Integer idx);
    public void removeProduct(Integer idx) throws Exception;
    public Integer getProduct(Integer idx);

    public Integer getMoney();
    public void refund();
    public HashMap<Integer, Integer> payment(Integer price);

}

public class Cart implements ICart {
    public Integer budget = 0; // coin -> cnt
    @Override
    public void addMoney(Integer coin) {
        budget += coin;
    }

    @Override
    public void addProduct(Integer idx) {
        Integer cnt = product.getOrDefault(idx, 0);
        product.put(idx, cnt + 1);
    }

    @Override
    public void removeProduct(Integer idx) throws Exception {
        Integer cnt = product.getOrDefault(idx, 0) - 1;
        if (cnt < 0) {
            throw new Exception("No item in Cart to remove");
        }
        product.put(idx, cnt);
    }

    @Override
    public Integer getProduct(Integer idx) {
        return product.getOrDefault(idx, 0);
    }

    @Override
    public Integer getMoney() {
        return budget;
    }

    @Override
    public void refund() {
        this.budget = 0;
    }

    @Override
    public HashMap<Integer, Integer> payment(Integer price) {
        this.budget -= price;
        HashMap<Integer, Integer> p = (HashMap<Integer, Integer>) product.clone();
        product.clear();
        return p;
    }
}
