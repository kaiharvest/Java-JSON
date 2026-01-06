package indradwiprabowo.json;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.exc.JsonNodeException;

import java.lang.reflect.Type;
import java.util.Map;

public class JsonObjectTest {

    @Test
    void createJson() throws JsonNodeException {
        Map<String, Object> map = Map.of(
                "firstName","Budi",
                "lastName", "Nugraha",
                "age", 30,
                "married", true,
                "address", Map.of(
                        "streat","belum ada",
                        "city","Semarang",
                        "country","Indonesia"
                )
        );

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(map);

        System.out.println(json);
    }

    @Test
    void readJson() throws JsonNodeException {
        String json = """
                {
                    "firstName": "Budi",
                    "address": {
                        "city": "Semarang",
                        "streat": "belum ada",
                        "country": "Indonesia"
                    },
                    "age": 30,
                    "married": true,
                    "lastName": "Nugraha"
                }
                """;

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> person = objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {
        });

        Assertions.assertEquals("Budi", person.get("firstName"));
        Assertions.assertEquals("Nugraha", person.get("lastName"));
    }

}
