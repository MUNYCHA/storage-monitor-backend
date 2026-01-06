package com.munycha.storage_monitor_backend.service;

import com.munycha.storage_monitor_backend.dto.ServerStorageUsageDto;

import java.util.List;

public interface ServerStorageUsageService {

   List<ServerStorageUsageDto> getSystemStorageUsages();
   ServerStorageUsageDto getSystemStorageUsageById(Long id);
   List<ServerStorageUsageDto> getLatestSystemStorageUsages();
}
