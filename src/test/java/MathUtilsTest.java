import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class MathUtilsTest {

    private MathUtils mathUtils;

    @BeforeEach
    void setUp() {
        mathUtils = new MathUtils();
    }

    @AfterEach
    void tearDown() {
        mathUtils = null;
    }

    @Test
    void testAdd() {
        assertEquals(5, mathUtils.add(2, 3));
        assertEquals(0, mathUtils.add(-1, 1));
    }

    @Test
    void testSubtract() {
        assertEquals(3, mathUtils.subtract(5, 2));
        assertEquals(-5, mathUtils.subtract(-3, 2));
    }

    @Test
    void testMultiply() {
        assertEquals(12, mathUtils.multiply(4, 3));
        assertEquals(0, mathUtils.multiply(5, 0));
    }

    @Test
    void testDivide() {
        assertEquals(5.0, mathUtils.divide(10, 2));
    }

    @Test
    void testDivideByZero() {
        assertEquals(-1.0, mathUtils.divide(10, 0));
    }
}

