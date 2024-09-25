package playersinfo;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class DataService {
    // this is the number of 'columns' in CSV data file
    private static final int NUMBER_OF_TEXT_FIELDS = 24;

    private static final String CSV_SEPARATOR = ",";

    Map<String, Player> players = new HashMap<>();

    public List<Player> getAllPlayers() {
        return new ArrayList<>(players.values());
    }

    public Player getPlayer(String id) {
        return players.get(id);
    }

    @PostConstruct
    public void init() {
        try (var reader = new BufferedReader(
                new InputStreamReader(DataService.class.getClassLoader().getResourceAsStream("player.csv")))) {
            reader.readLine(); // skip first line with headers
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    var player = createPlayer(line);
                    if (players.containsKey(player.id())) {
                        throw new RuntimeException("Player with id " + player.id() + " already exists");
                    }
                    players.put(player.id(), player);
                } catch (Exception e) {
                    // in case of parsing error, log this error and skip line
                    System.err.println("Error parsing line: \"" + line + "\" the error is" + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // create player from string
    Player createPlayer(String line) {
        var fields = line.split(CSV_SEPARATOR);
        if (fields.length < NUMBER_OF_TEXT_FIELDS) {
            var newFields = new String[NUMBER_OF_TEXT_FIELDS];
            System.arraycopy(fields, 0, newFields, 0, fields.length);
            for (int i = fields.length; i < NUMBER_OF_TEXT_FIELDS; i++) {
                newFields[i] = "";
            }
            fields = newFields;
        }
        return new Player(
                fields[0], // id
                createYearMonthDay(fields[1], fields[2], fields[3]), // birthdate
                createLocation(fields[4], fields[5], fields[6]), // birth location
                createYearMonthDay(fields[7], fields[8], fields[9]), // death date
                createLocation(fields[10], fields[11], fields[12]), // death location
                createString(fields[13]),  //first name
                createString(fields[14]),  // last name
                createString(fields[15]),  // given name
                createInt(fields[16]), // weight
                createInt(fields[17]), // height
                createLeftRight(fields[18]), // bats
                createLeftRight(fields[19]), // throws
                createLocalDate(fields[20]), // debut
                createLocalDate(fields[21]), // final game
                createString(fields[22]), // retroID
                createString(fields[23])  // bbrefID
        );
    }

    // parse YearMonthDay, at this object every field (year, month or day) could be empty
    YearMonthDay createYearMonthDay(String year, String month, String day) {
        if (year.isEmpty() && month.isEmpty() && day.isEmpty()) {
            return null;
        }
        return new YearMonthDay(year.isEmpty() ? null : Integer.parseInt(year), month.isEmpty() ? null : Integer.parseInt(month), day.isEmpty() ? null : Integer.parseInt(day));
    }

    LocalDate createLocalDate(String localDateString) {
        return localDateString.isEmpty() ? null : LocalDate.parse(localDateString);
    }

    Location createLocation(String country, String state, String city) {
        return (country.isEmpty() && state.isEmpty() && city.isEmpty()) ? null : new Location(country, state, city);
    }

    String createString(String s) {
        return s.isEmpty() ? null : s;
    }

    Integer createInt(String s) {
        return s.isEmpty() ? null : Integer.parseInt(s);
    }

    LeftRight createLeftRight(String s) {
        return s.isEmpty() ? null : LeftRight.fromShortName(s);
    }
}
