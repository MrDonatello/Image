package sample;

public class ImageInfo {

    private String url;
    private String width;
    private String height;
    private String size;

    ImageInfo(String url, String width, String height) {
        this.url = url;
        this.width = width;
        this.height = height;
        this.size = getSizeImage();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    private String getSizeImage() {
        return ((double) ((Integer.parseInt(width) * Integer.parseInt(height)) * 3) / 1024) + " KB";
    }
}
