import java.awt.Color;

public enum ColorEnum {
        SKY("000000"),//BLACK
        WATER("2B5B80"),//Some Blue
        STAR("7F00FF"),//Some Violet
        FUJI("FFFFFF"),//WHITE
        DAWNSKY("000050"),
        SUNRISE("E17800");

        private final Color color;

        ColorEnum(String colorCode) {
            this.color = new Color(Integer.parseInt(colorCode, 16));
        }

        public Color getColor() {
            return color;
        }
    }