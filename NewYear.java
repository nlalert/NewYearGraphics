import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class NewYear extends JPanel{

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
        plot(g, 150, 50, 1);
        plot(g, 540, 150, 1);
        plot(g, 250, 350, 1);
        plot(g, 450, 510, 1);
    }

    private void plot(Graphics g, int x, int y, int size) {
        g.drawLine(x, y, x, y);
    }

    private void fillTriangle(Graphics g, int[] x, int[] y){
        g.fillPolygon(x, y, 3);
    }
}
