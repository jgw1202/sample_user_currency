package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.ExchangeRequest;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ExchangeResponseDto {
    private Long id;
    private Long userId;
    private Long currencyId;
    private BigDecimal amountBeforeExchange;
    private BigDecimal amountAfterExchange;
    private String status;

    public ExchangeResponseDto(ExchangeRequest exchangeRequest) {
        this.id = exchangeRequest.getId();
        this.userId = exchangeRequest.getUser().getId();
        this.currencyId = exchangeRequest.getCurrency().getId();
        this.amountBeforeExchange = exchangeRequest.getAmountBeforeExchange();
        this.amountAfterExchange = exchangeRequest.getAmountAfterExchange();
        this.status = exchangeRequest.getStatus();
    }
}
