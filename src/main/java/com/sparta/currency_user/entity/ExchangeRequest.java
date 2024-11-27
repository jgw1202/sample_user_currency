package com.sparta.currency_user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ExchangeRequest extends Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;

    @Column(nullable = false)
    private BigDecimal amountBeforeExchange;

    @Column(nullable = false)
    private BigDecimal amountAfterExchange;

    @Column(nullable = false)
    private String status;

    public ExchangeRequest(User user, Currency currency, BigDecimal amountBeforeExchange, BigDecimal amountAfterExchange, String status) {
        this.user = user;
        this.currency = currency;
        this.amountBeforeExchange = amountBeforeExchange;
        this.amountAfterExchange = amountAfterExchange;
        this.status = status;
    }

}
