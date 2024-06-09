package ru.ecomshop.userservice.service.loyaltyCard;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ecomshop.userservice.model.dto.loayaltyCard.LoyaltyCardResponse;
import ru.ecomshop.userservice.model.entity.LoyaltyCard;
import ru.ecomshop.userservice.model.entity.enums.LoyaltyCardLevel;
import ru.ecomshop.userservice.model.mapper.loayaltyCard.LoyaltyCardMapper;
import ru.ecomshop.userservice.model.mapper.user.UserMapper;
import ru.ecomshop.userservice.repository.loyaltyCard.LoyaltyCardRepository;
import ru.ecomshop.userservice.service.user.UserService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static ru.ecomshop.userservice.model.entity.enums.LoyaltyCardLevel.BRONZE;
import static ru.ecomshop.userservice.model.entity.enums.LoyaltyCardStatus.ACTIVE;
import static ru.ecomshop.userservice.model.entity.enums.LoyaltyCardStatus.DISABLE;

@Service
@AllArgsConstructor
public class LoyaltyCardService {

    private final LoyaltyCardRepository loyaltyCardRepository;

    private final LoyaltyCardMapper loyaltyCardMapper;
    private final UserMapper userMapper;

    private final UserService userService;

    public List<LoyaltyCardResponse> getAllLoyaltyCards() {
        return loyaltyCardRepository.findAll().stream()
                .map(loyaltyCardMapper::map)
                .toList();
    }

    public LoyaltyCardResponse getLoyaltyCardById(Long id) {
        var loyaltyCard = processFindLoyaltyCardById(id);
        return loyaltyCardMapper.map(loyaltyCard);
    }

    public LoyaltyCardResponse getLoyaltyCardByUser(Long userId) {
        var loyaltyCard = processFindLoyaltyCardByUser(userId);
        return loyaltyCardMapper.map(loyaltyCard);
    }

    @Transactional
    public void deactivateLoyaltyCardByUser(Long userId) {
        var loyaltyCard = processFindLoyaltyCardByUser(userId);
        loyaltyCard.setStatus(DISABLE);
        loyaltyCard.setUpdateDate(new Date());
    }

    public LoyaltyCardResponse activateLoyaltyCardByUser(Long userId) {
        LoyaltyCard newLoyaltyCard;
        Optional<LoyaltyCard> loyaltyCard = loyaltyCardRepository.findByOwnerIdAndStatus(userId, ACTIVE);
        if (loyaltyCard.isEmpty()) {
            var user = userService.getUserById(userId);
            //TODO: Продумать, как уйти от builder в entity
            newLoyaltyCard = LoyaltyCard.builder()
                    //TODO: Написать генерацию номера карты
                    .cardNumber(UUID.randomUUID().toString())
                    //TODO: Сделавть черз маппер, мб сделать DTO
                    .owner(userMapper.map(user))
                    .level(BRONZE)
                    .status(ACTIVE)
                    .createDate(new Date()).build();
        } else {
            throw new EntityExistsException("У данного пользователя уже есть активная карта лояльности");
        }
        return loyaltyCardMapper.map(loyaltyCardRepository.save(newLoyaltyCard));
    }

    @Transactional
    public LoyaltyCardResponse updateLoyaltyCardLevelByUser(Long userId, LoyaltyCardLevel loyaltyCardLevel) {
        var loyaltyCard = processFindLoyaltyCardByUser(userId);
        loyaltyCard.setLevel(loyaltyCardLevel);
        loyaltyCard.setUpdateDate(new Date());

        return loyaltyCardMapper.map(loyaltyCard);
    }

    @Transactional
    public LoyaltyCardResponse setPreviousLoyaltyCardLevelByUser(Long userId) {
        var loyaltyCard = processFindLoyaltyCardByUser(userId);
        loyaltyCard.setPreviousLevel();
        loyaltyCard.setUpdateDate(new Date());

        return loyaltyCardMapper.map(loyaltyCard);
    }

    @Transactional
    public LoyaltyCardResponse setNextLoyaltyCardLevelByUser(Long userId) {
        var loyaltyCard = processFindLoyaltyCardByUser(userId);
        loyaltyCard.setNextLevel();
        loyaltyCard.setUpdateDate(new Date());

        return loyaltyCardMapper.map(loyaltyCard);
    }


    private LoyaltyCard processFindLoyaltyCardById(Long id) {
        return loyaltyCardRepository.findByIdAndStatus(id, ACTIVE).orElseThrow(
                () -> new EntityNotFoundException("Активной карты лояльности по такому идентификатору не существует")
        );
    }

    private LoyaltyCard processFindLoyaltyCardByUser(Long userId) {
        return loyaltyCardRepository.findByOwnerIdAndStatus(userId, ACTIVE).orElseThrow(
                () -> new EntityNotFoundException("Активной карты лояльности у данного пользователя не существует")
        );
    }

}
