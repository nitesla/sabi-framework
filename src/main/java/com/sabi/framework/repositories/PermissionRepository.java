package com.sabi.framework.repositories;

import com.sabi.framework.models.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission findByName(String name);



    @Query("SELECT f FROM Permission f WHERE ((:name IS NULL) OR (:name IS NOT NULL AND f.name = :name))")
    Page<Permission> findFunctions(@Param("name")String name, Pageable pageable);
}
