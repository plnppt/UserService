package ru.ecomshop.userservice.model.dto.user.request;

import ru.ecomshop.userservice.model.entity.enums.AccountRole;

import java.util.Date;
import java.util.Set;

public record CreateUserRequest (
        String name,
        String surname,
        String patronymic,
        Date birthdate,
        String address,
        String phoneNumber,
        String email,
        Set<AccountRole> accountRoles) {
}
