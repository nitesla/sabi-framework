package com.sabi.framework.repositories;

import com.sabi.framework.models.PreviousPasswords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreviousPasswordRepository extends JpaRepository<PreviousPasswords, Long> {


    @Query(value = "SELECT TOP 4 FROM previouspasswords p WHERE p.userId=? order by p.createdDate desc",
            nativeQuery = true)
    List<PreviousPasswords> previousPassword(Long useerId);


}
