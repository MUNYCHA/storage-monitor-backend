package com.munycha.storage_monitor_backend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "server_storage_snapshot")
public class ServerStorageSnapshotEntity {

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

    @OneToMany(mappedBy = "serverStorageSnapshot",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    @OrderBy("path ASC")
    private List<MountPathStorageUsageEntity> mountPathStorageUsages = new ArrayList<>();

    public void addMountPathStorageUsage(MountPathStorageUsageEntity mountPathStorageUsage){
        mountPathStorageUsage.setServerStorageSnapshot(this);
        this.mountPathStorageUsages.add(mountPathStorageUsage);
    }

    public ServerStorageSnapshotEntity() {
    }

    public ServerStorageSnapshotEntity(String systemId, String systemName, String serverIp, String serverName, LocalDateTime collectedAt, List<MountPathStorageUsageEntity> mountPathStorageUsages) {
        this.systemId = systemId;
        this.systemName = systemName;
        this.serverIp = serverIp;
        this.serverName = serverName;
        this.collectedAt = collectedAt;
        this.mountPathStorageUsages = mountPathStorageUsages;
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

    public List<MountPathStorageUsageEntity> getMountPathStorageUsages() {
        return mountPathStorageUsages;
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

    public void setMountPathStorageUsages(List<MountPathStorageUsageEntity> mountPathStorageUsages) {
        this.mountPathStorageUsages = mountPathStorageUsages;
    }
}
