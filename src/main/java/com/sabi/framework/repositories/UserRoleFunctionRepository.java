package com.sabi.framework.repositories;

import com.sabi.framework.models.Function;
import com.sabi.framework.models.UserRoleFunction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleFunctionRepository extends JpaRepository<UserRoleFunction, Long> {
    UserRoleFunction findByFunction(Function function);
}
