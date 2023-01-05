package mp.homework.gan.control.play;

public class PlayerOutOfLivesException extends Throwable {
    public PlayerOutOfLivesException(int playerIndex) {
        this.playerIndex = playerIndex;
    }
    private int playerIndex;

    public int getPlayerIndex() {
        return playerIndex;
    }
}
