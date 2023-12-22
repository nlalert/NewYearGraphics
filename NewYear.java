import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class NewYear extends JPanel{
    private static int panelWidth = 600;
    private static int panelHeight = 600;
    private static BufferedImage canvas;

    public static void main(String[] args) {
        JFrame f = new JFrame();
        NewYear ny = new NewYear();
        f.setTitle("New Year");
        ny.setPreferredSize(new Dimension(panelWidth, panelHeight));
        f.getContentPane().add(ny);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);

        export(ny);
    }


    //==================================================================================
    //==================================================================================
    //                                    Paint Zone
    //==================================================================================
    //==================================================================================


    private static void export(JPanel panel) {
        try {
            // Create a BufferedImage with the same size as the JPanel
            BufferedImage image = new BufferedImage(
                    panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics g = image.getGraphics();

            // Paint the contents of the JPanel onto the BufferedImage
            panel.paint(g);

            // Choose the file path and format (e.g., PNG)
            File file = new File("Drawing.png");

            // Write the image to the file
            ImageIO.write(image, "png", file);

            System.out.println("Exported to: " + file.getAbsolutePath());

            // Dispose of the Graphics object to release resources
            g.dispose();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintImage();
        g.drawImage(canvas, 0, 0, this);
    }
    
    private void paintImage() {
        canvas = new BufferedImage(panelWidth, panelHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = canvas.createGraphics();

        paintBackground(g);
        paintStar(g);
        paintWater(g);
        paintFuji(g);
    }

    private void paintBackground(Graphics g){
        g.setColor(ColorEnum.SKY.getColor());
        fillTriangle(g, new int[]{0, panelWidth, 0}, new int[]{0, 0, panelHeight});
        fillTriangle(g, new int[]{panelWidth, panelWidth, 0}, new int[]{panelHeight, 0, panelHeight});
        for (int height = 0; height <= 400; height++) {
            g.setColor(new Color(height*255/panelHeight, height*120/panelHeight, 80-height*80/panelHeight));
            drawLine(g,0, height, panelWidth, height);
        }
    }
    
    private void paintStar(Graphics g) {
        g.setColor(ColorEnum.STAR.getColor());
        //test star
        Random rand = new Random();
        int starCnt = 400;
        for (int i = 0; i < starCnt; i++) {
            plot(g, rand.nextInt(panelWidth), rand.nextInt(200));
        }
        //end test star
    }

    private void paintWater(Graphics2D g) {
        g.setColor(ColorEnum.WATER.getColor());
        drawLine(g,0,400,panelWidth,400);
        floodFill(g, panelWidth/2, 401, ColorEnum.SKY.getColor(), ColorEnum.WATER.getColor());
    }

    private void paintFuji(Graphics2D g) {
        g.setColor(ColorEnum.FUJI.getColor());
        drawCurve(g, new int[]{0,100,200,panelWidth/2}, new int[]{400,350,300,200});
        drawCurve(g, new int[]{panelWidth/2,400,500,panelWidth}, new int[]{200,300,350,400});
        floodFill(g, panelWidth/2, panelHeight/2+1, ColorEnum.SKY.getColor(), ColorEnum.FUJI.getColor());
    }

    //==================================================================================
    //==================================================================================
    //                                    Tools Zone
    //==================================================================================
    //==================================================================================

    private void drawCurve(Graphics g, int[] X, int[] Y){
        float sampleAmnt = 100000;
        for (int i = 0; i < sampleAmnt; i++) {
            float t = i/sampleAmnt;
            int x = (int)(Math.pow((1-t), 3)*X[0] + 
                    3*t*Math.pow((1-t), 2)*X[1] +
                    3*t*t*(1-t)*X[2]+
                    t*t*t*X[3]);
            int y = (int)(Math.pow((1-t), 3)*Y[0] + 
                    3*t*Math.pow((1-t), 2)*Y[1] +
                    3*t*t*(1-t)*Y[2]+
                    t*t*t*Y[3]);
            plot(g, x, y);
            
        }
    }

    private void drawLine(Graphics g, int x1, int y1, int x2, int y2){
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

    private void floodFill(Graphics g,int x, int y, Color targetColor, Color fillColor) {
        int targetRGB = 0;
        if(targetColor != null){
            targetRGB = targetColor.getRGB();
        }
        if (canvas.getRGB(x, y) == targetRGB) {
            Queue<Point> queue = new LinkedList<>();
            queue.add(new Point(x, y));

            while (!queue.isEmpty()) {
                Point point = queue.poll();
                x = (int) point.getX();
                y = (int) point.getY();

                if (canvas.getRGB(x, y) == targetRGB) {
                    g.setColor(fillColor);
                    plot(g, x, y);

                    // Enqueue adjacent pixels
                    if (x - 1 >= 0) queue.add(new Point(x - 1, y));
                    if (x + 1 < panelWidth) queue.add(new Point(x + 1, y));
                    if (y - 1 >= 0) queue.add(new Point(x, y - 1));
                    if (y + 1 < panelHeight) queue.add(new Point(x, y + 1));
                }
            }
        }
    }

    private void fillTriangle(Graphics g, int[] x, int[] y){
        g.fillPolygon(x, y, 3);
    }

    private void plot(Graphics g, int x, int y) {
        g.fillRect(x, y, 1, 1);
    }
}
