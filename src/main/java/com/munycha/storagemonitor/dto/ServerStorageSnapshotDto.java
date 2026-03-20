package com.munycha.storagemonitor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServerStorageSnapshotDto {
    private String systemId;
    private String systemName;
    private String serverIp;
    private String serverName;
    private LocalDateTime collectedAt;
    private List<MountPathStorageUsageDto> mountPathStorageUsages;
}
