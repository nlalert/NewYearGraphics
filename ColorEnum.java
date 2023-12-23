import java.awt.Color;

public enum ColorEnum {
        SKY("000000"),//BLACK
        WATER("2B5B80"),//Some Blue
        STAR("FFFFFF"),//Some Violet
        DAWNSKY("000050"),
        SUNRISE("E17800"),
        FUJI("011D4A"),
        SNOW("FEFEFE"),
        LAND("1B0A12");

        private final Color color;

        ColorEnum(String colorCode) {
            this.color = new Color(Integer.parseInt(colorCode, 16));
        }

        public Color getColor() {
            return color;
        }
    }