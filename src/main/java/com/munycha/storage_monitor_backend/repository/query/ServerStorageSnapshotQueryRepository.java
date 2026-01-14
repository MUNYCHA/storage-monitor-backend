package com.munycha.storage_monitor_backend.repository.query;


import com.munycha.storage_monitor_backend.entity.ServerStorageSnapshotEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ServerStorageSnapshotQueryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<ServerStorageSnapshotEntity> findLatestServerStorageSnapshots() {

        return entityManager
                .createQuery(
                        """
                            SELECT DISTINCT s
                                    FROM ServerStorageSnapshotEntity s
                                    LEFT JOIN FETCH s.mountPathStorageUsages
                                    WHERE s.collectedAt = (
                                        SELECT MAX(s2.collectedAt)
                                        FROM ServerStorageSnapshotEntity s2
                                        WHERE s2.serverIp = s.serverIp
                                          AND s2.systemId = s.systemId
                                    )
                                    ORDER BY s.systemName ASC, s.serverIp ASC
                        """,
                        ServerStorageSnapshotEntity.class
                )
                .getResultList();
    }



}
