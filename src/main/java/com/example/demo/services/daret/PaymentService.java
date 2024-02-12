package com.example.demo.services.daret;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.daret.PaymentRepository;
import com.example.demo.entities.daret.Payment;
import com.example.demo.entities.daret.Position;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getPaymentsByPositionAndPeriod(Long positionId, int period) {
        return paymentRepository.getPaymentsByPosition_IdAndPeriod(positionId, period);
    }
    public Payment createPayment(Payment payment) {
        // Ajoutez ici la logique nécessaire pour valider et créer le paiement
        // Vous pouvez également ajouter des vérifications, des calculs, etc.

        return paymentRepository.save(payment);
    }
    public boolean paymentExistsWithSamePositionAndPeriod(Long positionId, int period) {
        // Assuming you have a repository method for finding by positionId and period
        return paymentRepository.existsByPositionIdAndPeriod(positionId, period);
    }
 
    public List<Payment> getPaymentsByPositionId(Long positionId) {
        return paymentRepository.getPaymentsByPosition_Id(positionId);
    }

}

