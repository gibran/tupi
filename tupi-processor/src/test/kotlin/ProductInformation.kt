package tupi.processor.test

data class ProductInformation(
    val products: ArrayList<ProductInformationItem>,
    val limitValue: String,

    val order : Order,
    val list: MutableList<String>
)