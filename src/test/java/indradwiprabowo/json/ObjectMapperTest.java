package indradwiprabowo.json;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;

public class ObjectMapperTest {

    @Test
    void createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        Assertions.assertNotNull(objectMapper);
    }

}
