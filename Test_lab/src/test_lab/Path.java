package test_lab;

/**
 * Router forwarding table info.
 */
public class Path {

    /** length */
    private int length;
    /** path(split with space) */
    private String path;

    public Path(String path, int length) {
        this.length = length;
        this.path = path;
    }

    public int getLength() {
        return length;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "path: " + path + " length: " + length;
    }
}
