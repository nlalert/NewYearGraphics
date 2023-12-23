import java.awt.Color;

public enum ColorEnum {
        SKY("FFFFFF"),//BLACK
        WATER("2B5B80"),//Some Blue
        STAR("7F00FF"),//Some Violet
        FUJI("014D92"),
        SNOW("FEFEFE");

        private final Color color;

        ColorEnum(String colorCode) {
            this.color = new Color(Integer.parseInt(colorCode, 16));
        }

        public Color getColor() {
            return color;
        }
    }