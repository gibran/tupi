package tupi.sample

open class Order (val orderId : Int, val description : String, val notas : String?, val orders: List<Order>, val invoices : MutableList<String>, val invoice : Order, val price : Float, val qualquercoisa : Array<String>)
