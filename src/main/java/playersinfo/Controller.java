package playersinfo;

import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    private DataService dataService;

    @GetMapping("/api/players")
    public List<Player> getAllPlayers() {
        return dataService.getAllPlayers();
    }

    @GetMapping("/api/players/{playerID}")
    public Player getPlayer(@PathVariable @Nullable String playerID) {
        var player = dataService.getPlayer(playerID);
        if (player == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found");
        } else {
            return player;
        }
    }
}
