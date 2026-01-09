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
@RequestMapping("/api/server-storage-usage")
public class ServerStorageUsageController {
    private final ServerStorageUsageService serverStorageUsageService;


    public ServerStorageUsageController(ServerStorageUsageService serverStorageUsageService) {
        this.serverStorageUsageService = serverStorageUsageService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ServerStorageUsageDto>> getServerStorageUsages() {
        return ResponseEntity.ok(
                serverStorageUsageService.getServerStorageUsages()
        );
    }




    @GetMapping("/{id}")
    public ResponseEntity<ServerStorageUsageDto> getServerStorageUsageById(@PathVariable Long id){
        return ResponseEntity.ok(this.serverStorageUsageService.getServerStorageUsageById(id));
    }

    @GetMapping("/latest")
    public ResponseEntity<List<ServerStorageUsageDto>> getLatestServerStorageUsages(){
        return ResponseEntity.ok(this.serverStorageUsageService.getLatestServerStorageUsages());
    }
}
