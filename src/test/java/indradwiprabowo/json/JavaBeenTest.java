package indradwiprabowo.json;

import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

public class JavaBeenTest {

    @Test
    void createJson() {
        Person person = new Person();
        person.setId("1");
        person.setName("Joko");
        person.setHobbies(List.of("Berenang", "Basket"));

        Address address = new Address();
        address.setStreet("Jalan Randublatung");
        address.setCity("Cepu");
        address.setCountry("Indonesia");
        person.setAddress(address);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(person);

        System.out.println(json);
    }

}
