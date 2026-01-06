package indradwiprabowo.json;

import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.exc.JsonNodeException;

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

}
