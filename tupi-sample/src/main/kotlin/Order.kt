package tupi.sample

class Order(val orderId: Int, val description: String, val notas: String?, val orders: List<Item>, val invoices: MutableList<String>, val invoice: Order, val price: Float, val qualquerCoisa: Array<String>)

class Item(val id: Int, val description: String)

class User(val id: Int, val name: String)