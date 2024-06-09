package ru.ecomshop.userservice.controller.loayaltyCard;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ecomshop.userservice.model.dto.loayaltyCard.LoyaltyCardResponse;
import ru.ecomshop.userservice.model.entity.enums.LoyaltyCardLevel;
import ru.ecomshop.userservice.service.loyaltyCard.LoyaltyCardService;

import java.util.Collection;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/loyaltyCards")
@RequiredArgsConstructor
public class LoyaltyCardController implements LoyaltyCardApi {

    private final LoyaltyCardService loyaltyCardService;

    @Override
    @GetMapping
    public ResponseEntity<Collection<LoyaltyCardResponse>> getAllLoyaltyCards() {
        return ok(loyaltyCardService.getAllLoyaltyCards());
    }

    @Override
    @GetMapping("/{loyaltyCardId}")
    public ResponseEntity<LoyaltyCardResponse> getLoyaltyCardById(@PathVariable Long loyaltyCardId) {
        return ok(loyaltyCardService.getLoyaltyCardById(loyaltyCardId));
    }

    @Override
    @GetMapping("/user/{userId}")
    public ResponseEntity<LoyaltyCardResponse> getLoyaltyCardByUser(@PathVariable Long userId) {
        return ok(loyaltyCardService.getLoyaltyCardByUser(userId));
    }

    @Override
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<LoyaltyCardResponse> deactivateLoyaltyCardByUser(@PathVariable Long userId) {
        loyaltyCardService.deactivateLoyaltyCardByUser(userId);
        return ok().build();
    }

    @Override
    @PostMapping("/user/{userId}")
    public ResponseEntity<LoyaltyCardResponse> activateLoyaltyCardByUser(@PathVariable Long userId) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(loyaltyCardService.activateLoyaltyCardByUser(userId));
    }

    @Override
    @PutMapping("/user/{userId}")
    public ResponseEntity<LoyaltyCardResponse> updateLoyaltyCardLevelByUser(@PathVariable Long userId,
                                                                    @RequestBody LoyaltyCardLevel loyaltyCardLevel) {
        return ok(loyaltyCardService.updateLoyaltyCardLevelByUser(userId, loyaltyCardLevel));
    }

    @GetMapping("/user/{userId}/previous-level")
    public ResponseEntity<LoyaltyCardResponse> setPreviousLoyaltyCardLevelByUser(@PathVariable Long userId) {
        return ok(loyaltyCardService.setPreviousLoyaltyCardLevelByUser(userId));
    }

    @GetMapping("/user/{userId}/next-level")
    public ResponseEntity<LoyaltyCardResponse> setNextLoyaltyCardLevelByUser(@PathVariable Long userId) {
        return ok(loyaltyCardService.setNextLoyaltyCardLevelByUser(userId));
    }

}
