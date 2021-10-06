package com.sabi.framework.repositories;


import com.sabi.framework.models.RolePermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {

    RolePermission findByRoleId(Long role_id);

    @Query("SELECT p FROM RolePermission p WHERE ((:roleId IS NULL) OR (:roleId IS NOT NULL AND p.roleId = :roleId)) " +
            " AND ((:isActive IS NULL) OR (:isActive IS NOT NULL AND p.isActive = :isActive))")
    Page<RolePermission> findRolePermission(@Param("roleId") Long roleId,
                                            @Param("isActive") Boolean isActive,
                                            Pageable Pageable);
    boolean existsByRoleIdAndPermissionId(Long roleId, Long permissionId);
}
