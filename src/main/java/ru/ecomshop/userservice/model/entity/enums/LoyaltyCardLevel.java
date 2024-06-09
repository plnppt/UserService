package ru.ecomshop.userservice.model.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum LoyaltyCardLevel {

    BRONZE("Bronze", 3.0, "Базовый статус при активации карты"),
    SILVER("Silver", 4.5, "При сумме покупок от 5 000 р."),
    GOLD("Gold", 6.0, "При сумме покупок от 20 000 р."),
    PLATINUM("Platinum", 10.0, "При сумме покупок от 100 000 р.");

    private final String loyaltyCardLevelCode;
    private final Double discountPercentage;
    private final String description;

    LoyaltyCardLevel(String loyaltyCardLevelCode, Double discountPercentage, String description) {
        this.loyaltyCardLevelCode = loyaltyCardLevelCode;
        this.discountPercentage = discountPercentage;
        this.description = description;
    }

    public LoyaltyCardLevel getNext() {
        int currentIndex = ordinal();
        if (currentIndex == values().length - 1) {
            throw new RuntimeException("Уровень карты лояльности уже максимальный.");
        }
        return values()[currentIndex + 1];
    }

    public LoyaltyCardLevel getPrevious() {
        int currentIndex = ordinal();
        if (currentIndex == 0) {
            throw new RuntimeException("Уровень карты лояльности уже минимальный.");
        }
        return values()[currentIndex - 1];
    }

}
