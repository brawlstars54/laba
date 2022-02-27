package com.poortoys.examples;

public class CalculationBrick extends AbstractCalculation{
    private String[] typeNames;
    private float[][] brickPriceAndSize;
    CalculationBrick(AppMain w) {
        super(w);
        brickPriceAndSize=new float[][]{{125,0.03125f},{200,0.0375f},{150,0.025f},{270,0.04f},{350,0.03125f}};
        typeNames=new String[]{"Кирпич полый","Кирпич красный","Кирпич мелкий","Кирпич большой","Кирпич твердый"};
    }
    void calculate(int type){
        try {
            calculateSP();
            int bricksCount=(int)(squareWalls/brickPriceAndSize[type][1]);
            bricksCount+=(int)(additional[4]?1:0)*(sizes[0]+sizes[1])*sizes[2]/brickPriceAndSize[type][1];
            float bricksCost=bricksCount*brickPriceAndSize[type][0];

            sumCost+=bricksCost;
            showMain("Строй-мат: Кирпич("+typeNames[type]+":"+bricksCount+" кирпичей)<br> Стоимость кирпичей"+bricksCost+",руб<br>");
        } catch (Exception e) {
            app.print(e.getMessage());
        }
    }
    public String[] getNames(){
        return typeNames;
    }
}
