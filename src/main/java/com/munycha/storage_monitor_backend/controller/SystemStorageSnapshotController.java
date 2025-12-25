package com.munycha.storage_monitor_backend.controller;

import com.munycha.storage_monitor_backend.dto.SystemStorageSnapshotDto;
import com.munycha.storage_monitor_backend.service.SystemStorageSnapshotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/system-storage-snapshot")
public class SystemStorageSnapshotController {
    private final SystemStorageSnapshotService systemStorageSnapshotService;

    public SystemStorageSnapshotController(SystemStorageSnapshotService systemStorageSnapshotService) {
        this.systemStorageSnapshotService = systemStorageSnapshotService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SystemStorageSnapshotDto> getSnapshot(@PathVariable Long id){
        return ResponseEntity.ok(this.systemStorageSnapshotService.getSystemStorageSnapshot(id));
    }
}
