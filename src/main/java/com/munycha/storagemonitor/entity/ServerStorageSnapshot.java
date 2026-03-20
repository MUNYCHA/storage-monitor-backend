package com.munycha.storagemonitor.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "server_storage_snapshot")
@Getter
@Setter
@NoArgsConstructor
public class ServerStorageSnapshot {

    @Id
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

    @OneToMany(mappedBy = "serverStorageSnapshot", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("path ASC")
    private List<MountPathStorageUsage> mountPathStorageUsages = new ArrayList<>();

    public void addMountPathStorageUsage(MountPathStorageUsage mountPathStorageUsage) {
        mountPathStorageUsage.setServerStorageSnapshot(this);
        this.mountPathStorageUsages.add(mountPathStorageUsage);
    }
}
