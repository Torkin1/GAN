package mp.homework.gan.view.singleplayergame;


import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsSeekBar;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import mp.homework.gan.R;
import mp.homework.gan.beans.GameInfoBean;
import mp.homework.gan.control.play.Difficulty;
import mp.homework.gan.beans.StartGameBean;
import mp.homework.gan.beans.TryToGuessBean;
import mp.homework.gan.control.play.GameController;
import mp.homework.gan.control.play.PlayerOutOfLivesException;
import mp.homework.gan.control.play.ProposedTooHighException;
import mp.homework.gan.control.play.ProposedTooLowException;
import mp.homework.gan.view.MappableSeekBar;

public class SinglePlayerGameActivity extends AppCompatActivity {

    private class Holder{

        private final Button bPlus = findViewById(R.id.bPlus);
        private final Button bMinus = findViewById(R.id.bMinus);
        private final LinearLayout llLivesBar = findViewById(R.id.llLivesBar);
        private final ImageView ivCurrentLife = findViewById(R.id.ivCurrentLife);
        private final TextView tvScientistMsg = findViewById(R.id.tvScientistMsg);
        private final TextView tvLowerBoundValue = findViewById(R.id.tvLowerBoundValue);
        private final TextView tvUpperBoundValue = findViewById(R.id.tvUpperBoundValue);
        private final Button bGuess = findViewById(R.id.bGuess);
        private final MappableSeekBar msbGuess = findViewById(R.id.msbGuess);
        private final TextView tvGuess = findViewById(R.id.tvGuess);

        private Holder(){ }

        public LinearLayout getLlLivesBar() { return llLivesBar; }

        public ImageView getIvCurrentLife() {
            return ivCurrentLife;
        }

        public TextView getTvScientistMsg() {
            return tvScientistMsg;
        }

        public TextView getTvLowerBoundValue() {
            return tvLowerBoundValue;
        }

        public TextView getTvUpperBoundValue() {
            return tvUpperBoundValue;
        }

        private void initialize(StartGameBean bean){
            // populating llLivesBar with image views
            LinearLayout insertPoint = this.getLlLivesBar();
            for (int i = 0; i < bean.getDifficulty().getStartingLives(); i ++){
                ImageView ivLife = new ImageView(SinglePlayerGameActivity.this);
                ivLife.setBackgroundColor(Color.WHITE);
                ivLife.setImageDrawable(getDrawable(R.drawable.smile_expression_emotion_emoji_512));
                LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(150, 150);
                llParams.setMargins(8,8,8,8);
                llParams.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                ivLife.setLayoutParams(llParams);
                insertPoint.addView(ivLife);
            }
            insertPoint.getChildAt(0).setBackgroundColor(Color.YELLOW);
            // Setting ivCurrentLife with same image as first life to the left
            this.getIvCurrentLife().setImageDrawable(((ImageView)this.getLlLivesBar().getChildAt(0)).getDrawable());
            // Setting boundaries for msbGuess with same values passed to GameController
            this.msbGuess.setNewMax(bean.getDifficulty().getUpperBound());
            this.msbGuess.setNewMin(bean.getDifficulty().getLowerBound());
            this.getTvUpperBoundValue().setText(String.valueOf(bean.getDifficulty().getUpperBound()));
            this.getTvLowerBoundValue().setText(String.valueOf(bean.getDifficulty().getLowerBound()));
            // Setting text in tvScientistMsg
            this.getTvScientistMsg().setText(R.string.scientistMsgStart);
            // Linking sbGuess scroll to tvGuess, so that it can display the value chosen
            this.msbGuess.setOnSeekBarChangeListener(new sbGuessHandler());
            // Setting logic of buttons
            this.bGuess.setOnClickListener(new bGuessHandler());
            this.bPlus.setOnClickListener(new bPlusHandler());
            this.bMinus.setOnClickListener(new bMinusHandler());
        }

        private class sbGuessHandler implements SeekBar.OnSeekBarChangeListener{

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // updates display of tvGuess
                Holder.this.tvGuess.setText(String.valueOf(Holder.this.msbGuess.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // no-op
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // no-op
            }
        }

        private class bPlusHandler implements View.OnClickListener{
            @Override
            public void onClick(View v) {
                Holder.this.msbGuess.setProgress(Holder.this.msbGuess.getProgress() + 1);
            }
        }
        private class bMinusHandler implements View.OnClickListener{
            @Override
            public void onClick(View v) {
                Holder.this.msbGuess.setProgress(Holder.this.msbGuess.getProgress() - 1);
            }
        }


        private class bGuessHandler implements View.OnClickListener{

            @Override
            public void onClick(View v) {
                // Submits guess proposal to GameController
                TryToGuessBean tryToGuessBean = new TryToGuessBean();
                tryToGuessBean.setPlayerIndex(currentPlayerIndex);
                tryToGuessBean.setProposed(Holder.this.msbGuess.getProgress());
                try {
                    GameController.getInstance().tryToGuess(tryToGuessBean);
                    // got it! :D
                    Toast toast = Toast.makeText(SinglePlayerGameActivity.this, R.string.you_win, Toast.LENGTH_LONG);
                    toast.show();
                    finish();
                } catch (ProposedTooHighException | ProposedTooLowException e) {
                    // wrong guess, keep trying! :(
                    // updating player lives
                    GameInfoBean gameInfoBean = new GameInfoBean();
                    GameController.getInstance().getGameInfo(gameInfoBean);
                    int currentIvLifeIndex = gameInfoBean.getPlayerMaxLives(currentPlayerIndex) - gameInfoBean.getPlayerLives(currentPlayerIndex) - 1;
                    ImageView currentIvLife = (ImageView) Holder.this.llLivesBar.getChildAt(currentIvLifeIndex);
                    currentIvLife.setBackgroundColor(Color.WHITE);
                    currentIvLife.setImageDrawable(getDrawable(R.drawable.tongue_out_expression_512));
                    ImageView nextIvLife = (ImageView) Holder.this.llLivesBar.getChildAt(currentIvLifeIndex + 1);
                    nextIvLife.setBackgroundColor(Color.YELLOW);
                    // updating boundaries and scientist msg
                    if (e instanceof ProposedTooHighException){
                        Holder.this.tvUpperBoundValue.setText(Holder.this.tvGuess.getText());
                        Holder.this.msbGuess.setNewMax(Integer.parseInt(Holder.this.tvGuess.getText().toString()) - 1);
                        Holder.this.tvScientistMsg.setText(R.string.scientistMsgTooHigh);
                    }
                    else if(e instanceof  ProposedTooLowException){
                        Holder.this.tvLowerBoundValue.setText(Holder.this.tvGuess.getText());
                        Holder.this.msbGuess.setNewMin(Integer.parseInt(Holder.this.tvGuess.getText().toString()) + 1);
                        Holder.this.tvScientistMsg.setText(R.string.scientistMsgTooLow);
                    }
                    if (Holder.this.msbGuess.getProgress() == Holder.this.msbGuess.getNewMin()){
                        Holder.this.msbGuess.setProgress(Holder.this.msbGuess.getNewMin() + 1);
                    }
                    // updating progress bar
                    Holder.this.msbGuess.setProgress(Holder.this.msbGuess.getNewMin());
                }  catch (PlayerOutOfLivesException e) {
                    // Another victory to the unerring machine kind X(
                    Toast toast = Toast.makeText(SinglePlayerGameActivity.this, R.string.you_lose, Toast.LENGTH_LONG);
                    toast.show();
                    finish();
                }
            }
        }

    }

    private int currentPlayerIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_player_game);
        // Starting a single-player game
        StartGameBean bean = new StartGameBean();
        bean.setNumOfPlayers(1);
        bean.setDifficulty(Difficulty.NORMAL);
        GameController.getInstance().startGame(bean);
        // initializing dynamic part of gui
        Holder holder = new Holder();
        holder.initialize(bean);
    }
}
