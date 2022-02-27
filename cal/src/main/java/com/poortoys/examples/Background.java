package com.poortoys.examples;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class Background extends JComponent {
    BufferedImage image;
    ArrayList<border> borders;
    Background background;
    int width;
    int height;
    Mouselistener mouselistener;
    Color[] c;
    int col = 0;
    AppMain w;
    public Background(int w, int h, AppMain window) {
        mouselistener=new Mouselistener();
        this.w=window;
        background=this;
        c = new Color[]{Color.ORANGE, Color.red, Color.blue, Color.green, Color.CYAN, Color.magenta, Color.white, Color.BLACK};
        width=w;
        height=h;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int i=0;i<height;i++){
            for (int j=0;j<width;j++){
                image.setRGB(j,i, Color.BLACK.getRGB());
            }
        }
        border b1 = new border(10, width - 10, 10, 10, new vec(0, 1));
        border b2 = new border(10, width - 10, height - 10, height - 10, new vec(0, -1));
        border b3 = new border(10, 10, 10, height - 10, new vec(1, 0));
        border b4 = new border(width - 10, width - 10, 10, height - 10, new vec(-1, 0));
        border[] borAr = {b1, b2, b3, b4};
        borders = new ArrayList<border>(Arrays.asList(borAr));

    }
    void run() throws InterruptedException {
        new line(this, Color.BLACK, 10, true, 1);
        new line(this, Color.BLACK, 10, true, 1);
    }
    void spawnLine(int x,int y){
        Point p=new Point(0,0);
        if(x+10>=width)x=width-20;
        if(x-10<=0)x=40;
        if(y+10>=height)y=height-20;
        if(y-10<=0)y=20;
        p.set(x,y);
        try {
            for (int i=0;i<3;i++){
                line l=new line(this, c[col],9,false,1000,p,true);
                col=(int) Background.random(0,6);
            }
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }
    public class Mouselistener implements MouseListener {

        public void mouseClicked(MouseEvent e) {
            w.getBack().spawnLine(e.getX()-10,e.getY()-30);
        }
        public void mousePressed(MouseEvent e) { }
        public void mouseReleased(MouseEvent e) { }
        public void mouseEntered(MouseEvent e) {
            w.getBack().spawnLine(e.getX()-10,e.getY()-30);
        }
        public void mouseExited(MouseEvent e) {  w.getBack().spawnLine(e.getX()-10,e.getY()-30);}
    }
    static float random(float from, float to){return from+(float)Math.random()*(to-from);
    }
}
class line extends Thread {
    int width = 4;
    int speed;
    boolean immortal = true;
    boolean canhavechild = false;
    float lifelenght = 3500;
    float lenghtoftrace = 0;
    float sumlenght = 0;
    Point leftPoint, rightPoint;
    vec v = new vec(Background.random(-10, 10), Background.random(-10, 10));
    Point gravityPoint;
    Color color;
    Background background;

    line(Background f, Color c, int spd, boolean im, float lifel, Point k, boolean child) throws InterruptedException {
        this(f, c, spd, im, lifel);
        rightPoint = new Point(k);
        canhavechild = child;
    }

    line(Background f, Color c, int spd, boolean im, float lifel) throws InterruptedException {
        background =f;
        color = c;
        rightPoint = new Point((float) (Math.random() * (background.width - width - 105) + width + 50), (float) (Math.random() * (background.height - width - 105) + width + 50));
        speed = spd;
        immortal = im;
        lifelenght = lifel;
        v.norm();
        gravityPoint =new Point(background.width*2, background.height/2);
        start();
    }
    public void run() {
        if (!immortal) {
            leftPoint = new Point(rightPoint);
            speed = 10 - speed;
            while (sumlenght + lenghtoftrace < lifelenght) {
                paint();
                try {
                    sleep(speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                rightPoint.add(v);
                gravity(rightPoint, v, 0.1f,gravityPoint, new vec(0,0), 3000);
                lenghtoftrace = rightPoint.mod(leftPoint);
            }
        } else while (true) {
            paint();
            rightPoint.add(v);
        }
    }

    void reflect(vec norm) {
        v.refrain(norm);
        if (!immortal) {
            sumlenght = sumlenght + lenghtoftrace;
            leftPoint.set(rightPoint);
            try {
                createchild();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void paint() {
        for (int i = (int) rightPoint.x; i < rightPoint.x + width; i++) {
            background.image.setRGB(i, (int) rightPoint.y, color.getRGB());
        }
        for (border b : background.borders) {
            if (b.intersect(rightPoint.addC(v.x, v.y))) reflect(b.norm);
        }
        background.repaint();
    }

    void createchild() throws InterruptedException {
        if (!canhavechild) return;
        else {
            new line(background, color, 8, false, 300, rightPoint, false);
            new line(background, color, 8, false, 300, rightPoint, false);
            canhavechild = false;
        }
    }
    void gravity(Point a, vec aa, float ma, Point b, vec bb, float mb){
        float G=0.06674f*3;
        vec Fg = new vec(a,b);
        Fg.norm(ma*mb / (float) Math.pow(a.mod(b), 2)*G);
        aa.add(Fg.mulC(1/ma));
        bb.add(Fg.reverseC().mulC(1/mb));
    }
}
class vec {
    static vec vv;
    static vec v;
    float x, y;
    vec(float xx, float yy) {
        x = xx;
        y = yy;
    }
    vec(Point a, Point b) {
        x = b.x - a.x;
        y = b.y - a.y;
    }
    vec(vec b) {
        x = b.x;
        y = b.y;
    }
    static vec mul(vec vv, float a) {
        v = new vec(vv);
        v.x = vv.x * a;
        v.y = vv.y * a;
        return v;
    }
    void refrain(vec norm) {
        vv = new vec(mul(norm, 2 * cos(this, norm)));
        vv.reverse();
        this.add(vv);
        x = x+ Background.random(-0.25f,0.25f);
        y = y+ Background.random(-0.25f,0.25f);
        norm();
    }
    float skl(vec v1, vec v2) {
        return v1.x * v2.x + v1.y * v2.y;
    }
    float skl(vec v) {
        return x * v.x + y * v.y;
    }
    float cos(vec a, vec b) {
        return (skl(a, b)) / (mod(a) * mod(b));
    }
    static float mod(vec v) {
        return (float) Math.sqrt(Math.pow(0 - v.x, 2) + Math.pow(0 - v.y, 2));
    }
    void norm() {
        float mod = mod(this);
        x = x / mod;
        y = y / mod;
    }
    vec norm(float k) {
        float mod = mod(this);
        x = x / mod * k;
        y = y / mod * k;
        return this;
    }
    vec add(vec b) {
        x = x + b.x;
        y = y + b.y;
        return this;
    }
    vec reverse() {
        x = -x;
        y = -y;
        return this;
    }
    vec reverseC() {
        vec cop = new vec(this);
        return cop.reverse();
    }
    vec mulC(float a) {
        vec v = new vec(this);
        v.x = v.x * a;
        v.y = v.y * a;
        return v;
    }
    void set(Point a, Point b) {
        x = b.x - a.x;
        y = b.y - a.y;
    }
}
class Point {
    float x,y,z;
    void set(float x,float y){
        this.x=x;
        this.y=y;
    }
    void set(Point b){
        x=b.x;
        y=b.y;
    }
    Point(float x,float y){
        this.x=x;
        this.y=y;
    }
    Point(Point b){
        x=b.x;
        y=b.y;
    }
    public Point add(vec v){
        x=x+v.x;
        y=y+v.y;
        return this;
    }
    public Point addC(float xx, float yy){
        return new Point(x+xx,y+yy);
    }
    float mod(Point b){
        return (float) Math.sqrt(Math.pow(b.x-x,2)+Math.pow(b.y-y,2));
    }
}
class border {
    float x1, x2, y1, y2;
    vec norm;
    Point s;
    vec t;

    border(float x1, float x2, float y1, float y2, vec norm) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.norm = norm;
        s = new Point(x2, y2);
        t = new vec(s, new Point(0, 0));
    }
    boolean intersect(Point p) {
        t.set(s, p);
        return t.skl(norm) <= 0;
    }
}
