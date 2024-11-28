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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExchangeRequestService {
    private final ExchangeRequestRepository exchangeRequestRepository;
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;

    @Transactional
    public ExchangeResponseDto createExchangeRequest(ExchangeRequestDto exchangeRequestDto) {
        User user = userRepository.findById(exchangeRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        Currency currency = currencyRepository.findById(exchangeRequestDto.getCurrencyId())
                .orElseThrow(() -> new IllegalArgumentException("통화를 찾을 수 없습니다."));

        BigDecimal amountAfterExchange = exchangeRequestDto.getAmountBeforeExchange()
                .divide(currency.getExchangeRate(), 2, RoundingMode.HALF_UP);

        ExchangeRequest exchangeRequest = exchangeRequestDto.toEntity(user, currency, amountAfterExchange, "normal");
        exchangeRequest = exchangeRequestRepository.save(exchangeRequest);

        return new ExchangeResponseDto(exchangeRequest);
    }

    public List<ExchangeResponseDto> getExchangeRequestsByUserId(Long userId) {
        List<ExchangeRequest> exchangeRequests = exchangeRequestRepository.findByUserId(userId);
        return exchangeRequests.stream().map(ExchangeResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public ExchangeResponseDto cancelExchangeRequest(Long id) {
        ExchangeRequest exchangeRequest = exchangeRequestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("환전 요청을 찾을 수 없습니다."));
        exchangeRequest.setStatus("cancelled");
        return new ExchangeResponseDto(exchangeRequest);
    }
}
