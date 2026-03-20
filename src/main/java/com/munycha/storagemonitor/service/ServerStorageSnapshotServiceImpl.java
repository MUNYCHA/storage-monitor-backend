package com.munycha.storagemonitor.service;

import com.munycha.storagemonitor.dto.MountPathStorageUsageDto;
import com.munycha.storagemonitor.dto.ServerStorageSnapshotDto;
import com.munycha.storagemonitor.entity.ServerStorageSnapshot;
import com.munycha.storagemonitor.repository.ServerStorageSnapshotQueryRepository;
import com.munycha.storagemonitor.repository.ServerStorageSnapshotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ServerStorageSnapshotServiceImpl implements ServerStorageSnapshotService {

    private final ServerStorageSnapshotRepository serverStorageSnapshotRepository;
    private final ServerStorageSnapshotQueryRepository serverStorageSnapshotQueryRepository;

    @Autowired
    public ServerStorageSnapshotServiceImpl(ServerStorageSnapshotRepository serverStorageSnapshotRepository,
                                            ServerStorageSnapshotQueryRepository serverStorageSnapshotQueryRepository) {
        this.serverStorageSnapshotRepository = serverStorageSnapshotRepository;
        this.serverStorageSnapshotQueryRepository = serverStorageSnapshotQueryRepository;
    }

    @Override
    public List<ServerStorageSnapshotDto> getServerStorageSnapshots() {
        return serverStorageSnapshotRepository.findAllWithMountPaths()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public ServerStorageSnapshotDto getServerStorageSnapshotById(Long id) {
        ServerStorageSnapshot snapshot = serverStorageSnapshotRepository.findByIdWithMountPaths(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Server storage snapshot not found: id=" + id));
        return toDto(snapshot);
    }

    @Override
    public List<ServerStorageSnapshotDto> getLatestServerStorageSnapshots() {
        return serverStorageSnapshotQueryRepository.findLatestServerStorageSnapshots()
                .stream()
                .map(this::toDto)
                .toList();
    }

    private ServerStorageSnapshotDto toDto(ServerStorageSnapshot snapshot) {
        List<MountPathStorageUsageDto> mountPathStorageUsages = snapshot.getMountPathStorageUsages()
                .stream()
                .map(usage -> new MountPathStorageUsageDto(
                        usage.getPath(),
                        usage.getTotalBytes(),
                        usage.getUsedBytes(),
                        usage.getUsedPercent()
                ))
                .toList();

        return new ServerStorageSnapshotDto(
                snapshot.getSystemId(),
                snapshot.getSystemName(),
                snapshot.getServerIp(),
                snapshot.getServerName(),
                snapshot.getCollectedAt(),
                mountPathStorageUsages
        );
    }
}
