package br.com.cdc.car.interfaces.outcoming

import com.jayway.jsonpath.JsonPath
import net.minidev.json.JSONArray
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class GMapsService(
    // Trocar o perfil de execução da app para dev
    @Value("\${app.car.domain.googlemaps.apikey}")
    val appKey: String
) {
    val GMAPS_TEMPLATE: String = "https://maps.googleapis.com/maps/api/directions/json?origin={origin}&destination={destination}&key={key}"
    val DISTANCE_JSON_PATH_EXPRESSION = "\$..legs[*].duration.value"
    // No jsonpath o $.. é utilizado para fazer uma busca em toda a árvore, enquanto que $. procura apenas no primeiro
    // nível
    // [*] serve para pegar todos os elementos da lista
    // A expressão completa é: $..legs[*].duration.value
    fun getDistanceBetweenAddresses(addressOne: String, addressTwo: String): Int {
        val template = RestTemplate()
        val jsonResult = template.getForObject(GMAPS_TEMPLATE, String::class.java, addressOne, addressTwo, appKey)

        val rawResult: JSONArray = JsonPath.parse(jsonResult).read(DISTANCE_JSON_PATH_EXPRESSION)
        return rawResult.map { it as Int }.minOrNull() ?: Int.MAX_VALUE
    }
}