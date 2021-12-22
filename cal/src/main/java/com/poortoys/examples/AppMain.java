package com.poortoys.examples;

import javax.swing.JFrame;

public class AppMain{
	private JFrame frame;
	static AppMain okno=new AppMain();
	public AppMain() {
		frame=new JFrame();
        frame.setTitle ("Калькулятор стоимости строительства частного дома");
        frame.setBounds(300,0,600,700);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setVisible(true);
	}
    public static void main(String[] args) {
    }

}
