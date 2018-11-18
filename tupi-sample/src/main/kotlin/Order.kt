package tupi.sample

class Order(val orderId: Int, val description: String, val notas: String?, val orders: List<Item>, val invoices: MutableList<String>, val invoice: Order, val price: Float, val qualquerCoisa: Array<String>)

class Item(val id: Int, val description: String, val metadatas: List<Metadata> )

class User(val id: Int, val name: String, val metadata: Metadata)

class Metadata(val key: String, val value: String)