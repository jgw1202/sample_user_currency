package com.sparta.currency_user.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ExchangeRequestDto {
    private Long userId;
    private Long currencyId;
    private BigDecimal amountBeforeExchange;
}
