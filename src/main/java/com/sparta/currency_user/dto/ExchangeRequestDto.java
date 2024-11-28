package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.ExchangeRequest;
import com.sparta.currency_user.entity.User;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ExchangeRequestDto {

    @NotNull(message = "User ID는 필수 입력 값입니다.")
    private Long userId;

    @NotNull(message = "Currency ID는 필수 입력 값입니다.")
    private Long currencyId;

    @NotNull(message = "Amount Before Exchange는 필수 입력 값입니다.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount Before Exchange는 0보다 커야 합니다.")
    private BigDecimal amountBeforeExchange;

    // toEntity 메서드 추가
    public ExchangeRequest toEntity(User user, Currency currency, BigDecimal amountAfterExchange, String status) {
        return new ExchangeRequest(user, currency, amountBeforeExchange, amountAfterExchange, status);
    }
}
