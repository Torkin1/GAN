package mp.homework.gan.control.play;

import java.util.Calendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import mp.homework.gan.beans.GameInfoBean;
import mp.homework.gan.beans.StartGameBean;
import mp.homework.gan.beans.TryToGuessBean;
import mp.homework.gan.entities.Game;
import mp.homework.gan.entities.Player;

public class GameController {
    // Singleton
    private static GameController ref = null;
    public static GameController getInstance(){
        if (ref == null) {
            ref = new GameController();
        }
        return ref;
    }
    private GameController() {}

    private Game game = new Game();

    public void startGame(StartGameBean bean){
        // this method has to be called whenever the player wants to start a new game
        // configure game with settings specified in bean
        for (int i = 0; i < bean.getNumOfPlayers(); i ++){
            // adds to the game number of players specified in bean
            this.game.getPlayers().add(new Player());
            // Choose a different random number to be guessed for every player
            Random randGenerator = new Random (Calendar.getInstance().getTime().getTime());
            int toGuess = randGenerator.nextInt(bean.getDifficulty().getUpperBound()) + bean.getDifficulty().getLowerBound();
            this.game.getPlayers().get(i).setToGuess(toGuess);
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, String.format("number for player %d is: %d", i, toGuess));
            // Sets all other game parameters depending on Difficulty specified in bean
            this.game.getPlayers().get(i).setLives(bean.getDifficulty().getStartingLives());
            this.game.getPlayers().get(i).setMaxLives(bean.getDifficulty().getStartingLives());
            this.game.setDifficulty(bean.getDifficulty());
        }

    }
    public void tryToGuess(TryToGuessBean bean) throws ProposedTooHighException, ProposedTooLowException, PlayerOutOfLivesException {
        //Simple tree of decision which checks if player guessed correctly. If this is the case the player wins the game, else it raises an exception
        if (bean.getProposed() > game.getPlayers().get(bean.getPlayerIndex()).getToGuess()){
            if (game.getPlayers().get(bean.getPlayerIndex()).subLife() <= 0) {
                throw new PlayerOutOfLivesException(bean.getPlayerIndex());
            }
            throw new ProposedTooHighException();
        }
        else if (bean.getProposed() < game.getPlayers().get(bean.getPlayerIndex()).getToGuess()){
            if (game.getPlayers().get(bean.getPlayerIndex()).subLife() <= 0) {
                throw new PlayerOutOfLivesException(bean.getPlayerIndex());
            }
            throw new ProposedTooLowException();
        } else {
            // no-op, correct guess :)
        }
    }
    public void getGameInfo(GameInfoBean bean){
        bean.setGame(this.game);
    }

}
