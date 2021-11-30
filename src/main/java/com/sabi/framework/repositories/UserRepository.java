package com.sabi.framework.repositories;

import com.sabi.framework.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByPhone(String phone);

    User findByUsername(String username);

    User findByEmailOrPhone (String email, String phone);

    User findByResetToken (String resetToken);

    List<User> findByIsActive(Boolean isActive);

    User findByTransactionPin (String transactionPin);

    User findByFirstName(String firstName);
    User findByLastName(String lastName);

    User findByFirstNameAndLastName(String firstName, String lastName);






    @Query("SELECT u FROM User u WHERE ((:firstName IS NULL) OR (:firstName IS NOT NULL AND u.firstName = :firstName))" +
            " AND ((:lastName IS NULL) OR (:lastName IS NOT NULL AND u.lastName = :lastName))"+
            " AND ((:phone IS NULL) OR (:phone IS NOT NULL AND u.phone = :phone))"+
            " AND ((:isActive IS NULL) OR (:isActive IS NOT NULL AND u.isActive = :isActive))"+
            " AND ((:email IS NULL) OR (:email IS NOT NULL AND u.email = :email))")
    Page<User> findUsers(@Param("firstName")String firstName,
                                @Param("lastName")String lastName,
                                @Param("phone")String phone,
                                @Param("isActive")Boolean isActive,
                                @Param("email")String email,
                                Pageable pageable);




    @Query("SELECT u FROM User u WHERE ((:firstName IS NULL) OR (:firstName IS NOT NULL AND u.firstName = :firstName))" +
            " AND ((:lastName IS NULL) OR (:lastName IS NOT NULL AND u.lastName = :lastName))")
    Page<User> findAgentUser(@Param("firstName")String firstName,
                         @Param("lastName")String lastName,
                         Pageable pageable);




    @Query("SELECT u FROM User u WHERE ((:firstName IS NULL) OR (:firstName IS NOT NULL AND u.firstName = :firstName))" +
            " AND ((:phone IS NULL) OR (:phone IS NOT NULL AND u.phone = :phone))"+
            " AND ((:email IS NULL) OR (:email IS NOT NULL AND u.email = :email))"+
            " AND ((:username IS NULL) OR (:username IS NOT NULL AND u.username = :username))"+
            " AND ((:roleId IS NULL) OR (:roleId IS NOT NULL AND u.roleId = :roleId))"+
            " AND ((:clientId IS NULL) OR (:clientId IS NOT NULL AND u.clientId = :clientId))"+
            " AND ((:lastName IS NULL) OR (:lastName IS NOT NULL AND u.lastName = :lastName))")
    Page<User> findByClientId(@Param("firstName")String firstName,
                              @Param("phone")String phone,
                              @Param("email")String email,
                              @Param("username")String username,
                              @Param("roleId")Long roleId,
                              @Param("clientId")Long clientId,
                              @Param("lastName")String lastName,
                             Pageable pageable);


    @Query("SELECT u FROM User u WHERE ((:isActive IS NULL) OR (:isActive IS NOT NULL AND u.isActive = :isActive))" +
            " AND ((:clientId IS NULL) OR (:clientId IS NOT NULL AND u.clientId = :clientId))")
    List<User> findByIsActiveAndClientId(Boolean isActive,Long clientId);

    User findByClientId (Long clientId);
}
