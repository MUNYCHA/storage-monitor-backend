package com.munycha.storage_monitor_backend.entity;

import jakarta.persistence.*;
import lombok.Cleanup;

@Entity
@Table(name = "path_storage")
public class PathStorageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "snapshot_id")
    private Long snapshotId;

    @Column(name = "path", nullable = false)
    private String path;

    @Column(name = "total_bytes", nullable = false)
    private Long totalBytes;

    @Column(name = "used_bytes", nullable = false)
    private Long usedBytes;

    @Column(name = "used_percent", nullable = false)
    private Double usedPercent;


    public PathStorageEntity() {
    }

    public PathStorageEntity(Long snapshotId, String path, Long totalBytes, Long usedBytes, Double usedPercent) {
        this.snapshotId = snapshotId;
        this.path = path;
        this.totalBytes = totalBytes;
        this.usedBytes = usedBytes;
        this.usedPercent = usedPercent;
    }

    public Long getSnapshotId() {
        return snapshotId;
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

    public void setSnapshotId(Long snapshotId) {
        this.snapshotId = snapshotId;
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



