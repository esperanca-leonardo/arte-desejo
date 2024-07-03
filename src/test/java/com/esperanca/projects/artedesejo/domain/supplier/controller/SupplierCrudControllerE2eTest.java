package com.esperanca.projects.artedesejo.domain.supplier.controller;

import io.restassured.RestAssured;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;
import static org.springframework.http.HttpStatus.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SupplierCrudControllerE2eTest
{
  @LocalServerPort
  int port;

  @Autowired
  Flyway flyway;

  @BeforeEach
  void setup()
  {
    this.flyway.migrate();

    RestAssured.port = this.port;
    RestAssured.basePath = "/suppliers";
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
  }

  @Nested
  class FindAll
  {
    @Test
    @DisplayName("Should return 10 suppliers and status code 200")
    void shouldReturn10SuppliersWithStatus200()
    {
      given()
          .accept(JSON)
      .when()
          .get()
      .then()
          .statusCode(OK.value())
          .body("name", hasSize(10))
          .body("name", hasItems("Sensual Pleasures", "Passionate Desires",
              "Intimate Moments", "Eros Essentials", "Silk Seductions",
              "Desire Delights", "Pleasure Palace", "Lustful Lingerie",
              "Fetish Fantasy", "Seductive Secrets")
          );
    }
  }

  @Nested
  class FindById
  {
    @Test
    @DisplayName("Should return a supplier when a valid supplier ID is provided")
    void shouldReturnSupplierWhenValidIdIsProvided()
    {
      given()
          .accept(JSON)
          .pathParam("supplierId", 1)
      .when()
          .get("/{supplierId}")
      .then()
          .statusCode(OK.value())
          .body("name", equalTo("Sensual Pleasures"));
    }

    @Test
    @DisplayName("Should return 404 and problem details when supplier is not found")
    void shouldReturn404AndProblemDetailsWhenSupplierNotFound()
    {
      final int invalidSupplierId = 90;
      final int statusCode = NOT_FOUND.value();

      given()
          .accept(JSON)
          .pathParam("supplierId", invalidSupplierId)
      .when()
          .get("/{supplierId}")
      .then()
          .statusCode(statusCode)
          .body("type", equalTo("https://artedesejo.com.br/problems/supplier/not-found"))
          .body("title", equalTo("Supplier not found"))
          .body("status", equalTo(statusCode))
          .body("detail", equalTo("Supplier not found with id: " + invalidSupplierId))
          .body("instance", equalTo("/suppliers/" + invalidSupplierId))
          .body("timestamp", notNullValue());
    }
  }

  @Nested
  class Save
  {
    @Test
    @DisplayName("Should create a supplier successfully and return 201 status code")
    void shouldCreateSupplierSuccessfully()
    {
      given()
          .body("{ \"name\": \"Test\" }")
          .contentType(JSON)
      .when()
          .post()
      .then()
          .statusCode(CREATED.value())
          .body("id", equalTo(11))
          .body("name", equalTo("Test"));
    }
  }

  @Nested
  class UpdateById
  {
    @Test
    @DisplayName("Should return an updated supplier with the correct details")
    void shouldReturnUpdatedSupplierWithCorrectDetails()
    {
      final int supplierId = 1;

      given()
          .body("{ \"name\": \"Test\" }")
          .contentType(JSON)
          .pathParam("supplierId", supplierId)
      .when()
          .put("/{supplierId}")
      .then()
          .statusCode(OK.value())
          .body("id", equalTo(supplierId))
          .body("name", equalTo("Test"));
    }

    @Test
    @DisplayName("Should return 404 and problem details when supplier is not found")
    void shouldReturn404AndProblemDetailsWhenSupplierNotFound()
    {
      final int invalidSupplierId = 90;
      final int statusCode = NOT_FOUND.value();

      given()
          .body("{ \"name\": \"Test\" }")
          .contentType(JSON)
          .pathParam("supplierId", invalidSupplierId)
      .when()
          .put("/{supplierId}")
      .then()
          .statusCode(statusCode)
          .body("type", equalTo("https://artedesejo.com.br/problems/supplier/not-found"))
          .body("title", equalTo("Supplier not found"))
          .body("status", equalTo(statusCode))
          .body("detail", equalTo("Supplier not found with id: " + invalidSupplierId))
          .body("instance", equalTo("/suppliers/" + invalidSupplierId))
          .body("timestamp", notNullValue());
    }
  }

  @Nested
  class DeleteById
  {
    @Test
    @DisplayName("Should delete a supplier successfully and return 204 status code")
    void shouldDeleteSupplierSuccessfully()
    {
      final int supplierId = 10;

      given()
          .pathParam("supplierId", supplierId)
      .when()
          .delete("/{supplierId}")
      .then()
          .statusCode(NO_CONTENT.value());
    }

    @Test
    @DisplayName("Should return 404 and problem details when supplier is not found during deletion")
    void shouldReturn404AndProblemDetailsWhenSupplierNotFound()
    {
      final int supplierId = 11;
      final int statusCode = NOT_FOUND.value();

      given()
          .pathParam("supplierId", supplierId)
      .when()
          .delete("/{supplierId}")
      .then()
          .statusCode(statusCode)
          .body("type", equalTo("https://artedesejo.com.br/problems/supplier/not-found"))
          .body("title", equalTo("Supplier not found"))
          .body("status", equalTo(statusCode))
          .body("detail", equalTo("Supplier not found with id: " + supplierId))
          .body("instance", equalTo("/suppliers/" + supplierId))
          .body("timestamp", notNullValue());
    }

    @Test
    @DisplayName("Should return 409 and problem details when supplier is in use and cannot be deleted")
    void shouldReturn409AndProblemDetailsWhenSupplierInUse()
    {
      final int supplierId = 1;
      final int statusCode = CONFLICT.value();

      given()
          .pathParam("supplierId", supplierId)
      .when()
          .delete("/{supplierId}")
      .then()
          .statusCode(statusCode)
          .body("type", equalTo("https://artedesejo.com.br/problems/supplier/in-use"))
          .body("title", equalTo("Supplier in use"))
          .body("status", equalTo(statusCode))
          .body("detail", equalTo("Supplier in use with id: " + supplierId))
          .body("instance", equalTo("/suppliers/" + supplierId))
          .body("timestamp", notNullValue());
    }
  }
}
