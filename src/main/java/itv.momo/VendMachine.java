package itv.momo;

import java.util.HashMap;
import java.util.Map;

interface IVendMachine {
    public Integer addProduct(Item item) throws Exception;
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


    public VendMachine() {
        init(4);
    }

    public VendMachine(Integer productSize) {
        init(productSize);
    }

    private void init(Integer size) {
        this.moneyController = new MoneyController();
        this.productContainer = new ProductContainer(size);
        this.cart = new Cart();
    }

    public Integer addProduct(Item item) throws Exception {
        return productContainer.add(item);
    }

    public void insertMoney(Integer value) throws Exception {
        moneyController.add(value);
        cart.addMoney(value);
    }

    public void buy() throws Exception {
        // check enough money to buy
        Integer estimate = productContainer.estimate(cart.product);

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
        Item itemInContainer = productContainer.getItem(idx);
        cart.addProduct(idx, itemInContainer);
    }

    @Override
    public void decProduct(Integer idx) throws Exception {
        cart.removeProduct(idx);
    }
}
