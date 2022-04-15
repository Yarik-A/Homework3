const val SECONDS_IN_MINUTE = 60
const val SECONDS_IN_HOUR = 3600
const val SECONDS_IN_DAY = 24 * 3600

fun main() {

    print("Количество секунд: ")
    val secondsAgo = readln().toInt()
    println(agoToText(secondsAgo, ::agoMinutes, ::agoHours))

}

fun agoToText(secondsAgo: Int,
              agoMinutes: (Int) -> String,
              agoHours: (Int) -> String): String {
    val agoToText = when (secondsAgo) {
        in 0..60 -> "только что"
        in 61..SECONDS_IN_HOUR -> "был(а) ${agoMinutes(secondsAgo)} назад"
        in (SECONDS_IN_HOUR + 1)..SECONDS_IN_DAY -> "был(а) ${agoHours(secondsAgo)} назад"
        in (SECONDS_IN_DAY + 1)..(SECONDS_IN_DAY * 2) -> "был(а) сегодня"
        in (SECONDS_IN_DAY * 2 + 1)..(SECONDS_IN_DAY * 3) -> "был(а) вчера"
        else -> "давно"
    }
    return agoToText
}

fun agoMinutes(secondsAgo: Int): String {
    val minutesAgo = secondsAgo / SECONDS_IN_MINUTE

    val agoMinutesText = if (minutesAgo % 10 == 1 && minutesAgo != 11) {
        "$minutesAgo минуту"
    } else if (minutesAgo % 10 in 2..4 && minutesAgo !in 12..14) {
        "$minutesAgo минуты"
    } else if (minutesAgo % 10 in 5..9 || minutesAgo % 10 == 0) {
            "$minutesAgo минут"
    } else {
        "$minutesAgo минут"
    }

    return agoMinutesText
}

fun agoHours(secondsAgo: Int): String {
    val hoursAgo = secondsAgo / SECONDS_IN_HOUR

    val agoHoursText = if (hoursAgo % 10 == 1 && hoursAgo != 11) {
        "$hoursAgo час"
    } else if (hoursAgo % 10 in 2..4 && hoursAgo !in 12..14) {
        "$hoursAgo часа"
    } else if (hoursAgo % 10 in 5..9 || hoursAgo % 10 == 0) {
        "$hoursAgo часов"
    } else {
        "$hoursAgo часов"
    }

    return agoHoursText
}
