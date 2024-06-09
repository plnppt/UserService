package ru.ecomshop.userservice.model.mapper.loayaltyCard;

import org.mapstruct.Mapper;
import ru.ecomshop.userservice.model.dto.loayaltyCard.LoyaltyCardResponse;
import ru.ecomshop.userservice.model.entity.LoyaltyCard;

@Mapper
public interface LoyaltyCardMapper {

    LoyaltyCardResponse map(LoyaltyCard loyaltyCard);
    LoyaltyCard map(LoyaltyCardResponse dto);
}
