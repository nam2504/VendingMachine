package itv2

import itv.momo.ExceptionMsg

trait IMoneyControl {
    def addCoin(coin: Int, count: Int = 1)
    def removeCoin(coin: Int, count: Int = 1)
    def refund(amount: Int) : Map[Int, Int]
}

class MoneyControl extends IMoneyControl {
    private val data = collection.mutable.Map[Int, Int]()


    override def addCoin(coin: Int, count: Int): Unit = {
        if (!Coin.isValid(coin)) {
            throw new Exception(ExceptionMsg.InsertCoinInValid(coin))
        }
        data.putOrUpdate(coin, count, _ + count)
    }

    override def removeCoin(coin: Int, count: Int): Unit = {
        val cnt = data.getOrElse(coin, 0) - count
        data.put(coin, cnt)
    }

    override def refund(amount: Int): Map[Int, Int] = {
        val refundCoin = collection.mutable.Map[Int, Int]()
        var remain = amount
        for (coin <- data.keys.toArray.sorted.reverse) {
            if (remain > coin) {
                val cnt = Math.min(remain / coin, data(coin))
                remain -= cnt * coin
                refundCoin.put(coin, cnt)
            }
        }
        refundCoin.toMap
    }

    implicit class MutableMapHelper[K, V](m: collection.mutable.Map[K, V]) {
        def putOrUpdate(k: K, v: V, update: (V => V)) {
            m.get(k) match {
                case Some(value) => m.put(k, update(value))
                case _ => m.put(k, v)
            }
        }
    }
}
