package com.munycha.storagemonitor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MountPathStorageUsageDto {
    private String path;
    private Long totalBytes;
    private Long usedBytes;
    private Double usedPercent;
}
