package br.com.cdc.car.interfaces

import br.com.cdc.car.domain.TravelRequestService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/travelRequests"], produces = [MediaType.APPLICATION_JSON_VALUE])
class TravelRequestAPI(
    val travelService: TravelRequestService,
    val mapper: TravelRequestMapper
) {


    @PostMapping
    fun makeTravelRequest(@RequestBody travelRequestInput: TravelRequestInput) {
        val travelRequest = mapper.map(travelRequestInput)
        travelService.saveTravelRequest(travelRequest)
    }

}

data class TravelRequestInput(
    val passengerId: Long,
    val origin: String,
    val destination: String
)

