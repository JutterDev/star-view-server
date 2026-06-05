package online.jutter.fkyx.common.ext

import java.net.URL

/**
 * Просто http хапрос, вынесен в экстеншон чтоб,
 * в будущем отловить ошибки.
 */
fun httpGet(url: String): String {
    return URL(url).readText()
}