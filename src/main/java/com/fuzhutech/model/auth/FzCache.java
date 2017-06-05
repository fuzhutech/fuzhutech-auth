package com.fuzhutech.model.auth;

public class FzCache {
    private String name;
    private String status;
    private long memoryStoreSize;
    private int diskStoreSize;
    private float averageGetTime;
    private float averageSearchTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getMemoryStoreSize() {
        return memoryStoreSize;
    }

    public void setMemoryStoreSize(long memoryStoreSize) {
        this.memoryStoreSize = memoryStoreSize;
    }

    public int getDiskStoreSize() {
        return diskStoreSize;
    }

    public void setDiskStoreSize(int diskStoreSize) {
        this.diskStoreSize = diskStoreSize;
    }

    public float getAverageGetTime() {
        return averageGetTime;
    }

    public void setAverageGetTime(float averageGetTime) {
        this.averageGetTime = averageGetTime;
    }

    public float getAverageSearchTime() {
        return averageSearchTime;
    }

    public void setAverageSearchTime(float averageSearchTime) {
        this.averageSearchTime = averageSearchTime;
    }
}
