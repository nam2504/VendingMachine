package itv.momo;

import java.util.HashMap;
import java.util.Map;

interface IProductContainer {
    public int add(Item item) throws Exception;
    public void add(int idx, Item item) throws Exception;
    public void update(int idx, Item item) throws Exception;
    public Item getItem(int idx) throws Exception;
    public Item[] getAllItem();
    public int estimate(HashMap<Integer, Integer> itemCnt);
}

public class ProductContainer implements IProductContainer {
    private final Item[] data;
    private int cnt;


    public ProductContainer(int size) {
        this.data = new Item[size];
        this.cnt = 0;
    }

    public ProductContainer() {
        this.data = new Item[4];
        this.cnt = 0;
    }

    public int add(Item item) throws Exception {
        if (cnt == data.length) {
            throw new Exception(ExceptionMsg.FullItem);
        }

        for (int i = 0; i < data.length; i++) {
            if (data[i] == null) {
                _add(i, item);
                return i;
            }
        }

        return -1;
    }

    public void add(int idx, Item item) throws Exception {
        if (item == null) {
            throw new Exception(ExceptionMsg.AddInvalidItem);
        }

        if (data[idx] != null) {
            throw new Exception(ExceptionMsg.AddItemInExitedIndex(idx));
        }

        _add(idx, item);
    }

    public void update(int idx, Item item) throws Exception {
        if (item == null) {
            throw new Exception(ExceptionMsg.UpdateInvalidItem);
        }

        if (0 < idx || idx >= data.length) {
            throw new Exception(ExceptionMsg.UpdateInvalidIndex(idx));
        }

        if (data[idx] != null) {
            remove(idx);
        }
        _add(idx, item);
    }


    protected void _add(int idx, Item item) {
        cnt += 1;
        data[idx] = item;
    }

    protected Item remove(int idx) {
        cnt -= 1;
        Item item = data[idx];
        data[idx] = null;
        return item;
    }

    public Item getItem(int idx) throws Exception {
        if (idx >= data.length) {
            throw new Exception(ExceptionMsg.SelectNonExitItem);
        }
        return data[idx];
    }

    @Override
    public Item[] getAllItem() {
        return data;
    }

    @Override
    public int estimate(HashMap<Integer, Integer> itemCnt) {
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : itemCnt.entrySet()) {
            Item item = null;
            try {
                item = getItem(entry.getKey());
            } catch (Exception ignored) {

            }
            if (item != null)
                sum += item.getPrice() * entry.getValue();
        }
        return sum;
    }
}
