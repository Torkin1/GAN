package mp.homework.gan.view.mainmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mp.homework.gan.R;
import mp.homework.gan.view.NotImplementedDialog;
import mp.homework.gan.view.singleplayergame.SinglePlayerGameActivity;

public class MainMenuActivity extends AppCompatActivity {

    private class Holder {
        private Button bPlay = findViewById(R.id.bPlay);
        private Button bScores = findViewById(R.id.bScores);
        private Button bCredits = findViewById(R.id.bCredits);
        private Button bTutorial = findViewById(R.id.bTutorial);

        private Holder() {
            bPlay.setOnClickListener(new BPlayHandler());
            bScores.setOnClickListener(new BScoresHandler());
            bCredits.setOnClickListener(new BCreditsHandler());
            bTutorial.setOnClickListener(new BTutorialHandler());
        }

        private class BScoresHandler implements View.OnClickListener{
            @Override
            public void onClick(View v) {
                // TODO: starts ShowScores activity
                (new NotImplementedDialog()).show(MainMenuActivity.this.getSupportFragmentManager(), null);
            }
        }
        private class BCreditsHandler implements View.OnClickListener{
            @Override
            public void onClick(View v) {
                // TODO: starts ShowCredits activity
                (new NotImplementedDialog()).show(MainMenuActivity.this.getSupportFragmentManager(), null);
            }
        }
        private class BTutorialHandler implements View.OnClickListener{
            @Override
            public void onClick(View v) {
                // TODO: starts ShowTutorial
                (new NotImplementedDialog()).show(MainMenuActivity.this.getSupportFragmentManager(), null);
            }
        }

        private class BPlayHandler implements View.OnClickListener {
            // starts a SinglePlayerGame activity (more modes can be implemented using same GameController class)
            @Override
            public void onClick(View v) {
                Intent singlePlayerGameIntent = new Intent(v.getContext(), SinglePlayerGameActivity.class);
                startActivity(singlePlayerGameIntent);
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Holder holder = new Holder();

    }
}
