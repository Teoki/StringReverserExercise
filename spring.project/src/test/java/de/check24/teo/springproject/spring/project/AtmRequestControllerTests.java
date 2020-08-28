package de.check24.teo.springproject.spring.project;

import de.check24.teo.springproject.spring.project.extern.in.rest.AtmRequestController;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static de.check24.teo.springproject.spring.project.AppConfiguration.ATM111;
import static de.check24.teo.springproject.spring.project.extern.in.rest.AtmRequestController.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AtmRequestControllerTests {


    @LocalServerPort
    private int port;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = port;
    }

    @Test
    @DirtiesContext
    public void testDepositPositive() {
        getRequestSpecification()
                .pathParam(MENU_IDS, "22,5")
                .queryParam(AMOUNT, "500")
                .get(AtmRequestController.URI)
                .then()
                .statusCode(200)
                .assertThat().body(equalTo("Neuer Kontostand: " + 1500))
        ;
    }

    private RequestSpecification getRequestSpecification() {
        return given()
                .pathParam(ATM_ID, ATM111)
                .queryParam(CARD_ID, "1234567")
                .queryParam(PIN, "0987");
    }

    @Test
    @DirtiesContext
    public void testDepositNegative() {
        getRequestSpecification()
                .pathParam(MENU_IDS, "22,6")
                .queryParam(AMOUNT, "500")
                .get(AtmRequestController.URI)
                .then()
                .statusCode(418);
    }

    @Test
    @DirtiesContext
    public void testWithDrawPositive() {
        getRequestSpecification()
                .pathParam(MENU_IDS, "11,1,444")
                //.queryParam(AMOUNT, 500)
                .get(AtmRequestController.URI)
                .then()
                .statusCode(200)
                .assertThat().body(equalTo("Neuer Kontostand: 950"));
    }

    @Test
    @DirtiesContext
    public void testWithDrawNegative() {
        getRequestSpecification()
                .pathParam(MENU_IDS, "11,1")
                .queryParam(AMOUNT)
                .get(AtmRequestController.URI)
                .then()
                .statusCode(418);
    }

    @Test
    @DirtiesContext
    public void testWithDrawNegativeNoCardId() {
        given()
                .pathParam(ATM_ID, ATM111)
                .queryParam(CARD_ID, "")
                .pathParam(MENU_IDS, "11,1,444")
                .queryParam(PIN, "0987")
                //.queryParam(AMOUNT, 500)
                .get(AtmRequestController.URI)
                .then()
                .statusCode(400)
                .assertThat()
                .header("X-ERROR", "CardId is strange");
    }

    @Test
    @DirtiesContext
    public void testWithDrawNegativeNoPin() {
        given()
                .pathParam(ATM_ID, ATM111)
                .queryParam(CARD_ID, "01234567")
                .pathParam(MENU_IDS, "11,1,444")
                .queryParam(PIN, "")
                //.queryParam(AMOUNT, 500)
                .get(AtmRequestController.URI)
                .then()
                .statusCode(400)
                .assertThat()
                .header("X-ERROR", "Pin is strange");
    }

    @Test
    @DirtiesContext
    public void testWithDrawNegativePin() {
        given()
                .pathParam(ATM_ID, ATM111)
                .queryParam(CARD_ID, "1234567")
                .pathParam(MENU_IDS, "11,1,444")
                .queryParam(PIN, "0000")

                //.queryParam(AMOUNT, 500)
                .get(AtmRequestController.URI)
                .then()
                .statusCode(418)
                .assertThat()
                .header("X-ERROR", "Incorrect pin");
    }
}
