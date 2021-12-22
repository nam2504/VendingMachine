package itv.momo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VendMachineTest {
    VendMachine vendMachine = new VendMachine(4);

    @BeforeEach
    void setUp() {
        try {
            addProduct("Coke", 10000, 10);
            addProduct("Pepsi", 10000, 10);
            addProduct("Soda", 20000, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer addProduct(String name, Integer price, Integer cnt) throws Exception {
        Item item = new Item(name, price, cnt);
        return vendMachine.addProduct(item);
    }


    @Test
    void addProduct() {
        try {
            Integer idx = 3;
            String name = "Coke2";
            Integer price = 10000;
            Integer cnt = 10;
            assertEquals(idx, addProduct(name, price, cnt));
            Item item = vendMachine.productContainer.getItem(idx);
            assertEquals(name, item.getName());
            assertEquals(price, item.getPrice());
            assertEquals(cnt, item.getCount());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        Exception exception = assertThrows(Exception.class, () ->
                addProduct("Coke3", 10000, 10));

        assertEquals(ExceptionMsg.FullItem, exception.getMessage());

    }

    @Test
    void insertMoneyValid() {
        Integer[] coin = { 10000, 20000, 50000, 100000, 200000 };
        for (Integer c : coin) {
            try {
                vendMachine.insertMoney(c);
                assertTrue(true);
            } catch (Exception e) {
                e.printStackTrace();
                fail();
            }
        }
    }
    @Test
    void insertMoneyInValid() {
        Integer[] coin = { 1000, 2000, 5000, 500000};
        for (Integer c : coin) {
            try {
                vendMachine.insertMoney(c);
                fail();
            } catch (Exception e) {
                assertEquals(e.getMessage(), ExceptionMsg.InsertCoinInValid(c));
            }
        }
    }

    @Test
    void buy() {

    }

    @Test
    void refund() {

    }

    @Test
    void incProduct() {

    }

    @Test
    void decProduct() {

    }
}