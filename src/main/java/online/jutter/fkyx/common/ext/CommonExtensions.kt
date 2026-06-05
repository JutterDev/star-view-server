package online.jutter.fkyx.common.ext

import java.math.BigInteger
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.*


/**
 * Получение дайджеста
 * строки в MD5.
 *
 * @author Solyanoy Leonid (solyanoy.leonid@gmail.com)
 */
fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(this.toByteArray())).toString(16).padStart(32, '0')
}

/**
 * Получение дайджеста
 * строки в SHA-256.
 *
 * @author Solyanoy Leonid (solyanoy.leonid@gmail.com)
 */
fun String.digestSHA256(): String {
    val messageDigest = MessageDigest.getInstance("SHA-256")
    messageDigest.update(this.toByteArray())
    return String(messageDigest.digest())
}

/**
 * Получение UUID строки.
 */
fun getUUID(): String {
    return UUID.randomUUID().toString()
}

fun <T> MutableList<T>.addNotNull(item: T?) {
    if (item != null) this.add(item)
}

fun String.toCalendar(): Calendar {
    val cal = Calendar.getInstance()
    val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH)
    cal.time = sdf.parse(this)
    return cal
}


fun Calendar.toDateString(): String {
    val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH)
    return sdf.format(this.time)
}