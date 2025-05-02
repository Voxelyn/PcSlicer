import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author malagrino.luisa, franzoni.andrea, singh.anshmeet
 */
public class FinestraPrincipale extends javax.swing.JFrame implements MouseListener, MouseMotionListener{
    public ArrayList<Point> punti = new ArrayList<Point>(); // punti tracciati
    public boolean cancella = false;
    private Image sfondoImage;
    private boolean giocaTagliato = false;

    public FinestraPrincipale() {
        initComponents();
        this.setSize(1472, 832);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setTitle("PcSlicer.exe");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        // Carica immagine di sfondo (assicurati che il file sia nella directory corretta)
        sfondoImage = new ImageIcon("sfondoPC.jpg").getImage();

        // Imposta il pannello con lo sfondo
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (sfondoImage != null) {
                    g.drawImage(sfondoImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        panel.setLayout(null); // Per posizionamento manuale
        setContentPane(panel);

        // Aggiungi label "gioca"
        gioca.setIcon(new ImageIcon("GIOCA.png"));
        panel.add(gioca);

        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // Disegna le linee
        if (!cancella && punti.size() > 1) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.RED);
            g2.setStroke(new BasicStroke(5));
            for (int i = 0; i < punti.size() - 1; i++) {
                Point p1 = punti.get(i);
                Point p2 = punti.get(i + 1);
                g2.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }
    
    public boolean collisioni(){
        if(this.giocaTagliato || punti.size() < 2){
            return false;
        }
        
        int cX = gioca.getX() + gioca.getWidth() / 2;
        int cY = gioca.getY() + gioca.getHeight() / 2;
        int raggio = Math.max(gioca.getWidth(), gioca.getHeight()) / 2;
        
        for(int i=0; i<punti.size()-1; i++){
            Point p1 = punti.get(i);
            Point p2 = punti.get(i+1);
            
            double distance = distanza(cX, cY, p1,p2);
            if(distance <= raggio){
                giocaTagliato = true;
                Gioco g = new Gioco();
                FinestraDiGioco f = new FinestraDiGioco(g);
                System.out.println("TAGLIATO SEEEEEE");
                
                break;
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gioca = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImages(null);

        gioca.setBackground(new java.awt.Color(0, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(gioca, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(523, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(179, 179, 179)
                .addComponent(gioca, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(466, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FinestraPrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FinestraPrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FinestraPrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FinestraPrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FinestraPrincipale().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel gioca;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        punti.clear();
        punti.add(e.getPoint());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        punti.clear();
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        punti.add(e.getPoint());
        collisioni();
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
}