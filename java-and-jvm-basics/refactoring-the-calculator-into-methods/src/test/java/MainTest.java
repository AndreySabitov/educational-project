import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testAdd() {
       double result = Main.add(1.0, 1.0);
       assertEquals(2.0, result);
    }

}