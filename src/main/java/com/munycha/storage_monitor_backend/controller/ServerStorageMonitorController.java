package com.munycha.storage_monitor_backend.controller;

import com.munycha.storage_monitor_backend.dto.ServerStorageUsageDto;
import com.munycha.storage_monitor_backend.service.ServerStorageUsageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/system-storage-snapshot")
public class ServerStorageMonitorController {
    private final ServerStorageUsageService systemStorageSnapshotService;

    public ServerStorageMonitorController(ServerStorageUsageService systemStorageSnapshotService) {
        this.systemStorageSnapshotService = systemStorageSnapshotService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ServerStorageUsageDto>> getSystemStorageUsages() {
        return ResponseEntity.ok(
                systemStorageSnapshotService.getSystemStorageUsages()
        );
    }




    @GetMapping("/{id}")
    public ResponseEntity<ServerStorageUsageDto> getSnapshot(@PathVariable Long id){
        return ResponseEntity.ok(this.systemStorageSnapshotService.getSystemStorageUsageById(id));
    }

    @GetMapping("/latest")
    public ResponseEntity<List<ServerStorageUsageDto>> getLatestSnapshot(){
        return ResponseEntity.ok(this.systemStorageSnapshotService.getLatestSystemStorageUsages());
    }
}
