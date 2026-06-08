package online.jutter.fkyx.net

import online.jutter.fkyx.common.ext.httpGet
import online.jutter.fkyx.domain.DataWrapper
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.net.*
import java.util.concurrent.ConcurrentHashMap


@Component
class LocalServiceDiscoverer : CommandLineRunner {

    private val discoveredServices = ConcurrentHashMap<String, String>()

    @Throws(Exception::class)
    override fun run(vararg args: String?) {
        Thread(Runnable {
            val buffer = ByteArray(1024)
            try {
                DatagramSocket(PORT).use { socket ->
                    while (!Thread.currentThread().isInterrupted) {
                        val packet = DatagramPacket(buffer, buffer.size)
                        socket.receive(packet) // Блокирующий вызов, ждет пакет

                        val senderIp = packet.address.hostAddress
                        val message = String(packet.data, 0, packet.length).trim()

                        if (message.startsWith("APP_NAME:")) {
                            val parts = message.split(";")
                            val appName = parts[0].substringAfter("APP_NAME:")
                            val appPort = parts[1].substringAfter("PORT:")

                            val fullUrl = "http://$senderIp:$appPort"

                            discoveredServices[appName] = fullUrl
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }).start()
    }

    fun getTelescopes(): List<TelescopeInfo> {
        val resultList = mutableListOf<TelescopeInfo>()
        discoveredServices.forEach { entry ->
            val info = try {
                httpGet<TelescopeInfo>("${entry.value}/api/1.0/command/info")
            } catch (ex: Exception) {
                ex.printStackTrace()
                discoveredServices.remove(entry.key)
                null
            }
            if (info != null) resultList.add(info)
        }
        return resultList
    }

    companion object {
        private const val MULTICAST_ADDRESS = "224.0.0.1"
        private const val PORT = 8888
    }
}

data class TelescopeInfo(
    val name: String,
    val lat: Float,
    val lon: Float,
)