package com.munycha.storage_monitor_backend.repository.crud;

import com.munycha.storage_monitor_backend.entity.ServerStorageSnapshotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerStorageSnapshotRepository
        extends JpaRepository<ServerStorageSnapshotEntity, Long> {}

