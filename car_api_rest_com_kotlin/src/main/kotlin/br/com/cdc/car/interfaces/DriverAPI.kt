package br.com.cdc.car.interfaces

import br.com.cdc.car.domain.Driver
import br.com.cdc.car.domain.DriverRepository
import br.com.cdc.car.interfaces.incoming.errorhandling.ErrorResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate

// @Service // Não precisa dessa anotação
@RestController
@RequestMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
class DriverAPIImpl(
    val driverRepository: DriverRepository
): DriverAPI {

    @GetMapping("/drivers")
    override fun listDrivers() : List<Driver> = driverRepository.findAll()

    @GetMapping("/drivers/{id}")
    override fun findDriver(@PathVariable("id") id: Long) : Driver = driverRepository.findById(id).orElseThrow {
        ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @PostMapping("/drivers")
    override fun createDriver(@RequestBody driver: Driver) = driverRepository.save(driver)

    @PutMapping("/drivers/{id}")
    override fun incrementalUpdateDriver(@PathVariable id: Long, @RequestBody driver: PatchDriver): Driver {
        val foundDriver = findDriver(id)
        val copyDriver = foundDriver.copy(
            name = driver.name ?: foundDriver.name,
            birthDate = driver.birthDate ?: foundDriver.birthDate
        )
        return driverRepository.save(copyDriver)
    }

    @DeleteMapping("/drivers/{id}")
    override fun deleteDriver(@PathVariable("id") id: Long) = driverRepository.delete(findDriver(id))
}

data class PatchDriver(
    val name: String?,
    val birthDate: LocalDate?
)

@Tag(name = "Driver API", description = "manipula dados de motoristas.")
interface DriverAPI {

    @Operation(description = "Lista todos os motoristas disponíveis")
    fun listDrivers() : List<Driver>
    @Operation(description = "Localiza um motorista específico", responses = [
        ApiResponse(responseCode = "200", description = "Caso o motorista tenha sido encontrado na base"),
        ApiResponse(responseCode = "404", description = "Caso o motorista não tenha sido encontrado",
            content = [Content(schema = Schema(implementation = ErrorResponse::class))]
        )
    ])
    fun findDriver(@PathVariable("id") id: Long) : Driver

    fun createDriver(@RequestBody driver: Driver): Driver

    fun incrementalUpdateDriver(@PathVariable id: Long, @RequestBody driver: PatchDriver): Driver

    fun deleteDriver(@PathVariable("id") id: Long)
}