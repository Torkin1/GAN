package mp.homework.gan.beans;

import mp.homework.gan.control.play.Difficulty;

public class StartGameBean {
    // mean of communication beetween view and a GameController
    private Difficulty difficulty;
    private int numOfPlayers;

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public void setNumOfPlayers(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}
