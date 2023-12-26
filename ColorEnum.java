import java.awt.Color;

public enum ColorEnum {
        SKY1("Ffda5f"),
        SKY2("Fff9e3"),
        SUN("F84434"),
        FUJI("011D4A"),
        SNOW("FEFEFE"),
        LAND("1B0A12"),
        CLOUD("afd4e7"),
        BRANCH("953c38");

        private final Color color;

        ColorEnum(String colorCode) {
            this.color = new Color(Integer.parseInt(colorCode, 16));
        }

        public Color getColor() {
            return color;
        }
    }