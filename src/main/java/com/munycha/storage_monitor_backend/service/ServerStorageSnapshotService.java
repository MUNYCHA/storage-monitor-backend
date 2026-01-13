package com.munycha.storage_monitor_backend.service;

import com.munycha.storage_monitor_backend.dto.ServerStorageSnapshotDto;

import java.util.List;

public interface ServerStorageSnapshotService {

   List<ServerStorageSnapshotDto> getServerStorageSnapshots();
   ServerStorageSnapshotDto getServerStorageSnapshotById(Long id);
   List<ServerStorageSnapshotDto> getLatestServerStorageSnapshots();
}
