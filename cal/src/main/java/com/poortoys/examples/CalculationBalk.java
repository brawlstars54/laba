package com.poortoys.examples;

public class CalculationBalk extends AbstractCalculation{
    private float cost;
    private String[] typeNames;
    private float[][] balkPriceAndSize;
    CalculationBalk(AppMain w) {
        super(w);
        typeNames=new String[]{"Бревно гориз","Балка","Доски,арм","ПВП","Брусья"};
        balkPriceAndSize=new float[][]{{20,1},{200,0.0375f},{150,0.025f},{270,0.04f},{350,0.03125f}};
    }
    void calculate(int type){
        try {
            calculateSP();
            int balksCount=(int)(squareWalls/balkPriceAndSize[type][1]);
            float balksCost=balksCount*balkPriceAndSize[type][0];
            sumCost+=balksCost;
            showMain("Строй-мат: Дерево("+typeNames[type]+")<br> Стоимость:"+balksCost+"<br>");
        } catch (Exception e) {
            app.print(e.getMessage());
        }
    }
    public String[] getNames(){
        return typeNames;
    }
}
