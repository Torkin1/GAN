package mp.homework.gan.control.play;

public enum Difficulty {
    // If using CUSTOM, fields have to be set by invoking their setters
    CUSTOM,
    NORMAL (6, 0, 100),
    ;

    private int startingLives;
    private int lowerBound;
    private int upperBound;

    Difficulty () {}

    Difficulty(int startingLives, int lowerBound, int upperBound) {
        this.startingLives = startingLives;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public int getLowerBound() {
        return lowerBound;
    }

    public int getUpperBound() {
        return upperBound;
    }

    public int getStartingLives() {
        return startingLives;
    }

    public void setStartingLives(int startingLives) {
        this.startingLives = startingLives;
    }

    public void setLowerBound(int lowerBound) {
        this.lowerBound = lowerBound;
    }

    public void setUpperBound(int upperBound) {
        this.upperBound = upperBound;
    }
}
