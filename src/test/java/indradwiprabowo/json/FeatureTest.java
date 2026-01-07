package indradwiprabowo.json;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.MapperFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;


public class FeatureTest {

    @Test
    void mapperFeature() throws Exception {
        ObjectMapper objectMapper = JsonMapper.builder()
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
                .build();

        String json = """
                {"ID":"1", "Name":"Indra"}
                """;

        Person person = objectMapper.readValue(json, Person.class);
        Assertions.assertEquals("1", person.getId());
        Assertions.assertEquals("Indra", person.getName());
    }

    @Test
    void deserializationFeature() throws Exception {
        ObjectMapper objectMapper = JsonMapper.builder()
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
                .build();


        String json = """
                {"ID":"1", "Name":"Indra", "age":"22", "hobbies":"Coding"}
                """;

        Person person = objectMapper.readValue(json, Person.class);
        Assertions.assertEquals("1", person.getId());
        Assertions.assertEquals("Indra", person.getName());
        Assertions.assertEquals(List.of("Coding"), person.getHobbies());
    }

    @Test
    void serializationFeature() {
        Person person = new Person();
        person.setId("1");
        person.setName("Joko");
        person.setHobbies(List.of("Berenang", "Basket"));

        Address address = new Address();
        address.setStreet("Jalan Randublatung");
        address.setCity("Cepu");
        address.setCountry("Indonesia");
        person.setAddress(address);

        ObjectMapper objectMapper = JsonMapper.builder()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .build();
        String json = objectMapper.writeValueAsString(person);

        System.out.println(json);
    }

}
