package itv.momo;

import java.util.HashMap;
import java.util.Map;

interface IVendMachine {
    public Integer addProduct(Item item) throws Exception;
    public Item getItem(int idx) throws Exception;
    public Item[] getAllItem();

    // User select or remove Item In cart
    public HashMap<Integer, Integer> getItemInCart();
    public void incProduct(int idx) throws Exception;
    public void decProduct(int idx) throws Exception;
    // user control money
    public int userBudget();
    public void insertMoney(int value) throws Exception;
    public void buy() throws Exception;
    public void refund() throws Exception;

}

public class VendMachine implements IVendMachine{
    private IMoneyController moneyController;
    private IProductContainer productContainer;
    private ICart cart;


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

    @Override
    public Item getItem(int idx) throws Exception {
        return productContainer.getItem(idx);
    }

    public void insertMoney(int value) throws Exception {
        moneyController.add(value);
        cart.addMoney(value);
    }

    public void buy() throws Exception {
        HashMap<Integer, Integer> cartItem = cart.getAllItemQuantity();
        // check enough money to buy
        int estimate = productContainer.estimate(cartItem);

        // payment in Cart
        cart.payment(estimate);
        // and Dec product
        for (Map.Entry<Integer, Integer> entry : cartItem.entrySet()) {
            Integer idx = entry.getKey();
            Integer cnt = entry.getValue();

            Item item = productContainer.getItem(idx);
            item.remove(cnt);
            System.out.printf("You get %s x %d", item.getName(), cnt);
        }

    }


    public void refund() throws Exception {
        int money = cart.getMoney();
        HashMap<Integer, Integer> refundCoin = moneyController.refund(money);


        System.out.println("Refund coins: ");

        refundCoin.forEach((coin, cnt) -> {
            System.out.printf("Coin %,d x %d\n", coin, cnt);
            cart.useMoney(coin * cnt);
        });
        int remainMoney = cart.getMoney();
        System.out.println("Total refund: " + (money - remainMoney));

        if (remainMoney > 0) {
            throw new Exception(ExceptionMsg.NotEnoughMoneyToRefund(remainMoney));
        }
    }

    @Override
    public void incProduct(int idx) throws Exception {
        Item itemInContainer = productContainer.getItem(idx);
        cart.addProduct(idx, itemInContainer);
    }

    @Override
    public void decProduct(int idx) throws Exception {
        cart.removeProduct(idx);
    }

    @Override
    public int userBudget() {
        return cart.getMoney();
    }

    @Override
    public Item[] getAllItem() {
        return productContainer.getAllItem();
    }

    @Override
    public HashMap<Integer, Integer> getItemInCart() {
        return cart.getAllItemQuantity();
    }
}
