package itv.momo;

import java.util.HashMap;
import java.util.Map;

interface IProductContainer {
    public Integer add(Item item) throws Exception;
    public void add(Integer idx, Item item) throws Exception;
    public void update(Integer idx, Item item) throws Exception;
    public Item getItem(Integer idx);
    public Integer estimate(HashMap<Integer, Integer> itemCnt);
}

public class ProductContainer implements IProductContainer {
    private final Item[] data;
    private Integer cnt;


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

    public void add(Integer idx, Item item) throws Exception {
        if (item == null) {
            throw new Exception(ExceptionMsg.AddInvalidItem);
        }

        if (data[idx] != null) {
            throw new Exception(ExceptionMsg.AddItemInExitedIndex(idx));
        }

        _add(idx, item);
    }

    public void update(Integer idx, Item item) throws Exception {
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
        return data[idx];
    }

    @Override
    public Integer estimate(HashMap<Integer, Integer> itemCnt) {
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : itemCnt.entrySet()) {
            Item item = getItem(entry.getKey());
            if (item != null)
                sum += item.getPrice() * entry.getValue();
        }
        return sum;
    }
}
