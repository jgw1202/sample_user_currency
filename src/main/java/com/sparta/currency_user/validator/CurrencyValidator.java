package com.sparta.currency_user.validator;

import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CurrencyValidator {

    private final CurrencyRepository currencyRepository;

    @PostConstruct
    public void validateCurrencyExchangeRates() {
        List<Currency> currencies = currencyRepository.findAll();

        for (Currency currency : currencies) {
            BigDecimal exchangeRate = currency.getExchangeRate();

            // 환율이 0이거나 음수이거나 지정된 범위를 벗어나는 경우
            if (exchangeRate.compareTo(BigDecimal.ZERO) <= 0 || exchangeRate.compareTo(new BigDecimal(1000)) > 0) {
                log.error("Invalid exchange rate for currency: {} (ID: {}) - exchange rate: {}", currency.getCurrencyName(), currency.getId(), exchangeRate);
            }
        }
    }
}
