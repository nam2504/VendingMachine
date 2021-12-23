package itv.momo;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

public class Main {
    final static int  productSize  = 4;

    final static int  ShowItem     = 0;
    final static int  SelectItem   = ShowItem + 1;
    final static int  InsertCoin   = SelectItem + 1;
    final static int  ShowCart     = InsertCoin + 1;
    final static int  Buy          = ShowCart + 1;
    final static int  Refund       = Buy + 1;

    final static String  FuncInputMsg = String.format(
            "%d : Show All Items\n%d : Select Item\n%d : Insert Coin\n%d : Show Cart\n%d : Buy\n%d : Refund\n-1: Exit!"
            , ShowItem, SelectItem, InsertCoin, ShowCart, Buy, Refund);


    static VendMachine vendMachine = new VendMachine(productSize);
    static boolean isContinue = true;
    public static void main(String[] args) {
        initProduct();
        while (isContinue) {
            System.out.println(FuncInputMsg);
            selectFunc("Please enter number to Continue:");
        }
    }

    static void selectFunc(String msg) {
        sleep();
        System.out.println(msg);

        int itemIdx = Integer.parseInt(readLine());

        switch (itemIdx) {
            case -1:
                isContinue = false;
                return;
            case ShowItem:
                showProduct();
                break;
            case SelectItem:
                selectProduct();
                break;
            case InsertCoin:
                insertCoin("Please input your Coin (Valid Coin: " + Coin.asString  +")  :");
                break;
            case ShowCart:
                showCart();
                break;
            case Buy:
                buy();
                break;
            case Refund:
                refund();
                break;
            default:
                selectProduct("Please select again!");
        }


    }

    private static void showProduct() {
        Item[] item = vendMachine.getAllItem();
        System.out.println("All Items: ");
        for (int i = 0; i < item.length; i++) {
            if (item[i] != null && item[i].getCount() > 0) {
                System.out.printf("%d : %s , price: %,d,remain: %d\n", i, item[i].getName(), item[i].getPrice(), item[i].getCount());
            }
        }
        selectProduct();
    }

    static void selectProduct() {
        selectProduct("Please enter number of Item to select (-1 : back to MainMenu): ");
    }

    static void selectProduct(String msg) {
        wrap(msg, itemIdx -> {
            try {
                vendMachine.incProduct(itemIdx);
                selectProduct("Please enter the item number to continue to choose (-1 : back to MainMenu): ");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                selectProduct("Please select again!");
            }
            return null;
        });
    }

    public static void wrap(String msg, Function<Integer, Void> f) {
        sleep();
        System.out.println(msg);
        String input = readLine();
        int option = Integer.parseInt(input);
        if (option == -1)
            return;
        f.apply(option);
    }

    static void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {

        }
    }

    static void showCart() {
        HashMap<Integer, Integer> cartItem = vendMachine.getItemInCart();
        System.out.println("Your cart: ");
        int totalPrice = 0;
        for (Map.Entry<Integer, Integer> entry : cartItem.entrySet()) {
            int itemIdx = entry.getKey();
            int quantity = entry.getValue();
            Item item = null;
            try {
                item = vendMachine.getItem(itemIdx);
                int tPrice = item.getPrice() * quantity;
                totalPrice += tPrice;
                System.out.printf("%d. %s, selected %d, price %,d, totalPrice %,d\n",
                        itemIdx, item.getName(), quantity, item.getPrice(), tPrice);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Budget: " + vendMachine.userBudget() + "\t Total Price: " + totalPrice);
    }


    private static void buy() {
        try {
            vendMachine.buy();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void insertCoin(String msg) {
        wrap(msg, coin -> {
            try {
                vendMachine.insertMoney(coin);
                insertCoin("Please insert coin to continue (-1 : back to MainMenu): ");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                insertCoin("Please insert again!");
            }

            return null;
        });
    }

    private static void refund() {
        try {
            vendMachine.refund();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void initProduct() {
        addProduct("Coke", 10000, 3);
        addProduct("Pepsi", 10000, 3);
        addProduct("Soda", 20000, 3);
    }

    public static void addProduct(String name, Integer price, Integer cnt) {
        Item item = new Item(name, price, cnt);
        try {
            vendMachine.addProduct(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static Scanner scanner = new Scanner(System.in);
    static String readLine() {
        return scanner.nextLine();
    }
}
