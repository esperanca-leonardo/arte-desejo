package com.esperanca.projects.artedesejo.domain.consumer.controller;

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
import static java.lang.String.format;
import static org.hamcrest.Matchers.*;
import static org.springframework.http.HttpStatus.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConsumerCrudControllerE2eTest
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
    RestAssured.basePath = "/consumers";
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
  }

  @Nested
  class FindAll
  {
    @Test
    @DisplayName("Should return 10 consumers and 200 status code")
    void shouldReturnTenConsumersAndStatus200()
    {
      given()
          .contentType(JSON)
          .when()
          .get()
          .then()
          .statusCode(OK.value())
          .body("name", hasSize(10))
          .body("name", hasItems(
              "Alice", "Bob", "Carol", "David", "Emma", "Frank", "Gina",
              "Henry", "Iris", "Jack"
              )
          );
    }
  }

  @Nested
  class FindById
  {
    @Test
    @DisplayName("Should return a consumer when valid id is provided")
    void shouldReturnConsumerWhenValidId()
    {
      final int consumerId = 1;
      final int statusCode = OK.value();

      given()
          .pathParam("consumerId", consumerId)
      .when()
          .get("/{consumerId}")
      .then()
          .statusCode(statusCode)
          .body("id", equalTo(consumerId))
          .body("name", equalTo("Alice"))
          .body("gender", equalTo("WOMAN"))
          .body("sexualOrientation", equalTo("STRAIGHT"))
          .body("dateOfBirth", equalTo("1990-05-15"))
          .body("phoneNumber", equalTo("(11) 98765-4321"))
          .body("email", equalTo("alice@example.com"))
          .body("cpf", equalTo("012.413.320-70"))
          .body("address.cep", equalTo("12345-678"))
          .body("address.publicPlace", equalTo("Rua das Flores"))
          .body("address.number", equalTo("123"))
          .body("address.complement", equalTo("Apto 101"))
          .body("address.district", equalTo("Centro"))
          .body("address.city", equalTo("São Paulo"))
          .body("address.state", equalTo("SP"))
          .body("createdAt", matchesPattern(ISO8601_WITH_NANO))
          .body("updatedAt", matchesPattern(ISO8601_WITH_NANO));
    }

    @Test
    @DisplayName("Should return 404 and problem details when invalid id is provided")
    void shouldReturn404AndProblemDetailsWhenInvalidId()
    {
      final int consumerId = 90;
      final int statusCode = NOT_FOUND.value();

      given()
          .pathParam("consumerId", consumerId)
          .when()
          .get("/{consumerId}")
          .then()
          .statusCode(statusCode)
          .body("type", equalTo("https://artedesejo.com.br/problems/consumer/not-found"))
          .body("title", equalTo("Consumer not found"))
          .body("status", equalTo(statusCode))
          .body("detail", equalTo("Consumer not found with id: " + consumerId))
          .body("instance", equalTo("/consumers/" + consumerId))
          .body("timestamp", matchesPattern(ISO8601_WITHOUT_NANO));
    }
  }

  @Nested
  class Save
  {
    @Test
    @DisplayName("Should save a consumer successfully")
    void shouldSaveConsumerSuccessfully()
    {
      final int statusCode = CREATED.value();
      final Map<String, Object> consumer = createConsumerWithValidData();
      final String json = gson.toJson(consumer);

      given()
          .body(json)
          .contentType(JSON)
      .when()
          .post()
      .then()
          .statusCode(statusCode)
          .body("id", equalTo(11))
          .body("name", equalTo(consumer.get("name")))
          .body("gender", equalTo(consumer.get("gender")))
          .body("sexualOrientation", equalTo(consumer.get("sexualOrientation")))
          .body("dateOfBirth", equalTo(consumer.get("dateOfBirth")))
          .body("phoneNumber", equalTo(consumer.get("phoneNumber")))
          .body("email", equalTo(consumer.get("email")))
          .body("cpf", equalTo(consumer.get("cpf")))
          .body("address.cep", equalTo("98765-432"))
          .body("address.publicPlace", equalTo("Avenida Paulista"))
          .body("address.number", equalTo("987"))
          .body("address.complement", equalTo("Sala 101"))
          .body("address.district", equalTo("Bela Vista"))
          .body("address.city", equalTo("São Paulo"))
          .body("address.state", equalTo("SP"))
          .body("createdAt", matchesPattern(ISO8601_WITH_NANO))
          .body("updatedAt", matchesPattern(ISO8601_WITH_NANO));
    }

    @Test
    @DisplayName("Should return 409 and problem details when CPF is already registered")
    void shouldReturn409AndProblemDetailsWhenCpfAlreadyRegistered()
    {
      final int statusCode = 409;
      final Map<String, Object> consumer = createConsumerWithCpfAlreadyRegistered();
      String json = gson.toJson(consumer);

      given()
          .body(json)
          .contentType(JSON)
      .when()
          .post()
      .then()
          .statusCode(statusCode)
          .body("type", equalTo("https://artedesejo.com.br/problems/consumer/cpf-already-exists"))
          .body("title", equalTo("CPF already exists"))
          .body("status", equalTo(statusCode))
          .body("detail", equalTo(
              format("CPF: %s already exists", consumer.get("cpf")))
          )
          .body("instance", equalTo("/consumers"))
          .body("timestamp", matchesPattern(ISO8601_WITHOUT_NANO));
    }

    @Test
    @DisplayName("Should return 409 and problem details when Email is already registered")
    void shouldReturn409AndProblemDetailsWhenEmailAlreadyRegistered()
    {
      final int statusCode = 409;
      final Map<String, Object> consumer = createConsumerWithEmailAlreadyRegistered();
      String json = gson.toJson(consumer);

      given()
          .body(json)
          .contentType(JSON)
      .when()
          .post()
      .then()
          .statusCode(statusCode)
          .body("type", equalTo("https://artedesejo.com.br/problems/consumer/email-already-exists"))
          .body("title", equalTo("Email already exists"))
          .body("status", equalTo(statusCode))
          .body("detail", equalTo(
              format("Email: %s already exists", consumer.get("email")))
          )
          .body("instance", equalTo("/consumers"))
          .body("timestamp", matchesPattern(ISO8601_WITHOUT_NANO));
    }

    @Test
    @DisplayName("Should return 409 and problem details when Phone number is " +
        "already registered")
    void shouldReturn409AndProblemDetailsWhenPhoneNumberAlreadyRegistered()
    {
      final int statusCode = 409;
      final Map<String, Object> consumer = createConsumerWithPhoneNumberAlreadyRegistered();
      String json = gson.toJson(consumer);

      given()
          .body(json)
          .contentType(JSON)
      .when()
          .post()
      .then()
          .statusCode(statusCode)
          .body("type", equalTo("https://artedesejo.com.br/problems/consumer/phone-number-already-exists"))
          .body("title", equalTo("Phone number already exists"))
          .body("status", equalTo(statusCode))
          .body("detail", equalTo(
              format("Phone number: %s already exists", consumer.get(
                  "phoneNumber")))
          )
          .body("instance", equalTo("/consumers"))
          .body("timestamp", matchesPattern(ISO8601_WITHOUT_NANO));
    }

    @Test
    @DisplayName("Should return 400 and problem details when data is invalid")
    void shouldReturn400AndProblemDetailsWhenInvalidData()
    {
      final int statusCode = BAD_REQUEST.value();
      final Map<String, Object> consumer = createConsumerWithInvalidData();
      final String json = gson.toJson(consumer);

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
          .body("instance", equalTo("/consumers"))
          .body("timestamp", matchesPattern(ISO8601_WITHOUT_NANO))
          .body("fieldErrors", containsInAnyOrder(
              allOf(
                  hasEntry("field", "email"),
                  hasEntry("message", "must be a well-formed email address")
              ),
              allOf(
                  hasEntry("field", "name"),
                  hasEntry("message", "must not be blank")
              ),
              allOf(
                  hasEntry("field", "cpf"),
                  hasEntry("message", "invalid Brazilian individual taxpayer registry number (CPF)")
              )
          ));
    }
  }

  @Nested
  class UpdateById
  {
    @Test
    @DisplayName("Should update consumer successfully")
    void shouldUpdateConsumerSuccessfully()
    {
      final int consumerId = 1;
      final int statusCode = OK.value();
      final Map<String, Object> consumer = createConsumerWithValidData();
      final String json = gson.toJson(consumer);

      given()
          .body(json)
          .contentType(JSON)
          .pathParam("consumerId", consumerId)
      .when()
          .put("/{consumerId}")
      .then()
          .statusCode(statusCode)
          .body("id", equalTo(consumerId))
          .body("name", equalTo(consumer.get("name")))
          .body("gender", equalTo(consumer.get("gender")))
          .body("sexualOrientation", equalTo(consumer.get("sexualOrientation")))
          .body("dateOfBirth", equalTo(consumer.get("dateOfBirth")))
          .body("phoneNumber", equalTo(consumer.get("phoneNumber")))
          .body("email", equalTo(consumer.get("email")))
          .body("cpf", equalTo(consumer.get("cpf")))
          .body("address.cep", equalTo("98765-432"))
          .body("address.publicPlace", equalTo("Avenida Paulista"))
          .body("address.number", equalTo("987"))
          .body("address.complement", equalTo("Sala 101"))
          .body("address.district", equalTo("Bela Vista"))
          .body("address.city", equalTo("São Paulo"))
          .body("address.state", equalTo("SP"))
          .body("createdAt", matchesPattern(ISO8601_WITH_NANO))
          .body("updatedAt", matchesPattern(ISO8601_WITH_NANO));
    }

    @Test
    @DisplayName("Should return 404 and problem details when invalid id is provided")
    void shouldReturn404AndProblemDetailsWhenInvalidId()
    {
      final int consumerId = 90;
      final int statusCode = NOT_FOUND.value();
      final Map<String, Object> consumer = createConsumerWithValidData();
      final String json = gson.toJson(consumer);

      given()
          .body(json)
          .contentType(JSON)
          .pathParam("consumerId", 90)
      .when()
          .put("/{consumerId}")
      .then()
          .body("type", equalTo("https://artedesejo.com.br/problems/consumer/not-found"))
          .body("title", equalTo("Consumer not found"))
          .body("status", equalTo(statusCode))
          .body("detail", equalTo("Consumer not found with id: " + consumerId))
          .body("instance", equalTo("/consumers/" + consumerId))
          .body("timestamp", matchesPattern(ISO8601_WITHOUT_NANO));
    }

    @Test
    @DisplayName("Should return 409 and problem details when CPF is already registered")
    void shouldReturn409AndProblemDetailsWhenCpfAlreadyRegistered()
    {
      final int consumerId = 1;
      final int statusCode = 409;
      final Map<String, Object> consumer = createConsumerWithCpfAlreadyRegistered();
      final String json = gson.toJson(consumer);

      given()
          .body(json)
          .contentType(JSON)
          .pathParam("consumerId", consumerId)
      .when()
          .put("/{consumerId}")
      .then()
          .statusCode(statusCode)
          .body("type", equalTo("https://artedesejo.com.br/problems/consumer/cpf-already-exists"))
          .body("title", equalTo("CPF already exists"))
          .body("status", equalTo(statusCode))
          .body("detail", equalTo(
              format("CPF: %s already exists", consumer.get("cpf")))
          )
          .body("instance", equalTo("/consumers/" + consumerId))
          .body("timestamp", matchesPattern(ISO8601_WITHOUT_NANO));
    }

    @Test
    @DisplayName("Should return 409 and problem details when Email is already registered")
    void shouldReturn409AndProblemDetailsWhenEmailAlreadyRegistered()
    {
      final int consumerId = 2;
      final int statusCode = 409;
      final Map<String, Object> consumer = createConsumerWithEmailAlreadyRegistered();
      final String json = gson.toJson(consumer);

      given()
          .body(json)
          .contentType(JSON)
          .pathParam("consumerId", consumerId)
      .when()
          .put("/{consumerId}")
      .then()
          .statusCode(statusCode)
          .body("type", equalTo("https://artedesejo.com.br/problems/consumer/email-already-exists"))
          .body("title", equalTo("Email already exists"))
          .body("status", equalTo(statusCode))
          .body("detail", equalTo(
              format("Email: %s already exists", consumer.get("email")))
          )
          .body("instance", equalTo("/consumers/" + consumerId))
          .body("timestamp", matchesPattern(ISO8601_WITHOUT_NANO));
    }

    @Test
    @DisplayName("Should return 409 and problem details when Phone number is " +
        "already registered")
    void shouldReturn409AndProblemDetailsWhenPhoneNumberAlreadyRegistered()
    {
      final int consumerId = 2;
      final int statusCode = 409;
      final Map<String, Object> consumer = createConsumerWithPhoneNumberAlreadyRegistered();
      final String json = gson.toJson(consumer);

      given()
          .body(json)
          .contentType(JSON)
          .pathParam("consumerId", consumerId)
      .when()
          .put("/{consumerId}")
      .then()
          .statusCode(statusCode)
          .body("type", equalTo("https://artedesejo.com.br/problems/consumer/phone-number-already-exists"))
          .body("title", equalTo("Phone number already exists"))
          .body("status", equalTo(statusCode))
          .body("detail", equalTo(
              format("Phone number: %s already exists", consumer.get(
                  "phoneNumber")))
          )
          .body("instance", equalTo("/consumers/" + consumerId))
          .body("timestamp", matchesPattern(ISO8601_WITHOUT_NANO));
    }

    @Test
    @DisplayName("Should return 400 and problem details when data is invalid")
    void shouldReturn400AndProblemDetailsWhenInvalidData()
    {
      final int consumerId = 1;
      final int statusCode = BAD_REQUEST.value();
      final Map<String, Object> consumer = createConsumerWithInvalidData();
      final String json = gson.toJson(consumer);

      given()
          .body(json)
          .contentType(JSON)
          .pathParam("consumerId", consumerId)
      .when()
          .put("/{consumerId}")
      .then()
          .statusCode(statusCode)
          .body("type", equalTo("https://artedesejo.com.br/problems/invalid-data"))
          .body("title", equalTo("Invalid data"))
          .body("status", equalTo(statusCode))
          .body("detail", equalTo("One or more fields are invalid. " +
              "Please fill in the correct information and try again.")
          )
          .body("instance", equalTo("/consumers/" + consumerId))
          .body("timestamp", matchesPattern(ISO8601_WITHOUT_NANO))
          .body("fieldErrors", containsInAnyOrder(
              allOf(
                  hasEntry("field", "email"),
                  hasEntry("message", "must be a well-formed email address")
              ),
              allOf(
                  hasEntry("field", "name"),
                  hasEntry("message", "must not be blank")
              ),
              allOf(
                  hasEntry("field", "cpf"),
                  hasEntry("message",
                      "invalid Brazilian individual taxpayer registry number (CPF)"
                  )
              )
          ));
    }
  }

  @Nested
  class DeleteById
  {
    @Test
    @DisplayName("Should delete consumer successfully")
    void shouldDeleteConsumerSuccessfully()
    {
      final int consumerId = 1;
      final int statusCode = NO_CONTENT.value();

      given()
          .pathParam("consumerId", consumerId)
      .when()
          .delete("/{consumerId}")
      .then()
          .statusCode(statusCode);
    }

    @Test
    @DisplayName("Should return 404 and problem details when invalid id is provided")
    void shouldReturn404AndProblemDetailsWhenInvalidId()
    {
      final int consumerId = 90;
      final int statusCode = NOT_FOUND.value();

      given()
          .pathParam("consumerId", consumerId)
      .when()
          .delete("/{consumerId}")
      .then()
          .statusCode(statusCode)
          .body("type", equalTo("https://artedesejo.com.br/problems/consumer/not-found"))
          .body("title", equalTo("Consumer not found"))
          .body("status", equalTo(statusCode))
          .body("detail", equalTo("Consumer not found with id: " + consumerId))
          .body("instance", equalTo("/consumers/" + consumerId))
          .body("timestamp", matchesPattern(ISO8601_WITHOUT_NANO));
    }
  }

  Map<String, Object> createConsumerWithValidData()
  {
    final Map<String, Object> consumerAddress = new HashMap<>();
    consumerAddress.put("cep", "98765-432");
    consumerAddress.put("publicPlace", "Avenida Paulista");
    consumerAddress.put("number", "987");
    consumerAddress.put("complement", "Sala 101");
    consumerAddress.put("district", "Bela Vista");
    consumerAddress.put("city", "São Paulo");
    consumerAddress.put("state", "SP");

    final Map<String, Object> consumerData = new HashMap<>();
    consumerData.put("name", "Mariana Oliveira");
    consumerData.put("gender", "WOMAN");
    consumerData.put("sexualOrientation", "STRAIGHT");
    consumerData.put("dateOfBirth", "1992-04-15");
    consumerData.put("phoneNumber", "+5511987654321");
    consumerData.put("email", "mariana.oliveira@example.com");
    consumerData.put("cpf", "538.296.000-38");
    consumerData.put("address", consumerAddress);

    return consumerData;
  }

  Map<String, Object> createConsumerWithInvalidData()
  {
    final Map<String, Object> consumerAddress = new HashMap<>();
    consumerAddress.put("cep", "98765-432");
    consumerAddress.put("publicPlace", "Avenida Paulista");
    consumerAddress.put("number", "987");
    consumerAddress.put("complement", "Sala 101");
    consumerAddress.put("district", "Bela Vista");
    consumerAddress.put("city", "São Paulo");
    consumerAddress.put("state", "SP");

    final Map<String, Object> consumerData = new HashMap<>();
    consumerData.put("name", "");
    consumerData.put("gender", "WOMAN");
    consumerData.put("sexualOrientation", "STRAIGHT");
    consumerData.put("dateOfBirth", "1992-04-15");
    consumerData.put("phoneNumber", "+5511987654321");
    consumerData.put("email", "mariana.oliveiraexample.com");
    consumerData.put("cpf", "538.296.000-385");
    consumerData.put("address", consumerAddress);

    return consumerData;
  }

  Map<String, Object> createConsumerWithCpfAlreadyRegistered()
  {
    final Map<String, Object> consumerAddress = new HashMap<>();
    consumerAddress.put("cep", "98765-432");
    consumerAddress.put("publicPlace", "Avenida Paulista");
    consumerAddress.put("number", "987");
    consumerAddress.put("complement", "Sala 101");
    consumerAddress.put("district", "Bela Vista");
    consumerAddress.put("city", "São Paulo");
    consumerAddress.put("state", "SP");

    final Map<String, Object> consumerData = new HashMap<>();
    consumerData.put("name", "Mariana Oliveira");
    consumerData.put("gender", "WOMAN");
    consumerData.put("sexualOrientation", "STRAIGHT");
    consumerData.put("dateOfBirth", "1992-04-15");
    consumerData.put("phoneNumber", "+5511987654321");
    consumerData.put("email", "mariana.oliveira@example.com");
    consumerData.put("cpf", "332.732.040-36");
    consumerData.put("address", consumerAddress);

    return consumerData;
  }

  Map<String, Object> createConsumerWithEmailAlreadyRegistered()
  {
    final Map<String, Object> consumerAddress = new HashMap<>();
    consumerAddress.put("cep", "98765-432");
    consumerAddress.put("publicPlace", "Avenida Paulista");
    consumerAddress.put("number", "987");
    consumerAddress.put("complement", "Sala 101");
    consumerAddress.put("district", "Bela Vista");
    consumerAddress.put("city", "São Paulo");
    consumerAddress.put("state", "SP");

    final Map<String, Object> consumerData = new HashMap<>();
    consumerData.put("name", "Mariana Oliveira");
    consumerData.put("gender", "WOMAN");
    consumerData.put("sexualOrientation", "STRAIGHT");
    consumerData.put("dateOfBirth", "1992-04-15");
    consumerData.put("phoneNumber", "+5511987654321");
    consumerData.put("email", "alice@example.com");
    consumerData.put("cpf", "660.589.530-49");
    consumerData.put("address", consumerAddress);

    return consumerData;
  }

  Map<String, Object> createConsumerWithPhoneNumberAlreadyRegistered()
  {
    final Map<String, Object> consumerAddress = new HashMap<>();
    consumerAddress.put("cep", "98765-432");
    consumerAddress.put("publicPlace", "Avenida Paulista");
    consumerAddress.put("number", "987");
    consumerAddress.put("complement", "Sala 101");
    consumerAddress.put("district", "Bela Vista");
    consumerAddress.put("city", "São Paulo");
    consumerAddress.put("state", "SP");

    final Map<String, Object> consumerData = new HashMap<>();
    consumerData.put("name", "Mariana Oliveira");
    consumerData.put("gender", "WOMAN");
    consumerData.put("sexualOrientation", "STRAIGHT");
    consumerData.put("dateOfBirth", "1992-04-15");
    consumerData.put("phoneNumber", "(11) 98765-4321");
    consumerData.put("email", "mariana.oliveira@example.com");
    consumerData.put("cpf", "773.514.010-04");
    consumerData.put("address", consumerAddress);

    return consumerData;
  }
}
