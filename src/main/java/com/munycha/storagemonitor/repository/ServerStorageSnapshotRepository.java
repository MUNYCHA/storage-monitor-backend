package com.munycha.storagemonitor.repository;

import com.munycha.storagemonitor.entity.ServerStorageSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ServerStorageSnapshotRepository extends JpaRepository<ServerStorageSnapshot, Long> {

    @Query("SELECT DISTINCT s FROM ServerStorageSnapshot s LEFT JOIN FETCH s.mountPathStorageUsages ORDER BY s.systemName ASC, s.serverIp ASC")
    List<ServerStorageSnapshot> findAllWithMountPaths();

    @Query("SELECT s FROM ServerStorageSnapshot s LEFT JOIN FETCH s.mountPathStorageUsages WHERE s.id = :id")
    Optional<ServerStorageSnapshot> findByIdWithMountPaths(Long id);
}
