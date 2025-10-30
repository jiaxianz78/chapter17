package com.jiaxian.tankgame04;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener, Runnable {
    //定義坦克
    Hero hero = null;

    //定義敵對坦克並放入Vector
    Vector<EnemyTank> enemyTanks = new Vector<>();
    int enemyTanksSize = 3;

    //定義Vector用於存放炸彈
    Vector<Boom> booms = new Vector<>();

    //定義炸彈圖片
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;


    public MyPanel() {
        // 初始化自己坦克
        hero = new Hero(500, 100);

        //初始化敵對坦克
        for (int i = 0; i < enemyTanksSize; i++) {
            //創建敵對坦克
            EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
            //初始坦克方向
            enemyTank.setDirection(2);
            //啟動敵對坦克移動線程
            new Thread(enemyTank).start();
            //enemyTank加入一顆子彈
            Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirection());
            //enemyTank的成員
            enemyTank.shots.add(shot);
            new Thread(shot).start();
            //畫入
            enemyTanks.add(enemyTank);

        }

        //初始爆炸圖片
        image1 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/images/boom1.png"));
        image2 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/images/boom2.png"));
        image3 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/images/boom3.png"));

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 1000); //填充矩行背板 默認黑色

        if (hero != null && hero.isLive) {
            drawTank(hero.getX(), hero.getY(), g, hero.getDirection(), 1);
        }
        //畫出坦克 封裝方法

        //畫hero子彈
//        if (hero.shot != null && hero.shot.isLive != false) {
//            g.fill3DRect(hero.shot.x, hero.shot.y, 1, 1, false);
//            g.draw3DRect(hero.shot.x, hero.shot.y, 1, 1, false);
//
//        }

        //hero子彈遍歷匯出
        for (int i = 0; i < hero.shots.size(); i++) {
            Shot shot = hero.shots.get(i);
            if (shot != null && shot.isLive) {
                g.fill3DRect(shot.x, shot.y, 1, 1, false);

            } else {
                hero.shots.remove(shot);
            }
        }

        //如果集合有炸彈 就畫出
        for (int i = 0; i < booms.size(); i++) {
            Boom boom = booms.get(i);
            if (boom.life > 6) {
                g.drawImage(image1, boom.x, boom.y, 60, 60, this);
            } else if (boom.life > 3) {
                g.drawImage(image2, boom.x, boom.y, 60, 60, this);
            } else {
                g.drawImage(image3, boom.x, boom.y, 60, 60, this);
            }

            boom.lifeDown();
            //如果boom生命危0從集合中刪除
            if (boom.life == 0) {
                booms.remove(boom);
            }

        }

        //畫出敵對坦克 封裝方法
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            if (enemyTank.isLive) {
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirection(), 0);

                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    Shot shot = enemyTank.shots.get(j);
                    if (shot.isLive) {
                        g.draw3DRect(shot.x, shot.y, 1, 1, false);
                    } else {
                        enemyTank.shots.remove(shot);
                    }

                }

            }
        }
    }

    /**
     *
     * @param x         坦克左上角x座標
     * @param y         坦克左上角y座標
     * @param g         畫筆
     * @param direction 坦克方向
     * @param type      坦克類型
     */
    public void drawTank(int x, int y, Graphics g, int direction, int type) {

        //坦克類型顏色
        switch (type) {
            case 0: //自身坦克
                g.setColor(Color.CYAN);
                break;
            case 1://敵人坦愾
                g.setColor(Color.YELLOW);
                break;
        }


        //根據方向繪製對應坦克
        //direction 方向 0123 上右下左
        switch (direction) {
            case 0:
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y);
                break;
            case 1:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x + 60, y + 20);
                break;
            case 2:
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y + 60);
                break;
            case 3:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x, y + 20, x + 30, y + 20);
                break;
            default:
                break;
        }
    }

    //敵對坦克是否擊中我方坦克

    public void hitHero() {
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);

            for (int j = 0; j < enemyTank.shots.size(); j++) {

                Shot shot = enemyTank.shots.get(j);

                if (hero.isLive && shot.isLive) {
                    hitTank(shot, hero);
                }

            }
        }
    }


    public void hitEnemyTank() {
        for (int j = 0; j < hero.shots.size(); j++) {
            Shot shot = hero.shots.get(j);


            if (shot != null && shot.isLive) {

                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    hitTank(shot, enemyTank);
                }
            }
        }
    }

    //判斷我方是否擊中敵方
    public void hitTank(Shot s, Tank enemyTank) {
        switch (enemyTank.getDirection()) {
            case 0:
            case 2:
                if (s.x > enemyTank.getX() && s.x < enemyTank.getX() + 40
                        && s.y > enemyTank.getY() && s.y < enemyTank.getY() + 60) {
                    s.isLive = false;
                    enemyTank.isLive = false;

                    //敵對坦克被擊中從 Vector移出
                    enemyTanks.remove(enemyTank);
                    //創建Boom 加入booms集合中
                    Boom boom = new Boom(enemyTank.getX(), enemyTank.getY());
                    booms.add(boom);
                }
                break;
            case 1:
            case 3:
                if (s.x > enemyTank.getX() && s.x < enemyTank.getX() + 60
                        && s.y > enemyTank.getY() && s.y < enemyTank.getY() + 40) {
                    s.isLive = false;
                    enemyTank.isLive = false;
                    //敵對坦克被擊中從 Vector移出
                    enemyTanks.remove(enemyTank);
                    //創建Boom 加入booms集合中
                    Boom boom = new Boom(enemyTank.getX(), enemyTank.getY());
                    booms.add(boom);
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    //wasd 按鍵處理
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            hero.setDirection(0);
            if (hero.getY() > 0) {
                hero.moveUp();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            hero.setDirection(1);
            if (hero.getX() + 60 < 1000) {
                hero.moveRight();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            hero.setDirection(2);
            if (hero.getY() + 60 < 750) {
                hero.moveDown();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            hero.setDirection(3);
            if (hero.getX() > 0) {
                hero.moveLeft();
            }
        }

        //射擊觸發按鈕 J

        if (e.getKeyCode() == KeyEvent.VK_J) {
//            System.out.println("使用者和下J");

//            if (hero.shot == null || !hero.shot.isLive) {
//                hero.shotEnemyTank();
//            }
            hero.shotEnemyTank();
        }

        this.repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


            //判斷是否擊中敵人坦克
            hitEnemyTank();
            hitHero();
            this.repaint();
        }
    }


}
