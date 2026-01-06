package com.munycha.storage_monitor_backend.repository.query;

import com.munycha.storage_monitor_backend.entity.ServerPathStorageUsageEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ServerPathStorageUsageQueryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<ServerPathStorageUsageEntity> findBySnapshotId(Long snapshotId) {

        return entityManager
                .createNativeQuery(
                        """
                        SELECT *
                        FROM server_path_storage_usage
                        WHERE server_storage_usage_id = :snapshotId
                        """,
                        ServerPathStorageUsageEntity.class
                )
                .setParameter("snapshotId", snapshotId)
                .getResultList();
    }
}
