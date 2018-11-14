package tupi.processor.test

data class ProductInformationItem(
    val productId: Long,
    val cardTypeId: Long,
    val productDescription: String,
    val cardDescription: String,
    val annuity: String = "0.00",
    val annuityMaxInstallments: Int = 0,
    val debitAllowed: Boolean = true,
    val creditAllowed: Boolean = true,
    val isInternational: Boolean = true,
    val taxText: String = "PTAX (câmbio oficial do BC) venda + 4% + IOF (6,38%). A cotação do dólar é na" +
            " data da compra.",
    val regenerationTax: String
)