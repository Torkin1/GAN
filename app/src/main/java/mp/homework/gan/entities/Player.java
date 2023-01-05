package mp.homework.gan.entities;

public class Player {
    private int lives = -1;
    private String username = "";
    private int toGuess = -1;

    public int getMaxLives() {
        return maxLives;
    }

    public void setMaxLives(int maxLives) {
        this.maxLives = maxLives;
    }

    private int maxLives = -1;

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int addLife(){
        this.lives++;
        return this.lives;
    }

    public int subLife(){
        this.lives--;
        return this.lives;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getToGuess() {
        return toGuess;
    }

    public void setToGuess(int toGuess) {
        this.toGuess = toGuess;
    }
}
