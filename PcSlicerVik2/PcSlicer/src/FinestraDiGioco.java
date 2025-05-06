
import javax.swing.JFrame;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author franzoni.andrea
 */
public class FinestraDiGioco extends JFrame{
    
    //public Gioco g = new Gioco(this);
    public FinestraDiGioco(Gioco g){
        this.add(g);
        
        this.pack();
        this.setVisible(true);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setTitle("PcSlicer.exe");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        
        
    }
    
}
