package online.jutter.fkyx.net

import online.jutter.fkyx.common.ext.createWrapperResponse
import online.jutter.fkyx.net.base.BASE_API_PATH
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@Suppress("Unused")
@RestController
@RequestMapping(
        value = ["$BASE_API_PATH/telescope"],
        produces = ["application/json; charset=utf-8"]
)
class TelescopeController(
    private val serviceDiscoverer: LocalServiceDiscoverer,
) {

    @RequestMapping(
        value = ["list"],
        method = [RequestMethod.GET],
    )
    fun list() = createWrapperResponse {
        serviceDiscoverer.getTelescopes()
    }
}