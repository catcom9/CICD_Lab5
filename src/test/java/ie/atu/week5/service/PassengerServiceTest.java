package ie.atu.week5.service;

import ie.atu.week5.model.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class PassengerServiceTest {
    private PassengerService service;

    @BeforeEach
    void setup(){
        service = new PassengerService();
    }

    @Test
    void createThenFindById(){
        Passenger p = new Passenger("P1", "Paul", "paul@atu.ie");

        service.create(p);

        Optional<Passenger> found = service.findById("P1");
        assertTrue(found.isPresent());
        assertEquals("Paul", found.get().getName());
    }

    @Test
    void duplicateIdThrows(){
        service.create(new Passenger("P2", "Bob", "bob@atu.ie"));

        assertThrows(IllegalArgumentException.class, () ->
                service.create(new Passenger("P2", "Bob", "bob@atu.ie")));
    }

    @Test
    void updateNameTestFail(){
        service.create(new Passenger("P2", "Bob", "bob@atu.ie"));

        assertThrows(IllegalArgumentException.class, () ->
                service.updateName("P3", "Joe"));
    }

    @Test
    void updateNameTest(){
        service.create(new Passenger("P2", "Bob", "bob@atu.ie"));
        Optional<Passenger> found = service.updateName("P2", "Joe");
        assertTrue(found.isPresent());
        assertEquals("Joe", found.get().getName());

    }

    @Test
    void deleteFAIL(){
        boolean response = service.delete("P1");
        assertEquals(response, false);
    }

    @Test
    void delete(){
        service.create(new Passenger("P1", "Bob", "bob@atu.ie"));
        boolean response = service.delete("P1");
        assertEquals(response, true);
    }




}
