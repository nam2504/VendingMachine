package itv2

import itv.momo.ExceptionMsg

trait IProductContainer {
    def addProduct(item: Item): Int
    def getItem(idx: Int): Item
    def getAllProduct(): Array[Item]
    def estimate(itemCnt : Map[Int, Int]): Int
}

class ProductContainer(size: Int = 4) extends IProductContainer {
    private val data = Array.fill[Item](size)(null)
    private var count = 0;

    override def addProduct(item: Item) = {
        if (count == size) {
            throw new Exception(ExceptionMsg.FullItem)
        }
        val idx = data.indexOf(null)
        data(idx) = item
        idx
    }

    override def getItem(idx: Int) = {
        if (idx < 0 || idx >= size) {
            throw new Exception(ExceptionMsg.SelectNonExitItem)
        }
        data(idx)
    }

    override def getAllProduct(): Array[Item] = data

    override def estimate(itemCnt: Map[Int, Int]) = {
        var sum = 0
        for ((idx, cnt) <- itemCnt) {
            sum += cnt * data(idx).price
        }
        sum
    }
}
