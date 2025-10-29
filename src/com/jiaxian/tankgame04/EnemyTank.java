package com.jiaxian.tankgame04;


import java.util.Vector;

//敵人的坦克
public class EnemyTank extends Tank implements Runnable {

    Vector<Shot> shots = new Vector<>();
    boolean isLive = true;

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    @Override
    public void run() {
        //根據坦克方向移動 並隨機改變坦克方向
        while (true) {
            switch (getDirection()) {
                //上右下左
                case 0:
                    //讓坦克走30步
                    for (int i = 0; i <= 30; i++) {
                        if (getY() > 0) {
                            moveUp();
                        }

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;

                case 1:
                    for (int i = 0; i <= 30; i++) {
                        if (getX() + 60 < 1000) {
                            moveRight();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i <= 30; i++) {
                        if (getY() + 60 < 750) {
                            moveDown();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i <= 30; i++) {
                        if (getX() < 0) {
                            moveLeft();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
            }

            //隨機改變坦克方向
            setDirection((int) (Math.random() * 4));

            if (!(isLive)) {
                break;
            }
        }
    }
}
