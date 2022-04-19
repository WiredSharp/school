package wiredsharp.study;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class SchoolResourceTest {

    @Test
    public void testSchoolApiEndpoint() {
        given()
          .when().get("/api/schools")
          .then()
             .statusCode(200)
             .contentType(ContentType.JSON);
    }

}