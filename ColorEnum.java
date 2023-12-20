import java.awt.Color;

public enum ColorEnum {
        SKY(Color.BLACK),
        WATER(Color.BLUE),
        STAR(Color.WHITE),
        FUJI(Color.GRAY);

        private final Color color;

        ColorEnum(Color color) {
            this.color = color;
        }

        public Color getColor() {
            return color;
        }
    }