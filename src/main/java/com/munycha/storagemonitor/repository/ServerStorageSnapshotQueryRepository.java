package com.munycha.storagemonitor.repository;

import com.munycha.storagemonitor.entity.ServerStorageSnapshot;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ServerStorageSnapshotQueryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<ServerStorageSnapshot> findLatestServerStorageSnapshots() {
        return entityManager
                .createQuery(
                        """
                            SELECT DISTINCT s
                            FROM ServerStorageSnapshot s
                            LEFT JOIN FETCH s.mountPathStorageUsages
                            WHERE s.collectedAt = (
                                SELECT MAX(s2.collectedAt)
                                FROM ServerStorageSnapshot s2
                                WHERE s2.serverIp = s.serverIp
                                  AND s2.systemId = s.systemId
                            )
                            ORDER BY s.systemName ASC, s.serverIp ASC
                        """,
                        ServerStorageSnapshot.class
                )
                .getResultList();
    }
}
