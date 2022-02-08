package com.sabi.framework.repositories;

import com.sabi.framework.models.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDetailRepository extends JpaRepository<PaymentDetails, Long> {
    PaymentDetails findByPaymentReference(String paymentReference);
    PaymentDetails findByIdAndPaymentReference(Long id, String paymentReference);
}
