package mp.homework.gan.beans;

public class TryToGuessBean {
    private int playerIndex;
    private int proposed;

    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    public void setProposed(int proposed) {
        this.proposed = proposed;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public int getProposed() {
        return proposed;
    }
}
