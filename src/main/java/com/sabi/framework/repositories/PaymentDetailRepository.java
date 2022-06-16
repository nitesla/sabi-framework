package com.sabi.framework.repositories;

import com.sabi.framework.models.PaymentDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PaymentDetailRepository extends JpaRepository<PaymentDetails, Long> {
    PaymentDetails findByPaymentReference(String paymentReference);
    PaymentDetails findByIdAndPaymentReference(Long id, String paymentReference);

    @Query(value = "Select * from PaymentDetails where " +
            "((:orderId IS NULL) OR (:orderId IS NOT NULL AND PaymentDetails.orderId = :orderId))", nativeQuery = true)
    Page<PaymentDetails> paymentHistory(@Param("orderId") Long orderId,
                             Pageable pageable);

    List<PaymentDetails> findAllByOrderId(Long orderId);
}
