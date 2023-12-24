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
        paintRibbon(g);
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
        drawCurve(g, 0, 380+150, 0, 380+150,30, 370+150, 30, 370+150);
        drawCurve(g, 30, 370+150, 70, 355+150, 100, 340+150, 120, 330+150);
        drawCurve(g, 120, 330+150, 130, 325+150, 160, 305+150, 180, 295+150);
        drawCurve(g, 180, 295+150, 180, 295+150, 235, 255+150, 235, 255+150);
        drawCurve(g, 235, 255+150, 240, 250+150, 260, 225+150, 265, 230+150);
        drawCurve(g, 265, 230+150, 285, 230+150, 300, 225+150, 310, 222+150);
        //Half
        drawCurve(g, 310, 222+150, 315, 225+150, 323, 230+150, 323, 230+150);
        drawCurve(g, 323, 230+150, 327, 225+150, 330, 230+150, 335, 230+150);
        drawCurve(g, 335, 230+150, 335, 230+150, 365, 255+150, 365, 255+150);
        drawCurve(g, 365, 255+150, 365, 255+150, 420, 295+150, 420, 295+150);
        drawCurve(g, 420, 295+150, 440, 305+150, 470, 325+150, 480, 330+150);
        drawCurve(g, 480, 330+150, 500, 340+150, 530, 355+150, 570, 370+150);
        drawCurve(g, 570, 370+150, 570, 370+150, 600, 380+150, 600, 380+150);
        floodFillBorder(g, panelWidth/2, 340+150, new Color[]{ColorEnum.WATER.getColor(), ColorEnum.SNOW.getColor(),ColorEnum.FUJI.getColor(),ColorEnum.FUJI.getColor().brighter()}, ColorEnum.FUJI.getColor());
    }

    private void drawSnow(Graphics2D g) {
        g.setColor(ColorEnum.SNOW.getColor());
        drawCurve(g, 133, 322+150, 145, 320+150, 150, 325+150, 150, 325+150);
        drawCurve(g, 150, 325+150, 155, 325+150, 170, 315+150, 170, 315+150);
        drawCurve(g, 170, 315+150, 165, 325+150, 170, 325+150, 180, 330+150);
        drawCurve(g, 180, 330+150, 185, 335+150, 205, 320+150, 205, 330+150);
        drawCurve(g, 205, 330+150, 205, 330+150, 224, 335+150, 224, 335+150);
        drawCurve(g, 224, 335+150, 224, 335+150, 235, 325+150, 235, 325+150);
        drawCurve(g, 235, 325+150, 235, 325+150, 240, 335+150, 240, 335+150);
        drawCurve(g, 240, 335+150, 240, 335+150, 285, 320+150, 300, 325+150);
        drawCurve(g, 300, 325+150, 300, 325+150, 315, 335+150, 315, 335+150);
        drawCurve(g, 315, 335+150, 315, 335+150, 355, 320+150, 355, 320+150);
        drawCurve(g, 355, 320+150, 355, 320+150, 400, 325+150, 400, 325+150);
        drawCurve(g, 400, 325+150, 400, 325+150, 410, 320+150, 410, 320+150);
        drawCurve(g, 410, 320+150, 410, 320+150, 480, 330+150, 480, 330+150);
        floodFill(g, 280, 265+150, ColorEnum.FUJI.getColor(), ColorEnum.SNOW.getColor());
    }

    private void drawFootHillShadow(Graphics2D g){
        g.setColor(ColorEnum.FUJI.getColor().brighter());
        drawCurve(g, 353, 320+150, 367, 337+150, 367, 337+150, 364, 355+150);
        drawCurve(g, 364, 355+150, 361, 364+150, 372, 375+150, 372, 375+150);
        drawCurve(g, 372, 375+150, 385, 376+150, 391, 404+150, 391, 404+150);
        drawCurve(g, 391, 404+150, 391, 404+150, 407, 413+150, 407, 413+150);
        drawCurve(g, 407, 413+150, 415, 431+150, 415, 431+150, 443, 450+150);
        floodFill(g, 120, 385+150, ColorEnum.FUJI.getColor(), ColorEnum.FUJI.getColor().brighter());

        g.setColor(ColorEnum.FUJI.getColor());
        drawCurve(g, 353, 332+150, 355, 344+150, 338, 361+150, 340, 373+150);
        drawCurve(g, 353, 332+150, 363, 346+150, 335, 373+150, 340, 373+150);
        floodFill(g, 350, 349+150, ColorEnum.FUJI.getColor().brighter(), ColorEnum.FUJI.getColor());
        drawCurve(g, 358, 354+150, 347, 371+150, 347, 371+150, 355, 381+150);
        drawCurve(g, 358, 354+150, 358, 354+150, 355, 381+150, 355, 381+150);
        floodFill(g, 353, 371+150, ColorEnum.FUJI.getColor().brighter(), ColorEnum.FUJI.getColor());
        drawCurve(g, 367, 365+150, 367, 365+150, 353, 396+150, 357, 404+150);
        drawCurve(g, 357, 404+150, 357, 404+150, 369, 420+150, 369, 420+150);
        drawCurve(g, 377, 366+150, 377, 366+150, 363, 392+150, 363, 392+150);
        drawCurve(g, 363, 392+150, 361, 401+150, 369, 420+150, 369, 420+150);
        floodFill(g, 367, 376+150, ColorEnum.FUJI.getColor().brighter(), ColorEnum.FUJI.getColor());
        drawCurve(g, 376, 371+150, 376, 371+150, 375, 396+150, 379, 404+150);
        drawCurve(g, 379, 404+150, 379, 404+150, 402, 413+150, 418, 429+150);
        floodFill(g, 383, 392+150, ColorEnum.FUJI.getColor().brighter(), ColorEnum.FUJI.getColor());
        drawCurve(g, 235, 325+150, 235, 325+150, 207, 360+150, 207, 360+150);
        drawCurve(g, 207, 360+150, 201, 368+150, 212, 376+150, 206, 380+150);
        drawCurve(g, 206, 380+150, 205, 387+150, 165, 416+150, 165, 416+150);
        drawCurve(g, 239, 336+150, 239, 336+150, 236, 363+150, 236, 363+150);
        drawCurve(g, 236, 363+150, 236, 363+150, 222, 369+150, 223, 378+150);
        drawCurve(g, 223, 378+150, 223, 378+150, 223, 396+150, 223, 396+150);
        drawCurve(g, 223, 396+150, 223, 396+150, 198, 400+150, 198, 400+150);
        drawCurve(g, 198, 400+150, 198, 400+150, 165, 416+150, 165, 416+150);
        floodFill(g, 226, 355+150, ColorEnum.FUJI.getColor().brighter(), ColorEnum.FUJI.getColor());
        drawCurve(g, 211, 344+150, 193, 352+150, 183, 365+150, 189, 379+150);
        drawCurve(g, 211, 344+150, 211, 344+150, 201, 357+150, 201, 357+150);
        drawCurve(g, 201, 357+150, 194, 361+150, 199, 372+150, 189, 379+150);
        floodFill(g, 194, 360+150, ColorEnum.FUJI.getColor().brighter(), ColorEnum.FUJI.getColor());
        drawCurve(g, 167, 318+150, 170, 320+150, 103, 347+150, 103, 347+150);
        drawCurve(g, 167, 324+150, 160, 330+150, 130, 340+150, 103, 347+150);
        floodFill(g, 155, 328+150, ColorEnum.FUJI.getColor().brighter(), ColorEnum.FUJI.getColor());

        drawCurve(g, 155, 358+150, 155, 358+150, 130, 364+150, 130, 364+150);
        drawCurve(g, 130, 364+150, 111, 375+150, 111, 375+150, 65, 391+150);
        drawCurve(g, 155, 358+150, 135, 372+150, 117, 379+150, 117, 379+150);
        drawCurve(g, 117, 379+150, 117, 379+150, 99, 391+150, 65, 391+150);
        floodFill(g, 101, 384+150, ColorEnum.FUJI.getColor().brighter(), ColorEnum.FUJI.getColor());
        fillTriangle(g, 203, 328+150, 186, 332+150, 168, 347+150);
        
        fillTriangle(g, 287, 340+150, 295, 388+150, 282, 420+150);
        drawCurve(g, 276, 392+150, 260, 410+150, 247, 442+150, 247, 442+150);
        drawCurve(g, 276, 392+150, 275, 408+150, 262, 427+150, 247, 442+150);
        floodFill(g, 265, 414+150, ColorEnum.FUJI.getColor().brighter(), ColorEnum.FUJI.getColor());
        drawCurve(g, 276, 356+150, 276, 356+150, 272, 410+150, 251, 436+150);
        drawCurve(g, 276, 356+150, 276, 356+150, 255, 430+150, 255, 430+150);
        floodFill(g, 266, 397+150, ColorEnum.FUJI.getColor().brighter(), ColorEnum.FUJI.getColor());
        fillTriangle(g, 159, 363+150, 143, 385+150, 150, 410+150);
        

        g.setColor(ColorEnum.FUJI.getColor().brighter());
        drawCurve(g, 375, 374+150, 375, 374+150, 379, 386+150, 387, 388+150);
        fillTriangle(g, 227, 343+150, 211, 360+150, 213, 368+150);
        fillTriangle(g, 227, 373+150, 220, 392+150, 200, 402+150);
    }

    private void drawSnowShadow(Graphics2D g) {
        g.setColor(ColorEnum.SNOW.getColor().darker());
        fillTriangle(g, 322, 227+150, 320, 240+150, 330, 240+150);
        fillTriangle(g, 325, 250+150, 320, 240+150, 330, 240+150);
        fillTriangle(g, 325, 245+150, 320, 260+150, 330, 255+150);
        fillTriangle(g, 320, 290+150, 320, 260+150, 330, 255+150);
        fillTriangle(g, 320, 290+150, 355, 322+150, 325, 265+150);
        drawCurve(g, 315, 230+150, 310, 245+150, 310, 255+150, 315, 255+150);
        drawCurve(g, 315, 230+150, 310, 245+150, 320, 250+150, 315, 255+150);
        floodFill(g, 312, 250+150, ColorEnum.SNOW.getColor(), ColorEnum.SNOW.getColor().darker());
        floodFill(g, 365, 280+150, ColorEnum.SNOW.getColor(), ColorEnum.SNOW.getColor().darker());
        drawCurve(g, 310, 270+150, 310, 300+150, 335, 310+150, 340, 320+150);
        drawCurve(g, 310, 270+150, 310, 270+150, 315, 305+150, 315, 305+150);
        drawCurve(g, 315, 305+150, 315, 305+150, 340, 320+150, 340, 320+150);
        floodFill(g, 318, 301+150, ColorEnum.SNOW.getColor(), ColorEnum.SNOW.getColor().darker());
        drawCurve(g, 266, 252+150, 260, 255+150, 265, 270+150, 260, 270+150);
        drawCurve(g, 260, 270+150, 260, 270+150, 262, 288+150, 262, 288+150);
        drawCurve(g, 262, 288+150, 262, 288+150, 271, 309+150, 271, 309+150);
        drawCurve(g, 266, 252+150, 260, 255+150, 255, 265+150, 255, 265+150);
        drawCurve(g, 255, 265+150, 255, 265+150, 260, 288+150, 260, 288+150);
        drawCurve(g, 260, 288+150, 260, 288+150, 271, 309+150, 271, 309+150);
        floodFill(g, 260, 265+150, ColorEnum.SNOW.getColor(), ColorEnum.SNOW.getColor().darker());
        drawCurve(g, 245, 255+150, 245, 255+150, 225, 275+150, 225, 280+150);
        drawCurve(g, 225, 280+150, 225, 280+150, 211, 306+150, 211, 306+150);
        drawCurve(g, 245, 255+150, 250, 260+150, 225, 280+150, 230, 280+150);
        drawCurve(g, 230, 280+150, 230, 280+150, 215, 306+150, 211, 306+150);
        floodFill(g, 236, 267+150, ColorEnum.SNOW.getColor(), ColorEnum.SNOW.getColor().darker());
        drawCurve(g, 205, 290+150, 200, 296+150, 164, 316+150, 168, 316+150);
        drawCurve(g, 205, 290+150, 205, 305+150, 170, 310+150, 168, 316+150);
        floodFill(g, 194, 300+150, ColorEnum.SNOW.getColor(), ColorEnum.SNOW.getColor().darker());
        drawCurve(g, 265, 232+150, 285, 232+150, 300, 227+150, 310, 224+150);
        
        drawCurve(g, 236, 296+150, 236, 296+150, 223, 316+150, 223, 316+150);
        drawCurve(g, 223, 316+150, 215, 318+150, 224, 336+150, 224, 336+150);
        drawCurve(g, 236, 296+150, 236, 296+150, 231, 316+150, 231, 316+150);
        drawCurve(g, 231, 316+150, 231, 316+150, 235, 324+150, 235, 324+150);
        floodFill(g, 228, 322+150, ColorEnum.SNOW.getColor(), ColorEnum.SNOW.getColor().darker());

        g.setColor(ColorEnum.SNOW.getColor());
        fillTriangle(g, 325, 275+150, 360, 320+150, 325, 285+150);
    }

    private void paintLand(Graphics2D g) {
        g.setColor(ColorEnum.LAND.getColor());
        drawCurve(g, 340, 450+150, 343, 432+150, 359, 415+150, 380, 425+150);
        drawCurve(g, 380, 425+150, 406, 413+150, 419, 428+150, 446, 430+150);
        drawCurve(g, 446, 430+150, 470, 430+150, 500, 435+150, 520, 450+150);
        floodFill(g, 439, 434+150, ColorEnum.FUJI.getColor(), ColorEnum.LAND.getColor().brighter());
        floodFill(g, 365, 435+150, ColorEnum.FUJI.getColor().brighter(), ColorEnum.LAND.getColor().brighter());

        drawCurve(g, 0, 411+150, 15, 405+150, 40, 410+150, 45, 415+150);
        drawCurve(g, 45, 415+150, 50, 420+150, 80, 420+150, 80, 420+150);
        drawCurve(g, 80, 420+150, 130, 410+150, 150, 420+150, 155, 425+150);
        drawCurve(g, 155, 425+150, 165, 435+150, 220, 450+150, 220, 450+150);
        floodFill(g, 50, 435+150, ColorEnum.FUJI.getColor().brighter(), ColorEnum.LAND.getColor());
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
