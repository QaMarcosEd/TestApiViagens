package com.montanha.gerenciador.utils;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;

public class TokenViagem {

    public static String obterTokenAdmin() {
        return given()
                .body("{\n" +
                        " \"email\": \"admin@email.com\",\n" +
                        " \"senha\": \"654321\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .post("/v1/auth")
                .then()
                .extract()
                .path("data.token");
    }

    public static String obterTokenUsuario() {
        return given()
                .body("{\n" +
                        "  \"email\": \"usuario@email.com\",\n" +
                        "  \"senha\": \"123456\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .post("/v1/auth")
                .then()
                .extract()
                .path("data.token");
    }
}

