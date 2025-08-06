package com.mycompany.loyalty.repository;

import com.mycompany.loyalty.model.LoyaltyCard;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LoyaltyCardRepository extends JpaRepository<LoyaltyCard, Long> {

    Optional<LoyaltyCard> findByCardNumber(String cardNumber);
    Optional<LoyaltyCard> findByCustomerId(Long customerId);
}
