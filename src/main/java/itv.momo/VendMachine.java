package itv.momo;

import java.util.HashMap;
import java.util.Map;

interface IVendMachine {
    public void addProduct(Item item) throws Exception;
    public void incProduct(Integer idx) throws Exception;
    public void decProduct(Integer idx) throws Exception;
    // user insert money
    public void insertMoney(Integer value) throws Exception;
    public void buy() throws Exception;
    public void refund();

}

public class VendMachine implements IVendMachine{
    public IMoneyController moneyController;
    public IProductContainer productContainer;
    public ICart cart;


    public VendMachine(Integer productSize) {
        this.moneyController = new MoneyController();
        this.productContainer = new ProductContainer(productSize);
        this.cart = new Cart();
    }

    public void addProduct(Item item) throws Exception {
        productContainer.add(item);
    }

    public void insertMoney(Integer value) throws Exception {
        if (Coin.isValid(value)) {
            moneyController.add(value);
            cart.addMoney(value);
        } else {
            throw new Exception(String.format("Invalid coin! coin : %,d ; Valid coin: %s", value, Coin.asString()));
        }
    }

    public void buy() throws Exception {
        // check enough money to buy
        Integer estimate = productContainer.estimate(cart.product);
        if (cart.getMoney() < estimate) {
            throw new Exception("Not enough money to buy!");
        }

        // payment in Cart and Dec product
        for (Map.Entry<Integer, Integer> entry : cart.payment(estimate).entrySet()) {
            Integer idx = entry.getKey();
            Integer cnt = entry.getValue();

            Item item = productContainer.getItem(idx);
            item.remove(cnt);
        }

    }


    public void refund() {
        Integer money = cart.getMoney();
        HashMap<Integer, Integer> refundCoin = moneyController.refund(money);
        cart.refund();

        System.out.println("Refund coins: ");

        refundCoin.forEach((coin, cnt) -> {
            System.out.printf("Coin %,d x %d\n", coin, cnt);
        });
    }

    @Override
    public void incProduct(Integer idx) throws Exception {
        if (cart.getProduct(idx) < productContainer.getItem(idx).getCount()) {
            cart.addProduct(idx);
        } else {
            throw new Exception("Not enough Item to choose!");
        }

    }

    @Override
    public void decProduct(Integer idx) throws Exception {
        cart.removeProduct(idx);
    }
}
