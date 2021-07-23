import java.util.Random;
import javax.servlet.http.HttpServletResponse;

import addPet.CreatePetDescriptiorRequest;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import jdk.jfr.Name;
import org.junit.Test;
import org.junit.jupiter.api.Order;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PetStoreTest extends AbstractClass {

    private CreatePetDescriptiorRequest petBody;
    private Response response;
    private Integer getPetId;
    private String getPetName;
    private Integer statusCode;
    private Faker fakerPet = new Faker(new Random(10));
    private String petName = fakerPet.name().firstName();
    private Integer petId = fakerPet.idNumber().hashCode();

    @Test
    @Name("Create Pet Test")
    @Order(1)
    public void createPetTest() {



        petBody = createPetBody(petId, petName);

        response = createPet(petBody);

        getPetId = response.jsonPath()
                .getInt("id");
        getPetName = response.jsonPath()
                .getString("name");

        assertAll(() -> assertEquals(petId, getPetId, "Pet id should be " + petId + " but is " + getPetId),
                () -> assertEquals(petName, getPetName, "Pet name is " + petName + " but is " + getPetName));
    }

    @Test
    @Name("Get Pet By Id Test")
    @Order(2)
    public void getPetByIdTest() {
        //value 1 is hardcoded, because of limitation of test api
        response = getPetbyId(1);

        getPetId = response.jsonPath()
                .getInt("id");
        getPetName = response.jsonPath()
                .getString("name");

        assertEquals(1, getPetId, "Pet id should be " + 1 + " but is " + getPetId);
    }

    @Test
    @Name("Delete Pet Test")
    @Order(3)
    public void deletePetTest() {

        statusCode = deletePetById(petId).getStatusCode();

        assertEquals(HttpServletResponse.SC_NOT_FOUND, statusCode,
                "Request status code is not 404 - NOT FOUND. Actual status code is: " + statusCode);

    }
}
