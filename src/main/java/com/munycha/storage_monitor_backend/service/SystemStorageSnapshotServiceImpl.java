package com.munycha.storage_monitor_backend.service;

import com.munycha.storage_monitor_backend.dto.PathStorageDto;
import com.munycha.storage_monitor_backend.dto.SystemStorageSnapshotDto;
import com.munycha.storage_monitor_backend.entity.SystemStorageSnapshotEntity;
import com.munycha.storage_monitor_backend.repository.PathStorageRepository;
import com.munycha.storage_monitor_backend.repository.SystemStorageSnapshotRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemStorageSnapshotServiceImpl implements SystemStorageSnapshotService {

    private final SystemStorageSnapshotRepository systemStorageSnapshotRepository;
    private final PathStorageRepository pathStorageRepository;

    public SystemStorageSnapshotServiceImpl(SystemStorageSnapshotRepository systemStorageSnapshotRepository, PathStorageRepository pathStorageRepository) {
        this.systemStorageSnapshotRepository = systemStorageSnapshotRepository;
        this.pathStorageRepository = pathStorageRepository;
    }

    @Transactional
    @Override
    public SystemStorageSnapshotDto getSystemStorageSnapshot(Long id) {
        SystemStorageSnapshotEntity systemStorageSnapshotEntity = systemStorageSnapshotRepository.findById(id).orElseThrow(() -> new RuntimeException("snapshot not found"));

        List<PathStorageDto> pathStorageDtos = pathStorageRepository.findBySnapshotId(id).
                stream().
                map(entity -> new PathStorageDto(
                        entity.getPath(),
                        entity.getTotalBytes(),
                        entity.getUsedBytes(),
                        entity.getUsedPercent()
                )).toList();


        return new SystemStorageSnapshotDto(systemStorageSnapshotEntity.getServerName(), systemStorageSnapshotEntity.getServerIp(),systemStorageSnapshotEntity.getSnapshotTime(),pathStorageDtos);

    }

}
