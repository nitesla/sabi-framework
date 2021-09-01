package com.sabi.framework.repositories;

import com.sabi.framework.models.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);


    @Query("SELECT r FROM Role r WHERE ((:name IS NULL) OR (:name IS NOT NULL AND r.name = :name))")
    Page<Role> findRoles(@Param("name")String name, Pageable pageable);
}
