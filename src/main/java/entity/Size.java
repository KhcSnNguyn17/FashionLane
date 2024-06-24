package entity;

public class Size {
    int size_ID;
    String size_Name;

    public Size() {
    }

    public Size(int size_ID, String size_Name) {
        this.size_ID = size_ID;
        this.size_Name = size_Name;
    }

    public int getSize_ID() {
        return size_ID;
    }

    public void setSize_ID(int size_ID) {
        this.size_ID = size_ID;
    }

    public String getSize_Name() {
        return size_Name;
    }

    public void setSize_Name(String size_Name) {
        this.size_Name = size_Name;
    }
}
