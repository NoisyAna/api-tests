import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import addPet.CreatePetDescriptiorRequest;
import addPet.CreatePetDescriptiorRequest.PetBuilder;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpHeaders;

public class AbstractClass {

    protected static final String BASE_URL = "https://petstore.swagger.io/v2/pet/";

    public CreatePetDescriptiorRequest createPetBody(Integer id, String name) {
        return new PetBuilder().id(id)
                .name(name)
                .build();
    }

    public Response createPet(CreatePetDescriptiorRequest petBody) {
        return RestAssured.given()
                .spec(buildHeader())
                .body(petBody)
                .post(BASE_URL)
                .then()
                .statusCode(HttpServletResponse.SC_OK)
                .extract()
                .response();
    }

    public Response getPetbyId(Integer id) {
        return RestAssured.given()
                .spec(buildHeader())
                .get(BASE_URL + id)
                .then()
                .statusCode(HttpServletResponse.SC_OK)
                .extract()
                .response();
    }

    public Response deletePetById(Integer id) {
        return RestAssured.given()
                .headers(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .delete(BASE_URL + id)
                .then()
                .statusCode(HttpServletResponse.SC_NOT_FOUND)
                .extract()
                .response();
    }

    protected RequestSpecification buildHeader() {
        return new RequestSpecBuilder().addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .build();
    }

}
