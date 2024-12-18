package dev.n0roo.toy.authenticate.rest.authenticate

import dev.n0roo.toy.authenticate.rest.authenticate.dto.DevicesDto
import dev.n0roo.toy.authenticate.rest.authenticate.handler.DevicesCompositeService
import org.springframework.stereotype.Controller
import org.springframework.web.reactive.function.server.*
import java.net.URI

@Controller
class DeviceManageHandler
constructor(
    private val devicesCompositeService: DevicesCompositeService
){

    suspend fun registrationDevices(request: ServerRequest): ServerResponse {
        val params = request.awaitBody<DevicesDto.RegistrationRequest>()
        val appVersion = request.queryParamOrNull("app-version")!!
        val result = devicesCompositeService.registrationDevices(appVersion, params)
        return ServerResponse.created(URI("/v1/devices/${result.registrationId}")).bodyValueAndAwait(result)
    }

}