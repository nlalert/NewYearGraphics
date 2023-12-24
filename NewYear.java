import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
//DeleteLalter
public class NewYear extends JPanel implements MouseListener{
    private static int panelWidth = 600;
    private static int panelHeight = 600;
    private static BufferedImage canvas;

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
        paintBackground(g);
        //paintStar(g);
        paintSun(g);
        paintWater(g);
        paintFuji(g);
        paintLand(g);
        //paintReflection(g);
        //paintRibbon(g);
    }

    private void paintSun(Graphics2D g) {
        g.setColor(Color.RED);
        drawCircle(g, 300, 212, 125);
        floodFillBorder(g, 300, 212, new Color[]{Color.RED}, Color.RED);
    }

    private void paintBackground(Graphics2D g){
        //gradientFill(g, 0, 0, panelWidth, 450, Color.black, Color.black, 'V');
        //gradientFill(g, 0, 0, panelWidth, 50, ColorEnum.SKY1.getColor(), ColorEnum.SKY2.getColor(), 'V');
        //gradientFill(g, 0, 50, panelWidth, 385, ColorEnum.SKY2.getColor(), ColorEnum.SKY3.getColor(), 'V');
        gradientFill(g, 0, 0, panelWidth, 450, ColorEnum.SKY3.getColor(), ColorEnum.SKY3.getColor(), 'V');
    }
    
    private void paintStar(Graphics2D g) {
        g.setColor(ColorEnum.STAR.getColor());
        Random rand = new Random();
        int starCnt = 300;
        for (int i = 0; i < starCnt; i++) {
            int color = rand.nextInt(100,255);
            g.setColor(new Color(color,color,color));
            plot(g, rand.nextInt(panelWidth), rand.nextInt(300));
        }
    }

    private void paintWater(Graphics2D g) {
        g.setColor(ColorEnum.WATER.getColor());
        drawLine(g,0,450,panelWidth,450);
        floodFill(g, panelWidth/2, 451, null, ColorEnum.WATER.getColor());
    }

    private void paintFuji(Graphics2D g) {
        drawFuji(g);
        drawSnow(g);
        drawFootHillShadow(g);
        drawSnowShadow(g);
    }

    private void drawFuji(Graphics2D g){
        g.setColor(ColorEnum.FUJI.getColor());
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
        floodFillBorder(g, panelWidth/2, 340, new Color[]{ColorEnum.WATER.getColor(), ColorEnum.SNOW.getColor(),ColorEnum.FUJI.getColor(),ColorEnum.FUJI.getColor().brighter()}, ColorEnum.FUJI.getColor());
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
        floodFill(g, 280, 265, ColorEnum.FUJI.getColor(), ColorEnum.SNOW.getColor());
    }

    private void drawFootHillShadow(Graphics2D g){
        g.setColor(ColorEnum.FUJI.getColor().brighter());
        drawCurve(g, 353, 320, 367, 337, 367, 337, 364, 355);
        drawCurve(g, 364, 355, 361, 364, 372, 375, 372, 375);
        drawCurve(g, 372, 375, 385, 376, 391, 404, 391, 404);
        drawCurve(g, 391, 404, 391, 404, 407, 413, 407, 413);
        drawCurve(g, 407, 413, 415, 431, 415, 431, 443, 450);
        floodFill(g, 120, 385, ColorEnum.FUJI.getColor(), ColorEnum.FUJI.getColor().brighter());

        g.setColor(ColorEnum.FUJI.getColor());
        drawCurve(g, 353, 332, 355, 344, 338, 361, 340, 373);
        drawCurve(g, 353, 332, 363, 346, 335, 373, 340, 373);
        floodFill(g, 350, 349, ColorEnum.FUJI.getColor().brighter(), ColorEnum.FUJI.getColor());
        drawCurve(g, 358, 354, 347, 371, 347, 371, 355, 381);
        drawCurve(g, 358, 354, 358, 354, 355, 381, 355, 381);
        floodFill(g, 353, 371, ColorEnum.FUJI.getColor().brighter(), ColorEnum.FUJI.getColor());
        drawCurve(g, 367, 365, 367, 365, 353, 396, 357, 404);
        drawCurve(g, 357, 404, 357, 404, 369, 420, 369, 420);
        drawCurve(g, 377, 366, 377, 366, 363, 392, 363, 392);
        drawCurve(g, 363, 392, 361, 401, 369, 420, 369, 420);
        floodFill(g, 367, 376, ColorEnum.FUJI.getColor().brighter(), ColorEnum.FUJI.getColor());
        drawCurve(g, 376, 371, 376, 371, 375, 396, 379, 404);
        drawCurve(g, 379, 404, 379, 404, 402, 413, 418, 429);
        floodFill(g, 383, 392, ColorEnum.FUJI.getColor().brighter(), ColorEnum.FUJI.getColor());
        drawCurve(g, 235, 325, 235, 325, 207, 360, 207, 360);
        drawCurve(g, 207, 360, 201, 368, 212, 376, 206, 380);
        drawCurve(g, 206, 380, 205, 387, 165, 416, 165, 416);
        drawCurve(g, 239, 336, 239, 336, 236, 363, 236, 363);
        drawCurve(g, 236, 363, 236, 363, 222, 369, 223, 378);
        drawCurve(g, 223, 378, 223, 378, 223, 396, 223, 396);
        drawCurve(g, 223, 396, 223, 396, 198, 400, 198, 400);
        drawCurve(g, 198, 400, 198, 400, 165, 416, 165, 416);
        floodFill(g, 226, 355, ColorEnum.FUJI.getColor().brighter(), ColorEnum.FUJI.getColor());
        drawCurve(g, 211, 344, 193, 352, 183, 365, 189, 379);
        drawCurve(g, 211, 344, 211, 344, 201, 357, 201, 357);
        drawCurve(g, 201, 357, 194, 361, 199, 372, 189, 379);
        floodFill(g, 194, 360, ColorEnum.FUJI.getColor().brighter(), ColorEnum.FUJI.getColor());
        drawCurve(g, 167, 318, 170, 320, 103, 347, 103, 347);
        drawCurve(g, 167, 324, 160, 330, 130, 340, 103, 347);
        floodFill(g, 155, 328, ColorEnum.FUJI.getColor().brighter(), ColorEnum.FUJI.getColor());

        drawCurve(g, 155, 358, 155, 358, 130, 364, 130, 364);
        drawCurve(g, 130, 364, 111, 375, 111, 375, 65, 391);
        drawCurve(g, 155, 358, 135, 372, 117, 379, 117, 379);
        drawCurve(g, 117, 379, 117, 379, 99, 391, 65, 391);
        floodFill(g, 101, 384, ColorEnum.FUJI.getColor().brighter(), ColorEnum.FUJI.getColor());
        fillTriangle(g, 203, 328, 186, 332, 168, 347);
        
        fillTriangle(g, 287, 340, 295, 388, 282, 420);
        drawCurve(g, 276, 392, 260, 410, 247, 442, 247, 442);
        drawCurve(g, 276, 392, 275, 408, 262, 427, 247, 442);
        floodFill(g, 265, 414, ColorEnum.FUJI.getColor().brighter(), ColorEnum.FUJI.getColor());
        drawCurve(g, 276, 356, 276, 356, 272, 410, 251, 436);
        drawCurve(g, 276, 356, 276, 356, 255, 430, 255, 430);
        floodFill(g, 266, 397, ColorEnum.FUJI.getColor().brighter(), ColorEnum.FUJI.getColor());
        fillTriangle(g, 159, 363, 143, 385, 150, 410);
        

        g.setColor(ColorEnum.FUJI.getColor().brighter());
        drawCurve(g, 375, 374, 375, 374, 379, 386, 387, 388);
        fillTriangle(g, 227, 343, 211, 360, 213, 368);
        fillTriangle(g, 227, 373, 220, 392, 200, 402);
    }

    private void drawSnowShadow(Graphics2D g) {
        g.setColor(ColorEnum.SNOW.getColor().darker());
        fillTriangle(g, 322, 227, 320, 240, 330, 240);
        fillTriangle(g, 325, 250, 320, 240, 330, 240);
        fillTriangle(g, 325, 245, 320, 260, 330, 255);
        fillTriangle(g, 320, 290, 320, 260, 330, 255);
        fillTriangle(g, 320, 290, 355, 322, 325, 265);
        drawCurve(g, 315, 230, 310, 245, 310, 255, 315, 255);
        drawCurve(g, 315, 230, 310, 245, 320, 250, 315, 255);
        floodFill(g, 312, 250, ColorEnum.SNOW.getColor(), ColorEnum.SNOW.getColor().darker());
        floodFill(g, 365, 280, ColorEnum.SNOW.getColor(), ColorEnum.SNOW.getColor().darker());
        drawCurve(g, 310, 270, 310, 300, 335, 310, 340, 320);
        drawCurve(g, 310, 270, 310, 270, 315, 305, 315, 305);
        drawCurve(g, 315, 305, 315, 305, 340, 320, 340, 320);
        floodFill(g, 318, 301, ColorEnum.SNOW.getColor(), ColorEnum.SNOW.getColor().darker());
        drawCurve(g, 266, 252, 260, 255, 265, 270, 260, 270);
        drawCurve(g, 260, 270, 260, 270, 262, 288, 262, 288);
        drawCurve(g, 262, 288, 262, 288, 271, 309, 271, 309);
        drawCurve(g, 266, 252, 260, 255, 255, 265, 255, 265);
        drawCurve(g, 255, 265, 255, 265, 260, 288, 260, 288);
        drawCurve(g, 260, 288, 260, 288, 271, 309, 271, 309);
        floodFill(g, 260, 265, ColorEnum.SNOW.getColor(), ColorEnum.SNOW.getColor().darker());
        drawCurve(g, 245, 255, 245, 255, 225, 275, 225, 280);
        drawCurve(g, 225, 280, 225, 280, 211, 306, 211, 306);
        drawCurve(g, 245, 255, 250, 260, 225, 280, 230, 280);
        drawCurve(g, 230, 280, 230, 280, 215, 306, 211, 306);
        floodFill(g, 236, 267, ColorEnum.SNOW.getColor(), ColorEnum.SNOW.getColor().darker());
        drawCurve(g, 205, 290, 200, 296, 164, 316, 168, 316);
        drawCurve(g, 205, 290, 205, 305, 170, 310, 168, 316);
        floodFill(g, 194, 300, ColorEnum.SNOW.getColor(), ColorEnum.SNOW.getColor().darker());
        drawCurve(g, 265, 232, 285, 232, 300, 227, 310, 224);
        
        drawCurve(g, 236, 296, 236, 296, 223, 316, 223, 316);
        drawCurve(g, 223, 316, 215, 318, 224, 336, 224, 336);
        drawCurve(g, 236, 296, 236, 296, 231, 316, 231, 316);
        drawCurve(g, 231, 316, 231, 316, 235, 324, 235, 324);
        floodFill(g, 228, 322, ColorEnum.SNOW.getColor(), ColorEnum.SNOW.getColor().darker());

        g.setColor(ColorEnum.SNOW.getColor());
        fillTriangle(g, 325, 275, 360, 320, 325, 285);
    }

    private void paintLand(Graphics2D g) {
        g.setColor(ColorEnum.LAND.getColor());
        drawCurve(g, 340, 450, 343, 432, 359, 415, 380, 425);
        drawCurve(g, 380, 425, 406, 413, 419, 428, 446, 430);
        drawCurve(g, 446, 430, 470, 430, 500, 435, 520, 450);
        floodFill(g, 439, 434, ColorEnum.FUJI.getColor(), ColorEnum.LAND.getColor().brighter());
        floodFill(g, 365, 435, ColorEnum.FUJI.getColor().brighter(), ColorEnum.LAND.getColor().brighter());

        drawCurve(g, 0, 411, 15, 405, 40, 410, 45, 415);
        drawCurve(g, 45, 415, 50, 420, 80, 420, 80, 420);
        drawCurve(g, 80, 420, 130, 410, 150, 420, 155, 425);
        drawCurve(g, 155, 425, 165, 435, 220, 450, 220, 450);
        floodFill(g, 50, 435, ColorEnum.FUJI.getColor().brighter(), ColorEnum.LAND.getColor());
    }

    private void paintReflection(Graphics2D g) {
        reflect(g, 0, 0, 600, 450, 300);
    }

    private void paintRibbon(Graphics2D g) {
        g.setColor(Color.RED);
        drawLine(g, 0, 500-58, panelWidth, 500-58);
        drawLine(g, 0, 506-58, panelWidth, 506-58);
        drawLine(g, 0, 512-58, panelWidth, 512-58);
        drawLine(g, 0, 518-58, panelWidth, 518-58);

        drawLine(g, 291, 490-58, 309, 490-58);
        //
        drawLine(g, 291, 490-58, 291, 520-58);
        drawLine(g, 297, 490-58, 297, 520-58);
        drawLine(g, 303, 490-58, 303, 520-58);
        drawLine(g, 309, 490-58, 309, 520-58);
        //
        drawLine(g, 291, 520-58, 309, 520-58);

        //Curve
        drawCurve(g, 309, 508-58, 368, 431-58, 418, 500-58, 309, 520-58);
        drawCurve(g, 309, 502-58, 363, 431-58, 413, 500-58, 309, 516-58);
        drawCurve(g, 309, 496-58, 358, 431-58, 408, 500-58, 309, 512-58);
        drawCurve(g, 309, 490-58, 353, 431-58, 403, 500-58, 309, 508-58);

        drawCurve(g, 291, 508-58, 232, 431-58, 182, 500-58, 291, 520-58);
        drawCurve(g, 291, 502-58, 237, 431-58, 187, 500-58, 291, 516-58);
        drawCurve(g, 291, 496-58, 242, 431-58, 192, 500-58, 291, 512-58);
        drawCurve(g, 291, 490-58, 247, 431-58, 197, 500-58, 291, 508-58);

        drawCurve(g, 309, 520-58, 309, 520-58, 346, 569-58, 379, 578-58);
        drawCurve(g, 309, 516-58, 309, 516-58, 349, 565-58, 382, 574-58);
        drawCurve(g, 309, 512-58, 309, 512-58, 352, 561-58, 385, 570-58);
        drawCurve(g, 309, 508-58, 309, 508-58, 355, 556-58, 388, 566-58);

        drawCurve(g, 291, 520-58, 291, 520-58, 254, 569-58, 221, 578-58);
        drawCurve(g, 291, 516-58, 291, 516-58, 251, 565-58, 218, 574-58);
        drawCurve(g, 291, 512-58, 291, 512-58, 248, 561-58, 215, 570-58);
        drawCurve(g, 291, 508-58, 291, 508-58, 245, 556-58, 212, 566-58);
    }

    //==================================================================================
    //==================================================================================
    //                                    Tools Zone
    //==================================================================================
    //==================================================================================


    private void reflect(Graphics g,int x1, int y1, int x2, int y2,int y_axis){
        for (int y = y1; y < y2; y++) {
            for (int x = x1; x < x2; x++) {
                //if(canvas.getRGB(x, y) != ColorEnum.SKY3.getColor().getRGB()){
                    Color color = new Color(canvas.getRGB(x, y));
                    int R = color.getRed();
                    int G = color.getGreen();
                    int B = color.getBlue();
                    g.setColor(new Color(R,G,B,100));
                    plot(g, x, (600-y) + (y_axis));
                //}
            }
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

    private void floodFillBorder(Graphics g,int x, int y, Color[] borderColor, Color fillColor) {
        int[] borderRGB;
        if(borderColor != null){
            borderRGB = new int[borderColor.length];
            for (int i = 0; i < borderRGB.length; i++) {
                borderRGB[i] = borderColor[i].getRGB();
            }
        }
        else{
            borderRGB = new int[] {-1};
        }

        if (!isIn(canvas.getRGB(x, y), borderRGB)) {
            Queue<Point> queue = new LinkedList<>();
            queue.add(new Point(x, y));

            while (!queue.isEmpty()) {
                Point point = queue.poll();
                x = (int) point.getX();
                y = (int) point.getY();

                if (!isIn(canvas.getRGB(x, y), borderRGB)) {
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

    private boolean isIn(int color, int[] borderColor){
        for (int i : borderColor) {
            if(color == i || color == 0)
                return true;
        }
        return false;
    }

    private void gradientFill(Graphics g, int x1, int y1, int x2, int y2, Color startColor, Color endColor, char direction) {      
        int startR = startColor.getRed();   
        int startG = startColor.getGreen(); 
        int startB = startColor.getBlue();
    
        int endR = endColor.getRed();     
        int endG  = endColor.getGreen();   
        int endB  = endColor.getBlue();
    
        int range;
        if (direction == 'H') {
            range = x2 - x1;
            for (int i = x1; i <= x2; i++) {
                int R = interpolateColor(startR, endR, range, i - x1);
                int G = interpolateColor(startG, endG, range, i - x1);
                int B = interpolateColor(startB, endB, range, i - x1);
                g.setColor(new Color(R, G, B));
                drawLine(g, i, y1, i, y2);
            }
        } else if (direction == 'V') {
            range = y2 - y1;
            for (int i = y1; i <= y2; i++) {
                int R = interpolateColor(startR, endR, range, i - y1);
                int G = interpolateColor(startG, endG, range, i - y1);
                int B = interpolateColor(startB, endB, range, i - y1);
                g.setColor(new Color(R, G, B));
                drawLine(g, x1, i, x2, i);
            }
        }
    }
    
    private int interpolateColor(int start, int end, int range, int position) {
        return clampRGB(start + position * (end - start) / range);
    }
    
    private int clampRGB(int value) {
        //between 0 - 255 only
        return Math.max(0, Math.min(value, 255));
    }

    private void drawCircle(Graphics g,int x, int y, int r){
        drawCurve(g, x-r, y, x-r, (int)Math.round(y-r*(4/3.0)), x+r, (int)Math.round(y-r*(4/3.0)), x+r, y);
        drawCurve(g, x-r, y, x-r, (int)Math.round(y+r*(4/3.0)), x+r, (int)Math.round(y+r*(4/3.0)), x+r, y);
    }

    private void fillTriangle(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3){
        g.fillPolygon(new int[]{x1,x2,x3}, new int[]{y1,y2,y3}, 3);
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

    private void plot(Graphics g, int x, int y) {
        g.drawLine(x, y, x, y);
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
}
