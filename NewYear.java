import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class NewYear extends JPanel{
    private static int panelWidth = 600;
    private static int panelHeight = 600;

    public static void main(String[] args) {
        JFrame f = new JFrame();
        NewYear ny = new NewYear();
        f.setTitle("New Year");
        ny.setPreferredSize(new Dimension(panelWidth, panelHeight));
        f.getContentPane().add(ny);
        f.setResizable(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // panelWidth = getWidth();
        // panelHeight = getHeight();
        // paintBackground(g);
        // paintStar(g);
        test(g);
    }

    private void test(Graphics g) {
        drawLine(g, 0, panelHeight/2, panelWidth, panelHeight/2);
    }

    private void paintBackground(Graphics g){
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
            plot(g, rand.nextInt(panelWidth), rand.nextInt(panelHeight));
        }
        //end test star
    }

    private void plot(Graphics g, int x, int y) {
        g.drawLine(x, y, x, y);
    }

    private void drawLine(Graphics g, int x1, int y1, int x2, int y2){
        //g.drawLine(x1, y1, x2, y2);
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = (x1 < x2) ? 1 : -1;
        int sy = (y1 < y2) ? 1 : -1;
        boolean isSwap = false;
        if(dy > dx)
        {
            int temp = dx;
            dx = dy;
            dy = temp;
            isSwap = true;
        }
        int D = 2 * dy - dx;
        int x = x1;
        int y = y1;
        for (int i = 1; i <= dx; i++){
            plot(g, x, y);
            if (D >= 0)
            {
                if (isSwap) 
                    x += sx;
                else 
                    y += sy;
                D -= 2 * dx;
            }
            if (isSwap) 
                y += sy;
            else 
                x += sx;
            D += 2 * dy;
        }
    }

    private void fillTriangle(Graphics g, int[] x, int[] y){
        g.fillPolygon(x, y, 3);
    }
}
