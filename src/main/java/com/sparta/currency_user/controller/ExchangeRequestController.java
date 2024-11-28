package com.sparta.currency_user.controller;

import com.sparta.currency_user.dto.ExchangeRequestDto;
import com.sparta.currency_user.dto.ExchangeResponseDto;
import com.sparta.currency_user.service.ExchangeRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exchange-requests")
@RequiredArgsConstructor
public class ExchangeRequestController {

    private final ExchangeRequestService exchangeRequestService;

    @PostMapping
    public ResponseEntity<ExchangeResponseDto> createExchangeRequest(@RequestBody ExchangeRequestDto exchangeRequestDto) {
        return ResponseEntity.ok().body(exchangeRequestService.createExchangeRequest(exchangeRequestDto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ExchangeResponseDto>> getExchangeRequestsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok().body(exchangeRequestService.getExchangeRequestsByUserId(userId));
    }

    @PutMapping("/{requestId}/cancel")
    public ResponseEntity<ExchangeResponseDto> cancelExchangeRequest(@PathVariable Long requestId) {
        return ResponseEntity.ok().body(exchangeRequestService.cancelExchangeRequest(requestId));
    }
}
