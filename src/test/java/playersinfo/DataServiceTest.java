package playersinfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DataServiceTest {
    @Test
    public void testCreateString() {
        assertEquals("la-la-la", new DataService().createString("la-la-la"));
    }

    @Test
    public void testCreateStringNull() {
        assertEquals(null, new DataService().createString(""));
    }

    @Test
    public void testCreateInt() {
        assertEquals(25, new DataService().createInt("25"));
    }

    @Test
    public void testCreateIntNull() {
        assertEquals(null, new DataService().createInt(""));
    }

    @Test
    public void testCreateIntError() {
        assertThrows(Exception.class, () -> new DataService().createInt("abc"));
    }

    @Test
    public void testCreateLocation() {
        assertEquals(new Location("USA", "NY", "New York"), new DataService().createLocation("USA", "NY", "New York"));
    }

    @Test
    public void testCreateLocationNull() {
        assertEquals(null, new DataService().createLocation("", "", ""));
    }

    @Test
    public void testCreateYearMonthDay() {
        assertEquals(new YearMonthDay(1985, 9, 15), new DataService().createYearMonthDay("1985", "09", "15"));
    }

    @Test
    public void testCreateYearMonthDayWithoutDay() {
        assertEquals(new YearMonthDay(1985, 9, null), new DataService().createYearMonthDay("1985", "09", ""));
    }

    @Test
    public void testCreateYearMonthDayNull() {
        assertEquals(null, new DataService().createYearMonthDay("", "", ""));
    }

    @Test
    public void testCreateYearMonthDayError() {
        assertThrows(Exception.class, () -> new DataService().createYearMonthDay("hjkl", "09", ""));
    }

    @Test
    public void testCreateLocalDate() {
        assertEquals(LocalDate.of(1995, 12, 11), new DataService().createLocalDate("1995-12-11"));
    }

    @Test
    public void testCreateLocalDateNull() {
        assertEquals(null, new DataService().createLocalDate(""));
    }

    @Test
    public void testCreateLocalDateError() {
        assertThrows(Exception.class, () -> new DataService().createLocalDate("hjkl-09-15"));
    }

    @Test
    public void testCreateLeftRight() {
        assertEquals(LeftRight.RIGHT, new DataService().createLeftRight("R"));
    }

    @Test
    public void testCreateLeftRightNull() {
        assertEquals(null, new DataService().createLeftRight(""));
    }

    @Test
    public void testCreateLeftRightError() {
        assertThrows(Exception.class, () -> new DataService().createLeftRight("E"));
    }

    @Test
    public void testCreatePlayer() {
        assertEquals(new Player(
                "id15",
                new YearMonthDay(1985, 5, 18),
                new Location("USA", "TX", "Austin"),
                null,
                null,
                "John",
                "Smith",
                null,
                215,
                75,
                LeftRight.RIGHT,
                LeftRight.RIGHT,
                LocalDate.of(2005, 11, 1),
                null,
                "retro",
                "bbref"
        ), new DataService().createPlayer("id15,1985,5,18,USA,TX,Austin,,,,,,,John,Smith,,215,75,R,R,2005-11-01,,retro,bbref"));
    }

    @Test
    public void testCreatePlayerFromShortString() {
        assertEquals(new Player(
                "id15",
                new YearMonthDay(1985, 5, 18),
                new Location("USA", "TX", "Austin"),
                null,
                null,
                "John",
                "Smith",
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        ), new DataService().createPlayer("id15,1985,5,18,USA,TX,Austin,,,,,,,John,Smith,,,,"));
    }
}
