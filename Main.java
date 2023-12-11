import javax.swing.JFrame;

public class Main {
    //- Use lines, curves, and triangles to create a 600 x 600 still image
    public static void main(String[] args) {
        JFrame f = new JFrame();
        NewYear ny = new NewYear();
        f.add(ny);
        f.setTitle("New Year");
        f.setSize(600,600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
