package indradwiprabowo.json;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.MapperFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;


public class MapperFeatureTest {

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

}
