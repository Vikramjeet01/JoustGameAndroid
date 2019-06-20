package demoexam.indravadan.com.joustgame;


import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;

public class MainActivity extends AppCompatActivity {

    GameEngine joustGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        joustGame = new GameEngine(this, size.x, size.y);

        setContentView(joustGame);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Pause the game
        joustGame.pauseGame();
    }

    @Override
    protected void onResume() {
        super.onResume();

        joustGame.startGame();
    }
}
