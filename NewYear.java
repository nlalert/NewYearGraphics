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
        paintSun(g);
        paintFuji(g);
        paintLand(g);
        paintLabel(g);
    }

    private void paintLabel(Graphics2D g) {
        paint_A(g);     //明
        paint_Ke(g);    //け
        paint_Ma(g);    //ま
        paint_Shi(g);   //し
        paint_Te1(g);   //て
    }
    

    private void paint_A(Graphics2D g) { //明
        g.setColor(Color.black);
        g.drawRect(525, 25, 50, 50);
        drawCurve(g,532,36,528,42,532,54,536,58);
        drawCurve(g,532,36,536,42,540,54,536,58);
        drawCurve(g,537,40,542,35,549,34,549,39);
        drawCurve(g,537,40,541,38,545,43,549,39);
        drawCurve(g,543,45,539,44,537,44,535,44);
        drawCurve(g,543,45,540,47,538,49,536,51);
        drawCurve(g,548,36,547,43,548,56,546,58);
        drawCurve(g,544,39,543,45,543,55,546,58);
        drawCurve(g,544,55,536,64,534,64,528,60);
        drawLine(g,528,60,544,52);
        floodFill(g, 534, 46, ColorEnum.SKY3.getColor(), Color.black);
        floodFill(g, 539, 46, ColorEnum.SKY3.getColor(), Color.black);
        floodFill(g, 545, 38, ColorEnum.SKY3.getColor(), Color.black);
        floodFill(g, 545, 48, ColorEnum.SKY3.getColor(), Color.black);
        floodFill(g, 536, 59, ColorEnum.SKY3.getColor(), Color.black);
        drawCurve(g,552,29,556,45,554,57,546,66);
    }  
    private void paint_Ke(Graphics2D g) {
        g.setColor(Color.black);
        g.drawRect(525, 100, 50, 50);
    }

    private void paint_Ma(Graphics2D g) {
        g.setColor(Color.black);
        g.drawRect(525, 175, 50, 50);
    }  

    private void paint_Shi(Graphics2D g) {
        g.setColor(Color.black);
        g.drawRect(525, 250, 50, 50);
    }

    private void paint_Te1(Graphics2D g) {
        g.setColor(Color.black);
        g.drawRect(525, 325, 50, 50);
        paintRibbon(g);
        paintCloud(g);
    }

    private void paintCloud(Graphics2D g) {
        drawCloud(g,20,30,50,5);
    }

    private void drawCloud(Graphics2D g, int x, int y, int length,int r) {
        g.setColor(ColorEnum.CLOUD.getColor());
        drawLine(g, x, y-r, x+length, y-r);
        drawLine(g, x, y+r, x+length, y+r);
        drawCircle(g, x, y, r);
        drawCircle(g, x+length, y, r);
        floodFill(g, x, y, ColorEnum.SKY3.getColor(), ColorEnum.CLOUD.getColor());
        floodFill(g, x+length, y, ColorEnum.SKY3.getColor(), ColorEnum.CLOUD.getColor());
        floodFill(g, x+length/2, y, ColorEnum.SKY3.getColor(), ColorEnum.CLOUD.getColor());
    }

    private void paintSun(Graphics2D g) {
        g.setColor(Color.RED);
        drawCircle(g, 300, 362, 125);
        floodFillBorder(g, 300, 362, new Color[]{Color.RED}, Color.RED);
    }

    private void paintBackground(Graphics2D g){
        //gradientFill(g, 0, 0, panelWidth, 450, Color.black, Color.black, 'V');
        //gradientFill(g, 0, 0, panelWidth, 50, ColorEnum.SKY1.getColor(), ColorEnum.SKY2.getColor(), 'V');
        //gradientFill(g, 0, 50, panelWidth, 385, ColorEnum.SKY2.getColor(), ColorEnum.SKY3.getColor(), 'V');
        gradientFill(g, 0, 0, panelWidth, panelHeight, ColorEnum.SKY3.getColor(), ColorEnum.SKY3.getColor(), 'V');
    }

    private void paintFuji(Graphics2D g) {
        drawFuji(g);
        drawSnow(g);
        drawFootHillShadow(g);
        drawSnowShadow(g);
    }

    private void drawFuji(Graphics2D g){
        g.setColor(ColorEnum.FUJI.getColor());
        drawCurve(g, 0, 530, 0, 530,30, 520, 30, 520);
        drawCurve(g, 30, 520, 70, 505, 100, 490, 120, 480);
        drawCurve(g, 120, 480, 130, 475, 160, 455, 180, 445);
        drawCurve(g, 180, 445, 180, 445, 235, 405, 235, 405);
        drawCurve(g, 235, 405, 240, 400, 260, 375, 265, 380);
        drawCurve(g, 265, 380, 285, 380, 300, 375, 310, 372);
        //Half
        drawCurve(g, 310, 372, 315, 375, 323, 380, 323, 380);
        drawCurve(g, 323, 380, 327, 375, 330, 380, 335, 380);
        drawCurve(g, 335, 380, 335, 380, 365, 405, 365, 405);
        drawCurve(g, 365, 405, 365, 405, 420, 445, 420, 445);
        drawCurve(g, 420, 445, 440, 455, 470, 475, 480, 480);
        drawCurve(g, 480, 480, 500, 490, 530, 505, 570, 520);
        drawCurve(g, 570, 520, 570, 520, 600, 530, 600, 530);
        floodFillBorder(g, panelWidth/2, 490, new Color[]{ColorEnum.WATER.getColor(), ColorEnum.SNOW.getColor(),ColorEnum.FUJI.getColor(),ColorEnum.FUJI.getColor().brighter()}, ColorEnum.FUJI.getColor());
    }

    private void drawSnow(Graphics2D g) {
        g.setColor(ColorEnum.SNOW.getColor());
        drawCurve(g, 133, 472, 145, 470, 150, 475, 150, 475);
        drawCurve(g, 150, 475, 155, 475, 170, 465, 170, 465);
        drawCurve(g, 170, 465, 165, 475, 170, 475, 180, 480);
        drawCurve(g, 180, 480, 185, 485, 205, 470, 205, 480);
        drawCurve(g, 205, 480, 205, 480, 224, 485, 224, 485);
        drawCurve(g, 224, 485, 224, 485, 235, 475, 235, 475);
        drawCurve(g, 235, 475, 235, 475, 240, 485, 240, 485);
        drawCurve(g, 240, 485, 240, 485, 285, 470, 300, 475);
        drawCurve(g, 300, 475, 300, 475, 315, 485, 315, 485);
        drawCurve(g, 315, 485, 315, 485, 355, 470, 355, 470);
        drawCurve(g, 355, 470, 355, 470, 400, 475, 400, 475);
        drawCurve(g, 400, 475, 400, 475, 410, 470, 410, 470);
        drawCurve(g, 410, 470, 410, 470, 480, 480, 480, 480);
        floodFill(g, 280, 415, ColorEnum.FUJI.getColor(), ColorEnum.SNOW.getColor());
    }

    private void drawFootHillShadow(Graphics2D g){
        g.setColor(ColorEnum.FUJI.getColor().brighter());
        drawCurve(g, 353, 470, 367, 487, 367, 487, 364, 505);
        drawCurve(g, 364, 505, 361, 514, 372, 525, 372, 525);
        drawCurve(g, 372, 525, 385, 526, 391, 554, 391, 554);
        drawCurve(g, 391, 554, 391, 554, 407, 563, 407, 563);
        drawCurve(g, 407, 563, 415, 581, 415, 581, 443, 600);
        floodFill(g, 120, 535, ColorEnum.FUJI.getColor(), ColorEnum.FUJI.getColor().brighter());

        g.setColor(ColorEnum.FUJI.getColor());
        drawCurve(g, 353, 482, 355, 494, 338, 511, 340, 523);
        drawCurve(g, 353, 482, 363, 496, 335, 523, 340, 523);
        floodFill(g, 350, 499, ColorEnum.FUJI.getColor().brighter(), ColorEnum.FUJI.getColor());
        drawCurve(g, 358, 504, 347, 521, 347, 521, 355, 531);
        drawCurve(g, 358, 504, 358, 504, 355, 531, 355, 531);
        floodFill(g, 353, 521, ColorEnum.FUJI.getColor().brighter(), ColorEnum.FUJI.getColor());
        drawCurve(g, 367, 515, 367, 515, 353, 546, 357, 554);
        drawCurve(g, 357, 554, 357, 554, 369, 570, 369, 570);
        drawCurve(g, 377, 516, 377, 516, 363, 542, 363, 542);
        drawCurve(g, 363, 542, 361, 551, 369, 570, 369, 570);
        floodFill(g, 367, 526, ColorEnum.FUJI.getColor().brighter(), ColorEnum.FUJI.getColor());
        drawCurve(g, 376, 521, 376, 521, 375, 546, 379, 554);
        drawCurve(g, 379, 554, 379, 554, 402, 563, 418, 579);
        floodFill(g, 383, 542, ColorEnum.FUJI.getColor().brighter(), ColorEnum.FUJI.getColor());
        drawCurve(g, 235, 475, 235, 475, 207, 510, 207, 510);
        drawCurve(g, 207, 510, 201, 518, 212, 526, 206, 530);
        drawCurve(g, 206, 530, 205, 537, 165, 566, 165, 566);
        drawCurve(g, 239, 486, 239, 486, 236, 513, 236, 513);
        drawCurve(g, 236, 513, 236, 513, 222, 519, 223, 528);
        drawCurve(g, 223, 528, 223, 528, 223, 546, 223, 546);
        drawCurve(g, 223, 546, 223, 546, 198, 550, 198, 550);
        drawCurve(g, 198, 550, 198, 550, 165, 566, 165, 566);
        floodFill(g, 226, 505, ColorEnum.FUJI.getColor().brighter(), ColorEnum.FUJI.getColor());
        drawCurve(g, 211, 494, 193, 502, 183, 515, 189, 529);
        drawCurve(g, 211, 494, 211, 494, 201, 507, 201, 507);
        drawCurve(g, 201, 507, 194, 511, 199, 522, 189, 529);
        floodFill(g, 194, 510, ColorEnum.FUJI.getColor().brighter(), ColorEnum.FUJI.getColor());
        drawCurve(g, 167, 468, 170, 470, 103, 494, 103, 494);
        drawCurve(g, 167, 474, 160, 480, 130, 490, 103, 494);
        floodFill(g, 155, 478, ColorEnum.FUJI.getColor().brighter(), ColorEnum.FUJI.getColor());

        drawCurve(g, 155, 508, 155, 508, 130, 514, 130, 514);
        drawCurve(g, 130, 514, 111, 525, 111, 525, 65, 541);
        drawCurve(g, 155, 508, 135, 522, 117, 529, 117, 529);
        drawCurve(g, 117, 529, 117, 529, 99, 541, 65, 541);
        floodFill(g, 101, 534, ColorEnum.FUJI.getColor().brighter(), ColorEnum.FUJI.getColor());
        fillTriangle(g, 203, 478, 186, 482, 168, 494);
        
        fillTriangle(g, 287, 490, 295, 538, 282, 570);
        drawCurve(g, 276, 542, 260, 560, 247, 592, 247, 592);
        drawCurve(g, 276, 542, 275, 558, 262, 577, 247, 592);
        floodFill(g, 265, 564, ColorEnum.FUJI.getColor().brighter(), ColorEnum.FUJI.getColor());
        drawCurve(g, 276, 506, 276, 506, 272, 560, 251, 586);
        drawCurve(g, 276, 506, 276, 506, 255, 580, 255, 580);
        floodFill(g, 266, 547, ColorEnum.FUJI.getColor().brighter(), ColorEnum.FUJI.getColor());
        fillTriangle(g, 159, 513, 143, 535, 150, 560);
        

        g.setColor(ColorEnum.FUJI.getColor().brighter());
        drawCurve(g, 375, 524, 375, 524, 379, 536, 387, 538);
        fillTriangle(g, 227, 493, 211, 510, 213, 518);
        fillTriangle(g, 227, 523, 220, 542, 200, 552);
    }

    private void drawSnowShadow(Graphics2D g) {
        g.setColor(ColorEnum.SNOW.getColor().darker());
        fillTriangle(g, 322, 377, 320, 390, 330, 390);
        fillTriangle(g, 325, 400, 320, 390, 330, 390);
        fillTriangle(g, 325, 395, 320, 410, 330, 405);
        fillTriangle(g, 320, 440, 320, 410, 330, 405);
        fillTriangle(g, 320, 440, 355, 472, 325, 415);
        drawCurve(g, 315, 380, 310, 395, 310, 405, 315, 405);
        drawCurve(g, 315, 380, 310, 395, 320, 400, 315, 405);
        floodFill(g, 312, 400, ColorEnum.SNOW.getColor(), ColorEnum.SNOW.getColor().darker());
        floodFill(g, 365, 430, ColorEnum.SNOW.getColor(), ColorEnum.SNOW.getColor().darker());
        drawCurve(g, 310, 420, 310, 450, 335, 460, 340, 470);
        drawCurve(g, 310, 420, 310, 420, 315, 455, 315, 455);
        drawCurve(g, 315, 455, 315, 455, 340, 470, 340, 470);
        floodFill(g, 318, 451, ColorEnum.SNOW.getColor(), ColorEnum.SNOW.getColor().darker());
        drawCurve(g, 266, 402, 260, 405, 265, 420, 260, 420);
        drawCurve(g, 260, 420, 260, 420, 262, 438, 262, 438);
        drawCurve(g, 262, 438, 262, 438, 271, 459, 271, 459);
        drawCurve(g, 266, 402, 260, 405, 255, 415, 255, 415);
        drawCurve(g, 255, 415, 255, 415, 260, 438, 260, 438);
        drawCurve(g, 260, 438, 260, 438, 271, 459, 271, 459);
        floodFill(g, 260, 415, ColorEnum.SNOW.getColor(), ColorEnum.SNOW.getColor().darker());
        drawCurve(g, 245, 405, 245, 405, 225, 425, 225, 430);
        drawCurve(g, 225, 430, 225, 430, 211, 456, 211, 456);
        drawCurve(g, 245, 405, 250, 410, 225, 430, 230, 430);
        drawCurve(g, 230, 430, 230, 430, 215, 456, 211, 456);
        floodFill(g, 236, 417, ColorEnum.SNOW.getColor(), ColorEnum.SNOW.getColor().darker());
        drawCurve(g, 205, 440, 200, 446, 164, 466, 168, 466);
        drawCurve(g, 205, 440, 205, 455, 170, 460, 168, 466);
        floodFill(g, 194, 450, ColorEnum.SNOW.getColor(), ColorEnum.SNOW.getColor().darker());
        drawCurve(g, 265, 382, 285, 382, 300, 377, 310, 374);
        
        drawCurve(g, 236, 446, 236, 446, 223, 466, 223, 466);
        drawCurve(g, 223, 466, 215, 468, 224, 486, 224, 486);
        drawCurve(g, 236, 446, 236, 446, 231, 466, 231, 466);
        drawCurve(g, 231, 466, 231, 466, 235, 474, 235, 474);
        floodFill(g, 228, 472, ColorEnum.SNOW.getColor(), ColorEnum.SNOW.getColor().darker());

        g.setColor(ColorEnum.SNOW.getColor());
        fillTriangle(g, 325, 425, 360, 470, 325, 435);
    }

    private void paintLand(Graphics2D g) {
        g.setColor(ColorEnum.LAND.getColor());
        drawCurve(g, 340, 600, 343, 582, 359, 565, 380, 575);
        drawCurve(g, 380, 575, 406, 563, 419, 578, 446, 580);
        drawCurve(g, 446, 580, 470, 580, 500, 585, 520, 600);
        floodFill(g, 439, 584, ColorEnum.FUJI.getColor(), ColorEnum.LAND.getColor().brighter());
        floodFill(g, 365, 585, ColorEnum.FUJI.getColor().brighter(), ColorEnum.LAND.getColor().brighter());

        drawCurve(g, 0, 561, 15, 555, 40, 560, 45, 565);
        drawCurve(g, 45, 565, 50, 570, 80, 570, 80, 570);
        drawCurve(g, 80, 570, 130, 560, 150, 570, 155, 575);
        drawCurve(g, 155, 575, 165, 585, 220, 600, 220, 600);
        floodFill(g, 50, 585, ColorEnum.FUJI.getColor().brighter(), ColorEnum.LAND.getColor());
    }

    private void paintRibbon(Graphics2D g) {
        g.setColor(Color.RED);
        drawLine(g, 0, 442, panelWidth, 442);
        drawLine(g, 0, 448, panelWidth, 448);
        drawLine(g, 0, 454, panelWidth, 454);
        drawLine(g, 0, 460, panelWidth, 460);

        drawLine(g, 291, 432, 309, 432);
        //
        drawLine(g, 291, 432, 291, 462);
        drawLine(g, 297, 432, 297, 462);
        drawLine(g, 303, 432, 303, 462);
        drawLine(g, 309, 432, 309, 462);
        //
        drawLine(g, 291, 462, 309, 462);

        //Curve
        drawCurve(g, 309, 450, 368, 373, 418, 442, 309, 462);
        drawCurve(g, 309, 444, 363, 373, 413, 442, 309, 458);
        drawCurve(g, 309, 438, 358, 373, 408, 442, 309, 454);
        drawCurve(g, 309, 432, 353, 373, 403, 442, 309, 450);

        drawCurve(g, 291, 450, 232, 373, 182, 442, 291, 462);
        drawCurve(g, 291, 444, 237, 373, 187, 442, 291, 458);
        drawCurve(g, 291, 438, 242, 373, 192, 442, 291, 454);
        drawCurve(g, 291, 432, 247, 373, 197, 442, 291, 450);

        drawCurve(g, 309, 462, 309, 462, 346, 511, 379, 520);
        drawCurve(g, 309, 458, 309, 458, 349, 507, 382, 516);
        drawCurve(g, 309, 454, 309, 454, 352, 503, 385, 512);
        drawCurve(g, 309, 450, 309, 450, 355, 498, 388, 508);

        drawCurve(g, 291, 462, 291, 462, 254, 511, 221, 520);
        drawCurve(g, 291, 458, 291, 458, 251, 507, 218, 516);
        drawCurve(g, 291, 454, 291, 454, 248, 503, 215, 512);
        drawCurve(g, 291, 450, 291, 450, 245, 498, 212, 508);
    }

    //==================================================================================
    //==================================================================================
    //                                    Tools Zone
    //==================================================================================
    //==================================================================================

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

    private void drawCircle(Graphics2D g, int x, int y, int r) {
        drawCurve(g, x+r, y, x+r, (int)(y - (0.552 * r)), (int)(x - (0.552 *-r)), y-r, x, y-r);
        drawCurve(g, x-r, y, x-r, (int)(y - (0.552 * r)), (int)(x - (0.552 * r)), y-r, x, y-r);
        drawCurve(g, x-r, y, x-r, (int)(y - (0.552 *-r)), (int)(x - (0.552 * r)), y+r, x, y+r);
        drawCurve(g, x+r, y, x+r, (int)(y - (0.552 *-r)), (int)(x - (0.552 *-r)), y+r, x, y+r);
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
