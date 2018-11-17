package tupi.processor.test.dtos

class complexClass (val id: Int, val description: String?, val items: MutableList<item>)

class item (val id: Int, val description: String, val price: Double)