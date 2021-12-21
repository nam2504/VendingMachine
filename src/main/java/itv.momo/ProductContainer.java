package itv.momo;

interface IProductContainer {
    public Integer add(Item item) throws Exception;
    public void add(Integer idx, Item item) throws Exception;
    public void update(Integer idx, Item item) throws Exception;
    public Item getItem(Integer idx);
}

public class ProductContainer implements IProductContainer {
    private Item[] data;
    private Integer cnt;

//    public ProductContainer(Item[] data) {
//        this.data = data;
//        this.cnt = 0;
//        for (Item item : data) {
//            if (item != null)
//                this.cnt += 1;
//        }
//    }

    public ProductContainer(Integer size) {
        this.data = new Item[size];
        this.cnt = 0;
    }

    public ProductContainer() {
        this.data = new Item[4];
        this.cnt = 0;
    }

    public Integer add(Item item) throws Exception {
        if (cnt == data.length) {
            throw new Exception("Full item!!!"); //TODO: define new Exception
        }

        for (int i = 0; i < data.length; i++) {
            if (data[i] == null) {
                _add(i, item);
                return i;
            }
        }

        return -1;
    }

    public void add(Integer idx, Item item) throws Exception {
        if (item == null) {
            throw new Exception("Adding Invalid item!!!");
        }

        if (data[idx] != null) {
            throw new Exception("Adding item in exited Item. Idx: %d !!!");
        }

        _add(idx, item);
    }

    public void update(Integer idx, Item item) throws Exception {
        if (item == null) {
            throw new Exception("Updating Invalid item!!!");
        }

        if (0 < idx || idx >= data.length) {
            throw new Exception("Updating invalid index %d!!!");
        }

        if (data[idx] != null) {
            remove(idx);
        }
        _add(idx, item);
    }


    protected void _add(Integer idx, Item item) {
        cnt += 1;
        data[idx] = item;
    }

    protected Item remove(Integer idx) {
        cnt -= 1;
        Item item = data[idx];
        data[idx] = null;
        return item;
    }

    public Item getItem(Integer idx) {
        return null;
    }
}