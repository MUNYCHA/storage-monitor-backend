package com.munycha.storage_monitor_backend.service;

import com.munycha.storage_monitor_backend.dto.MountPathStorageUsageDto;
import com.munycha.storage_monitor_backend.dto.ServerStorageSnapshotDto;
import com.munycha.storage_monitor_backend.entity.ServerStorageSnapshotEntity;
import com.munycha.storage_monitor_backend.repository.crud.MountPathStorageUsageRepository;
import com.munycha.storage_monitor_backend.repository.crud.ServerStorageSnapshotRepository;
import com.munycha.storage_monitor_backend.repository.query.ServerStorageSnapshotQueryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ServerStorageSnapshotServiceImpl
        implements ServerStorageSnapshotService {

    private final ServerStorageSnapshotRepository serverStorageSnapshotRepository;
    private final ServerStorageSnapshotQueryRepository serverStorageSnapshotQueryRepository;

    @Autowired
    public ServerStorageSnapshotServiceImpl(ServerStorageSnapshotRepository serverStorageSnapshotRepository, ServerStorageSnapshotQueryRepository serverStorageSnapshotQueryRepository) {
        this.serverStorageSnapshotRepository = serverStorageSnapshotRepository;
        this.serverStorageSnapshotQueryRepository = serverStorageSnapshotQueryRepository;
    }

    @Override
    public List<ServerStorageSnapshotDto> getServerStorageSnapshots() {

        return serverStorageSnapshotRepository.findAll()
                .stream()
                .map(serverStorageSnapshot -> {

                    List<MountPathStorageUsageDto> mountPathStorageUsages =
                            serverStorageSnapshot.getMountPathStorageUsages()
                                    .stream()
                                    .map(usage -> new MountPathStorageUsageDto(
                                            usage.getPath(),
                                            usage.getTotalBytes(),
                                            usage.getUsedBytes(),
                                            usage.getUsedPercent()
                                    ))
                                    .toList();

                    return new ServerStorageSnapshotDto(
                            serverStorageSnapshot.getSystemId(),
                            serverStorageSnapshot.getSystemName(),
                            serverStorageSnapshot.getServerIp(),
                            serverStorageSnapshot.getServerName(),
                            serverStorageSnapshot.getCollectedAt(),
                            mountPathStorageUsages
                    );
                })
                .toList();
    }





    @Override
    public ServerStorageSnapshotDto getServerStorageSnapshotById(Long id) {

        ServerStorageSnapshotEntity serverStorageSnapshot =
                serverStorageSnapshotRepository.findById(id)
                        .orElseThrow(() ->
                                new IllegalStateException(
                                        "Server storage usage not found: id=" + id)
                        );

        List<MountPathStorageUsageDto> mountPathStorageUsages = serverStorageSnapshot.getMountPathStorageUsages()
                .stream()
                .map(mountPathStorageUsage -> new MountPathStorageUsageDto(
                        mountPathStorageUsage.getPath(),
                        mountPathStorageUsage.getTotalBytes(),
                        mountPathStorageUsage.getUsedBytes(),
                        mountPathStorageUsage.getUsedPercent()
                )).toList();



        return new ServerStorageSnapshotDto(
                serverStorageSnapshot.getSystemId(),
                serverStorageSnapshot.getSystemName(),
                serverStorageSnapshot.getServerIp(),
                serverStorageSnapshot.getServerName(),
                serverStorageSnapshot.getCollectedAt(),
                mountPathStorageUsages
        );
    }


    @Override
    public List<ServerStorageSnapshotDto> getLatestServerStorageSnapshots() {

        return serverStorageSnapshotQueryRepository.findLatestServerStorageSnapshots()
                .stream()
                .map(serverStorageSnapshot ->
                        {
                            List<MountPathStorageUsageDto> mountPathStorageUsages = serverStorageSnapshot.getMountPathStorageUsages()
                                    .stream()
                                    .map(mountPathStorageUsage -> new MountPathStorageUsageDto(
                                            mountPathStorageUsage.getPath(),
                                            mountPathStorageUsage.getTotalBytes(),
                                            mountPathStorageUsage.getUsedBytes(),
                                            mountPathStorageUsage.getUsedPercent()
                                    )).toList();

                            return new ServerStorageSnapshotDto(
                                    serverStorageSnapshot.getSystemId(),
                                    serverStorageSnapshot.getSystemName(),
                                    serverStorageSnapshot.getServerIp(),
                                    serverStorageSnapshot.getServerName(),
                                    serverStorageSnapshot.getCollectedAt(),
                                    mountPathStorageUsages
                            );
                        })
                        .toList();

    }


}

