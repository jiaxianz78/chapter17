package com.jiaxian.tankgame04;

import java.util.Vector;

public class Hero extends Tank {

    // 定義shot
    Shot shot = null;
    Vector<Shot> shots = new Vector<>();

    public Hero(int x, int y) {
        super(x, y);
    }


    //射擊
    public void shotEnemyTank() {
        if(shots.size() == 5) {
            return;
        }
        //創建Shot 根據Hero創建

        switch (getDirection()) {//得到Hero方向
            case 0:
                shot = new Shot(getX() + 20, getY(), 0);
                break;
            case 1:
                shot = new Shot(getX() + 60, getY() + 20, 1);
                break;
            case 2:
                shot = new Shot(getX() + 20, getY() + 60, 2);
                break;
            case 3:
                shot = new Shot(getX(), getY() + 20, 3  );
                break;
        }


        shots.add(shot);

        //啟動Shot
        new Thread(shot).start();
    }

}
