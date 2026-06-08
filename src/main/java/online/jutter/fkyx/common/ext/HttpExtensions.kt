package online.jutter.fkyx.common.ext

import com.google.gson.Gson
import java.net.URL

/**
 * Просто http хапрос, вынесен в экстеншон чтоб,
 * в будущем отловить ошибки.
 */
fun httpGet(url: String): String {
    return URL(url).readText()
}

inline fun <reified T> httpGet(url: String): T {
    return Gson().fromJson(URL(url).readText(), T::class.java)
}