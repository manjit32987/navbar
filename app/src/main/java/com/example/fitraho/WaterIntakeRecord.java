package com.example.fitraho;

public class WaterIntakeRecord {
    private String time;
    private int containerSize;

    public WaterIntakeRecord(String time, int containerSize) {
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
