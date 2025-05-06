


/**
 *
 * @author siciliano.alessio
 */

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class FinestraGraficoRecord extends JFrame {
    private JLabel jl;
    public FinestraGraficoRecord(){
        this.setSize(1000,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Medie della classe");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        jl = new JLabel();
    }
    
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        
        //Popoliamo l'array
        int temp;
        String str = "<html>";
        for(int i=0; i<Gioco.punteggi.size(); i++){
            temp= Gioco.punteggi.get(i);
            str += temp+"";
        }
        str += "</html>";
        this.add(jl);
        jl.setBounds(20,20,200,300);
        jl.setText(str);
        System.out.println(str);
        
        //TROVO MASSIMO DEI PUNTEGGI
        int max=0;
        for(int i=0; i<Gioco.punteggi.size(); i++){
            if(Gioco.punteggi.get(i)>max)
                max=Gioco.punteggi.get(i);
        }
        int x=300;
        for(int i=0; i<Gioco.punteggi.size(); i++){
            if(Gioco.punteggi.get(i)<max)
                g2.setColor(Color.red);
            else
                g2.setColor(Color.orange);
            
            g2.fillRect(x, 550-((int)Gioco.punteggi.get(i)*50),
                  30, (int)Gioco.punteggi.get(i)*50);
            x+=60;
        }
        
        g2.setStroke(new BasicStroke(5f));
        g2.setColor(Color.black);
        g2.drawLine(270, 550, x, 550);
        
        
    }
}
