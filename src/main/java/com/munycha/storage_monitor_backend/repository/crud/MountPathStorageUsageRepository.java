package com.munycha.storage_monitor_backend.repository.crud;

import com.munycha.storage_monitor_backend.entity.MountPathStorageUsageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MountPathStorageUsageRepository extends JpaRepository<MountPathStorageUsageEntity,Long> {}
