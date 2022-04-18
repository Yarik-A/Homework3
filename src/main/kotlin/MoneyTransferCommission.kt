const val VK = "0"
const val MASTER_MAESTRO = "1"
const val VISA_MIR = "2"

fun main() {

    // Выбор карты
    println("Выберите тип карты:")
    println("VK Pay - 0")
    println("MasterCard или Maestro - 1")
    println("Visa или Мир - 2")
    print("Тип карты: ")
    val cardType = readln()

    // Сумма перевода
    println("Сумма перевода в рублях: ")
    val amountKop = readln().toInt() * 100

    // Сумма перевода в рублях
    print("Сумма переводов в текущем месяце в рублях: ")
    val monthlyTransferAmountKop = readln().toInt() * 100

    // Проверка лимитов и вывод комиссии
    if (cardType == VK && (amountKop > 1500000 || (monthlyTransferAmountKop + amountKop) > 4000000)) {
        println("Вы превысили лимит")
    } else if (cardType == MASTER_MAESTRO && (amountKop > 15_000_000 || (monthlyTransferAmountKop + amountKop) > 60_000_000)) {
        println("Вы превысили лимит")
    } else if (cardType == VISA_MIR && (amountKop > 15_000_000 || (monthlyTransferAmountKop + amountKop) > 60_000_000)) {
        println("Вы превысили лимит")
    } else {
        val commission = commissionToPay(cardType, monthlyTransferAmountKop, amountKop)
        println("Комиссия за перевод составляет ${commission / 100} рублей ${commission % 100} копеек")
    }
}

fun commissionToPay(cardType: String = VK,
                    monthlyTransferAmountKop: Int = 0,
                    amountKop: Int): Int {

    val commission = when (cardType) {
        MASTER_MAESTRO -> if (monthlyTransferAmountKop + amountKop <= 7_500_000) {
                0
            } else {
                (amountKop * 0.006 + 2000).toInt()
            }
        VISA_MIR -> if (amountKop * 0.0075 < 3500) {
                3_500
            } else {
                (amountKop * 0.0075).toInt()
            }
        else -> 0
    }

    return commission
}