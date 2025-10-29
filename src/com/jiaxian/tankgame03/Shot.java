package com.jiaxian.tankgame03;


public class Shot implements Runnable {

    int x;
    int y;
    int direction = 0;
    int spped = 2;
    boolean isLive = true;


    public Shot(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }


    @Override
    public void run() {//射擊
        while (true) {

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            switch (direction) {
                case 0:
                    y -= spped;
                    break;
                case 1:
                    x += spped;
                    break;
                case 2:
                    y += spped;
                    break;
                case 3:
                    x -= spped;
                    break;
            }

            System.out.println("子彈 X = " + x + ", Y = " + y);

            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750)){
                isLive = false;
                break;
            }
        }
    }
}
