package com.example.demo.controllers.daret;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.daret.Payment;
import com.example.demo.entities.daret.Position;
import com.example.demo.services.daret.PaymentService;
import com.example.demo.services.daret.PositionService;

@RestController
@RequestMapping("/api/payments")
public class PaymentRestController {
    
    @Autowired
    private PaymentService paymentService;
    
    @Autowired
    private PositionService positionService;

    @GetMapping("/byPeriod/{positionId}/{period}")
    public ResponseEntity<List<Payment>> getPaymentsByPeriod(
            @PathVariable Long positionId,
            @PathVariable int period) {
        Optional<Position> positionOptional = positionService.getPositionById(positionId);

        if (positionOptional.isPresent()) {
            Position position = positionOptional.get();
            List<Payment> paymentsByPeriod = paymentService.getPaymentsByPositionAndPeriod(position.getId(), period);

            return new ResponseEntity<>(paymentsByPeriod, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }    
    
    
    @PostMapping("/create/{positionId}")
    public ResponseEntity<Payment> createPayment(
            @PathVariable Long positionId,
            @RequestBody Payment payment) {

        // Verify if a payment with the same position and number of periods already exists
        if (paymentService.paymentExistsWithSamePositionAndPeriod(positionId, payment.getPeriod())) {
            // If a payment already exists, return a conflict response
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        // If no existing payment is found, proceed to create a new payment
        Payment createdPayment = paymentService.createPayment(payment);

        if (createdPayment != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPayment);
        } else {
            // If creating a payment fails, return a bad request response
            return ResponseEntity.badRequest().body(null);
        }
    }

    
    
    @GetMapping("/byPositionId")
    public ResponseEntity<List<Payment>> getPaymentsByPositionId(
            @RequestParam Long userId,
            @RequestParam Long daretId) {
        Optional<Position> positionOptional = positionService.getPositionByUserIdAndDaretId(userId, daretId);

        if (positionOptional.isPresent()) {
            Position position = positionOptional.get();
            List<Payment> paymentsByPositionId = paymentService.getPaymentsByPositionId(position.getId());
            
            return new ResponseEntity<>(paymentsByPositionId, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
