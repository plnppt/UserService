package ru.ecomshop.userservice.advice;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdviceResponse {

    private String errorCode;
    private String errorMessage;
}
