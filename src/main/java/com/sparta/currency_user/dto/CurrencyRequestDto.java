package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.Currency;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CurrencyRequestDto {

    @NotBlank(message = "Currency Name은 필수 입력 값입니다.")
    private String currencyName;

    @NotNull(message = "Exchange Rate은 필수 입력 값입니다.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Exchange Rate은 0보다 커야 합니다.")
    private BigDecimal exchangeRate;

    @NotBlank(message = "Symbol은 필수 입력 값입니다.")
    private String symbol;

    // toEntity 메서드 추가
    public Currency toEntity() {
        return new Currency(currencyName, exchangeRate, symbol);
    }
}
