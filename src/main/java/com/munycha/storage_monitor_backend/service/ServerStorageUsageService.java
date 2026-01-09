package com.munycha.storage_monitor_backend.service;

import com.munycha.storage_monitor_backend.dto.ServerStorageUsageDto;

import java.util.List;

public interface ServerStorageUsageService {

   List<ServerStorageUsageDto> getServerStorageUsages();
   ServerStorageUsageDto getServerStorageUsageById(Long id);
   List<ServerStorageUsageDto> getLatestServerStorageUsages();
}
