package indradwiprabowo.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.List;


public class FeatureTest {

    @Test
    void mapperFeature() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

        String json = """
                {"ID":"1", "Name":"Indra"}
                """;

        Person person = objectMapper.readValue(json, Person.class);
        Assertions.assertEquals("1", person.getId());
        Assertions.assertEquals("Indra", person.getName());
    }

    @Test
    void deserializationFeature() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
                .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String json = """
                {"ID":"1", "Name":"Indra", "age":"22", "hobbies":"Coding"}
                """;

        Person person = objectMapper.readValue(json, Person.class);
        Assertions.assertEquals("1", person.getId());
        Assertions.assertEquals("Indra", person.getName());
        Assertions.assertEquals(List.of("Coding"), person.getHobbies());
    }

    @Test
    void serializationFeature() throws Exception {
        Person person = new Person();
        person.setId("1");
        person.setName("Joko");
        person.setHobbies(List.of("Berenang", "Basket"));

        Address address = new Address();
        address.setStreet("Jalan Randublatung");
        address.setCity("Cepu");
        address.setCountry("Indonesia");
        person.setAddress(address);

        ObjectMapper objectMapper = new ObjectMapper()
                .configure(SerializationFeature.INDENT_OUTPUT, true);

        String json = objectMapper.writeValueAsString(person);

        System.out.println(json);
    }

    @Test
    void serializationInclusion() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(SerializationFeature.INDENT_OUTPUT, true)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);

        Person person = new Person();
        person.setId("1");
        person.setName("Indra");

        String json = objectMapper.writeValueAsString(person);
        System.out.println(json);
    }

}
