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
        value = ["$BASE_API_PATH/test"],
        produces = ["application/json; charset=utf-8"]
)
class TestConnection {

    @Value("\${app.api-key}")
    private val apiKey: String? = null

    @Value("\${app.point-name}")
    private val pointName: String? = null

    @RequestMapping(
        value = ["test"],
        method = [RequestMethod.POST],
    )
    fun test(@RequestBody request: TestConnectionRequest) = createWrapperResponse {
        if (request.key == apiKey) {
            TestConnectionResponse(
                pointName = pointName ?: "null",
            )
        } else error("wrong key")
    }
}

class TestConnectionRequest(
    val key: String,
    val version: String,
)

class TestConnectionResponse(
    val pointName: String,
)