package demoexam.indravadan.com.joustgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameEngine extends SurfaceView implements Runnable {

    final static String TAG="JOUST-GAME";

    int screenHeight;
    int screenWidth;

    boolean gameIsRunning;

    Thread gameThread;

    SurfaceHolder holder;
    Canvas canvas;
    Paint paintbrush;

    Player player;

    Enemy enemy;

    int score = 0;
    int lives = 4;

    Square line1;
    Square line2;
    Square line3;
    Square line4;
    Square line5;

    int SQUARE_WIDTH = 400;
    int SQUARE_HEIGHT = 50;

    boolean playerMovingUp = true;



    public GameEngine(Context context, int w, int h) {
        super(context);


        this.holder = this.getHolder();
        this.paintbrush = new Paint();

        this.screenWidth = w;
        this.screenHeight = h;

        this.line1 = new Square(context, 1200, this.screenHeight - 500, SQUARE_WIDTH, SQUARE_HEIGHT);
        this.line2 = new Square(context, 200, this.screenHeight - 500, SQUARE_WIDTH, SQUARE_HEIGHT);
        this.line3 = new Square(context, this.screenWidth / 2 - 200, this.screenHeight - 700, SQUARE_WIDTH, SQUARE_HEIGHT);
        this.line4 = new Square(context, 1200, this.screenHeight - 900, SQUARE_WIDTH, SQUARE_HEIGHT);
        this.line5 = new Square(context, 200, this.screenHeight - 900, SQUARE_WIDTH, SQUARE_HEIGHT);

        this.spawnPlayer();
        this.spawnEnemy();
    }

    private void spawnPlayer() {
        //@TODO: Start the player at the left side of screen

        player = new Player(this.getContext(), this.screenWidth / 2, this.screenHeight - 400);
    }
    private void spawnEnemy() {
        //Random random = new Random();

        //@TODO: Place the enemies in a random location
        enemy = new Enemy(this.getContext(), 1400, 500);

    }

    @Override
    public void run() {
        while (gameIsRunning == true) {
            this.updatePositions();
            this.redrawSprites();
            this.setFPS();
        }
    }


    public void pauseGame() {
        gameIsRunning = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            // Error
        }
    }

    public void startGame() {
        gameIsRunning = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void updatePositions() {

        // @TODO: Update position of player
        player.updatePlayerPosition();
        // @TODO: Update position of enemy ships
        enemy.updateEnemyPosition();
        // @TODO: Update the position of the sprites

        if (enemy.getXPosition() <= 0) {
            enemy.setXPosition(this.screenWidth);
        }

        if(player.getYPosition() <= 0){
            player.setYPosition(this.screenHeight - 400);
        }

        // @TODO: Collision detection between player and enemy
        if (player.getHitbox().intersect(enemy.getHitbox())) {
            // reduce lives
            lives--;
            // reset player to original position
            player.setXPosition(this.screenWidth / 2);
            player.setYPosition(this.screenHeight-400);
        }

        if(player.getHitboxBottom().intersect(enemy.getHitboxTop())){
            score++;

            enemy.setXPosition(1400);
            enemy.setYPosition(500);
        }

        if(player.getHitbox().intersect(line1.getHitbox())){
            playerMovingUp = false;

        }

        if(player.getHitbox().intersect(line2.getHitbox())){

        }

        if(player.getHitbox().intersect(line3.getHitbox())){

        }

        if(player.getHitbox().intersect(line4.getHitbox())){

        }

        if(player.getHitbox().intersect(line5.getHitbox())){

        }

        }


    public void redrawSprites() {
        if (this.holder.getSurface().isValid()) {
            this.canvas = this.holder.lockCanvas();

            this.canvas.drawColor(Color.argb(255,0,0,255));
            paintbrush.setStyle(Paint.Style.STROKE);
            paintbrush.setStrokeWidth(5);

            canvas.drawBitmap(this.player.getBitmap(), this.player.getXPosition(), this.player.getYPosition(), paintbrush);

            //@TODO: Draw the enemy
            canvas.drawBitmap(this.enemy.getBitmap(), this.enemy.getXPosition(), this.enemy.getYPosition(), paintbrush);

            paintbrush.setColor(Color.RED);
            paintbrush.setStyle(Paint.Style.STROKE);
            paintbrush.setStrokeWidth(5);
            Rect playerHitbox = player.getHitbox();
            canvas.drawRect(playerHitbox.left, playerHitbox.top, playerHitbox.right, playerHitbox.bottom, paintbrush);

            Rect playerHitboxBottom = player.getHitboxBottom();
            canvas.drawRect(playerHitboxBottom.left, playerHitboxBottom.top, playerHitboxBottom.right, playerHitboxBottom.bottom, paintbrush);

            Rect enemyHitbox = enemy.getHitbox();
            canvas.drawRect(enemyHitbox.left, enemyHitbox.top, enemyHitbox.right, enemyHitbox.bottom, paintbrush);

            Rect enemyHitboxTop = enemy.getHitboxTop();
            canvas.drawRect(enemyHitboxTop.left, enemyHitboxTop.top, enemyHitboxTop.right, enemyHitboxTop.bottom, paintbrush);

            paintbrush.setColor(Color.BLACK);
            paintbrush.setStyle(Paint.Style.FILL);
            canvas.drawRect(
                    this.line1.getxPosition(),
                    this.line1.getyPosition(),
                    this.line1.getxPosition() + this.line1.getWidth(),
                    this.line1.getyPosition() + this.line1.getHeight(),
                    paintbrush
            );
            canvas.drawRect(
                    this.line1.getHitbox(),
                    paintbrush
            );

            canvas.drawRect(
                    this.line2.getxPosition(),
                    this.line2.getyPosition(),
                    this.line2.getxPosition() + this.line2.getWidth(),
                    this.line2.getyPosition() + this.line2.getHeight(),
                    paintbrush
            );
            canvas.drawRect(
                    this.line2.getHitbox(),
                    paintbrush
            );

            canvas.drawRect(
                    this.line3.getxPosition(),
                    this.line3.getyPosition(),
                    this.line3.getxPosition() + this.line3.getWidth(),
                    this.line3.getyPosition() + this.line3.getHeight(),
                    paintbrush
            );
            canvas.drawRect(
                    this.line3.getHitbox(),
                    paintbrush
            );

            canvas.drawRect(
                    this.line4.getxPosition(),
                    this.line4.getyPosition(),
                    this.line4.getxPosition() + this.line4.getWidth(),
                    this.line4.getyPosition() + this.line4.getHeight(),
                    paintbrush
            );
            canvas.drawRect(
                    this.line4.getHitbox(),
                    paintbrush
            );

            canvas.drawRect(
                    this.line5.getxPosition(),
                    this.line5.getyPosition(),
                    this.line5.getxPosition() + this.line5.getWidth(),
                    this.line5.getyPosition() + this.line5.getHeight(),
                    paintbrush
            );
            canvas.drawRect(
                    this.line5.getHitbox(),
                    paintbrush
            );

            // draw game stats
            paintbrush.setTextSize(60);
            canvas.drawText("Lives remaining: " + lives, 100, 800, paintbrush);
            canvas.drawText("Score:" + score, 100, 850,paintbrush);

            //----------------
            this.holder.unlockCanvasAndPost(canvas);
        }
    }

    public void setFPS() {
        try {
            gameThread.sleep(50);
        }
        catch (Exception e) {

        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_UP:


                double e = (int) event.getX() - this.player.getXPosition();
                double f = (int)event.getY() - this.player.getYPosition();

                // d = sqrt(a^2 + b^2)

                double d1 = Math.sqrt((e * e) + (f * f));

                // 2. calculate xn and yn constants
                // (amount of x to move, amount of y to move)
                double xn1 = (e / d1);
                double yn1 = (f / d1);

                // 3. calculate new (x,y) coordinates
                int newX = this.player.getXPosition() + (int) (xn1 * 100);
                int newY = this.player.getYPosition() + (int) (yn1 * 100);
                this.player.setXPosition(newX);
                this.player.setYPosition(newY);

                // 4. update the bullet hitbox position
                this.player.updateHitbox();





                break;

            case MotionEvent.ACTION_DOWN:

                break;

        }

        return true;
    }

}
