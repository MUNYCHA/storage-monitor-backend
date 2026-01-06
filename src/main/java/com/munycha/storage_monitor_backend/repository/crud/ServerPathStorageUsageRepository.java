package com.munycha.storage_monitor_backend.repository.crud;

import com.munycha.storage_monitor_backend.entity.ServerPathStorageUsageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerPathStorageUsageRepository extends JpaRepository<ServerPathStorageUsageEntity,Long> {}
