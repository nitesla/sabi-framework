package com.sabi.framework.repositories;

import com.sabi.framework.models.Function;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FunctionRepository extends JpaRepository<Function, Long> {
    Function findByName(String name);
}
