package br.com.cdc.car.interfaces.incoming

import io.restassured.RestAssured
import io.restassured.RestAssured.basic
import io.restassured.RestAssured.given
import io.restassured.http.ContentType.JSON
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.notNullValue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PassengerAPITestIT {
    @LocalServerPort
    private var port: Int = 0

    @BeforeEach
    fun setup() {
        RestAssured.baseURI = "https://localhost:$port"
        RestAssured.useRelaxedHTTPSValidation()
        RestAssured.authentication = basic("admin", "password")
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