package com.munycha.storage_monitor_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "server_path_storage_usage")
public class MountPathStorageUsageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "server_storage_usage_id")
    private Long serverStorageUsageId;

    @Column(name = "path", nullable = false)
    private String path;

    @Column(name = "total_bytes", nullable = false)
    private Long totalBytes;

    @Column(name = "used_bytes", nullable = false)
    private Long usedBytes;

    @Column(name = "used_percent", nullable = false)
    private Double usedPercent;


    public MountPathStorageUsageEntity() {
    }

    public MountPathStorageUsageEntity(Long serverStorageUsageId, String path, Long totalBytes, Long usedBytes, Double usedPercent) {
        this.serverStorageUsageId = serverStorageUsageId;
        this.path = path;
        this.totalBytes = totalBytes;
        this.usedBytes = usedBytes;
        this.usedPercent = usedPercent;
    }

    public Long getId() {
        return id;
    }

    public Long getServerStorageUsageId() {
        return serverStorageUsageId;
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

    public void setServerStorageUsageId(Long serverStorageUsageId) {
        this.serverStorageUsageId = serverStorageUsageId;
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



