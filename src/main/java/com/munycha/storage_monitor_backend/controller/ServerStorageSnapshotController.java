package com.munycha.storage_monitor_backend.controller;

import com.munycha.storage_monitor_backend.dto.ServerStorageSnapshotDto;
import com.munycha.storage_monitor_backend.service.ServerStorageSnapshotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/server-storage-usage")
public class ServerStorageSnapshotController {

    private final ServerStorageSnapshotService serverStorageSnapshotService;

    @Autowired
    public ServerStorageSnapshotController(ServerStorageSnapshotService serverStorageSnapshotService) {
        this.serverStorageSnapshotService = serverStorageSnapshotService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ServerStorageSnapshotDto>> getServerStorageSnapshots() {
        return ResponseEntity.ok(
                serverStorageSnapshotService.getServerStorageSnapshots()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServerStorageSnapshotDto> getServerStorageSnapshotById(@PathVariable Long id){
        return ResponseEntity.ok(this.serverStorageSnapshotService.getServerStorageSnapshotById(id));
    }

    @GetMapping("/latest")
    public ResponseEntity<List<ServerStorageSnapshotDto>> getLatestServerStorageSnapshots(){
        return ResponseEntity.ok(this.serverStorageSnapshotService.getLatestServerStorageSnapshots());
    }
}
