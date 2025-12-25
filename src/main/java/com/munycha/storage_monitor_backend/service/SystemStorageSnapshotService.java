package com.munycha.storage_monitor_backend.service;

import com.munycha.storage_monitor_backend.dto.SystemStorageSnapshotDto;
import com.munycha.storage_monitor_backend.repository.SystemStorageSnapshotRepository;

public interface SystemStorageSnapshotService {
   SystemStorageSnapshotDto getSystemStorageSnapshot(Long id);

}
