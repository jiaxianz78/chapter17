package com.jiaxian.tankgame03;

import javax.swing.*;

public class JiaxianTankGame03 extends JFrame {

    MyPanel mp = null;
    public static void main(String[] args) {

        JiaxianTankGame03 jiaxianTankGame01 = new JiaxianTankGame03();

    }

    public JiaxianTankGame03() {
        mp = new MyPanel();
        //將mp放入Thread 並啟動
        new Thread(mp).start();
        this.add(mp);
        this.setSize(1000,750);
        this.addKeyListener(mp);//監聽鍵盤
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
