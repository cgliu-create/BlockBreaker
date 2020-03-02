package CLOO;

import java.awt.Color;

public class Shape {
    // instance fields
    private int xPos;
    private int yPos;
    private int width;
    private int height;
    //booleans that control movement
    private boolean moving=false;
    private boolean movingUp=false;
    private boolean movingDown=true;
    private boolean movingLeft=false;
    private boolean movingRight=false;
    //constructor
    private Color color;
    public Shape(int x, int y, int w, int h, Color c){
        xPos=x; yPos=y; width=w; height=h; color=c;
    }
    //methods to access fields
    public int getxPos(){return xPos;}
    public int getyPos(){return yPos;}
    public int getWidth(){return width;}
    public int getHeight(){return height;}
    public Color getColor(){return color;}
    //methods to access fields
    public void setxPos(int x){xPos=x;}
    public void setyPos(int y){yPos=y;}
    public void setWidth(int w){width=w;}
    public void setHeight(int h){height=h;}
    public void setColor(Color c){color=c;}
    //methods to access booleans
    public boolean isMoving(){return moving;}
    public boolean isMovingRight(){return movingRight;}
    public boolean isMovingLeft(){return movingLeft;}
    public boolean isMovingUp(){return movingUp;}
    public boolean isMovingDown(){return movingDown;}
    //methods to change booleans
    public void setMovingRight() {moving=true;movingLeft=false;movingRight=true;}
    public void setMovingLeft(){moving=true;movingLeft=true;movingRight=false;}
    public void setMovingDown() {moving=true;movingDown=true;movingUp=false;}
    public void setMovingUp(){moving=true;movingDown=false;movingUp=true;}
    public void setMoving(){moving=false;movingLeft=false;movingRight=false;}
    //checks "contact" with two shapes
    //other = ball
    public boolean hitsTop(Object s){
        Shape other = (Shape)s;
        if(other.getxPos()>=this.getxPos()&&other.getxPos()<=(this.getxPos()+this.width)){
            if(Math.abs((other.getyPos()+other.height) - this.getyPos())<=10){
                return true;
            }
        }
        return false;
    }
    public boolean hitsBottom(Object s){
        Shape other = (Shape)s;
        if(other.getxPos()>=this.getxPos()&&other.getxPos()<=(this.getxPos()+this.width)){
            if(Math.abs(other.getyPos() - (this.getyPos()+this.height))<=10){
                return true;
            }
        }
        return false;
    }
    public boolean hitsLeft(Object s){
        Shape other = (Shape)s;
        if(other.getyPos()>=this.getyPos()&&other.getyPos()<=(this.getyPos()+this.height)){
            if(Math.abs(other.getxPos()-this.getxPos())<=10){
                return true;
            }
        }
        return false;
    }
    public boolean hitsRight(Object s){
        Shape other = (Shape)s;
        if(other.getyPos()>=this.getyPos()&&other.getyPos()<=(this.getyPos()+this.height)){
            if(Math.abs(other.getxPos() - (this.getxPos() + this.width) )<= 10){
                return true;
            }
        }
        return false;
    }

}
