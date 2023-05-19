import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;

class HippodromeTest {
    @Test
    public void testConstructorSecondNull() {
        List<Horse> horses = null;

        assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(horses);
        });
    }
    @Test
    public void testConstructorNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        String expectedMessage = "Horses cannot be null.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    public void testConstructorEmptyList() {
        List<Horse> emptyList = Collections.emptyList();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(emptyList));
        String expectedMessage = "Horses cannot be empty.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    public void testGet30Horses() {
        ArrayList<Horse> horses = new ArrayList<>();
        for (int i =1; i < 30; i++){
            horses.add(new Horse("" + i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        List<Horse> result = hippodrome.getHorses();
        assertEquals(horses, result);
    }
    @Test
    void testMove50Horses() {
        // Создаем список моков лошадей
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
               hippodrome.move();
        for (Horse horse : horses) {
            verify(horse).move();
        }
    }
    @Test
    void testGetWinner() {
        // Создаем список лошадей
        List<Horse> horses = new ArrayList<>();
        Horse horse1 = new Horse("Horse 1", 10, 100);
        Horse horse2 = new Horse("Horse 2", 15, 200);
        Horse horse3 = new Horse("Horse 3", 20, 150);
        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);
        Hippodrome hippodrome = new Hippodrome(horses);
        // Получаем победителя
        Horse winner = hippodrome.getWinner();
        // Проверяем, что победителем является лошадь с наибольшим значением distance
        assertEquals(horse2, winner);
    }

}