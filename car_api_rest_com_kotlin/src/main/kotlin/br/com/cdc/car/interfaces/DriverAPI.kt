package br.com.cdc.car.interfaces

import br.com.cdc.car.domain.Driver
import br.com.cdc.car.domain.DriverRepository
import br.com.cdc.car.interfaces.incoming.errorhandling.ErrorResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.PageRequest
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.Link
import org.springframework.hateoas.server.mvc.linkTo
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate

// @Service // Não precisa dessa anotação
@RestController
@RequestMapping(produces = [MediaType.APPLICATION_JSON_VALUE], path = ["/drivers"])
class DriverAPIImpl(
    val driverRepository: DriverRepository
): DriverAPI {

    @GetMapping
    override fun listDrivers(page: Int) : Drivers {
        val driverPage = driverRepository.findAll(PageRequest.of(page, PAGE_SIZE))
        val drivers = driverPage.content.map { EntityModel.of(it) }
        val lastPageLink = linkTo<DriverAPIImpl> { listDrivers(driverPage.totalPages - 1) }.withRel("lastPage")
        return Drivers(drivers, listOf(lastPageLink))
    }

    companion object {
        private const val PAGE_SIZE: Int = 20
    }

    @GetMapping("/{id}")
    override fun findDriver(@PathVariable("id") id: Long) : Driver = driverRepository.findById(id).orElseThrow {
        ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @PostMapping
    override fun createDriver(@RequestBody driver: Driver) = driverRepository.save(driver)

    @PutMapping("/{id}")
    override fun incrementalUpdateDriver(@PathVariable id: Long, @RequestBody driver: PatchDriver): Driver {
        val foundDriver = findDriver(id)
        val copyDriver = foundDriver.copy(
            name = driver.name ?: foundDriver.name,
            birthDate = driver.birthDate ?: foundDriver.birthDate
        )
        return driverRepository.save(copyDriver)
    }

    @DeleteMapping("/{id}")
    override fun deleteDriver(@PathVariable("id") id: Long) = driverRepository.delete(findDriver(id))
}

open class Drivers(
    val drivers: List<EntityModel<Driver>>,
    val links: List<Link> = emptyList()
)

data class PatchDriver(
    val name: String?,
    val birthDate: LocalDate?
)

@Tag(name = "Driver API", description = "manipula dados de motoristas.")
interface DriverAPI {

    @Operation(description = "Lista todos os motoristas disponíveis")
    fun listDrivers(@RequestParam(name = "page", defaultValue = "0") page: Int) : Drivers
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