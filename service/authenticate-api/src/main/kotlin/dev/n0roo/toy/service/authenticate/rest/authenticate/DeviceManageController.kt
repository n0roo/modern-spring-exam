package dev.n0roo.toy.service.authenticate.rest.authenticate

import dev.n0roo.toy.service.authenticate.rest.authenticate.dto.DevicesDto
import dev.n0roo.toy.service.authenticate.rest.authenticate.services.DevicesCompositeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/v1/devices"])
class DeviceManageController
constructor(
    private val devicesCompositeService: DevicesCompositeService
){

    @PostMapping(value = [""])
    fun registrationDevices(appVersion: String, params: DevicesDto.RegistrationRequest): ResponseEntity<DevicesDto.CommandResponse> {
        return ResponseEntity.ok(devicesCompositeService.registrationDevices(appVersion, params))
    }
}