package com.munycha.storage_monitor_backend.service;

import com.munycha.storage_monitor_backend.dto.ServerPathStorageUsageDto;
import com.munycha.storage_monitor_backend.dto.ServerStorageUsageDto;
import com.munycha.storage_monitor_backend.entity.ServerStorageUsageEntity;
import com.munycha.storage_monitor_backend.repository.crud.ServerStorageUsageRepository;
import com.munycha.storage_monitor_backend.repository.query.ServerPathStorageUsageQueryRepository;
import com.munycha.storage_monitor_backend.repository.query.ServerStorageUsageQueryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ServerStorageUsageServiceImpl
        implements ServerStorageUsageService {

    private final ServerStorageUsageRepository snapshotRepository;
    private final ServerStorageUsageQueryRepository snapshotQueryRepository;
    private final ServerPathStorageUsageQueryRepository pathStorageQueryRepository;

    public ServerStorageUsageServiceImpl(
            ServerStorageUsageRepository snapshotRepository,
            ServerStorageUsageQueryRepository snapshotQueryRepository,
            ServerPathStorageUsageQueryRepository pathStorageQueryRepository
    ) {
        this.snapshotRepository = snapshotRepository;
        this.snapshotQueryRepository = snapshotQueryRepository;
        this.pathStorageQueryRepository = pathStorageQueryRepository;
    }

    @Override
    public List<ServerStorageUsageDto> getSystemStorageUsages() {

        return snapshotRepository.findAll()
                .stream()
                .map(snapshot -> {

                    // fetch path storages for THIS snapshot
                    List<ServerPathStorageUsageDto> pathDtos =
                            pathStorageQueryRepository
                                    .findBySnapshotId(snapshot.getId())
                                    .stream()
                                    .map(path -> new ServerPathStorageUsageDto(
                                            path.getPath(),
                                            path.getTotalBytes(),
                                            path.getUsedBytes(),
                                            path.getUsedPercent()
                                    ))
                                    .toList();

                    return new ServerStorageUsageDto(
                            snapshot.getSystemId(),
                            snapshot.getSystemName(),
                            snapshot.getServerIp(),
                            snapshot.getServerName(),
                            snapshot.getCollectedAt(),
                            pathDtos
                    );
                })
                .toList();
    }



    @Override
    public ServerStorageUsageDto getSystemStorageUsageById(Long id) {

        ServerStorageUsageEntity snapshot = snapshotRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalStateException(
                                "System storage snapshot not found: id=" + id)
                );

        return toDto(snapshot, mapPathStorages(snapshot.getId()));
    }

    @Override
    public List<ServerStorageUsageDto> getLatestSystemStorageUsages() {

        return snapshotQueryRepository.findLatestSystemStorageUsages()
                .stream()
                .map(snapshot ->
                        toDto(
                                snapshot,
                                mapPathStorages(snapshot.getId())
                        )
                )
                .toList();
    }


    private List<ServerPathStorageUsageDto> mapPathStorages(Long snapshotId) {
        return pathStorageQueryRepository.findBySnapshotId(snapshotId)
                .stream()
                .map(entity -> new ServerPathStorageUsageDto(
                        entity.getPath(),
                        entity.getTotalBytes(),
                        entity.getUsedBytes(),
                        entity.getUsedPercent()
                ))
                .toList();
    }

    private ServerStorageUsageDto toDto(
            ServerStorageUsageEntity snapshot,
            List<ServerPathStorageUsageDto> pathStorages
    ) {
        return new ServerStorageUsageDto(
                snapshot.getSystemId(),
                snapshot.getSystemName(),
                snapshot.getServerIp(),
                snapshot.getServerName(),
                snapshot.getCollectedAt(),
                pathStorages
        );
    }
}

