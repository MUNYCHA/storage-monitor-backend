package com.munycha.storage_monitor_backend.dto;

public class MountPathStorageUsageDto {
    private String path;
    private Long totalBytes;
    private Long usedBytes;
    private Double usedPercent;

    public MountPathStorageUsageDto() {
    }

    public MountPathStorageUsageDto(String path, Long totalBytes, Long usedBytes, Double usedPercent) {
        this.path = path;
        this.totalBytes = totalBytes;
        this.usedBytes = usedBytes;
        this.usedPercent = usedPercent;
    }


    public String getPath() {
        return path;
    }

    public Long getTotalBytes() {
        return totalBytes;
    }

    public Long getUsedBytes() {
        return usedBytes;
    }

    public Double getUsedPercent() {
        return usedPercent;
    }



    public void setPath(String path) {
        this.path = path;
    }

    public void setTotalBytes(Long totalBytes) {
        this.totalBytes = totalBytes;
    }

    public void setUsedBytes(Long usedBytes) {
        this.usedBytes = usedBytes;
    }

    public void setUsedPercent(Double usedPercent) {
        this.usedPercent = usedPercent;
    }
}
