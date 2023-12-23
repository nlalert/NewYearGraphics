import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
//DeleteLalter
public class NewYear extends JPanel implements MouseListener{
    private static int panelWidth = 600;
    private static int panelHeight = 600;
    private static BufferedImage canvas;
    //DeleteLalter
    private static int X = 0;
    private static int Y = 0;
    //DeleteLalter
    NewYear(){
        addMouseListener(this);
    }
    public static void main(String[] args) {
        JFrame f = new JFrame();
        NewYear ny = new NewYear();
        f.setTitle("New Year");
        ny.setPreferredSize(new Dimension(panelWidth, panelHeight));
        f.getContentPane().add(ny);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        export(ny);
    }


    //==================================================================================
    //==================================================================================
    //                                    Paint Zone
    //==================================================================================
    //==================================================================================


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintImage();
        g.drawImage(canvas, 0, 0, this);
    }
    
    private void paintImage() {
        canvas = new BufferedImage(panelWidth, panelHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = canvas.createGraphics();

        //paintBackground(g);
        //paintStar(g);
        paintWater(g);
        paintFuji(g);
        paintLand(g);
    }

    private void paintBackground(Graphics g){
        g.setColor(ColorEnum.SKY.getColor());
        fillTriangle(g, new int[]{0, panelWidth, 0}, new int[]{0, 0, panelHeight});
        fillTriangle(g, new int[]{panelWidth, panelWidth, 0}, new int[]{panelHeight, 0, panelHeight});
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
        drawLine(g,0,450,panelWidth,450);
        floodFill(g, panelWidth/2, 401, ColorEnum.SKY.getColor(), ColorEnum.WATER.getColor());
    }

    private void paintFuji(Graphics2D g) {
        drawFujiOutline(g);
        drawSnow(g);
        floodFill(g, panelWidth/2, 340, null, ColorEnum.FUJI.getColor());
        floodFill(g, panelWidth/2, 300, null, ColorEnum.SNOW.getColor());
        drawFujiShadow(g);
    }

    private void drawFujiOutline(Graphics2D g){
        g.setColor(ColorEnum.FUJI.getColor());
        //plot(g, panelWidth/2, 230);
        drawCurve(g, 0, 380, 0, 380,30, 370, 30, 370);
        drawCurve(g, 30, 370, 70, 355, 100, 340, 120, 330);
        drawCurve(g, 120, 330, 130, 325, 160, 305, 180, 295);
        drawCurve(g, 180, 295, 180, 295, 235, 255, 235, 255);
        drawCurve(g, 235, 255, 240, 250, 260, 225, 265, 230);
        drawCurve(g, 265, 230, 285, 230, 300, 225, 310, 222);
        //Half
        drawCurve(g, 310, 222, 315, 225, 323, 230, 323, 230);
        drawCurve(g, 323, 230, 327, 225, 330, 230, 335, 230);
        drawCurve(g, 335, 230, 335, 230, 365, 255, 365, 255);
        drawCurve(g, 365, 255, 365, 255, 420, 295, 420, 295);
        drawCurve(g, 420, 295, 440, 305, 470, 325, 480, 330);
        drawCurve(g, 480, 330, 500, 340, 530, 355, 570, 370);
        drawCurve(g, 570, 370, 570, 370, 600, 380, 600, 380);
    }

    private void drawSnow(Graphics2D g) {
        g.setColor(ColorEnum.SNOW.getColor());
        drawCurve(g, 133, 322, 145, 320, 150, 325, 150, 325);
        drawCurve(g, 150, 325, 155, 325, 170, 315, 170, 315);
        drawCurve(g, 170, 315, 165, 325, 170, 325, 180, 330);
        drawCurve(g, 180, 330, 185, 335, 205, 320, 205, 330);
        drawCurve(g, 205, 330, 205, 330, 224, 335, 224, 335);
        drawCurve(g, 224, 335, 224, 335, 235, 325, 235, 325);
        drawCurve(g, 235, 325, 235, 325, 240, 335, 240, 335);
        drawCurve(g, 240, 335, 240, 335, 285, 320, 300, 325);
        drawCurve(g, 300, 325, 300, 325, 315, 335, 315, 335);
        drawCurve(g, 315, 335, 315, 335, 355, 320, 355, 320);
        drawCurve(g, 355, 320, 355, 320, 400, 325, 400, 325);
        drawCurve(g, 400, 325, 400, 325, 410, 320, 410, 320);
        drawCurve(g, 410, 320, 410, 320, 480, 330, 480, 330);
    }

    private void drawFujiShadow(Graphics2D g) {
        //Lower
        g.setColor(ColorEnum.FUJI.getColor().brighter());
        fillTriangle(g, 170, 315, 91, 360, 143, 344);
        fillTriangle(g, 86, 345, 91, 360, 129, 340);
        fillTriangle(g, 150, 328, 180, 340, 143, 344);
        fillTriangle(g, 165, 340, 196, 337, 230, 352);
        fillTriangle(g, 220, 351, 290, 340, 350, 395);
        fillTriangle(g, 350, 395, 330, 420, 305, 370);
        fillTriangle(g, 330, 415, 400, 450, 325, 450);
        floodFill(g, 200, 405, ColorEnum.FUJI.getColor(), ColorEnum.FUJI.getColor().brighter());
        //Upper Snow

        g.setColor(ColorEnum.SNOW.getColor().darker());
        fillTriangle(g, 323, 230, 320, 240, 330, 240);
        fillTriangle(g, 325, 250, 320, 240, 330, 240);
        fillTriangle(g, 325, 245, 320, 260, 330, 255);
        fillTriangle(g, 320, 290, 320, 260, 330, 255);
        fillTriangle(g, 320, 290, 355, 321, 325, 265);
        floodFill(g, 365, 280, ColorEnum.SNOW.getColor(), ColorEnum.SNOW.getColor().darker());
        
    }

    private void paintLand(Graphics2D g) {
        g.setColor(ColorEnum.LAND.getColor());
        drawCurve(g, 0, 411, 15, 405, 40, 410, 45, 415);
        drawCurve(g, 45, 415, 50, 420, 80, 420, 80, 420);
        drawCurve(g, 80, 420, 130, 410, 150, 420, 155, 425);
        drawCurve(g, 155, 425, 165, 435, 220, 450, 220, 450);
        floodFill(g, 50, 435, ColorEnum.FUJI.getColor().brighter(), ColorEnum.LAND.getColor());
    }

    //==================================================================================
    //==================================================================================
    //                                    Tools Zone
    //==================================================================================
    //==================================================================================

    private void drawCurve(Graphics g, int[] x, int[] y){
        drawCurve(g, x[0], y[0], x[1], y[1], x[2], y[2], x[3], y[3]);
    }
    private void drawCurve(Graphics g, int x1,int y1,int x2,int y2, int x3,int y3, int x4,int y4){
        float sampleAmnt = 100000;
        for (int i = 0; i < sampleAmnt; i++) {
            float t = i/sampleAmnt;
            int x = (int)(Math.pow((1-t), 3)*x1 + 
                    3*t*Math.pow((1-t), 2)*x2 +
                    3*t*t*(1-t)*x3+
                    t*t*t*x4);
            int y = (int)(Math.pow((1-t), 3)*y1 + 
                    3*t*Math.pow((1-t), 2)*y2 +
                    3*t*t*(1-t)*y3+
                    t*t*t*y4);
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

    private void fillTriangle(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3){
        g.fillPolygon(new int[]{x1,x2,x3}, new int[]{y1,y2,y3}, 3);
    }

    private void plot(Graphics g, int x, int y) {
        g.fillRect(x, y, 1, 1);
    }
    //DeleteLalter
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        System.out.println("Clicked on pixel at coordinates: (" + x + ", " + y + ")");
    }
    //DeleteLalter
    @Override
    public void mousePressed(MouseEvent e) {
        X = e.getX();
        Y = e.getY();
        System.out.println("X Y: (" + X + ", " + Y + ")");
        repaint();
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }
    //DeleteLalter
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }
    //DeleteLalter
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }

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
}
