package demoexam.indravadan.com.joustgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Enemy {
    int xPosition;
    int yPosition;
    int direction;
    Bitmap image;

    private Rect hitBox;
    private Rect hitBoxTop;

    public Enemy(Context context, int x, int y) {
        this.image = BitmapFactory.decodeResource(context.getResources(), R.drawable.e2);
        this.xPosition = x;
        this.yPosition = y;

        this.hitBox = new Rect(this.xPosition, this.yPosition + 20, this.xPosition + this.image.getWidth(), this.yPosition + 20 + this.image.getHeight() - 20);
        this.hitBoxTop = new Rect(this.xPosition, this.yPosition, this.xPosition + this.image.getWidth(), this.yPosition+20);
    }

    public void updateEnemyPosition() {
        this.xPosition = this.xPosition - 15;

        // update the position of the hitbox
        this.hitBox.left = this.xPosition;
        this.hitBox.right = this.xPosition + this.image.getWidth();
        this.updateHitbox();

        this.updateHitboxTop();
    }

    public void updateHitbox() {
        // update the position of the hitbox
        this.hitBox.left = this.xPosition;
        this.hitBox.top = this.yPosition + 20;
        this.hitBox.right = this.xPosition + this.image.getWidth();
        this.hitBox.bottom = this.yPosition + 20 + this.image.getHeight() - 20;
    }

    public void  updateHitboxTop(){
        this.hitBoxTop.left = this.xPosition;
        this.hitBoxTop.top = this.yPosition;
        this.hitBoxTop.right = this.xPosition + this.image.getWidth();
        this.hitBoxTop.bottom = this.yPosition + 20;
    }

    public Rect getHitbox() {
        return this.hitBox;
    }

    public  Rect getHitboxTop(){
        return  this.hitBoxTop;
    }

    public void setXPosition(int x) {
        this.xPosition = x;
        this.updateHitbox();
        this.updateHitboxTop();
    }
    public void setYPosition(int y) {
        this.yPosition = y;
        this.updateHitbox();
        this.updateHitboxTop();
    }
    public int getXPosition() {
        return this.xPosition;
    }
    public int getYPosition() {
        return this.yPosition;
    }

    public Bitmap getBitmap() {
        return this.image;
    }
}
