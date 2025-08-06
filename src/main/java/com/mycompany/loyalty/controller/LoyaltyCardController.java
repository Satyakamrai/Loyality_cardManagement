package com.mycompany.loyalty.controller;

import com.mycompany.loyalty.model.LoyaltyCard;
import com.mycompany.loyalty.service.LoyaltyCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cards")
public class LoyaltyCardController {

    @Autowired
    private LoyaltyCardService loyaltyCardService;

    @PostMapping("/new/{customerId}")
    public ResponseEntity<LoyaltyCard> createNewCard(@PathVariable Long customerId) {
        LoyaltyCard newCard = loyaltyCardService.createNewCard(customerId);
        return ResponseEntity.ok(newCard);
    }
    
    @GetMapping("/{customerId}")
    public ResponseEntity<LoyaltyCard> getCardByCustomerId(@PathVariable Long customerId) {
        try {
            LoyaltyCard card = loyaltyCardService.getCardByCustomerId(customerId);
            return ResponseEntity.ok(card);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{cardNumber}/add-points")
    public ResponseEntity<LoyaltyCard> addPointsToCard(
            @PathVariable String cardNumber,
            @RequestParam int points) {
        try {
            LoyaltyCard updatedCard = loyaltyCardService.addPoints(cardNumber, points);
            return ResponseEntity.ok(updatedCard);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
