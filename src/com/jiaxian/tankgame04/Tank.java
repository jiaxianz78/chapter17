package com.jiaxian.tankgame04;

public class Tank {
    private int x;//坦克X座標
    private int y;//坦克y座標
    private int direction;//坦克方向 0 1 2 3 上右下左
    private int speed = 8;


    //上右下左移動

    public void moveUp() {
        y -= speed;
    }

    public void moveRight() {
        x += speed;
    }

    public void moveDown() {
        y += speed;
    }

    public void moveLeft() {
        x -= speed;
    }


    @Override
    public String toString() {
        return "Tank{" +
                "x='" + x + '\'' +
                ", y='" + y + '\'' +
                '}';
    }

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
