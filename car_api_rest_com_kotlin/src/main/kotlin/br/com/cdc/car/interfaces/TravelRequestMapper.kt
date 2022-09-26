package br.com.cdc.car.interfaces

import br.com.cdc.car.domain.PassengerRepository
import br.com.cdc.car.domain.TravelRequest
import br.com.cdc.car.domain.TravelRequestRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

@Component
class TravelRequestMapper(
    val passengerRepository: PassengerRepository
) {
    fun map(input: TravelRequestInput) : TravelRequest {
        val passenger = passengerRepository.findById(input.passengerId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND)
        }
        return TravelRequest(
            passenger = passenger,
            origin = input.origin,
            destination = input.destination
        )
    }
}