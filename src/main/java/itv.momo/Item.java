package itv.momo;

public class Item {
    private final String name;
    private final Integer price;
    private Integer count;

    protected Item(String name, Integer price, Integer count) {
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public String getName() {
        return this.name;
    }

    public Integer price() {
        return this.price;
    }


    public Integer getCount() {
        return count;
    }

    public void setCount(Integer cnt) {
        this.count = cnt;
    }

    public void remove(Integer cnt) {
        this.count -= cnt;
    }
}
