package com.poortoys.examples;

public abstract class AbstractCalculation{
    AppMain app;
    protected float square;
    protected float squareWalls;
    private float perimetr;
    protected float[] sizes;
    protected boolean[] additional;
    private String[] addsName;
    float sumCost;
    float[] addCosts;
    float[] mainCosts;
    float[] addsPrice;
    int[] mainParam;
    float[][] mainPrices;
    AbstractCalculation(Window w){
        app=w;
        sumCost=0;
        addsName=new String[]{"теплый шов","отделка","Электросеть","водоснабжение","+ ПОДВАЛ"};
        addsPrice=new float[]{50,200,25,20,700};
        mainPrices=new float[][]{{500,700,1000},{1000,1600},{300,1000,1500}};
        addCosts=new float[5];
        mainCosts=new float[4];
    }
    void calculateSP() throws Exception {
        sumCost=0;
        mainParam=app.getParam();
        additional=app.getAdditional();
        try{
        sizes=app.getSizes();
        for(int i=0;i<4;i++) if (sizes[i]<0)
            throw new Exception("<html><font color=\"red\"> Ошибка, число меньше нуля!</font>");
        square=sizes[0]*sizes[1];
        squareWalls=(sizes[0]+sizes[1])*2*sizes[2]*sizes[3];
        perimetr=(sizes[0]+sizes[1])*2;
        mainCosts[0]=square*(float)(Math.pow(sizes[3]*sizes[2]/7,1.3))*mainPrices[0][mainParam[0]];
        sumCost+=mainCosts[0]*1.5;
        mainCosts[1]=square*mainPrices[1][mainParam[1]];
        sumCost+=mainCosts[1]*1.2;
        mainCosts[2]=square*(mainParam[1]==0?1:1.6f)*mainPrices[2][mainParam[2]];
        sumCost+=mainCosts[2]*1.1;
        addCosts[0]=perimetr*(sizes[3]+1)+sizes[2]*4*sizes[3];
        addCosts[0]=addCosts[0]*addsPrice[0]*(additional[0]?1:0);
        sumCost+=addCosts[0]*1.4;
        addCosts[1]=(sizes[0]*sizes[2]+sizes[1]*sizes[2])*2*sizes[3]+sizes[0]*sizes[1]*sizes[3]*2;
        addCosts[1]=addCosts[1]*addsPrice[1]*(additional[1]?1:0);
        sumCost+=addCosts[1]*1.3;
        addCosts[2]=square*sizes[2]*sizes[3]*(additional[2]?1:0)*addsPrice[2];
        sumCost+=addCosts[2]*1.4;
        addCosts[3]=square*sizes[2]*sizes[3]*(additional[3]?1:0)*addsPrice[3];
        sumCost+=addCosts[3]*0.3;
        addCosts[4]=square*addsPrice[4]*(additional[4]?1:0);
        sumCost+=addCosts[4]*1.3;
        }catch (NumberFormatException nfe) {
            throw new Exception("<html><font color=\"red\"> Ошибка, введено не число!</font>");
        }
    }
    void showMain(String s){
        String output="<html><font color=\"green\">";
        app.print(output);
        output+="@Площадь:"+square;
        output+=", @Площадь стен: "+squareWalls;
        app.showPrice(addCosts);
        output+="<br>"+s+"</font><font color=\"white\"> Стоимость:"+sumCost+"</font>";
        app.print(output);
    }
