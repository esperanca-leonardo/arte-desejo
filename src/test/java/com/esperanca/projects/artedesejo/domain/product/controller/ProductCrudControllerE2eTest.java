package com.esperanca.projects.artedesejo.domain.product.controller;

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
public class ProductCrudControllerE2eTest
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
    RestAssured.basePath = "/products";
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
  }

  @Nested
  class FindAll
  {
    @Test
    @DisplayName("Should return 10 products and status code 200")
    void shouldReturn10ProductsAndStatus200()
    {
      given()
          .contentType(JSON)
      .when()
          .get()
      .then()
          .statusCode(OK.value())
          .body("name", hasSize(10))
          .body("name", hasItems("Vibrador Clássico", "Gel Lubrificante",
              "Camisinha Texturizada", "Plug Anal", "Calcinha comestível",
              "Anel Peniano", "Fantasia de Enfermeira", "Vela de Massagem",
              "Bolinhas Tailandesas", "Fetiche Algema")
          );
    }
  }

  @Nested
  class FindById
  {
    @Test
    @DisplayName("Should return a product when a valid ID is provided")
    void shouldReturnProductWhenValidIdIsProvided()
    {
      final int productId = 6;

      given()
          .contentType(JSON)
          .pathParam("productId", productId)
      .when()
          .get("/{productId}")
      .then()
          .statusCode(OK.value())
          .body("id", equalTo(productId))
          .body("name", equalTo("Anel Peniano"))
          .body("description", equalTo("Anel peniano com vibração."))
          .body("color", equalTo("Azul"))
          .body("brand", equalTo("Durex"))
          .body("flavor", nullValue())
          .body("sensation", equalTo("Vibração"))
          .body("category", equalTo("Brinquedos"))
          .body("subCategory", equalTo("Anéis"))
          .body("targetAudience", equalTo("Adultos"))
          .body("size", equalTo("Ajustável"))
          .body("fabric", equalTo("Silicone"))
          .body("additionalInformation", equalTo("Bateria incluída"))
          .body("quantity", equalTo(60))
          .body("stockQuantity", equalTo(45))
          .body("costPrice", equalTo(9.90F))
          .body("salePrice", equalTo(19.90F))
          .body("supplier.id", equalTo(3))
          .body("supplier.name", equalTo("Intimate Moments"))
          .body("supplier.createdAt", matchesPattern(ISO8601_WITH_NANO))
          .body("supplier.updatedAt", matchesPattern(ISO8601_WITH_NANO))
          .body("createdAt", matchesPattern(ISO8601_WITH_NANO))
          .body("updatedAt", matchesPattern(ISO8601_WITH_NANO));
    }

    @Test
    @DisplayName("Should return 404 and problem details when product is not found")
    void shouldReturn404AndProblemDetailsWhenProductNotFound()
    {
      final int productId = 90;
      final int statusCode = NOT_FOUND.value();

      given()
          .accept(JSON)
          .pathParam("productId", productId)
      .when()
          .get("/{productId}")
      .then()
          .statusCode(statusCode)
          .body("type", equalTo("https://artedesejo.com.br/problems/product/not-found"))
          .body("title", equalTo("Product not found"))
          .body("status", equalTo(statusCode))
          .body("detail", equalTo("Product not found with id: " + productId))
          .body("instance", equalTo("/products/" + productId))
          .body("timestamp", matchesPattern(ISO8601_WITHOUT_NANO));
    }
  }

  @Nested
  class Save
  {
    @Test
    @DisplayName("Should create a product successfully and return 201 status code")
    void shouldCreateProductSuccessfully()
    {
      final Map<String, Object> product = createProductWithValidData();
      final String json = gson.toJson(product);

      given()
          .body(json)
          .contentType(JSON)
      .when()
          .post()
      .then()
          .statusCode(CREATED.value())
          .body("id", equalTo(11))
          .body("name", equalTo(product.get("name")))
          .body("description", equalTo(product.get("description")))
          .body("color", equalTo(product.get("color")))
          .body("brand", equalTo(product.get("brand")))
          .body("flavor", equalTo(product.get("flavor")))
          .body("sensation", equalTo(product.get("sensation")))
          .body("category", equalTo(product.get("category")))
          .body("subCategory", equalTo(product.get("subCategory")))
          .body("targetAudience", equalTo(product.get("targetAudience")))
          .body("size", equalTo(product.get("size")))
          .body("fabric", equalTo(product.get("fabric")))
          .body("additionalInformation", equalTo(product.get("additionalInformation")))
          .body("quantity", equalTo(product.get("quantity")))
          .body("stockQuantity", equalTo(product.get("stockQuantity")))
          .body("costPrice", equalTo(product.get("costPrice")))
          .body("salePrice", equalTo(product.get("salePrice")))
          .body("supplier.id", equalTo(1))
          .body("supplier.name", equalTo("Sensual Pleasures"))
          .body("supplier.createdAt", matchesPattern(ISO8601_WITH_NANO))
          .body("supplier.updatedAt", matchesPattern(ISO8601_WITH_NANO))
          .body("createdAt", matchesPattern(ISO8601_WITH_NANO))
          .body("updatedAt", matchesPattern(ISO8601_WITH_NANO));
    }

    @Test
    @DisplayName("Should return a 400 status code and problem details when " +
        "a invalid data is provided")
    void shouldReturn400AndProblemDetailsWhenInvalidDataIsProvided()
    {
      final int statusCode = BAD_REQUEST.value();
      final Map<String, Object> product = createProductWithInvalidData();
      final String json = gson.toJson(product);

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
          .body("instance", equalTo("/products"))
          .body("timestamp", matchesPattern(ISO8601_WITHOUT_NANO))
          .body("fieldErrors", hasSize(7))
          .body("fieldErrors", containsInAnyOrder(
              allOf(
                  hasEntry("field", "name"),
                  hasEntry("message", "must not be blank")
              ),
              allOf(
                  hasEntry("field", "quantity"),
                  hasEntry("message", "must be greater than or equal to 0")
              ),
              allOf(
                  hasEntry("field", "salePrice"),
                  hasEntry("message", "must be greater than or equal to 0")
              ),
              allOf(
                  hasEntry("field", "stockQuantity"),
                  hasEntry("message", "must be greater than or equal to 0")
              ),
              allOf(
                  hasEntry("field", "supplierIdInput"),
                  hasEntry("message", "Object not found")
              ),
              allOf(
                  hasEntry("field", "brand"),
                  hasEntry("message", "must not be blank")
              ),
              allOf(
                  hasEntry("field", "costPrice"),
                  hasEntry("message", "must be greater than or equal to 0")
              )
          ));
    }
  }

  @Nested
  class UpdateById
  {
    @Test
    void shouldReturnUpdatedProductWithCorrectDetails()
    {
      final int productId = 1;
      final int statusCode = OK.value();
      final Map<String, Object> product = createProductWithValidData();
      final String json = gson.toJson(product);

      given()
          .body(json)
          .contentType(JSON)
          .pathParam("productId", productId)
      .when()
          .put("/{productId}")
      .then()
          .statusCode(statusCode)
          .body("id", equalTo(productId))
          .body("name", equalTo(product.get("name")))
          .body("description", equalTo(product.get("description")))
          .body("color", equalTo(product.get("color")))
          .body("brand", equalTo(product.get("brand")))
          .body("flavor", equalTo(product.get("flavor")))
          .body("sensation", equalTo(product.get("sensation")))
          .body("category", equalTo(product.get("category")))
          .body("subCategory", equalTo(product.get("subCategory")))
          .body("targetAudience", equalTo(product.get("targetAudience")))
          .body("size", equalTo(product.get("size")))
          .body("fabric", equalTo(product.get("fabric")))
          .body("additionalInformation", equalTo(product.get("additionalInformation")))
          .body("quantity", equalTo(product.get("quantity")))
          .body("stockQuantity", equalTo(product.get("stockQuantity")))
          .body("costPrice", equalTo(product.get("costPrice")))
          .body("salePrice", equalTo(product.get("salePrice")))
          .body("supplier.id", equalTo(1))
          .body("supplier.name", equalTo("Sensual Pleasures"))
          .body("supplier.createdAt", matchesPattern(ISO8601_WITH_NANO))
          .body("supplier.updatedAt", matchesPattern(ISO8601_WITH_NANO))
          .body("createdAt", matchesPattern(ISO8601_WITH_NANO))
          .body("updatedAt", matchesPattern(ISO8601_WITH_NANO));
    }

    @Test
    void shouldReturn404AndProblemDetailsWhenProductNotFound()
    {
      final int productId = 90;
      final int statusCode = NOT_FOUND.value();
      final Map<String, Object> product = createProductWithValidData();
      final String json = gson.toJson(product);

      given()
          .body(json)
          .contentType(JSON)
          .pathParam("productId", productId)
      .when()
          .put("/{productId}")
      .then()
          .statusCode(statusCode)
          .body("type", equalTo("https://artedesejo.com.br/problems/product/not-found"))
          .body("title", equalTo("Product not found"))
          .body("status", equalTo(statusCode))
          .body("detail", equalTo("Product not found with id: " + productId))
          .body("instance", equalTo("/products/" + productId))
          .body("timestamp", matchesPattern(ISO8601_WITHOUT_NANO));
    }

    @Test
    void shouldReturnStatusCode400AndErrorMessageWhenInvalidFieldsAreSent()
    {
      final int productId = 9;
      final int statusCode = BAD_REQUEST.value();
      final Map<String, Object> product = createProductWithInvalidData();
      final String json = gson.toJson(product);

      given()
          .body(json)
          .contentType(JSON)
          .pathParam("productId", productId)
      .when()
          .put("/{productId}")
      .then()
          .statusCode(statusCode)
          .body("type", equalTo("https://artedesejo.com.br/problems/invalid-data"))
          .body("title", equalTo("Invalid data"))
          .body("status", equalTo(statusCode))
          .body("detail", equalTo("One or more fields are invalid. " +
              "Please fill in the correct information and try again.")
          )
          .body("instance", equalTo("/products/" + productId))
          .body("timestamp", matchesPattern(ISO8601_WITHOUT_NANO))
          .body("fieldErrors", hasSize(7))
          .body("fieldErrors", containsInAnyOrder(
              allOf(
                  hasEntry("field", "name"),
                  hasEntry("message", "must not be blank")
              ),
              allOf(
                  hasEntry("field", "quantity"),
                  hasEntry("message", "must be greater than or equal to 0")
              ),
              allOf(
                  hasEntry("field", "salePrice"),
                  hasEntry("message", "must be greater than or equal to 0")
              ),
              allOf(
                  hasEntry("field", "stockQuantity"),
                  hasEntry("message", "must be greater than or equal to 0")
              ),
              allOf(
                  hasEntry("field", "supplierIdInput"),
                  hasEntry("message", "Object not found")
              ),
              allOf(
                  hasEntry("field", "brand"),
                  hasEntry("message", "must not be blank")
              ),
              allOf(
                  hasEntry("field", "costPrice"),
                  hasEntry("message", "must be greater than or equal to 0")
              )
          ));
    }
  }

  @Nested
  class DeleteById
  {
    @Test
    void shouldDeleteProductSuccessfully()
    {
      given()
          .pathParam("productId", 1)
      .when()
          .delete("/{productId}")
      .then()
          .statusCode(NO_CONTENT.value());
    }

    @Test
    void shouldReturnStatusCode404AndErrorMessageWhenProductNotFound()
    {
      final int productId = 90;
      final int statusCode = NOT_FOUND.value();

      given()
          .pathParam("productId", 90)
      .when()
          .delete("/{productId}")
      .then()
          .statusCode(statusCode)
          .body("type", equalTo("https://artedesejo.com.br/problems/product/not-found"))
          .body("title", equalTo("Product not found"))
          .body("status", equalTo(statusCode))
          .body("detail", equalTo("Product not found with id: " + productId))
          .body("instance", equalTo("/products/" + productId))
          .body("timestamp", matchesPattern(ISO8601_WITHOUT_NANO));
    }
  }

  Map<String, Object> createProductWithValidData()
  {
    final Map<String, Object> supplierMap = new HashMap<>();
    supplierMap.put("id", 1);

    final Map<String, Object> productMap = new HashMap<>();
    productMap.put("name", "Óleo de Massagem");
    productMap.put("description", "Óleo de massagem com aroma de baunilha.");
    productMap.put("color", "Âmbar");
    productMap.put("brand", "Intt");
    productMap.put("flavor", "Baunilha");
    productMap.put("sensation", "Esquenta");
    productMap.put("category", "Óleos");
    productMap.put("subCategory", "Aromatizados");
    productMap.put("targetAudience", "Adultos");
    productMap.put("size", "150ml");
    productMap.put("fabric", null);
    productMap.put("additionalInformation", null);
    productMap.put("quantity", 1);
    productMap.put("stockQuantity", 1);
    productMap.put("costPrice", 14.90f);
    productMap.put("salePrice", 29.90f);
    productMap.put("supplierIdInput", supplierMap);

    return productMap;
  }

  Map<String, Object> createProductWithInvalidData()
  {
    final Map<String, Object> supplierMap = new HashMap<>();
    supplierMap.put("id", 90);

    final Map<String, Object> productMap = new HashMap<>();
    productMap.put("name", "");
    productMap.put("description", "Óleo de massagem com aroma de baunilha.");
    productMap.put("color", "Âmbar");
    productMap.put("brand", "");
    productMap.put("flavor", "Baunilha");
    productMap.put("sensation", "Esquenta");
    productMap.put("category", "Óleos");
    productMap.put("subCategory", "Aromatizados");
    productMap.put("targetAudience", "Adultos");
    productMap.put("size", "150ml");
    productMap.put("fabric", null);
    productMap.put("additionalInformation", null);
    productMap.put("quantity", -1);
    productMap.put("stockQuantity", -1);
    productMap.put("costPrice", -1f);
    productMap.put("salePrice", -1f);
    productMap.put("supplierIdInput", supplierMap);

    return productMap;
  }
}
