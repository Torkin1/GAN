package mp.homework.gan.entities;

import java.util.ArrayList;
import java.util.List;

import mp.homework.gan.control.play.Difficulty;

public class Game {
    private List<Player> players = new ArrayList<>();
    private Difficulty difficulty;

    public List<Player> getPlayers() {
        return players;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}
