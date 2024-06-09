package ru.ecomshop.userservice.repository.loyaltyCard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ecomshop.userservice.model.entity.LoyaltyCard;
import ru.ecomshop.userservice.model.entity.User;
import ru.ecomshop.userservice.model.entity.enums.LoyaltyCardStatus;

import java.util.Optional;

@Repository
public interface LoyaltyCardRepository extends JpaRepository<LoyaltyCard, Long> {

    Optional<LoyaltyCard> findByOwnerIdAndStatus(Long ownerId, LoyaltyCardStatus loyaltyCardStatus);
    Optional<LoyaltyCard> findByIdAndStatus(Long id, LoyaltyCardStatus loyaltyCardStatus);
}
