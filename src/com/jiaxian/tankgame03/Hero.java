package com.jiaxian.tankgame03;

public class Hero extends Tank {

    // 定義shot
    Shot shot = null;

    public Hero(int x, int y) {
        super(x, y);
    }


    //射擊
    public void shotEnemyTank() {
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

        //啟動Shot
        new Thread(shot).start();
    }

}
