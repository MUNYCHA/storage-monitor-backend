package com.munycha.storage_monitor_backend.service;

import com.munycha.storage_monitor_backend.dto.MountPathStorageUsageDto;
import com.munycha.storage_monitor_backend.dto.ServerStorageUsageDto;
import com.munycha.storage_monitor_backend.entity.ServerStorageUsageEntity;
import com.munycha.storage_monitor_backend.repository.crud.ServerStorageUsageRepository;
import com.munycha.storage_monitor_backend.repository.query.MountPathStorageUsageQueryRepository;
import com.munycha.storage_monitor_backend.repository.query.ServerStorageUsageQueryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ServerStorageUsageServiceImpl
        implements ServerStorageUsageService {

    private final ServerStorageUsageRepository serverStorageUsageRepository;
    private final ServerStorageUsageQueryRepository serverStorageUsageQueryRepository;
    private final MountPathStorageUsageQueryRepository mountPathStorageUsageQueryRepository;

    public ServerStorageUsageServiceImpl(ServerStorageUsageRepository serverStorageUsageRepository, ServerStorageUsageQueryRepository serverStorageUsageQueryRepository, MountPathStorageUsageQueryRepository mountPathStorageUsageQueryRepository) {
        this.serverStorageUsageRepository = serverStorageUsageRepository;
        this.serverStorageUsageQueryRepository = serverStorageUsageQueryRepository;
        this.mountPathStorageUsageQueryRepository = mountPathStorageUsageQueryRepository;
    }

    @Override
    public List<ServerStorageUsageDto> getServerStorageUsages() {

        return serverStorageUsageRepository.findAll()
                .stream()
                .map(serverUsage -> {

                    List<MountPathStorageUsageDto> mountPathUsages =
                            mountPathStorageUsageQueryRepository
                                    .findByServerStorageUsageId(serverUsage.getId())
                                    .stream()
                                    .map(mountPathUsage -> new MountPathStorageUsageDto(
                                            mountPathUsage.getPath(),
                                            mountPathUsage.getTotalBytes(),
                                            mountPathUsage.getUsedBytes(),
                                            mountPathUsage.getUsedPercent()
                                    ))
                                    .toList();

                    return new ServerStorageUsageDto(
                            serverUsage.getSystemId(),
                            serverUsage.getSystemName(),
                            serverUsage.getServerIp(),
                            serverUsage.getServerName(),
                            serverUsage.getCollectedAt(),
                            mountPathUsages
                    );
                })
                .toList();
    }




    @Override
    public ServerStorageUsageDto getServerStorageUsageById(Long id) {

        ServerStorageUsageEntity serverStorageUsage =
                serverStorageUsageRepository.findById(id)
                        .orElseThrow(() ->
                                new IllegalStateException(
                                        "Server storage usage not found: id=" + id)
                        );

        return toDto(
                serverStorageUsage,
                mapMountPathStorageUsages(serverStorageUsage.getId())
        );
    }


    @Override
    public List<ServerStorageUsageDto> getLatestServerStorageUsages() {

        return serverStorageUsageQueryRepository.findLatestServerStorageUsages()
                .stream()
                .map(snapshot ->
                        toDto(
                                snapshot,
                                mapMountPathStorageUsages(snapshot.getId())
                        )
                )
                .toList();
    }


    private List<MountPathStorageUsageDto> mapMountPathStorageUsages(Long serverStorageUsageId) {
        return mountPathStorageUsageQueryRepository
                .findByServerStorageUsageId(serverStorageUsageId)
                .stream()
                .map(mountPathUsage -> new MountPathStorageUsageDto(
                        mountPathUsage.getPath(),
                        mountPathUsage.getTotalBytes(),
                        mountPathUsage.getUsedBytes(),
                        mountPathUsage.getUsedPercent()
                ))
                .toList();
    }


    private ServerStorageUsageDto toDto(
            ServerStorageUsageEntity serverStorageUsage,
            List<MountPathStorageUsageDto> mountPathStorageUsages
    ) {
        return new ServerStorageUsageDto(
                serverStorageUsage.getSystemId(),
                serverStorageUsage.getSystemName(),
                serverStorageUsage.getServerIp(),
                serverStorageUsage.getServerName(),
                serverStorageUsage.getCollectedAt(),
                mountPathStorageUsages
        );
    }

}

