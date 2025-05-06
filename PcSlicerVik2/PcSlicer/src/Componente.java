/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author franzoni.andrea
 */


/**
 *
 * @author singh.anshmeet
 */
import java.awt.*;

public class Componente {
    public int x,y;
    public int velX,velY;
    public Color colore;
    public boolean taglio, flagBomba;
    
    
    public Componente(int coordX, int coordY, int veloX, int veloY, boolean fBomba){
        this.x = coordX;
        this.y = coordY;
        this.velX = veloX;
        this.velY = veloY;
        this.flagBomba = fBomba;
        this.colore = (flagBomba ? Color.BLACK : Color.RED);
    }
    
    public void paint(Graphics g){
        if(!this.getTagliato()){
            g.setColor(colore);
            g.fillOval(x, y, 50, 50);
        }
    }
    
    public void velocita(){
        this.y -= this.velY;
        this.velY -= 1;
        
        this.x += this.velX;
        //this.velX *= 0.99;
    }
    
    public boolean fuoriSchermo(int larghezza, int altezza){
        if(this.y > altezza || this.x < -50 || this.x > larghezza+50) {return true;}
        return false;
    }
    
    public void setTagliato(boolean t){
        this.taglio = t;
    }
    public boolean getTagliato(){
        return taglio;
    }
    public Color getColor(){
        return colore;
    }
    
    public boolean bomba(){
        return flagBomba;
    }
    
    public int getCentrox(){
        return x + 25;
    }
    
    public int getCentroY(){
        return y + 25;
    }
    
    
    
    
    
}
