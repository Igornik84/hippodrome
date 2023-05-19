import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;


class HorseTest {
    @Test
    public void testConstructorSecondNull() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 10, 20));
    }

    @Test
    public void testConstructorNullArgument() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 10, 20));
        String expectedMessage = "Name cannot be null.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "  \t "})
    public void testEmptyOrWhitespaceName(String name) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 10, 20));
        String expectedMessage = "Name cannot be null or whitespace.";
        String actualMessage = exception.getMessage();
        assertFalse(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testNegativeValue() {
        int negativeValue = -1;
        assertThrows(IllegalArgumentException.class, () -> new Horse("speed", negativeValue));
    }

    @Test
    public void testNegativeValueSpeed() {
        int negativeValue = -1;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("speed", negativeValue));
        String expectedMessage = "Speed cannot be negative.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testNegativeValue3() {
        int negValue = -1;
        assertThrows(IllegalArgumentException.class, () -> new Horse("distance", negValue));
    }

    @Test
    public void testNegativeValueDistance() {
        int negValue = -10;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("distance", negValue));
        String expectedMessage = "Distance cannot be negative.";
        String actualMessage = "Distance cannot be negative.";
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testGetName() {
        String expectedName = "name";
        Horse name = new Horse(expectedName, 5, 10);
        String actualName = name.getName();
        assertEquals(expectedName, actualName);
    }

    @Test
    public void testGetSpeed() {
        int expectedSpeed = 10;
        Horse speed = new Horse("speed", expectedSpeed, 20);
        int actualSpeed = (int) speed.getSpeed();
        assertEquals(expectedSpeed, actualSpeed);
    }

    @Test
    public void testGetDistanceParameter() {
        int expectedDistance = 10;
        Horse distance = new Horse("distance", expectedDistance, 10);
        int actualDistance = (int) distance.getDistance();
        assertEquals(expectedDistance, actualDistance);
    }

    @Test
    public void testGetDistanceTwoParameters() {
        Double expectedDistance = 0.0;
        Horse distance = new Horse("distance", 10);
        Double actualDistance = distance.getDistance();
        assertEquals(expectedDistance, actualDistance);
    }

    @Test
    public void testMoveMethod() {

        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            new Horse("TestHorse", 2.0, 0.0).move();

            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));

            }
    }
}