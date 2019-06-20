package demoexam.indravadan.com.joustgame;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Player {
    int xPosition;
    int yPosition;
    int direction = -1;              // -1 = not moving, 0 = down, 1 = up
    Bitmap playerImage;

    private Rect hitBox;
    private Rect hitBoxBottom;

    public Player(Context context, int x, int y) {
        // 1. set up the initial position of the Enemy
        this.playerImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.pikachu);
        this.xPosition = x;
        this.yPosition = y;
        this.hitBox = new Rect(this.xPosition, this.yPosition, this.xPosition + this.playerImage.getWidth(), this.yPosition + this.playerImage.getHeight() - 20);
        this.hitBoxBottom = new Rect(this.xPosition, this.yPosition + this.playerImage.getHeight() - 20, this.xPosition + this.playerImage.getWidth(), this.yPosition + this.playerImage.getHeight() - 20 + 20 );
    }

    public void updatePlayerPosition() {
        if (this.direction == 0) {
            // move down
            this.yPosition = this.yPosition - 15;
        }
        else if (this.direction == 1) {
            // move up
            this.yPosition = this.yPosition + 15;
        }

        // update the position of the hitbox
        this.updateHitbox();
        this.updatHitboxBottom();
    }

    public void updateHitbox() {
        // update the position of the hitbox
        this.hitBox.left = this.xPosition;
        this.hitBox.top = this.yPosition;
        this.hitBox.right = this.xPosition + this.playerImage.getWidth();
        this.hitBox.bottom = this.yPosition + this.playerImage.getHeight() - 20;
    }

    public void updatHitboxBottom(){
        this.hitBoxBottom.left = this.xPosition;
        this.hitBoxBottom.top = this.yPosition + this.playerImage.getHeight() - 20;
        this.hitBoxBottom.right = this.xPosition + this.playerImage.getWidth();
        this.hitBoxBottom.bottom = this.yPosition + this.playerImage.getHeight() - 20 + 20;
    }

    public Rect getHitbox() {
        return this.hitBox;
    }

    public  Rect getHitboxBottom(){
        return  this.hitBoxBottom;
    }


    public void setXPosition(int x) {
        this.xPosition = x;
        this.updateHitbox();
        this.updatHitboxBottom();
    }
    public void setYPosition(int y) {
        this.yPosition = y;
        this.updateHitbox();
        this.updatHitboxBottom();
    }

    public int getXPosition() {
        return this.xPosition;
    }

    public int getYPosition() {
        return this.yPosition;
    }


    public void setDirection(int i) {
        this.direction = i;
    }

    public Bitmap getBitmap() {
        return this.playerImage;
    }
}
