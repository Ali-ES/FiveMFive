package ir.FiveMFive.FiveMFive.Java;

public class DashboardItem {
    private int imageResID;
    private String title;
    public DashboardItem(int imageResID, String title) {
        this.imageResID = imageResID;
        this.title = title;
    }

    public int getImageResID() {
        return imageResID;
    }

    public void setImageResID(int imageResID) {
        this.imageResID = imageResID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
