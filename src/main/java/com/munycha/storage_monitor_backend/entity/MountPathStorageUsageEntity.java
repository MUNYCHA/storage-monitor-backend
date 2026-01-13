package com.munycha.storage_monitor_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "mount_path_storage_usage")
public class MountPathStorageUsageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "path", nullable = false)
    private String path;

    @Column(name = "total_bytes", nullable = false)
    private Long totalBytes;

    @Column(name = "used_bytes", nullable = false)
    private Long usedBytes;

    @Column(name = "used_percent", nullable = false)
    private Double usedPercent;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "server_storage_snapshot_id")
    private ServerStorageSnapshotEntity serverStorageSnapshot;

    public MountPathStorageUsageEntity() {
    }

    public MountPathStorageUsageEntity(String path, Long totalBytes, Long usedBytes, Double usedPercent, ServerStorageSnapshotEntity serverStorageSnapshot) {
        this.path = path;
        this.totalBytes = totalBytes;
        this.usedBytes = usedBytes;
        this.usedPercent = usedPercent;
        this.serverStorageSnapshot = serverStorageSnapshot;
    }

    public Long getId() {
        return id;
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

    public ServerStorageSnapshotEntity getServerStorageSnapshot() {
        return serverStorageSnapshot;
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

    public void setServerStorageSnapshot(ServerStorageSnapshotEntity serverStorageSnapshot) {
        this.serverStorageSnapshot = serverStorageSnapshot;
    }
}



