package com.munycha.storage_monitor_backend.repository.query;


import com.munycha.storage_monitor_backend.entity.ServerStorageUsageEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ServerStorageUsageQueryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<ServerStorageUsageEntity> findLatestSystemStorageUsages() {

        return entityManager
                .createQuery(
                        """
                        SELECT s
                        FROM ServerStorageUsageEntity s
                        WHERE s.collectedAt = (
                            SELECT MAX(s2.collectedAt)
                            FROM ServerStorageUsageEntity s2
                            WHERE s2.serverIp = s.serverIp
                              AND s2.systemId = s.systemId
                        )
                        """,
                        ServerStorageUsageEntity.class
                )
                .getResultList();
    }


}
