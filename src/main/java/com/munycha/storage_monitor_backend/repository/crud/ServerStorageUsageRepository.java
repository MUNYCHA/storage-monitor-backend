package com.munycha.storage_monitor_backend.repository.crud;

import com.munycha.storage_monitor_backend.entity.ServerStorageUsageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerStorageUsageRepository
        extends JpaRepository<ServerStorageUsageEntity, Long> {}

