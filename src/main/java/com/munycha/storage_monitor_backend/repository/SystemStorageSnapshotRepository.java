package com.munycha.storage_monitor_backend.repository;

import com.munycha.storage_monitor_backend.entity.SystemStorageSnapshotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemStorageSnapshotRepository extends JpaRepository<SystemStorageSnapshotEntity,Long> {
}
