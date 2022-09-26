package br.com.cdc.car.interfaces

import br.com.cdc.car.domain.Passenger
import br.com.cdc.car.domain.PassengerRepository
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping(path = ["/passengers"], produces = [MediaType.APPLICATION_JSON_VALUE])
class PassengerAPI(
    val passengerRepository: PassengerRepository
) {

    @GetMapping
    fun listPassengers() : List<Passenger> = passengerRepository.findAll()

    @GetMapping("/{id}")
    fun findPassenger(@PathVariable("id") id: Long) : Passenger = passengerRepository.findById(id).orElseThrow {
        ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @PostMapping
    fun createPassenger(@RequestBody passenger: Passenger) = passengerRepository.save(passenger)

    @PutMapping("/{id}")
    fun incrementalUpdatePassenger(@PathVariable id: Long, @RequestBody passenger: PatchPassenger): Passenger {
        val foundPassenger = findPassenger(id)
        val copyPassenger = foundPassenger.copy(
            name = passenger.name ?: foundPassenger.name,
        )
        return passengerRepository.save(copyPassenger)
    }

    @DeleteMapping("/{id}")
    fun deletePassenger(@PathVariable("id") id: Long) = passengerRepository.delete(findPassenger(id))

}

data class PatchPassenger(
    val name: String?
)