package itv2

trait IVendMachine extends IProductContainer {

    def insertMoney(coin: Int)
    def buy()
    def refund()
    def getUserBudget()

    def getItemInCar(): Map[Int, Int]
    def incItemToCar(idx: Int)

}

class VendMachine(productSize: Int = 4) extends IVendMachine {
    val moneyController: IMoneyControl = new MoneyControl()
    val productContainer: IProductContainer = new ProductContainer(productSize)
    val cart: ICart = new Cart()

    override def insertMoney(coin: Int): Unit = ???

    override def buy(): Unit = ???

    override def refund(): Unit = ???

    override def getUserBudget(): Unit = ???

    override def getItemInCar(): Map[Int, Int] = ???

    override def incItemToCar(idx: Int): Unit = ???

    override def addProduct(item: Item): Int = ???

    override def getItem(idx: Int): Item = ???

    override def getAllProduct(): Array[Item] = ???

    override def estimate(itemCnt: Map[Int, Int]): Int = ???
}
