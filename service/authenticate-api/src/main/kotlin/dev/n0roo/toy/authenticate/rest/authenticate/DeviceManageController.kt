package dev.n0roo.toy.authenticate.rest.authenticate

import dev.n0roo.toy.authenticate.rest.authenticate.dto.DevicesDto
import dev.n0roo.toy.authenticate.rest.authenticate.handler.DevicesCompositeHandler
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping(value = ["/v1/devices"])
class DeviceManageController
constructor(
    private val devicesCompositeService: DevicesCompositeHandler
){

    @PostMapping(value = [""])
    suspend fun registrationDevices(
        @RequestParam("app-version", required = true) appVersion: String,
        @RequestBody(required = true) params: DevicesDto.RegistrationRequest
    ): ResponseEntity<DevicesDto.CommandResponse> {
        val result = devicesCompositeService.registrationDevices(appVersion, params)
        return ResponseEntity.created(URI("/v1/devices/${result.registrationId}")).body(result)
    }

}