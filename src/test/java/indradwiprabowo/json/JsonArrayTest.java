package indradwiprabowo.json;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.exc.JsonNodeException;

import java.util.List;
import java.util.Map;

public class JsonArrayTest {

    @Test
    void createJsonArray() throws JsonNodeException {
        List<String> hobbies = List.of("Coding", "Reading", "Traveling");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(hobbies);

        System.out.println(json);
    }

    @Test
    void createJsonArrayObject() throws JsonNodeException {
        List<Map<String, Object>> hobbies = List.of(
                Map.of("Coding", "Kampus"),
                Map.of("Traveling", "Bali"),

                Map.of("Basket","Kampus", "Muncak", "Gunung")
        );

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(hobbies);

        System.out.println(json);
    }

    @Test
    void readJsonArray() throws JsonNodeException {
        String json = """
                ["Coding","Reading","Traveling"]
                """;

        ObjectMapper objectMapper = new ObjectMapper();
        List<String> hobbies = objectMapper.readValue(json, new TypeReference<List<String>>() {
        });

        Assertions.assertEquals(List.of("Coding","Reading","Traveling"), hobbies);
    }

    @Test
    void readJsonArrayObject() throws JsonNodeException {
        String json = """
            [
                {
                    "Coding": "Kampus"
                },
                {
                    "Traveling": "Bali"
                },
                {
                    "Basket": "Kampus",
                    "Muncak": "Gunung"
                }
            ]
            """;

        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> hobbies = objectMapper.readValue(json, new TypeReference<>() {
        });

        List<String> keys = hobbies.stream()
                .flatMap(map -> map.keySet().stream())
                .toList();

        Assertions.assertEquals(List.of("Coding", "Traveling", "Basket", "Muncak"), keys);
    }

}
