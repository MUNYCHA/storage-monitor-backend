package com.munycha.storage_monitor_backend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "server_storage_usage")
public class ServerStorageUsageEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "system_id")
    private String systemId;

    @Column(name = "system_name")
    private String systemName;

    @Column(name = "server_ip")
    private String serverIp;

    @Column(name = "server_name")
    private String serverName;



    @Column(name = "collected_at")
    private LocalDateTime collectedAt;

    public ServerStorageUsageEntity() {
    }

    public ServerStorageUsageEntity(String systemId, String systemName, String serverIp, String serverName, LocalDateTime collectedAt) {
        this.systemId = systemId;
        this.systemName = systemName;
        this.serverIp = serverIp;
        this.serverName = serverName;
        this.collectedAt = collectedAt;
    }

    public Long getId() {
        return id;
    }

    public String getSystemId() {
        return systemId;
    }

    public String getSystemName() {
        return systemName;
    }

    public String getServerIp() {
        return serverIp;
    }

    public String getServerName() {
        return serverName;
    }

    public LocalDateTime getCollectedAt() {
        return collectedAt;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public void setCollectedAt(LocalDateTime collectedAt) {
        this.collectedAt = collectedAt;
    }
}
