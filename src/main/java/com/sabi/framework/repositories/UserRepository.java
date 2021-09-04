package com.sabi.framework.repositories;

import com.sabi.framework.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByPhone(String phone);

    User findByEmailAndPhone (String email, String phone);

    User findByResetToken (String resetToken);

    @Query("SELECT u FROM User u WHERE ((:firstName IS NULL) OR (:firstName IS NOT NULL AND u.firstName = :firstName))" +
            " AND ((:lastName IS NULL) OR (:lastName IS NOT NULL AND u.lastName = :lastName))"+
            " AND ((:phone IS NULL) OR (:phone IS NOT NULL AND u.phone = :phone))"+
            " AND ((:email IS NULL) OR (:email IS NOT NULL AND u.email = :email))")
    Page<User> findUsers(@Param("firstName")String firstName,
                                @Param("lastName")String lastName,
                                @Param("phone")String phone,
                                @Param("email")String email,
                                Pageable pageable);


}
