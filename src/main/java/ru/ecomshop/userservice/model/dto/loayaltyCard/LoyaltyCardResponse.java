package ru.ecomshop.userservice.model.dto.loayaltyCard;

import ru.ecomshop.userservice.model.dto.user.response.UserResponse;
import ru.ecomshop.userservice.model.entity.enums.LoyaltyCardLevel;
import ru.ecomshop.userservice.model.entity.enums.LoyaltyCardStatus;

public record LoyaltyCardResponse(
     Long id,
     String cardNumber,
     UserResponse owner,
     LoyaltyCardLevel level,
     LoyaltyCardStatus status) {
}
