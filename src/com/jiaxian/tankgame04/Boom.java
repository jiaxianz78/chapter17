package com.jiaxian.tankgame04;

public class Boom {
    int x, y;//炸彈座標
    int life = 9; //炸彈生命週期
    boolean isLive = true;

    public Boom(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //減少生命值
    public void lifeDown() {
        if (life > 0) {
            life--;
        } else {
            isLive = false;
        }
    }
}
