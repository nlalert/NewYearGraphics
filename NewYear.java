import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class NewYear extends JPanel{

    public static void main(String[] args) {
        JFrame f = new JFrame();
        NewYear ny = new NewYear();
        f.add(ny);
        f.setTitle("New Year");
        f.setSize(600,600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        paintBackground(g);
        paintStar(g);
    }

    
    private void paintBackground(Graphics g){
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        g.setColor(Color.BLACK);
        fillTriangle(g, new int[]{0, panelWidth, 0}, new int[]{0, 0, panelHeight});
        fillTriangle(g, new int[]{panelWidth, panelWidth, 0}, new int[]{panelHeight, 0, panelHeight});
    }
    
    private void paintStar(Graphics g) {
        g.setColor(Color.WHITE);
        //test star
        Random rand = new Random();
        int starCnt = 400;
        for (int i = 0; i < starCnt; i++) {
            plot(g, rand.nextInt(600), rand.nextInt(600), 1);
        }
        //end test star
    }

    private void plot(Graphics g, int x, int y, int size) {
        drawLine(g, x, y, x, y);
    }

    private void drawLine(Graphics g, int x1, int y1, int x2, int y2){
        g.drawLine(x1, y1, x2, y2);
    }

    private void fillTriangle(Graphics g, int[] x, int[] y){
        g.fillPolygon(x, y, 3);
    }
}
