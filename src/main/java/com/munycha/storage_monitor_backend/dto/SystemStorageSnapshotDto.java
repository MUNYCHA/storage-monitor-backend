package com.munycha.storage_monitor_backend.dto;

import java.time.LocalDateTime;
import java.util.List;

public class SystemStorageSnapshotDto {
    private String serverName;
    private String serverIp;
    private LocalDateTime snapshotTime;
    private List<PathStorageDto> pathStorageDtos;

    public SystemStorageSnapshotDto() {
    }

    public SystemStorageSnapshotDto(String serverName, String serverIp, LocalDateTime snapshotTime, List<PathStorageDto> pathStorageDtos) {
        this.serverName = serverName;
        this.serverIp = serverIp;
        this.snapshotTime = snapshotTime;
        this.pathStorageDtos = pathStorageDtos;
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

    public List<PathStorageDto> getPathStorageDtos() {
        return pathStorageDtos;
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

    public void setPathStorageDtos(List<PathStorageDto> pathStorageDtos) {
        this.pathStorageDtos = pathStorageDtos;
    }
}
