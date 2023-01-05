package mp.homework.gan.beans;

import mp.homework.gan.control.play.Difficulty;
import mp.homework.gan.entities.Game;

public class GameInfoBean {
    private Game game;

    public void setGame(Game game){
        this.game = game;
    }

    public Difficulty getDifficulty(){
        return this.game.getDifficulty();
    }
    public int getPlayerLives(int i){
        return this.game.getPlayers().get(i).getLives();
    }
    public int getPlayerMaxLives(int i){ return this.game.getPlayers().get(i).getMaxLives(); }
}
