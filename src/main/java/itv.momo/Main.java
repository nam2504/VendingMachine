package itv.momo;

import org.jetbrains.annotations.NotNull;
import scala.Console;

public class Main {
    static VendMachine vendMachine = new VendMachine(4);
    public static void main(String[] args) {
        initProduct();
        while (true) {
            showProduct();
//            userInsertCoins();
//            userChoseProduct();
//            buy();
//            refush();
        }
        // TODO: use stage to control
    }

    private static void showProduct() {


    }

    public static void initProduct() {
        addProduct("Coke", 10000, 10);
        addProduct("Pepsi", 10000, 10);
        addProduct("Soda", 20000, 10);
    }

    public static void addProduct(String name, Integer price, Integer cnt) {
        Item item = new Item(name, price, cnt);
        try {
            vendMachine.addProduct(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void println(String msg) {
        Console.out().println(msg);
    }

    @NotNull
    public static Integer read() {
        String line = System.console().readLine();
        return Integer.parseInt(line);
    }
}
