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
        RestAssured.port = port
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
    fun testCreatePassenger() {
        val createPassengerJSON = """
            {"name" : "John Doe"}
        """.trimIndent()

        given()
            .contentType(JSON)
            . body(createPassengerJSON)
            .post("/passengers")
            .then()
            .statusCode(200)
            .body("id", notNullValue())
            .body("name", equalTo("John Doe"))
    }

}