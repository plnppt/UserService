package ru.ecomshop.userservice.model.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
public enum AccountRole {

    USER("User", "Обычный пользователь"),
    DELIVERYMAN("Deliveryman", "Доставщик"),
    ADMIN("Administrator", "Администратор системы");

    private final String roleCode;
    private final String description;

    AccountRole(String roleCode, String description) {
        this.roleCode = roleCode;
        this.description = description;
    }
}
