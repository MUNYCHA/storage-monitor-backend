package com.munycha.storage_monitor_backend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "system_storage_snapshot")
public class SystemStorageSnapshotEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "server_name")
    private String serverName;

    @Column(name = "server_ip")
    private String serverIp;

    @Column(name = "snapshot_time")
    private LocalDateTime snapshotTime;

    public SystemStorageSnapshotEntity() {
    }

    public SystemStorageSnapshotEntity(String serverName, String serverIp, LocalDateTime snapshotTime) {
        this.serverName = serverName;
        this.serverIp = serverIp;
        this.snapshotTime = snapshotTime;
    }

    public Long getId() {
        return id;
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

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public void setSnapshotTime(LocalDateTime snapshotTime) {
        this.snapshotTime = snapshotTime;
    }
}
