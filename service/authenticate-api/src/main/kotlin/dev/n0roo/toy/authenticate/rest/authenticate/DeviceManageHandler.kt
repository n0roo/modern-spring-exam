package dev.n0roo.toy.authenticate.rest.authenticate

import dev.n0roo.toy.authenticate.config.errors.FunctionalValidator
import dev.n0roo.toy.authenticate.rest.authenticate.dto.DevicesDto
import dev.n0roo.toy.authenticate.rest.authenticate.handler.DevicesCompositeService
import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.stereotype.Controller
import org.springframework.web.reactive.function.server.*
import java.net.URI

@Controller
class DeviceManageHandler(
    private val devicesCompositeService: DevicesCompositeService,
    private val validator: FunctionalValidator
){

    suspend fun registrationDevices(request: ServerRequest): ServerResponse {
        val params = request.bodyToMono(DevicesDto.RegistrationRequest::class.java)
            .filter{body -> validator.validate(body, DevicesDto.RegistrationRequest::class)}
            .awaitFirst()
        val appVersion = request.queryParamOrNull("app-version")!!
        val result = devicesCompositeService.registrationDevices(appVersion, params)
        return ServerResponse.created(URI("/v1/devices/${result.registrationId}")).bodyValueAndAwait(result)
    }

    suspend fun fetchDevices(request: ServerRequest): ServerResponse {
        val registrationId = request.pathVariable("registrationId")
        return ServerResponse.ok().bodyValueAndAwait(devicesCompositeService.fetchDevices(registrationId))
    }

    suspend fun pathDevices(request: ServerRequest): ServerResponse {
        val registrationId = request.pathVariable("registrationId")
        val appVersion = request.queryParamOrNull("app-version")
        val params = request.bodyToMono(DevicesDto.UpdateRequest::class.java)
            .filter{body -> validator.validate(body, DevicesDto.UpdateRequest::class)}
            .awaitFirst()
        return ServerResponse.accepted().bodyValueAndAwait(
            devicesCompositeService
        )
    }

}