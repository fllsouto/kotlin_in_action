package br.com.cdc.car.interfaces

import br.com.cdc.car.domain.TravelService
import br.com.cdc.car.domain.TravelRequestStatus
import br.com.cdc.car.interfaces.incoming.mapping.TravelRequestMapper
import org.springframework.hateoas.EntityModel
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import javax.validation.Valid
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@RestController
@RequestMapping(path = ["/travelRequests"], produces = [MediaType.APPLICATION_JSON_VALUE])
class TravelRequestAPI(
    val travelService: TravelService,
    val mapper: TravelRequestMapper
) {


    @PostMapping
    fun makeTravelRequest(@RequestBody @Valid travelRequestInput: TravelRequestInput): EntityModel<TravelRequestOutput> {
        val travelRequest = travelService.saveTravelRequest(mapper.map(travelRequestInput))
        val output = mapper.map(travelRequest)
        return mapper.buildOutputModel(travelRequest, output)
    }

    @GetMapping("/nearby")
    fun listNearbyRequests(@RequestParam currentAddress: String): List<EntityModel<TravelRequestOutput>> {
        val requests = travelService.listNearbyTravelRequests(currentAddress)
        return mapper.buildOutputModel(requests)
    }
}

data class TravelRequestInput(
    @get:NotNull(message = "O campo passengerId não pode ser nulo")
    val passengerId: Long?,
    @get:NotEmpty(message = "O campo origin não pode ser vazio")
    val origin: String?,
    @get:NotEmpty(message = "O campo destination não pode ser vazio")
    val destination: String?
)
data class TravelRequestOutput(
    val id: Long,
    val origin: String,
    val destination: String,
    val status: TravelRequestStatus,
    val creationDate: LocalDateTime
)

