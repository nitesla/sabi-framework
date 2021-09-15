package com.sabi.framework.repositories;

import com.sabi.framework.models.ExternalToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ExternalTokenRepository extends JpaRepository<ExternalToken, Long> {
}
