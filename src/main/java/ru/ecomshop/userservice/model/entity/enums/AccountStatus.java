package ru.ecomshop.userservice.model.entity.enums;

import lombok.Getter;


@Getter
public enum AccountStatus {

    CREATED("Created", "Созданный, но не подтверженный аккаунт"),
    CONFIRMED("Confirmed", "Подтвержденный аккаунт"),
    BLOCKED("Blocked", "Заблокированный аккаунт");

    private final String statusCode;
    private final String description;

    AccountStatus(String statusCode, String description) {
        this.statusCode = statusCode;
        this.description = description;
    }

}
