package ru.ecomshop.userservice.controller.loayaltyCard;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import ru.ecomshop.userservice.advice.AdviceResponse;
import ru.ecomshop.userservice.model.dto.loayaltyCard.LoyaltyCardResponse;
import ru.ecomshop.userservice.model.entity.enums.LoyaltyCardLevel;

import java.util.Collection;

@Tag(name = "Loyalty Card API", description = "API для работы с картами лоялности пользователей")
public interface LoyaltyCardApi {

    @ApiResponse(responseCode = "200",
            description = "Успешный возврат всех новостей",
            content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = LoyaltyCardResponse.class)
                            )
                    )
            }
    )
    @Operation(summary = "Возвращает все карты лояльности")
    ResponseEntity<Collection<LoyaltyCardResponse>> getAllLoyaltyCards();

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный возврат карты лояльности",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = LoyaltyCardResponse.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Не найдена карта лоялности по переданному уникальному идентификатору",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AdviceResponse.class)
                            )
                    }
            )
    })
    @Operation(summary = "Возвращает карту лояльности по переданнрому уникальному идентификатору")
    ResponseEntity<LoyaltyCardResponse> getLoyaltyCardById(
            @Parameter(description = "Уникальный идентификатор карты лояльности") Long loyaltyCardId
    );

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный возврат карты лояльности",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = LoyaltyCardResponse.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Не найдена карта лоялности у данного пользователя",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AdviceResponse.class)
                            )
                    }
            )
    })
    @Operation(summary = "Возвращает карту лояльности по переданнрому уникальному идентификатору пользователя")
    ResponseEntity<LoyaltyCardResponse> getLoyaltyCardByUser(
            @Parameter(description = "Уникальный идентификатор пользователя") Long userId
    );

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешная деактивация карты лояльности",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = LoyaltyCardResponse.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Не найдена активная карта у данного пользователя",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = LoyaltyCardResponse.class)
                            )
                    }
            )
    })
    @Operation(summary = "Деактивирует карту лояльности для переданного пользователя")
    ResponseEntity<LoyaltyCardResponse> deactivateLoyaltyCardByUser(
            @RequestBody(description = "Уникальный идентификатор пользователя") Long userId
    );

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешная активация карты лояльности",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = LoyaltyCardResponse.class)
                            )
                    }
            )
    })
    @Operation(summary = "Активирует карту лояльности для переданного пользователя")
    ResponseEntity<LoyaltyCardResponse> activateLoyaltyCardByUser(
            @RequestBody(description = "Уникальный идентификатор пользователя") Long userId
    );

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешное изменение уровня карты лоялности",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = LoyaltyCardResponse.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Не найдена активная карта у данного пользователя",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AdviceResponse.class)
                            )
                    }
            )
    })
    @Operation(summary = "Изменяет уровень карты лоялности для переданного пользователя")
    ResponseEntity<LoyaltyCardResponse> updateLoyaltyCardLevelByUser(
            @Parameter(description = "Уникальный идентификатор пользователя") Long userId,
            @RequestBody(description = "Новый уровень карты лояльности") LoyaltyCardLevel loyaltyCardLevel
            );

}
