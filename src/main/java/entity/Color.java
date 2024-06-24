package entity;

public class Color {
    int color_ID;
    String color_Name;

    public Color() {
    }

    public Color(int color_ID, String color_Name) {
        this.color_ID = color_ID;
        this.color_Name = color_Name;
    }

    public int getColor_ID() {
        return color_ID;
    }

    public void setColor_ID(int color_ID) {
        this.color_ID = color_ID;
    }

    public String getColor_Name() {
        return color_Name;
    }

    public void setColor_Name(String color_Name) {
        this.color_Name = color_Name;
    }
}
