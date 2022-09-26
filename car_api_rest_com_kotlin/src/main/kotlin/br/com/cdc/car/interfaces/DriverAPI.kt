package br.com.cdc.car.interfaces

import br.com.cdc.car.domain.Driver
import br.com.cdc.car.domain.DriverRepository
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate

// @Service // Não precisa dessa anotação
@RestController
@RequestMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
class DriverAPI(
    val driverRepository: DriverRepository
) {

    @GetMapping("/drivers")
    fun listDrivers() : List<Driver> = driverRepository.findAll()

    @GetMapping("/drivers/{id}")
    fun findDriver(@PathVariable("id") id: Long) : Driver = driverRepository.findById(id).orElseThrow {
        ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @PostMapping("/drivers")
    fun createDriver(@RequestBody driver: Driver) = driverRepository.save(driver)

    @PutMapping("/drivers/{id}")
    fun incrementalUpdateDriver(@PathVariable id: Long, @RequestBody driver: PatchDriver): Driver {
        val foundDriver = findDriver(id)
        val copyDriver = foundDriver.copy(
            name = driver.name ?: foundDriver.name,
            birthDate = driver.birthDate ?: foundDriver.birthDate
        )
        return driverRepository.save(copyDriver)
    }

    @DeleteMapping("/drivers/{id}")
    fun deleteDriver(@PathVariable("id") id: Long) = driverRepository.delete(findDriver(id))

}

data class PatchDriver(
    val name: String?,
    val birthDate: LocalDate?
)