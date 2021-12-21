package itv.momo;

interface IVendMachine {
    public void addProduct(Item item) throws Exception;
    // add base money to refund after buy
    public void addMoney();
    // user insert money
    public void insertMoney(Integer value);
    public void buy(Integer idx);
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

    public void addMoney() {

    }

    public void insertMoney(Integer value) {

    }

    public void buy(Integer idx) {

    }


    public void refund() {

    }
}
