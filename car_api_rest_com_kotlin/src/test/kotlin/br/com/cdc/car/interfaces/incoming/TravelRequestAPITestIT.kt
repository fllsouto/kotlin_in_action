package br.com.cdc.car.interfaces.incoming

import br.com.cdc.car.infrastructure.loadFileContent
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.*
import com.github.tomakehurst.wiremock.client.WireMock.equalTo as equalToWM
import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.http.ContentType.JSON
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.notNullValue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = DYNAMIC_PORT)
@ActiveProfiles("test")
class TravelRequestAPITestIT {

    @Autowired
    lateinit var server: WireMockServer
    @LocalServerPort
    private var port: Int = 0

    @BeforeEach
    fun setup() {
        RestAssured.baseURI = "https://localhost:$port"
        RestAssured.useRelaxedHTTPSValidation()
        RestAssured.authentication = RestAssured.basic("admin", "password")
    }

    fun setupServer() {
        val origin = "Avenida Paulista, 900"
        val destination = "Avenida Paulista, 1000"
        val apiKey = "API-KEY"
        server
            .stubFor(
                get(urlPathEqualTo("/maps/api/directions/json"))
                    .withQueryParam("origin", equalToWM(origin))
                    .withQueryParam("destination", equalToWM(destination))
                    .withQueryParam("key", equalToWM(apiKey))
                    .willReturn(
                        okJson(loadFileContent("/responses/gmaps/sample_response.json"))
                    )
            )
    }

    @Test
    fun testFindNearbyTravelRequest() {
        setupServer()

        val passengerId = given()
            .contentType(JSON)
            .body(loadFileContent("/requests/passengers_api/create_new_passenger.json"))
            .post("/passengers")
            .then()
            .statusCode(200)
            .body("id", notNullValue())
            .body("name", equalTo("John Doe"))
            .extract()
            .body()
            .jsonPath().getString("id")

        val data = mapOf<String, String>("passengerId" to passengerId)

        val travelRequestId = given()
            .contentType(JSON)
            .body(loadFileContent("/requests/travel_requests_api/create_new_request.json", data))
            .post("/travelRequests")
            .then()
            .statusCode(200)
            .body("id", notNullValue())
            .body("origin", equalTo("Avenida Paulista, 1000"))
            .body("destination", equalTo("Avenida Ipiranga, 100"))
            .body("status", equalTo("CREATED"))
            .body("_links.passenger.title", equalTo("John Doe"))
            .extract()
            .body()
            .jsonPath().getInt("id")

        given()
            .get("/travelRequests/nearby?currentAddress=Avenida Paulista, 900")
            .then()
            .statusCode(200)
            .body("[0].id", equalTo(travelRequestId))
            .body("[0].origin", equalTo("Avenida Paulista, 1000"))
            .body("[0].destination", equalTo("Avenida Ipiranga, 100"))
            .body("[0].status", equalTo("CREATED"))
    }




}