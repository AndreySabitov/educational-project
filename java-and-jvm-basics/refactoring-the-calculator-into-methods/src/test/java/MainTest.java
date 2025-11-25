import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @BeforeEach
    void log() {
        System.out.println("Вывожу сообщение перед каждым тестом");
    }

    @Test
    void testAdd() {
       double result = Main.add(1.0, 1.0);
       assertEquals(2.0, result);
    }

}