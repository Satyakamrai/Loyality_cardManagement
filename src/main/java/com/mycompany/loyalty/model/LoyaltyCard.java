package com.mycompany.loyalty.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoyaltyCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String cardNumber;
    private Long customerId;
    private int points;

    public LoyaltyCard(String cardNumber, Long customerId) {
        this.cardNumber = cardNumber;
        this.customerId = customerId;
        this.points = 0; // New card starts with 0 points
    }
}
