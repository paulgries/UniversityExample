package entity;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    private Person p1;

    @BeforeEach
    void setUp() {
        String[] name = {"First", "Middle", "Last"};
        p1 = new Person(name, "moogah");
    }

    @Test
    void formatName() {
        String actual = p1.formatName();
        String expected = "Last,First Middle";
        assertEquals(expected, actual);
    }

    @Test
    void testGetNameImmutability() {
        String[] tmpName = p1.getName();
        tmpName[0] = "Frooble";
        String[] names = p1.getName();
        // In lecture, we had this line:
        // assertNotEquals(tmpName, names);
        // However, assertEquals and assertNotEquals don't actually compare the
        // contents of the two arrays. There is an assertArrayEquals, but
        // no assertArrayNotEquals. We can do this instead:
        assertFalse(Arrays.equals(tmpName, names));

    }
}