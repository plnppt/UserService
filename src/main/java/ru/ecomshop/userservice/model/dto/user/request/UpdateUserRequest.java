package ru.ecomshop.userservice.model.dto.user.request;

import ru.ecomshop.userservice.model.entity.LoyaltyCard;
import ru.ecomshop.userservice.model.entity.enums.AccountRole;
import ru.ecomshop.userservice.model.entity.enums.AccountStatus;

import java.util.Date;
import java.util.Set;

public record UpdateUserRequest(
        String name,
        String surname,
        String patronymic,
        Date birthdate,
        String address,
        String phoneNumber,
        String email,
        Set<AccountRole> accountRoles,
        AccountStatus accountStatus,
        LoyaltyCard loyaltyCard) {
}
