package addPet;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CreatePetDescriptiorRequest.class)
public class CreatePetDescriptiorRequest {

    private final Integer id;
    private final String name;

    public CreatePetDescriptiorRequest(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class PetBuilder {

        private Integer id;
        private String name;

        public PetBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public PetBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CreatePetDescriptiorRequest build() {
            return new CreatePetDescriptiorRequest(id, name);
        }
    }

}
