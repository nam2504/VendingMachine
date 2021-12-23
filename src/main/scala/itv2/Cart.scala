package itv2

import itv.momo.ExceptionMsg

import scala.collection.mutable

trait ICart {
    def addMoney(amount: Int)
    def getMoney(): Int
    def useMoney(amount: Int)

    def addItem(idx: Int, itemInContainer: Item)
    def removeItem(idx: Int, cnt: Int = 1)
    def getAllItemQuantity(): Map[Int, Int]

    def payment(price: Int)
}

class Cart extends ICart {
    private var budget = 0
    private val data = mutable.Map[Int, Int]()

    override def addMoney(amount: Int): Unit = budget += amount

    override def getMoney(): Int = budget

    override def useMoney(amount: Int): Unit = budget -= amount

    override def addItem(idx: Int, itemInContainer: Item): Unit = {
        if (itemInContainer == null) {
            throw new Exception(ExceptionMsg.SelectNonExitItem);
        }
        val cnt = data.getOrElse(idx, 0) + 1
        val remain = itemInContainer.count
        if (remain < cnt) {
            throw new Exception(ExceptionMsg.NotEnoughItemToSelect(cnt, remain));
        }

        data.put(idx, cnt);
    }

    override def removeItem(idx: Int, cnt: Int): Unit = ???

    override def getAllItemQuantity(): Map[Int, Int] = data.toMap

    override def payment(price: Int): Unit = {
        if (this.budget < price) {
            throw new Exception(ExceptionMsg.UserNotEnoughMoney(budget, price));
        }

        this.budget -= price;
        data.clear();

    }
}
