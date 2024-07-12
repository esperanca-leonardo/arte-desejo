package com.esperanca.projects.artedesejo.domain.saleorder.controller;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;
import static org.springframework.http.HttpStatus.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SaleOrderCrudControllerE2eTest
{
  @LocalServerPort
  int port;

  @Autowired
  Flyway flyway;

  @Autowired
  Gson gson;

  static final String ISO8601_WITH_NANO = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d{0,6}";
  static final String ISO8601_WITHOUT_NANO = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}";

  @BeforeEach
  void setup()
  {
    this.flyway.migrate();

    RestAssured.port = this.port;
    RestAssured.basePath = "/sale-orders";
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
  }

  @Nested
  class FindAll
  {
    @Test
    @DisplayName("Should return 9 SaleOrders and status 200")
    void shouldReturn200AndNineSaleOrders()
    {
      given()
          .contentType(JSON)
      .when()
          .get()
      .then()
          .statusCode(OK.value())
          .body("id", hasSize(9))
          .body("total", hasItems(90.00F, 250.45F, 49.90F, 273.75F, 123.49F,
              19.90F, 299.90F, 125.90F, 179.90F)
          );
    }
  }

  @Nested
  class FindById
  {
    @Test
    @DisplayName("Should return sale order when valid ID is provided\"")
    void shouldReturnSaleOrderWhenValidIdProvided()
    {
      final int saleOrderId = 1;
      final int statusCode = OK.value();

      given()
          .contentType(JSON)
          .pathParam("saleOrderId", saleOrderId)
      .when()
          .get("/{saleOrderId}")
      .then()
          .statusCode(statusCode)
          .body("id", equalTo(saleOrderId))
          .body("createdAt", matchesPattern(ISO8601_WITH_NANO))
          .body("updatedAt", matchesPattern(ISO8601_WITH_NANO))
            .body("consumer.id", equalTo(2))
            .body("consumer.createdAt", matchesPattern(ISO8601_WITH_NANO))
            .body("consumer.updatedAt", matchesPattern(ISO8601_WITH_NANO))
            .body("consumer.name", equalTo("Bob"))
            .body("consumer.gender", equalTo("MAN"))
            .body("consumer.sexualOrientation", equalTo("GAY"))
            .body("consumer.dateOfBirth", equalTo("1985-08-20"))
            .body("consumer.phoneNumber", equalTo("(21) 91234-5678"))
            .body("consumer.email", equalTo("bob@example.com"))
            .body("consumer.cpf", equalTo("108.603.130-00"))
              .body("consumer.address.cep", equalTo("54321-876"))
              .body("consumer.address.publicPlace", equalTo("Avenida Principal"))
              .body("consumer.address.number", equalTo("456"))
              .body("consumer.address.complement", equalTo("Casa"))
              .body("consumer.address.district", equalTo("Centro"))
              .body("consumer.address.city", equalTo("Rio de Janeiro"))
              .body("consumer.address.state", equalTo("RJ"))
          .body("total", equalTo(90F));
    }

    @Test
    @DisplayName("Should return 404 and problem details when sale order not found")
    void shouldReturn404AndProblemDetailsWhenSaleOrderNotFound()
    {
      final int saleOrderId = 90;
      final int statusCode = NOT_FOUND.value();

      given()
          .contentType(JSON)
          .pathParam("saleOrderId", saleOrderId)
      .when()
          .get("/{saleOrderId}")
      .then()
          .statusCode(statusCode)
          .body("type", equalTo("https://artedesejo.com.br/problems/sale-orders/not-found"))
          .body("title", equalTo("SaleOrder not found"))
          .body("status", equalTo(statusCode))
          .body("detail", equalTo("SaleOrder not found with id: 90"))
          .body("instance", equalTo("/sale-orders/90"))
          .body("timestamp", matchesPattern(ISO8601_WITHOUT_NANO));
    }
  }

  @Nested
  class Save
  {
    @Test
    @DisplayName("Should save sale order successfully")
    void shouldSaveSaleOrderSuccessfully()
    {
      final int statusCode = CREATED.value();

      final Map<String, Object> consumerIdInput = new HashMap<>();
      consumerIdInput.put("id", 2);

      final Map<String, Object> saleOrderMap = new HashMap<>();
      saleOrderMap.put("consumerIdInput", consumerIdInput);
      saleOrderMap.put("total", 500F);

      final String json = gson.toJson(saleOrderMap);

      given()
          .body(json)
          .contentType(JSON)
      .when()
          .post()
      .then()
          .statusCode(statusCode)
          .body("id", equalTo(10))
          .body("createdAt", matchesPattern(ISO8601_WITH_NANO))
          .body("updatedAt", matchesPattern(ISO8601_WITH_NANO))
            .body("consumer.id", equalTo(2))
            .body("consumer.createdAt", matchesPattern(ISO8601_WITH_NANO))
            .body("consumer.updatedAt", matchesPattern(ISO8601_WITH_NANO))
            .body("consumer.name", equalTo("Bob"))
            .body("consumer.gender", equalTo("MAN"))
            .body("consumer.sexualOrientation", equalTo("GAY"))
            .body("consumer.dateOfBirth", equalTo("1985-08-20"))
            .body("consumer.phoneNumber", equalTo("(21) 91234-5678"))
            .body("consumer.email", equalTo("bob@example.com"))
            .body("consumer.cpf", equalTo("108.603.130-00"))
              .body("consumer.address.cep", equalTo("54321-876"))
              .body("consumer.address.publicPlace", equalTo("Avenida Principal"))
              .body("consumer.address.number", equalTo("456"))
              .body("consumer.address.complement", equalTo("Casa"))
              .body("consumer.address.district", equalTo("Centro"))
              .body("consumer.address.city", equalTo("Rio de Janeiro"))
              .body("consumer.address.state", equalTo("RJ"))
          .body("total", equalTo(saleOrderMap.get("total")));
    }

    @Test
    @DisplayName("Should return 400 and problem details when invalid data is provided")
    void shouldReturn400AndProblemDetailsWhenInvalidDataProvided()
    {
      final int statusCode = BAD_REQUEST.value();

      final Map<String, Object> consumerIdInput = new HashMap<>();
      consumerIdInput.put("id", 90);

      final Map<String, Object> saleOrderMap = new HashMap<>();
      saleOrderMap.put("consumerIdInput", consumerIdInput);
      saleOrderMap.put("total", 500F);

      final String json = gson.toJson(saleOrderMap);

      given()
          .body(json)
          .contentType(JSON)
      .when()
          .post()
      .then()
          .statusCode(statusCode)
          .body("type", equalTo("https://artedesejo.com.br/problems/invalid-data"))
          .body("title", equalTo("Invalid data"))
          .body("status", equalTo(statusCode))
          .body("detail", equalTo("One or more fields are invalid. " +
              "Please fill in the correct information and try again.")
          )
          .body("instance", equalTo("/sale-orders"))
          .body("timestamp", matchesPattern(ISO8601_WITHOUT_NANO))
          .body("fieldErrors", containsInAnyOrder(
              allOf(
                  hasEntry("field", "consumerIdInput"),
                  hasEntry("message", "Object not found")
              )
          ));
    }
  }

  @Nested
  class UpdateById
  {
    @Test
    @DisplayName("Should update sale order successfully")
    void shouldUpdateSaleOrderSuccessfully()
    {
      final int saleOrderId = 4;
      final int statusCode = OK.value();

      final Map<String, Object> consumerIdInput = new HashMap<>();
      consumerIdInput.put("id", 2);

      final Map<String, Object> saleOrderMap = new HashMap<>();
      saleOrderMap.put("consumerIdInput", consumerIdInput);
      saleOrderMap.put("total", 500F);

      final String json = gson.toJson(saleOrderMap);

      given()
          .body(json)
          .contentType(JSON)
          .pathParam("saleOrderId", saleOrderId)
      .when()
          .put("/{saleOrderId}")
      .then()
          .statusCode(statusCode)
          .body("id", equalTo(saleOrderId))
          .body("createdAt", matchesPattern(ISO8601_WITH_NANO))
          .body("updatedAt", matchesPattern(ISO8601_WITH_NANO))
            .body("consumer.id", equalTo(2))
            .body("consumer.createdAt", matchesPattern(ISO8601_WITH_NANO))
            .body("consumer.updatedAt", matchesPattern(ISO8601_WITH_NANO))
            .body("consumer.name", equalTo("Bob"))
            .body("consumer.gender", equalTo("MAN"))
            .body("consumer.sexualOrientation", equalTo("GAY"))
            .body("consumer.dateOfBirth", equalTo("1985-08-20"))
            .body("consumer.phoneNumber", equalTo("(21) 91234-5678"))
            .body("consumer.email", equalTo("bob@example.com"))
            .body("consumer.cpf", equalTo("108.603.130-00"))
              .body("consumer.address.cep", equalTo("54321-876"))
              .body("consumer.address.publicPlace", equalTo("Avenida Principal"))
              .body("consumer.address.number", equalTo("456"))
              .body("consumer.address.complement", equalTo("Casa"))
              .body("consumer.address.district", equalTo("Centro"))
              .body("consumer.address.city", equalTo("Rio de Janeiro"))
              .body("consumer.address.state", equalTo("RJ"))
          .body("total", equalTo(saleOrderMap.get("total")));
    }

    @Test
    @DisplayName("Should return 400 and problem details when invalid data is provided")
    void shouldReturn400AndProblemDetailsWhenInvalidDataProvided()
    {
      final int saleOrderId = 3;
      final int statusCode = BAD_REQUEST.value();

      final Map<String, Object> consumerIdInput = new HashMap<>();
      consumerIdInput.put("id", 90);

      final Map<String, Object> saleOrderMap = new HashMap<>();
      saleOrderMap.put("consumerIdInput", consumerIdInput);
      saleOrderMap.put("total", 500F);

      final String json = gson.toJson(saleOrderMap);

      given()
          .body(json)
          .contentType(JSON)
          .pathParam("saleOrderId", saleOrderId)
      .when()
          .put("/{saleOrderId}")
      .then()
          .statusCode(statusCode)
          .body("type", equalTo("https://artedesejo.com.br/problems/invalid-data"))
          .body("title", equalTo("Invalid data"))
          .body("status", equalTo(statusCode))
          .body("detail", equalTo("One or more fields are invalid. " +
              "Please fill in the correct information and try again.")
          )
          .body("instance", equalTo("/sale-orders/" + saleOrderId))
          .body("timestamp", matchesPattern(ISO8601_WITHOUT_NANO))
          .body("fieldErrors", containsInAnyOrder(
              allOf(
                  hasEntry("field", "consumerIdInput"),
                  hasEntry("message", "Object not found")
              )
          ));
    }

    @Test
    @DisplayName("Should return 404 and problem details when sale order not found")
    void shouldReturn404AndProblemDetailsWhenSaleOrderNotFound()
    {
      final int saleOrderId = 90;
      final int statusCode = NOT_FOUND.value();

      final Map<String, Object> consumerIdInput = new HashMap<>();
      consumerIdInput.put("id", 2);

      final Map<String, Object> saleOrderMap = new HashMap<>();
      saleOrderMap.put("consumerIdInput", consumerIdInput);
      saleOrderMap.put("total", 500F);

      final String json = gson.toJson(saleOrderMap);

      given()
          .body(json)
          .contentType(JSON)
          .pathParam("saleOrderId", saleOrderId)
      .when()
          .put("/{saleOrderId}")
      .then()
          .statusCode(statusCode)
          .body("type", equalTo("https://artedesejo.com.br/problems/sale-orders/not-found"))
          .body("title", equalTo("SaleOrder not found"))
          .body("status", equalTo(statusCode))
          .body("detail", equalTo("SaleOrder not found with id: 90"))
          .body("instance", equalTo("/sale-orders/90"))
          .body("timestamp", matchesPattern(ISO8601_WITHOUT_NANO));
    }
  }

  @Nested
  class DeleteById
  {
    @Test
    @DisplayName("Should delete sale order successfully")
    void shouldDeleteSaleOrderSuccessfully()
    {
      final int saleOrderId = 1;
      final int statusCode = NO_CONTENT.value();

      given()
          .pathParam("saleOrderId", saleOrderId)
      .when()
          .delete("/{saleOrderId}")
      .then()
          .statusCode(statusCode);
    }

    @Test
    @DisplayName("Should return 404 and problem details when sale order not found")
    void shouldReturn404AndProblemDetailsWhenSaleOrderNotFound()
    {
      final int saleOrderId = 90;
      final int statusCode = NOT_FOUND.value();

      given()
          .contentType(JSON)
          .pathParam("saleOrderId", saleOrderId)
      .when()
          .delete("/{saleOrderId}")
      .then()
          .statusCode(statusCode)
          .body("type", equalTo("https://artedesejo.com.br/problems/sale-orders/not-found"))
          .body("title", equalTo("SaleOrder not found"))
          .body("status", equalTo(statusCode))
          .body("detail", equalTo("SaleOrder not found with id: 90"))
          .body("instance", equalTo("/sale-orders/90"))
          .body("timestamp", matchesPattern(ISO8601_WITHOUT_NANO));
    }
  }
}
