package com.example.fitraho;

public class listview {
    private String time;
    private int containerSize;

    public listview(String time, int containerSize) {
        this.time = time;
        this.containerSize = containerSize;
    }

    public String getTime() {
        return time;
    }

    public int getContainerSize() {
        return containerSize;
    }
}
