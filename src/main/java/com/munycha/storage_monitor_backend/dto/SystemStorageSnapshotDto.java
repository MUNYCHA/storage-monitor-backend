package com.munycha.storage_monitor_backend.dto;

import java.time.LocalDateTime;
import java.util.List;

public class SystemStorageSnapshotDto {
    private String serverName;
    private String serverIp;
    private LocalDateTime snapshotTime;
    private List<PathStorageDto> pathStorages;

    public SystemStorageSnapshotDto() {
    }

    public SystemStorageSnapshotDto(String serverName, String serverIp, LocalDateTime snapshotTime, List<PathStorageDto> pathStorages) {
        this.serverName = serverName;
        this.serverIp = serverIp;
        this.snapshotTime = snapshotTime;
        this.pathStorages = pathStorages;
    }

    public String getServerName() {
        return serverName;
    }

    public String getServerIp() {
        return serverIp;
    }

    public LocalDateTime getSnapshotTime() {
        return snapshotTime;
    }

    public List<PathStorageDto> getPathStorages() {
        return pathStorages;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public void setSnapshotTime(LocalDateTime snapshotTime) {
        this.snapshotTime = snapshotTime;
    }

    public void setPathStorages(List<PathStorageDto> pathStorages) {
        this.pathStorages = pathStorages;
    }
}
