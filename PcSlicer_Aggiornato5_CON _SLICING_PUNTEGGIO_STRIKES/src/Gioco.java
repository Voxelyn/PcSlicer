
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;


/**
 *
 * @author singh.anshmeet
 */
public class Gioco extends JPanel implements ActionListener, MouseListener, MouseMotionListener{
    public ArrayList <Componente> componenti = new ArrayList<Componente>();
    public ArrayList <Point> punti = new ArrayList<Point>(); //punti Tracciati
    public Timer timer;
    public JLabel titolo;
    public JTextArea punteggio;
    public int cont = 0;
    public boolean gameOver = false;
    public int strike = 0;

    
    
    public Gioco(){

        Dimension d = new Dimension(800,600);
        this.setPreferredSize(d);
        
        this.setVisible(true);
        this.setDoubleBuffered(true); // lag meno
        titolo = new JLabel("Score: ");
        titolo.setBounds(30, 30, 50, 50);
        
        punteggio = new JTextArea();
        punteggio.setBounds(100, 60, 50, 50);
        punteggio.setEditable(false);
        
        this.add(titolo);
        this.add(punteggio);
        
        timer = new Timer(1000/60,this);
        timer.start();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);   
    }

    public void actionPerformed(ActionEvent e) {
        if(gameOver){
            timer.stop();
            return;
        }
        
        //SPAWN COMPONENTE
        if(Math.random() < 0.03){
            int x = (int)(Math.random() * this.getWidth());
            int y = this.getHeight();
            int velocitaX = -5 + (int) (Math.random() * 11);
            int velocitaY = 20 + (int) (Math.random() * 10);
          
            
            boolean isBomba = Math.random() < 0.2;
            componenti.add(new Componente(x,y,velocitaX,velocitaY, isBomba));
            
        }
        
        for(Componente x : componenti){
            x.velocita();
        }
        
        Iterator <Componente> iteratore = componenti.iterator();
        
        while(iteratore.hasNext()){
            Componente c = iteratore.next();
            if(c.fuoriSchermo(800, 600)){
                if(!c.getTagliato() && !c.bomba()){
                    strike++;
                    repaint();
                    if(strike >= 3){
                        JOptionPane.showMessageDialog(this, "3 STRIKE !!! GAME OVER!!!", "ATTENZIONE", JOptionPane.WARNING_MESSAGE);
                        //f.dispose();
                        timer.stop();                        
                    }
                }
                iteratore.remove();
            }
                
        }
        
        repaint();
    }
    
    public void paint(Graphics g){
        super.paint(g);
        
        for(Componente x : componenti){
            x.paint(g);
        }
        
        if(punti.size() > 1){
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.RED);
            g2.setStroke(new BasicStroke(5));
            for(int i=0; i<punti.size()-1; i++){
                Point p1 = punti.get(i);
                Point p2 = punti.get(i+1);
                g2.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
            
        }
        
        if(strike == 1){
            Graphics2D g2 = (Graphics2D) g;
            
            g2.setColor(Color.red);
            g2.setStroke(new BasicStroke(15));
            
            //PRIMA X
            g2.drawLine(750, 20, 800, 70);
            g2.drawLine(800, 20, 750, 70);
        }
        
        if(strike == 2){
            Graphics2D g2 = (Graphics2D) g;
            
            g2.setColor(Color.red);
            g2.setStroke(new BasicStroke(15));
            
            //PRIMA X
            g2.drawLine(750, 20, 800, 70);
            g2.drawLine(800, 20, 750, 70);
            
            //SECONDA X
            g2.drawLine(690, 20, 740, 70);
            g2.drawLine(740, 20, 690, 70);            
        }
        
        if(strike == 3){
            Graphics2D g2 = (Graphics2D) g;
            
            g2.setColor(Color.red);
            g2.setStroke(new BasicStroke(15));
            
            //PRIMA X
            g2.drawLine(750, 20, 800, 70);
            g2.drawLine(800, 20, 750, 70);
            
            //SECONDA X
            g2.drawLine(690, 20, 740, 70);
            g2.drawLine(740, 20, 690, 70); 
            
            //TERZA X
            g2.drawLine(630, 20, 680, 70);
            g2.drawLine(680, 20, 630, 70);  
        }
    }
    public boolean collisioni(Componente x, ArrayList<Point> punti){
        if(punti.size() < 2){
            return false;
        }
        
        double cX = x.getCentrox();
        double cY = x.getCentroY();
        for(int i=0; i<punti.size()-1; i++){
            Point p1 = punti.get(i);
            Point p2 = punti.get(i+1);
            
            double distance = distanza(cX, cY, p1,p2);
            if(distance <= 50){
                return true;
            }
        }
        return false;
    }
    
    public double distanza(double cx, double cy, Point p1, Point p2){
        double A = cx - p1.x;
        double B = cy - p1.y;
        double C = p2.x - p1.x;
        double D = p2.y - p1.y;
        double punto = A * C + B * D;
        double lenSQ = C * C + D * D;
        double param = (lenSQ != 0) ? punto/ lenSQ : -1;
        
        double vicinoX, vicinoY;
        if(param<0){
            vicinoX = p1.x;
            vicinoY = p1.y;
        } else if(param > 1){
            vicinoX = p2.x;
            vicinoY = p2.y;
        } else{
            vicinoX = p1.x + param * C;
            vicinoY = p1.y + param * D;
        }
        double dx = cx - vicinoX;
        double dy = cy - vicinoY;
        
        return Math.sqrt(dx * dx + dy*dy);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(gameOver) return;
        punti.clear();
        punti.add(e.getPoint());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        punti.clear();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
        Point nuovoPunto = e.getPoint();
        punti.add(nuovoPunto);
        

        this.punteggio.setText(cont+"");
        if(punti.size() > 2){
            for(Componente x : componenti){
                if(!x.getTagliato()){
                    if(collisioni(x, punti)){
                        x.setTagliato(true);
                        cont++;
                        if(x.bomba()){
                            gameOver = true;
                            //f.dispose();
                            JOptionPane.showMessageDialog(this, "GAME OVER!!!", "ATTENZIONE", JOptionPane.WARNING_MESSAGE);
                            
                        }
                    }
                }
            }
        }
        
        System.out.println("conto punti: " + punti.size());
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
    
    
}
