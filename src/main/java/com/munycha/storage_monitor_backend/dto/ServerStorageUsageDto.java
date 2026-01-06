package com.munycha.storage_monitor_backend.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ServerStorageUsageDto {
    private String systemId;
    private String systemName;
    private String serverIp;
    private String serverName;
    private LocalDateTime collectedAt;
    private List<ServerPathStorageUsageDto> serverPathStorageUsages;

    public ServerStorageUsageDto() {
    }

    public ServerStorageUsageDto(String systemId, String systemName, String serverIp, String serverName, LocalDateTime collectedAt, List<ServerPathStorageUsageDto> serverPathStorageUsages) {
        this.systemId = systemId;
        this.systemName = systemName;
        this.serverIp = serverIp;
        this.serverName = serverName;
        this.collectedAt = collectedAt;
        this.serverPathStorageUsages = serverPathStorageUsages;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public LocalDateTime getCollectedAt() {
        return collectedAt;
    }

    public void setCollectedAt(LocalDateTime collectedAt) {
        this.collectedAt = collectedAt;
    }

    public List<ServerPathStorageUsageDto> getServerPathStorageUsages() {
        return serverPathStorageUsages;
    }

    public void setServerPathStorageUsages(List<ServerPathStorageUsageDto> serverPathStorageUsages) {
        this.serverPathStorageUsages = serverPathStorageUsages;
    }
}
