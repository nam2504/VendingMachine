package itv2

object Coin {
    private val ValidCoins = Set(10000, 20000, 50000, 100000, 200000)

    def isValid(coin: Int) = ValidCoins.contains(coin)

    val asString: String = ValidCoins.mkString(", ")

}
