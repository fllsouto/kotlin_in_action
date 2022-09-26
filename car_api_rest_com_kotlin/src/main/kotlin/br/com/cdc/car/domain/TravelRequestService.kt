package br.com.cdc.car.domain

import org.springframework.stereotype.Component

@Component
class TravelRequestService(
    val travelRequestRepository: TravelRequestRepository
) {
    fun saveTravelRequest(travelRequest: TravelRequest) = travelRequestRepository.save(travelRequest)
}