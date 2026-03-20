package com.munycha.storagemonitor.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mount_path_storage_usage")
@Getter
@Setter
@NoArgsConstructor
public class MountPathStorageUsage {

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

    @ManyToOne
    @JoinColumn(name = "server_storage_snapshot_id")
    private ServerStorageSnapshot serverStorageSnapshot;
}
