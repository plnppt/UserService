package ru.ecomshop.userservice.model.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
public enum LoyaltyCardStatus {

    ACTIVE("Active", "Карта активна"),
    DISABLE("Disable", "Карта деактивирована");

    private final String statusCode;
    private final String description;

    LoyaltyCardStatus(String statusCode, String description) {
        this.statusCode = statusCode;
        this.description = description;
    }

}
