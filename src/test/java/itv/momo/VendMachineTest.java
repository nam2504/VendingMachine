package itv.momo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VendMachineTest {
    VendMachine vendMachine = new VendMachine(4);

    @BeforeEach
    void setUp() {
        try {
            addProduct("Coke", 10000, 3);
            addProduct("Pepsi", 10000, 3);
            addProduct("Soda", 20000, 3);
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
            Item item = vendMachine.getItem(idx);
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
    void refund() {
        try {
            vendMachine.insertMoney(20000);
            vendMachine.incProduct(0);
            vendMachine.buy();
            vendMachine.refund();
            fail();
        } catch (Exception e) {
            assertEquals(vendMachine.userBudget(), 10000);
        }

        try {
            // buy to reset money
            vendMachine.incProduct(0);
            vendMachine.buy();
            //
            vendMachine.insertMoney(200000);
            vendMachine.insertMoney(200000);
            vendMachine.insertMoney(100000);
            vendMachine.insertMoney(10000);
            vendMachine.insertMoney(10000);
            vendMachine.insertMoney(10000);

            vendMachine.refund();

            assertEquals(vendMachine.userBudget(), 0);

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

    }

    @Test
    void incProduct() {
        int idx = 0;
        try {
            vendMachine.incProduct(idx);
            vendMachine.incProduct(idx);
            vendMachine.incProduct(idx);
        } catch (Exception e) {
            fail();
        }
        try {
            vendMachine.incProduct(idx);
            fail();
        } catch (Exception e) {
            assertEquals(ExceptionMsg.NotEnoughItemToSelect(4, 3), e.getMessage());
        }

        try {
            vendMachine.incProduct(1000);
            fail();
        } catch (Exception e) {
            assertEquals(ExceptionMsg.SelectNonExitItem, e.getMessage());
        }

    }

    @Test
    void buy() {
        try {
            vendMachine.incProduct(0);
            vendMachine.buy();
            fail();
        } catch (Exception e) {
            assertEquals(ExceptionMsg.UserNotEnoughMoney(0, 10000), e.getMessage());
        }
        try {
            vendMachine.insertMoney(10000);
            vendMachine.buy();
        } catch (Exception e) {
            fail();
        }

        try {
            vendMachine.insertMoney(20000);
            vendMachine.incProduct(0);
            vendMachine.buy();
            assertEquals(vendMachine.userBudget(), 10000);
        } catch (Exception e) {
            fail();
        }

    }
}