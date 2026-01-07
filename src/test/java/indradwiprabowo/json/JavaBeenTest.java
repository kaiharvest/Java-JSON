package indradwiprabowo.json;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JavaBeenTest {

    @Test
    void createJson() throws Exception {
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

    @Test
    void createObjectFormJson() throws Exception {
        String json = """
                {
                    "address":
                        {
                            "city":"Cepu",
                            "country":"Indonesia",
                            "street":"Jalan Randublatung"
                        },
                    "hobbies":["Berenang","Basket"],
                    "id":"1",
                    "name":"Joko"
                }
                """;

        ObjectMapper objectMapper = new ObjectMapper();
        Person person = objectMapper.readValue(json, Person.class);

        Assertions.assertEquals("1", person.getId());
        Assertions.assertEquals("Joko", person.getName());
        Assertions.assertEquals("Jalan Randublatung", person.getAddress().getStreet());
        Assertions.assertEquals("Cepu", person.getAddress().getCity());
        Assertions.assertEquals("Indonesia", person.getAddress().getCountry());
        Assertions.assertEquals(List.of("Berenang","Basket"), person.getHobbies());
    }

}
