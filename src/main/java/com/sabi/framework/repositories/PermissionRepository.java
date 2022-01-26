package com.sabi.framework.repositories;

import com.sabi.framework.models.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long>{
    Permission findByName(String name);



    @Query("SELECT p FROM Permission p WHERE ((:name IS NULL) OR (:name IS NOT NULL AND p.name like %:name%)) " +
            " AND ((:isActive IS NULL) OR (:isActive IS NOT NULL AND p.isActive = :isActive)) order by p.id")
    Page<Permission> findFunctions(@Param("name")String name,
                                   @Param("isActive")Boolean isActive,
                                   Pageable pageable);

    List<Permission> findByIsActive(Boolean isActive);




    @Query(value ="SELECT p.name FROM Permission p  INNER JOIN RolePermission rp  ON p.id = rp.permissionId\n" +
            "      INNER JOIN UserRole ur  ON rp.roleId = ur.roleId\n" +
            "    WHERE ur.userId =?1")
    List<Object[]> getPermissionsByUserId(Long userId);


}
