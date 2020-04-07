package client;

import java.io.File;


public class FileInfo {
    private File file;
    private String name;
    private long length;

    public String getFilename() {
        return name;
    }

    public long getLength() {
        return length;
    }

    public void setFilename(String filename) {
        this.name = name;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public FileInfo(File file) {
        this.file = file;
        this.name = file.getName();
        this.length = file.length();

    }

    public FileInfo(File file, String name, long length) {
        this.file = file;
        this.name = name;
        this.length = length;
    }
}
