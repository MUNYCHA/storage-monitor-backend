package com.munycha.storagemonitor.service;

import com.munycha.storagemonitor.dto.ServerStorageSnapshotDto;

import java.util.List;

public interface ServerStorageSnapshotService {

    List<ServerStorageSnapshotDto> getServerStorageSnapshots();

    ServerStorageSnapshotDto getServerStorageSnapshotById(Long id);

    List<ServerStorageSnapshotDto> getLatestServerStorageSnapshots();
}
