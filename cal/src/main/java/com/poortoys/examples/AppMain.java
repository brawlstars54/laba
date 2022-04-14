package com.poortoys.examples;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppMain {
        private int width;
        private int height;
        private JFrame GUI;
        private JPanel panel;
        private Background background;//проверка
        private JTextField inputW;
        private JTextField inputL;
        private JTextField inputH;
        private JLabel output;
        private JButton ExitButton;
        private JButton Calculate;
        private JMenuBar wallMaterial;
        private JComboBox foundation;
        private JComboBox roof;
        private JComboBox krol;
        private JSlider florCounter;
        private JRadioButton[] buttons;
        private String textColor;
        private CalculationBrick calculationBrick;
        private CalculationBalk calculationBalk;
        private int[] choice;
        private String[] namesAdd;
        public AppMain() throws InterruptedException {
            int TAB=20;
            int widthH=25;
            int poz=0;
            textColor="<html><font color=\"blue\">";
            width = 400;
            height = 600;
            GUI= new JFrame();
            panel = new JPanel();
            background= new Background(width,height,this);
            GUI.setBounds(100, 0, width, height);
            GUI.setTitle ("Калькулятор стоимости строительства частного дома");
            GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            GUI.setResizable(false);
            GUI.add(panel);
            GUI.addMouseListener(background.mouselistener);
            panel.setLayout(null);

            calculationBrick=new CalculationBrick(this);
            calculationBalk=new CalculationBalk(this);

            poz+=widthH;
            int widthl1=75;
            JLabel l1=new JLabel(textColor+ "Ширина, м:</font>");
            l1.setBounds(TAB,TAB,widthl1,widthH);
            inputW=new JTextField("");
            inputW.setBounds(TAB+widthl1,poz,100,widthH);
            panel.add(inputW);
            panel.add(l1);
            poz+=widthH;

            JLabel l2=new JLabel(textColor+ "Длина, м:</font>");
            l2.setBounds(TAB,poz,widthl1,widthH);
            inputL=new JTextField();
            inputL.setBounds(TAB+widthl1,poz,100,widthH);
            panel.add(inputL);
            panel.add(l2);
            poz+=widthH;

            JLabel l5=new JLabel(textColor+ "Стена, м:</font>");
            l5.setBounds(TAB,poz,widthl1,widthH);
            inputH=new JTextField();
            inputH.setBounds(TAB+widthl1,poz,100,widthH);
            panel.add(inputH);
            panel.add(l5);
            poz+=widthH+40;

            wallMaterial=new JMenuBar();
            choice=new int[2];
            choice[0]=0;
            JLabel l3=new JLabel(textColor+ "Стены:</font>");
            l3.setBounds(TAB,poz,(int)(widthl1*1.5),widthH);
            wallMaterial.setBounds(TAB+widthl1,poz,100,widthH);
            JMenuItem[] bricks=new JMenuItem[5];
            JMenuItem[] balks=new JMenuItem[5];
            JMenu kir=new JMenu("Кирпич");
            JMenu brev=new JMenu("Дерево");
            for(int i = 0; i < 5; i++) {
                bricks[i] = new JMenuItem(calculationBrick.getNames()[i]);
                int finalI = i;
                bricks[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        choice[0]=0;
                        choice[1]= finalI;
                    }
                });
                balks[i]=new JMenuItem(calculationBalk.getNames()[i]);
                balks[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        choice[0]=1;
                        choice[1]= finalI;
                    }
                });
                kir.add(bricks[i]);
                brev.add(balks[i]);
            }
            wallMaterial.add(kir);
            wallMaterial.add(brev);
            panel.add(wallMaterial);
            panel.add(l3);
            poz+=widthH;

            JLabel l4=new JLabel(textColor+ "Фундамент:</font>");
            l4.setBounds(TAB,poz,(int)(widthl1*1.5),widthH);
            foundation=new JComboBox(new String[]{"Ленточный", "Свайно-винтовый","Плиточный"});
            foundation.setBounds(TAB+widthl1,poz,100,widthH);
            panel.add(foundation);
            panel.add(l4);
            poz+=widthH;

            JLabel l6=new JLabel(textColor+ "Крыша:</font>");
            l6.setBounds(TAB,poz,(int)(widthl1*1.5),widthH);
            roof=new JComboBox(new String[]{"одно-скатная", "двух-скатная"});
            roof.setBounds(TAB+widthl1,poz,100,widthH);
            panel.add(roof);
            panel.add(l6);
            poz+=widthH;

            JLabel l7=new JLabel(textColor+ "Кровля:</font>");
            l7.setBounds(TAB,poz,(int)(widthl1*1.5),widthH);
            krol=new JComboBox(new String[]{"Профнастил", "Металло-череп","Гибкая черепица"});
            krol.setBounds(TAB+widthl1,poz,100,widthH);
            panel.add(krol);
            panel.add(l7);
            poz+=widthH+20;

            JLabel l8=new JLabel(textColor+ "Этажность</font></em>");
            l8.setBounds(250,(int)(widthH),150,widthH);
            florCounter=new JSlider(SwingConstants.VERTICAL,1,4,1);
            florCounter.setBounds((250),(int)(height/8-20),50,150);
            florCounter.setMajorTickSpacing(1);
            florCounter.setPaintLabels(true);
            florCounter.setPaintTicks(true);
            florCounter.setBackground(Color.BLACK);
            florCounter.setForeground(Color.white);
            panel.add(florCounter);
            panel.add(l8);

            namesAdd=new String[]{"Теплый шов","Отделка","Электричество","Водоснабжение","Подвал"};
            buttons=new JRadioButton[5];
            for(int i = 0; i < 5; i++) {
                buttons[i] = new JRadioButton();
                buttons[i].setBounds(TAB,poz,250,30);
                buttons[i].setContentAreaFilled(false);
                panel.add(buttons[i]);
                buttons[i].setText("<html><em><font color=\"blue\">"+namesAdd[i]+"</font></em>");
                poz+=widthH;
            }
            buttons[4].setBounds(250,(int)(widthH+190),200,widthH);

            Calculate=new JButton("Рассчитать");
            Calculate.setBounds(TAB,poz,150,widthH);
            panel.add( Calculate);
            Calculate.addActionListener((ActionEvent e)-> 
            	{
                    switch(choice[0]){
                        case 0:calculationBrick.calculate(choice[1]);break;
                        case 1:calculationBalk.calculate(choice[1]);break;
                    }
                }
            );
            poz+=widthH;

            output=new JLabel();
            output.setBounds(TAB,poz,width-3*TAB,100);
            panel.add(output);
            poz+=200;

            ExitButton=new JButton("Выход");
            ExitButton.setBounds(width-125,height-75,100,25);
            panel.add(ExitButton);
            ExitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   System.exit(0);
                }
            });
            poz+=25;

            ////////////////
            background.setBounds(0, 0, width, height);
            panel.add(background);
            background.run();

            GUI.setVisible(true);
        }
    public float[] getSizes() throws NumberFormatException{
            float[] arr=new float[4];
             try {
                 arr[0]=Float.parseFloat(inputW.getText());
                 arr[1]=Float.parseFloat(inputL.getText());
                 arr[2]=Float.parseFloat(inputH.getText());
                 arr[3]=florCounter.getValue();
            } catch (NumberFormatException nfe) {
                throw new NumberFormatException();
             }
            return arr;
    }
    public int[] getParam(){
            int[] arr=new int[3];
            arr[0]=foundation.getSelectedIndex();
            arr[1]=roof.getSelectedIndex();
            arr[2]=krol.getSelectedIndex();
            return arr;
    }
    public boolean[] getAdditional(){
            boolean[] arr=new boolean[5];
            for(int i=0;i<5;i++){
               arr[i]=buttons[i].isSelected();
            }
            return arr;
    }
    public void showPrice(float[] price){
        for(int i=0;i<5;i++){
            buttons[i].setText("<html><em><font color=\"blue\">"+namesAdd[i]+"</font></em>"
                    +" <font color=\"green\">"+price[i]+",руб</font>");;
        }

    }
    public void print(String s){
        output.setText(s);
    }
    public Background getBack(){
            return background;
    }
    public static void main(String[] args) throws InterruptedException {
        AppMain w=new AppMain();
    }
}

