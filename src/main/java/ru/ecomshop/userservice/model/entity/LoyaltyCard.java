package ru.ecomshop.userservice.model.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.ecomshop.userservice.model.entity.enums.LoyaltyCardLevel;
import ru.ecomshop.userservice.model.entity.enums.LoyaltyCardStatus;

import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "loyalty_cards")
public class LoyaltyCard {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "loyalty_cards_sequence"
    )
    @SequenceGenerator(
            name = "loyalty_cards_sequence",
            allocationSize = 1
    )
    private Long id;
    private String cardNumber;

    @OneToOne
    private User owner;

    @Enumerated(EnumType.STRING)
    private LoyaltyCardLevel level;

    @Enumerated(EnumType.STRING)
    private LoyaltyCardStatus status;

    private Date createDate;
    private Date updateDate;

    public void setNextLevel() {
        this.level = level.getNext();
    }

    public void setPreviousLevel() {
        this.level = level.getPrevious();
    }

}
