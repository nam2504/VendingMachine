package itv.momo;

import java.util.HashMap;

interface ICart {
    public HashMap<Integer, Integer> product = new HashMap<>(); // product idx -> cnt

    public void addMoney(int coin);
    public void addProduct(int idx, Item itemInContainer) throws Exception;
    public void removeProduct(int idx) throws Exception;
    public int getItemQuantity(int idx);
    public HashMap<Integer, Integer> getAllItemQuantity();

    public int getMoney();
    public void useMoney(int amount);
    public HashMap<Integer, Integer> payment(int price) throws Exception;

}

public class Cart implements ICart {
    public Integer budget = 0; // coin -> cnt
    @Override
    public void addMoney(int coin) {
        budget += coin;
    }

    @Override
    public void addProduct(int idx, Item itemInContainer) throws Exception {
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
    public void removeProduct(int idx) throws Exception {
        int cnt = product.getOrDefault(idx, 0) - 1;
        if (cnt < 0) {
            throw new Exception(ExceptionMsg.NoneItemInCartToRemove);
        }
        product.put(idx, cnt);
    }

    @Override
    public int getItemQuantity(int idx) {
        return product.getOrDefault(idx, 0);
    }

    @Override
    public HashMap<Integer, Integer> getAllItemQuantity() {
        return product;
    }

    @Override
    public int getMoney() {
        return budget;
    }

    @Override
    public void useMoney(int amount) {
        this.budget -= amount;
    }

    @Override
    public HashMap<Integer, Integer> payment(int price) throws Exception {
        if (this.budget < price) {
            throw new Exception(ExceptionMsg.UserNotEnoughMoney(budget, price));
        }

        this.budget -= price;
        HashMap<Integer, Integer> p = (HashMap<Integer, Integer>) product.clone();
        product.clear();
        return p;
    }
}
