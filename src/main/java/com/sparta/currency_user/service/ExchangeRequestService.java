package com.sparta.currency_user.service;

import com.sparta.currency_user.dto.ExchangeRequestDto;
import com.sparta.currency_user.dto.ExchangeResponseDto;
import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.ExchangeRequest;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.repository.CurrencyRepository;
import com.sparta.currency_user.repository.ExchangeRequestRepository;
import com.sparta.currency_user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeRequestService {

    private final ExchangeRequestRepository exchangeRequestRepository;
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;

    @Transactional
    public ExchangeResponseDto createExchangeRequest(ExchangeRequestDto exchangeRequestDto) {
        User user = userRepository.findById(exchangeRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        Currency currency = currencyRepository.findById(exchangeRequestDto.getCurrencyId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid currency ID"));

        BigDecimal exchangeRate = currency.getExchangeRate();
        BigDecimal amountAfterExchange = exchangeRequestDto.getAmountBeforeExchange().divide(exchangeRate, 2, RoundingMode.HALF_UP);

        ExchangeRequest exchangeRequest = new ExchangeRequest(user, currency, exchangeRequestDto.getAmountBeforeExchange(), amountAfterExchange, "normal");
        exchangeRequest = exchangeRequestRepository.save(exchangeRequest);
        return new ExchangeResponseDto(exchangeRequest);
    }

    public List<ExchangeResponseDto> getExchangeRequestsByUserId(Long userId) {
        return exchangeRequestRepository.findByUserId(userId).stream().map(ExchangeResponseDto::new).toList();
    }

    @Transactional
    public ExchangeResponseDto cancelExchangeRequest(Long requestId) {
        ExchangeRequest exchangeRequest = exchangeRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid request ID"));
        exchangeRequest.setStatus("cancelled");
        exchangeRequest = exchangeRequestRepository.save(exchangeRequest);
        return new ExchangeResponseDto(exchangeRequest);
    }
}
