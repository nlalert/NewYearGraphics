import java.awt.*;
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
        paintFlowerTree(g);
        paintFallingPetals(g);
        paintText(g);
    }


    //==================================================================================
    //                                    Main Paint
    //==================================================================================


    private void paintBackground(Graphics2D g){
        gradientFill(g, 0, 0, panelWidth, panelHeight, Palette.SKY1.getColor(), Palette.SKY2.getColor(), 'V');
    }

    private void paintSun(Graphics2D g) {
        g.setColor(Palette.SUN.getColor());
        drawCircle(g, 300, 362, 125);
        floodFillBorder(g, 300, 362, new Color[]{Palette.SUN.getColor()}, Palette.SUN.getColor());
    }

    private void paintFuji(Graphics2D g) {
        drawFuji(g, 150, Palette.FUJI.getColor());
        drawSnow(g);
        drawFootHillShadow(g);
        drawSnowShadow(g);
    }

    private void paintLand(Graphics2D g) {
        g.setColor(Palette.LAND.getColor());
        drawCurve(g, 340, 600, 343, 582, 359, 565, 380, 575);
        drawCurve(g, 380, 575, 406, 563, 419, 578, 446, 580);
        drawCurve(g, 446, 580, 470, 580, 500, 585, 520, 600);
        floodFill(g, 439, 584, Palette.FUJI.getColor(), Palette.LAND.getColor().brighter());
        floodFill(g, 365, 585, Palette.FUJI.getColor().brighter(), Palette.LAND.getColor().brighter());

        drawCurve(g, 0, 561, 15, 555, 40, 560, 45, 565);
        drawCurve(g, 45, 565, 50, 570, 80, 570, 80, 570);
        drawCurve(g, 80, 570, 130, 560, 150, 570, 155, 575);
        drawCurve(g, 155, 575, 165, 585, 220, 600, 220, 600);
        floodFill(g, 50, 585, Palette.FUJI.getColor().brighter(), Palette.LAND.getColor());
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

    private void paintFlowerTree(Graphics2D g) {
        drawBranches(g);
        drawFlowers(g);
    }

    private void paintFallingPetals(Graphics2D g) {
        drawFallingPetals(g, 164, 251, 1, -1);
        drawFallingPetals(g, 87, 334, -1, 1);
        drawFallingPetals(g, 169, 308, -1, -1);
        drawFallingPetals(g, 235, 330, 1, 1);
        drawFallingPetals(g, 291, 266, 1, -1);
        drawFallingPetals(g, 355, 309, -1, 1);
        drawFallingPetals(g, 295, 199, 1, -1);
        drawFallingPetals(g, 495, 396, -1, 1);
        drawFallingPetals(g, 450, 513, -1, 1);
        drawFallingPetals(g, 303, 350, 1, 1);
        drawFallingPetals(g, 207, 424, -1, -1);
    }

    private void paintText(Graphics2D g) {
        paint_A(g);     //明
        paint_Ke(g);    //け
        paint_Ma1(g);   //ま
        paint_Shi(g);   //し
        paint_Te(g);    //て
        paint_O(g);     //お
        paint_Me(g);    //め
        paint_De(g);    //で
        paint_To(g);    //と
        paint_U(g);     //う
        paint_Go(g);    //ご
        paint_Za(g);    //ざ
        paint_I(g);     //い
        paint_Ma2(g);   //ま
        paint_Su(g);    //す
    }

    
    //==================================================================================
    //                                    Detail Paint
    //==================================================================================


    private void drawFuji(Graphics2D g, int y,Color color){
        g.setColor(Palette.FUJI.getColor());
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
        floodFillBorder(g, panelWidth/2, 490, new Color[]{Palette.SNOW.getColor(),Palette.FUJI.getColor(),Palette.FUJI.getColor().brighter()}, Palette.FUJI.getColor());
    }

    private void drawSnow(Graphics2D g) {
        g.setColor(Palette.SNOW.getColor());
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
        floodFill(g, 280, 415, Palette.FUJI.getColor(), Palette.SNOW.getColor());
    }

    private void drawFootHillShadow(Graphics2D g){
        g.setColor(Palette.FUJI.getColor().brighter());
        drawCurve(g, 353, 470, 367, 487, 367, 487, 364, 505);
        drawCurve(g, 364, 505, 361, 514, 372, 525, 372, 525);
        drawCurve(g, 372, 525, 385, 526, 391, 554, 391, 554);
        drawCurve(g, 391, 554, 391, 554, 407, 563, 407, 563);
        drawCurve(g, 407, 563, 415, 581, 415, 581, 443, 600);
        floodFill(g, 120, 535, Palette.FUJI.getColor(), Palette.FUJI.getColor().brighter());

        g.setColor(Palette.FUJI.getColor());
        drawCurve(g, 353, 482, 355, 494, 338, 511, 340, 523);
        drawCurve(g, 353, 482, 363, 496, 335, 523, 340, 523);
        floodFill(g, 350, 499, Palette.FUJI.getColor().brighter(), Palette.FUJI.getColor());
        drawCurve(g, 358, 504, 347, 521, 347, 521, 355, 531);
        drawCurve(g, 358, 504, 358, 504, 355, 531, 355, 531);
        floodFill(g, 353, 521, Palette.FUJI.getColor().brighter(), Palette.FUJI.getColor());
        drawCurve(g, 367, 515, 367, 515, 353, 546, 357, 554);
        drawCurve(g, 357, 554, 357, 554, 369, 570, 369, 570);
        drawCurve(g, 377, 516, 377, 516, 363, 542, 363, 542);
        drawCurve(g, 363, 542, 361, 551, 369, 570, 369, 570);
        floodFill(g, 367, 526, Palette.FUJI.getColor().brighter(), Palette.FUJI.getColor());
        drawCurve(g, 376, 521, 376, 521, 375, 546, 379, 554);
        drawCurve(g, 379, 554, 379, 554, 402, 563, 418, 579);
        floodFill(g, 383, 542, Palette.FUJI.getColor().brighter(), Palette.FUJI.getColor());
        drawCurve(g, 235, 475, 235, 475, 207, 510, 207, 510);
        drawCurve(g, 207, 510, 201, 518, 212, 526, 206, 530);
        drawCurve(g, 206, 530, 205, 537, 165, 566, 165, 566);
        drawCurve(g, 239, 486, 239, 486, 236, 513, 236, 513);
        drawCurve(g, 236, 513, 236, 513, 222, 519, 223, 528);
        drawCurve(g, 223, 528, 223, 528, 223, 546, 223, 546);
        drawCurve(g, 223, 546, 223, 546, 198, 550, 198, 550);
        drawCurve(g, 198, 550, 198, 550, 165, 566, 165, 566);
        floodFill(g, 226, 505, Palette.FUJI.getColor().brighter(), Palette.FUJI.getColor());
        drawCurve(g, 211, 494, 193, 502, 183, 515, 189, 529);
        drawCurve(g, 211, 494, 211, 494, 201, 507, 201, 507);
        drawCurve(g, 201, 507, 194, 511, 199, 522, 189, 529);
        floodFill(g, 194, 510, Palette.FUJI.getColor().brighter(), Palette.FUJI.getColor());
        drawCurve(g, 167, 468, 170, 470, 103, 494, 103, 494);
        drawCurve(g, 167, 474, 160, 480, 130, 490, 103, 494);
        floodFill(g, 155, 478, Palette.FUJI.getColor().brighter(), Palette.FUJI.getColor());

        drawCurve(g, 155, 508, 155, 508, 130, 514, 130, 514);
        drawCurve(g, 130, 514, 111, 525, 111, 525, 65, 541);
        drawCurve(g, 155, 508, 135, 522, 117, 529, 117, 529);
        drawCurve(g, 117, 529, 117, 529, 99, 541, 65, 541);
        floodFill(g, 101, 534, Palette.FUJI.getColor().brighter(), Palette.FUJI.getColor());
        fillTriangle(g, 203, 478, 186, 482, 168, 494);
        
        fillTriangle(g, 287, 490, 295, 538, 282, 570);
        drawCurve(g, 276, 542, 260, 560, 247, 592, 247, 592);
        drawCurve(g, 276, 542, 275, 558, 262, 577, 247, 592);
        floodFill(g, 265, 564, Palette.FUJI.getColor().brighter(), Palette.FUJI.getColor());
        drawCurve(g, 276, 506, 276, 506, 272, 560, 251, 586);
        drawCurve(g, 276, 506, 276, 506, 255, 580, 255, 580);
        floodFill(g, 266, 547, Palette.FUJI.getColor().brighter(), Palette.FUJI.getColor());
        fillTriangle(g, 159, 513, 143, 535, 150, 560);
        

        g.setColor(Palette.FUJI.getColor().brighter());
        drawCurve(g, 375, 524, 375, 524, 379, 536, 387, 538);
        fillTriangle(g, 227, 493, 211, 510, 213, 518);
        fillTriangle(g, 227, 523, 220, 542, 200, 552);
    }

    private void drawSnowShadow(Graphics2D g) {
        g.setColor(Palette.SNOW.getColor().darker());
        fillTriangle(g, 322, 377, 320, 390, 330, 390);
        fillTriangle(g, 325, 400, 320, 390, 330, 390);
        fillTriangle(g, 325, 395, 320, 410, 330, 405);
        fillTriangle(g, 320, 440, 320, 410, 330, 405);
        fillTriangle(g, 320, 440, 355, 472, 325, 415);
        drawCurve(g, 315, 380, 310, 395, 310, 405, 315, 405);
        drawCurve(g, 315, 380, 310, 395, 320, 400, 315, 405);
        floodFill(g, 312, 400, Palette.SNOW.getColor(), Palette.SNOW.getColor().darker());
        floodFill(g, 365, 430, Palette.SNOW.getColor(), Palette.SNOW.getColor().darker());
        drawCurve(g, 310, 420, 310, 450, 335, 460, 340, 470);
        drawCurve(g, 310, 420, 310, 420, 315, 455, 315, 455);
        drawCurve(g, 315, 455, 315, 455, 340, 470, 340, 470);
        floodFill(g, 318, 451, Palette.SNOW.getColor(), Palette.SNOW.getColor().darker());
        drawCurve(g, 266, 402, 260, 405, 265, 420, 260, 420);
        drawCurve(g, 260, 420, 260, 420, 262, 438, 262, 438);
        drawCurve(g, 262, 438, 262, 438, 271, 459, 271, 459);
        drawCurve(g, 266, 402, 260, 405, 255, 415, 255, 415);
        drawCurve(g, 255, 415, 255, 415, 260, 438, 260, 438);
        drawCurve(g, 260, 438, 260, 438, 271, 459, 271, 459);
        floodFill(g, 260, 415, Palette.SNOW.getColor(), Palette.SNOW.getColor().darker());
        drawCurve(g, 245, 405, 245, 405, 225, 425, 225, 430);
        drawCurve(g, 225, 430, 225, 430, 211, 456, 211, 456);
        drawCurve(g, 245, 405, 250, 410, 225, 430, 230, 430);
        drawCurve(g, 230, 430, 230, 430, 215, 456, 211, 456);
        floodFill(g, 236, 417, Palette.SNOW.getColor(), Palette.SNOW.getColor().darker());
        drawCurve(g, 205, 440, 200, 446, 164, 466, 168, 466);
        drawCurve(g, 205, 440, 205, 455, 170, 460, 168, 466);
        floodFill(g, 194, 450, Palette.SNOW.getColor(), Palette.SNOW.getColor().darker());
        drawCurve(g, 265, 382, 285, 382, 300, 377, 310, 374);
        
        drawCurve(g, 236, 446, 236, 446, 223, 466, 223, 466);
        drawCurve(g, 223, 466, 215, 468, 224, 486, 224, 486);
        drawCurve(g, 236, 446, 236, 446, 231, 466, 231, 466);
        drawCurve(g, 231, 466, 231, 466, 235, 474, 235, 474);
        floodFill(g, 228, 472, Palette.SNOW.getColor(), Palette.SNOW.getColor().darker());

        g.setColor(Palette.SNOW.getColor());
        fillTriangle(g, 325, 425, 360, 470, 325, 435);
    }

    private void drawBranches(Graphics2D g) {
        g.setColor(Palette.BRANCH.getColor());
        //main branch
        drawCurve(g, 0, 150, 34, 138, 73, 166, 100, 170);
        drawCurve(g, 100, 170, 138, 168, 203, 124, 231, 120);
        drawCurve(g, 0, 164, 62, 167, 73, 176, 100, 180);
        drawCurve(g, 100, 180, 138, 178, 175, 148, 231, 120);
        floodFillBorder(g, 23, 157, new Color[]{Palette.BRANCH.getColor()}, Palette.BRANCH.getColor());
        floodFillBorder(g, 152, 153, new Color[]{Palette.BRANCH.getColor()}, Palette.BRANCH.getColor());
        //lower branch
        drawCurve(g, 0, 160, 26, 172, 26, 172, 50, 191);
        drawCurve(g, 50, 191, 57, 196, 77, 236 , 77, 236);
        drawCurve(g, 20, 160, 20, 160, 46, 172, 60, 191);
        drawCurve(g, 60, 191, 67, 196, 77, 236, 77, 236);
        floodFillBorder(g, 37, 176, new Color[]{Palette.BRANCH.getColor()}, Palette.BRANCH.getColor());
        //branch 1
        drawCurve(g, 127, 172, 127, 172, 169, 188, 169, 188);
        drawCurve(g, 169, 188, 195, 185, 216, 192, 216, 192);
        drawCurve(g, 216, 192, 216, 192, 237, 199, 237, 199);
        drawCurve(g, 115, 174, 115, 174, 168, 193, 168, 193);
        drawCurve(g, 168, 193, 168, 193, 187, 188, 215, 195);
        drawCurve(g, 215, 195, 215, 195, 237, 199, 237, 199);
        floodFillBorder(g, 134, 179, new Color[]{Palette.BRANCH.getColor()}, Palette.BRANCH.getColor());
        //branch 2
        drawCurve(g, 145, 160, 145, 160, 191, 169, 191, 169);
        drawCurve(g, 145, 164, 145, 164, 171, 169, 191, 169);
        floodFillBorder(g, 155, 164, new Color[]{Palette.BRANCH.getColor()}, Palette.BRANCH.getColor());
        //branch 3
        drawCurve(g, 143, 157, 143, 157, 151, 155, 158, 144);
        drawCurve(g, 158, 144, 156, 130, 156, 130, 184, 111);
        drawCurve(g, 163, 151, 157, 131, 184, 111, 184, 111);
        floodFillBorder(g, 159, 137, new Color[]{Palette.BRANCH.getColor()}, Palette.BRANCH.getColor());
        //branch 4
        drawCurve(g, 199, 136, 199, 136, 250, 142, 250, 142);
        drawCurve(g, 250, 142, 250, 142, 287, 128, 287, 128);
        drawCurve(g, 193, 139, 193, 139, 251, 145, 251, 145);
        drawCurve(g, 251, 145, 251, 145,287, 128, 287, 128);
        floodFillBorder(g, 207, 139, new Color[]{Palette.BRANCH.getColor()}, Palette.BRANCH.getColor());
    }

    private void drawFlowers(Graphics2D g) {
        drawFlower(g, 82, 227, 1, 1);
        drawFlower(g, 123, 168 ,1 , 1);
        drawFlower(g, 223, 120, 1, 1);
        drawFlower(g, 167, 191, -1, 1);
        drawFlower(g, 183, 112, -1, 1);
        drawFlower(g, 192, 165, -1, -1);
        drawFlower(g, 46, 189, 1, -1);
        drawFlower(g, 279, 133, 1, 1);
        drawFlower(g, 232, 200, 1, 1);
    }

    private void drawFlower(Graphics2D g, int x, int y, int xd, int yd) {
        drawPetals(g, x, y, xd, yd);
        drawPollenShadow(g, x, y);
        drawPollen(g, x, y, xd, yd);
    }

    private void drawPetals(Graphics2D g, int x, int y, int xd, int yd) {
        g.setColor(Palette.POLLEN.getColor());
        drawCircle(g, x, y, 3);
        floodFillBorder(g, x, y, new Color[]{Palette.POLLEN.getColor()}, Palette.POLLEN.getColor());
        g.setColor(Palette.PETAL.getColor());
        drawCurve(g, x-9*xd, y-7*yd, x-11*xd, y-12*yd, x-4*xd, y-17*yd, x-4*xd,  y-17*yd);
        drawCurve(g, x-4*xd, y-17*yd, x-4*xd,  y-17*yd, x+1*xd, y-19*yd, x+1*xd, y-19*yd);
        drawCurve(g, x+1*xd, y-19*yd, x+1*xd, y-19*yd, x+8*xd, y-15*yd, x+8*xd, y-7*yd);
        drawCurve(g, x+8*xd, y-7*yd, x+8*xd, y-7*yd, x+13*xd, y-11*yd, x+19*xd, y-9*yd);
        drawCurve(g, x+19*xd, y-9*yd, x+13*xd, y-9*yd, x+24*xd, y-5*yd, x+21*xd, y);
        drawCurve(g, x+21*xd, y, x+21*xd, y, x+19*xd, y+6*yd, x+13*xd, y+5*yd);
        drawCurve(g, x+13*xd, y+5*yd, x+13*xd, y+5*yd, x+18*xd, y+10*yd, x+17*xd, y+13*yd);
        drawCurve(g, x+17*xd, y+13*yd, x+17*xd, y+13*yd, x+13*xd, y+21*yd, x+7*xd, y+18*yd);
        drawCurve(g, x+7*xd, y+18*yd, x+7*xd, y+18*yd, x+1*xd, y+16*yd, x+1*xd, y+12*yd);
        drawCurve(g, x+1*xd, y+12*yd, x+1*xd, y+12*yd, x+4*xd, y+22*yd, x-9*xd, y+18*yd);
        drawCurve(g, x-9*xd, y+18*yd, x-16*xd, y+14*yd, x-16*xd, y+9*yd, x-8*xd, y+6*yd);
        drawCurve(g, x-8*xd, y+6*yd, x-11*xd, y+9*yd, x-20*xd, y+7*yd, x-19*xd, y+1*yd);
        drawCurve(g, x-19*xd, y+1*yd, x-20*xd, y-7*yd, x-16*xd, y-7*yd, x-9*xd, y-7*yd);
        floodFillBorder(g, x-2*xd, y-9*yd, new Color[]{Palette.POLLEN.getColor(), Palette.PETAL.getColor()}, Palette.PETAL.getColor());
    }

    private void drawPollenShadow(Graphics2D g, int x, int y) {
        g.setColor(Palette.PETALSHADOW.getColor());
        drawCircle(g, x, y, 8);
        floodFill(g, x+3, y+5, Palette.PETAL.getColor(), Palette.PETALSHADOW.getColor());
    }

    private void drawPollen(Graphics2D g, int x, int y, int xd, int yd) {
        g.setColor(Palette.POLLEN.getColor());
        drawLine(g, x, y, x+3*xd, y-9*yd);
        drawLine(g, x, y, x-3*xd, y-9*yd);
        drawLine(g, x, y, x+10*xd, y-3*yd);
        drawLine(g, x, y, x+9*xd, y+8*yd);
        drawLine(g, x, y, x-3*xd, y+8*yd);
        drawLine(g, x, y, x-9*xd, y-3*yd);
        drawLine(g, x, y, x-7*xd, y+4*yd);
    }

    private void drawFallingPetals(Graphics2D g, int x, int y, int xd, int yd) {
        g.setColor(Palette.PETAL.getColor());
        drawCurve(g, x-37*xd, y+4*yd, x-35*xd, y-2*yd, x-26*xd, y+2*yd, x-24*xd, y-3*yd);
        drawCurve(g, x-37*xd, y+4*yd, x-28*xd, y+8*yd, x-24*xd, y-3*yd, x-24*xd, y-3*yd);
        floodFillBorder(g, x-33*xd, y+2*yd, new Color[]{Palette.PETAL.getColor()}, Palette.PETAL.getColor());
    }

    private void paint_A(Graphics2D g) {
        g.setColor(Palette.TEXT.getColor());
        drawCurve(g,547,26,543,32,547,44,551,48);
        drawCurve(g,547,26,551,32,555,44,551,48);
        drawCurve(g,552,30,557,25,564,24,564,29);
        drawCurve(g,552,30,556,28,560,33,564,29);
        drawCurve(g,558,35,554,34,552,34,550,34);
        drawCurve(g,558,35,555,37,553,39,551,41);
        drawCurve(g,563,26,562,33,563,46,561,48);
        drawCurve(g,559,29,558,35,558,45,561,48);
        drawCurve(g,559,45,551,54,549,54,543,50);
        drawLine(g,543,50,559,42);
        floodFillBorder(g, 549, 36, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 554, 36, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 560, 28, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 560, 38, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 551, 49, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        drawCurve(g,567,19,571,35,569,47,561,56);
        drawCurve(g,567,19,575,25,573,54,561,56);
        drawCurve(g,569,24,579,15,584,17,584,23);
        drawCurve(g,569,24,579,20,584,22,579,23);
        drawCurve(g,584,23,582,35,583,48,586,58);
        drawCurve(g,579,23,578,33,578,47,586,58);
        drawCurve(g,571,44,576,51,579,52,586,58);
        drawCurve(g,571,44,578,49,579,50,584,51);
        drawCurve(g,571,28,577,27,579,30,572,32);
        drawCurve(g,571,36,579,38,581,40,571,40);
        drawLine(g,576,27,571,36);
        drawLine(g,577,27,571,36);
        drawLine(g,578,27,572,36);
        floodFillBorder(g, 570, 33, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 568, 22, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 581, 28, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 583, 51, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 579, 51, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 573, 38, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 573, 29, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 565, 52, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 564, 53, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 577, 49, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 574, 21, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
    } 

    private void paint_Ke(Graphics2D g) {
        g.setColor(Palette.TEXT.getColor());
        drawCurve(g,552,68,555,78,544,90,552,98);
        drawCurve(g,552,68,561,75,549,90,554,92);
        drawCurve(g,552,97,553,102,558,95,561,85);
        drawCurve(g,554,92,554,94,562,84,561,85);
        floodFillBorder(g, 553, 95, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 558, 89, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        drawCurve(g,563,79,579,71,585,75,582,77);
        drawCurve(g,563,79,570,78,578,78,582,77);
        drawCurve(g,570,67,571,69,576,95,570,102);
        drawCurve(g,570,67,573,63,577,66,576,82);
        drawCurve(g,576,82,577,88,575,96,570,102);
        floodFillBorder(g, 574, 84, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 574, 76, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 573, 71, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 578, 76, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 570, 77, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
    }

    private void paint_Ma1(Graphics2D g) {
        g.setColor(Palette.TEXT.getColor());
        drawCurve(g,558,116,565,120,564,142,562,145);
        drawCurve(g,558,116,569,120,566,130,568,140);
        drawCurve(g,568,140,565,159,562,157,551,148);
        drawCurve(g,551,148,556,137,572,142,579,153);
        drawCurve(g,562,145,561,154,551,147,562,145);
        drawCurve(g,567,147,569,148,572,149,579,153);
        floodFillBorder(g, 565,135, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 569,146, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 563,148, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 574,149, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        drawCurve(g,551,121,557,126,566,118,577,125);
        drawCurve(g,551,121,557,130,566,122,577,125);
        drawCurve(g,554,131,552,135,568,133,578,135);
        drawCurve(g,554,131,552,139,568,137,578,135);
        drawCurve(g,577,125,575,128,560,128,554,131);
        drawCurve(g,575,125,576,129,559,128,554,131);
        floodFillBorder(g, 559,123, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 567,123, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 560,135, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 569,135, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
    }  

    private void paint_Shi(Graphics2D g) {
        g.setColor(Palette.TEXT.getColor());
        drawCurve(g,560,167,568,179,550,196,576,204);
        drawCurve(g,560,167,573,179,557,193,576,204);
        floodFillBorder(g, 563,186, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 562,171, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 569,200, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 568,199, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
    }

    private void paint_Te(Graphics2D g) {
        g.setColor(Palette.TEXT.getColor());
        drawCurve(g,555,220,552,224,558,228,577,223);
        drawCurve(g,555,220,546,224,558,238,579,223);
        drawCurve(g,577,223,578,224,579,225,580,226);
        drawCurve(g,580,226,565,232,560,244,568,250);
        drawCurve(g,574,227,560,233,558,242,564,252);
        drawCurve(g,564,252,565,256,567,253,568,250);
        plot(g, 567, 229);
        floodFillBorder(g, 564,234, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 561,226, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 573,224, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 573,228, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 575,226, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
    }

    private void paint_O(Graphics2D g) {
        g.setColor(Palette.TEXT.getColor());
        drawCurve(g,497,70,500,79,498,95,496,99);
        drawCurve(g,501,73,504,78,502,97,498,103);
        drawCurve(g,496,70,498,71,500,72,501,73);
        drawCurve(g,498,103,492,100,489,98,488,92);
        drawCurve(g,488,92,510,78,525,88,514,103);
        drawCurve(g,493,94,509,82,519,92,514,103);
        drawCurve(g,496,99,494,96,492,95,493,94);
        floodFillBorder(g, 500,79, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 499,94, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 514,89, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 500,88, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        drawCurve(g,493,76,499,80,505,70,508,74);
        g.drawRect(480, 60, 50, 50);
    }

    private void paint_Me(Graphics2D g) {
        g.setColor(Palette.TEXT.getColor());
        g.drawRect(480, 110, 50, 50);
    }

    private void paint_De(Graphics2D g) {
        g.setColor(Palette.TEXT.getColor());
        drawCurve(g,495,170,492,174,498,178,517,173);
        drawCurve(g,495,170,486,174,498,188,519,173);
        drawCurve(g,517,173,518,174,519,175,520,176);
        drawCurve(g,520,176,505,182,500,194,508,200);
        drawCurve(g,514,177,500,183,498,192,504,202);
        drawCurve(g,504,202,505,206,507,203,508,200);
        floodFillBorder(g, 504,184, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 501,176, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 513,174, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 513,178, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 515,176, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        drawCurve(g,519,169,526,179,531,174,519,169);
        drawCurve(g,520,166,529,172,529,163,520,166);
        plot(g, 507, 179);
        floodFillBorder(g, 524,173, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 524,166, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 523,172, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
    }

    private void paint_To(Graphics2D g) {
        g.setColor(Palette.TEXT.getColor());
        drawCurve(g,512,222,517,224,514,230,501,232);
        drawCurve(g,512,222,525,223,515,233,504,235);
        drawCurve(g,501,231,487,233,488,255,513,253);
        drawCurve(g,504,234,489,237,492,247,508,249);
        drawCurve(g,508,249,516,248,517,253,513,253);
        floodFillBorder(g, 505,251, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        drawCurve(g,496,220,501,224,503,232,505,239);
        drawCurve(g,496,220,504,223,509,233,505,239);
        floodFillBorder(g, 503,230, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 505,235, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 501,225, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
    }

    private void paint_U(Graphics2D g) {
        g.setColor(Palette.TEXT.getColor());
        drawCurve(g,493,280,491,284,494,285,512,280);
        drawCurve(g,493,280,489,288,492,290,510,284);
        drawCurve(g,512,280,521,280,515,302,503,307);
        drawCurve(g,510,284,512,292,509,299,503,307);
        floodFillBorder(g, 513,287, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 506,303, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        drawCurve(g,503,265,509,268,515,270,517,268);
        drawCurve(g,503,265,506,268,508,270,514,273);
        drawCurve(g,517,268,517,273,511,275,507,276);
        drawCurve(g,514,272,509,273,508,274,507,276);
        drawLine(g, 517,269,517,271);
        floodFillBorder(g, 513,270, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 511,273, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 509,274, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
    }

    private void paint_Go(Graphics2D g) {
        g.setColor(Palette.TEXT.getColor());
        drawCurve(g,434,123,437,133,453,122,456,137);
        drawCurve(g,434,123,436,133,443,133,450,134);
        drawCurve(g,455,137,451,138,448,138,449,139);
        drawLine(g,446,139,450,133);
        plot(g,448,137);
        plot(g,436,127);
        floodFillBorder(g, 452,133, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        drawCurve(g,431,141,426,146,442,147,452,148);
        drawCurve(g,431,141,424,148,439,154,449,152);
        drawCurve(g,452,148,455,147,453,151,449,152);
        plot(g,430,145);
        floodFillBorder(g, 444,151, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        drawCurve(g,454,122,461,132,466,127,454,122);
        drawCurve(g,455,119,464,125,464,116,455,119);
        floodFillBorder(g, 459,126, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 459,119, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 458,125, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
    }

    private void paint_Za(Graphics2D g) {
        g.setColor(Palette.TEXT.getColor());
        g.drawRect(420, 160, 50, 50);
    }

    private void paint_I(Graphics2D g) {
        g.setColor(Palette.TEXT.getColor());
        drawCurve(g,430,225,426,240,432,247,442,253);
        drawCurve(g,430,225,430,229,433,239,438,245);
        drawCurve(g,441,253,442,247,443,242,447,239);
        drawCurve(g,438,245,441,241,443,240,447,239);
        floodFillBorder(g, 437,247, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        drawCurve(g,446,221,456,225,459,234,458,248);
        drawCurve(g,446,221,460,223,465,236,462,245);
        drawCurve(g,462,245,461,246,461,247,458,248);
        floodFillBorder(g, 461,237, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 453,224, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 454,225, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
    }

    private void paint_Ma2(Graphics2D g) {
        g.setColor(Palette.TEXT.getColor());
        drawCurve(g,438,266,445,270,444,292,442,295);
        drawCurve(g,438,266,449,270,446,280,448,290);
        drawCurve(g,448,290,445,309,442,307,431,298);
        drawCurve(g,431,298,436,287,452,292,459,303);
        drawCurve(g,442,295,441,304,431,297,442,295);
        drawCurve(g,447,297,449,298,452,299,459,303);
        floodFillBorder(g, 445,285, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 449,296, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 443,298, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 454,299, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        drawCurve(g,431,271,437,276,446,268,457,275);
        drawCurve(g,431,271,437,280,446,272,457,275);
        drawCurve(g,434,281,432,285,448,283,458,285);
        drawCurve(g,434,281,432,289,448,287,458,285);
        drawCurve(g,457,275,455,278,440,278,434,281);
        drawCurve(g,455,275,456,279,439,278,434,281);
        floodFillBorder(g, 439,273, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 447,273, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 440,285, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
        floodFillBorder(g, 449,285, new Color[]{Palette.TEXT.getColor()}, Palette.TEXT.getColor());
    }

    private void paint_Su(Graphics2D g) {
        g.setColor(Palette.TEXT.getColor());
        g.drawRect(420, 310, 50, 50);
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
        drawHalfCircle(g, x, y, r, "L");
        drawHalfCircle(g, x, y, r, "R");
    }

    private void drawHalfCircle(Graphics2D g, int x, int y, int r,String d) {
        if(d == "L"){
            drawCurve(g, x-r, y, x-r, (int)(y - (0.552 * r)), (int)(x - (0.552 * r)), y-r, x, y-r);
            drawCurve(g, x-r, y, x-r, (int)(y - (0.552 *-r)), (int)(x - (0.552 * r)), y+r, x, y+r);
        }
        else if(d == "R"){
            drawCurve(g, x+r, y, x+r, (int)(y - (0.552 * r)), (int)(x - (0.552 *-r)), y-r, x, y-r);
            drawCurve(g, x+r, y, x+r, (int)(y - (0.552 *-r)), (int)(x - (0.552 *-r)), y+r, x, y+r);
        }
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

enum Palette {
    SKY1("Ffda5f"),
    SKY2("fca29a"),
    SUN("F84434"),
    FUJI("2a4a87"),
    SNOW("FEFEFE"),
    LAND("1B0A12"),
    CLOUD("afd4e7"),
    BRANCH("953c38"),
    POLLEN("f8bf81"),
    PETAL("fb7d91"),
    PETALSHADOW("F64764"),
    TEXT("000000");

    private final Color color;

    Palette(String colorCode) {
        this.color = new Color(Integer.parseInt(colorCode, 16));
    }

    public Color getColor() {
        return color;
    }
}