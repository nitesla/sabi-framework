package com.sabi.framework.repositories;


import com.sabi.framework.models.RolePermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {

    RolePermission findByRoleId(Long role_id);

    @Query("SELECT p FROM RolePermission p WHERE ((:roleId IS NULL) OR (:roleId IS NOT NULL AND p.roleId = :roleId)) " +
            " AND ((:isActive IS NULL) OR (:isActive IS NOT NULL AND p.isActive = :isActive)) order by p.id")
    Page<RolePermission> findRolePermission(@Param("roleId") Long roleId,
                                            @Param("isActive") Boolean isActive,
                                            Pageable Pageable);
    boolean existsByRoleIdAndPermissionId(Long roleId, Long permissionId);

    List<RolePermission> findByRoleIdAndIsActive(Long roleId, Boolean isActive);

    @Query("SELECT rp FROM RolePermission rp WHERE rp.roleId=?1 order by rp.id" )
    List<RolePermission> getPermissionsByRole(Long roleId);
}
