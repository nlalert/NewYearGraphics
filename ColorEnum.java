import java.awt.Color;

public enum ColorEnum {
        WATER("1f282d"),//Some Blue
        STAR("FFFFFF"),//Some Violet
        SKY1("69a4d9"),
        SKY2("bce6e2"),
        SKY3("ffeeb0"),
        SKY4("eeaa63"),
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