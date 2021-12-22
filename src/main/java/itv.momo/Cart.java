package itv.momo;

import java.util.HashMap;

interface ICart {
    public HashMap<Integer, Integer> product = new HashMap<>(); // product idx -> cnt

    public void addMoney(Integer coin);
    public void addProduct(Integer idx, Item itemInContainer) throws Exception;
    public void removeProduct(Integer idx) throws Exception;
    public Integer getProduct(Integer idx);

    public Integer getMoney();
    public void refund();
    public HashMap<Integer, Integer> payment(Integer price) throws Exception;

}

public class Cart implements ICart {
    public Integer budget = 0; // coin -> cnt
    @Override
    public void addMoney(Integer coin) {
        budget += coin;
    }

    @Override
    public void addProduct(Integer idx, Item itemInContainer) throws Exception {
        if (itemInContainer == null) {
            throw new Exception(ExceptionMsg.SelectNonExitItem);
        }
        Integer cnt = product.getOrDefault(idx, 0) + 1;
        Integer remain = itemInContainer.getCount();
        if (remain < cnt) {
            throw new Exception(ExceptionMsg.NotEnoughItemToSelect(cnt, remain));
        }

        product.put(idx, cnt);
    }

    @Override
    public void removeProduct(Integer idx) throws Exception {
        Integer cnt = product.getOrDefault(idx, 0) - 1;
        if (cnt < 0) {
            throw new Exception(ExceptionMsg.NoneItemInCartToRemove);
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
    public HashMap<Integer, Integer> payment(Integer price) throws Exception {
        if (this.budget < price) {
            throw new Exception(ExceptionMsg.UserNotEnoughMoney(budget, price));
        }

        this.budget -= price;
        HashMap<Integer, Integer> p = (HashMap<Integer, Integer>) product.clone();
        product.clear();
        return p;
    }
}
