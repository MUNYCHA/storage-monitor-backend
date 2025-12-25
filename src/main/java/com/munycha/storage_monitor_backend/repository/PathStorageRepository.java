package com.munycha.storage_monitor_backend.repository;

import com.munycha.storage_monitor_backend.dto.PathStorageDto;
import com.munycha.storage_monitor_backend.entity.PathStorageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PathStorageRepository extends JpaRepository<PathStorageEntity,Long> {

    List<PathStorageEntity> findBySnapshotId(Long snapshotId);
}
