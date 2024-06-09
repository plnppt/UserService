package ru.ecomshop.userservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import ru.ecomshop.userservice.model.dto.user.request.UpdateUserRequest;
import ru.ecomshop.userservice.model.entity.enums.AccountRole;
import ru.ecomshop.userservice.model.entity.enums.AccountStatus;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "users_sequence"
    )
    @SequenceGenerator(
            name = "users_sequence",
            allocationSize = 1
    )
    private Long id;

    private String name;
    private String surname;
    private String patronymic;
    private Date birthdate;
    private String address;

    private String phoneNumber;
    private String email;

    @ElementCollection(targetClass = AccountRole.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<AccountRole> accountRoles;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    @OneToOne
    private LoyaltyCard loyaltyCard;

    private Date registrationDate;
    private Date updateDate;

    //TODO: Перенести в маппер
    public void updateFromRequest(UpdateUserRequest request) {
        this.name = request.name();
        this.surname = request.surname();
        this.patronymic = request.patronymic();
        this.birthdate = request.birthdate();
        this.address = request.address();
        this.phoneNumber = request.phoneNumber();
        this.email = request.email();
        this.accountRoles = request.accountRoles();
        this.accountStatus = request.accountStatus();
        this.loyaltyCard = request.loyaltyCard();
        this.updateDate = new Date();
    }

}
