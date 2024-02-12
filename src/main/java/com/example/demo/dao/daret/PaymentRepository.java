package com.example.demo.dao.daret;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.daret.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> getPaymentsByPosition_IdAndPeriod(Long positionId, int period);
    boolean existsByPositionIdAndPeriod(Long participationId, int period);
    List<Payment> getPaymentsByPosition_Id(Long participationId);

}
