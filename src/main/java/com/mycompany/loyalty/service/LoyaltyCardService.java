package com.mycompany.loyalty.service;

import com.mycompany.loyalty.model.LoyaltyCard;
import com.mycompany.loyalty.repository.LoyaltyCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
public class LoyaltyCardService {

    @Autowired
    private LoyaltyCardRepository loyaltyCardRepository;

    public LoyaltyCard createNewCard(Long customerId) {
        // Simple card number generation logic
        String cardNumber = "LC" + System.currentTimeMillis(); 
        LoyaltyCard card = new LoyaltyCard(cardNumber, customerId);
        return loyaltyCardRepository.save(card);
    }

    public LoyaltyCard getCardByCustomerId(Long customerId) {
        return loyaltyCardRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new RuntimeException("Card not found for customer: " + customerId));
    }
    
    public LoyaltyCard getCardByCardNumber(String cardNumber) {
        return loyaltyCardRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new RuntimeException("Card not found: " + cardNumber));
    }

    @Transactional
    public LoyaltyCard addPoints(String cardNumber, int pointsToAdd) {
        if (pointsToAdd <= 0) {
            throw new IllegalArgumentException("Points to add must be positive.");
        }
        
        LoyaltyCard card = getCardByCardNumber(cardNumber);
        card.setPoints(card.getPoints() + pointsToAdd);
        return loyaltyCardRepository.save(card);
    }
}
